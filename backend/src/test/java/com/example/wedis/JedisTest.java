package com.example.wedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class JedisTest {
    @Test
    public void test() {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.select(0);
        System.out.println(jedis.keys("*"));
        System.out.println(jedis.type("mysort"));
        System.out.println(jedis.lrange("mylist", 0, -1));
    }
}
