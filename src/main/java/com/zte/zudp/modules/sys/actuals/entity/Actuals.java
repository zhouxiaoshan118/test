package com.zte.zudp.modules.sys.actuals.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.zte.zudp.common.persistence.entity.DateEntity;
import com.zte.zudp.common.persistence.entity.ables.Weightable;
import com.zte.zudp.modules.sys.utils.json.View;

/**
 * 实战演练
 *
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-12-18.
 */

public class Actuals extends DateEntity implements Weightable<Actuals> {
	
	private static final long serialVersionUID = 2937778114274390471L;
	
	@JsonView(View.Default.class)
	private String name;//项目名称
	
	@JsonView(View.Default.class)
	private String describe;//项目描述
	
	@JsonView(View.Default.class)
	private String person;//负责人
	
	private List<String> developers;//开发人员
	
	private Integer deveNum;
	
	//@JsonFormat(pattern = "yyyy-MM-dd") 
	private Date startTime;//开始时间
	
	//@JsonFormat(pattern = "yyyy-MM-dd") 
	private Date endTime;//截止时间
	
	@JsonView(View.Default.class)
	private Integer weight;//权重
	
	@JsonView(View.Default.class)
	private Integer projectstatus;//是否结束 结束为"0" 进行为"1"
	
	@JsonView(View.Default.class)
	private Integer delFlag;  //删除标记默认0正常，1删除
	
	@JsonView(View.Default.class)
	private String researStatus;  //项目研发状态，1完成，0开发中
	
	@JsonView(View.Default.class)
	private String actualTarget;  //项目目标
	
	@JsonView(View.Default.class)
	private String researStage;   //研发阶段
	
	private Integer teamNum; //团队人员
	
	private Integer trendsNum; //动态条数
	
	private Long workTimes;//剩余时间
	
	private String developersString;
	 
	private String filesSrc;//开发文档路径
	
	private Integer fileNum;//开发文档数目
	
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public String getResearStatus() {
		return researStatus;
	}
	public void setResearStatus(String researStatus) {
		this.researStatus = researStatus;
	}
	public String getActualTarget() {
		return actualTarget;
	}
	public void setActualTarget(String actualTarget) {
		this.actualTarget = actualTarget;
	}
	public String getResearStage() {
		return researStage;
	}
	public void setResearStage(String researStage) {
		this.researStage = researStage;
	}
	public Integer getProjectstatus() {
		return projectstatus;
	}
	public void setProjectstatus(Integer projectstatus) {
		this.projectstatus = projectstatus;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	
	public Integer getTrendsNum() {
		return trendsNum;
	}
	public void setTrendsNum(Integer trendsNum) {
		this.trendsNum = trendsNum;
	}
	public Integer getTeamNum() {
		return teamNum;
	}
	public void setTeamNum(Integer teamNum) {
		this.teamNum = teamNum;
	}
	public List<String> getDevelopers() {
		return developers;
	}
	public void setDevelopers(List<String> developers) {
		this.developers = developers;
	}
	public Long getWorkTimes() {
		
		return workTimes;
	}
	public void setWorkTimes(Long workTimes) {
		this.workTimes = workTimes;
	}
	
	
	public Integer getDeveNum() {
		return deveNum;
	}
	public void setDeveNum(Integer deveNum) {
		this.deveNum = deveNum;
	}
	@Override
	public int compareTo(Actuals entity) {
		 Integer other = entity.getWeight();
	     Integer weight = this.getWeight();

	        if (weight.equals(other)) {
	            return 0;
	        }

	        return weight > other ? 1 : -1;
	}
	@Override
	public Integer getWeight() {
		
		return this.weight;
	}
	@Override
	public void setWeight(Integer weight) {
		
		this.weight=weight;
	}
	
	public String getDevelopersString() {
		return developersString;
	}
	public void setDevelopersString(String developersString) {
		this.developersString = developersString;
	}
	public String getFilesSrc() {
		return filesSrc;
	}
	public void setFilesSrc(String filesSrc) {
		this.filesSrc = filesSrc;
	}
	public Integer getFileNum() {
		return fileNum;
	}
	public void setFileNum(Integer fileNum) {
		this.fileNum = fileNum;
	}
	
	
}
