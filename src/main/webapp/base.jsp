<%
    String path = request.getContextPath();
    String basePath = path + "/";
%>
<base href="<%=basePath%>">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript" lang="" src="${serverRoot }static/base/jquery/1.12.2/jquery-1.12.2.min.js"></script>
<script type="text/javascript" lang="" src="${serverRoot }static/base/bootstrap/3.3.5/js/bootstrap.min.js"></script>


<link rel="stylesheet" type="text/css" href="${serverRoot }static/base/bootstrap/3.3.5/css/bootstrap.min.css">