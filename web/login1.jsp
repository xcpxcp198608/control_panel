<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>Login</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="format-detection" content="telephone=no" />

    <link rel="stylesheet" type="text/css" href="css/index.css">
    <link rel="shortcut icon" href="img/btv.ico">
</head>

<body>

<div id="body">
    <img id="bg" src="img/bg.jpg" alt="image">
    <div id="login">
        <s:form method="POST" action="login1" namespace="/user1">
            <s:textfield name="user1Info.userName" class="text"/>
            <s:textfield name="user1Info.password" class="text" type="password"/>
            <s:submit value="Login" class="text"/>
        </s:form>
    </div>
</div>

</body>
</html>
