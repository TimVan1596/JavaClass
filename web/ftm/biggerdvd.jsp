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
        :root .fdad {
            display: none !important;
        }

        input::-webkit-input-placeholder {
            color: white !important;    /* WebKit browsers */
        }

        input:-moz-placeholder {
            color: white !important;   /* Mozilla Firefox 4 to 18 */
        }

        input::-moz-placeholder {
            　　color: white !important;   /* Mozilla Firefox 19+ */
        }

        input:-ms-input-placeholder {
            color: white !important;   /* Internet Explorer 10+ */
        }
    </style>

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
                <img src="./带CSS3动画特效的时尚登录界面UI设计_files/user_icon_copy.png">
            </div>
            <input placeholder="用户名" type="text" style="-webkit-box-shadow: 0 0 0px 1000px #32364a inset;" autocomplete="off" >
            <div class="validation">
                <img src="./带CSS3动画特效的时尚登录界面UI设计_files/tick.png">
            </div>

        </div>
        <div class="login_fields__password">
            <div class="icon">
                <img src="./带CSS3动画特效的时尚登录界面UI设计_files/lock_icon_copy.png">
            </div>
            <input placeholder="密码" type="password" autocomplete="off" style="-webkit-box-shadow: 0 0 0px 1000px #32364a inset;" >
            <div class="validation">
                <img src="./带CSS3动画特效的时尚登录界面UI设计_files/tick.png">
            </div>
        </div>
        <div class="login_fields__submit">
            <input type="submit" value="登录">
            <div class="forgot">
                <a href="http://www.jq22.com/demo/css3signin20160815/#">忘记密码?</a>
            </div>
        </div>
    </div>
    <div class="success">
        <h2>认证成功</h2>
        <p>欢迎回来</p>
    </div>
    <div class="disclaimer">
        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce semper laoreet placerat. Nullam semper auctor
            justo, rutrum posuere odio vulputate nec.</p>
    </div>
</div>
<div class="authent">
    <img src="./带CSS3动画特效的时尚登录界面UI设计_files/puff.svg">
    <p>认证中...</p>
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


</body>
</html>