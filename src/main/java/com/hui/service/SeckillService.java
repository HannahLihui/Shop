package com.hui.service;

import java.util.List;

import com.hui.dto.Exposer;
import com.hui.dto.SeckillExecution;
import com.hui.entity.Seckill;
import com.hui.entity.Time;
import com.hui.exception.RepeatKillException;
import com.hui.exception.SeckillCloseException;
import com.hui.exception.SeckillException;

public interface SeckillService {
	/**
	   *  ��ѯȫ������ɱ��¼.
	   * @return ���ݿ������е���ɱ��¼
	   */
	  List<Seckill> getSeckillList();
	  List<Time> queryTime();
	  /**
	   *   ��ѯ������ɱ��¼
	   * @param seckillId   ��ɱ��¼��ID
	   * @return   ����ID��ѯ�����ļ�¼��Ϣ
	   */
	  Seckill getById(long seckillId);

	  /**
	   * ����ɱ����ʱ�����ɱ�ӿڵĵ�ַ,�������ϵͳʱ�����ɱ��ַ
	   * @param seckillId  ��ɱ��ƷId
	   * @return  ���ݶ�Ӧ��״̬���ض�Ӧ��״̬ʵ��
	   */
	  Exposer exportSeckillUrl(long seckillId);

	  /**
	   * ִ����ɱ����,�п�����ʧ�ܵ�,ʧ�����Ǿ��׳��쳣
	   * @param seckillId  ��ɱ����ƷID
	   * @param userPhone �ֻ�����
	   * @param md5   md5����ֵ
	   * @return   ���ݲ�ͬ�Ľ�����ز�ͬ��ʵ����Ϣ
	   */
	  SeckillExecution executeSeckill(long seckillId,long userPhone,String md5)throws SeckillException,RepeatKillException,SeckillCloseException;
	  
	  SeckillExecution executeSeckillProcedure(long seckillId,long userPhone,String md5);
}
