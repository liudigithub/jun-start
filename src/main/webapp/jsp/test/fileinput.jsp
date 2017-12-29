<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件上传</title>
</head>
<body>
	<div class="htmleaf-container">
                <header class="htmleaf-header">
                    <h1>基于Bootstrup 3可预览的HTML5文件上传插件 <span>An enhanced HTML 5 file input for Bootstrap 3.x with file preview, multiple selection, and more features</span></h1>
                    <div class="htmleaf-links">
                        <a class="htmleaf-icon icon-htmleaf-home-outline" href="http://www.htmleaf.com/" title="jQuery之家" target="_blank"><span> jQuery之家</span></a>
                        <a class="htmleaf-icon icon-htmleaf-arrow-forward-outline" href="http://www.htmleaf.com/html5/html5muban/201505091801.html" title="返回下载页" target="_blank"><span> 返回下载页</span></a>
                    </div>
                </header>
                <div class="container kv-main">
                    <div class="page-header">
                    <h2>Bootstrap File Input Example</h2>
                    </div>
                    <form enctype="multipart/form-data">
                        <input id="file-0a" class="file" type="file" multiple data-min-file-count="1">
                        <br>
                        <button type="submit" class="btn btn-primary">Submit</button>
                        <button type="reset" class="btn btn-default">Reset</button>
                    </form>
                    <hr>
                    <form enctype="multipart/form-data">
                        <label>Test invalid input type</label>
                        <input id="file-0b" class="file" type="text" multiple data-min-file-count="1">
                        <script>
                        $(document).on('ready', function(){$("#file-0b").fileinput();});
                        </script>
                    </form>
                    <hr>
                    <form enctype="multipart/form-data">
                        <input id="file-0a" class="file" type="file" multiple data-min-file-count="3">
                        <hr>
                         <div class="form-group">
                            <input id="file-0b" class="file" type="file">
                        </div>
                        <hr>
                        <div class="form-group">
                            <input id="file-1" type="file" multiple class="file" data-overwrite-initial="false" data-min-file-count="2">
                        </div>
                        <hr>
                        <div class="form-group">
                            <input id="file-2" type="file" class="file" readonly data-show-upload="false">
                        </div> 
                        <hr>
                        <div class="form-group">
                            <label>Preview File Icon</label>
                            <input id="file-3" type="file" multiple=true>
                        </div>
                        <hr>
                        <div class="form-group">
                            <input id="file-4" type="file" class="file" data-upload-url="#">
                        </div>
                        <hr>
                        <div class="form-group">
                            <button class="btn btn-warning" type="button">Disable Test</button>
                            <button class="btn btn-info" type="reset">Refresh Test</button>
                            <button class="btn btn-primary">Submit</button>
                            <button class="btn btn-default" type="reset">Reset</button>
                        </div>
                        <hr>
                        <div class="form-group">
                            <input type="file" class="file" id="test-upload" multiple>
                            <div id="errorBlock" class="help-block"></div>
                        </div>
                        <hr>
                        <div class="form-group">
                            <input id="file-5" class="file" type="file" multiple data-preview-file-type="any" data-upload-url="#">
                        </div>
                    </form>
                    
                    
                    <hr>
                    <h4>支持多种语言</h4>
                    <form enctype="multipart/form-data">
                        <label>简体中文</label>
                        <input id="file-zh" name="file-zh[]" type="file" multiple>
                        <hr style="border: 2px dotted">
                        <label>繁体中文</label>
                        <input id="file-zh-TW" name="file-zh-TW[]" type="file" multiple>
                    </form>
                    <hr>
                    <br>
                </div>
                <div class="related">
                    <h3>如果你喜欢这个插件，那么你可能也喜欢:</h3>
                    <a href="http://www.htmleaf.com/jQuery/Layout-Interface/201505011764.html">
                      <img src="related/1.jpg" width="300" alt="基于bootstrup的响应式jQuery滚动新闻插件"/>
                      <h3>基于bootstrup的响应式jQuery滚动新闻插件</h3>
                    </a>
                    <a href="http://www.htmleaf.com/jQuery/Menu-Navigation/201502281434.html">
                      <img src="related/2.jpg" width="300" alt="基于Twitter Bootstrap 3的炫酷jQuery下拉列表插件"/>
                      <h3>基于Twitter Bootstrap 3的炫酷jQuery下拉列表插件</h3>
                    </a>
                </div>
            </div>
<script type="text/javascript" lang="" src="${serverRoot }static/page/test/fileinput.js"></script>
</body>
</html>