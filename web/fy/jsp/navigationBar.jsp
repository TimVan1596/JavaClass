<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/18 0018
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
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
<body>

<ul >
    <li ><a class="active" href="/fy/servlet/toShowDvd" >主页</a></li>
    <li ><a href="column.jsp">柱形图</a></li>
    <li ><a href="/fy/recycle">回收站<span class="layui-badge"><%=(int)session.getAttribute("recycle")%></span></a></li>
    <li ><a href="/fy/servlet/toCancelling" style="margin-left:1200px;">退出登录</a></li>
</ul>

</body>
</html>
