<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>生医容美未审核产品列表</title>
<script type="text/javascript">
	//全选,此js的意思是，找到name值为传进来的name的input标签，设置选中属性true/flase，这个选中属性在
	//下方点击事件中使用this.checked来获取true、flase
	function checkBox(name, checked) {
		$("input[name=" + name + "]").attr("checked", checked);
		//$("input[name=" + name +"]").prop("checked",checked);
	}
	//批量删除,需要配合form标签使用，在需要提交的部分用form包裹，设置id=“jvForm”,在js控制此id的form提交
	//快捷键Ctrl+K直接跳转到该方法
	//传入搜索条件和分页页码,这样做是为了删除之后显示的依然是通过条件搜索过的页面
	function optDelete(uid,name,pageNo) {
		//请至少选择一个,只取被选中的，使用checked区分,判断长度是否为0，如为0提示至少选一个
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
				"/Pvc/deletes.do?name=" + name + "&pageNo=" + pageNo + "&uid=" + uid);
		$("#jvForm").attr("method", "post").submit();
	}
</script>
</head>
<body>
	<div class="box-positon">
		<div class="rpos">当前位置: 未审核产品 - 列表</div>
		<form class="ropt">
			<input class="add" type="button" value="添加"
				onclick="javascript:window.location.href='/Pvc/toAdd.do'" />
		</form>
		<div class="clear"></div>
	</div>
	<div class="body-box">
		<form action="/Pvc/list.do" method="post" style="padding-top: 5px;">
			产品名称: <input type="text" name="name" value="${name}" /> 
			<input type="submit" class="query" value="查询" />
		</form>
		<form id="jvForm">
			<table cellspacing="1" cellpadding="0" border="0" width="100%"
				class="pn-ltable">
				<thead class="pn-lthead">
					<tr>
						<th width="20"><input type="checkbox"
							onclick="checkBox('ids',this.checked)" /></th>
						<th>产品ID</th>
						<th>品牌</th>
						<th>产品图片</th>
						<th>规格</th>
						<th>生产厂家</th>
						<th>库存</th>
						<th>售价</th>
						<th>操作选项</th>
					</tr>
				</thead>
				<tbody class="pn-ltbody">
					<c:forEach items="${pagination.list}" var="product">
						<tr bgcolor="#ffffff" onmouseout="this.bgColor='#ffffff'"
							onmouseover="this.bgColor='#eeeeee'">
							<td align="center"><input type="checkbox"
								value="${product.id}" name="ids" /></td>
							<td align="center">${product.id}</td>
							<td align="center">${product.brandName}</td>
							<td align="center"><img width="40" height="40"
								src="${product.pic}" onerror="javascript:this.src='/img/error.jpg'"/></td>
								<td align="center">${product.spec}</td>
							<td align="center">${product.factoryName}</td>
							<td align="center">${product.stock}</td>
							<td align="center">${product.price}</td>
							<td align="center"><a class="pn-opt"
								href="/Pvc/toEdit.do?id=${product.id}&name=${name}&isDisplay=${isDisplay}&pageNo=${pagination.pageNo}">修改</a> | <a
								class="pn-opt" onclick="if(!confirm('您确定删除吗？')) {return false;}"
								href="delete.do?id=${product.id}&uid=${pid}&name=${name}&pageNo=${pagination.pageNo}">删除</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
		<div class="page pb15">
		
			<span class="r inb_a page_b">
			<span style="font-weight:bold;font-size:15px;">数据总数  ${pagination.totalCount}  条,单页最大显示  ${pagination.pageSize}  条. </span> &nbsp&nbsp&nbsp
			 <c:forEach
					items="${pagination.pageView}" var="page">
					${page}
			</c:forEach>
			</span>
		</div>
		<div style="margin-top: 15px;">
		<input class="del-button" type="button" value="删除"
				onclick="optDelete('${pid}','${name}','${pagination.pageNo}');" />
		</div>
	</div>
</body>
</html>