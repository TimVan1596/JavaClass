<%--
  Created by IntelliJ IDEA.
  User: r
  Date: 2018/10/8
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册账户</title>
</head>
<body>
<h1>注册账号</h1>

<form>
    <p>
        输入账号：<input name="name" type="text" title="name" value="" leipiplugins="text" orghide="0" orgalign="left"
                    orgwidth="150" orgtype="text" style="text-align: left; width: 150px;"/>
    </p>
    <p>
        输入密码：<input name="password" type="password" title="password" value="" leipiplugins="text" orghide="0"
                    orgalign="left" orgwidth="150" orgtype="text" style="text-align: left; width: 150px;"/>
    </p>
    <p>
        重复密码：<input name="re-password" type="password"
                    title="re-password" value=""
                    leipiplugins="text" orghide="0"
                    orgalign="left" orgwidth="150" orgtype="text" style="text-align: left; width: 150px;"/>
    </p>

    <p>
        <br/>
        <input type="button" value="提交"
               onclick="register()">
    </p>
</form>

<script>
    function register() {
        alert("提交信息")
    }
</script>

</body>
</html>
