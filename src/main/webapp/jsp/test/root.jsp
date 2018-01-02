<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>URL</title>
</head>
<body>
    <div class="container theme-showcase" role="main">
    	<div class="row">
   		<div class="col-sm-12">
          <div class="list-group">
            <a href="javascript:void(0)" class="list-group-item active">
              URL:
            </a>
            <c:forEach items="${data.map }" var="map"> 
            	<a href="${map.value }" class="list-group-item">${map.key } : ${map.value }</a>
            </c:forEach>
          </div>
        </div>
    	</div>
    </div>
</body>
</html>