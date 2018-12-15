package com.hui.dao;

import org.apache.ibatis.annotations.Param;

import com.hui.entity.Order;

public interface OrderMapper {
	 int insertOrder(Order order
			 );
}
