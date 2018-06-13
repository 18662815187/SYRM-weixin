<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="head.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>syrm-left</title>
<script type="text/javascript">
	function reload() {
		window.top.location.href = "<%=basePath%>/index.do"; 
	}
</script>
</head>
<body class="lbody">
	<div class="left">
		<%@ include file="date.jsp"%>
		<ul class="w-lful">
			<li><a href="#" onclick="reload()">首页</a></li>
			<!--  
			<li><a href="/modifyPwd/toModify" target="rightFrame">修改密码</a></li>
			<li><a href="/modifyPwd/toModifyPhone" target="rightFrame">修改联系电话</a></li>
			<li><a href="/apply/toApply" target="rightFrame">申请提现</a></li>
			<li><a href="/apply/list.do" target="rightFrame">提现记录</a></li>-->
			<!-- 
     	<li><a href="right.jsp" target="rightFrame">PV统计</a></li>
     	<li><a href="right.jsp" target="rightFrame">独立IP统计</a></li>
     	<li><a href="right.jsp" target="rightFrame">独立访客统计</a></li>
     	<li><a href="right.jsp" target="rightFrame">人均浏览次数统计</a></li>
     	<li><a href="right.jsp" target="rightFrame">来访网站</a></li>
     	<li><a href="right.jsp" target="rightFrame">来访页面</a></li>
     	<li><a href="right.jsp" target="rightFrame">来访关键字</a></li>
     	<li><a href="right.jsp" target="rightFrame">受访页面</a></li>
     	<li><a href="right.jsp" target="rightFrame">地区分布</a></li>
     	-->
		</ul>
	</div>
</body>
</html>