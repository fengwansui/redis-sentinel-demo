package com.sylar.test;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

public class RedisSentinelTest {

	public static void main(String[] args) {

		Set<String> sentinels = new HashSet<String>();
		sentinels.add("192.168.10.109:26379");
		JedisSentinelPool pool = null;

		try {
			pool = new JedisSentinelPool("mymaster", sentinels);
			Jedis jedis = pool.getResource();
			System.out.println(jedis.get("jedis"));
			System.out.println(jedis.getClient().getHost());
			pool.returnResource(jedis);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pool != null) {
				pool.destroy();
			}
		}

	}

}
