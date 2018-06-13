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
		$("#jvForm").attr("action", "/product/isShow.do");
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
	function optDelete(des,pageNo) {
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
				"<%=basePath%>/activity/delTypes?des=" + des +  "&pageNo=" + pageNo );
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
</script>
</head>
<body>
	<div class="box-positon">
		<div class="rpos">当前位置: 微信活动 - 列表</div>
		<form class="ropt">
			<input class="add" type="button" value="添加"
				onclick="window.location.href='<%=basePath%>/activity/toType'" />
		</form>
		<div class="clear"></div>
	</div>
	<div class="body-box">
		<form action="<%=basePath%>/activity/toTypeList" method="post"
			style="padding-top: 5px;">
			类型名称: <input type="text" name="des" value="${des}" />
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
						<th>类型名称</th>
						<th>添加时间</th>
						<th width="12%">操作选项</th>
					</tr>
				</thead>
				<tbody class="pn-ltbody">
					<c:forEach items="${pagination.list}" var="type">
						<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'"
							onmouseout="this.bgColor='#ffffff'">
							<td align="center"><input type="checkbox" name="ids"
								value="${type.id}" /></td>
							<td align="center">${type.id}</td>
							<td align="center">${type.des}</td>
							<td align="center"><date:date
									value="${type.addtime}" /></td>
							<td align="center">
								<!--<a href="#" class="pn-opt">查看</a> |   --> <a
								href="<%=basePath%>/activity/toType?id=${type.id}" class="pn-opt">编辑</a> | <a
								href="<%=basePath%>/activity/delType?id=${type.id}&pageNo=${pagination.pageNo}&des=${des}"
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
					onclick="optDelete('${des}','${pagination.pageNo}');" />
			</div>
		</form>
	</div>
</body>
</html>