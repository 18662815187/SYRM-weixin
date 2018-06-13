<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@page session="false"%>
<%@ include file="../head.jsp"%>
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
<title>申请提现</title>
<link rel="stylesheet" type="text/css" href="css/index.css" />
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
/*以上为公共样式*/
.body {
	background-color: white;
	border-bottom: 2px solid #3281c9;
	padding: 20px;
	text-align: center;
}

.cue_box {
	text-align: center;
	position: relative;
}

.cue {
	background-color: #000000;
	color: white;
	position: absolute;
	display: none;
	padding: 5px;
	right: 30%;
	border-radius: 3px;
}

.head_w {
	font-size: 25px;
	color: #3281c9;
	background-color: white;
	border-bottom: 2px solid #3281c9;
	padding: 15px;
	border-radius: 5px 5px 0px 0px;
}

.foot {
	text-align: center;
	font-size: 20px;
	background-color: #f5f5f5;;
	border-bottom: 2px solid #3281c9;
	border-radius: 0px 0px 5px 5px;
}

.body span {
	color: #3281c9
}

.box {
	margin: 150px auto;
	width: 360px;
}

input[type="button"] {
	width: 100%;
	background: linear-gradient(#beeaff, #3281c9, #3281c9);
	border: 0px solid red;
	height: 35px;
	font-size: 20px;
	color: white;
}

input[type="text"] {
	background-color: #f5f5f5;
	padding: 0px 10px;
	border: 0px solid red;
	width: 200px;
	border-radius: 5px;
	color: black;
	height: 35px;
}
</style>

<script type="text/javascript">
	function sbClick() {
		$.ajax({
			type : 'post',
			url : '/apply/submitApply',
			data : $('#jvForm').serialize(),
			cache : false,
			dataType : 'json',
			success : function(data) {
				//alert(data);
				if (data.status == 1) {
					alert("提现成功！");
					window.location.href = "/apply/list.do";
				} else {
					var html = "<span style='color:red'>" + data.error
							+ "</span>";

					$("#error").html(html);
				}
			},
			error : function() {
				// view("异常！");    
				alert("网络异常，请联系管理员");
				window.location.href = "/register/toRegiste";
			}
		});
	}
</script>
</head>
<body style="background: url(../img/1.jpg);">
	<form id="jvForm" action="" method="post">
		<div class="box">
			<div class="head_w">申请提现</div>
			<div>
				<table class="body">
					<tr>
						<td><b>银行卡号：</b><input id="CardNumber" type="text"
							placeholder="请输入银行卡账号" size="15" maxlength="20" name="banknum"
							value="${withDraw.banknum}" /></td>
					</tr>
					<tr>
						<td><b>银行名称：</b><input id="bankName" type="text" placeholder="请输入银行名称"
							size="15" name="bankname" value="${withDraw.bankname}" /></td>
					</tr>
					<tr>
						<td><b>开户支行：</b><input id="bankAdderss" type="text"
							placeholder="请输入银行卡具体开户行" size="15" name="bankdeposit"
							value="${withDraw.bankdeposit}" /></td>
					</tr>
					<tr>
						<td><b>真实姓名：</b><input id="name" type="text" placeholder="请输入姓名"
							size="15" name="name" value="${withDraw.name}" /></td>
					</tr>
					<tr>
						<td><b>预留电话：</b><input id="telephone" type="text" placeholder="请输入银行预留电话"
							size="15" maxlength="11" name="tel" value="${withDraw.tel}" /></td>
					</tr>
					<tr>
						<td><b>提现金额：</b><input id="money" type="text" name="money"
							placeholder="请输入提现金额" size="15" /></td>
					</tr>
					<tr>
						<td><span>您的可用余额：￥</span><span id="balance">${pharmacy.money}</span></td>
					</tr>
					<tr id="error"></tr>
				</table>
				<div class="foot">
					<input type="button" id="registration_completed"
						onclick="sbClick()" value="提交申请" />
				</div>
			</div>
		</div>
	</form>
</body>
</html>
