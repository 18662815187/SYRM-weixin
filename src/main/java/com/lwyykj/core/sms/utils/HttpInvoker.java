package com.lwyykj.core.sms.utils;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * HTTP请求类
 * @author LiHong
 */
public class HttpInvoker {

	/**
	 * GET请求
	 * @param getUrl
	 * @throws IOException
	 * @return 提取HTTP响应报文包体，以字符串形式返回
	 */
	public static String httpGet(String getUrl,Map<String, String> getHeaders) throws IOException { 
		URL getURL = new URL(getUrl); 
		HttpURLConnection connection = (HttpURLConnection) getURL.openConnection(); 

        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("connection", "Keep-Alive");
        connection.setRequestProperty("user-agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
		if(getHeaders != null) {
			for(String pKey : getHeaders.keySet()) {
				connection.setRequestProperty(pKey, getHeaders.get(pKey));
			}
		}
		connection.connect();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder sbStr = new StringBuilder();
		String line;
		while ((line = bufferedReader.readLine()) != null) { 
			sbStr.append(line); 
		} 
		bufferedReader.close();
		connection.disconnect(); 
		return new String(sbStr.toString().getBytes(),"utf-8");
	}
	
	/**
	 * POST请求
	 * @param postUrl
	 * @param postHeaders
	 * @param postEntity
	 * @throws IOException
	 * @return 提取HTTP响应报文包体，以字符串形式返回
	 */
	public static String httpPost(String postUrl,Map<String, String> postHeaders, String postEntity) throws IOException {
		
		URL postURL = new URL(postUrl); 
		HttpURLConnection httpURLConnection = (HttpURLConnection) postURL.openConnection(); 
		httpURLConnection.setDoOutput(true);                 
		httpURLConnection.setDoInput(true); 
		httpURLConnection.setRequestMethod("POST"); 
		httpURLConnection.setUseCaches(false); 
		httpURLConnection.setInstanceFollowRedirects(true); 
		httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

		if(postHeaders != null) {
			for(String pKey : postHeaders.keySet()) {
				httpURLConnection.setRequestProperty(pKey, postHeaders.get(pKey));
			}
		}
		if(postEntity != null) {
			DataOutputStream out = new DataOutputStream(httpURLConnection.getOutputStream()); 
			out.writeBytes(postEntity); 
			out.flush(); 
			out.close(); // flush and close 
		}
		//connection.connect(); 
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream())); 
		StringBuilder sbStr = new StringBuilder();
		String line;
		while ((line = bufferedReader.readLine()) != null) { 
			sbStr.append(line); 
		} 
		bufferedReader.close();
		httpURLConnection.disconnect(); 
		return new String(sbStr.toString().getBytes(),"utf-8");
	} 
	/**
	 * POST请求 ,解决中文乱码问题
	 * @param postUrl
	 * @param postHeaders
	 * @param postEntity
	 * @throws IOException
	 * @return 提取HTTP响应报文包体，以字符串形式返回
	 */
	public static String httpPost1(String postUrl,Map<String, String> postHeaders, String postEntity) throws IOException {
		
		URL postURL = new URL(postUrl); 
		HttpURLConnection httpURLConnection = (HttpURLConnection) postURL.openConnection(); 
		httpURLConnection.setDoOutput(true);                 
		httpURLConnection.setDoInput(true); 
		httpURLConnection.setRequestMethod("POST"); 
		httpURLConnection.setUseCaches(false); 
		httpURLConnection.setInstanceFollowRedirects(true); 
		httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		StringBuilder sbStr = new StringBuilder();
		if(postHeaders != null) {
			for(String pKey : postHeaders.keySet()) {
				httpURLConnection.setRequestProperty(pKey, postHeaders.get(pKey));
			}
		}
		if(postEntity != null) {
			PrintWriter out = new PrintWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(),"utf-8"));   
			out.println(postEntity);  
			out.close();  
			BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection  
			        .getInputStream()));  
			  
			String inputLine; 
			while ((inputLine = in.readLine()) != null) {  
				sbStr.append(inputLine);  
			}  
			in.close();  
		}
		httpURLConnection.disconnect(); 
		return new String(sbStr.toString().getBytes(),"utf-8");
	} 


}
