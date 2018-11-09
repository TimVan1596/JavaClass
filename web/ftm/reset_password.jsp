<%--
  找回密码
  User: TimVan
  Date: 2018-10-26 00:58:54
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BiggerDVD - 找回密码</title>
    <link rel="stylesheet" type="text/css" href="style/html/biggerdvd/default.css">
    <link rel="stylesheet" type="text/css" href="style/html/biggerdvd/styles.css">
    <link rel="stylesheet" type="text/css" href="style/html/reset_password.css">
    <link rel="stylesheet" type="text/css" href="fonts/iconfont.css">

    <style>


        :root .fdad {
            display: none !important;
        }

        input::-webkit-input-placeholder {
            color: white !important; /* WebKit browsers */
        }

        input:-moz-placeholder {
            color: white !important; /* Mozilla Firefox 4 to 18 */
        }

        input::-moz-placeholder {
            color: white !important; /* Mozilla Firefox 19+ */
        }

        input:-ms-input-placeholder {
            color: white !important; /* Internet Explorer 10+ */
        }
    </style>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="js/common-js.js"></script>
</head>
<body>

<!--登录页面-->
<div id="login-page" class="login">
    <div class="login_title">
        <span>修改密码</span>
    </div>
    <div class="login_fields" style="width: 100%;">
        <div class="login_fields__user">
            <div class="icon">
                <img src="img/biggerdvd/user_icon_copy.png">
            </div>
            <input placeholder="输入您要找回的邮箱地址" type="text" name="name" id="user-name"
                   style="-webkit-box-shadow: 0 0 0px 1000px #32364a inset;width: 100%" autocomplete="off">
            <div class="validation">
                <img src="img/biggerdvd/tick.png">
            </div>

        </div>
        <div id="slide_box">
            <div id="slide_xbox">
                <div id="btn">
                    <i class="iconfont icon-double-right"></i>
                    <img src="" alt="">
                </div>
            </div>
            请按住滑块，拖动到最右边
        </div>


        <div class="login_fields__submit">
            <input class="resetpwd-login-submit" type="submit" value="提交" id="login-submit" onclick="resetPassword()">
            <div class="forgot">
                <a href="index.html">返回登录</a>
            </div>
        </div>
    </div>
    <div class="success">
        <h2>密码修改成功</h2>
        <p>返回首页</p>
    </div>
    <div class="disclaimer">
        <p>© 2018-2019 TimVanX.com 版权所有 皖ICP备18002720号 .</p>
    </div>
</div>

<script src="js/html/reset_password.js"></script>
</body>
</html>
