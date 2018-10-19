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
<div align='center'>
    <h3>----找回密码----</h3>
</div>
<form action="../../../../Retrieve.do" method="post">
    <table border="1" width="300" align="center">
        <tr>
            <th width=150px>用户账号：</th>
            <th><input name="name" type="text" title="" style="text-align: center; width: 150px;"/></th>
        </tr>
        <tr>
            <th width=150px>绑定手机：</th>
            <th><input name="phone" type="text" title="" style="text-align: center; width: 150px;"/></th>
        </tr>
        <tr>
            <th colspan = '1'><a href = '../../../javaWebDvdLogin.jsp'>返回</a></th>
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
