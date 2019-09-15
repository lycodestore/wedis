package com.example.wedis.aop;

import com.example.wedis.utils.ConnectionPool;
import com.example.wedis.utils.ResponseUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class ConnectionAspect {
    @Pointcut("within(com.example.wedis.controller..*)")
    public void matchController() {}

    @Pointcut("within(com.example.wedis.service..*)")
    public void matchService() {}

    @Around("matchController()")
    public Object checkToken(ProceedingJoinPoint joinPoint) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        if (request.getHeader("token") == null) {
            if (!request.getRequestURI().equals("/connect")) {
                Object[] args = joinPoint.getArgs();
                Object result = joinPoint.proceed(args);
                return ResponseUtils.error(40001, "forbidden", null);
            }
        }
        Object[] args = joinPoint.getArgs();
        Object result = joinPoint.proceed(args);
        return result;
    }

    @Around("matchService()()")
    public Object getConnection(ProceedingJoinPoint joinPoint) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();

        if (request.getRequestURI().equals("/connect")) {
            Object[] args = joinPoint.getArgs();
            Object result = joinPoint.proceed(args);
            return result;
        }

        String token = request.getHeader("token");
        if (ConnectionPool.connectionMap.get(token) == null) {
            return ResponseUtils.error(40001, "forbidden", null);
        } else {
            request.setAttribute("connection", ConnectionPool.connectionMap.get(token));
            Object[] args = joinPoint.getArgs();
            Object result = joinPoint.proceed(args);
            return result;
        }
    }

}
