package com.wxapi.service;

import java.util.Date;

import com.wxapi.process.MpAccount;

/**
 * 微信摇一摇周边服务接口，主要用于结合自己的业务和微信接口
 */
public interface ShakeroundService {
	
	//消息处理
	public boolean getPagelist(Date date,MpAccount mpAccount);
	
	//获取页面信息
	public boolean getPageInfo(String page_ids ,MpAccount mpAccount);
	
	//获取页面信息
	public boolean getZbPagesInfo( MpAccount mpAccount); 
		
	//获取页面信息
	public boolean getZbPagesInfo( Integer begin,Integer  count ,MpAccount mpAccount); 
	
}



