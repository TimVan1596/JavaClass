<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\10\9 0009
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>找回密码</title>
</head>
<body>
<h3>------找回密码------</h3>
<form action="../../Retrieve.do" method="post">
    <p>
        用户账号：<input name="name" type="text" style="text-align: left; width: 150px;"/>
    </p>
    <p>
        绑定手机：<input name="phone" type="text" style="text-align: left; width: 150px;"/>
    </p>
    <p>
        <input type="submit" value="提交" style="text-align: left;">
    </p>
    <p>
        <a href = '../../display.jsp'>返回</a>
    </p>
    <script type="text/javascript">
        if(<%= request.getAttribute("MSG")!=null %>){
            alert('<%=request.getAttribute("MSG") %>');
        }
    </script>
</form>
</body>
</html>
