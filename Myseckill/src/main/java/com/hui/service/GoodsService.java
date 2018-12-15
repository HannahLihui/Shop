package com.hui.service;



import java.util.List;
import java.util.Map;

import com.hui.entity.Goods;
import com.hui.entity.PageBean;

/**
 * @auther TyCoding
 * @date 2018/9/19
 */
public interface GoodsService extends BaseService<Goods> {

    /**
     * 搜索 -- 从Solr索引库中
     *
     * @param searchMap
     * @return
     */
    Map<String, Object> search(Map searchMap);

    /**
     * 分页查询
     * @param goods 查询条件
     * @param pageCode 当前页
     * @param pageSize 每页的记录数
     * @return
     */
    PageBean findByPage(Goods goods, int pageCode, int pageSize);

	
}
