<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>RollImage</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <link rel="stylesheet" type="text/css" href="css/base.css">
    <link rel="stylesheet" type="text/css" href="css/image.css">
    <link rel="shortcut icon" href="img/btv.ico">

</head>

<body>
<div id="head">
    <div id="head_content">
        <a id="title">BTVi3 CONTROL PANNEL</a>
        <a class="navigation" href="/control_panel/rollimage/show">RollImage</a>
        <a class="navigation" href="/control_panel/adimage/show">AdImage</a>
        <a class="navigation" href="/control_panel/kodiimage/show">BTVImage</a>
        <a class="navigation" href="/control_panel/rolloverimage/show">Eufonic</a>
        <a class="navigation_logout" href="/control_panel/manager/logout">Logout</a>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0" />
        <meta name="apple-mobile-web-app-capable" content="yes" />
        <meta name="format-detection" content="telephone=no" />
    </div>
</div>

<div id="content">
    <br/><br/>
    <div id="content1">
        <a href="${imageInfo.url}" target="_blank"><img class="url1" alt="image" src="${imageInfo.url}"></a>
        <s:form action="upload" namespace="/rollimage" method="post" enctype="multipart/form-data">
            <s:hidden name="id"></s:hidden>
            <s:file name="file"></s:file><br/><br/>
            <s:submit value="Upload"></s:submit>
            <s:property value="uploadStatus"/>
        </s:form><br/><br/><br/><br/>

        <s:form action="update" namespace="/rollimage" method="post">
            <s:hidden name="id"></s:hidden>
            <s:hidden name="imageInfo.name"></s:hidden>
            <s:hidden name="imageInfo.url"></s:hidden>
            <s:textfield name="imageInfo.name" label="Name" disabled="true" size="70"></s:textfield>
            <s:textfield name="imageInfo.link" label="Link" size="70"></s:textfield>
            <s:submit value="Commit"></s:submit>
        </s:form>
    </div>
</div>

<div id="foot">
    <img id="foot_icon" src="img/btv.ico"/>
    <a id="copyright">Copyright © 2016-2017 WIATEC All Rights Reserved</a>
</div>

</body>
</html>
