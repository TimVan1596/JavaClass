<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/1/001
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>JQuery实现登录注册</title>
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript">
        $(function() {
            $("#send").click(function () {
                var url = "Reg.do?";
                var pusername="userName="+$("#userName").val();
                var puserpassword="userPassword="+$("#userpassword").val();
                url = url+pusername+"&"+puserpassword;
                $.get(url,callback);
            });
        });

        function callback(data,status) {
            alert(data+":"+status);
            $("msg").html(data);
        }
    </script>
</head>
<body>
账号:<input id="userName" type="text"/>
<br>
密码：<input id="userpassword" type="password"/>
<br>
<input id="send" type="button" value="注册"/>

<div id="msg"></div>
</body>
</html>
