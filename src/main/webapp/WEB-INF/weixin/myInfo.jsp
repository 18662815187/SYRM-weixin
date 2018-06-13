<%@ page language="java" import="java.util.*"  contentType="text/html; charset=utf-8"%>
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
<meta charset="utf-8"></meta>
<title>生医容美药房管理系统--个人资料管理</title>
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
input[type="submit"] {
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
</script>
</head>

<body style="background: url(/img/1.jpg);">
	<form action="/myInfo/saveUpdate" method="post"
		enctype="multipart/form-data" id="jvForm">
		<div class="body">
			<table class="body_table">
				<tr>
					<td colspan="2"><span style="font-size: 30px;"> 个人资料 </span></td>
				</tr>
				<tr>
					<td width="80px"><span>联系人电话：</span></td>
					<td><input id="tel" name="tel" type="text" size="22"
						disabled="disabled" value="${pharmacy.tel}"/><input type = "hidden" name="id" value="${pharmacy.id}"/></td>
						
				</tr>
				<tr>
					<td><span>药房全名：</span></td>
					<td><input type="text" id="pharmacyName" name="pharmacyName"
						size="25" disabled="disabled" value="${pharmacy.pharmacyName}"/></td>
				</tr>
				<tr>
					<td><span>联系人：</span></td>
					<td><input type="text" id="manager" name="manager" size="25" value="${pharmacy.manager}"/></td>
				</tr>
				<tr>
					<td><span>所属医院：</span></td>
					<td><input type="text" id="hos_name" name="hos_name" size="25" value="${pharmacy.hospital.user}" disabled="disabled"/></td>
				</tr>
				<tr>
					<td><span>药房性质：</span></td>
					<td><input id="type" type="radio" name="type" value="0"
						checked="checked" /> <span>私营药房</span> <input id="type"
						type="radio" name="type" value="1" /> <span>医院内部药房</span></td>
				</tr>
				<tr>
					<td><span>详细地址：</span></td>
					<td><input id="address" type="text" name="address" size="22" value="${pharmacy.address}"/></td>
				</tr>
				<tr>
					<td></td>
					<td><span style="color: red">例：xx省xx市xx区xx路xx号</span></td>
				</tr>
				
				<tr>
					<td></td>
					<td><span style="color: red">${msg}</span></td>
				</tr>
				<tr>
					<td colspan="2" style="padding: 10px;height='50px';text-align:center" ><div
							id="error" style="height: 35px"></div> <input id="sb"
						type="submit" name="button" value="提交修改" height="50px"
						style="float: right" /></td>
				</tr>
			</table>

		</div>
	</form>
</body>

</html>