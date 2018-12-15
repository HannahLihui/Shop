package com.hui.dto;

import com.hui.entity.SuccessKilled;
import com.hui.enums.SeckillStatEnum;

public class SeckillExecution {
	   private long seckillId;
	    /* ִ����ɱ�����״̬   */
	    private int state;
	    /* ״̬�����ı�ʾ   */
	    private String stateInfo;
	    /*  ����ɱ�ɹ�ʱ,��Ҫ������ɱ����Ķ����ȥ  */
	    private SuccessKilled successKilled;

	    /*  ��ɱ�ɹ����ص�ʵ��  */
	    public SeckillExecution(long seckillId, SeckillStatEnum statEnum, SuccessKilled successKilled) {
	        this.seckillId = seckillId;
	        this.state = statEnum.getState();
	        this.stateInfo = statEnum.getInfo();
	        this.successKilled = successKilled;
	    }

	    /*  ��ɱʧ�ܷ��ص�ʵ��  */
	    public SeckillExecution(long seckillId, SeckillStatEnum statEnum) {
	        this.seckillId = seckillId;
	        this.state = statEnum.getState();
	        this.stateInfo = statEnum.getInfo();
	    }

	    public long getSeckillId() {
	        return seckillId;
	    }

	    public void setSeckillId(long seckillId) {
	        this.seckillId = seckillId;
	    }

	    public int getState() {
	        return state;
	    }

	    public void setState(int state) {
	        this.state = state;
	    }

	    public String getStateInfo() {
	        return stateInfo;
	    }

	    public void setStateInfo(String stateInfo) {
	        this.stateInfo = stateInfo;
	    }

	    public SuccessKilled getSuccessKilled() {
	        return successKilled;
	    }

	    public void setSuccessKilled(SuccessKilled successKilled) {
	        this.successKilled = successKilled;
	    }

	    @Override
	    public String toString() {
	        return "SeckillExecution{" +
	                "商品ID=" + seckillId +
	                ", 状态״̬=" + state +
	                ", 状态信息='" + stateInfo + '\'' +
	                ", 秒杀成功=" + successKilled +
	                '}';
	    }
}
