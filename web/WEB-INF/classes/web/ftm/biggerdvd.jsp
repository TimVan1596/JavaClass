<%--
  Created by IntelliJ IDEA.
  User: TimVan
  Date: 2018/10/8
  Time: 8:09
  To change this template use File | Settings | File Templates.
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
    <title>BiggerDVD <%=VERSION%>
    </title>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js">
    </script>
    <script src="js/common-js.js">
    </script>
</head>
<body>
<h1>********** 欢迎来到BiggerDVD 管理系统 **************</h1>
<h3>**版本号 <%=VERSION %>
</h3>

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
                    alert("登录成功！");


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