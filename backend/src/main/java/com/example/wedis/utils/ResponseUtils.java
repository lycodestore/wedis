package com.example.wedis.utils;

public class ResponseUtils {
    public static Response success(Object result) {
        Response response = new Response();
        response.setCode(20000);
        response.setMessage("success");
        response.setResult(result);
        return response;
    }

    public static Response error(Integer code, String message, Object result) {
        Response response = new Response();
        response.setCode(code);
        response.setMessage(message);
        response.setResult(result);
        return response;
    }
}
