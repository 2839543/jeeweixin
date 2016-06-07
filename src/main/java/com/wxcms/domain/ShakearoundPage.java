package com.wxcms.domain;

import com.core.domain.BaseEntity;

/**
 *  微信摇一摇周边 页面统计
 */
public class ShakearoundPage extends BaseEntity {

	private String page_id;// 页面ID
	private Integer click_pv;// 点击摇周边消息的次数
	private Integer click_uv;// 点击摇周边消息的人数
	private Integer shake_pv;// 摇周边的次数
	private Integer shake_uv;// 摇周边的人数
	private Integer date; //日期
	
	public String getPage_id() {
		return page_id;
	}
	public void setPage_id(String page_id) {
		this.page_id = page_id;
	}
	public Integer getClick_pv() {
		return click_pv;
	}
	public void setClick_pv(Integer click_pv) {
		this.click_pv = click_pv;
	}
	public Integer getClick_uv() {
		return click_uv;
	}
	public void setClick_uv(Integer click_uv) {
		this.click_uv = click_uv;
	}
	public Integer getShake_pv() {
		return shake_pv;
	}
	public void setShake_pv(Integer shake_pv) {
		this.shake_pv = shake_pv;
	}
	public Integer getShake_uv() {
		return shake_uv;
	}
	public void setShake_uv(Integer shake_uv) {
		this.shake_uv = shake_uv;
	}
	public Integer getDate() {
		return date;
	}
	public void setDate(Integer date) {
		this.date = date;
	}
	
}