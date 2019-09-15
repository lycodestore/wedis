package com.example.wedis.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.example.wedis.domain.ListData;
import com.example.wedis.domain.StringData;
import com.example.wedis.service.QueryService;
import com.example.wedis.utils.ConnectionUtils;
import com.example.wedis.utils.Response;
import com.example.wedis.utils.ResponseUtils;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.*;

@Service
public class QueryServiceImpl implements QueryService {
    @Override
    public Response getKeys(JSONObject jsonObject) {
        Jedis jedis = ConnectionUtils.getConnection();
        int index = jsonObject.getInteger("index");
        jedis.select(index);
        Set<String> keyNames = jedis.keys("*");
        ArrayList<Map<String, String>> keys = new ArrayList<>();
        for (String keyName: keyNames) {
            String type = jedis.type(keyName);
            Map<String, String> map = new HashMap<>();
            map.put("key", keyName);
            map.put("type", type);
            keys.add(map);
        }
        return ResponseUtils.success(keys);
    }

    @Override
    public Response getData(JSONObject jsonObject) {
        Jedis jedis = ConnectionUtils.getConnection();
        int index = jsonObject.getInteger("index");
        jedis.select(index);
        String type = jsonObject.getString("type");
        String key = jsonObject.getString("key");

        try {
            Long expiration = jedis.ttl(key);
            switch (type) {
                case "string":
                    String value = jedis.get(key);
                    StringData stringData = new StringData(key, value, expiration);
                    return ResponseUtils.success(stringData);
                case "list":
                    List<String> list = jedis.lrange(key, 0, -1);
                    ArrayList dataList = new ArrayList<>();
                    for (int i = 0; i < list.size(); i ++) {
                        Map map = new HashMap<>();
                        map.put("index", i);
                        map.put("value", list.get(i));
                        dataList.add(map);

                    }
                    ListData listData = new ListData(key, expiration, dataList);
                    return ResponseUtils.success(listData);
                default:
                    return ResponseUtils.success(null);
            }
        } catch (Exception e) {
            return ResponseUtils.error(60001, e.getMessage(), null);
        }
    }
}
