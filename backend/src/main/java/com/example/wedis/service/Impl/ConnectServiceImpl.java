package com.example.wedis.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.example.wedis.service.ConnectService;
import com.example.wedis.utils.ConnectionPool;
import com.example.wedis.utils.Response;
import com.example.wedis.utils.ResponseUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;

@Service
public class ConnectServiceImpl implements ConnectService {

    @Override
    public Response getConnection(JSONObject object) {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String connectName = object.getString("host") + ":" + object.getString("port");
        if (ConnectionPool.connectionMap.containsKey(connectName)) {
            return ResponseUtils.success(null);
        } else {
            try {
                String host = object.getString("host");
                int port = object.getInteger("port");
                Jedis jedis = new Jedis(host, port);
                ConnectionPool.connectionMap.put(host + ":" + port, jedis);
                return ResponseUtils.success(null);
            } catch (Exception e) {
                e.printStackTrace();
                Response response = new Response();
                response.setCode(30001);
                response.setMessage(e.getMessage());
                return response;
            }
        }
    }
}
