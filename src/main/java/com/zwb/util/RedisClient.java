package com.zwb.util;

import java.util.Set;

import org.crazycake.shiro.BaseRedisManager;
import org.crazycake.shiro.IRedisManager;
import org.springframework.stereotype.Controller;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


public class RedisClient implements IRedisManager {

	private static JedisPool jedisPool = null;

	public RedisClient(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	protected Jedis getJedis() {
		return jedisPool.getResource();
	}

	@Override
	public byte[] get(byte[] key) {
		return null;
	}

	@Override
	public byte[] set(byte[] key, byte[] value, int expire) {
		return null;
	}

	@Override
	public void del(byte[] key) {
	}

	@Override
	public Long dbSize() {
		return null;
	}

	@Override
	public Set<byte[]> keys(byte[] pattern) {
		return null;
	}

}
