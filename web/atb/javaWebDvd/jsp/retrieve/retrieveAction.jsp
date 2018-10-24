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
            cursor: pointer;
        }
    </style>
</head>
<body>
<div align='center'>
    <h2>----修改密码----</h2>
    <h6 style="color: red">账号和密码为字母和数字组成</h6>
</div>
<form action="../../../../Retrieve.do" method="get">
    <table border="1" width="300" align="center">
        <tr>
            <th width=150px>用户账号：</th>
            <th><input name="name" type="text" title="" value="<%=request.getAttribute("name")%>" readonly style = "color:gray; text-align: center; width: 150px;"/></th>
        </tr>
        <tr>
            <th width=150px>账号密码：</th>
            <th><input name="password" type="password" title="" style="text-align: center; width: 150px;"
                       onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"/></th>
        </tr>
        <tr>
            <th width=150px>确认密码：</th>
            <th><input name="cpassword" type="password" title="" style="text-align: center; width: 150px;"
                       onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"/></th>
        </tr>
        <tr>
            <th colspan = '1'></th>
            <th colspan = '1'><button type="submit" class="button">提交</button></th>
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
