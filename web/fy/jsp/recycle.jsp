<%@ page import="com.smallfangyu.data.DVD" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/30 0030
  Time: 9:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/common/util/layui/css/layui.css"  media="all">
    <title>回收站</title>
    <style>

        table{
            border:1px solid blue;
        }
        .button {
            background-color: #008CBA;
            border-radius: 4px;
            border: none;
            color: white;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 10px;
            margin: 4px 2px;
            cursor: pointer;
        }

         ul {
             list-style-type: none;
             margin: 0;
             padding: 0;
             overflow: hidden;
             border: 1px solid #e7e7e7;
             background-color: #f3f3f3;
         }
        li {
            float:left;
        }
        li a{
            display: block;
            color: #666;
            text-align: center;
            padding: 24px 34px;
            text-decoration-line: none;
        }
        li a:hover{
            background-color: #ddd;
        }
        li a.active{
            color:white;
            background-color: #4CAF50;
        }

    </style>

</head>
<%
session.getAttribute("deletelistDVD");
//把SESSION的dvd数据放进集合里
ArrayList<DVD> list=(ArrayList<DVD>)session.getAttribute("deletelistDVD");
%>
<body>
<ul>
    <li><a href="/fy/servlet/toShowDvd" >主页</a></li>
    <li><a href="column.jsp">柱形图</a></li>
    <li><a class="active" href="/fy/recycle">回收站</a></li>
    <li><a href="/fy/servlet/toCancelling" style="margin-left:1200px;">退出登录</a></li>
</ul>

<table id="tb"  style="text-align:center;margin: 0 auto;margin-top: 30px;" border="1">
    <tr><th>DVD编号</th><th>DVD预览</th><th>DVD名称</th><th>DVD状态</th><th>操作</th></tr>
    <% for(DVD dvd:list){ %>
    <tr>
        <td style="width:110px"><%=dvd.getId() %></td>
        <td style="width:110px"><img style="width:110px;height:80px" src="<%= dvd.getPicture()%>" /></td>
        <td style="width:110px"> <%=dvd.getDvdname() %></td>
        <% if(dvd.getState().equals("可以借")){%><td style="width:110px;background-color: limegreen"><%=dvd.getState() %></td><% }%>
        <% if(dvd.getState().equals("已借出")){%><td style="width:110px;background-color: red"><%=dvd.getState() %></td><% }%>
        <td style="width:110px"><a class="button" href="/fy/reduce?id=<%=dvd.getId() %>" >还原</a></td>
    </tr>
    <%  }%>
</table>

</body>
</html>
