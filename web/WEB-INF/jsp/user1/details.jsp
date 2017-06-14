<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>DETAILS</title>

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
    <style rel="stylesheet" type="text/css" >
        tr {
            height: 35px;
            font-size: 20px;
        }
        tr:nth-child(odd){
            background: #adcccc;
        }
        td{
            align-content: center;
            text-align: center;
        }
    </style>

</head>

<body style="background-color: #dcdcdc ; font-family: Arial ;padding: 0px; margin: 0px">
    <div style="height: 50px; background-color: #000000; color: #d5d5d5; width: 100%">
        <a style="width: 100%; display: block; line-height: 50px;font-size: 35px;color: white;
                margin: auto; text-align: center; font-family: Arial; text-decoration: none"
           href="/control_panel/user1/showByPage">User Control Panel</a>
    </div><br/><br/><br/>
    <div style="width: 70%; margin: auto">
        <table width="100%" border="1px" cellspacing="0px" cellpadding="0px" bgcolor="white">
            <tr><th width="25%">Name</th><th width="75%">Description</th></tr>
            <tr>
                <td>UserName</td><td>${user1Info.userName}</td>
            </tr>
            <tr>
                <td>Email</td><td>${user1Info.email}</td>
            </tr>
            <tr>
                <td>FirstName</td><td>${user1Info.firstName}</td>
            </tr>
            <tr>
                <td>LastName</td><td>${user1Info.lastName}</td>
            </tr>
            <tr>
                <td>Phone</td><td>${user1Info.phone}</td>
            </tr>
            <tr>
                <td>Level</td><td>${user1Info.level}</td>
            </tr>
            <tr>
                <td>ActiveStatus</td>
                <td>
                    <s:if test="user1Info.emailStatus == 1">ACTIVE</s:if>
                    <s:else><span style="color:#ff0000;">NEGATIVE</span></s:else>
                </td>
            </tr>
            <tr>
                <td>Mac</td><td>${user1Info.mac}</td>
            </tr>
            <tr>
                <td>EthernetMac</td><td>${user1Info.ethernetMac}</td>
            </tr>
            <tr>
                <td>Country</td><td>${user1Info.country}</td>
            </tr>
            <tr>
                <td>Region</td><td>${user1Info.region}</td>
            </tr>
            <tr>
                <td>City</td><td>${user1Info.city}</td>
            </tr>
            <tr>
                <td>TimeZone</td><td>${user1Info.timeZone}</td>
            </tr>
            <tr>
                <td>ActiveDate</td><td>${user1Info.activeDate}</td>
            </tr>
            <tr>
                <td>MemberDate</td><td>${user1Info.memberDate}</td>
            </tr>
        </table>
    </div>

</body>
</html>
