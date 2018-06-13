<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>

<head>
<base href="<%=basePath%>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>生医容美药房管理系统--注册页</title>
<script src="./js/jquery.js"></script>
<style type="text/css">
* {
	font-family: "微软雅黑";
}

li {
	list-style-type: none;
}

ul {
	overflow: auto;
	height: 100px;
}

body {
	background-color: #f5f5f5;
}

table {
	width: 100%;
}

.body {
	width: 358px;
	border: 1px solid #e5e5e5;
	background-color: #fff;
	border-radius: 5px;
	margin: 150px auto;
	padding: 20px;
}

.body_table tr td {
	padding: 8px 0px;
}

input[type="button"] {
	background: linear-gradient(#0084cd, #0048cd);
	border: 0px solid red;
	border-radius: 3px;
	color: #FFFFFF;
	height: 35px;
}

input[type="text"] {
	height: 30px;
	font-size: 15px;
	border-radius: 3px;
	padding-left: 10px;
}

#popup {
	position: absolute;
	top: 200px;
	right: 200px;
	border: 1px solid #E5E5E5;
	padding: 20px;
	background-color: #FFFFFF;
	border-radius: 5px;
	display: none;
}
</style>
<script type="text/javascript">
		function sbClick() {
			var pwd = document.getElementById("pwd").value;
			var pwd1 = document.getElementById("pwd1").value;
			var code = document.getElementById("code").value;
			if (null == code) {
				alert("短信验证码不能为空！");
				return;
			} else {
				if (pwd != pwd1) {
					alert("两次输入的密码不一样，请重新输入！");
					return;
				} else {
					$.ajax({
						type : 'post',
						url : '/register/reg',
						data : $('#jvForm').serialize(),
						cache : false,
						dataType : 'json',
						success : function(data) {
							//alert(data);
							if (data.status == 1) {
								alert("注册成功，请登录后申请实名认证，否则将无法正常使用！");
								window.location.href="/toLogin";
							} else {
								var html = "<span style='color:red'>"
										+ data.error + "</span>";
								
								$("#error").html(html);
							}
						},
						error : function() {
							//view("异常！");   
							alert("网络异常，请联系管理员");
							window.location.href="/register/toRegiste";
						}
					});

				}
			}
		}
	$(document).ready(function() {
						//发送验证倒计时
						var clock = '';
						var nums = 60;
						var btn;
						var tel = null;
						$("#aa").click(function() {
											tel = document.getElementById("tel").value;
											//alert(tel);
											if (tel == null || tel == "") {
												alert("手机号不能为空，请输入！");
												document.getElementById("tel").focus();
												return;
											}
											btn = this;
											btn.disabled = true; //将按钮置为不可点击
											btn.value = nums + '秒后可重新获取';
											$('#aa').css("background","#cbcbc3")
											clock = setInterval(
													function() {
														nums--;
														if (nums > 0) {
															btn.value = nums+ '秒后可重新获取';
														} else {
															clearInterval(clock); //清除js定时器
															btn.disabled = false;
															$('#aa').css("background","linear-gradient(#0084cd, #0048cd)")
															btn.value = '点击发送验证码';
															nums = 60; //重置时间
														}
													}, 1000); //一秒执行一次
											$
													.ajax({
														type : 'post',
														url : '/sendSms',
														data : "tel=" + tel,
														cache : false,
														dataType : 'json',
														success : function(data) {
															if (data.res_code == 0) {
																alert("验证码已发送，请注意查收！");
															} else {
																alert("验证码发送失败，请注意手机号是否正确，或者联系网站管理员！");
															}
														},
														error : function() {
															// view("异常！");    
															alert("网络异常，请联系管理员");
														}
													});
										})
					})

	
</script>
</head>

<body style="background: url(/img/1.jpg);">
	<form action="/register/reg" method="post"
		enctype="multipart/form-data" id="jvForm">
		<div class="body">
			<table class="body_table">
				<tr>
					<td colspan="2"><span style="font-size: 30px;"> 申请入驻 </span></td>
				</tr>
				<tr>
					<td width="80px"><span>手机号码：</span></td>
					<td><input id="tel" name="tel" type="text"
						placeholder="输入手机号码" size="22" /><span style="color: red">*</span>
						<input type="button" id="aa" value="点击发送验证码" /></td>

				</tr>
				<tr>
					<td><span>手机验证：</span></td>
					<td><input id="code" name="code" type="text"
						placeholder="请输入手机验证码" size="22" /><span style="color: red">*</span></td>
				</tr>
				<tr>
					<td><span>登录密码：</span></td>
					<td><input id="pwd" name="password" type="text"
						placeholder="请输入登录密码" size="22" /><span style="color: red">*</span></td>
				</tr>
				<tr>
					<td><span>再次输入登录密码：</span></td>
					<td><input id="pwd1" name="pwd" type="text"
						placeholder="请再次输入登录密码" size="22" /><span style="color: red">*</span></td>
				</tr>
				<tr>
					<td><span>药房全名：</span></td>
					<td><input type="text" id="pharmacyName" name="pharmacyName"
						placeholder="请输入店名" size="22" /><span style="color: red">*</span></td>
				</tr>
				<tr>
					<td><span>联系人：</span></td>
					<td><input type="text" id="manager" name="manager"
						placeholder="请输入负责人姓名" size="22" /><span style="color: red">*</span></td>
				</tr>
				<tr>
					<td><span>所属医院：</span></td>
					<td><input type="text" name="hosName" placeholder="请输入所属医院"
						size="22" id="hosName" /> <span style="color: red">*</span> <!-- <input type="button" name="" id="lookup" value="查找所属医院" /> -->
					</td>
				</tr>
				<tr>
					<td><span>药房性质：</span></td>
					<td><input id="type" type="radio" name="type" value="0"
						checked="checked" /> <span>私营药房</span> <input id="type"
						type="radio" name="type" value="1" /> <span>医院内部药房</span><span
						style="color: red">*</span></td>
				</tr>
				<tr>
					<td><span>详细地址：</span></td>
					<td><input id="address" type="text" name="address"
						placeholder="请输入详细地址" size="22" /></td>
				</tr>

				<tr>
					<td colspan="2" style="padding: 10px;height="50px";text-align:center" ><div
							id="error" style="height:35px"></div>
						<input id="sb" type="button" name="button" value="申请入驻"
						height="50px" style="float: right" onclick="sbClick()"/></td>
				</tr>
			</table>
			<!-- 
			<div id="popup">
				<input type="text" name="" id="hospital_val" value="" /> <input
					type="button" name="" id="hospital_search" value="搜索医院" /><br />
				<div id="hospital">
					<ul>
						<li>杭州市第一人民医院</li>
						<li>杭州市第二人民医院</li>
						<li>杭州市第仨人民医院</li>
						<li>杭州市第四人民医院</li>
						<li>杭州市第五人民医院</li>
						<li>杭州市第六人民医院</li>
						<li>杭州市第七人民医院</li>
						<li>杭州市第八人民医院</li>
						<li>杭州市第一人民医院</li>
						<li>杭州市第一人民医院</li>
					</ul>
				</div>

				<input type="button" name="" id="hospital_button" value="确定" />
				<p style="font-size: 12px; color: #cecdcd;">若是列表中没有所相应的医院，请手动输入</p>
			</div>
			 -->

		</div>
	</form>
</body>

</html>