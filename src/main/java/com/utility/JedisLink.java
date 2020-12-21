package com.utility;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisLink {
    static JedisPool pool = null;
    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(5);
        pool = new JedisPool(config,"localhost",6379);
    }
//    index表示的是使用的redis中的哪一个db
    public static Jedis redisLink(int index){
        Jedis resource = pool.getResource();
        resource.select(index);
        return resource;
    }

}