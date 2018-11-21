package com.hui.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.hui.dao.SeckillMapper;
import com.hui.entity.Seckill;

public class SeckillMapperTest {
	 @Resource
	    private SeckillMapper seckillMapper;

  @Test
	    public void reduceNumber() throws Exception {
	   
	        long seckillId=1000;
	        
	        int i = seckillMapper.reduceNumber(seckillId);
	        System.out.println(i);
	    }

    @Test
   
	    public void queryById() throws Exception {
    	
	        long seckillId = 1000;
	        Seckill seckill = seckillMapper.queryById(seckillId);
	        System.out.println(seckill.toString());
	    }

	    @Test
	    public void queryAll() throws Exception {
	        List<Seckill> seckills = seckillMapper.queryAll(0, 4);
	        for (Seckill seckill : seckills) {
	            System.out.println(seckill.toString());
	        }
	    }
}
