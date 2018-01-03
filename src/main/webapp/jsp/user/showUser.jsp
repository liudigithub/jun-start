<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试</title>
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

            <div class="form-group has-feedback">
                <div class="blt-start-img">
					<div id="preview" class="blt-img-preview blt-img-size-lg center">
						
						<img id="imgView1" border="0" src="" class="blt-border blt-img">
					</div>
				</div>
            </div>

        </form>
    </div>
</div>
</body>
</html>