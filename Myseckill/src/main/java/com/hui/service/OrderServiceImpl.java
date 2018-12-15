package com.hui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hui.dao.OrderMapper;
import com.hui.entity.Order;

@Service(value="orderService")
public class OrderServiceImpl implements OrderService{
	 @Autowired
	private OrderMapper orderdao;

	public void orderSubmit(Order order) {
		// TODO Auto-generated method stub
		orderdao.insertOrder(order);
	}

}
