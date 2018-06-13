package com.lwyykj.core.sms.utils;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;



public class TokenUtil {	
	
	public static final String authorization_code = "";
	public static final String refresh_token = "";
	public static final String APP_ID = "382009190000265633" ;
	public static final String APP_SECRET = "f4c25191aa4d9606bce7af50fa37f2df" ;
	public static final String REDIRECT_URI = "https://oauth.api.189.cn/emp/" ;
	
	public static final String authorize_url = "https://oauth.api.189.cn/emp/oauth2/v3/authorize";
	public static final String access_token_url = "https://oauth.api.189.cn/emp/oauth2/v3/access_token";
	public static final String logout_url = "https://oauth.api.189.cn/emp/oauth2/v3/logout";

	public static final String get_ac_url =
		"https://oauth.api.189.cn/emp/oauth2/v3/authorize?app_id=" +APP_ID 
		+ "&redirect_uri=" + REDIRECT_URI
		+ "&response_type=code";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		System.getProperties().put("proxySet", "true");
//		System.getProperties().put("socksProxyHost", "192.168.13.19");
//		System.getProperties().put("socksProxyPort", 1080);
		// AC,IG,CC//{"res_code":"0","res_message":"Success","p_user_id":"99084135814","access_token":"eb7d0ddea79cc0fad22f33a6f52bbbaa1362638923925","expires_in":2592000,"refresh_token":"910eb1637fd741f165100c0079a9bddf1362638923925"}
		String oauth_model = "CC";
		String accessToken = getAccessToken(oauth_model);
		System.err.println(accessToken);
	}
	
	public static String getAccessToken(String oauth_type) {
		String req_url = access_token_url;
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("app_id", APP_ID));
		formparams.add(new BasicNameValuePair("app_secret", APP_SECRET));
		formparams.add(new BasicNameValuePair("redirect_uri", REDIRECT_URI));
		if("AC_1".equals(oauth_type)) {
			return get_ac_url;
		} else if("AC_2".equals(oauth_type)) {
			formparams.add(new BasicNameValuePair("grant_type", "authorization_code"));
			formparams.add(new BasicNameValuePair("code", authorization_code));
		} else if("IG".equals(oauth_type)) {
			req_url = authorize_url;
			formparams.add(new BasicNameValuePair("response_type", "token"));
		} else if("CC".equals(oauth_type)) {
			formparams.add(new BasicNameValuePair("grant_type", "client_credentials"));
		} else if("refresh".equals(oauth_type)) {
			formparams.add(new BasicNameValuePair("grant_type", "refresh_token"));
			formparams.add(new BasicNameValuePair("refresh_token", refresh_token));
			
		}
		return HttpsUtil.doPost(req_url, formparams, true);
	}
	

}
