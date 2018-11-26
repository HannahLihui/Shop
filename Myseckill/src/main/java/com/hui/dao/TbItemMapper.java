package com.hui.dao;


import java.util.List;



import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hui.entity.TbItem;



public interface TbItemMapper {
	 @Select("select * from tb_item where id = #{id}")
	    TbItem findItemById(Long id);
	 
	    @Insert("insert into tb_item(userName, birth) values(#{userName}, #{birth})")
	    @Options(useGeneratedKeys = true, keyProperty = "id")
	    void insert(TbItem item);
	    
	    //public Integer saveItem(TbItem item);
	    
	    @Select("select * from tb_item")
	   public List<TbItem> findAllItem();

		
}