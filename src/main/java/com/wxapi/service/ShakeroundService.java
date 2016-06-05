package com.wxapi.service;

import net.sf.json.JSONObject;

import com.wxapi.process.MpAccount;
import com.wxapi.vo.MsgRequest;
import com.wxcms.domain.AccountFans;

/**
 * 微信摇一摇周边服务接口，主要用于结合自己的业务和微信接口
 */
public interface ShakeroundService {
	
	//消息处理
	public String getPagelist();
	
}



