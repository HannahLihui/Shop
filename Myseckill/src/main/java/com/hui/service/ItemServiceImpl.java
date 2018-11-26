package com.hui.service;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hui.dao.TbItemMapper;
import com.hui.entity.IDUtils;
import com.hui.entity.TaotaoResult;
import com.hui.entity.TbItem;

@Service(value="itemService")
public class ItemServiceImpl implements ItemService {
	@Autowired
	private TbItemMapper itemMapper;

	public TbItem findItemById(Long id) {
		// TODO Auto-generated method stub
		return itemMapper.findItemById(id);
	}

	

	public List<TbItem> findAllItem() {
		// TODO Auto-generated method stub
		return itemMapper.findAllItem();
	}

	public TaotaoResult addItem(TbItem item, String desc) {
		// TODO Auto-generated method stub
		//生成商品id
				long itemId = IDUtils.genItemId();
				//补全item的属性
				item.setId(itemId);
				//商品状态，1-正常，2-下架，3-删除
				item.setStatus((byte) 1);
				item.setCreated(new Date());
				item.setUpdated(new Date());
				//向商品表插入数据
				itemMapper.insert(item);
				//创建一个商品描述表对应的pojo
				
				
				return TaotaoResult.ok();
	}



	


	


	

}
