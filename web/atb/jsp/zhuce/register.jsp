<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\10\9 0009
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
</head>
<body>
<h3>------欢迎来到注册界面------</h3>

<form action="Register.do" method="post">
    <p>
        用户账号：<input name="name" type="text" style="text-align: left; width: 150px;"/>
    </p>
    <p>
        账号密码：<input name="password" type="password" style="text-align: left; width: 150px;"/>
    </p>
    <p>
        确认密码：<input name="cpassword" type="password" style="text-align: left; width: 150px;"/>
    </p>
    <p>
        绑定手机：<input name="phone" type="text" style="text-align: left; width: 150px;"/>
    </p>
    <p>
        <input type="submit" value="提交" style="text-align: left;">
    </p>
    <script type="text/javascript">
        if(<%= request.getAttribute("MSG")!=null %>){
            alert('<%=request.getAttribute("MSG") %>');
        }
    </script>
</form>
</body>
</html>