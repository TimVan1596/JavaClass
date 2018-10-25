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
    <title>修改密码</title>
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
        <h1>修改密码</h1>
        <form action="../../../../Retrieve.do" method="get">
            <input type="text" name="name" id="name" title="" value="用户账号" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"/>
            <input type="text" name="password" id="password" title="" value="账号密码" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" />
            <input type="text" name="cpassword" id="cpassword" title="" value="确认密码" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"/>
            <%--<input type="text" name="name" title="name" value="用户账号" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '用户账号';}">--%>
            <%--<input type="text" name="password" title="password" value="账号密码" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '账号密码';}">--%>
            <%--<input type="text" name="cpassword" title="cpassword" value="确认密码" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '确认密码';}">--%>
            <%--<input type="text" name="phone" title="phone" value="绑定手机" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '绑定手机';}">--%>
            <div class="forgot">
                <input type="submit" value="确认">
            </div>
        </form>
    </div>
    <div class="login-bottom">
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
