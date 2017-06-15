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
    <style type="text/css">
        table tr:nth-child(odd){
            background: #adcccc;
        }
        a:hover{
            text-decoration: none;
            color: white;
        }
    </style>

</head>

<body style="background-color: #dcdcdc">
    <div style="height: 50px; background-color: #000000; color: #d5d5d5; width: 100%">
        <a style="width: 100%; display: block; line-height: 50px;font-size: 30px;color: white;
            margin: auto; text-align: center; font-family: Times; text-decoration: none"
           href="/control_panel/user/show">User Control Panel</a>
    </div><br/>
    <div style="height: 30px; width: 90%; margin: auto">
        <s:form method="POST" namespace="/user" action="search" theme="simple">
            Search:
            <s:select name="searchKey" label="search" listKey="key" listValue="value"
                      list="#{1:'id', 2:'firstname',3:'lastname', 4:'email', 5:'username', 6:'status', 7:'level'}"/>
            <s:textfield name="condition"/>
            <s:submit value="search"/>
        </s:form>
    </div>
    <div style="width: 90%; margin: auto" >
        <table border="1px" cellspacing="0px" cellpadding="0px" width="100%" bgcolor="white">
        <tr style="height: 40px; font-size: 16px; background-color: #000000; color: #d5d5d5" >
            <th>Item</th><th>Id</th><th>UserName</th><th>Email</th><th>Name</th><th>RegisterDate</th>
            <th>Status</th><th>UserLevel</th><th>MemberExpireDate</th><th>Operation</th><th>Delete</th>
        </tr>
        <s:iterator value="userInfoList" status="s">
        <tr style="font-size: 11px; height: 30px; line-height: 30px ; text-align: center">
            <td align="center" width="3%">${s.index+1}</td>
            <td align="center" width="3%">${id}</td>
            <td align="center" width="7%">${userName}</td>
            <td align="center" width="13%">${email}</td>
            <td align="center" width="8%">${firstName} ${lastName}</td>
            <td align="center" width="11%">${registerDate}</td>
            <td align="center" width="12%">
                <s:form action="active" method="POST" theme="simple" namespace="/user">
                    <s:if test="emailStatus == 1">ACTIVE</s:if>
                    <s:else>NEGATIVE</s:else>
                    <s:hidden name="userName"/>
                    <s:submit value="Active"/>
                </s:form>

            </td>
            <td align="center" width="5%">${level}</td>
            <td align="center" width="12%">${memberDate}</td>
            <td align="center" width="18%">
                <s:form method="POST" action="changeMember" namespace="/user" theme="simple">
                    <s:hidden name="userName"/>
                    <s:hidden name="memberTime"/>
                    <s:select name="level1" label="Level" list="#{1:0 , 2:1 , 3:2 , 4:3}" listKey="key" listValue="value"
                                headerKey="0" headerValue="Level"/>
                    <s:select name="month1" label="Month" list="#{1:0 , 2:1 , 3:3 , 4:6 , 5:12}" listKey="key" listValue="value"
                              headerKey="0" headerValue="Month"/>
                    <s:submit value="confirm"/>
                </s:form>
            </td>
            <td align="center" width="8%">
                <a href="/control_panel/user/delete?userName=${userName}" onclick="return confirm('Confirm Delete')">
                    <img src="img/delete.png" alt="delete" width="20px" height="20px">
                </a>
            </td>
        </tr>
        </s:iterator>
    </table>
    </div>
</body>
</html>
