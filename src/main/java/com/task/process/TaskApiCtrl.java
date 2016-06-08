package com.task.process;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.core.util.DateUtil;
import com.wxapi.process.MpAccount;
import com.wxapi.process.WxMemoryCacheClient;
import com.wxapi.service.impl.ShakeroundServiceImpl;

/**
 * 微信与开发者服务器交互接口
 */
@Controller
@RequestMapping("/wxtask")
public class TaskApiCtrl {

	@Autowired
	private ShakeroundServiceImpl shakeroundService;

	// 查询微信摇周边的页面数据 
	@RequestMapping(value = "/getShakeroundPage")
	@ResponseBody
	public String getShakeroundPage(HttpServletRequest request) {
		System.out.println("---->getShakeroundPage now..");

		// 如果是多账号，根据url中的account参数获取对应的MpAccount处理即可
		MpAccount mpAccount = WxMemoryCacheClient.getSingleMpAccount();// 获取缓存中的唯一账号 
		String date = StringUtils.EMPTY ;
		if (mpAccount != null) {
			date = request.getParameter("date");// 日期

			Date pageDate = null;
			String [] date_lst = StringUtils.split(date,',');
			for (String s_date : date_lst) {
				try {
				pageDate = DateUtil.getTextDate(s_date, "yyyyMMdd");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("传入格式应该为[yyyyMMdd]"); 
				String failureMsg = "查询["+pageDate+"]页面数据失败"; 
				System.out.println("---->getShakeroundPage fail!");
				return failureMsg;
			}

			Calendar calendar = new GregorianCalendar();
			calendar.setTime(pageDate);
			calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
					0, 0, 0);
			pageDate = calendar.getTime();
			shakeroundService.getPagelist(pageDate, mpAccount); 
			} 
			System.out.println("---->getShakeroundPage success!"); 
		}
		System.out.println("---->getShakeroundPage end..");
		return "查询["+date+"]页面数据成功";
	}
	
	
	// 查询微信摇周边的页面数据 
	@RequestMapping(value = "/getPage" )
	@ResponseBody
	public String getPageInfo(HttpServletRequest request) {
		System.out.println("---->getPageInfo now..");

		// 如果是多账号，根据url中的account参数获取对应的MpAccount处理即可
		MpAccount mpAccount = WxMemoryCacheClient.getSingleMpAccount();// 获取缓存中的唯一账号 
		if (mpAccount != null) {
			shakeroundService.getZbPagesInfo(mpAccount); 
			System.out.println("---->getPageInfo success!"); 
		}
		System.out.println("---->getPageInfo end..");
		return "页面数据更新成功";
	}
}
