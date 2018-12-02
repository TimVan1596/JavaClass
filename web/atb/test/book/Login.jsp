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
    <title>webTest</title>
    <style>
        .button {
            background-color: #4183c4;
            border: none;
            color: white;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            width: 150px;
            height: 30px;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<form action="../../webTestLogin.do" method="post">
    <table border="1" width="300" align="center">
        <tr>
            <th width=150px>账号：</th>
            <th><input type="text" name="username" title="username"
                       style="text-align: center; width: 150px;"/></th>
        </tr>
        <tr>
            <th width=150px>密码：</th>
            <th><input type="password" name="userpassword" title="userpassword"
                       style="text-align: center; width: 150px;"/></th>
        </tr>
        <tr>
            <th colspan = '2'>
                <button type="submit" class="button">登陆</button>
            </th>
        </tr>
    </table>
</form>
<script type="text/javascript">
    if(<%= request.getAttribute("MSG")!=null %>){
        alert('<%=request.getAttribute("MSG") %>');
    }
</script>
</body>
</html>
