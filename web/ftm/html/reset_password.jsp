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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js">
    </script>
    <script src="../js/common-js.js">
    </script>
</head>
<body>
<h1>修改密码</h1>

<form>
    <p>
        输入账号：<input name="name" type="text" title="name" value="" leipiplugins="text" orghide="0" orgalign="left"
                    orgwidth="150" orgtype="text" style="text-align: left; width: 150px;"/>
    </p>
    <p>
        输入新密码：<input name="password" type="password" title="password" value="" leipiplugins="text" orghide="0"
                     orgalign="left" orgwidth="150" orgtype="text" style="text-align: left; width: 150px;"/>
    </p>
    <p>
        重复新密码：<input name="re-password" type="password"
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


    //注册账号
    function register() {

        var name = $("[name=name]").val();
        var password = $("[name=password]").val();
        var rePassword = $("[name=re-password]").val();

        if (password !== rePassword) {
            alert("两次输入的密码不同！");
        }
        else {
            //通过ajax检查是否正常登录
            $.post('../resetPassword.do', {
                name: name,
                password: password
            }, function (ret) {
                //解析ret
                ret = eval("(" + ret + ")");

                if (ret['error'] === 0) {
                    alert("密码修改成功！");
                    window.location.href = '../index.html';

                } else if (ret['error'] === 1) {
                    var errorInfo = ret['errorInfo'];
                    alert("修改失败！" + errorInfo);
                    location.reload();
                }


            });
        }

    }
</script>

</body>
</html>
