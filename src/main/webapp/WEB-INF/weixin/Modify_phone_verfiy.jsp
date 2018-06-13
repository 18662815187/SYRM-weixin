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
<meta charset="utf-8" />
<title>生医容美药房管理系统--修改联系电话</title>
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
		$.ajax({
			type : 'post',
			url : '/modifyPwd/submit',
			data : $('#jvForm').serialize(),
			cache : false,
			async: false,
			dataType : 'json',
			success : function(data) {
				//alert(data);
				if (data.status == 1) {
					alert("修改成功，请重新登录！");
					window.top.location.href="/logout2.do";
				$("#msg").html(html);
				} else {
					var html = "<span style='color:red'>" + data.error
							+ "</span>";

					$("#error").html(html);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				alert(XMLHttpRequest.status);
                alert(XMLHttpRequest.readyState);
                alert(textStatus);
				// view("异常！");    
				alert("网络异常，请联系管理员1");
				window.location.href = "/modifyPwd/toModifyPhone?id=" + ${pharmacy.id};
			}
		});

	}
	$(document)
			.ready(
					function() {
						//发送验证倒计时
						var clock = '';
						var nums = 60;
						var btn;
						var tel = null;
						$("#aa")
								.click(
										function() {
											tel = document
													.getElementById("tel").value;
											//alert(tel);
											if (tel == null || tel == "") {
												alert("手机号不能为空，请输入！");
												document.getElementById("tel")
														.focus();
												return;
											}
											btn = this;
											btn.disabled = true; //将按钮置为不可点击
											btn.value = nums + '秒后可重新获取';
											$('#aa').css("background",
													"#cbcbc3")
											clock = setInterval(
													function() {
														nums--;
														if (nums > 0) {
															btn.value = nums
																	+ '秒后可重新获取';
														} else {
															clearInterval(clock); //清除js定时器
															btn.disabled = false;
															$('#aa')
																	.css(
																			"background",
																			"linear-gradient(#0084cd, #0048cd)")
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
	<form action="/modifyPwd/czPwd" method="post"
		enctype="multipart/form-data" id="jvForm">
		<div class="body">
			<table class="body_table">
				<tr>
					<td colspan="2"><span style="font-size: 30px;"> 填写新号码 </span></td>
				</tr>
				<tr>
					<td width="80px"><span>新手机号码：</span><input type="hidden"
						name="id" value="${pharmacy.id}"></input> <input type="hidden"
						name="type" value="${pharmacy.type}"></input> <input type="hidden"
						name="type" value="${pharmacy.isDel}"></input> <input
						type="hidden" name="type" value="${pharmacy.isShow}"></input></td>
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
					<td colspan="2"
						style="padding: 10px; height ='50px'; text-align: center"><div
							id="error" style="height: 35px"></div>
							<div id="msg" style="height: 35px"></div>
							 <input id="sb"
						type="button" name="button" value="提交修改" height="50px"
						style="float: right" onclick="sbClick()" /></td>
				</tr>
			</table>
		</div>
	</form>
</body>

</html>