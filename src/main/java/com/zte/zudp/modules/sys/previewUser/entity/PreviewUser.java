package com.zte.zudp.modules.sys.previewUser.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zte.zudp.common.persistence.entity.DateEntity;

public class PreviewUser extends DateEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String previewUserName;
	private String previewPassword;
	@JsonFormat(locale="zh",timezone="GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
	private Date startDate;
	@JsonFormat(locale="zh",timezone="GMT+8",pattern="yyyy-MM-dd HH:mm:ss")
	private Date endDate;
	private String viewResources;
	private Integer isValid;//0:提示登录;1:可以查看;2:不在指定时间;3:权限不足;4:用户名或密码错误
	private Integer delState=1;//0:标识删除；1：表示未删除
	private Integer isExist=0;//0:表示不存在;1:表示存在
	
	private List<String> resources;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPreviewUserName() {
		return previewUserName;
	}
	public void setPreviewUserName(String previewUserName) {
		this.previewUserName = previewUserName;
	}
	public String getPreviewPassword() {
		return previewPassword;
	}
	public void setPreviewPassword(String previewPassword) {
		this.previewPassword = previewPassword;
	}
	

	public Date getStartDate() {
		return startDate;
	}
	
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getViewResources() {
		return viewResources;
	}
	public void setViewResources(String viewResources) {
		this.viewResources = viewResources;
	}
	public Integer getDelState() {
		return delState;
	}
	public void setDelState(Integer delState) {
		this.delState = delState;
	}
	public List<String> getResources() {
		return resources;
	}
	public void setResources(List<String> resources) {
		this.resources = resources;
	}
	public Integer getIsValid() {
		return isValid;
	}
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
	public Integer getIsExist() {
		return isExist;
	}
	public void setIsExist(Integer isExist) {
		this.isExist = isExist;
	}
	
	
	
}
