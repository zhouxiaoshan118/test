package com.zte.zudp.modules.sys.actuals.entity;

import java.util.Date;

import com.zte.zudp.common.persistence.entity.DateEntity;
import com.zte.zudp.modules.sys.user.entity.Officer;

public class Comment extends DateEntity{
	private String id;
	private String commentId;
	private String repeatId;
	private String articleId;
	private Date commentDate;
	private String commentContent;
	private Integer delfalg; //0:删除 ； 1：不删除
	private Integer level;	//1:一楼 2:其他楼
	private long beforeCommentTime;
	
	private Officer commentUser;
	private Officer repeatUser;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getRepeatId() {
		return repeatId;
	}
	public void setRepeatId(String repeatId) {
		this.repeatId = repeatId;
	}
	public String getArticleId() {
		return articleId;
	}
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public Officer getCommentUser() {
		return commentUser;
	}
	public void setCommentUser(Officer commentUser) {
		this.commentUser = commentUser;
	}
	public Officer getRepeatUser() {
		return repeatUser;
	}
	public void setRepeatUser(Officer repeatUser) {
		this.repeatUser = repeatUser;
	}
	public Integer getDelfalg() {
		return delfalg;
	}
	public void setDelfalg(Integer delfalg) {
		this.delfalg = delfalg;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public long getBeforeCommentTime() {
		return beforeCommentTime;
	}
	public void setBeforeCommentTime(long beforeCommentTime) {
		this.beforeCommentTime = beforeCommentTime;
	}
	
	
}
