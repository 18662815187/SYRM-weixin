<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>babasport-add</title>
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
						filePostName : "uploadFile",
						//指定上传文件请求的url。
						uploadJson : '/upload/uploadFck.do',
						//上传类型，分别为image、flash、media、file
						dir : "image",
					//width : '1000px',
					//height : '400px'
					};
					KindEditor.create('#productdesc', kingEditorParams);
					KindEditor.sync();
				}
			});
		});
	});
	//上传图片
	function uploadPic1() {
		//jquery.form.js
		var options = {
			url : "/uploadFck.do",
			dataType : "json",
			type : "post",
			success : function(data) {
				//alert(1);
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
			url : "/upload/uploadPics.do",
			type : "post",
			dataType : "json",
			//datas就是后台传来的urls
			success : function(data) {
				//多图片回显,增加tr行和td列,原本此行是不存在的，在回显时给加上去，然后在第二个列（td）中加上两个input标签，第一个是遍历回显图片
				//第二个input设置成hidden用于提交数据
				var html = '<tr>'
						+ '<td width="20%" class="pn-flabel pn-flabel-h"></td>'
						+ '<td width="80%" class="pn-fcontent">';
				for (var i = 0; i < data.length; i++) {
					html += '<img width="100" height="100" src="' + data[i] + '" />'
							+ '<input type="hidden" name="imgUrl" value="' + data[i] + '"/>'
				}
				html += '<a href="javascript:;" class="pn-opt" onclick="jQuery(this).parents(\'tr\').remove()">删除</a>'
						+ '</td>' + '</tr>';
				//回显
				$("#tab_2").append(html);

			}
		}
		$("#jvForm").ajaxSubmit(options);
	}
	//商品分类级联
	$(function() {
		//alert("jquery起作用了");
		$("#sele_1")
				.change(
						function() {
							//使#city只保留第一个option子节点  
							$("#sele_2 option:not(:first)").remove();
							var one_id = $(this).val();
							//alert(one_id);
							if (one_id != "") {
								var url = "/type/queryByLevel.do";
								var args = {
									"id" : one_id,
								};
								$
										.getJSON(
												url,
												args,
												function(data) {
													for (var i = 0; i < data.length; i++) {
														var id = data[i].id;
														var optionName = data[i].optionName;
														$("#sele_2")
																.append(
																		"<option value='"
																				+ id
																				+ "'<c:if test='"+${id==optionID}+"'>selected=''"
																				+ selected
																				+ "''</c:if>>"
																				+ optionName
																				+ "</option>");
													}
												});
							}

						});
	});
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
			<a href="javascript:void(0);" ref="#tab_2" title="商品图片" class="nor">商品图集</a>
			<a href="javascript:void(0);" ref="#tab_3" title="商品描述" class="nor">商品描述</a>
		</span>
	</h2>
	<div class="body-box" style="float: right">
		<form id="jvForm" action="saveOrUpdate.do" method="post" enctype="multipart/form-data">
			<table cellspacing="1" cellpadding="2" width="100%" border="0"
				class="pn-ftable">
				<tbody id="tab_1">
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 产品名称:</td>
						<td width="80%" class="pn-fcontent"><input type="text"
							class="required" name="productName" maxlength="100" size="100" /></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 上传产品图片(90x150尺寸):</td>
						<td width="80%" class="pn-fcontent">注:该尺寸图片必须为90x150。</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"></td>
						<td width="80%" class="pn-fcontent">
							<!-- allUrl全路径是用于回显的，如此前进入编辑页面时有图片则此路径在重新上传图片后
						会被替换，此标签内的数据无法进行提交，需要配合隐藏域使用 --> <img width="100" height="100"
							id="allUrl" src="${product.pic}" /> <!-- 下方这个隐藏域用于form提交图片数据时使用，value就是用作提交使用的 -->
							<input type="hidden" name="pic" id="imgUrl"
							value="${product.pic}" /> <!-- 此处的name值需要和控制器传入对象的小名一致，MultipartFile pic -->
							<input type="file" name="pic2" onchange="uploadPic1()" />
						</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span>产品品牌:</td>
						<td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="brandName" maxlength="100" size="20" /></td>
						</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span>产品规格:</td>
						<td width="80%" class="pn-fcontent"><input type="text" name="spec"
							class="required" name="weight" maxlength="100" size="100" /></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 生产厂家:</td>
						<td width="80%" class="pn-fcontent">
						<input type="text" class="required" name="factoryName" maxlength="100" size="50" />
						</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 库存:</td>
						<td width="80%" class="pn-fcontent"><input type="text" class="required" name="stock" maxlength="100" size="5" /></td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span>售价:</td>
						<td width="80%" class="pn-fcontent"><input type="text" class="required" name="price" maxlength="100" size="5" /></td>
					</tr>
				</tbody>
				<tbody id="tab_2" style="display: none">
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"><span
							class="pn-frequired">*</span> 上传商品图片(90x150尺寸):</td>
						<td width="80%" class="pn-fcontent">注:该尺寸图片必须为90x150,可一次选择多张图片上传。</td>
					</tr>
					<tr>
						<td width="20%" class="pn-flabel pn-flabel-h"></td>
						<td width="80%" class="pn-fcontent"><input type="file"
							onchange="uploadPic()" name="images" multiple="multiple" /></td>
					</tr>
				</tbody>
				<tbody id="tab_3" style="display: none">
					<tr>
						<td><textarea rows="20" cols="180" id="productdesc"
								name="content"></textarea></td>
					</tr>
				</tbody>
				<tbody id="tab_4" style="display: none">
					<tr>
						<td><textarea rows="20" cols="180" id="productList"
								name="packageList"></textarea></td>
					</tr>
				</tbody>
				<tbody>
					<tr>
						<td class="pn-fbutton" colspan="2"><input type="submit" class="submit" value="提交" /> &nbsp; <input type="reset"
							class="reset" value="重置" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>