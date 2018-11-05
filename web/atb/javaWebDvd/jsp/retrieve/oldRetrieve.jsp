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
    <title>找回密码</title>
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
        <h1>找回密码</h1>
        <form action="../../../../atbRetrieve.do" method="post">
            <input type="text" name="name" id="name" title="" value="用户账号" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"
                   onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '用户账号';}"/>
            <input type="text" name="phone" id="phone" title="" value="绑定邮箱" onfocus="this.value = '';"
                   onblur="if (this.value == '') {this.value = '绑定邮箱';}">
            <input type="text" name="yzm" id="yzm" title="" value="验证码"
                   onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '验证码';}">
            <input type="hidden" name="yc" id="yc" title="<%=(int)((Math.random()*9+1)*1000)%>" value="<%=(int)((Math.random()*9+1)*1000)%>">
            <input type="button" onclick="Code()" value="获取验证码">
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
    function Code() {
        $.post("../../../../imageServlet.do");
        // var userEmail = $("[name=phone]").val();
        // var yc = $("[name=yc]").val();
        // $.post("../../../../atbemail.do?userEmail="+userEmail+"&yc="+yc);
    }
</script>
<div class="copyright">
    <p>安徽信息工程学院 2016级 JAVA①班 安天宝 JavaWeb项目作业</p>
</div>
</body>
</html>
