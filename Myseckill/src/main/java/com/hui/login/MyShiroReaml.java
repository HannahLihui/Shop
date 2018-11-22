package com.hui.login;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
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
	

	    public UserService getShiroService() {
	        return userService;
	    }

	    public void setShiroService(UserService shiroService) {
	        this.userService = shiroService;
	    }
	   
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {

        /**
         * 
         * ����
         * 1.�����û�user->2.��ȡ��ɫid->3.���ݽ�ɫid��ȡȨ��permission
         */
        //����һ�����user����
        User user=(User)pc.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //��ȡpermission
        if(user!=null) {
            List<Permission> permissionsByUser = userService.getPermissionsByUser(user);
            if (permissionsByUser.size()!=0) {
                for (Permission p: permissionsByUser) {

                    info.addStringPermission(p.getUrl());
                }
                return info;
            }
        }

        //�������� ��subject���������ȡuser
//      Subject subject = SecurityUtils.getSubject();
//      User _user = (User) subject.getPrincipal();
//      System.out.println("subject"+_user.getUsername());




        return null;
    }

    // ��֤����
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("������֤��");
        //��֤�˺�����
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        System.out.println("1:"+token.getUsername());
        User user = userService.getUserByUserName(token.getUsername());
        System.out.println("2");
        if(user==null){
            return null;
        }
        //���ıȶ���Ҫ������ȫ������
        //�����������г����ļ���֤��Ϣ����İ�װ
        AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getSimpleName());

        return info;
    }


   

	
}

