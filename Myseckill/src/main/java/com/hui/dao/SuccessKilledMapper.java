package com.hui.dao;
import org.apache.ibatis.annotations.Param;

import com.hui.entity.SuccessKilled;

public interface SuccessKilledMapper {
	/**
	  * ����һ����ϸ�Ĺ�����Ϣ.
	    *
	    * @param seckillId ��ɱ��Ʒ��ID
	    * @param userPhone �����û����ֻ�����
	    * @return �ɹ�����ͷ���1, ����ͷ���0
	    */
	   int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

	   /**
	    * ������ɱ��Ʒ��ID��ѯ<code>SuccessKilled</code>����ϸ��Ϣ.
	    *
	    * @param seckillId ��ɱ��Ʒ��ID
	    * @param userPhone �����û����ֻ�����
	    * @return ��ɱ��Ʒ����ϸ��Ϣ
	    */
	   SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}
