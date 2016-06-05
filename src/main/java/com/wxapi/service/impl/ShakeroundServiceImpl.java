package com.wxapi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wxapi.process.HttpMethod;
import com.wxapi.process.MpAccount;
import com.wxapi.process.MsgType;
import com.wxapi.process.MsgXmlUtil;
import com.wxapi.process.WxApi;
import com.wxapi.process.WxApiClient;
import com.wxapi.process.WxMessageBuilder;
import com.wxapi.service.ShakeroundService;
import com.wxcms.domain.AccountFans;
import com.wxcms.domain.MsgText;
import com.wxcms.mapper.ShakearoundPageDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 业务消息处理 开发者根据自己的业务自行处理消息的接收与回复；
 */

@Service
public class ShakeroundServiceImpl implements ShakeroundService {

	@Autowired
	private ShakearoundPageDao shakearoundPageDao; 

	/**
	 * 获取页面统计数据
	 */
	public String getPagelist() { 
		
		
		String nextOpenId = null;
		AccountFans lastFans = fansDao.getLastOpenId();
		if(lastFans != null){
			nextOpenId = lastFans.getOpenId();
		}
		return doSyncAccountFansList(nextOpenId,mpAccount);
		
		
		String msgtype = msgRequest.getMsgType();//接收到的消息类型
		String respXml = null;//返回的内容；
		if(msgtype.equals(MsgType.Text.toString())){
			/**
			 * 文本消息，一般公众号接收到的都是此类型消息
			 */
			respXml = this.processTextMsg(msgRequest,mpAccount);
		}else if(msgtype.equals(MsgType.Event.toString())){//事件消息
			/**
			 * 用户订阅公众账号、点击菜单按钮的时候，会触发事件消息
			 */
			respXml = this.processEventMsg(msgRequest);
			
		//其他消息类型，开发者自行处理
		}else if(msgtype.equals(MsgType.Image.toString())){//图片消息
			
		}else if(msgtype.equals(MsgType.Location.toString())){//地理位置消息
			
		}
		
		//如果没有对应的消息，默认返回订阅消息；
		if(StringUtils.isEmpty(respXml)){
			MsgText text = msgBaseDao.getMsgTextByInputCode(MsgType.SUBSCRIBE.toString());
			if(text != null){
				respXml = MsgXmlUtil.textToXml(WxMessageBuilder.getMsgResponseText(msgRequest, text));
			}
		}
		return respXml;
	}
	
	
	
	//同步粉丝列表(开发者在这里可以使用递归处理)
		private boolean doSyncPageList(String nextOpenId,MpAccount mpAccount){
			
			String accessToken = WxApiClient.getAccessToken(mpAccount);
			String url = WxApi.getBatchMaterialUrl(accessToken);
			JSONObject bodyObj = new JSONObject();
			bodyObj.put("date", );
			bodyObj.put("page_index", offset);
			String body = bodyObj.toString();
			try {
				JSONObject jsonObj = WxApi.httpsRequest(url, "POST", body);
			
			
			
			String url = WxApi.getShakearoundPagelistUrl(WxApiClient.getAccessToken(mpAccount));
			JSONObject jsonObject = WxApi.httpsRequest(url, HttpMethod.POST, null);
			if(jsonObject.containsKey("errcode")){
				return false;
			}
			List<AccountFans> fansList = new ArrayList<AccountFans>();
			if(jsonObject.containsKey("data")){
				if(jsonObject.getJSONObject("data").containsKey("openid")){
					JSONArray openidArr = jsonObject.getJSONObject("data").getJSONArray("openid");
					int length = 5;//同步5个
					if(openidArr.size() < length){
						length = openidArr.size();
					}
					for(int i = 0; i < length ;i++){
						Object openId = openidArr.get(i);
						AccountFans fans = WxApiClient.syncAccountFans(openId.toString(), mpAccount);
						fansList.add(fans);
					}
					//批处理
					fansDao.addList(fansList);
				}
			}
			return true;
		}
}
