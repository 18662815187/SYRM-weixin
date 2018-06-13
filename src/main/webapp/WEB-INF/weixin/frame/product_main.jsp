<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<title>jeecms-main</title>
</head>
<frameset cols="227,*" frameborder="0" border="0" framespacing="0">
	<frame src="<%=basePath%>/frame/product_left.do" name="leftFrame" noresize="noresize" id="leftFrame" />
	<frame src="<%=basePath%>/activity/list.do" name="rightFrame" id="rightFrame" />
</frameset>
<noframes><body></body></noframes>
</html>
