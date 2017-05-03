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

    <title>Message</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <link rel="stylesheet" type="text/css" href="css/base.css"/>
    <link rel="stylesheet" type="text/css" href="css/image.css"/>
    <link rel="shortcut icon" href="img/btv.ico"/>
</head>

<body>

<div id="head">
    <div id="head_content">
        <a id="title">BTVi3 CONTROL PANNEL</a>
        <a class="navigation" href="/control_panel/rollimage/show">RollImage</a>
        <a class="navigation" href="/control_panel/rolloverimage/show">RollOverImage</a>
        <a class="navigation" href="/control_panel/adimage/show">AdImage</a>
        <a class="navigation" href="/control_panel/kodiimage/show">BTVImage</a>
        <a class="navigation" href="/control_panel/message/show">Message</a>
        <a class="navigation" href="/control_panel/message1/show">RSS</a>
        <a class="navigation_logout" href="/control_panel/manager/logout">Logout</a>
    </div>
</div>

<div id="content">
    <br/><br/>
    <a href="/control_panel/message/edit"><img class="button" src="img/add.png"></a>
    <br/><br/>
    <table border="1px" bordercolor="#333333" cellspacing="0" width="100%" >
        <thead><tr><th>Id</th><th>Title</th><th>Content</th><th>Type</th><th>Link</th><th>Edit</th><th>Delete</th></tr></thead>
        <tbody>
        <s:iterator value="messageInfoList">
            <tr>
                <td width="20px" align="center">${id}</td>
                <td width="200px">${title}</td>
                <td width="300px">${content}</td>
                <td width="100px" align="center">${type}</td>
                <td width="300px">${link}</td>
                <td width="50px" align="center"><a href="/control_panel/message/edit?id=${id}"><img class="button" src="img/edit.png"/></a></td>
                <td width="50px" align="center"><a href="/control_panel/message/delete?id=${id}"><img class="button" src="img/delete.png"/></a></td>
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
