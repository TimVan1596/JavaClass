<%--
  入口页面
  User: TimVan
  Date: 2018/10/8
  Time: 8:09
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>


<%@ page import="com.timvanx.biggerdvd.util.Constants" %>

<%!
    //初始化常量信息
    String VERSION = Constants.VERSION;

%>

<html>
<head>
    <title>BiggerDVD <%=VERSION%></title>

    <link rel="stylesheet" type="text/css"
          href="style/html/biggerdvd/default.css">

    <link rel="stylesheet" type="text/css"
          href="style/html/biggerdvd/styles.css">
    <!--[if IE]>
    <script src="http://libs.baidu.com/html5shiv/3.7/html5shiv.min.js"></script>
    <![endif]-->
    <style type="text/css">
        :root .fdad,
        :root .adsbygoogle,
        :root .container > a.mid-wrapper,
        :root #container > a.item,
        :root #container > a.block,
        :root #container > .wrapper > .fl,
        :root #container > .item.title_top,
        :root #container > .item.title_normal,
        :root #container > #pic_container,
        :root .adinfo
        { display: none !important; }</style>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js">
    </script>
    <script src="js/common-js.js"></script>
    <script src="js/html/biggerdvd/jquery-ui.min.js"></script>
    <script src="js/html/biggerdvd/stopExecutionOnTimeout.js"></script>
</head>
<body>
<h1>********** 欢迎来到BiggerDVD 管理系统 **************</h1>
<h3>**版本号 <%=VERSION %>
</h3>

<div class="login">
    <div class="login_title">
        <span>账号登录</span>
    </div>
    <div class="login_fields">
        <div class="login_fields__user">
            <div class="icon">
                <img src="img/biggerdvd/user_icon_copy.png">
            </div>
            <input placeholder="用户名" type="text">
            <div class="validation">
                <img src="img/biggerdvd/tick.png">
            </div>

        </div>
        <div class="login_fields__password">
            <div class="icon">
                <img src="img/biggerdvd/lock_icon_copy.png">
            </div>
            <input placeholder="密码" type="password">
            <div class="validation">
                <img src="img/biggerdvd/tick.png">
            </div>
        </div>
        <div class="login_fields__submit">
            <input type="submit" value="登录">
            <div class="forgot">
                <a href="#">忘记密码?</a>
            </div>
        </div>
    </div>
    <div class="success">
        <h2>认证成功</h2>
        <p>欢迎回来</p>
    </div>
    <div class="disclaimer">
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce semper laoreet placerat. Nullam semper auctor justo, rutrum posuere odio vulputate nec.</p>
    </div>
</div>

<form>
    <p>
        账号：<input name="name" type="text" title="name" value="" leipiplugins="text" orghide="0" orgalign="left"
                  orgwidth="150" orgtype="text" style="text-align: left; width: 150px;"/>
    </p>
    <p>
        密码：<input name="password" type="password" title="password" value="" leipiplugins="text" orghide="0"
                  orgalign="left" orgwidth="150" orgtype="text" style="text-align: left; width: 150px;"/>
    </p>

    <div>
        <a style="margin-right: 50px;
    text-decoration-line: underline;
    color: blue;" href="html/reset_password.jsp">找回密码</a>
        <a style="text-decoration-line: underline;
    color: blue;" href="html/register.jsp">注册账号</a>
    </div>

    <p>
        <br/>
        <input type="button" value="登录" onclick="checkAccount()">
    </p>

    <h3 id="show1" style="color: red">

    </h3>
</form>

<script>
    //验证登录
    function checkAccount() {


        var name = $("[name=name]").val();
        var password = $("[name=password]").val();

        //非空判断
        if (isNull(name) || isNull(password)) {
            alert("用户名或密码不能为空！");
        }
        else {
            //通过ajax检查是否正常登录
            $.post('AccountCheckServlet.do', {
                name: name,
                password: password
            }, function (ret) {
                //解析ret
                ret = eval("(" + ret + ")");

                if (ret['error'] === 0) {
                    window.location.href = 'html/menu.jsp';

                } else if (ret['error'] === 1) {
                    var errorInfo = ret['errorInfo'];
                    alert("登录失败！" + errorInfo);
                    location.reload();
                }


            });
        }


    }
</script>

<script>
    $('input[type="submit"]').click(function () {
        $('.login').addClass('test');
        setTimeout(function () {
            $('.login').addClass('testtwo');
        }, 300);
        setTimeout(function () {
            $('.authent').show().animate({ right: -320 }, {
                easing: 'easeOutQuint',
                duration: 600,
                queue: false
            });
            $('.authent').animate({ opacity: 1 }, {
                duration: 200,
                queue: false
            }).addClass('visible');
        }, 500);
        setTimeout(function () {
            $('.authent').show().animate({ right: 90 }, {
                easing: 'easeOutQuint',
                duration: 600,
                queue: false
            });
            $('.authent').animate({ opacity: 0 }, {
                duration: 200,
                queue: false
            }).addClass('visible');
            $('.login').removeClass('testtwo');
        }, 2500);
        setTimeout(function () {
            $('.login').removeClass('test');
            $('.login div').fadeOut(123);
        }, 2800);
        setTimeout(function () {
            $('.success').fadeIn();
        }, 3200);
    });
    $('input[type="text"],input[type="password"]').focus(function () {
        $(this).prev().animate({ 'opacity': '1' }, 200);
    });
    $('input[type="text"],input[type="password"]').blur(function () {
        $(this).prev().animate({ 'opacity': '.5' }, 200);
    });
    $('input[type="text"],input[type="password"]').keyup(function () {
        if (!$(this).val() == '') {
            $(this).next().animate({
                'opacity': '1',
                'right': '30'
            }, 200);
        } else {
            $(this).next().animate({
                'opacity': '0',
                'right': '20'
            }, 200);
        }
    });
    var open = 0;
    $('.tab').click(function () {
        $(this).fadeOut(200, function () {
            $(this).parent().animate({ 'left': '0' });
        });
    });
</script>

</body>
</html>