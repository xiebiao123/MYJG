package com.soshow.ssi.common.bean;

import java.io.Serializable;

public class WebSessionUser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private  Long uid;
	private  String name;
	private  String seesionToken;
	private Integer authStatus;
	private String  account;
	private String headImgUrl;
	private Integer  authType;
	private Integer otherAuthStatus;//0未填写其他证件号，已填写其他证件号
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSessionToken() {
		return seesionToken;
	}
	public void setSessionToken(String seesionToken) {
		this.seesionToken = seesionToken;
	}
	public Integer getAuthStatus() {
		return authStatus;
	}
	public void setAuthStatus(Integer authStatus) {
		this.authStatus = authStatus;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	public Integer getAuthType() {
		return authType;
	}
	public void setAuthType(Integer authType) {
		this.authType = authType;
	}
	public Integer getOtherAuthStatus() {
		return otherAuthStatus;
	}
	public void setOtherAuthStatus(Integer otherAuthStatus) {
		this.otherAuthStatus = otherAuthStatus;
	}
	
	
}
