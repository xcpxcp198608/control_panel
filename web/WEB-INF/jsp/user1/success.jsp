<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>Success</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="format-detection" content="telephone=no"/>
    <!--
    <link rel="stylesheet" type="text/css" href="css/base.css">
    <script type="application/javascript" language="JavaScript" src="js/base.js"/>
    <link rel="shortcut icon" href="img/xxx.ico">
    -->

</head>

<body>
    <div style="height: 100px"></div>
    <div style="width: 620px; height:420px; margin: auto; background-image: url(img/bg_s.png)">
    <br/>
    <div style="height: 128px; width: 128px; margin: auto;">
        <img src="img/success.png">
    </div>
    <a style="width: 620px; margin: auto; display: block ;text-align: center;font-size: 30px; color: green">
        ${userOperationMessage}</a>
    <br><div style="width: 90%; height: 1px; background-color: #5a5a5a; margin: auto"></div><br>
    <span style="width: 100%; margin: auto; display:block; text-align: center; font-size: 22px; color: red">
            Please remember your user name and password</span><br>
    <span style="width: 60%; margin: auto; display:block; font-size: 20px">
            UserName: ${user1Info.userName}</span><br>
    <span style="width: 60%; margin: auto; display:block; font-size: 20px">
            Password: ${user1Info.password}</span><br>

</div>
</body>
</html>
