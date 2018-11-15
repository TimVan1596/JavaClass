<%--
  最终找回密码
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

    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script src="js/common-js.js"></script>
</head>
<body>

<%
    if (session.getAttribute("captchaEmail") == null) {
        response.sendRedirect("index.html");
        return;
    }
%>

<!--登录页面-->
<div id="login-page" class="login">
    <div class="login_title">
        <span>修改密码</span>
    </div>
    <div class="login_fields" style="width: 100%;">
        <div class="layui-form">
            <div class="login_fields" style="width: 100%;">
                <div class="login_fields__user">
                    <div class="icon">
                        <img src="img/biggerdvd/user_icon_copy.png">
                    </div>
                    <input type="text" name="name" id="user-name" value="<%=(String) session.getAttribute("captchaEmail")%>" disabled="disabled"
                           style="-webkit-box-shadow: 0 0 0px 1000px #32364a inset;width: 100%" autocomplete="off" >
                    <div class="validation">
                        <img src="img/biggerdvd/tick.png">
                    </div>

                </div>
                <div class="login_fields__password">
                    <div class="icon">
                        <img src="img/biggerdvd/lock_icon_copy.png">
                    </div>
                    <input placeholder="输入新密码" type="password"
                           autocomplete="off" id="user-password"
                           style="-webkit-box-shadow: 0 0 0px 1000px #32364a inset;width: 100%" name="password">
                    <div class="validation">
                        <img src="img/biggerdvd/tick.png">
                    </div>
                </div>
                <div class="login_fields__password">
                    <div class="icon">
                        <img src="img/biggerdvd/lock_icon_copy.png">
                    </div>
                    <input placeholder="重复新密码" type="password"
                           autocomplete="off" id="user-re-password"
                           style="-webkit-box-shadow: 0 0 0px 1000px #32364a inset;width: 100%" name="re-password">
                    <div class="validation">
                        <img src="img/biggerdvd/tick.png">
                    </div>
                </div>
                <br>
                <div class="login_fields__submit" style="position: initial">
                    <input type="submit" value="提交" id="login-submit"   onclick="resetPassword()">
                    <div class="forgot">
                        <a href="index.html">返回登录</a>
                    </div>
                </div>

            </div>

        </div>
        <div class="success">
            <h2>密码修改成功</h2>
            <p>返回首页</p>
        </div>
    </div>

    <script src="../common/util/layui/layui.js"></script>
    <script src="js/html/realresetpwd.js"></script>
</div>
</body>
</html>
