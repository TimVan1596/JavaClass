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
    <title>DVD管理系统</title>
    <!-- Custom Theme files -->
    <%
        if(request.getAttribute("DL") == null){
    %>
    <link href="./javaWebDvd/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <%
    }else{
    %>
    <link href="./atb/javaWebDvd/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <%
        }
    %>
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
        <h1>欢迎登陆</h1>
        <form action="../atblogin.do" method="post">
            <input type="text" name="username" title="username" value="绑定邮箱"
                   onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '绑定邮箱';}" />
            <input type="text" name="userpassword" title="userpassword" value="输入密码" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"
                   onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '输入密码';}" />
            <%--<input type="text" name="username" title="username" value="账号" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '账号';}">--%>
            <%--<input type="text" name="userpassword" title="userpassword" value="密码" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '密码';}">--%>
            <div class="forgot">
                <%
                    if(request.getAttribute("DL") == null){
                %>
                <a href="./javaWebDvd/jsp/retrieve/retrieve.jsp">忘记密码</a>
                <%
                }else{
                %>
                <a href="./atb/javaWebDvd/jsp/retrieve/retrieve.jsp">忘记密码</a>
                <%
                    }
                %>
                <input type="submit" value="登陆">
            </div>
        </form>
    </div>
    <div class="login-bottom">
        <h3>新用户 &nbsp;
            <%
                if(request.getAttribute("DL") == null){
            %>
            <a href="./javaWebDvd/jsp/register/register.jsp">注册</a>
            <%
            }else{
            %>
            <a href="./atb/javaWebDvd/jsp/register/register.jsp">注册</a>
            <%
                }
            %>
            &nbsp 账号</h3>
    </div>
</div>
<script type="text/javascript">
    if(<%= request.getAttribute("MSG")!=null %>){
        alert('<%=request.getAttribute("MSG") %>');
    }
</script>
<div class="copyright">
    <p>安徽信息工程学院 2016级 JAVA①班 安天宝 JavaWeb项目作业</p>
</div>
</body>
</html>
