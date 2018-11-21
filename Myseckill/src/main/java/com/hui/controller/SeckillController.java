package com.hui.controller;

import java.sql.Timestamp;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hui.dao.SeckillResult;
import com.hui.dto.*;

import com.hui.entity.Seckill;
import com.hui.entity.Time;
import com.hui.enums.SeckillStatEnum;
import com.hui.exception.RepeatKillException;
import com.hui.exception.SeckillCloseException;
import com.hui.exception.SeckillException;
import com.hui.service.SeckillService;
@Controller
public class SeckillController {
	 private final SeckillService seckillService;

	    @Autowired
	    public SeckillController(SeckillService seckillService) {
	        this.seckillService = seckillService;
	    }

	    /**
	     * ������ɱ�б�.
	     *
	     * @param model ģ������,�����������ɱ��Ʒ����Ϣ
	     * @return ��ɱ�б�����ҳ��
	     */
	
	    @RequestMapping(value = "/list", method = RequestMethod.GET)
	    public String list(Model model) {
	        List<Seckill> seckillList = seckillService.getSeckillList();
	        System.out.println(seckillList);
	       // List<String>stampStart=new ArrayList<String>();
	       /* for(int i=0;i<seckillList.size();i++) {
	        	Timestamp datetime=seckillList.get(i).getStartTime();
	        	 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
	        	  String str = df.format(datetime);
	        	  stampStart.add(str);
	        }*/
	         
	      //model.addAttribute("start",stampStart);
	        model.addAttribute("list", seckillList);
	        return "list";
	    }

	    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
	    public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
	    	
	        if (seckillId == null) {
	            return "redirect:/seckill/list";
	        }
	        Seckill seckill = seckillService.getById(seckillId);
	       
	        if (seckill == null) {
	            return "forward:/seckill/list";
	        }
	        model.addAttribute("seckill", seckill);
	        return "detail";
	    }

	    /**
	     * ��¶��ɱ�ӿڵķ���.
	     *
	     * @param seckillId ��ɱ��Ʒ��id
	     * @return �����û���ɱ����Ʒid����ҵ���߼��ж�,���ز�ͬ��jsonʵ����
	     */
	    @RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.GET)
	    @ResponseBody
	    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId) {
	        // ��ѯ��ɱ��Ʒ�Ľ��
	    	
	        SeckillResult<Exposer> result;
	        try {
	            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
	            result = new SeckillResult<Exposer>(true, exposer);
	        } catch (Exception e) {
	            e.printStackTrace();
	            result = new SeckillResult<Exposer>(false, e.getMessage());
	        }
	        return result;
	    }

	    /**
	     * �û�ִ����ɱ,��ҳ������Ӧ����ɱ����,������ȡ��Ӧ�Ĳ��������ж�,�������Ӧ��jsonʵ����,ǰ���ٽ��д���.
	     *
	     * @param seckillId ��ɱ����Ʒ,��Ӧ��ʱ��ɱ��id
	     * @param md5       һ����������md5����ֵ
	     * @param userPhone ������ɱ�û��Ķ��ֻ�����,�����˺�����ʹ��
	     * @return ������ɱ�Ľ��,Ϊjson����
	     */
	    @RequestMapping(value = "/{seckillId}/{md5}/execution", method = RequestMethod.POST)
	    @ResponseBody
	    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") long seckillId,
	                                                   @PathVariable("md5") String md5,
	                                                   @CookieValue(value = "userPhone", required = false) Long userPhone) {
	        // ����û����ֻ�����Ϊ�յ�˵��û����д�ֻ����������ɱ
	        if (userPhone == null) {
	            return new SeckillResult<SeckillExecution>(false, "û��ע��");
	        }
	        // �����û����ֻ�����,��ɱ��Ʒ��id��md5������ɱ��Ʒ,û�쳣������ɱ�ɹ�
	        try {
	            // ���ﻻ�ɴ������
	 SeckillExecution execution = seckillService.executeSeckill(seckillId, userPhone, md5);
	            return new SeckillResult<SeckillExecution>(true, execution);
	        } catch (RepeatKillException e1) {
	            // �ظ���ɱ
	            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.REPEAT_KILL);
	            return new SeckillResult<SeckillExecution>(false, execution);
	        } catch (SeckillCloseException e2) {
	            // ��ɱ�ر�
	            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.END);
	            return new SeckillResult<SeckillExecution>(false, execution);
	        } catch (SeckillException e) {
	            // �����жϵ��쳣
	            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
	            return new SeckillResult<SeckillExecution>(false, execution);
	        }
	        // ������쳣������ɱʧ��
	    }

	    /**
	     * ��ȡ��������ʱ��,��ֹ�û��۸Ŀͻ���ʱ����ǰ������ɱ
	     *
	     * @return ʱ���json����
	     */
	    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
	    @ResponseBody
	    public SeckillResult<LocalDateTime> time() {
	        LocalDateTime localDateTime = LocalDateTime.now();
	        return new SeckillResult<LocalDateTime>(true, localDateTime);
	    }



}
