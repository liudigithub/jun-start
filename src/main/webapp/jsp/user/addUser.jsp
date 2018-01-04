<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link>
<link rel="stylesheet" type="text/css" href="${serverRoot }static/page/user/addUser.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增用户</title>
</head>
<body>
	<jsp:include page="nav.jsp"></jsp:include>
<div class="container theme-showcase top-100" role="main">
    <div class="col-md-6 col-md-offset-3">
        <form action="user/addUesr" method="post"  class="" enctype="multipart/form-data" >

			<div class="form-group has-feedback reg">
				<label >新用户注册</label>
			</div>
			
			<c:if test="${code!='0' }">
			<div class="form-group has-feedback error-message">
				<label >${message }</label>
			</div>
			</c:if>
			
            <div class="form-group has-feedback">
                <label for="username">用户名</label>
                <div class="input-group">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                    <input id="username" name="username" class="form-control" placeholder="请输入用户名" maxlength="20" type="text">
                </div>

                <span style="color:red;display: none;" class="tips"></span>
                <span style="display: none;" class=" glyphicon glyphicon-remove form-control-feedback"></span>
                <span style="display: none;" class="glyphicon glyphicon-ok form-control-feedback"></span>
            </div>

            <div class="form-group has-feedback">
                <label for="password">密码</label>
                <div class="input-group">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                    <input id="password" name="password" class="form-control" placeholder="请输入密码" maxlength="20" type="password">
                </div>

                <span style="color:red;display: none;" class="tips"></span>
                <span style="display: none;" class="glyphicon glyphicon-remove form-control-feedback"></span>
                <span style="display: none;" class="glyphicon glyphicon-ok form-control-feedback"></span>
            </div>

            <div class="form-group has-feedback">
                <label for="passwordConfirm">确认密码</label>
                <div class="input-group">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                    <input id="passwordConfirm" class="form-control" placeholder="请再次输入密码" maxlength="20" type="password">
                </div>
                <span style="color:red;display: none;" class="tips"></span>
                <span style="display: none;" class="glyphicon glyphicon-remove form-control-feedback"></span>
                <span style="display: none;" class="glyphicon glyphicon-ok form-control-feedback"></span>
            </div>

            <div class="form-group has-feedback">
                <label>头像</label>
                <div class="blt-start-img">
					<div id="preview" class="blt-img-preview blt-img-size-lg">
						<img id="imgView1" border="0" src="${serverRoot }static/public/image/uploadImage.jpg" class="blt-border blt-img">
					</div>
					<div class="blt-a-version-upload blt-img-size-lg">本地上传图片
						<input type="file" name="headImage" id="headImage" onchange="PreviewImage(this,'imgView1','divNewPreview')" class="btn blt-btn-choose-file blt-img-size-lg">
					</div>
				</div>
            </div>

            <div class="form-group has-feedback">
                <label>简介</label>
				<div class="adjoined-bottom">
					<div class="grid-container">
						<div class="grid-width-100">
							<textarea id="editor" name="content">
								<h1>Hello world!</h1>
								<p>I'm an instance of <a href="https://ckeditor.com">CKEditor</a>.</p>
							</textarea>
						</div>
					</div>
				</div>
            </div>






<!--             <div class="row"> -->
<!--                 <div class="col-xs-7"> -->
<!--                     <div class="form-group has-feedback"> -->
<!--                         <label for="idcode-btn">验证码</label> -->
<!--                         <div class="input-group"> -->
<!--                             <span class="input-group-addon"><span class="glyphicon glyphicon-qrcode"></span></span> -->
<!--                             <input id="idcode-btn" class="form-control" placeholder="请输入验证码" maxlength="4" type="text"> -->
<!--                         </div> -->
<!--                         <span style="color:red;display: none;" class="tips"></span> -->
<!--                         <span style="display: none;" class="glyphicon glyphicon-remove form-control-feedback"></span> -->
<!--                         <span style="display: none;" class="glyphicon glyphicon-ok form-control-feedback"></span> -->
<!--                     </div> -->
<!--                 </div> -->
<!--                 <div class="col-xs-5" style="padding-top: 30px"> -->
<!--                     <div id="idcode" style="background: transparent;"><div id="ehong-code" class="ehong-idcode-val ehong-idcode-val3" href="#" onblur="return false" onfocus="return false" oncontextmenu="return false" onclick="$.idcode.setCode()"><font color="#6B486E">V</font><font color="#73C841">H</font><font color="#D6A03C">X</font><font color="#73C841">D</font></div><span id="ehong-code-tip-ck" class="ehong-code-val-tip" onclick="$.idcode.setCode()">换个验证码?</span></div> -->
<!--                 </div> -->
<!--             </div> -->

<!--             <div class="form-group has-feedback"> -->
<!--                 <label for="phoneNum">手机号码</label> -->
<!--                 <div class="input-group"> -->
<!--                     <span class="input-group-addon"><span class="glyphicon glyphicon-phone"></span></span> -->
<!--                     <input id="phoneNum" class="form-control" placeholder="请输入手机号码" maxlength="11" type="text"> -->
<!--                 </div> -->
<!--                 <span style="color:red;display: none;" class="tips"></span> -->
<!--                 <span style="display: none;" class="glyphicon glyphicon-remove form-control-feedback"></span> -->
<!--                 <span style="display: none;" class="glyphicon glyphicon-ok form-control-feedback"></span> -->
<!--             </div> -->

<!--             <div class="row"> -->
<!--                 <div class="col-xs-7"> -->
<!--                     <div class="form-group has-feedback"> -->
<!--                         <label for="idcode-btn">校验码</label> -->
<!--                         <div class="input-group"> -->
<!--                             <span class="input-group-addon"><span class="glyphicon glyphicon-qrcode"></span></span> -->
<!--                             <input id="idcode-btn" class="form-control" placeholder="请输入校验码" maxlength="6" type="text"> -->
<!--                         </div> -->
<!--                         <span style="color:red;display: none;" class="tips"></span> -->
<!--                         <span style="display: none;" class="glyphicon glyphicon-remove form-control-feedback"></span> -->
<!--                         <span style="display: none;" class="glyphicon glyphicon-ok form-control-feedback"></span> -->
<!--                     </div> -->
<!--                 </div> -->
<!--                 <div class="col-xs-5 text-center" style="padding-top: 26px"> -->
<!--                     <button type="button" id="loadingButton" class="btn btn-primary" autocomplete="off">获取短信校验码</button> -->
<!--                 </div> -->
<!--             </div> -->

            <div class="form-group">
            	<input type="submit" class="form-control btn btn-primary" value="立&nbsp;&nbsp;即&nbsp;&nbsp;注&nbsp;&nbsp;册">
            </div>

            <div class="form-group">
            	<input type="button" class="form-control btn btn-danger" value="登&nbsp;&nbsp;录" onclick="javascript:location.href='toLogin'">
            </div>
        </form>
    </div>
</div>
<script src="${serverRoot }static/base/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${serverRoot }static/page/user/addUser.js"></script>
</body>
</html>