package com.hui.controller;

import java.util.ArrayList;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hui.entity.TaotaoResult;
import com.hui.entity.TbItem;
import com.hui.service.ItemService;

@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	/*
	@RequestMapping(value="/item/save", method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult addItem(TbItem item, String desc) {
		TaotaoResult result = itemService.addItem(item, desc);
		return result;
	}
	
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable Long itemId) {
		TbItem tbItem = itemService.findItemById(itemId);
		return tbItem;
	}
	*/
	@RequestMapping("/item/list")
	@ResponseBody
	
    public Map<String,Object> getAllItem(@RequestParam(required = false, defaultValue = "2") int startPage,
    		@RequestParam(required = false, defaultValue = "10")int PageSize) {
        PageHelper.startPage(startPage, PageSize,true);
        List<TbItem> users = new ArrayList<TbItem>();
        users =itemService.findAllItem();
        PageInfo<TbItem> pi = new PageInfo<TbItem>(users);
        users =pi.getList();
        //��ȡ����
        long count =pi.getTotal();
        //ǰ̨���յ���json��map���Զ�תΪjson��������F12����̨�鿴
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("total",count);
        map.put("rows",users);
        return map;
    }
}
