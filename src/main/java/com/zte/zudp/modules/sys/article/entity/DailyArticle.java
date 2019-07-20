package com.zte.zudp.modules.sys.article.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.zte.zudp.common.persistence.entity.DateEntity;
import com.zte.zudp.common.persistence.entity.ables.Weightable;
import com.zte.zudp.modules.sys.actuals.entity.Actuals;
import com.zte.zudp.modules.sys.user.entity.Officer;
import com.zte.zudp.modules.sys.utils.json.View;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

/**
 * 日常报告
 * @author piumnl
 * @version 1.0.0
 * @since on 2017-12-18.
 */
public class DailyArticle extends DateEntity implements Weightable<DailyArticle> {

	private String id;//日报ID
	
	@JsonView(View.Default.class)
	private String title;  //标题
	
	@JsonView(View.Default.class)
	private Date createTime;  //日报创建时间
	
	@JsonView(View.Default.class)
	private Date pushDate;  //发布时间

	@JsonView(View.Default.class)
	private Date updateTime;  //日报更新时间

	@JsonView(View.Default.class)
	private String pushPerson;  //发布人
	
	@JsonView(View.Default.class)
	private String description;  //日报摘要
	
	@JsonView(View.Default.class)
	private String content;  //正文内容
	
	@JsonView(View.Default.class)
	private Integer isAudit=0; //是否需要审核，1审核通过，0未审核
	
	@JsonView(View.Default.class)
	private String image;  //缩略图
	
	@JsonView(View.Default.class)
	private Integer hits;  //点击量
	
	@JsonView(View.Default.class)
	private Integer weight;  //权重
	
	@JsonView(View.Default.class)
	private String remarks;  //备注
	
	@JsonView(View.Default.class)
	private Integer delFlag;  //删除标记，0正常，1删除
	
	private Integer articleRoot;//是否从项目入口 1：是  0:否
	
	/*@Value("${dailyArticle.image.defaultUrl}")
	private String defaultUrl;*/
	private Officer officer;//部门人员
	
	private Actuals  actuals;//项目实战
	
	private String officerId;//部门人员ID
	
	private String actualsId;//项目ID
	
	private String stringPushDate;
	
	private Integer week;//星期几
	
	private boolean rootMan; //是否有權限发日报
	
	@JsonView(View.Default.class)
	private long beforePushDate;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPushDate() {
		return pushDate;
	}

	public void setPushDate(Date pushDate) {
		this.pushDate = pushDate;
	}

	public String getPushPerson() {
		return pushPerson;
	}

	public void setPushPerson(String pushPerson) {
		this.pushPerson = pushPerson;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getIsAudit() {
		return isAudit;
	}

	public void setIsAudit(Integer isAudit) {
		this.isAudit = isAudit;
	}

	public String getImage() {
		return image;
	}
	

	public void setImage(String image) {
		
		if(image!=null||StringUtils.isNoneBlank(image)){
			this.image = image;
		}else{
			this.image="/plugins/dailyarticle/img/03.jpg";
		}
		
		
	}

	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Officer getOfficer() {
		return officer;
	}

	public void setOfficer(Officer officer) {
		this.officer = officer;
	}

	
	
	public Actuals getActuals() {
		return actuals;
	}

	public void setActuals(Actuals actuals) {
		this.actuals = actuals;
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

	public Integer getArticleRoot() {
		return articleRoot;
	}

	public void setArticleRoot(Integer articleRoot) {
		this.articleRoot = articleRoot;
	}
	
	
	
	public String getStringPushDate() {
		return stringPushDate;
	}

	public void setStringPushDate(String stringPushDate) {
		this.stringPushDate = stringPushDate;
	}

	public long getBeforePushDate() {
		return beforePushDate;
	}

	public void setBeforePushDate(long beforePushDate) {
		this.beforePushDate = beforePushDate;
	}

	@Override
	    public int compareTo(DailyArticle entity) {
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
		this.weight = weight;
	}

	public Integer getWeek() {
		return week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}

	public boolean getRootMan() {
		return rootMan;
	}

	public void setRootMan(boolean rootMan) {
		this.rootMan = rootMan;
	}

	

	
	
	
}
