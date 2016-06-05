package com.task.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import com.wxapi.process.ErrCode;
import com.wxapi.process.MpAccount;
import com.wxapi.process.WxMemoryCacheClient;
import com.wxapi.service.impl.MyServiceImpl;

import net.sf.json.JSONObject;


/**
 * 微信与开发者服务器交互接口
 */ 
@Controller
public class TaskApiCtrl {
	
	@Autowired
	private MyServiceImpl myService;
	
	//查询微信公众账号菜单 
	public ModelAndView getMenu() {
		System.out.println("---->getMenu now..");
		JSONObject rstObj = null;
		MpAccount mpAccount = WxMemoryCacheClient.getSingleMpAccount();//获取缓存中的唯一账号
		if(mpAccount != null){
			//rstObj = myService.deleteMenu(mpAccount);
			rstObj = myService.getMenu(mpAccount);
			if(rstObj != null && rstObj.getInt("errcode") == 0){
				ModelAndView mv = new ModelAndView("common/success");
				mv.addObject("successMsg", "查询菜单成功");
				System.out.println("---->getMenu success!");
				return mv;
			}
		}
		ModelAndView mv = new ModelAndView("common/failure");
		String failureMsg = "查询菜单失败";
		if(rstObj != null){
			failureMsg += ErrCode.errMsg(rstObj.getInt("errcode"));
		}
		mv.addObject("failureMsg", failureMsg);
		System.out.println("---->getMenu fail!");
		return mv;
	}
}




