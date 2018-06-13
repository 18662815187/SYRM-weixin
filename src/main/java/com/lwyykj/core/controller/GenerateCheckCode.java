package com.lwyykj.core.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lwyykj.common.VerifyCodeUtils;

/**
 * 验证码
 * 
 * @author john
 *
 */

@Controller
public class GenerateCheckCode {

	@RequestMapping(value = "/toImg")
	public String toImg() {

		return "image/image";
	}

	// 登录获取验证码
	@RequestMapping("/getSysManageLoginCode")
	@ResponseBody
	public String getSysManageLoginCode(HttpServletResponse response, HttpServletRequest request,HttpSession session) {
		String randcode =null;
		// 设置相应类型,告诉浏览器输出的内容为图片
		response.setContentType("image/jpeg");
		// 设置响应头信息，告诉浏览器不要缓存此内容
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		// 设置HttpOnly属性,防止Xss攻击
		response.setHeader("Set-Cookie", "name=value; HttpOnly");
		response.setDateHeader("Expire", 0);
		VerifyCodeUtils randomValidateCode = new VerifyCodeUtils();
		
		try {
			// 输出图片方法
			
			randcode = randomValidateCode.getRandcode(request, response, "imagecode");
//			System.out.println("这是验证码获取控制器的："+randcode);
//			request.getSession().setAttribute("rancode", randcode);
//			String key = (String) request.getSession().getAttribute("sessionkey");
//			System.out.println(request.getSession().getAttribute(key)+"session");
//			System.out.println(request.getSession().getAttribute("rancode")+"测试");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return randcode;
	}

	// 验证码验证，未使用这个方法，直接在登陆方法内验证了
	@RequestMapping(value = "/checkimagecode")
	@ResponseBody
	public String checkTcode(HttpServletRequest request, HttpServletResponse response) {
		String validateCode = request.getParameter("validateCode");
		String code = null;
		// 1:获取cookie里面的验证码信息
		Cookie[] cookies = request.getCookies();
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if ("imagecode".equals(cookie.getName())) {
					code = cookie.getValue();
					break;
				}
			}
		} else {
			// 1:获取session验证码的信息
			code = (String) request.getSession().getAttribute("imagecode");
		}

		// 2:判断验证码是否正确
		if (!StringUtils.isEmpty(validateCode) && validateCode.equals(code)) {
			return "ok";
		}
		return "error";
		// 这里我没有进行字母大小模糊的验证处理，感兴趣的你可以去试一下！
	}

}
