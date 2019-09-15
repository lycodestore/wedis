package com.example.wedis.service;

import com.alibaba.fastjson.JSONObject;
import com.example.wedis.utils.Response;

public interface QueryService {
    Response getKeys(JSONObject jsonObject);
    Response getData(JSONObject jsonObject);
}
