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

    <title>ONLINE</title>

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
    <style type="text/css">
        #data tr:nth-child(odd){
            background: #adcccc;
        }
        a:hover{
            text-decoration: none;
            color: white;
        }
    </style>

</head>

<body style="background-color: #dcdcdc ; font-family: Arial">
<div style="height: 50px; background-color: #000000; color: #d5d5d5; width: 100%">
    <a style="width: 100%; display: block; line-height: 50px;font-size: 35px;color: white;
            margin: auto; text-align: center; font-family: Arial; text-decoration: none"
       href="/control_panel/user2/showByPage">User Control Panel</a>
</div><br/>
<div style="width: 90%; margin: auto" >
    <table id="data" border="1px" cellspacing="0px" cellpadding="0px" width="100%" bgcolor="white">
        <tr style="height: 40px; font-size: 16px; background-color: #000000; color: #d5d5d5" >
            <th>Id</th><th>UserName</th><th>Email</th><th>Phone</th><th>FirstName</th><th>LastName</th><th>EmailStatus</th>
            <th>UserLevel</th><th>MemberExpireDate</th><th>Online</th><th>Delete</th>
        </tr>
        <s:iterator value="user1InfoList" status="s">
            <tr style="font-size: 11px; height: 28px; line-height: 30px ; text-align: center">
                <td align="center" width="3%">${id}</td>
                <td align="center" width="7%">${userName}</td>
                <td align="center" width="16%">${email}</td>
                <td align="center" width="12%">${phone}</td>
                <td align="center" width="10%">${firstName}</td>
                <td align="center" width="10%">${lastName}</td>
                <td align="center" width="15%">
                    <s:form action="active" method="POST" theme="simple" namespace="/user1">
                        <s:hidden name="userName"/>
                        <s:if test="emailStatus == 1">ACTIVE</s:if>
                        <s:else><span style="color: red">NEGATIVE</span><s:submit value="Active"/></s:else>
                    </s:form>
                </td>
                <td align="center" width="8%">${level}</td>
                <td align="center" width="12%">${memberDate}</td>
                <td>
                    <s:if test="online"><img src="img/online.png" alt="online" width="16px" height="16px"></s:if>
                    <s:else><img src="img/offline.png" alt="online" width="16px" height="16px"></s:else>
                </td>
                <td align="center" width="7%">
                    <a href="/control_panel/user1/delete?userName=${userName}" onclick="return confirm('Do you confirm delete this user')">
                        <img src="img/delete.png" alt="delete" width="20px" height="20px">
                    </a>
                </td>

            </tr>
        </s:iterator>
    </table>
</div>
</body>
</html>
