<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\10\25 0025
  Time: 9:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>用户注册</title>
    <!-- Custom Theme files -->
    <%
        if(request.getAttribute("FH") == null){
    %>
    <link href="../../css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <%
    }else{
    %>
    <link href="./atb/javaWebDvd/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <%
        }
    %>
    <link href="../../css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <!-- Custom Theme files -->
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
    <!--Google Fonts-->
    <link href='http://fonts.useso.com/css?family=Roboto:500,900italic,900,400italic,100,700italic,300,700,500italic,100italic,300italic,400' rel='stylesheet' type='text/css'>
    <link href='http://fonts.useso.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <!--Google Fonts-->
</head>
<body>
<div class="login">
    <h2>DVD Mgr 6.0 管理系统</h2>
    <div class="login-top">
        <h1>欢迎注册</h1>
        <form action="../../../../atbRegister.do" method="post">
            <input type="hidden" name="yc" id="yc" title="<%=(int)((Math.random()*9+1)*1000)%>" value="<%=(int)((Math.random()*9+1)*1000)%>">
            <input type="button" id="btn" onclick="settime(this)" value="获取验证码">
            <div class="forgot">
                <%
                    if(request.getAttribute("FH") == null){
                %>
                <a href = '../../../javaWebDvdLogin.jsp'>返回</a>
                <%
                }else{
                %>
                <a href = './atb/javaWebDvdLogin.jsp'>返回</a>
                <%
                    }
                %>
                <input type="submit" value="注册">
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    if(<%= request.getAttribute("MSG")!=null %>){
        alert('<%=request.getAttribute("MSG") %>');
    }
    var countdown=5;
    function settime(val) {
        if (countdown == 0) {
            <%System.out.println(1);%>
            val.removeAttribute("disabled");
            val.value="获取验证码";
            countdown = 5;
        } else {
            <%System.out.println(5);%>
            val.setAttribute("disabled", true);
            val.value="重新发送(" + countdown + ")";
            countdown--;
        }
        setTimeout(function() {
            settime(val)
        },1000)
    }
</script>
<div class="copyright">
    <p>安徽信息工程学院 2016级 JAVA①班 安天宝 JavaWeb项目作业</p>
</div>
</body>
</html>
