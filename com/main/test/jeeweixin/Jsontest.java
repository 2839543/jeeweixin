package jeeweixin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.core.util.DateUtil;
import com.wxcms.domain.ShakearoundPage;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Jsontest {

	static String s = "{\"data\":{\"pages\":[{\"click_pv\":54,\"click_uv\":43,\"page_id\":990808,\"shake_pv\":481,\"shake_uv\":140},{\"click_pv\":7,\"click_uv\":6,\"page_id\":2460992,\"shake_pv\":52,\"shake_uv\":17},{\"click_pv\":24,\"click_uv\":19,\"page_id\":2869809,\"shake_pv\":123,\"shake_uv\":63},{\"click_pv\":74,\"click_uv\":50,\"page_id\":3004812,\"shake_pv\":716,\"shake_uv\":197},{\"click_pv\":42,\"click_uv\":24,\"page_id\":3161707,\"shake_pv\":140,\"shake_uv\":67},{\"click_pv\":14,\"click_uv\":11,\"page_id\":3265576,\"shake_pv\":143,\"shake_uv\":28}]},\"date\":1464969600,\"errcode\":0,\"errmsg\":\"success.\",\"page_index\":1,\"total_count\":6}";
	
	 
	
	public static void main(String[] args) {
		
		Calendar tmpCalendar = Calendar.getInstance();
		tmpCalendar.setTime(new Date());
		Integer pageDate = Integer.parseInt(DateUtil.getDateText(tmpCalendar.getTime(),"yyyyMMdd"));
		pageDate.toString();
		
		Date date = new Date();
		 Calendar calendar = new GregorianCalendar();
		 calendar.setTime(new Date());
		 calendar.add(Calendar.DATE, -1); 
		 
		 calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),0,0,0);
		 date = calendar.getTime(); 
		 String date_str = Long.toString(date.getTime()).substring(0,10);
		 date_str.toString();
		 Integer i_date =  Integer.parseInt(Long.toString(date.getTime()).substring(0,10));
		 i_date.toString();
		
		// TODO Auto-generated method stub
		JSONObject jsonObject = JSONObject.fromObject(s);
		if(jsonObject.containsKey("errcode")){
			//return false;
		}
		List<ShakearoundPage> pageList = new ArrayList<ShakearoundPage>();
		if(jsonObject.containsKey("data")){
			if(jsonObject.getJSONObject("data").containsKey("pages")){
				JSONArray pageArr = jsonObject.getJSONObject("data").getJSONArray("pages");
				int length = 50;//同步5个
				if(pageArr.size() < length){
					length = pageArr.size();
				}
				for(int i = 0; i < length ;i++){
					JSONObject page_json = (JSONObject) pageArr.get(i);
					ShakearoundPage page = new ShakearoundPage();
					
					page.setPage_id(page_json.get("page_id").toString()); 
					page.setClick_pv( (Integer)page_json.get("click_pv"));
					page.setClick_uv((Integer)page_json.get("click_uv"));
					page.setShake_pv((Integer)page_json.get("shake_pv"));
					page.setShake_uv((Integer)page_json.get("shake_uv"));
					 
					pageList.add(page);
					
				}
	}
		}
		}
}
