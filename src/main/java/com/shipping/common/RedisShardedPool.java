package com.shipping.common;

import com.shipping.util.PropertiesUtil;
import redis.clients.jedis.*;
import redis.clients.util.Hashing;
import redis.clients.util.Sharded;

import java.util.ArrayList;

public class RedisShardedPool {

    private static ShardedJedisPool pool;//sharded jedis连接池
    private static Integer maxTotal = Integer.parseInt(PropertiesUtil.getProperty("redis.max.total", "20"));//最大连接数
    private static Integer maxTdle = Integer.parseInt(PropertiesUtil.getProperty("redis.max.idle", "20"));//在jedispool中最大的idle状态(空闲的)jedis实例的个数
    private static Integer minTdle = Integer.parseInt(PropertiesUtil.getProperty("redis.min.idle", "20"));//在jedispool中最小的idle状态(空闲的)jedis实例的个数

    //在borrow一个jedis实例的时候,是否要进行验证操作,如果赋值为true,则得到的jedis实例肯定是可以用的。
    private static Boolean testOnBorrow = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.borrow", "true"));
    //在return一个jedis实例的时候,是否要进行验证操作,如果赋值为true,则放回jedispool的jedis实例肯定是可以用的。
    private static Boolean testOnReturn = Boolean.parseBoolean(PropertiesUtil.getProperty("redis.test.return", "true"));

    private static String redis1Ip = PropertiesUtil.getProperty("redis1.ip");
    private static Integer redis1Port = Integer.parseInt(PropertiesUtil.getProperty("redis1.port"));
    private static String redis2Ip = PropertiesUtil.getProperty("redis2.ip");
    private static Integer redis2Port = Integer.parseInt(PropertiesUtil.getProperty("redis2.port"));

    private static void initPool() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxTotal);
        config.setMaxIdle(maxTdle);
        config.setMinIdle(minTdle);
        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);
        //连接耗尽的时候，是否阻塞,false会抛出异常,true阻塞直到超时，默认为true.
        config.setBlockWhenExhausted(true);

        JedisShardInfo info1 = new JedisShardInfo(redis1Ip, redis1Port, 1000 * 2);
        JedisShardInfo info2 = new JedisShardInfo(redis2Ip, redis2Port, 1000 * 2);
        ArrayList<JedisShardInfo> jedisShardInfoList = new ArrayList<>(2);
        jedisShardInfoList.add(info1);
        jedisShardInfoList.add(info2);
        pool = new ShardedJedisPool(config, jedisShardInfoList, Hashing.MURMUR_HASH, Sharded.DEFAULT_KEY_TAG_PATTERN);
    }

    static {
        initPool();
    }

    public static ShardedJedis getJedis() {
        return pool.getResource();
    }

    public static void returnBrokenResource(ShardedJedis jedis) {
        pool.returnBrokenResource(jedis);
    }

    public static void returnResource(ShardedJedis jedis) {
        pool.returnResource(jedis);
    }

    public static void main(String[] args) {
        ShardedJedis jedis = pool.getResource();
        for (int i = 0; i < 10; i++) {
            jedis.set("geelykey"+i, "geelyvalue"+i);
        }
        returnResource(jedis);
        pool.destroy();//临时调用，销毁连接池的所有连接
        System.out.println("program is end");
    }
}
