package com.hui.service;

import java.util.List;

import com.hui.entity.TaotaoResult;
import com.hui.entity.TbItem;



public interface ItemService {
	TbItem findItemById(Long id);
	 
   
    
    List<TbItem> findAllItem();

	TaotaoResult addItem(TbItem item, String desc);
}
