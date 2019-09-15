package com.example.wedis.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.wedis.service.ConnectService;
import com.example.wedis.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

@RestController
public class ConnectController {
    @Autowired
    private ConnectService connectService;

    @PostMapping("/connect")
    public Response getConnection(@RequestHeader LinkedHashMap header, @RequestBody JSONObject body) {
        return connectService.getConnection(body);
    }
}
