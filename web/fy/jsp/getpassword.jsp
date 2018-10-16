<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/10 0010
  Time: 19:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>找回密码</title>
</head>
<body>

<div style="text-align: center">
    <h4>找回密码</h4>
<form action="/fy/servlet/toGetPassWord" method="get" onsubmit="return returnPassword()">
    请输入账号：<input type=" text" name="username" id="returnName"/><br>
    <input type="submit" value="提交"/>
</form>
</div>
    <script>
        function returnPassword(){
            var  name=document.getElementById("returnName").value;
            if(name!=""){
                return true;
            }else{
                alert("亲，账号不能为空哦~");
                return false;
            }

        }
    </script>
</body>
</html>
