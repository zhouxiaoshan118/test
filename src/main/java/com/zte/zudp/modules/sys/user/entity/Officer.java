package com.zte.zudp.modules.sys.user.entity;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zte.zudp.common.persistence.entity.DateEntity;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Officer extends DateEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;//id标识
	private String name;//姓名
	private String image;//头像
	private Character jobState='1';//在职状态 0：离职；1：试用；2：正式
	private Character sex='1';//0:女性；1：男性
	private String job;//职位
	private String department;//所属小组
	private Integer weight;//权重
	private String telephone;//手机号
	private String email;//邮箱
	private String skill;//技能
	private Character delState='1';//删除标识0:删除 1：不删除
	//private String discription;//个人介绍
	private String hobby;  //个人爱好
	private String address;//住址
	private String educational;//教育经历
	private String work;//工作经历
	private String experience;//项目经历
	private String evaluate;//自我评价
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		
		return image;
	}
	public void setImage(String image) {
		if(StringUtils.isNoneBlank(image)){
			this.image =image;
		}else{
			
			this.image="/plugins/officer/img/bg01.jpg";
		}
		
		
	}
	public Character getJobState() {
		return jobState;
	}
	public void setJobState(Character jobState) {
		this.jobState = jobState;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public Character getDelState() {
		return delState;
	}
	public void setDelState(Character delState) {
		this.delState = delState;
	}
	/*public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}*/
	
	
	/*public String getSelectName() {
		return selectName;
	}
	public void setSelectName(String selectName) {
		this.selectName = selectName;
	}*/
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getEducational() {
		return educational;
	}
	public void setEducational(String educational) {
		this.educational = educational;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getEvaluate() {
		return evaluate;
	}
	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}
	public Character getSex() {
		return sex;
	}
	public void setSex(Character sex) {
		this.sex = sex;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
