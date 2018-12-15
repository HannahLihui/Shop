package com.hui.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hui.entity.Result;
import com.hui.entity.User;
import com.hui.service.UserService;
@RestController
public class LoginController {
	@Autowired
	private UserService userService;
	 @RequestMapping(value = "/login")
	    public Result Login(@RequestParam(value="username",required=false)String username,
	    		@RequestParam(value="password",required=false) String password,
	    		HttpServletResponse response,HttpServletRequest request) {
		 String error=null;
		response.setCharacterEncoding("UTF-8");
	        HttpSession session=request.getSession();
	       if(username!=null){
	        	System.out.println(username);
	           
	       
	        Subject subject = SecurityUtils.getSubject();
	        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
	     session.setAttribute("username", username);
	     session.setAttribute("password", password);
	       
	        User user;
	        
	        try {
	            subject.login(token);
	        boolean role=subject.hasRole("admin");
	        System.out.println(role);
	        if(role==true) {
	        	error="manager";
	        }
	            user = (User)subject.getPrincipal();
	            System.out.println(user.toString());
	            return new Result(true,error);
	        } catch (UnknownAccountException e) {
	        	 System.out.println( "登陆出错");
	           error="登陆出错";
	            
	        }catch (IncorrectCredentialsException ex) {
	        	 System.out.println( "用户名和密码不匹配");
	        	error="用户名和密码不匹配";
	        }catch (AuthenticationException e) {
	        	 System.out.println( "其他的登陆错误");
		        error="其他的登陆出错";
	        	
	        }
	        }else {
	        	error="请输入用户和密码";
	        }
	        

	        return new Result(false,error);
	    }
	   
	

}
