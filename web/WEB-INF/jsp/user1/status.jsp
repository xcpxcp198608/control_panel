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

    <title>STATUS</title>

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
    <h1>CURRENT USER STATUS</h1>
    <hr/>
    <h3>Current Online Count: <s:property value="userSessionMap.size()"/><h3/>
    <table width="100%" border="1px" cellpadding="0px" cellspacing="0px">
        <tr><th>Item</th><th>UserName</th><th>Country</th><th>RegionName</th><th>TimeZone</th>
            <th>City</th><th>Mac</th><th>EthernetMac</th></tr>
        <s:iterator value="user1InfoList">
            <tr>
                <td align="center">${id}</td>
                <td align="center">${userName}</td>
                <td align="center">${country}</td>
                <td align="center">${region}</td>
                <td align="center">${timeZone}</td>
                <td align="center">${city}</td>
                <td align="center">${mac}</td>
                <td align="center">${ethernetMac}</td>
            </tr>
        </s:iterator>

    </table>

</body>
</html>
