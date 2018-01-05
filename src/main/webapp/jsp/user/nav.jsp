<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传</title>
<style type="text/css">
.size30{
	width: 30px;
}
</style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar" aria-expanded="false"
				aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">TEST PROJECT</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li><a href="overview">Home</a></li>
				<c:if test="${empty currentUser }">
				<li><a href="user/toAddUser">登录/注册</a></li>
				</c:if>
				<c:if test="${!empty currentUser }">
				<li ><a href="user/showUser"><img class="img-circle size30" alt="" src="${imageRoot }${currentUser.headImage}">${currentUser.userName }</a></li>
				<li><a href="logout">退出</a></li>
				</c:if>
			</ul>
		</div>
		<!--/.nav-collapse -->
	</div>
</nav>
</body>
</html>