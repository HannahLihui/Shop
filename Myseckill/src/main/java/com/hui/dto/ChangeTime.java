package com.hui.dto;

import java.sql.Timestamp;

public class ChangeTime {

	  private long seckillId;
	    /*  秒杀商品名字 */
	    private String name;
	    /* 秒杀的商品编号 */
	    private int number;
	    /* 开始秒杀的时间 */
	    private String startTime;
	    /* 结束秒杀的时间 */
	    private String endTime;
	    /* 创建的时间 */
	    private String createTime;
	    public ChangeTime (long SeckillId,String name,int number,String startTime,String endTime,String createTime) {
	    	this.createTime=createTime;
	    	this.endTime=endTime;
	    	this.name=name;
	    	this.number=number;
	    	this.startTime=startTime;
	    	this.seckillId=SeckillId;
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
		public String getStartTime() {
			return startTime;
		}
		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}
		public String getEndTime() {
			return endTime;
		}
		public void setEndTime(String endTime) {
			this.endTime = endTime;
		}
		public String getCreateTIme() {
			return createTime;
		}
		public void setCreateTIme(String createTIme) {
			this.createTime = createTIme;
		}
}
