package com.lwyykj.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 业务常量
 * 
 * @author john
 *
 */
public class Constants {
	private static long fileNewName = 0;
	// 图片服务器
	public static final String IMG_URL = "http://www.zjyyxxw.com/SyrmWeiXin/";
	// 用户名
	public static final String USER_NAME = "USER_NAME";
	// 购物车 保存在cookie中的名称
	public static final String BUYER_CART = "BUYER_CART";
	// 登录中转页面
	public static final String LOGIN_URL = "../../sessionrun.jsp";
	//登录状态码
	public static final String LOGIN_STATUS ="LOGIN_STATUS";
	//登录时间
	public static final String LOGIN_TIME="LONGIN_TIME";

	// 图片新名字
	public synchronized static String getNewFileName() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String now = sdf.format(new Date());
		fileNewName++;
		return now + fileNewName;
	}
}
