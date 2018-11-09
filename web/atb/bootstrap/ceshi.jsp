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
            margin:0 auto;
            list-style: none;
            width: 750px;
            padding: 0;
            overflow: hidden;
            background-color: #eee;
        }
        li {
            float: left;
            width: 150px;
            height: 40px;
            text-align: center;
        }
        li a {
            display: block;
            padding: 10px 15px;
            color: black;
            text-align: center;
            text-decoration: none;
        }
        li a:hover {
            background-color: red;
        }
        #footer {
            height: 40px;
            line-height: 40px;
            position: fixed;
            bottom: 0;
            width: 100%;
            text-align: center;
            background: #333;
            color: #fff;
            font-size: 12px;
            letter-spacing: 1px;
        }
    </style>
</head>
<body background="../javaWebDvd/images/banner.jpg">
<div align='center'>
    <ul>
        <li><a href="https://www.baidu.com">主页</a></li>
        <li><a href="https://www.baidu.com">管理数据</a></li>
        <li><a href="https://www.baidu.com">借阅情况</a></li>
        <li><a href="https://www.baidu.com">撤回数据</a></li>
        <li><a href="https://www.baidu.com">说明介绍</a></li>
    </ul>
</div>
<div id="footer">安徽信息工程学院 2016级 JAVA①班 安天宝 JavaWeb项目作业</div>
</body>
</html>