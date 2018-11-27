<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/27 0027
  Time: 19:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <style>
        #log{
            width: 550px;
            height:350px;
            border: #0099FF solid 1px;
            border-radius:10px;
            background-color: #fdfdfd;
            text-align: center;
            margin-left: 33%
        }
        #name {
            width: 320px;
            height: 45px;
            margin-top: 50px;
            border:#0099FF solid 1px;
            padding-left: 10px
        }
        #password {
            width: 320px;
            height: 45px;
            margin-top: 30px;
            border:#0099FF solid 1px;
            padding-left: 10px
        }
        #login{
            background-color: #2494e5;
            width:320px;
            height:50px;
            margin-top: 35px;
            border-radius: 16px
        }
    </style>
</head>
<body>
<div id="log">

    <input type="text" placeholder="用户名/邮箱/手机号" id="name"><br>

    <input type="password" placeholder="六位密码" id="password"><br>

    <button id="login">登录</button>

</div>
</body>
</html>
