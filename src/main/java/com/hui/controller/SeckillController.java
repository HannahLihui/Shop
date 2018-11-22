package com.hui.controller;

import java.sql.Timestamp;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
import com.hui.entity.User;
import com.hui.enums.SeckillStatEnum;
import com.hui.exception.RepeatKillException;
import com.hui.exception.SeckillCloseException;
import com.hui.exception.SeckillException;
import com.hui.service.SeckillService;
import com.hui.service.UserService;
@Controller
public class SeckillController {
	 private final SeckillService seckillService;
	 private final UserService userService;
	    @Autowired
	    public SeckillController(SeckillService seckillService,UserService userService) {
	        this.seckillService = seckillService;
	      this.userService=userService;
	    }
	    @RequestMapping(value = "/login")
	    public String Login(String username, String password, HttpSession session, Model model){
	        if(username==null){
	            model.addAttribute("message", "查看页面");
	            return "login";
	        }


	        //涓讳綋,褰撳墠鐘舵�佷负娌℃湁璁よ瘉鐨勭姸鎬佲�滄湭璁よ瘉鈥�
	        Subject subject = SecurityUtils.getSubject();
	        // 鐧诲綍鍚庡瓨鏀捐繘shiro token
	        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
	        User user;
	        //鐧诲綍鏂规硶锛堣璇佹槸鍚﹂�氳繃锛�
	        //浣跨敤subject璋冪敤securityManager,瀹夊叏绠＄悊鍣ㄨ皟鐢≧ealm
	        try {
	            //鍒╃敤寮傚父鎿嶄綔
	            //闇�瑕佸紑濮嬭皟鐢ㄥ埌Realm涓�
	            System.out.println("========================================");
	            //System.out.println("1銆佽繘鍏ヨ璇佹柟娉�");
	            subject.login(token);
	            user = (User)subject.getPrincipal();
	            session.setAttribute("user",subject);
	            model.addAttribute("message", "信息");
	           // System.out.println("鐧诲綍瀹屾垚");
	        } catch (UnknownAccountException e) {
	            model.addAttribute("message", "查看信息");
	            return "index";
	        }


	        return "test";
	    }
	   


	    /**
	     * 进入秒杀列表.
	     *
	     * @param model 模型数据,里面放置有秒杀商品的信息
	     * @return 秒杀列表详情页面
	     */
	   
	    @RequestMapping("/check")
	    public String check(HttpSession session){

	        Subject subject=(Subject)session.getAttribute("user");

	        User user=(User)subject.getPrincipal();
	        System.out.println(user.toString());
	        return "permission";
	    }

	    @RequestMapping("/readName")
	    public String readName(HttpSession session){

	        return "name";
	    }

	    @RequestMapping("/readData")
	    public String readData(){

	        return "data";
	    }


	    @RequestMapping("/nopermission")
	    public String noPermission(){
	        return "error";
	    }
	
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
	     * 暴露秒杀接口的方法.
	     *
	     * @param seckillId 秒杀商品的id
	     * @return 根据用户秒杀的商品id进行业务逻辑判断,返回不同的json实体结果
	     */
	    @RequestMapping(value = "/{seckillId}/exposer", method = RequestMethod.GET)
	    @ResponseBody
	    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId) {
	        // 查询秒杀商品的结果
	    	
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
	     * 用户执行秒杀,在页面点击相应的秒杀连接,进入后获取对应的参数进行判断,返回相对应的json实体结果,前端再进行处理.
	     *
	     * @param seckillId 秒杀的商品,对应的时秒杀的id
	     * @param md5       一个被混淆的md5加密值
	     * @param userPhone 参与秒杀用户的额手机号码,当做账号密码使用
	     * @return 参与秒杀的结果,为json数据
	     */
	    @RequestMapping(value = "/{seckillId}/{md5}/execution", method = RequestMethod.POST)
	    @ResponseBody
	    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") long seckillId,
	                                                   @PathVariable("md5") String md5,
	                                                   @CookieValue(value = "userPhone", required = false) Long userPhone) {
	        // 如果用户的手机号码为空的说明没有填写手机号码进行秒杀
	        if (userPhone == null) {
	            return new SeckillResult<SeckillExecution>(false, "没有注册");
	        }
	        // 根据用户的手机号码,秒杀商品的id跟md5进行秒杀商品,没异常就是秒杀成功
	        try {
	            // 这里换成储存过程
	 SeckillExecution execution = seckillService.executeSeckill(seckillId, userPhone, md5);
	            return new SeckillResult<SeckillExecution>(true, execution);
	        } catch (RepeatKillException e1) {
	            // 重复秒杀
	            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.REPEAT_KILL);
	            return new SeckillResult<SeckillExecution>(false, execution);
	        } catch (SeckillCloseException e2) {
	            // 秒杀关闭
	            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.END);
	            return new SeckillResult<SeckillExecution>(false, execution);
	        } catch (SeckillException e) {
	            // 不能判断的异常
	            SeckillExecution execution = new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
	            return new SeckillResult<SeckillExecution>(false, execution);
	        }
	        // 如果有异常就是秒杀失败
	    }

	    /**
	     * 获取服务器端时间,防止用户篡改客户端时间提前参与秒杀
	     *
	     * @return 时间的json数据
	     */
	    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
	    @ResponseBody
	    public SeckillResult<LocalDateTime> time() {
	        LocalDateTime localDateTime = LocalDateTime.now();
	        return new SeckillResult<LocalDateTime>(true, localDateTime);
	    }



}
