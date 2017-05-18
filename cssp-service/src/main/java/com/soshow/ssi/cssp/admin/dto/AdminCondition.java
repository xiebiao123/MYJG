package com.soshow.ssi.cssp.admin.dto;

import com.soshow.ssi.common.util.base.Paging;
/**
 * 管理员condition
 * @author xieb
 * @version 1.00
 * 2016/03/21
 */
public class AdminCondition extends Paging{

	private static final long serialVersionUID = 1L;

	/**  */
    private Integer id;

    /**  */
    private String adminPassword;

    /**  */
    private String adminName;

    /**  */
    private String email;

    /**  */
    private Long tel;

    /**  */
    private String picture;

    private AdminDTO admin;

	public AdminDTO getAdmin() {
		return admin;
	}

	public void setAdmin(AdminDTO admin) {
		this.admin = admin;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTel() {
        return tel;
    }

    public void setTel(Long tel) {
        this.tel = tel;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}

