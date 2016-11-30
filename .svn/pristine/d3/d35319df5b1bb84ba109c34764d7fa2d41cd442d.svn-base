package com.xhui.recmd.core.redis;

import com.xhui.recmd.core.utils.JsonUtil;
import org.apache.commons.collections.map.HashedMap;
import redis.clients.jedis.*;
import redis.clients.util.Hashing;
import redis.clients.util.Sharded;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by littlehui on 2016/11/18.
 */
public class RedisTemplate {

    static ShardedJedisPool pool;

    private RedisTemplate(Map<String, Integer> hosts, String password) {
        JedisPoolConfig config =new JedisPoolConfig();//Jedis池配置
        config.setMaxIdle(1000 * 10);//对象最大空闲时间
        config.setMaxWaitMillis(1000 * 60);//获取对象时最大等待时间
        //config.setTestOnBorrow(true);
        List<JedisShardInfo> jdsInfoList =new ArrayList<JedisShardInfo>(hosts.size());
        for (String host : hosts.keySet()) {
            JedisShardInfo jedisShardInfo = new JedisShardInfo(host, hosts.get(host));
            jedisShardInfo.setPassword(password);
            jdsInfoList.add(jedisShardInfo);
        }
        pool = new ShardedJedisPool(config, jdsInfoList, Hashing.MURMUR_HASH,
                Sharded.DEFAULT_KEY_TAG_PATTERN);
        shardedJedis = pool.getResource();
    }

    private static RedisTemplate redisTemplate;

    private String preFix = "recmd_";

    private ShardedJedis shardedJedis;

    public static RedisTemplate getInstans() {
        if (redisTemplate != null) {
            return redisTemplate;
        } else {
            Map<String, Integer> hosts = new HashedMap();
            hosts.put("10.5.102.214",6379);
            redisTemplate = new RedisTemplate(hosts, "java2000_wl");
            return redisTemplate;
        }
    }

    public void set(String key, Object value) {
        shardedJedis.set(genKey(key), JsonUtil.toJson(value));
    }

    public void set(String key, Object value, Integer seconds) {
        shardedJedis.set(genKey(key), JsonUtil.toJson(value));
        shardedJedis.expire(genKey(key), seconds);
    }

    public <T> T get(String key, Class<T> lassType) {
        String value = shardedJedis.get(genKey(key));
        if (value != null) {
            return JsonUtil.toObject(value, lassType);
        } else {
            return null;
        }
    }

    public String get(String key) {
        return shardedJedis.get(genKey(key));
    }

    private String genKey(String key) {
        return preFix + key;
    }

    public static void main(String[] args) {
        RedisTemplate.getInstans().set("test", "我是小辉");
        System.out.println(RedisTemplate.getInstans().get("test"));
    }
}
