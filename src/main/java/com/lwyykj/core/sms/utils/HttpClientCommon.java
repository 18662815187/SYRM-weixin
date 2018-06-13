package com.lwyykj.core.sms.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;




public class HttpClientCommon {
	
	//是否启用代理
	public static boolean isProxy = false;
	@SuppressWarnings("unused")
	private static String proxyHost = "";
	@SuppressWarnings("unused")
	private static int proxyPort ;
	public static int connectionTimeOut=6000;
	public static int readTimeOut=30000;
	public String responseBody2(String url,String data){
		StringBuffer buf = new StringBuffer() ;
		
		return buf.toString() ;
	}
	
	
	/**
	 * httpClient 响应数据
	 * @param url
	 * @return
	 */
	public String responseBody(String url,String data) throws Exception{
		
		StringBuffer buf = new StringBuffer() ;
		
		
		HttpClient httpClient = new HttpClient();
		
		HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams(); 
		managerParams.setParameter("http.protocol.content-charset","UTF-8");
		
		managerParams.setConnectionTimeout(1000) ;
		managerParams.setSoTimeout(20000) ;
		
		// Constants.EMP_WS_IS_SET_PROXY = 1 需要设置代理服务器
//		if(ConfigConstants.SETPROXY == Constants.EMP_WS_PROXY_YES){
//			
//			httpClient.getHostConfiguration().setProxy(ConfigConstants.PROXYHOST, ConfigConstants.PROXYPORT) ;
//			
//		}
		
		PostMethod postMethod = new PostMethod(url) ;
		
		RequestEntity requestEntity = new StringRequestEntity(data, "application/xml","UTF-8"); 
		
		postMethod.setRequestEntity(requestEntity) ;
		
		try{

			int responseCode = httpClient.executeMethod(postMethod) ;
			
			if(responseCode == HttpStatus.SC_OK){
				
				InputStream resStream = postMethod.getResponseBodyAsStream(); 
				
				BufferedReader br = new BufferedReader(new InputStreamReader(resStream,"UTF-8")); 
				
				StringBuffer resBuffer = new StringBuffer(); 
				
				String resTemp = ""; 
				
				while((resTemp = br.readLine()) != null){ 
					
					resBuffer.append(resTemp); 
				
				} 
				
				String response = resBuffer.toString(); 
				
				buf.append(response) ;
				
			}else{
				throw new Exception(""+responseCode) ;
			}
				
			
			return buf.toString() ;
		}finally{
			postMethod.releaseConnection() ;
		}
		
	}
	
	public String responseBody(String url,String[][] params){
		String result = "" ;
		
		HttpClient client = new HttpClient();
		client.getParams().setParameter("http.protocol.content-charset","UTF-8");
		client.getHttpConnectionManager().getParams().setConnectionTimeout(3000);
		GetMethod getMethod = new GetMethod(url);
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
		getMethod.addRequestHeader("Content-type", "text/html; charset=UTF-8");
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 3000);
		NameValuePair name[] = new NameValuePair[params.length];
		for (int i = 0; i < name.length; i++) {
			NameValuePair xmlData = new NameValuePair(params[i][0], params[i][1]);
			name[i] = xmlData;
		}
		getMethod.setQueryString(name);
		try {
			int statusCode = client.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {
				StringBuffer temp = new StringBuffer();
				InputStream in = getMethod.getResponseBodyAsStream();
				BufferedReader buffer = new BufferedReader( new InputStreamReader(in, "UTF-8"));
				for (String tempstr = ""; (tempstr = buffer.readLine()) != null;){
					temp.append(tempstr);
				}
				buffer.close();
				in.close();
				result = temp.toString().trim();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getMethod.releaseConnection();
		}
		return result;
		
	}
	
	public void responseBody(String url){
		
	}
	public String responsePostBody(String url,String data) throws Exception{
		String result = "" ;
		
		HttpClient client = new HttpClient();
		client.getHttpConnectionManager().getParams().setConnectionTimeout(3000);
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(3, false));
		postMethod.addRequestHeader("Content-type", "text/html;charset=utf-8");
		postMethod.addRequestHeader("Accept-Encoding", "gzip");
		postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 30000);
		postMethod.setRequestEntity(new StringRequestEntity(data, null, "utf-8"));
		try {
			int statusCode = client.executeMethod(postMethod);
			if (statusCode == HttpStatus.SC_OK) {
				StringBuffer temp = new StringBuffer();
				InputStream in = postMethod.getResponseBodyAsStream();
				BufferedReader buffer = new BufferedReader( new InputStreamReader(in, "UTF-8"));
				for (String tempstr = ""; (tempstr = buffer.readLine()) != null;){
					temp.append(tempstr);
				}
				buffer.close();
				in.close();
				result = temp.toString().trim();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();
		}
		return result;
	}
	
	/**
	 * httpClient 响应数据
	 * @param url
	 * @return
	 */
	public static String post(String url,String data,String contentType) throws Exception{
		
		StringBuffer buf = new StringBuffer() ;
		
		HttpClient httpClient = new HttpClient( );
//		HttpClient httpClient = new HttpClient(new HttpClientParams(),new SimpleHttpConnectionManager(true) );
		
		HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams(); 
		managerParams.setParameter("http.protocol.content-charset","UTF-8");
		
		managerParams.setConnectionTimeout(10000) ;
		managerParams.setSoTimeout(20000) ;
		
		// Constants.EMP_WS_IS_SET_PROXY = 1 需要设置代理服务器
		if(isProxy){
			System.getProperties().put("socksProxySet", "true");  
	        System.getProperties().put("socksProxyHost", "192.168.13.19");  
	        System.getProperties().put("socksProxyPort", "1080");
		}
		
		PostMethod postMethod = new PostMethod(url) ;
		postMethod.addRequestHeader("Connection","close");
		RequestEntity requestEntity = new StringRequestEntity(data, contentType,"UTF-8"); 
		postMethod.setRequestEntity(requestEntity) ;
		
		try{

			int responseCode = httpClient.executeMethod(postMethod) ;
			if(responseCode == HttpStatus.SC_OK){
				
				InputStream resStream = postMethod.getResponseBodyAsStream(); 
				
				BufferedReader br = new BufferedReader(new InputStreamReader(resStream,"UTF-8")); 
				
				StringBuffer resBuffer = new StringBuffer(); 
				
				String resTemp = ""; 
				
				while((resTemp = br.readLine()) != null){ 
					
					resBuffer.append(resTemp); 
				
				} 
				
				String response = resBuffer.toString(); 
				
				buf.append(response) ;
				
			}else{
				throw new RuntimeException("Bad response status, " + responseCode);
			}
			return buf.toString() ;
		}
		finally{
			postMethod.releaseConnection() ;
			httpClient.getHttpConnectionManager().closeIdleConnections(0);
//			((SimpleHttpConnectionManager)httpClient.getHttpConnectionManager()).shutdown();
		}
		
	}
	
	/**
	 * httpClient 响应数据
	 * @param url
	 * @return
	 */
	public static String post(String url,NameValuePair[] data) throws Exception{

		StringBuffer buf = new StringBuffer() ;
		
		HttpClient httpClient = new HttpClient( );
//		HttpClient httpClient = new HttpClient(new HttpClientParams(),new SimpleHttpConnectionManager(true) );
		
		HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams(); 
		managerParams.setParameter("http.protocol.content-charset","UTF-8");
		
		managerParams.setConnectionTimeout(connectionTimeOut) ;
		managerParams.setSoTimeout(readTimeOut) ;
		
		PostMethod postMethod = new PostMethod(url) ;
		postMethod.setRequestBody(data) ;
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
		
		try{

			int responseCode = httpClient.executeMethod(postMethod) ;
			if(responseCode == HttpStatus.SC_OK){
				InputStream resStream = postMethod.getResponseBodyAsStream(); 
				
				BufferedReader br = new BufferedReader(new InputStreamReader(resStream,"GBK")); 
				
				StringBuffer resBuffer = new StringBuffer(); 
				
				String resTemp = ""; 
				
				while((resTemp = br.readLine()) != null){ 
					
					resBuffer.append(resTemp); 
				
				} 
				
				String response = resBuffer.toString(); 
				
				buf.append(response) ;
				
			}else{
				throw new RuntimeException("Bad response status, " + responseCode);
			}
			return buf.toString() ;
		}finally{
			postMethod.releaseConnection() ;
			httpClient.getHttpConnectionManager().closeIdleConnections(0);
//			((SimpleHttpConnectionManager)httpClient.getHttpConnectionManager()).shutdown();
		}
	}
	
	/**
	 * httpClient 响应数据
	 * @param url
	 * @return
	 */
	public static String post(String url,NameValuePair[] data,Header[] header) throws Exception{
		
		StringBuffer buf = new StringBuffer() ;
		
		HttpClient httpClient = new HttpClient( );
//		HttpClient httpClient = new HttpClient(new HttpClientParams(),new SimpleHttpConnectionManager(true) );
		
		HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams(); 
		managerParams.setParameter("http.protocol.content-charset","UTF-8");
		
		managerParams.setConnectionTimeout(connectionTimeOut) ;
		managerParams.setSoTimeout(readTimeOut) ;
		
		PostMethod postMethod = new PostMethod(url) ;
		if(null != header){
			for(Header h : header){
				postMethod.setRequestHeader(h) ;
			}
		}
//		Stringentity
//		RequestEntity requestEntity = new StringRequestEntity(data, contentType,"UTF-8"); 
		postMethod.setRequestBody(data) ;
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
		
		try{

			int responseCode = httpClient.executeMethod(postMethod) ;
			if(responseCode == HttpStatus.SC_OK){
				
				InputStream resStream = postMethod.getResponseBodyAsStream(); 
				
				BufferedReader br = new BufferedReader(new InputStreamReader(resStream,"UTF-8")); 
				
				StringBuffer resBuffer = new StringBuffer(); 
				
				String resTemp = ""; 
				
				while((resTemp = br.readLine()) != null){ 
					
					resBuffer.append(resTemp); 
				
				} 
				
				String response = resBuffer.toString(); 
				
				buf.append(response) ;
				
			}else{
				throw new RuntimeException("Bad response status, " + responseCode);
			}
			return buf.toString() ;
		}finally{
			postMethod.releaseConnection() ;
			httpClient.getHttpConnectionManager().closeIdleConnections(0);
//			((SimpleHttpConnectionManager)httpClient.getHttpConnectionManager()).shutdown();
		}
	}
	/**
	 * @description: get方式提交
	 * @author: 吴鼎良 Dec 26, 2011
	 * @param url 接口URL地址
	 * @param param	参数名称
	 * @param content 内容
	 * @param isProxy 是否启用代理
	 * @return String
	 */
	public static String get(String url, String content) {
		if(content == null || "".equals(content)){
			content = "text/html";
		}
		String result = "";
		// Create an instance of HttpClient.
		HttpClient client = new HttpClient();
		// set proxy
		// set charset
		client.getParams().setParameter("http.protocol.content-charset", "utf-8");
		// set http client timeout
		client.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
		client.getHttpConnectionManager().getParams().setSoTimeout(20000);
		// Create a method instance.
		GetMethod getMethod = new GetMethod(url);
		getMethod.addRequestHeader("Connection","close");
		// Provide custom retry handler is necessary
		getMethod.addRequestHeader("Content-type", "text/html; charset=" + "utf-8");
		// set get method timeout
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 13000);
		// set query string
		InputStream in = null;
		BufferedReader buffer = null;
		try {
			// Execute the method.
			int statusCode = client.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				throw new RuntimeException("Bad response status, " + getMethod.getStatusLine());
			}
			// Read the response body.
	//		byte[] responseBody = getMethod.getResponseBody();
			 StringBuffer temp = new StringBuffer();
	         in = getMethod.getResponseBodyAsStream();
	         buffer = new BufferedReader(new InputStreamReader(in,"UTF-8"));
	         for(String tempstr = ""; (tempstr = buffer.readLine()) != null;)
	                temp = temp.append(tempstr);
	            
	         result = temp.toString().trim();
			// Deal with the response.
			// Use caution: ensure correct character encoding and is not binary
			// data
//			result = new String(responseBody);
		} catch (HttpException e) {
			throw new RuntimeException(e.getMessage());
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			getMethod.releaseConnection();
			client.getHttpConnectionManager().closeIdleConnections(0);
		}

		return result;
	}
	public static void main(String[] args) throws Exception{
		
	}

}


