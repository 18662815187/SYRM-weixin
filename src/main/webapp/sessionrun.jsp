<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>      
  <SCRIPT language="JavaScript"> 
    alert("您还未登录，请先登录。"); 
    setTimeout(function () { 
      window.top.location.href="<%=basePath%>/login.jsp"; 
    },2000); 
    
    function jumpToIndex(){
    	window.top.location.href="<%=basePath%>/login.jsp";
    }
  </script> 
  <div align="center">  
            <h2 style=color:red>请使用您的账号登陆系统</h2>  
            <h2 style=color:red><span id=jump>2</span> 秒钟后页面将自动返回登录页面...</h2>  
            <h2><a href="javascript:void(0);" onclick="jumpToIndex()">点击此处立即跳转至首页</a></h2>  
        </div> 
  </body>

</html>