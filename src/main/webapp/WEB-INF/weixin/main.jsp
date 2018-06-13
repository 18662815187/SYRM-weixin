<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>babasport-main</title>
</head>
<frameset cols="227,*" frameborder="0" border="0" framespacing="0">
	<frame src="<%=basePath%>/left.do" name="leftFrame" noresize="noresize" id="leftFrame" />
	<frame src="<%=basePath%>/right.do" name="rightFrame" id="rightFrame" />
</frameset>
<noframes><body></body></noframes>
</html>
