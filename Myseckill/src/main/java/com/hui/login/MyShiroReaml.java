package com.hui.login;

import java.util.List;


import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.hui.entity.Permission;
import com.hui.entity.User;
import com.hui.service.UserService;

public class MyShiroReaml extends AuthorizingRealm{
	 private UserService userService;
	

	    public UserService getUserService() {
	        return userService;
	    }

	    public void setUserService(UserService userService) {
	        this.userService = userService;
	    }
	   
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
		  User user=(User)pc.getPrimaryPrincipal();
	        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
	        //鑾峰彇permission
	        Permission p=userService.getPermissionsByUser(user);
	        String role=p.getDescription();
	        System.out.println(role);
	        if(role.equals("admin")) {
	        	info.addRole("admin");
	        }
	        if(role.equals("user")) {
	        	info.addRole("user");
	        }
	        info.addRole("all");
	        return info;
	      
    }
	@Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
         super.clearCache(principals);
    }
    // ��֤����
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
    	UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
       
        User user = userService.getUserByUserName(token.getUsername());
        
        if(user==null){
            return null;
        }
       
        AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getSimpleName());

        return info;
    }


   

	
}

