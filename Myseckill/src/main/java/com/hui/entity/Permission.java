package com.hui.entity;

import org.springframework.stereotype.Component;

@Component
public class Permission {
	private int id;
    private String token;
    /**��Դurl**/
    private String url;
    /**Ȩ��˵��**/
    private String description;
    /**������ɫ���**/
    private int roleId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

}
