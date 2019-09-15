package com.example.wedis.utils;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public class ConnectionPool {
    public static Map<String, Jedis> connectionMap = new HashMap<>();
}
