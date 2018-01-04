<%
    String path = request.getContextPath();
    String basePath = path + "/";
%>
<base href="<%=basePath%>">

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript" lang="" src="${serverRoot }static/base/jquery/1.12.2/jquery-1.12.2.min.js"></script>
<script type="text/javascript" lang="" src="${serverRoot }static/base/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${serverRoot }static/page/login/layui/layui.js"></script>

<link rel="stylesheet" type="text/css" href="${serverRoot }static/base/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"  href="${serverRoot }static/page/login/layui/css/layui.css"/>


        <link rel="stylesheet" type="text/css" href="${serverRoot }static/base/fileinput/css/default.css">
        <link href="${serverRoot }static/base/fileinput/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
        <script src="${serverRoot }static/base/fileinput/js/fileinput.js" type="text/javascript"></script>
<!--         简体中文 -->
        <script src="${serverRoot }static/base/fileinput/js/locales/zh.js" type="text/javascript"></script>
