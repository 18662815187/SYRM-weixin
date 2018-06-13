package com.lwyykj.core.sms;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lwyykj.core.sms.utils.HttpInvoker;
import com.lwyykj.core.sms.utils.RandomUtil;
import com.lwyykj.core.sms.utils.TokenUtil;

import net.sf.json.JSONObject;

/**
 * 模板短信DEMO
 *
 */
public class TemplateSms {
	public static String oauth_type="CC";//验证模式是CC
	public static JSONObject json=JSONObject.fromObject(TokenUtil.getAccessToken(oauth_type));
	public static String acc_t = json.getString("access_token");
	public static String APP_ID = "382009190000265633";//应用ID------登录平台在应用设置可以找到
	public static String APP_SECRET = "f4c25191aa4d9606bce7af50fa37f2df";//应用secret-----登录平台在应用设置可以找到
	public static String ACCESS_TOKEN = acc_t;//访问令牌AT-------CC模式，AC模式都可，推荐CC模式获取令牌
//	public static String TEMPLATE_ID = "";//模板ID


	
	public static Object sendSms(String tel,String code) throws Exception {
		//短信验证码模版
		String TEMPLATE_ID ="91550712";
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String timestamp = dateFormat.format(date);
		System.err.println(timestamp);
		Gson gson = new Gson();
		Map<String, String> map = new HashMap<String, String>();
//		String code = RandomUtil.randomFor6();
		//这里存放模板参数，如果模板没有参数直接用template_param={}
		map.put("param1", tel);
		map.put("param2", code);
//		map.put("param3","3" );


		String template_param = gson.toJson(map);
		System.out.println(template_param);
		String postUrl = "http://api.189.cn/v2/emp/templateSms/sendSms";
		
		String postEntity = "app_id=" + APP_ID + "&access_token="
				+ ACCESS_TOKEN + "&acceptor_tel=" + tel + "&template_id="
				+ TEMPLATE_ID + "&template_param=" + template_param
				+ "&timestamp=" + URLEncoder.encode(timestamp, "utf-8");
		System.out.println(postUrl);
		System.out.println(postEntity);
		String resJson = "";
		@SuppressWarnings("unused")
		String idertifier = null;
		Map<String, String> map2 =null;
		try {
			resJson = HttpInvoker.httpPost1(postUrl, null, postEntity);
			map2 = gson.fromJson(resJson,
					new TypeToken<Map<String, String>>() {
					}.getType());
			idertifier = map2.get("idertifier").toString();
		} catch (IOException e) {
			System.err.println(resJson);
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println(resJson);
			e.printStackTrace();
		}
		Object res_code = JSONObject.fromObject(resJson).get("res_code");
		System.err.println(resJson);
//		System.out.println(idertifier);
//		System.out.println(res_code);
		return res_code;

	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Object result = "";
		try {
				result = sendSms("15967125243",RandomUtil.randomFor6());
				System.out.println(result);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
