<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<base href="<%=basePath%>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" href="./img/108.ico" type="img/108.ico">
<link rel="shortcut icon" href="./img/108.ico" type="img/108.ico">
<title>生意容美药店后台</title>
<meta name="author" content="DeathGhost">
<link rel="stylesheet" type="text/css" href="./css/style.css"
	tppabs="css/style.css">
<style>
body {
	height: 100%;
	background: #16a085;
	overflow: hidden;
}

canvas {
	z-index: -1;
	position: absolute;
}
</style>
<script src="./js/jquery.js"></script>
<script src="./js/Particleground.js" tppabs="js/Particleground.js"></script>
<script>
	$(document).ready(function() {
		//粒子背景特效
		$('body').particleground({
			dotColor : '#5cbdaa',
			lineColor : '#5cbdaa'
		});
		//验证码
		//createCode();
		flushValidateCode();
		//测试提交，对接程序删除即可
		$(".submit_btn").click(function() {
			location.href = "javascrpt:;"/*tpa=http://***index.html*/;
		});
	});
	/* 刷新生成验证码 */
	function flushValidateCode() {
		var validateImgObject = document.getElementById("codeValidateImg");
		validateImgObject.src = "<%=basePath%>/getSysManageLoginCode?time="
				+ new Date();
	}
	function checkCell() {
		var tel = document.getElementById("loginName").value;
		//alert(tel);
		var code = document.getElementById("J_codetext").value;
		var pass = document.getElementById("pwd").value;

		if (null == code || "" == code) {
			alert("验证码不能为空！");
			return;
		}
		if (null == tel || "" == tel) {
			alert("用户名不能为空！");
			return;
		}
		if (null == pass || "" == pass) {
			alert("密码不能为空！");
			return;
		}

		document.getElementById("aaa").submit();
	}
	//跳转注册页面
	function gotoReg() {
	window.location.href="/register/toRegister";
	}
</script>
</head>
<style type="text/css" id="1897943781592"></style>
<body>
	<form id="aaa" action="/login" method='post'>
		<canvas class="pg-canvas" width="1920" height="921"></canvas>
		<dl class="admin_login">
			<dt>
				<strong>生医容美药店管理系统</strong> <em>Management System</em>
			</dt>
			<dd class="user_icon">
				<input type="text" id="loginName" name="tel" placeholder="账号"
					class="login_txtbx">
			</dd>
			<dd class="pwd_icon">
				<input type="password" id="pwd" name="password" placeholder="密码"
					class="login_txtbx">
			</dd>
			<dd class="val_icon">
				<div class="checkcode">
					<input type="text" id="J_codetext" name="verifyCode"
						placeholder="验证码" maxlength="4" class="login_txtbx">
				</div>
				<div style="width: 2px; height: 42px; float: left"></div>
				<div style="width: 82px; height: 42px; float: left">
					<img style="width: 91px; height: 42px; float: left"
						id="codeValidateImg" onclick="javascript:flushValidateCode();">
				</div>
				<div style="width: 4px; height: 42px; float: left"></div>
				<input type="button" value="点击换一张" class="ver_btn"
					onclick="flushValidateCode();">
			</dd>
			<dd>
			<div style="width:130px;float:left;heigth:42px;">
			<input type="submit" value="立即登陆" class="submit_btn"
					onclick="checkCell()">
			</div>
			<div style="width:130px;heigth:42px;float:right;">
				<input type="button" value="申请入驻"
					class="stand_btn" onclick="window.location.href='/register/toRegister'"/>
			</div>
			</dd>
			<div style="width: 300px; height: 20px; display: block;">
				<a href="#" style="color: blue; float: right">忘记密码?</a>
			</div>
			<div <c:if test="${empty error }">style="display: none;"</c:if>
				class="msg-error">
				<b style="color: red">${error}</b>
			</div>
			<dd>
				<p>Copyright © 2012 - 2017 杭州临武医药科技有限公司 版权所有</p>
				<p>浙ICP备12006601号-2</p>
			</dd>
		</dl>
		</dd>
		</dl>
		<div
			style="position: static; width: 0px; height: 0px; border: none; padding: 0px; margin: 0px;">
			<div id="trans-tooltip">
				<div id="tip-left-top"
					style="background: url(&quot;chrome-extension://ikkepelhgbcgmhhmcmpfkjmchccjblkd/imgs/map/tip-left-top.png&quot;);">
				</div>
				<div id="tip-top"
					style="background: url(&quot;chrome-extension://ikkepelhgbcgmhhmcmpfkjmchccjblkd/imgs/map/tip-top.png&quot;) repeat-x;">
				</div>
				<div id="tip-right-top"
					style="background: url(&quot;chrome-extension://ikkepelhgbcgmhhmcmpfkjmchccjblkd/imgs/map/tip-right-top.png&quot;);">
				</div>
				<div id="tip-right"
					style="background: url(&quot;chrome-extension://ikkepelhgbcgmhhmcmpfkjmchccjblkd/imgs/map/tip-right.png&quot;) repeat-y;">
				</div>
				<div id="tip-right-bottom"
					style="background: url(&quot;chrome-extension://ikkepelhgbcgmhhmcmpfkjmchccjblkd/imgs/map/tip-right-bottom.png&quot;);">
				</div>
				<div id="tip-bottom"
					style="background: url(&quot;chrome-extension://ikkepelhgbcgmhhmcmpfkjmchccjblkd/imgs/map/tip-bottom.png&quot;) repeat-x;"></div>
				<div id="tip-left-bottom"
					style="background: url(&quot;chrome-extension://ikkepelhgbcgmhhmcmpfkjmchccjblkd/imgs/map/tip-left-bottom.png&quot;);"></div>
				<div id="tip-left"
					style="background: url(&quot;chrome-extension://ikkepelhgbcgmhhmcmpfkjmchccjblkd/imgs/map/tip-left.png&quot;);"></div>
				<div id="trans-content"></div>
			</div>
			<div id="tip-arrow-bottom"
				style="background: url(&quot;chrome-extension://ikkepelhgbcgmhhmcmpfkjmchccjblkd/imgs/map/tip-arrow-bottom.png&quot;);">
			</div>
			<div id="tip-arrow-top"
				style="background: url(&quot;chrome-extension://ikkepelhgbcgmhhmcmpfkjmchccjblkd/imgs/map/tip-arrow-top.png&quot;);"></div>
		</div>
	</form>
</body>
</html>