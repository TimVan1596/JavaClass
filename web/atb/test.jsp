<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\10\18 0018
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>无序列表</title>
    <style type="text/css">
        ul{
            list-style: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
            background-color: green;
        }
        li {
            float: left;
            width: 150px;
            height: 50px;
            text-align: center;
            line-height: 50px;
        }
        li a {
            display: block;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }
        li a:hover {
            background-color: #111;
        }
    </style>
</head>
<body>
<ul>
    <li><a href="https://www.baidu.com">百度</a></li>
    <li><a href="https://www.baidu.com">百度</a></li>
    <li><a href="https://www.baidu.com">百度</a></li>
</ul>
</body>
</html>
