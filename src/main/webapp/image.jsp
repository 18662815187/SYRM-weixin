<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<html>
<head>
<base href="<%=basePath%>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>获取图片验证码</title>
<script type="text/javascript"
	src="/js/jquery.form.js"></script>
</head>
<body onload="flushValidateCode()">

	<form id="aaa" action="/login.aspx" method='post'>
		<input type="hidden" id="userId" name="userId" value="">
		<div class="form-group">
			<div class="email controls">
				<input type="text" name='tel' id="loginName" placeholder="用户名"
					value="" class='form-control' />
			</div>
		</div>
		<div class="form-group">
			<div class="pw controls">
				<input type="password" autocomplete="off" id="pwd" name="password"
					placeholder="密码" class='form-control' />
			</div>
		</div>

		<div class="form-group">
			<div class="email controls">
				<input id="validateCode" onblur="checkImg(this.value)"
					name="verifyCode" type="text" class="form-control"
					placeholder="输入验证码" />
			</div>
			<span class="y_yzimg"><img id="codeValidateImg"
				onClick="javascript:flushValidateCode();" /></span>
			<p class="y_change">
				<a href="javascript:flushValidateCode();">换一张</a>
			</p>
		</div>

		<div class="form-group">
			<span class="text-danger"></span>
		</div>

		<div class="submit">
			<div class="remember">

				<input type="checkbox" name="remember" value="1" class='icheck-me'
					data-skin="square" data-color="blue" id="remember"> <label
					for="remember">记住我</label>
			</div>
			<input type="button" value="登录" onclick="checkCell()"
				class='btn btn-primary'>
		</div>
		<div>
		<form id="jvFrom">
		
		
		 <img width="100" height="100"
							id="allUrl" src="${product.pic}" /> <!-- 下方这个隐藏域用于form提交图片数据时使用，value就是用作提交使用的 -->
							<input type="hidden" name="pic" id="imgUrl"
							value="${product.pic}" /> <!-- 此处的name值需要和控制器传入对象的小名一致，MultipartFile pic -->
							<input type="file" name="pic2" onchange="uploadPic1()" />
						
					<input type="file" onchange="uploadPic()" name="pics" multiple="multiple" />
		
		</form>
		
		</div>
	</form>

	<script type="text/javascript">
		//$(document).reday(function() {
		//	flushValidateCode();//进入页面就刷新生成验证码
		//});

		/* 刷新生成验证码 */
		function flushValidateCode() {
			var validateImgObject = document.getElementById("codeValidateImg");
			validateImgObject.src = "<%=basePath%>/getSysManageLoginCode?time="
					+ new Date();
		}
		function checkCell() {
			var tel = document.getElementById("loginName").value;
			//alert(tel);
			var code = document.getElementById("validateCode").value;
			var pass = document.getElementById("pwd").value;

			if (null == code || "" == code) {
				alert("验证码不能为空！");
				return false;
			}
			if (null == tel || "" == tel) {
				alert("用户名不能为空！");
				return false;
			}
			if (null == pass || "" == pass) {
				alert("密码不能为空！");
				return false;
			}
			document.getElementById("aaa").submit();

		}
		//上传图片
		function uploadPic1() {
			//jquery.form.js
			var options = {
				url : "/upload/uploadFck.do",
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
	</script>
</body>
</html>