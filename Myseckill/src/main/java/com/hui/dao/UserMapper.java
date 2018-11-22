package com.hui.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.hui.entity.Permission;
import com.hui.entity.User;
@Component
public interface UserMapper {
	  User getUserByUserName(@Param("roleid") String username);

	    /**
	     * 根据角色id获取该账号的权限
	     * @param roleId
	     * @return List
	     */
	    List<Permission> getPermissionsByRoleId(@Param("roleid") int roleid);

	    /**
	     * 根据userId获取角色id
	     * @param id
	     * @return LIST
	     */
	    List<Integer> getUserRoleByUserId(@Param("roleid") int roleid);

}
