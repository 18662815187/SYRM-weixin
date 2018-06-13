package com.lwyykj.core.sms.utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpsUtil {
	public static String doPost(String req_url, List<NameValuePair> form_params, boolean useSSL) {
		HttpClient httpclient = getHttpClient(useSSL);
		HttpPost httppost;
		HttpResponse response;
		try {
			URI uri = new URI(req_url);
			System.err.println("http post uri = " + uri);
			httppost = new HttpPost(uri);

			HttpEntity entity = new UrlEncodedFormEntity(form_params, "UTF-8");
			httppost.setEntity(entity);
			response = httpclient.execute(httppost);
			

			int status = response.getStatusLine().getStatusCode();
			System.err.println("http response status code = " + status);
			if (status == HttpStatus.SC_OK) {
				return EntityUtils.toString(response.getEntity(), "UTF-8");
			} else if(status == 302 || status == 307) {
				System.err.println("the http response status is : "+status);
				for(int i = 0; i < response.getAllHeaders().length; i++) {
					System.err.println("params of respons'" + i + ":" +response.getAllHeaders()[i]);
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return null;

	}

	public static HttpClient getHttpClient(boolean useSSL) {
		HttpClient httpclient = useSSL ? getHttpsClient() : new DefaultHttpClient();
		if (useProxy) {
			HttpHost proxy = new HttpHost(proxyHost, proxyPort);
			httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
		}
		return httpclient;
	}

	/**
	 * getHttpsClient()
	 * @return httpsClient
	 */
	private static HttpClient getHttpsClient() {
		HttpClient httpclient = new DefaultHttpClient();
		SSLContext sslContext = null;
		try {
			sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, new TrustManager[]{manager}, null);
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// Layered socket factory for TLS/SSL connections
		// SSLSocketFactory can be used to validate the
		// http://hc.apache.org/httpcomponents-client-ga/httpclient/apidocs/org/apache/http/conn/ssl/SSLSocketFactory.html
		SSLSocketFactory sslSocketFactory =
			new SSLSocketFactory(sslContext, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		ClientConnectionManager clientConnectionManager = httpclient.getConnectionManager();
		SchemeRegistry schemeRegistry = clientConnectionManager.getSchemeRegistry();
		schemeRegistry.register(new Scheme("https", 443, sslSocketFactory));
		return httpclient;
	}

	public static String proxyHost = "192.168.13.19";

	public static int proxyPort = 7777;

	public static boolean useProxy = false; 

	private static X509TrustManager manager = new X509TrustManager() {
		public void checkClientTrusted(X509Certificate[] xcs, String string)
		throws CertificateException {
			// TODO>>
		}

		public void checkServerTrusted(X509Certificate[] xcs, String string)
		throws CertificateException {
			// TODO>>
		}

		public X509Certificate[] getAcceptedIssuers() {
			// TODO>>
			return null;
		}
	};
}
