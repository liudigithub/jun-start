<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link>
<link rel="stylesheet" type="text/css" href="${serverRoot }static/page/user/addUser.css">
<script type="text/javascript" lang="" src="${serverRoot }static/page/user/addUser.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增用户</title>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">TEST PROJECT</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li><a href="#">Home</a></li>
            <li  class="active"><a href="user/toAddUser">登录/注册</a></li>
            <li><a href="#contact">Contact</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="#">Action</a></li>
                <li><a href="#">Another action</a></li>
                <li><a href="#">Something else here</a></li>
                <li role="separator" class="divider"></li>
                <li class="dropdown-header">Nav header</li>
                <li><a href="#">Separated link</a></li>
                <li><a href="#">One more separated link</a></li>
              </ul>
            </li>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
<div class="container theme-showcase top-100" role="main">
    <div class="col-md-6 col-md-offset-3">
        <form action="user/addUesr" method="post"  class="">

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
            	<input type="button" class="form-control btn btn-danger" value="登&nbsp;&nbsp;录" onclick="javascript:location.href='user/toLogin'">
            </div>
        </form>
    </div>
</div>
</body>
</html>