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

    <title>AdImage</title>

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
    <ul>

            <li>
                <a href="${imageInfo.url}" target="_blank" class="a"><img src="${imageInfo.url}" alt="img" class="url"></a>
                <table class="table_content">
                    <tbody>
                    <tr><td width="740px">${imageInfo.name}</td>
                        <td><a href="/control_panel/adimage/edit?id=${imageInfo.id}"><img class="button" src="img/edit.png"/></a></td>
                    </tr>
                    <tr><td colspan="3"><a href="${imageInfo.link}">${imageInfo.link}</a></td></tr>
                    </tbody>
                </table>
            </li>
    </ul>
</div>

<div id="foot">
    <img id="foot_icon" src="img/btv.ico"/>
    <a id="copyright">Copyright © 2016-2017 WIATEC All Rights Reserved</a>
</div>

</body>
</html>
