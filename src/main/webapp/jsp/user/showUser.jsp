<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试</title>
<style type="text/css">
.content{
	color: blue;
    font-size: 22px;
    text-align: center;
    width: 100%;
}
</style>
</head>
<body>
		<jsp:include page="nav.jsp"></jsp:include>
<div class="container theme-showcase" style="    margin-top: 100px;" role="main">
    <div class="col-md-6 col-md-offset-3">
        <form action="user/addUesr" method="post"  class="" enctype="multipart/form-data" >

			<div class="form-group has-feedback reg">
				<label ><h1 style="color:green">welcome</h1>
				 <img src="${imageRoot }${data.user.headImage}" width="60px" class="img-circle">
				 ${data.user.userName }</label>
			</div>

            <div class="form-group has-feedback" style="border: double;">
                <label class="content">简介</label>
                <div>${data.user.content }</div>
            </div>

        </form>
    </div>
</div>
</body>
</html>