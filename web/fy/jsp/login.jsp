<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/25 0025
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="/fy/css/style.css" />
    <link rel="stylesheet" type="text/css" href="/fy/css/body.css"/>
</head>
<body>

<div class="container">
    <section id="content">
        <form action="/fy/servlet/login.do" id="f1" method="get" onsubmit="return checkLogin()">
            <h1>DVD登录</h1>
            <div>
                <input type="text" placeholder="账号" name="username" id="username" />
            </div>
            <div>
                <input type="password" placeholder="密码" name="password" id="password" />
            </div>

            <div>
                <a href="getpassword.jsp">找回密码</a>
                <a href="register.jsp">注册</a>
            </div>
                <input type="submit" value="登录" />
        </form>
    </section>
</div>
<script type="text/javascript">
    //检查登录表单
    function checkLogin()
    {
        //1.取值
        var  name=document.getElementById("username").value;
        var  pass=document.getElementById("password").value;
        //2.判断
        if(name!="")
        {
            if(pass!="") {
                return true;
            }else{
                alert("亲，密码不能为空哦~");
                document.getElementById("password").focus(); //光标占位
                return false;
            }
        }
        else
        {
            alert("亲，账号不能为空哦~");
            document.getElementById("username").focus(); //光标占位
            return false;
        }
    }
</script>
</body>
</html>
