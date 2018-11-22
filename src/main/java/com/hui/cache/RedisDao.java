package com.hui.cache;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.hui.entity.Seckill;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;



public class RedisDao {
	  private final Logger logger = LoggerFactory.getLogger(this.getClass());

	    private final  JedisPool jedisPool;

	    private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);

	    public RedisDao(String ip, int port) {
	        jedisPool = new JedisPool(ip, port);
	    }
	    public Seckill getSeckill(long seckillId) {
	        // redis����ҵ���߼�
	    	try {
	    //	JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost", 6379, 100); 
	    	Jedis jedis = jedisPool.getResource();
	        try  {
	        	
	            String key =  "seckill:"+seckillId;
	            // ��û��ʵ���ڲ����л�����
	            //get->byte[]�ֽ�����->�����л�>Object(Seckill)
	            // �����Զ���ķ�ʽ���л�
	            // �����ȡ��
	            
	            byte[] bytes = jedis.get(key.getBytes());
	            if(bytes==null)
	            	return null;
	            if (bytes != null) {
	                // �ն���
	                Seckill seckill = schema.newMessage();
	                ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);
	                // seckill�������л�
	                return seckill;
	            }
	        }finally {
	        	jedis.close();
	        }
	        } catch (Exception e) {
	            logger.error(e.getMessage(), e);
	        }
	        return null;
	    }
	    public String putSeckill(Seckill seckill) {
	        //  set Object(Seckill) -> ���л� -> byte[]
	    	try {
	    	Jedis jedis = jedisPool.getResource();
	        try  {
	            String key = "seckill:" + seckill.getSeckillId();
	            byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema,
	                    LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
	            // ��ʱ����
	            int timeout=60*60;
	            return jedis.setex(key.getBytes(), timeout, bytes);
	            }finally {
	            	jedis.close();
	            }
	        } catch (Exception e) {
	            logger.error(e.getMessage(), e);
	        }
	        return null;
	    }
	  

}

