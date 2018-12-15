package com.hui.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hui.entity.Goods;
import com.hui.entity.PageBean;
import com.hui.entity.Result;
import com.hui.service.GoodsService;

import java.util.List;
import java.util.Map;

/**
 * @auther TyCoding
 * @date 2018/9/19
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 分页查询
     *
     * @param goods    查询条件
     * @param pageCode 当前页
     * @param pageSize 每页显示的记录数
     * @return
     */
    @RequestMapping("/orderSubmit")
	public void orderSubmit(@RequestBody Map<String, Object> ruleForm ) {
		  String itemid= (String) ruleForm.get("itemid");
		  String address= (String) ruleForm.get("address");
		  System.out.println(itemid);
		
	}
    @RequestMapping("/findByConPage")
    public PageBean findByConPage(Goods goods,
                                  @RequestParam(value = "pageCode", required = false) int pageCode,
                                  @RequestParam(value = "pageSize", required = false) int pageSize) {
        return goodsService.findByPage(goods, pageCode, pageSize);
    }

    /**
     * 搜索
     *
     * @param searchMap
     * @return
     */
    @RequestMapping("/search")
    public Map<String, Object> search(@RequestBody Map<String, Object> searchMap) {
        return goodsService.search(searchMap);
    }

    /**
     * 新增商品
     *
     * @param goods
     * @return
     */
    @RequestMapping("/create")
    public Result create(@RequestBody Goods goods) {
        try {
            goodsService.create(goods);
            return new Result(true, "创建成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }

    /**
     * 更新数据成功
     *
     * @param goods
     * @return
     */
    @RequestMapping("/update")
    public Result update(@RequestBody Goods goods) {
        try {
            goodsService.update(goods);
            return new Result(true, "更新数据成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }

    /**
     * 批量删除数据
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody Long... ids) {
        try {
            goodsService.delete(ids);
            return new Result(true, "更新数据成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public List<Goods> findById(@RequestParam(value = "id", required = false) Long id) {
        return goodsService.findById(id);
    }

}
