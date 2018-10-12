<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\10\12 0012
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查看DVD</title>
</head>
<body>
<form action="check.do" method="post">
    <p>

    </p>
    <p>
        <input type="text" name="name" title="name" style="text-align: left; width: 150px;"/>
        <input type="submit" value="搜索" style="text-align: left;">
    </p>
    <div>
        <a style="margin-right: 70px;text-decoration-line: none;color: blue;" href="../../choice.jsp">返回</a>
        <a style="text-decoration-line: none;color: blue;" href="../../login.jsp">退出</a>
    </div>
    <script type="text/javascript">
        if(<%= request.getAttribute("MSG")!=null %>){
            alert('<%=request.getAttribute("MSG") %>');
        }
    </script>
</form>
</body>
</html>
