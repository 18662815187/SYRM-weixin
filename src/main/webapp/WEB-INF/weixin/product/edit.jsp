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
<meta name="renderer" content="webkit" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />

<title>syrmWeiXin-add</title>
<style type="">
.h2_ch a:hover, .h2_ch a.here {
	color: #FFF;
	font-weight: bold;
	background-position: 0px -32px;
}

.h2_ch a {
	float: left;
	height: 32px;
	margin-right: -1px;
	padding: 0px 16px;
	line-height: 32px;
	font-size: 14px;
	font-weight: normal;
	border: 1px solid #C5C5C5;
	background: url('/images/admin/bg_ch.gif') repeat-x scroll 0% 0%
		transparent;
}

a {
	color: #06C;
	text-decoration: none;
}
</style>
<script type="text/javascript">
	$(function() {
		var tObj;
		//取id=tabs的a标签结果集
		$("#tabs a").each(function() {
			if ($(this).attr("class").indexOf("here") == 0) {
				tObj = $(this)
			}
			$(this).click(function() {
				var c = $(this).attr("class");
				if (c.indexOf("here") == 0) {
					return;
				}
				var ref = $(this).attr("ref");
				var ref_t = tObj.attr("ref");
				tObj.attr("class", "nor");
				$(this).attr("class", "here");
				$(ref_t).hide();
				$(ref).show();
				tObj = $(this);
				if (ref == '#tab_3') {
					// 编辑器参数
					var kingEditorParams = {
						//指定上传文件参数名称
						filePostName : "file",
						//指定上传文件请求的url。
						uploadJson : '/uploadKE',
						//上传类型，分别为image、flash、media、file
						dir : "image",
						width : '1000px',
						height : '400px',
						//失去焦点自动同步设置到textarea
						afterBlur: function(){this.sync();}
					};
					KindEditor.create('#productdesc', kingEditorParams);
					KindEditor.sync();
					//this.sync();
				}
			});
		});
	});
	//上传图片
	function uploadPic1() {
		//jquery.form.js
		//uploadFck.do
		//alert(1);
		var options = {
			url : "<%=basePath%>/uploadFck.do",
			dataType : "json",
			type : "post",
			success : function(data) {
				//alert(data.url);
				//返回的数据通过下方控件的id给他们设置值,src需要通过attr属性去设置，val可以直接设置
				//原数据存在图片时，重新上传替换URL需对此两个地方进行回显设置
				$("#allUrl").attr("src", data.url);
				$("#imgUrl").val(data.url);
			}
		}
		//ajax模拟jvForm这个form的提交
		$("#jvForm").ajaxSubmit(options);
	}
	//批量上传图片
	function uploadPic() {

		//上传图片 异步的  	Jquery.form.js
		var options = {
			url : "<%=basePath%>/uploadXX",
			type : "post",
			dataType : "json",
			//datas就是后台传来的urls
			success : function(data) {
				//alert(data);
				//alert(data[0]);
				//多图片回显,增加tr行和td列,原本此行是不存在的，在回显时给加上去，然后在第二个列（td）中加上两个input标签，第一个是遍历回显图片
				//第二个input设置成hidden用于提交数据
				var html = '<tr>'
						+ '<td width="20%" class="pn-flabel pn-flabel-h"></td>'
						+ '<td width="80%" class="pn-fcontent">';
				for (var i = 0; i < data.length; i++) {
					html += '<img width="100" height="100" src="' + data[i] + '" />'
							+ '<input type="hidden" name="image" value="' + data[i] + '"/>'
				}
				html += '<a href="javascript:;" class="pn-opt" onclick="jQuery(this).parents(\'tr\').remove()">删除</a>'
						+ '</td>' + '</tr>';
				//回显
				$("#tab_2").append(html);

			}
		}
		$("#jvForm").ajaxSubmit(options);
	}
	
	function remove() {
		$("#tt").remove();
	}
	function submitPd() {
		$.ajax({
			type : 'post',
			url : '<%=basePath%>/activity/saveOrUpdate',
			data : $('#jvForm').serialize(),
			cache : false,
			dataType : 'json',
			success : function(data) {
				//alert(data);
				if (data.status == 1) {
					//alert("修改成功，请重新登录！");
					window.location.href = "<%=basePath%>/activity/list.do";
				} else {
					var html = "<span style='color:red'>" + data.msg
							+ "</span>";

					$("#error").html(html);
				}
			},
			error : function() {
				// view("异常！");    
				alert("网络异常，请联系管理员");
				var html = "<span style='color:red'>"
						+ "网络异常，请联系管理员!客服电话：0571-83731253" + "</span>";

				$("#error").html(html);
			}
		});
	}
	//选择点赞是出现点赞门槛输入框
	function seclectId() {
		var a = $('#secId option:selected').val();//选中的值	
		var tr1 = document.getElementById("dz");
		if (a == 1) {
			tr1.style.display = '';
		}else{
			tr1.style.display = 'none';
		}
	}
</script>
</head>
<body>
	<div class="box-positon">
		<div class="rpos">当前位置: 商品管理 - 添加</div>
		<form class="ropt">
			<input type="submit" onclick="javascript:history.back(-1);"
				value="返回列表" class="return-button" />
		</form>
		<div class="clear"></div>
	</div>
	<h2 class="h2_ch">
		<span id="tabs"> <!-- class的样式中here是选中后未蓝色底，未被选中的是nor白底 --> <a
			href="javascript:void(0);" ref="#tab_1" title="基本信息" class="here">基本信息</a>
			<a href="javascript:void(0);" ref="#tab_2" title="商品图片" class="nor">活动图集</a>

			<a href="javascript:void(0);" ref="#tab_3" title="商品描述" class="nor">活动详细信息</a>
		</span>
	</h2>
	<div class="body-box" style="float: right">
		<form id="jvForm" action="<%=basePath%>/activity/saveOrUpdate" method="post"
			enctype="multipart/form-data">
			<table cellspacing="1" cellpadding="2" width="100%" border="0"
				class="pn-ftable">
				<tbody id="tab_1">
					<input type="hidden" name="id" value="${activity.id}"/>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 活动名称(描述):</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="name" maxlength="100" size="100"
							value="${activity.name}"/></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span>活动标题:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							 class="required" name="title" maxlength="100"
							size="100" value="${activity.title}" /></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 活动类型:</td>
						<td width="80%" class="pn-fcontent"><select id="secId"
							name="acType" onchange="seclectId()">
								<option value="">请选择</option>
								<c:forEach items="${types}" var="type">
									<option <c:if test="${activity.acType==type.id}">selected="selected"</c:if> value="${type.id}">${type.des}</option>
								</c:forEach>
						</select></td>
					</tr>
					
					<tr id="dz" style="display: none">
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span>点赞数量门槛:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
						 class="required" name="num" maxlength="100" size="5"
							value="${activity.num}" /><span style="color: red">*
								请输入数字</span></td>
					</tr>
			

					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 上传活动封面图片(90x150尺寸):</td>
						<td width="80%" class="pn-fcontent">注:该尺寸图片必须为90x150。</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"></td>
						<td width="80%" class="pn-fcontent">
							<!-- allUrl全路径是用于回显的，如此前进入编辑页面时有图片则此路径在重新上传图片后
						会被替换，此标签内的数据无法进行提交，需要配合隐藏域使用 --> <img width="100" height="100"
							id="allUrl" src="${activity.pic}" /> <!-- 下方这个隐藏域用于form提交图片数据时使用，value就是用作提交使用的 -->
							<input type="hidden" name="pic" id="imgUrl"
							value="${activity.pic}" /> <!-- 此处的name值需要和控制器传入对象的小名一致，MultipartFile pic -->
							<input type="file" name="pic2" onchange="uploadPic1()" />
						</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span>活动时间：</td>
						<td width="80%" class="pn-fcontent"><input id="d5221"
							class="Wdate" type="text" name="time1"
							onclick="var d5222=$dp.$('d5222');WdatePicker({onpicked:function(){d5222.click();},maxDate:'#F{$dp.$D(\'d5222\')}'})" value="<date:date
value='${activity.activityStart}' />"/>
							至 <input id="d5222" class="Wdate" type="text" name="time2"
							onclick="WdatePicker({minDate:'#F{$dp.$D(\'d5221\')}'})" value="<date:date
value='${activity.activityEnd}' />"/></td>
					</tr>

					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 是否用券:</td>
						<td width="80%" class="pn-fcontent"><input type="radio"
							class="required" name="ticket" value="0" checked="checked" <c:if test="${!activity.ticket}">checked="checked"</c:if>/>否&nbsp&nbsp<input type="radio"
							class="required" name="ticket" value="1" <c:if test="${activity.ticket}">checked="checked"</c:if>/>是</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 是否开始:</td>
						<td width="80%" class="pn-fcontent"><input type="radio"
							class="required" name="isShow" value="0" checked="checked" <c:if test="${!activity.isShow}">checked="checked"</c:if>/>否&nbsp&nbsp<input type="radio"
							class="required" name="isShow" value="1" <c:if test="${activity.isShow}">checked="checked"</c:if>/>是</td>
					</tr>
				</tbody>
				<tbody id="tab_2" style="display: none">
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 上传商品图片(90x150尺寸):</td>
						<td width="80%" class="pn-fcontent">注:该尺寸图片必须为90x150。</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"></td>
						<td width="80%" class="pn-fcontent"><input type="file"
							onchange="uploadPic()" name="file" multiple="multiple" /></td>
					</tr>
					<tr id="tt">
						<td width="20%" class="pn-flabel pn-flabel-h"></td>
						<td width="80%" class="pn-fcontent"><c:forEach
								items="${activity.pics}" var="pic">
								<img width="100" height="100" src="${pic}" />
							</c:forEach> <c:if test="${!empty activity.pics}">
								<a href="javascript:;" class="pn-opt" onclick="remove()">删除</a>
							</c:if></td>
					</tr>
				</tbody>
				<tbody id="tab_3" style="display: none">
					<tr>
						<td><textarea rows="20" cols="180" id="productdesc" name="content">${activity.content}</textarea></td>
					</tr>
				</tbody>
				<tbody>
					<tr>
						<td class="pn-fbutton" colspan="2">
							<div id="error"></div>
						</td>
					</tr>
					<tr>
						<td class="pn-fbutton" colspan="2"><input type="button"
							class="submit" onclick="submitPd()" value="提交" /> &nbsp; <input
							type="reset" class="reset" value="重置" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>