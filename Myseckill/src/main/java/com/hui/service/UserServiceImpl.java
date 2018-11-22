package com.hui.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hui.dao.UserMapper;
import com.hui.entity.Permission;
import com.hui.entity.User;


@Service(value="userService")
public class UserServiceImpl implements UserService{
@Autowired
UserMapper userdao;



	public User getUserByUserName(String username) {
		// TODO Auto-generated method stub
		  User userByUserName = userdao.getUserByUserName(username);
	        return userByUserName;
	}

	public List<Permission> getPermissionsByUser(User user) {
		// TODO Auto-generated method stub
		  List<Integer> roleId = userdao.getUserRoleByUserId(user.getId());
	        List<Permission> perArrary = new ArrayList<Permission>();

	        if (roleId!=null&&roleId.size()!=0) {
	            //根据roleid获取peimission
	            for (Integer i : roleId) {
	                perArrary.addAll(userdao.getPermissionsByRoleId(i));
	            }
	        }

	        System.out.println(perArrary);
	        return perArrary;
	}

}
