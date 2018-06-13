package com.lwyykj.core.sms;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lwyykj.core.sms.utils.HttpInvoker;

import open189.sign.ParamsSign;

/**
 * 下发短信验证码DEMO
 *
 */
public class SendRandcode
{
	public static String APP_ID = "";//应用ID------登录平台在应用设置可以找到
	public static String APP_SECRET = "";//应用secret-----登录平台在应用设置可以找到
	public static String ACCESS_TOKEN = "";//访问令牌AT-------CC模式，AC模式都可，推荐CC模式获取令牌
	public static String URL = "";//验证码通知接口地址---需要保证接口地址在公网能被访问

	/**
	 * 1 获取信任码token get提交
	 * 2自定义短信验证码下发 post提交
	 * @param userPhone 下发手机号
	 * @return
	 * @throws Exception
	 */
	private static String sendSms(String userPhone) throws Exception {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timestamp = dateFormat.format(date);
		System.err.println(timestamp);
		TreeMap<String, String> paramsMap = new TreeMap<String, String>();
		paramsMap.put("app_id", APP_ID);
		paramsMap.put("access_token", ACCESS_TOKEN);
		paramsMap.put("timestamp", timestamp);
		
		String getUrl = "http://api.189.cn/v2/dm/randcode/token?app_id=" + APP_ID
				+ "&access_token=" + ACCESS_TOKEN 
				+ "&timestamp="+URLEncoder.encode(timestamp,"UTF-8") 
				+ "&sign="+ParamsSign.value(paramsMap, APP_SECRET);
		System.out.println(getUrl);
		String resJson = HttpInvoker.httpGet(getUrl,null);
		System.err.println(resJson);
		Gson gson = new Gson();
		Map<String, String> map = gson.fromJson(resJson, new TypeToken<Map<String, String>>() {}.getType());
		System.out.println(map.get("token"));//获取信任码
		
		
		//-----------------------------------------------------------
		TreeMap<String, String> paramsMap1 = new TreeMap<String, String>();
		paramsMap1.put("app_id", APP_ID);
		paramsMap1.put("access_token", ACCESS_TOKEN);
		paramsMap1.put("timestamp", timestamp);
		paramsMap1.put("token", map.get("token").toString());
		paramsMap1.put("url", URL);		
		paramsMap1.put("phone", userPhone);
		paramsMap1.put("exp_time", "2");
		String postUrl = "http://api.189.cn/v2/dm/randcode/send";
		System.out.println(map.get("token"));
		String postEntity = "app_id="+APP_ID
						  + "&access_token="+ACCESS_TOKEN
						  + "&token=" + map.get("token").toString()
				          + "&phone=" + userPhone			          
				          + "&url=" + URL					          
				          + "&exp_time=2"
				          + "&timestamp="+ URLEncoder.encode(timestamp, "UTF-8") 
				          + "&sign="+ParamsSign.value(paramsMap1, APP_SECRET);
		System.out.println(postEntity); 		
		String resJson1 = HttpInvoker.httpPost(postUrl,null,postEntity);
		Map<String,String> map2=gson.fromJson(resJson1, new TypeToken<Map<String, String>>() {}.getType());
		System.out.println(resJson1);
		String result=map2.get("identifier").toString();
		System.out.println(resJson1);
		return result;
	}

		public static void main(String[] args) throws Exception {
			
			try {
				 String result=sendSms("");
				 System.out.println(result);
			}finally{

		}

}


}