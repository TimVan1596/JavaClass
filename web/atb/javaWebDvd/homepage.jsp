<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\10\12 0012
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户管理</title>
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
<body background="./atb/javaWebDvd/images/banner.jpg">
<div style='position:fixed; z-index:999; top:0;color: white'><h4>欢迎您小伙子，想走就<a href="./atb/javaWebDvdLogin.jsp" style="color: red">退出</a></h4></div>
<h1 align='center' style="color: white">----欢迎进入DVD Mgr 6.0 管理系统----</h1>
<h5 align='center' style="color: red">介绍：搜索（关键字）、管理数据（操作数据）、借阅情况（借阅情况柱状图）、历史数据（回收站）、说明介绍（项目记录）、退出（返回登录页面）</h5>
<h5 align='center' style="color: red">界面操作（待完成）：进入该界面需要密码，管理用户信息，可看到用户进行的操作</h5>
<div align='center'>
    <form action='./atbuser.do' method='post'>
        <h4 style="color: white">搜关键字：<input type='text' title="序号书名库存" name='search' style="width: 150px; height: 30px"/>
            <input type='submit' value='搜索' style="width: 50px; height: 30px"/>（这个搜索没用哦~）
        </h4>
    </form>
</div>
<div align='center'>
    <ul>
        <li><a href="./atblogin.do">管理数据</a></li>
        <li><a href="./atb/javaWebDvd/jsp/choice/data.jsp">借阅情况</a></li>
        <li><a href="./atbrevoke.do">历史数据</a></li>
        <li><a href="./atbuser.do" style="background-color: red">用户管理</a></li>
        <li><a href="./atbdescription.do">说明介绍</a></li>
    </ul>
</div>
<div align='center'>
    <h4 align='center' style="color: white">
        <br>还没实现别急躁！
        <br><img src="./atb/javaWebDvd/image/BUG.gif">
    </h4>
</div>
<div id="footer">安徽信息工程学院 2016级 JAVA①班 安天宝 JavaWeb项目作业</div>
</body>
</html>
