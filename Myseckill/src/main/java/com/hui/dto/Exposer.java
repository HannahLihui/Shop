package com.hui.dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Exposer {
	  /*�Ƿ�����ɱ */
	  private boolean exposed;
	  /*  ����ɱ��ַ���м��ܴ�ʩ  */
	  private String md5;
	  /* idΪseckillId����Ʒ��ɱ��ַ   */
	  private long seckillId;
	  /* ϵͳ��ǰ��ʱ��   */
	  private Timestamp now;
	  /* ��ɱ������ʱ��   */
	  private Timestamp start;
	  /*  ��ɱ������ʱ��  */
	  private Timestamp end;

	  public Exposer() {
	  }

	  public Exposer(boolean exposed, String md5, long seckillId) {
	      this.exposed = exposed;
	      this.md5 = md5;
	      this.seckillId = seckillId;
	  }

	  public Exposer(boolean exposed, long seckillId, Timestamp now, Timestamp start, Timestamp end) {
	      this.exposed = exposed;
	      this.seckillId = seckillId;
	      this.now = now;
	      this.start = start;
	      this.end = end;
	  }

	  public Exposer(boolean exposed, long seckillId) {
	      this.exposed = exposed;
	      this.seckillId = seckillId;
	  }

	  public boolean isExposed() {
	      return exposed;
	  }

	  public void setExposed(boolean exposed) {
	      this.exposed = exposed;
	  }

	  public String getMd5() {
	      return md5;
	  }

	  public void setMd5(String md5) {
	      this.md5 = md5;
	  }

	  public long getSeckillId() {
	      return seckillId;
	  }

	  public void setSeckillId(long seckillId) {
	      this.seckillId = seckillId;
	  }

	  public Timestamp getNow() {
	      return now;
	  }

	  public void setNow(Timestamp now) {
	      this.now = now;
	  }

	  public Timestamp getStart() {
	      return start;
	  }

	  public void setStart(Timestamp start) {
	      this.start = start;
	  }

	  public Timestamp getEnd() {
	      return end;
	  }

	  public void setEnd(Timestamp end) {
	      this.end = end;
	  }

	  @Override
	  public String toString() {
	      return "Exposer{" +
	              "秒杀接口" + exposed +
	              ", md5是='" + md5 + '\'' +
	              ", 商品ID=" + seckillId +
	              ", 当前时间=" + now +
	              ", 开启时间" + start +
	              ", 结束时间=" + end +
	              '}';
	  }
}
