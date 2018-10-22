<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/7 0007
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆</title>
    <style>
        #login{
            text-align:center;
        }
    </style>
</head>
<body>
<div id="login">
<form action="/fy/servlet/login.do" id="f1" method="get" onsubmit="return checkLogin()">
    <h2>MiniDVD 网页版</h2>
    <h4>用户登录</h4>
    <table style="margin: 0 auto " border="0">
        <tr>
        <td>账号：</td>
        <td><input type=" text" name="username" id="loginId" /></td>
        </tr>
        <tr>
        <td>密码：</td>
        <td><input type="password" name="password" id="loginPass"/></td>
        </tr>
    </table>
    <a href="register.jsp">注册</a><a style="margin-left: 100px" href="getpassword.jsp">找回密码</a><br>
    <input type="submit" value="登录"/>
</form>
</div>
<script type="text/javascript">
    //检查登录表单
    function checkLogin()
    {
        //1.取值
        var  name=document.getElementById("loginId").value;
        var  pass=document.getElementById("loginPass").value;
        //2.判断
        if(name!="")
        {
            if(pass!="") {
                return true;
            }else{
                alert("亲，密码不能为空哦~");
                document.getElementById("loginPass").focus(); //光标占位
                return false;
            }
        }
        else
        {
            alert("亲，账号不能为空哦~");
            document.getElementById("loginId").focus(); //光标占位
            return false;
        }
    }
</script>
</body>
</html>
