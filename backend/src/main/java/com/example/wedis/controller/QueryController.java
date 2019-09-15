package com.example.wedis.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.wedis.service.QueryService;
import com.example.wedis.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/query")
public class QueryController {
    @Autowired
    private QueryService queryService;
    @PostMapping("/keys")
    public Response getKeys(@RequestBody JSONObject jsonObject) {
        return queryService.getKeys(jsonObject);
    }

    @PostMapping("/data")
    public Response getData(@RequestBody JSONObject jsonObject) {
        return queryService.getData(jsonObject);
    }
}
