<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../head.jsp"%>
<%@ taglib uri="/tags" prefix="date"%>
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
<meta name="renderer" content="webkit" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
<script src="plugin/layui-v1.0.7/layui.js" type="text/javascript"></script>

<link rel="stylesheet" href="plugin/layui-v1.0.7/css/layui.css" />
<link rel="stylesheet" href="css/date.css" />
<title>生医容美微信活动列表</title>
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
	function optDelete(title,pageNo) {
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
				"<%=basePath%>/activity/deletes.do?title=" + title + "&pageNo="
						+ pageNo);
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
</head>
<body>
	<div class="box-positon">
		<div class="rpos">当前位置: 微信活动 - 列表</div>
		<form class="ropt">
			<input class="add" type="button" value="添加"
				onclick="window.location.href='activity/toAdd.do'" />
		</form>
		<div class="clear"></div>
	</div>
	<div class="body-box">
		<form action="<%=basePath%>/activity/list.do" method="post"
			style="padding-top: 5px;">
			标题: <input type="text" name="title" value="${title}" />
			&nbsp&nbsp&nbsp 时间区间：<input type="text" id="dp11"
				class="ui-time-text" name="time" value="${time}" kssj="" jssj="" />
			<!--   <select
				name="brandId">
				<option value="">请选择品牌</option>
				<c:forEach items="${brands}" var="brand">
					<option value="${brand.id}"
						<c:if test="${brandId == brand.id}">selected="selected"</c:if>>${brand.brandname}
					</option>
				</c:forEach>
			</select> -->
			<input type="submit" class="query" value="查询" />
		</form>
		<form id="jvForm">
			<table cellspacing="1" cellpadding="0" width="100%" border="0"
				class="pn-ltable">
				<thead class="pn-lthead">
					<tr>
						<th width="20"><input type="checkbox"
							onclick="Pn.checkbox('ids',this.checked)" /></th>
						<th>编号</th>
						<th>描述</th>
						<th>图片</th>
						<th>标题</th>
						<th>活动类型</th>
						<th>开始时间</th>
						<th>结束时间</th>
						<th>是否开始</th>
						<th>点赞数门槛</th>
						<th>是否用券</th>
						<th>添加时间</th>
						<th>活动链接</th>
						<th width="12%">操作选项</th>
					</tr>
				</thead>
				<tbody class="pn-ltbody">
					<c:forEach items="${pagination.list}" var="activity">
						<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'"
							onmouseout="this.bgColor='#ffffff'">
							<td align="center"><input type="checkbox" name="ids"
								value="${activity.id}" /></td>
							<td align="center">${activity.id}</td>
							<td align="center">${activity.name}</td>
							<td align="center"><img width="50" height="50"
								src="${activity.pic}"
								onerror="javascript:this.src='img/error.jpg'" /></td>
							<td align="center">${activity.title}</td>
							<td align="center">${activity.typeName}</td>
							<td align="center"><date:date
									value="${activity.activityStart}" /></td>
							<td align="center"><date:date
									value="${activity.activityEnd}" /></td>
							<td align="center"><c:if test="${activity.isShow}">已开启</c:if>
								<c:if test="${!activity.isShow}">未开启</c:if></td>
							<td align="center">${activity.num}</td>
							<td align="center"><c:if test="${activity.ticket}">需要用券</c:if>
								<c:if test="${!activity.ticket}">无需用券</c:if></td>
							<td align="center"><date:date value="${activity.addtime}" /></td>
							<td id="copyUrl" align="center">http://www.mryisheng.com/?c=wxweb&a=new_activity&id=${activity.id}&type=${activity.acType}</td>
							<td align="center">
								<!--<a href="#" class="pn-opt">查看</a> |   --> <a
								data-clipboard-action="copy" data-clipboard-target="#copyUrl"
								href="#" class="pn-opt">提取链接</a> | <a
								href="activity/modify?id=${activity.id}" class="pn-opt">编辑</a> |
								<a
								href="activity/delete?id=${activity.id}&pageNo=${pagination.pageNo}&title=${title}"
								onclick="if(!confirm('您确定删除吗？')) {return false;}" class="pn-opt">删除</a>
								| <a href="activity/toResult?id=${activity.id}" class="pn-opt">查看活动结果</a>
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
					onclick="optDelete('${title}','${pagination.pageNo}');" />
			</div>
		</form>
	</div>
</body>
</html>