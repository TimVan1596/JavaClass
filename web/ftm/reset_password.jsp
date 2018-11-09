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
    <link rel="stylesheet" type="text/css" href="style/verify.css">
    <style>
        body {
            background: linear-gradient(135deg, #EA5C54 0%, #bb6dec 100%);
        }

        /*字体图标的css*/
        @font-face {font-family: "iconfont";
            src: url('fonts/iconfont.eot?t=1508229193188'); /* IE9*/
            src: url('fonts/iconfont.eot?t=1508229193188#iefix') format('embedded-opentype'), /* IE6-IE8 */
            url('data:application/x-font-woff;charset=utf-8;base64,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') format('woff'),
            url('fonts/iconfont.ttf?t=1508229193188') format('truetype'), /* chrome, firefox, opera, Safari, Android, iOS 4.2+*/
            url('fonts/iconfont.svg?t=1508229193188#iconfont') format('svg'); /* iOS 4.1- */
        }

        .iconfont {
            font-family:"iconfont" !important;
            font-size:16px;
            font-style:normal;
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
        }

        .icon-check:before { content: "\e645"; }

        .icon-close:before { content: "\e646"; }

        .icon-right:before { content: "\e6a3"; }

        .icon-refresh:before { content: "\e6a4"; }
    </style>

    <style type="text/css">
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
    <script src="js/verify.min.js"></script>
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
        <div id="mpanel1" style="position: relative;"></div>

        <div class="login_fields__submit">
            <input type="submit" value="提交" id="login-submit" onclick="resetPassword()">
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
