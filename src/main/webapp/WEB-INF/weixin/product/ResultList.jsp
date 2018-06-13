<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../head.jsp"%>
<%@ taglib uri="/tags" prefix="date"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>生医容美微信活动结果列表</title>
<script type="text/javascript">
	//上架
	function isShow() {
		//请至少选择一个
		var size = $("input[name='ids']:checked").size();
		if (size == 0) {
			alert("请至少选择一个");
			return;
		}
		//你确定吗
		if (!confirm("你确定上架吗")) {
			return;
		}
		//提交 Form表单
		$("#jvForm").attr("action", "<%=basePath%>/product/isShow.do");
		$("#jvForm").attr("method", "post");
		$("#jvForm").submit();

	}
	//下架
	function unShow() {
		//请至少选择一个
		var size = $("input[name='ids']:checked").size();
		if (size == 0) {
			alert("请至少选择一个");
			return;
		}
		//你确定吗
		if (!confirm("你确定下架吗")) {
			return;
		}
		//提交 Form表单
		$("#jvForm").attr("action", "unshow.do");
		$("#jvForm").attr("method", "post");
		$("#jvForm").submit();
	}
	function optDelete(tel, pageNo, id) {
		//请至少选择一个,只取被选中的，使用checked区分,判断长度是否为0，如为0提示至少选一个
		//alert(1);
		var size = $("input[name=ids]:checked").size();
		if (size == 0) {
			alert("请至少选择一个");
			return;
		}
		//你确定删除吗？
		if (!confirm("你确定删除吗?")) {
			return;
		}
		$("#jvForm").attr(
				"action",
				"<%=basePath%>/activity/dels?tel=" + tel + "&pageNo=" + pageNo + "&ac_id="
						+ id);
		$("#jvForm").attr("method", "post").submit();
	}
</script>
<script type="text/javascript">
	$(document).ready(function() {
		var clipboard3 = new Clipboard('.pn-opt');
		clipboard3.on('success', function(e) {
			console.log(e);
			alert("复制成功,请使用微信打开该链接!")
		});
		clipboard3.on('error', function(e) {
			console.log(e);
			alert("复制失败,请手动复制")
		});
	})
	function export_report() {
		//var phId = ${pid};
		window.location.href = "<%=basePath%>/activity/export?ac_id=" + $
		{
			id
		}
		;
	}
</script>
</head>
<body>
	<div class="box-positon">
		<div class="rpos">当前位置: 微信活动 结果- 列表</div>
		<form class="ropt">
			<input class="add" type="button" value="添加"
				onclick="window.location.href='<%=basePath%>/activity/toAdd.do'" />
		</form>
		<div class="clear"></div>
	</div>
	<div class="body-box">
		<form action="<%=basePath%>/activity/toResult" method="post"
			style="padding-top: 5px;">
			<span Style="color: red">*点击可导出活动结果记录</span>&nbsp&nbsp&nbsp <input
				type="button" class="upload-file" onclick="export_report()"
				value="导出报表" />
			<!--   <select
				name="brandId">
				<option value="">请选择品牌</option>
				<c:forEach items="${brands}" var="brand">
					<option value="${brand.id}"
						<c:if test="${brandId == brand.id}">selected="selected"</c:if>>${brand.brandname}
					</option>
				</c:forEach>
			</select> -->
			<input type="hidden" id="id" value="${id}" />
		</form>
		<form id="jvForm">
			<table cellspacing="1" cellpadding="0" width="100%" border="0"
				class="pn-ltable">
				<thead class="pn-lthead">
					<tr>
						<th width="20"><input type="checkbox"
							onclick="Pn.checkbox('ids',this.checked)" /></th>
						<th>编号</th>
						<th>昵称</th>
						<th>头像</th>
						<th>参加人手机号</th>
						<th>openid</th>
						<th>是否中奖</th>
						<th>点赞数量</th>
						<th>收货地址</th>
						<th>收货人名字</th>
						<th>收货人手机号</th>
						<th>用户识别码</th>
						<th>券号</th>
						<th>是否已参加活动</th>
						<th>个人分享链接</th>
						<th width="12%">操作选项</th>
					</tr>
				</thead>
				<tbody class="pn-ltbody">
					<c:forEach items="${pagination.list}" var="acUser">
						<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'"
							onmouseout="this.bgColor='#ffffff'">
							<td align="center"><input type="checkbox" name="ids"
								value="${acUser.id}" /></td>
							<td align="center">${acUser.id}</td>
							<td align="center">${acUser.nickname}</td>
							<td align="center"><img width="50" height="50"
								src="${acUser.headimgurl}"
								onerror="javascript:this.src='/img/error.jpg'" /></td>
							<td align="center">${acUser.userPhone}</td>
							<td align="center">${acUser.openid}</td>
							<td align="center"><c:if test="${acUser.flag==1}">已中奖</c:if>
								<c:if test="${acUser.flag==0}">未中奖</c:if></td>
							<td align="center">${acUser.num}</td>
							<td align="center">${acUser.address}</td>
							<td align="center">${acUser.name}</td>
							<td align="center">${acUser.phone}</td>
							<td align="center">${acUser.userkey}</td>
							<td align="center">${acUser.ticket}</td>
							<td align="center"><c:if test="${acUser.isjoin==1}">已参加</c:if>
								<c:if test="${acUser.isjoin==0}">未参加</c:if></td>
							<td id="copyUrl" align="center">http://www.mryisheng.com/?c=wxweb&a=new_activity&userkey=${acUser.userkey}</td>
							<td align="center">
								<!--<a href="#" class="pn-opt">查看</a> |   --> <a
								data-clipboard-action="copy" data-clipboard-target="#copyUrl"
								href="#" class="pn-opt">提取链接</a> | <a
								href="<%=basePath%>/activity/delResult?id=${acUser.id}&pageNo=${pagination.pageNo}&tel=${tel}&ac_id=${id}"
								onclick="if(!confirm('您确定删除吗？')) {return false;}" class="pn-opt">删除</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="page pb15">
				<span class="r inb_a page_b"> <span
					style="font-weight: bold; font-size: 15px;">数据总数 <c:out
							value="${pagination.totalCount}" default="0" /> 条,单页最大显示 <c:out
							value="${pagination.pageSize}" default="0" /> 条.
				</span> &nbsp&nbsp&nbsp <c:forEach items="${pagination.pageView}"
						var="page">
				${page}
				</c:forEach>
				</span>
			</div>
			<div style="margin-top: 15px;">
				<input class="del-button" type="button" value="删除"
					onclick="optDelete('${tel}','${pagination.pageNo}','${id}');" />
			</div>
		</form>
	</div>
</body>
</html>