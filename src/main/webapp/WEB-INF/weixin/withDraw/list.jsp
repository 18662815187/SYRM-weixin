<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../head.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>生医容美申请提现列表</title>
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
		<div class="rpos">当前位置: 提现记录 - 列表</div>
		<div class="clear"></div>
	</div>
	<div class="body-box">
	<!--<form action="/order/list.do?style=1" method="post"
			style="padding-top: 5px;">
			手机号: <input type="text" name="tel" value="${tel}" /> 
			 <input type="submit" class="query" value="查询" />
		</form>  -->
		
		<input type="hidden" name="pageNo" value="${pageNo}" />
		<form method="post" id="tableForm">
			<input type="hidden" value="" name="pageNo" /> <input type="hidden"
				value="" name="queryName" />
			<table cellspacing="1" cellpadding="0" border="0" width="100%"
				class="pn-ltable">
				<thead class="pn-lthead">
					<tr>
						<th width="20">ID</th>
						<th>申请事件</th>
						<th>银行卡号</th>
						<th>银行</th>
						<th>开户行信息</th>
						<th>开户人姓名</th>
						<th>预留手机号</th>
						<th>提现金额</th>
						<th>申请时间</th>
						<th>审核状态</th>
					</tr>
				</thead>
				<tbody class="pn-ltbody">
					<c:forEach items="${pagination.list}" var="withDraw">
						<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'"
							onmouseout="this.bgColor='#ffffff'">
							<td align="center">${withDraw.id}</td>
							<td align="center">${withDraw.msg}</td>
							<td align="center">${withDraw.banknum}</td>
							<td align="center">${withDraw.bankname}</td>
							<td align="center">${withDraw.bankdeposit}</td>
							<td align="center">${withDraw.name}</td>
							<td align="center">${withDraw.tel}</td>
							<td align="center">${withDraw.money}</td>
							<td align="center"><fmt:formatDate value="${withDraw.addtime}" pattern="yyyy-MM-dd HH:MM:ss" /> </td>
							<td align="center">
							<c:if test="${withDraw.isShow==0}">等待审核</c:if> 
							<c:if test="${withDraw.isShow==1}">审核通过,请注意银行卡余额变化!</c:if> 
							<c:if test="${withDraw.isShow==2}">审核不通过，请联系客服，电话：0571-83731253</c:if> 
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="page pb15">
				<span class="r inb_a page_b"> 
				<span style="font-weight:bold;font-size:15px;">数据总数  ${pagination.totalCount}  条,单页最大显示  ${pagination.pageSize}  条. </span> &nbsp&nbsp&nbsp
				<c:forEach items="${pagination.pageView}" var="page">
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