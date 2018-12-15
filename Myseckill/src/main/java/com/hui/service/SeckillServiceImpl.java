package com.hui.service;

import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.hui.dao.SeckillMapper;
import com.hui.dao.SuccessKilledMapper;
import com.hui.dto.Exposer;
import com.hui.dto.SeckillExecution;
import com.hui.entity.Seckill;
import com.hui.entity.SuccessKilled;
import com.hui.entity.Time;
import com.hui.enums.SeckillStatEnum;
import com.hui.exception.RepeatKillException;
import com.hui.exception.SeckillCloseException;
import com.hui.exception.SeckillException;
import com.hui.cache.*;;
@Service(value="seckillService")
public class SeckillServiceImpl implements SeckillService {
	  private Logger logger = LoggerFactory.getLogger(this.getClass());
	    /* ����һ����ֵ,���ڻ���*/
	    private final String salt = "thisIsASaltValue";

	    @Autowired
	    private SeckillMapper seckillMapper;
	    @Autowired
	    private SuccessKilledMapper successKilledMapper;
		public List<Seckill> getSeckillList() {
			System.out.println(seckillMapper.queryAll(0, 4));
			// TODO Auto-generated method stub
			return seckillMapper.queryAll(0, 4);
		}
		public Seckill getById(long seckillId) {
		   // System.out.println("���������");
			// TODO Auto-generated method stub
			return seckillMapper.queryById(seckillId);
			
		}
		 @Autowired
		    private RedisDao redisDao;
		public Exposer exportSeckillUrl(long seckillId) {
		
			 Seckill seckill = redisDao.getSeckill(seckillId);
			 System.out.println(seckill);
		        if (seckill == null) {
		        	// System.out.println("�ǲ���Ϊ�գ�");
		            // �������ݿ��ȡ����
		        	 System.out.println(seckillId);
		            seckill = seckillMapper.queryById(seckillId);
		            System.out.println(seckill);
		            if (seckill == null) {
		                return new Exposer(false, seckillId);
		            } else {
		                // ����redis
		                redisDao.putSeckill(seckill);
		            }
		        }
		       // System.out.println("ִ�е�������");
		        
		       // LocalDateTime dateTime=LocalDateTime.now();
		        Timestamp startTime = seckill.getStartTime();
		        Timestamp endTime = seckill.getEndTime();
		       // Timestamp nowTime = Timestamp.valueOf(dateTime);
		        Timestamp nowTime = new Timestamp(System.currentTimeMillis()); 
		        System.out.println(nowTime);
		        System.out.println(startTime);
		        if (nowTime.after(startTime) && nowTime.before(endTime)) {
		            //��ɱ����,������ɱ��Ʒ��id,�ø��ӿڼ��ܵ�md5
		            String md5 = getMd5(seckillId);
		            return new Exposer(true, md5, seckillId);
		        }
		        return new Exposer(false, seckillId, nowTime, startTime, endTime);
			// TODO Auto-generated method stub
		
		}
		private String getMd5(long seckillId) {
	        String base = seckillId + "/" + salt;
	        return DigestUtils.md5DigestAsHex(base.getBytes());
	    }
		@Transactional
		public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
				throws SeckillException, RepeatKillException, SeckillCloseException {
			// TODO Auto-generated method stub
			 if (md5 == null || !md5.equals(getMd5(seckillId))) {
		            logger.error("md5是空,秒杀数据重写");
		            throw new SeckillException("seckill data rewrite");
		        }
		        // ִ����ɱҵ���߼�
		      Timestamp nowTime=new Timestamp(System.currentTimeMillis()); 

		        try {
		            //ִ�м�������
		            int reduceNumber = seckillMapper.reduceNumber(seckillId);
		            if (reduceNumber <= 0) {
		                logger.warn("商品客库存不足");
		                throw new SeckillCloseException("seckill is closed");
		            } else {
		                // �������ټ��ٵ�������Ϊ0��,��ɱ�ɹ��˾�����һ����ɱ�ɹ���ϸ
		                int insertCount = successKilledMapper.insertSuccessKilled(seckillId, userPhone);
		                // �鿴�Ƿ��ظ�����,���û��Ƿ��ظ���ɱ
		                if (insertCount <= 0) {
		                    throw new RepeatKillException("seckill repeated");
		                } else {
		                    // ��ɱ�ɹ���,������������ɹ���ɱ����Ϣ
		                    SuccessKilled successKilled = successKilledMapper.queryByIdWithSeckill(seckillId, userPhone);
		                    return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, successKilled);
		                }
		            }
		        } catch (RepeatKillException e1) {
		            throw e1;
		        } catch( SeckillCloseException e) {
		        	throw e;
		        }
		        catch (Exception e) {
		            logger.error(e.getMessage(), e);
		            // �ѱ������쳣ת��Ϊ����ʱ�쳣
		            throw new SeckillException("seckill inner error : " + e.getMessage());
		        }
		}
		public List<Time> queryTime() {
			// TODO Auto-generated method stub
			return seckillMapper.queryTime();
		}
		public SeckillExecution executeSeckillProcedure(long seckillId, long userPhone, String md5) {
			// TODO Auto-generated method stub
			   if (md5 == null || !md5.equals(getMd5(seckillId))) {
		            return new SeckillExecution(seckillId, SeckillStatEnum.DATE_REWRITE);
		        }
		        LocalDateTime killTime = LocalDateTime.now();
		        Map<String, Object> map = new HashMap<String, Object>();
		        map.put("seckillId", seckillId);
		        map.put("phone", userPhone);
		        map.put("killTime", killTime);
		        map.put("result", null);
		        // ִ�д������,result������
		        try {
		            seckillMapper.killByProcedure(map);
		            // ��ȡresult
		            int result = MapUtils.getInteger(map, "result", -2);
		            if (result == 1) {
		                SuccessKilled successKilled = successKilledMapper.queryByIdWithSeckill(seckillId, userPhone);
		                return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, successKilled);
		            } else {
		                return new SeckillExecution(seckillId, SeckillStatEnum.stateOf(result));
		            }
		        } catch (Exception e) {
		            logger.error(e.getMessage(), e);
		            return new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
		        }
		}


	    /**
	     * ��ѯȫ������ɱ��¼.
	     *
	     * @return ���ݿ������е���ɱ��¼
	     */
	  
}
