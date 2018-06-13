<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>product-left</title>
<link href="/css1/admin.css" rel="stylesheet" type="text/css" />
<link href="/css1/theme.css" rel="stylesheet" type="text/css" />
<link href="/css1/jquery.validate.css" rel="stylesheet" type="text/css" />
<link href="/css1/jquery.treeview.css" rel="stylesheet" type="text/css" />
<link href="/css1/jquery.ui.css" rel="stylesheet" type="text/css" />

<script src="/js1/jquery.js" type="text/javascript"></script>
<script src="/js1/jquery.ext.js" type="text/javascript"></script>
<script src="/js1/jquery.form.js" type="text/javascript"></script>
<script src="/js1/itcast.js" type="text/javascript"></script>
<script src="/js1/admin.js" type="text/javascript"></script>
</head>
<body class="lbody">
	<div class="left">
		<%@ include file="../date.jsp"%>
		<ul class="w-lful">
			<li><a target="rightFrame" href="/order/AllList.do">全部订单</a></li>
			<li><a target="rightFrame" href="/order/list.do?style=1">已支付</a></li>
			<li><a target="rightFrame" href="/order/notShipped.do?opstyle=0&&style=1">未发货</a></li>
			<li><a target="rightFrame" href="/order/completed.do?opstyle=2">已完成</a></li>
			<li><a target="rightFrame" href="/order/nonList.do?style=0">待付款</a></li>
			<!-- <li><a target="rightFrame" href="order_right.jsp">仓库配货</a></li>
			<li><a target="rightFrame" href="order_right.jsp">商品出库</a></li>
			<li><a target="rightFrame" href="order_right.jsp">等待收货</a></li>
			<li><a target="rightFrame" href="order_right.jsp">已完成</a></li>
			<li><a target="rightFrame" href="order_right.jsp">待退货</a></li>
			<li><a target="rightFrame" href="order_right.jsp">待退款</a></li>
			<li><a target="rightFrame" href="order_right.jsp">已退货</a></li>
			<li><a target="rightFrame" href="order_right.jsp">已退款</a></li> -->
		</ul>
	</div>
</body>
</html>