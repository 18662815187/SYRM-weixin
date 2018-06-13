<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../head.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="renderer" content="webkit" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script src="../../../plugin/layui-v1.0.7/layui.js"
	type="text/javascript"></script>
<script src="/js/main.js" type="text/javascript"></script>
<link rel="stylesheet" href="../../../plugin/layui-v1.0.7/css/layui.css" />
<link rel="stylesheet" href="../../../css/date.css" />
<title>生医容美报表</title>
<script type="text/javascript">
	//发货
	function delivery(Id, pid) {
		//你确定删除吗？
		if (!confirm("你确定发货吗?")) {
			return;
		}
		var url = "/order/deliver.do";
		var params = {
			"id" : Id,
			"pid" : pid
		};
		//var params1 = '{"marketPrice" : m,"price" : p,"stock" : s,"upperLimit" : l,"deliveFee" : f,"id" : skuId}';
		//异步保存
		$.post(url, params, function(data) {
			//alert(data.message);
			if (data.message == 1) {
				alert("发货成功!");
				window.location.href = "/order/completed.do?opstyle=2";
			}
			if (data.message == 0) {
				alert("网络请求失败,请联系管理员!");
				return;
			}
			if (data.message == 2) {
				alert("没有找到该产品的库存信息!");
				return;
			}
			if (data.message == 3) {
				alert("订单信息获取失败!");
				return;
			}
			if (data.message == 4) {
				alert("库存不足，请检查库存!");
				return;
			}
			if (data.message == 5) {
				alert("库存更新失败，请联系管理员!");
				return;
			}
			if (data.message == 6) {
				alert("订单状态更新失败，请联系管理员!");
				return;
			}
		}, "json")
	}
	layui.use([ 'laydate', 'dateLay' ], function() {
		var layer = layui.layer, laydate = layui.laydate;
		var obj = {
			init : function() {
				this.dp11 = $('#dp11');
				this.dp12 = $('#dp12');
				this.initEvent();
			},
			initEvent : function() {
				this.dp11.dateLay();
				this.dp12.dateLay();

			}
		}
		obj.init();
	});
</script>
<script type="text/javascript">
	function export_report() {
		var time = $("#dp11").val();
		var pro_name = $("#pro_name").val();
		//var phId = ${pid};
		window.location.href = "/export.do?proName=" + pro_name + "&time=" + time;
	}
</script>
</head>
<body>
	<div class="box-positon">
		<div class="rpos">当前位置: 收益报表 - 列表</div>
		<div class="clear"></div>
	</div>
	<div class="body-box">
		<form action="/report/list.do" method="post" style="padding-top: 5px;">
			产品名称: <input id="pro_name" type="text" name="proName"
				value="${proName}" />&nbsp&nbsp&nbsp 时间区间：<input type="text"
				id="dp11" class="ui-time-text" name="time" value="${time}" kssj=""
				jssj="" /> <input type="submit" class="query" value="查询" />&nbsp&nbsp&nbsp<span
				Style="color: red">*根据您的搜索条件导出报表</span>&nbsp&nbsp&nbsp <input
				type="button" class="upload-file" onclick="export_report()"
				value="导出报表" />
		</form>
		<input type="hidden" name="pageNo" value="${pageNo}" />
		<form method="post" id="tableForm" style="">
			<input type="hidden" value="" name="pageNo" /> <input type="hidden"
				value="" name="queryName" />
			<table cellspacing="1" cellpadding="0" border="0" width="100%"
				class="pn-ltable">
				<thead class="pn-lthead">
					<tr
						style="height: 29px; background-image: url(../images/theme/tit-tdbg.png); color: #3566a9;">
						<th width="20">ID</th>
						<th>产品名称</th>
						<th>品牌</th>
						<th>规格</th>
						<th>生产厂家</th>
						<th>单价</th>
						<th>销售数量</th>
						<th>销售总金额</th>
					</tr>
				</thead>
				<tbody class="pn-ltbody">
					<c:forEach items="${pagination.list}" var="product">
						<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'"
							onmouseout="this.bgColor='#ffffff'">
							<td align="center">${product.proId}</td>
							<td align="center">${product.proName}</td>
							<td align="center">${product.brandName}</td>
							<td align="center">${product.spec}</td>
							<td align="center">${product.facName}</td>
							<td align="center">${product.price}</td>
							<td align="center">${product.totalNum}</td>
							<td align="center">${product.totalFee}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="page pb15">
				<span class="r inb_a page_b"> <span
					style="font-weight: bold; font-size: 15px;">数据总数 <c:out
							value="${pagination.totalCount}" default="0" /> 条,单页最大显示 <c:out
							value="${pagination.pageSize}" default="0" /> 条. 当前时间段销售总金额：<c:out
							value="${totalMoney}" default="0.00" />&nbsp元
				</span> &nbsp&nbsp&nbsp <c:forEach items="${pagination.pageView}"
						var="page">
				${page}
				</c:forEach>
				</span>
			</div>
			<div style="margin-top: 15px;">
				<!-- 	<input class="del-button" type="button" value="取消" onclick="optCancel();"/>
	<input class="submit" type="button" value="通过" onclick="optPass();"/> -->
			</div>
		</form>
	</div>
</body>
</html>