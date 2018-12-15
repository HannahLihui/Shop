package com.hui.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hui.entity.Order;
import com.hui.entity.RecentOrder;
import com.hui.entity.Result;
import com.hui.service.OrderService;

@RestController
public class OrderController {
	@Autowired 
	private OrderService orderService;
	 @RequestMapping("/orderSubmit")
		public Result orderSubmit(@RequestBody Map<String, Object> ruleForm ) {
			  String itemid= (String) ruleForm.get("itemid");
			  String address= (String) ruleForm.get("address");
			  String message= (String) ruleForm.get("message");//买家留言
			  String phone=(String) ruleForm.get("phone");//获得手机号
			  String name=(String) ruleForm.get("name");//下单姓名
			  String username=(String) ruleForm.get("username");//用户登陆名
			boolean  delivery= (Boolean)ruleForm.get("delivery");///是否准时宝
			  
			  int number=(Integer) ruleForm.get("number");
			  String  price=(String) ruleForm.get("price");
			  System.out.println("商品ID"+itemid+"\n配送地址"+address+"\n备注"+message+"\n电话号"+phone
					  +"s准时险"+delivery+"\n商品数量"+number);
			Order order=new Order(Long.valueOf(itemid), address,message,phone,name,username, delivery,number,Double.valueOf(price));
			orderService.orderSubmit(order);
			return new Result(true,"success");
		}
	 @RequestMapping("/recentOrder")
	 public RecentOrder searchRecentOrder() {
		return null;
		 
	 }
	 

}
