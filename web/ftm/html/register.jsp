<%--
  注册页面
  User: TimVan
  Date: 2018-10-26 00:58:54
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BiggerDVD - 注册</title>

    <link rel="stylesheet"
          href="../../common/util/layui/css/layui.css"
          media="all">
    <link rel="stylesheet" type="text/css" href="../style/html/biggerdvd/default.css">
    <link rel="stylesheet" type="text/css" href="../style/html/biggerdvd/styles.css">
    <style>
        body{
            background: linear-gradient(135deg, #EA5C54 0%, #bb6dec 100%);
        }
    </style>

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



    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="../../common/util/layui/layui.js"></script>
    <script src="../js/common-js.js"></script>
</head>
<body>

<!--登录页面-->
<div id="login-page" class="login">
    <div class="login_title">
        <span>注册账号</span>
    </div>
    <div class="login_fields" style="width: 100%;">
        <div class="login_fields__user">
            <div class="icon">
                <img src="../img/biggerdvd/user_icon_copy.png">
            </div>
            <input placeholder="用户名" type="text" name="name" id="user-name"
                   style="-webkit-box-shadow: 0 0 0px 1000px #32364a inset;width: 100%" autocomplete="off" >
            <div class="validation">
                <img src="../img/biggerdvd/tick.png">
            </div>

        </div>
        <div class="login_fields__password">
            <div class="icon">
                <img src="../img/biggerdvd/lock_icon_copy.png">
            </div>

            <input placeholder="请填写您的邮箱" type="email"
                   autocomplete="off" id="user-email"
                   style="-webkit-box-shadow: 0 0 0px 1000px #32364a inset;width: 100%" name="email">
            <div class="validation">
                <img src="../img/biggerdvd/tick.png">
            </div>
        </div>
        <div class="login_fields__password">
            <div class="icon">
                <img src="../img/biggerdvd/lock_icon_copy.png">
            </div>

            <input placeholder="密码" type="password"
                   autocomplete="off" id="user-password"
                   style="-webkit-box-shadow: 0 0 0px 1000px #32364a inset;width: 100%" name="password">
            <div class="validation">
                <img src="../img/biggerdvd/tick.png">
            </div>
        </div>
        <div class="login_fields__password">
            <div class="icon">
                <img src="../img/biggerdvd/lock_icon_copy.png">
            </div>
            <input placeholder="重复密码" type="password"
                   autocomplete="off" id="user-re-password"
                   style="-webkit-box-shadow: 0 0 0px 1000px #32364a inset;width: 100%" name="re-password">
            <div class="validation">
                <img src="../img/biggerdvd/tick.png">
            </div>
        </div>
        <div class="login_fields__submit">
            <input type="submit" value="提交" id="login-submit"   onclick="register()">
            <div class="forgot">
                <a href="../index.html">返回登录</a>
            </div>
        </div>
    </div>
    <div class="success">
        <h2>注册成功</h2>
        <p>欢迎加入</p>
    </div>
    <div class="disclaimer">
        <p>© 2018-2019 TimVanX.com 版权所有 皖ICP备18002720号 .</p>
    </div>
</div>
<%--加载页面--%>
<div class="authent">
    <img src="../img/biggerdvd/puff.svg">
    <p>注册中...</p>
</div>

<script src = "../js/html/register.js"></script>


</body>
</html>
