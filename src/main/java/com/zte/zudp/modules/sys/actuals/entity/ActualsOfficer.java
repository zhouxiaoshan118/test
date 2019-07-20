package com.zte.zudp.modules.sys.actuals.entity;

import com.zte.zudp.common.persistence.entity.DateEntity;

public class ActualsOfficer extends DateEntity{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//标识
	private String id;
	
	//开发人员ID
	private String officerId;
	
	//项目ID
	private String actualsId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOfficerId() {
		return officerId;
	}

	public void setOfficerId(String officerId) {
		this.officerId = officerId;
	}

	public String getActualsId() {
		return actualsId;
	}

	public void setActualsId(String actualsId) {
		this.actualsId = actualsId;
	}
	
	
}
