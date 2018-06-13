<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../head.jsp"%>
<%@ taglib uri="/tags" prefix="date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>生医容美药店管理-列表</title>
<script type="text/javascript">
//发货
function delivery(Id,pid) {
	//你确定删除吗？
	if (!confirm("你确定发货吗?")) {
		return;
	}
	var url = "/order/deliver.do";
	var params = {
		"id" : Id,
		"pid":pid
	};
	//var params1 = '{"marketPrice" : m,"price" : p,"stock" : s,"upperLimit" : l,"deliveFee" : f,"id" : skuId}';
	//异步保存
	$.post(url, params, function(data) {
		//alert(data.message);
		if(data.message==1){
			alert("发货成功!");
			window.location.href="/order/completed.do?opstyle=2";
		}
		if(data.message==0){
			alert("网络请求失败,请联系管理员!");
			return;
		}
		if(data.message==2){
			alert("没有找到该产品的库存信息!");
			return;
		}
		if(data.message==3){
			alert("订单信息获取失败!");
			return;
		}
		if(data.message==4){
			alert("库存不足，请检查库存!");
			return;
		}
		if(data.message==5){
			alert("库存更新失败，请联系管理员!");
			return;
		}
		if(data.message==6){
			alert("订单状态更新失败，请联系管理员!");
			return;
		}
	}, "json")
}
</script>
</head>
<body>
	<div class="box-positon">
		<div class="rpos">当前位置: 订单管理 - 查看</div>
		<div class="clear"></div>
	</div>
	<div class="body-box">
		<form id="jvForm" action="v_list.do" method="post">
			<table cellspacing="1" cellpadding="2" border="0" width="100%"
				class="pn-ftable">
				<tbody>
					<tr>
						<td width="12%" class="pn-flabel pn-flabel-h">收货人:</td>
						<td width="38%" colspan="1" class="pn-fcontent">${order.buyername}</td>
						<td width="12%" class="pn-flabel pn-flabel-h">手机:</td>
						<td width="38%" colspan="1" class="pn-fcontent">${order.tel}</td>
					</tr>
					<tr>
						<td width="12%" class="pn-flabel pn-flabel-h">订单号:</td>
						<td width="38%" colspan="1" class="pn-fcontent">${order.orderNum}</td>
						<td width="12%" class="pn-flabel pn-flabel-h">运费:</td>
						<td width="38%" colspan="1" class="pn-fcontent">0.0元</td>
					</tr>
					<tr>
						<td width="12%" class="pn-flabel pn-flabel-h">订单金额:</td>
						<td width="38%" colspan="1" class="pn-fcontent">${order.fee}</td>
						<td width="12%" class="pn-flabel pn-flabel-h">应付金额:</td>
						<td width="38%" colspan="1" class="pn-fcontent">${order.fee}</td>
					</tr>
					<tr>
						<td width="12%" class="pn-flabel pn-flabel-h">订单状态:</td>
						<td width="38%" colspan="1" class="pn-fcontent"><c:if
								test="${order.style==0}">未支付</c:if> <c:if
								test="${order.style==1}">已支付</c:if> <c:if
								test="${order.style==2}">已完成</c:if> <c:if
								test="${order.style==3}">有退款</c:if> <c:if
								test="${order.style==4}">申请退款</c:if> <c:if
								test="${order.style==5}">已退款</c:if></td>
						<td width="12%" class="pn-flabel pn-flabel-h">发货状态:</td>
						<td width="38%" colspan="1" class="pn-fcontent"><c:if
								test="${order.opstyle==0}">未发货</c:if> <c:if
								test="${order.opstyle==1}">已发货</c:if> <c:if
								test="${order.opstyle==2}">订单已完成</c:if></td>
					</tr>
					<tr>
						<td width="12%" class="pn-flabel pn-flabel-h">创建时间:</td>
						<td width="38%" colspan="1" class="pn-fcontent"><date:date
								value="${order.addtime}" /></td>
						<td width="12%" class="pn-flabel pn-flabel-h">备注:</td>
						<td width="38%" colspan="1" class="pn-fcontent">无</td>
					</tr>
				</tbody>
			</table>
			<table cellspacing="1" cellpadding="0" border="0" width="100%"
				class="pn-ltable">
				<thead class="pn-ltbody">
					<tr>
						<th>订单编号</th>
						<th>产品名称</th>
						<th>品牌</th>
						<th>规格</th>
						<th>厂商</th>
						<th>数量</th>
						<th>单价</th>
						<th>订单金额</th>
						<!-- <th>处方医生</th> -->
						<th>下单时间</th>
					</tr>
				</thead>
				<tbody class="pn-ltbody">

					<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'"
						onmouseout="this.bgColor='#ffffff'">
						<td align="center">${order.orderNum}</td>
						<td align="center">${order.productName}</td>
						<td align="center">${order.brandname}</td>
						<td align="center">${order.spec}</td>
						<td align="center">${order.factoryname}</td>
						<td align="center">${order.num}</td>
						<td align="center">${order.price}</td>
						<td align="center">${order.fee}</td>
						<!--<td align="center">${order.docName}</td>  -->
						<td align="center"><date:date value="${order.addtime}" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	<div style="margin-top: 15px;" align="center">
		<input type="button" onclick="javascript:window.print()" value="打印"
			class="submit" />
		<c:if test="${order.style==1&&order.opstyle==0}">
			<input type="button" onclick="delivery(${order.orderId},${order.pid})" value="发货" class="submit" />
		</c:if>

	</div>
</body>
</html>