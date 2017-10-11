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
           href="/control_panel/user1/showByPage">User Control Panel</a>
    </div><br/>
    <div style="height: 30px; width: 90%; margin: auto">
        <table bgcolor="#dcdcdc" cellspacing="0px" cellpadding="0px" width="100%">
            <tr>
                <td width="60%">
                    <s:form method="POST" namespace="/user1" action="showByPage" theme="simple">
                        <s:select name="searchKey" label="search" listKey="key" listValue="value"
                                  list="#{1:'Id', 2:'FirstName',3:'LastName', 4:'Email', 5:'UserName', 6:'Status', 7:'Level'}"/>
                        <s:textfield name="condition"/>
                        <s:submit value="search"/>
                    </s:form>
                </td>
                <td width="24%" align="center">
                    <s:form theme="simple">
                        Online: <span style="color: #008400"><s:property value="totalOnlineCount"/></span> / Total: <s:property value="totalCount"/>
                    </s:form>
                </td>
                <td width="3%">
                    <s:if test="currentPage > 1">
                    <s:form method="POST" namespace="/user1" action="showByPage" theme="simple">
                        <s:hidden name="currentPage"/>
                        <s:hidden name="turn" value="1"/>
                        <s:hidden name="searchKey"/>
                        <s:hidden name="condition"/>
                        <s:submit value="<"/>
                    </s:form>
                    </s:if>
                </td>
                <td width="4%" align="center">
                    <s:form theme="simple">
                        <s:property value="currentPage"/>/<s:property value="totalPage"/>
                    </s:form>
                </td>
                <td width="3%">
                    <s:if test="currentPage < totalPage">
                    <s:form method="POST" namespace="/user1" action="showByPage" theme="simple">
                        <s:hidden name="currentPage"/>
                        <s:hidden name="turn" value="2"/>
                        <s:hidden name="searchKey"/>
                        <s:hidden name="condition"/>
                        <s:submit value=">"/>
                    </s:form>
                    </s:if>
                </td>
                <td width="6%" align="right">
                    <s:form method="POST" namespace="/user1" action="showByPage" theme="simple">
                        <s:textfield name="currentPage" size="5"/>
                        <s:submit value="Go"/>
                    </s:form>
                </td>
            </tr>
        </table>
    </div>
    <div style="width: 90%; margin: auto" >
        <table id="data" border="1px" cellspacing="0px" cellpadding="0px" width="100%" bgcolor="white">
        <tr style="height: 40px; font-size: 16px; background-color: #000000; color: #d5d5d5" >
            <th>Id</th><th>UserName</th><th>Email</th><th>Phone</th><th>Name</th><th>EmailStatus</th>
            <th>UserLevel</th><th>MemberExpireDate</th><th>Operation</th><th>Online</th><th>Delete</th><th>Details</th>
        </tr>
        <s:iterator value="user1InfoList" status="s">
        <tr style="font-size: 11px; height: 28px; line-height: 30px ; text-align: center">
            <td align="center" width="3%">${id}</td>
            <td align="center" width="7%">${userName}</td>
            <td align="center" width="13%">${email}</td>
            <td align="center" width="11%">${phone}</td>
            <td align="center" width="8%">${firstName} ${lastName}</td>
            <td align="center" width="12%">
                <s:form action="active" method="POST" theme="simple" namespace="/user1">
                    <s:hidden name="userName"/>
                    <s:if test="emailStatus == 1">ACTIVE</s:if>
                    <s:else><span style="color: red">NEGATIVE</span><s:submit value="Active"/></s:else>
                </s:form>
            </td>
            <td align="center" width="5%">
                <s:if test="level == 5">fto</s:if>
                <s:else>${level}</s:else>
            </td>
            <td align="center" width="12%">${memberDate}</td>
            <td align="center" width="18%">
                <s:form method="POST" action="changeMember" namespace="/user1" theme="simple">
                    <s:hidden name="userName"/>
                    <s:hidden name="id"/>
                    <s:hidden name="memberTime"/>
                    <s:select name="level1" label="Level" list="#{1:0 , 2:1 , 3:2 , 4:3, 5:4, 6:'fto'}" listKey="key" listValue="value"
                                headerKey="0" headerValue="Level"/>
                    <s:select name="month1" label="Month" list="#{1:0 , 2:7 , 3:15 , 4:30 , 5:92, 6:183, 7:365}" listKey="key" listValue="value"
                              headerKey="0" headerValue="Month"/>
                    <s:submit value="confirm"/>
                </s:form>
            </td>
            <td>
                <s:if test="online"><img src="img/online.png" alt="online" width="16px" height="16px"></s:if>
                <s:else><img src="img/offline.png" alt="online" width="16px" height="16px"></s:else>
            </td>
            <td align="center" width="6%">
                <a href="/control_panel/user1/delete?userName=${userName}" onclick="return confirm('Do you confirm delete this user')">
                    <img src="img/delete.png" alt="delete" width="20px" height="20px">
                </a>
            </td>
            <td width="5%">
                <a href="/control_panel/user1/details?userName=${userName}"><img src="img/details.png" alt="details"></a>
            </td>
        </tr>
        </s:iterator>
    </table>
    </div>
</body>
</html>
