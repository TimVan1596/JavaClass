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
<div align='center'>
    <h3>----修改密码----</h3>
</div>
<form action="../../../../Retrieve.do" method="get">
    <table border="1" width="300" align="center">
        <tr>
            <th width=150px>用户账号：</th>
            <th><input name="name" type="text" title="" value="<%=request.getAttribute("name")%>" readonly style = "color:gray; width: 150px;"/></th>
        </tr>
        <tr>
            <th width=150px>账号密码：</th>
            <th><input name="password" type="password" title="" style="width: 150px;"/></th>
        </tr>
        <tr>
            <th width=150px>确认密码：</th>
            <th><input name="cpassword" type="password" title="" style="width: 150px;"/></th>
        </tr>
        <tr>
            <th colspan = '1'><a href = '../../login.jsp'>返回</a></th>
            <th colspan = '1'><input type="submit" value="提交" ></th>
        </tr>
        <script type="text/javascript">
            if(<%= request.getAttribute("MSG")!=null %>){
                alert('<%=request.getAttribute("MSG") %>');
            }
        </script>
    </table>
</form>
</body>
</html>
