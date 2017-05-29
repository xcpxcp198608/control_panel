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

    <title>USER</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="format-detection" content="telephone=no"/>

    <link rel="stylesheet" type="text/css" href="css/base.css">
    <script type="application/javascript" language="JavaScript" src="js/base.js"></script>
    <link rel="shortcut icon" href="img/xxx.ico">

</head>

<body>
    <table border="1px" cellspacing="0px" cellpadding="0px" width="100%">
        <tr><th>Id</th><th>UserName</th><th>Email</th><th>FirstName</th><th>LastName</th>
            <th>RegisterDate</th><th>Status</th><th>UserLevel</th><th>MemberExpireDate</th><th>Operation</th></tr>
    <s:iterator value="userInfoList">

        <tr>
            <td align="center">${id}</td>
            <td align="center">${userName}</td>
            <td align="center">${email}</td>
            <td align="center">${firstName}</td>
            <td align="center">${lastName}</td>
            <td align="center">${registerDate}</td>
            <td align="center">${status}</td>
            <td align="center">${level}</td>
            <td align="center">${memberDate}</td>
            <td>
                <s:form method="POST" action="changeMember" namespace="/user">
                    <s:hidden name="userName"/>
                    <s:hidden name="memberTime"/>
                    <s:select name="level1" label="Level" list="#{1:0 , 2:1 , 3:2 , 4:3}" listKey="key" listValue="value"
                                headerKey="0" headerValue="Select"/>
                    <s:select name="month1" label="Month" list="#{1:1 , 2:3 , 3:6 , 4:12}" listKey="key" listValue="value"
                              headerKey="0" headerValue="Select"/>
                    <s:submit value="confirm"/>
                </s:form>
            </td>
        </tr>
    </s:iterator>
    </table>
</body>
</html>
