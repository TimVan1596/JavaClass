<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\10\8 0008
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DVD管理系统</title>
</head>
<body>
<h3>------欢迎进入DVD Mgr 6.0 管理系统------</h3>
<form action="login.do" method="post">
    <p>
        账号：<input type="text" name="username" title="username" style="text-align: left; width: 150px;"/>
    </p>
    <p>
        密码：<input type="password" name="userpassword" title="userpassword" style="text-align: left; width: 150px;"/>
    </p>
    <div>
        <a style="margin-right: 70px;text-decoration-line: underline;color: blue;" href="jsp/gaimi/retrieve.jsp">找回密码</a>
        <a style="text-decoration-line: underline;color: blue;" href="jsp/zhuce/register.jsp">注册账号</a>
    </div>
    <p>
        <input type="submit" value="登陆" style="text-align: left;">
    </p>
    <script type="text/javascript">
        if(<%= request.getAttribute("MSG")!=null %>){
            alert('<%=request.getAttribute("MSG") %>');
        }
    </script>
</form>
</body>
</html>
