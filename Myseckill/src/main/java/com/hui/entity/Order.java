package com.hui.entity;

public class Order {
	 Long itemid;
     String address;
     String message;
     String phone;
     String name;
     String username;
     boolean delivery;
    int number;
     Double price;
     public Order( Long itemid, String address, String message,String phone, String name
    		 , String username,boolean delivery,int number,Double price) {
    	 this.address=address;
    	 this.itemid=itemid;
    	 this.message=message;
    	 this.phone=phone;
    	 this.name=name;
    	 this.username=username;
    	 this.delivery=delivery;
    	 this.number=number;
    	 this.price=price;
    	 
     }
	public Long getItemid() {
		return itemid;
	}
	public void setItemid(Long itemid) {
		this.itemid = itemid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isDelivery() {
		return delivery;
	}
	public void setDelivery(boolean delivery) {
		this.delivery = delivery;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

}
