package com.hui.entity;

import java.sql.Timestamp;


public class Time {
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
	    private Timestamp createTime;
	   
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
			return createTime;
		}
		public void setCreateTIme(Timestamp createTIme) {
			this.createTime = createTIme;
		}


}
