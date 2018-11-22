package com.hui.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hui.cache.RedisDao;
import com.hui.dao.SeckillMapper;
import com.hui.entity.Seckill;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;




public class RedisTest {
	  private long id = 1001;

	    @Autowired
	    private RedisDao redisDao=new RedisDao("localhost",6379);

	    @Autowired
	    private SeckillMapper seckillMapper;
	   // @Test
	    public void test(){
	        JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost", 6379, 100); 
	        Jedis jedis = pool.getResource();
	        jedis.set("name", "cc");
	        String name = jedis.get("name");
	       // System.out.println(name);
	        String key = "s10001";
	        //System.out.println(key.getBytes()[0]);
	    }
	   @Test
	    public void getSeckill() throws Exception {
	    	
	           Seckill seckill = seckillMapper.queryById(id);
	            if (seckill != null) {
	                String result = redisDao.putSeckill(seckill);
	                System.out.println(result);
	                seckill = redisDao.getSeckill(id);
	                System.out.println(seckill);
	            }
	   }
	   
//	    @Test
	    public void putSeckill() throws Exception {

	    }

}
