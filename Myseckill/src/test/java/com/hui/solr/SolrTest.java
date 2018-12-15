package com.hui.solr;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.*;
import com.hui.entity.Goods;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:spring/spring-solr.xml"})

public class SolrTest {
	@Autowired
	private SolrTemplate solrTemplate;
	//@Test
	public void testAdd() {
		BigDecimal price=new BigDecimal (2.3);
	    Goods goods = new Goods(1l, "IPhone SE",price, "手机", "Apple", "Apple");
	    solrTemplate.saveBean(goods);
	    solrTemplate.commit(); //提交
	}
	//@Test
	public void testFindById() {
	    Goods goods = solrTemplate.getById(1, Goods.class);
	    System.out.println("--------" + goods.getTitle());
	}
	//@Test
	public void testAddList() {
	    List<Goods> list = new ArrayList<Goods>();
	    //循环插入100条数据
	    for (int i = 0; i < 100; i++) {
	    	BigDecimal price=new BigDecimal (2.3);
	        Goods goods = new Goods(i + 1L, "华为Mate" + i,price, "手机", "手机", "华为专卖店");
	        list.add(goods);
	    }
	    solrTemplate.saveBeans(list); //添加集合对象，调用saveBeans()；添加普通对象类型数据，使用saveBean();
	    solrTemplate.commit(); //提交
	}
	@Test
	public void testDeleteAll() {
		  Query query = new SimpleQuery("*:*");
		    solrTemplate.delete(query);
	}
	//@Test
	public void testPageQuery() {
	    Query query = new SimpleQuery("*:*");
	    query.setOffset(20); //开始索引（默认0）
	    query.setRows(20); //每页记录数（默认10）
	    ScoredPage<Goods> page = solrTemplate.queryForPage(query, Goods.class);
	    System.out.println("总记录数：" + page.getTotalElements());
	    List<Goods> list = page.getContent();
	}

}
