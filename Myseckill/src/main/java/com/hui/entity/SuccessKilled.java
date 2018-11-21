package com.hui.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class SuccessKilled implements Serializable{
	 private static final long serialVersionUID = 1834437127882846202L;

	    private long seckillId;
	    /* �û����ֻ�����*/
	    private long userPhone;
	    /* ��ɱ��״̬*/
	    private short state;
	    /* ����ʱ��*/
	    private LocalDateTime createTime;
	    /* ���һ,��Ϊһ����Ʒ�ڿ���п϶������,��Ӧ�Ĺ�����ϢҲ�кܶ�*/
	    private Seckill seckill;

	    public SuccessKilled() {
	    }

	    public SuccessKilled(long seckillId, long userPhone, short state, LocalDateTime createTime, Seckill seckill) {
	        this.seckillId = seckillId;
	        this.userPhone = userPhone;
	        this.state = state;
	        this.createTime = createTime;
	        this.seckill = seckill;
	    }

	    public long getSeckillId() {
	        return seckillId;
	    }

	    public void setSeckillId(long seckillId) {
	        this.seckillId = seckillId;
	    }

	    public long getUserPhone() {
	        return userPhone;
	    }

	    public void setUserPhone(long userPhone) {
	        this.userPhone = userPhone;
	    }

	    public short getState() {
	        return state;
	    }

	    public void setState(short state) {
	        this.state = state;
	    }

	    public LocalDateTime getCreateTime() {
	        return createTime;
	    }

	    public void setCreateTime(LocalDateTime createTime) {
	        this.createTime = createTime;
	    }

	    public Seckill getSeckill() {
	        return seckill;
	    }

	    public void setSeckill(Seckill seckill) {
	        this.seckill = seckill;
	    }

	    @Override
	    public String toString() {
	        return "SuccessKilled{" +
	                "����ID=" + seckillId +
	                ", �ֻ�����=" + userPhone +
	                ", ��ɱ״̬=" + state +
	                ", ����ʱ��=" + createTime +
	                ", ��ɱ����Ʒ=" + seckill +
	                '}';
	    }
}
