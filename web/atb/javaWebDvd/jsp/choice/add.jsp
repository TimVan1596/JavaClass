<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\10\12 0012
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增界面</title>
</head>
<body>
<h3>------添加DVD------</h3>
<form action="../../../../add.do" method="post">
    <p>
        书名：<input type="text" name="name" title="name" style="text-align: left; width: 150px;"/>
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
