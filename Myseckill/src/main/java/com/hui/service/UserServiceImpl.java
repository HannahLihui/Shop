package com.hui.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hui.dao.UserMapper;
import com.hui.entity.Permission;
import com.hui.entity.Role;
import com.hui.entity.User;


@Service("userService")
public class UserServiceImpl implements UserService{
@Autowired
UserMapper userService;




	public User getUserByUserName(String username) {
		// TODO Auto-generated method stub
		  User userByUserName = userService.getUserByUserName(username);
	        return userByUserName;
	}
	  public Permission getPermissionsByUser(User user) {
	        //获取到用户角色userRole
	        List<Integer> roleId = userService.getUserRoleByUserId(user.getId());
	       Permission per = new Permission();

	        if (roleId!=null&&roleId.size()!=0) {
	            //根据roleid获取peimission
	          per=userService.getPermissionsByRoleId(roleId.get(0));
	        }

	        System.out.println(per);
	        return per;
	    }
	 

	

}
