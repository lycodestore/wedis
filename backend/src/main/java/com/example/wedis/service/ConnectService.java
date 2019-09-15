package com.example.wedis.service;

import com.alibaba.fastjson.JSONObject;
import com.example.wedis.utils.Response;

public interface ConnectService {
    Response getConnection(JSONObject object);
}
