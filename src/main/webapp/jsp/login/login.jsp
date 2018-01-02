<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0"> 
	<title>登录界面</title>
    <link href="${serverRoot }static/page/login/css/default.css" rel="stylesheet" type="text/css" />
	<!--必要样式-->
    <link href="${serverRoot }static/page/login/css/styles.css" rel="stylesheet" type="text/css" />
    <link href="${serverRoot }static/page/login/css/demo.css" rel="stylesheet" type="text/css" />
    <link href="${serverRoot }static/page/login/css/loaders.css" rel="stylesheet" type="text/css" />
    
    <link href="${serverRoot }static/page/login/layui/css/layui.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class='login'>
	  <div class='login_title'>
	    <span>管理员登录</span>
	  </div>
	  <div class='login_fields'>
	    <div class='login_fields__user'>
	      <div class='icon'>
	        <img alt="" src='${serverRoot }static/page/login/img/user_icon_copy.png'>
	      </div>
	      <input name="login" placeholder='用户名' maxlength="16" type='text' autocomplete="off"/>
	        <div class='validation'>
	          <img alt="" src='${serverRoot }static/page/login/img/tick.png'>
	        </div>
	    </div>
	    <div class='login_fields__password'>
	      <div class='icon'>
	        <img alt="" src='${serverRoot }static/page/login/img/lock_icon_copy.png'>
	      </div>
	      <input name="pwd" placeholder='密码' maxlength="16" type='text' autocomplete="off">
	      <div class='validation'>
	        <img alt="" src='${serverRoot }static/page/login/img/tick.png'>
	      </div>
	    </div>
	    <div class='login_fields__password'>
	      <div class='icon'>
	        <img alt="" src='${serverRoot }static/page/login/img/key.png'>
	      </div>
	      <input name="code" placeholder='验证码' maxlength="4" type='text' name="ValidateNum" autocomplete="off">
	      <div class='validation' style="opacity: 1; right: -5px;top: -3px;">
          <canvas class="J_codeimg" id="myCanvas" onclick="Code();">对不起，您的浏览器不支持canvas，请下载最新版浏览器!</canvas>
	      </div>
	    </div>
	    <div class='login_fields__submit'>
	      <input type='button' value='登录'>
	    </div>
	  </div>
	  <div class='success'>
	  </div>
	  <div class='disclaimer'>
	    <p>欢迎登陆后台管理系统</p>
	  </div>
	</div>
	<div class='authent'>
	  <div class="loader" style="height: 44px;width: 44px;margin-left: 28px;">
        <div class="loader-inner ball-clip-rotate-multiple">
            <div></div>
            <div></div>
            <div></div>
        </div>
        </div>
	  <p>认证中...</p>
	</div>
	<div class="OverWindows"></div>
	
	<script src="${serverRoot }static/base/jquery/1.12.2/jquery-1.12.2.min.js" type="text/javascript"></script>
	<script src="${serverRoot }static/page/login/js/jquery-ui.min.js" type="text/javascript"></script>
	<script src='${serverRoot }static/page/login/js/stopExecutionOnTimeout.js?t=1' type="text/javascript" ></script>
    <script src="${serverRoot }static/page/login/layui/layui.js" type="text/javascript"></script>
    <script src="${serverRoot }static/page/login/js/Particleground.js" type="text/javascript"></script>
    <script src="${serverRoot }static/page/login/js/Treatment.js" type="text/javascript"></script>
    <script src="${serverRoot }static/page/login/js/jquery.mockjax.js" type="text/javascript"></script>
    <script src="${serverRoot }static/page/login/login.js" type="text/javascript"></script>
</body>
</html>