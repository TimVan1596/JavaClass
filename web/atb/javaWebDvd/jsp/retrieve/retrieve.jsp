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
    <%String FH = (String) request.getAttribute("FH");
    if(FH == null){%>
    <link href="../../css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <%}else{%>
    <link href="./atb/javaWebDvd/css/style.css" rel="stylesheet" type="text/css" media="all"/>
    <%}%>
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
        <%if(FH == null){%>
        <form action="../../../../atbRetrieve.do" method="post">
                <%}else{%>
            <form action="./atbRetrieve.do" method="post">
                <%}%>
                <input type="text" name="name" id="name" title="" value="绑定邮箱"
                       onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '绑定邮箱';}"/>
                <input type="text" name="yzm" id="yzm" title="" value="验证码"
                       onfocus="this.value = '';" onblur="if (this.value == '') {this.value = '验证码';}">
                <input type="hidden" name="yc" id="yc" title="<%=(int)((Math.random()*9+1)*1000)%>" value="<%=(int)((Math.random()*9+1)*1000)%>">
                <input type="button" onclick="Code()" value="获取验证码">
                <div class="forgot">
                    <%if(FH == null){%>
                    <a href = '../../../javaWebDvdLogin.jsp'>返回</a>
                    <%}else{%>
                    <a href = './atb/javaWebDvdLogin.jsp'>返回</a>
                    <%}%>
                    <input type="submit" value="确认">
                </div>
            </form>
    </div>
    <div class="login-bottom">
        <h3><span id="tishi1"></span></h3>
    </div>
</div>
<script type="text/javascript">
    if(<%= request.getAttribute("MSG")!=null %>){
        alert('<%=request.getAttribute("MSG") %>');
    }
</script>
<%if(FH == null){%>
<script src="./jquery-3.3.1.min.js" type="text/javascript"></script>
<%}else{%>
<script src="./atb/javaWebDvd/jsp/register/jquery-3.3.1.min.js" type="text/javascript"></script>
<%}%>
<script>
    //获取验证码
    function Code() {
        var userEmail = $("[name=name]").val();
        userEmail = $.trim(userEmail);
        var yc = $("[name=yc]").val();
        yc = $.trim(yc);
        <%if(FH == null){%>
        var url = "../../../../atbEmail.do?";
        // $.post("../../../../atbEmail.do?userEmail="+userEmail+"&yc="+yc);
        <%}else{%>
        var url = "./atbEmail.do?";
        // $.post("./atbEmail.do?userEmail="+userEmail+"&yc="+yc);
        <%}%>
        userEmail="userEmail="+userEmail;
        yc="&yc="+yc;
        url = url + userEmail + yc;
        //$.get()方法能够返回一个JQuery XMLHttpRequest对象
        $.post(url, callback);
    }
    //先将提示隐藏起来
    $("#tishi1").hide();
    $(function() {
        //当输入注册名的输入框获得焦点后就先隐藏提示语
        $("#name").focus(function cls() {
            $("#tishi1").hide();
        });
        //当输入框失去焦点就通过AJAX将数据传递给后端，在后端验证是否已存在该用户名
        $("#name").blur(
            function() {
                var name = $(this).val();
                name = $.trim(name);
                if (name != "") {
                    <%if(FH == null){%>
                    var url = "../../../../atbdelete.do?";
                    <%}else{%>
                    var url = "./atbdelete.do?";
                    <%}%>
                    name="name="+name;
                    url = url + name;
                    //$.get()方法能够返回一个JQuery XMLHttpRequest对象
                    var jqxhr = $.get(url, callback);
                    //若执行JQuery出现错误则提示错误信息
                    //在JQuery3.0以后需要用done()、fail()、alwayls()代替success()、error()、complete();
                    jqxhr.fail(function(xhr, error, throwerror) {
                        // alert("error" + xhr.status + " error=" + error + " throwerror:" + throwerror);
                    });
                }
            });
    });
    //ajax的回调函数
    function callback(data, status) {
        $("#tishi1").show();
        $("#tishi1").html(data);
    }
</script>
<div class="copyright">
    <p>安徽信息工程学院 2016级 JAVA①班 安天宝 JavaWeb项目作业</p>
</div>
</body>
</html>
