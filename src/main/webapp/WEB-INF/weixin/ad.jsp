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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>通知公告</title>
<style type="text/css">
table{
				border: 1px solid #0084CD;
				padding:20px;
				border-radius: 10px;
				width: 100%;
				height: 100%;
			}
			p{
				text-indent:2em;
			}
			h1{
				color: #33a9ff;
			}
			.body{
				border: 1px solid #99b3d6;
				border-radius: 10px;
				padding: 10px;
				box-shadow: 5px 5px 5px #99b3d6;
				background-image: url(img/123.jpg);
				background-repeat: repeat-y; 
				position: absolute;
				margin: 0 auto;
				height:96%;
				width:100%;
			}
			.subtitle{
				text-align: center;
				color: #6b6b6b;
			}
			.box{
				box-shadow: -5px -5px 5px #99b3d6;
			}
			.text{
				background-color: #FFFFFF;
				padding: 20px;
				border-radius: 10px;
				border-top:3px solid #99b3d6 ;
				padding: 60px 30px;
			}
</style>
</head>
<body>
		<div class="body">
			<table >
				<tr height="50px">
					<th colspan="2"><h1 style="font-weight:bold;">${notice.title}</h1></th>
				</tr>
				<tr height="50px">
					<td class="subtitle">
						<span style="font-weight:bold;font-size:20px;">作者：</span>
						<span style="font-weight:bold;font-size:20px;">${notice.author}</span>
					</td>
					<td class="subtitle">
						<span style="font-weight:bold;font-size:20px;">时间：</span>
						<span style="font-weight:bold;font-size:20px;"><fmt:formatDate value="${notice.addtime}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="text" valign="top">
						<p style="font-weight:bold;font-size:20px;"><n/><n/>${notice.context}</p>
					</td>
				</tr>
				
			</table>
		</div>
	</body>
</html>
