package com.hui.service;
import com.github.pagehelper.Page;

import com.github.pagehelper.PageHelper;
import com.hui.dao.GoodsMapper;
import com.hui.entity.Goods;
import com.hui.entity.PageBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.*;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther TyCoding
 * @date 2018/9/19
 */
@Service(value="goodsService")
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private SolrTemplate solrTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 模拟演示redis缓存的效果
     *从redis取出数据，
     *如果是空，从数据库取，帮放到redis中
     * @return 商品的容器
     */
	public List<Goods> findAll() {
		// TODO Auto-generated method stub
		List<Goods> contentList = (List<Goods>) redisTemplate.boundHashOps("goods").get("all");
        if (contentList == null) {
            //说明缓存中没有数据
            System.out.println("从数据库中读取数据放入redis...");
            contentList = goodsMapper.findAll();
            redisTemplate.boundHashOps("goods").put("all", contentList); //存入redis中
        } else {
            System.out.println("从缓存中读取数据...");
        }
        return contentList;
	}

	public List<Goods> findById(Long id) {
		// TODO Auto-generated method stub
		  return goodsMapper.findById(id);
	}
      /**
      * 数据库插入商品数据，获取ID
      * 并插入solr索引库
         * */
 
	public void create(Goods t) {
		// TODO Auto-generated method stub
		  goodsMapper.create(t);

	        Long id = goodsMapper.maxId(); //获取根据主键自增插入的最新一条记录的ID值
	        t.setId(id);

	        //更新索引库
	        solrTemplate.saveBean(t);
	        solrTemplate.commit();
	}
/**更新商品信息，并且更新到索引库
 * 
 */
	public void update(Goods t) {
		// TODO Auto-generated method stub
		  goodsMapper.update(t);

	        //更新索引库
	        solrTemplate.deleteById(String.valueOf(t.getId()));
	        solrTemplate.commit();
	        List<Goods> list = new ArrayList<Goods>();
	        list.add(t);
	        solrTemplate.saveBeans(list);
	        solrTemplate.commit();
		
	}

	public void delete(Long... ids) {
		// TODO Auto-generated method stub
		  for (Long id : ids) {
	            goodsMapper.delete(id);

	            //更新索引库
	            solrTemplate.deleteById(String.valueOf(id));
	            solrTemplate.commit();
		  }
		
	}

	public Map<String, Object> search(Map searchMap) {
		// TODO Auto-generated method stub
		  Map<String, Object> map = new HashMap<String, Object>();
	        map.putAll(searchList(searchMap));
	        return map;
	}
private Map searchList(Map searchMap) {
		// TODO Auto-generated method stub
	 Map map = new HashMap();

     //高亮配置
     HighlightQuery query = new SimpleHighlightQuery();
     HighlightOptions highlightOptions = new HighlightOptions().addField("item_title"); //设置高亮的域
     highlightOptions.setSimplePrefix("<em style='color: red'>"); //设置高亮前缀
     highlightOptions.setSimplePostfix("</em>"); //设置高亮后缀
     query.setHighlightOptions(highlightOptions); //设置高亮选项

     //关键字空格处理
     String keywords = (String) searchMap.get("keywords");
     searchMap.put("keywords", keywords.replace(" ", ""));

     Criteria criteria = new Criteria("item_keywords");
     //按照关键字查询
     if (searchMap.get("keywords") != null) {
         if (!searchMap.get("keywords").equals("")) {
             criteria.is(searchMap.get("keywords"));
         }
     }
     query.addCriteria(criteria);

     //按商品分类过滤
     if (searchMap.get("category") != null) {
         if (!searchMap.get("category").equals("")) {
             System.out.println("执行了category");
             FilterQuery filterQuery = new SimpleFilterQuery();
             Criteria filterCriteria = new Criteria("item_category").is(searchMap.get("category"));
             filterQuery.addCriteria(filterCriteria);
             query.addFilterQuery(filterQuery);
         }
     }

     //按品牌过滤
     if (searchMap.get("brand") != null) {
         if (!searchMap.get("brand").equals("")) {
             System.out.println("执行了brand...");
             FilterQuery filterQuery = new SimpleFilterQuery();
             Criteria filterCriteria = new Criteria("item_brand").is(searchMap.get("brand"));
             filterQuery.addCriteria(filterCriteria);
             query.addFilterQuery(filterQuery);
         }
     }

     //按价格区间查询
     if (searchMap.get("price") != null) {
         if (!searchMap.get("price").equals("")) {
             String[] price = ((String) searchMap.get("price")).split("-");
             if (!price[0].equals("0")) {
                 //如果起点区间不等于0
                 Criteria filterCriteria = new Criteria("item_price").greaterThanEqual(price[0]);
                 FilterQuery filterQuery = new SimpleFilterQuery(filterCriteria);
                 query.addFilterQuery(filterQuery);
             }

             if (!price[1].equals("*")) {
                 //如果区间重点不等于*
                 Criteria filterCriteria = new Criteria("item_price").lessThanEqual(price[1]);
                 FilterQuery filterQuery = new SimpleFilterQuery(filterCriteria);
                 query.addFilterQuery(filterQuery);
             }
         }
     }

     //按价格的升降序查询
     if (searchMap.get("sort") != null) {
         if (!searchMap.get("sort").equals("")) {
             String sortValue = (String) searchMap.get("sort");
             String sortField = (String) searchMap.get("field");
             if (sortValue != null && !sortValue.equals("")) {
                 if (sortValue.equals("asc")) {
                     Sort sort = new Sort(Sort.Direction.ASC, "item_" + sortField);
                     query.addSort(sort);
                 }
                 if (sortValue.equals("desc")) {
                     Sort sort = new Sort(Sort.Direction.DESC, "item_" + sortField);
                     query.addSort(sort);
                 }
             }
         }
     }

     /**
      * 分页查询
      *
      * @Param pageCode 当前页
      *
      * @Param pageSize 每页显示的记录数
      */
     Integer pageCode = (Integer) searchMap.get("pageCode");
     if (pageCode == null) {
         pageCode = 1; //默认第一页
     }
     Integer pageSize = (Integer) searchMap.get("pageSize");
     if (pageSize == null) {
         pageSize = 18; //默认18
     }
     query.setOffset((pageCode - 1) * pageSize); //从第几条记录开始查询：= 当前页 * 每页的记录数
     query.setRows(pageSize);

     HighlightPage<Goods> page = solrTemplate.queryForHighlightPage(query, Goods.class);
     //循环高亮入口集合
     for (HighlightEntry<Goods> h : page.getHighlighted()) {
         Goods goods = h.getEntity(); //获取原实体类
         if (h.getHighlights().size() > 0 && h.getHighlights().get(0).getSnipplets().size() > 0) {
             goods.setTitle(h.getHighlights().get(0).getSnipplets().get(0)); //设置高亮的结果
         }
     }

     map.put("rows", page.getContent()); //返回查询到的数据
     map.put("totalPage", page.getTotalPages()); //返回总页数
     map.put("total", page.getTotalElements()); //返回总记录数

     return map;
	}

/**
 * @Param pageCode是当前页，
 * @param pageSize是页面大小
 * @return分页实体类（当前页，当前记录)
 */
	public PageBean findByPage(Goods goods, int pageCode, int pageSize) {
		// TODO Auto-generated method stub
		  //使用Mybatis分页插件
        PageHelper.startPage(pageCode, pageSize);

        //调用分页查询方法，其实就是查询所有数据，mybatis自动帮我们进行分页计算
        Page<Goods> page = goodsMapper.findByPage(goods);

        return new PageBean(page.getTotal(), page.getResult());
	}

}
