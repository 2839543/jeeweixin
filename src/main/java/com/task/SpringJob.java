package com.task;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.wxapi.process.MpAccount;
import com.wxapi.process.WxMemoryCacheClient;
import com.wxapi.service.impl.ShakeroundServiceImpl;

@Component
public class SpringJob {
	static int count = 0; 
	
	@Autowired
	private ShakeroundServiceImpl shakeroundService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass()); 
	
	@Scheduled(cron = "0 0 23 * * ?") //每天晚上23点钟执行
	public void run() {
		getShakeroundPage(); 
	}
	
	// 查询微信公众账号菜单
		public void getShakeroundPage() {
			System.out.println("---->getShakeroundPage now.."); 
			MpAccount mpAccount = WxMemoryCacheClient.getSingleMpAccount();// 获取缓存中的唯一账号
			if (mpAccount != null) { 
				Date date = new Date();
				 Calendar calendar = new GregorianCalendar();
				 calendar.setTime(new Date());
				 calendar.add(Calendar.DATE, -1); 
				 
				 calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),0,0,0);
				 date = calendar.getTime(); 
				shakeroundService.getPagelist(date , mpAccount); 
			}
			
			System.out.println("---->getShakeroundPage end.."); 
		}
}