package com.hui.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Seckill implements Serializable{
	  private static final long serialVersionUID = 2912164127598660137L;
	    /* ����ID*/
	    private long seckillId;
	    /*  ��ɱ��Ʒ���� */
	    private String name;
	    /* ��ɱ����Ʒ��� */
	    private int number;
	    /* ��ʼ��ɱ��ʱ�� */
	    private Timestamp startTime;
	    /* ������ɱ��ʱ�� */
	    private Timestamp endTime;
	    /* ������ʱ�� */
	    private Timestamp createTIme;

	    public Seckill() {
	    }

	    public Seckill(long seckillId, String name, int number, Timestamp startTime, Timestamp endTime, Timestamp createTIme) {
	        this.seckillId = seckillId;
	        this.name = name;
	        this.number = number;
	        this.startTime = startTime;
	        this.endTime = endTime;
	        this.createTIme = createTIme;
	    }

	    public long getSeckillId() {
	        return seckillId;
	    }

	    public void setSeckillId(long seckillId) {
	        this.seckillId = seckillId;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public int getNumber() {
	        return number;
	    }

	    public void setNumber(int number) {
	        this.number = number;
	    }

	    public Timestamp getStartTime() {
	        return startTime;
	    }

	    public void setStartTime(Timestamp startTime) {
	        this.startTime = startTime;
	    }

	    public Timestamp getEndTime() {
	        return endTime;
	    }

	    public void setEndTime(Timestamp endTime) {
	        this.endTime = endTime;
	    }

	    public Timestamp getCreateTIme() {
	        return createTIme;
	    }

	    public void setCreateTIme(Timestamp createTIme) {
	        this.createTIme = createTIme;
	    }

	    @Override
	    public String toString() {
	        return "com.hui.entity.Seckill{" +
	                "����ID=" + seckillId +
	                ", ��ɱ��Ʒ='" + name + '\'' +
	                ", ���=" + number +
	                ", ��ʼ��ɱʱ��=" + startTime +
	                ", ������ɱʱ��=" + endTime +
	                ", ����ʱ��=" + createTIme +
	                '}';
	    }
}
