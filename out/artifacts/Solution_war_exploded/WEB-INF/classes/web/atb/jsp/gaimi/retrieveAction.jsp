<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\10\10 0010
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密码</title>
</head>
<body>
<h3>------修改密码------</h3>
<form action="Retrieve.do" method="get">
    <p>
        用户账号：<input name="name" type="text" value="<%=request.getAttribute("name")%>" readonly style = "color:gray" style="text-align: left; width: 100px;"/>
    </p>
    <p>
        账号密码：<input name="password" type="password" style="text-align: left; width: 150px;"/>
    </p>
    <p>
        确认密码：<input name="cpassword" type="password" style="text-align: left; width: 150px;"/>
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
