package com.babor.redis;

import redis.clients.jedis.Jedis;


public class RedisStart {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.set("foo", "bar");
        String value = jedis.get("foo");
        jedis.incr("a");

        System.out.printf(value);

    }
}
