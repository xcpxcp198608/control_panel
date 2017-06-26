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

    <title>RSS</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="format-detection" content="telephone=no" />

    <link rel="stylesheet" type="text/css" href="css/base.css"/>
    <link rel="stylesheet" type="text/css" href="css/image.css"/>
    <link rel="shortcut icon" href="img/btv.ico"/>
</head>

<body>

<div id="head">
    <div id="head_content">
        <a id="title">BTVi3 CONTROL PANNEL</a>
        <a class="navigation" href="/control_panel/rollimage/show">RollImage</a>
        <a class="navigation" href="/control_panel/adimage/show">AdImage</a>
        <a class="navigation" href="/control_panel/kodiimage/show">BTVImage</a>
        <a class="navigation_logout" href="/control_panel/manager/logout">Logout</a>
    </div>
</div>

<div id="content">
    <br/><br/>
    <a href="/control_panel/message1/edit"><img class="button" src="img/add.png"></a>
    <br/><br/>
    <table border="1px" bordercolor="#333333" cellspacing="0" width="100%">
        <thead><tr><th>Id</th><th>Content</th><th>ColorR</th><th>ColorG</th><th>ColorB</th><th>Edit</th><th>Delete</th></tr></thead>
        <tbody>
        <s:iterator value="message1InfoList">
            <tr>
                <td width="20px" align="center">${id}</td>
                <td width="600px">${content}</td>
                <td width="80px" align="center">${colorR}</td>
                <td width="80px" align="center">${colorG}</td>
                <td width="80px" align="center">${colorB}</td>
                <td width="50px" align="center"><a href="/control_panel/message1/edit?id=${id}"><img class="button" src="img/edit.png"/></a></td>
                <td width="50px" align="center"><a href="/control_panel/message1/delete?id=${id}"><img class="button" src="img/delete.png"/></a></td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</div>

<div id="foot">
    <img id="foot_icon" src="img/btv.ico"/>
    <a id="copyright">Copyright © 2016-2017 WIATEC All Rights Reserved</a>
</div>

</body>
</html>
