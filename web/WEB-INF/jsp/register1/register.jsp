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

    <title>Register</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="format-detection" content="telephone=no" />
    <!--
    <link rel="stylesheet" type="text/css" href="css/base.css">
    <script type="application/javascript" language="JavaScript" src="js/base.js"/>
    <link rel="shortcut icon" href="img/xxx.ico">
    -->

</head>

<body>
    Register<hr/>
    <s:form method="POST" namespace="/user1" action="register">
        <s:textfield name="user1Info.userName" label="UserName"/>
        <s:textfield name="user1Info.password" label="Password"/>
        <s:textfield name="user1Info.email" label="Email"/>
        <s:textfield name="user1Info.firstName" label="FirstName"/>
        <s:textfield name="user1Info.lastName" label="LastName"/>
        <s:textfield name="user1Info.ethernetMac" label="EthernetMac"/>
        <s:textfield name="language" label="Language"/>
        <s:submit value="Register"/>
    </s:form>

</body>
</html>
