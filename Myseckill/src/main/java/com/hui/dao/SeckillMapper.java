package com.hui.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hui.dto.SeckillExecution;
import com.hui.entity.Seckill;
import com.hui.entity.Time;

public interface SeckillMapper {
	
	 int reduceNumber(@Param("seckillId") long seckillId);

	  
	   Seckill queryById(@Param("seckillId") long seckillId);
	   List<Time> queryTime();
	   void killByProcedure(Map<String,Object> paramMap);
	   /**
	    * ����һ��ƫ����ȥ��ѯ��ɱ����Ʒ�б�.
	    *
	    * @param offset ƫ����
	    * @param limit  ���Ʋ�ѯ�����ݸ���
	    * @return ����ƫ��������������ݸ���
	    */
	   List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}
