package com.wxcms.domain;

import com.core.domain.BaseEntity;

/**
 *  微信摇一摇周边 页面信息
 */
public class ZbPageInfo extends BaseEntity {

	private String page_id;// 页面ID
	private String title;// 在摇一摇页面展示的主标题
	private String description;// 在摇一摇页面展示的副标题
	private String icon_url;//在摇一摇页面展示的图片
	private String page_url;// 跳转链接
	private String comment; //页面的备注信息 
	
	public String getPage_id() {
		return page_id;
	}
	public void setPage_id(String page_id) {
		this.page_id = page_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIcon_url() {
		return icon_url;
	}
	public void setIcon_url(String icon_url) {
		this.icon_url = icon_url;
	}
	public String getPage_url() {
		return page_url;
	}
	public void setPage_url(String page_url) {
		this.page_url = page_url;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}