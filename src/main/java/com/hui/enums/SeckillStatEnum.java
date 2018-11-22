package com.hui.enums;

public enum SeckillStatEnum {
	  SUCCESS(1, "��ɱ�ɹ�"),
	    END(0, "��ɱ����"),
	    REPEAT_KILL(-1, "�ظ���ɱ"),
	    INNER_ERROR(-2, "ϵͳ�쳣"),
	    DATE_REWRITE(-3, "���ݴ۸�");

	    private int state;
	    private String info;

	    SeckillStatEnum() {
	    }

	    SeckillStatEnum(int state, String info) {
	        this.state = state;
	        this.info = info;
	    }

	    public int getState() {
	        return state;
	    }

	    public String getInfo() {
	        return info;
	    }

	    public static SeckillStatEnum stateOf(int index) {
	        for (SeckillStatEnum statEnum : values()) {
	            if (statEnum.getState() == index) {
	                return statEnum;
	            }
	        }
	        return null;
	    }
}
