package com.wxapi.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.core.util.DateUtil;
import com.wxapi.process.MpAccount;
import com.wxapi.process.WxApiClient;
import com.wxapi.service.ShakeroundService;
import com.wxcms.domain.ShakearoundPage;
import com.wxcms.domain.ZbPageInfo;
import com.wxcms.mapper.ShakearoundPageDao;
import com.wxcms.mapper.ZbPageInfoDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 业务消息处理 开发者根据自己的业务自行处理消息的接收与回复；
 */

@Service
public class ShakeroundServiceImpl implements ShakeroundService {

	@Autowired
	private ShakearoundPageDao shakearoundPageDao;
	
	@Autowired
	private ZbPageInfoDao zbPageInfoDao; 

	/**
	 * 获取页面统计数据
	 */
	public boolean getPagelist(Date date,MpAccount mpAccount) {
		
		boolean hasnext = true;
		JSONObject jsonObject;
		int page_index = 0 ; 
		 
			Integer i_date =  Integer.parseInt(Long.toString(date.getTime()).substring(0,10));
			Calendar tmpCalendar = Calendar.getInstance();
			tmpCalendar.setTime(date);
			Integer pageDate = Integer.parseInt(DateUtil.getDateText(tmpCalendar.getTime(),"yyyyMMdd"));
			while (hasnext) {
				 jsonObject = WxApiClient.getShakearoundPagelist(i_date, ++page_index, mpAccount);
					if(jsonObject.containsKey("errcode")){
						if(Integer.parseInt(jsonObject.get("errcode").toString())  > 0) 
						return false;
					}
				List<ShakearoundPage> pageList = new ArrayList<ShakearoundPage>();
				if(jsonObject.containsKey("data")){
					if(jsonObject.getJSONObject("data").containsKey("pages")){
						JSONArray pageArr = jsonObject.getJSONObject("data").getJSONArray("pages");
						
						int length = 50;//同步50个
						System.out.println("---->getShakeroundPage apgeArrSize:"+pageArr.size());
						if(pageArr.size() < length){
							length = pageArr.size();
							hasnext = false;
						}
						for(int i = 0; i < length ;i++){
							JSONObject page_json = (JSONObject) pageArr.get(i);
							ShakearoundPage page = new ShakearoundPage();
							
							page.setPage_id(page_json.get("page_id").toString()); 
							page.setClick_pv( (Integer)page_json.get("click_pv"));
							page.setClick_uv((Integer)page_json.get("click_uv"));
							page.setShake_pv((Integer)page_json.get("shake_pv"));
							page.setShake_uv((Integer)page_json.get("shake_uv"));
							page.setDate(pageDate);
							 
							pageList.add(page);
							shakearoundPageDao.add(page);
						} 
					} 
				}
			}
			return true;
	}

	@Override
	public boolean getPageInfo(String page_ids, MpAccount mpAccount) { 
		WxApiClient.getZbPagesInfo(page_ids, mpAccount);
		
		return false;
	}

	@Override
	public boolean getZbPagesInfo( MpAccount mpAccount) {
		int begin = 0 ;
		int count = 50 ;
		boolean hasmore = true;
		JSONObject jsonObject;
		while(hasmore){			
			jsonObject = WxApiClient.getZbPagesInfo(begin, count, mpAccount);
			
			if(jsonObject.containsKey("errcode")){
				if(Integer.parseInt(jsonObject.get("errcode").toString())  > 0) {
					 System.out.println("errmsg:"+jsonObject.get("errmsg"));
					 return false;
				}
			}
		List<ZbPageInfo> pageList = new ArrayList<ZbPageInfo>();
		if(jsonObject.containsKey("data")){
			if(jsonObject.getJSONObject("data").containsKey("pages")){
				JSONArray pageArr = jsonObject.getJSONObject("data").getJSONArray("pages");
				 
				System.out.println("---->getZbPagesInfo apgeArrSize:"+pageArr.size());
				if(pageArr.size() < count){
					count = pageArr.size();
					hasmore = false;
				}
				for(int i = 0; i < count ;i++){
					JSONObject page_json = (JSONObject) pageArr.get(i);
					ZbPageInfo page = new ZbPageInfo();
					
					page.setComment(page_json.get("comment").toString()); 
					page.setDescription( page_json.get("description").toString());
					page.setIcon_url(page_json.get("icon_url").toString());
					page.setPage_id(page_json.get("page_id").toString());
					page.setPage_url(page_json.get("page_url").toString());
					page.setTitle(page_json.get("title").toString()); 
					 
					pageList.add(page);
					zbPageInfoDao.add(page);
				} 
			} 
		} 
	}
		return true; 
	
	}

	@Override
	public boolean getZbPagesInfo(Integer begin, Integer count, MpAccount mpAccount) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
