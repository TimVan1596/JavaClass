<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/12 0012
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加DVD</title>
</head>
<style>
    .button {
        background-color: #008CBA;
        border-radius: 4px;
        border: none;
        color: white;
        padding: 10px 20px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 10px;
        margin: 4px 2px;
        cursor: pointer;
    }
</style>
<body>
<form action="/fy/servlet/toAddDvd" method="get" enctype="multipart/form-data" onsubmit="return check()">
    <br>
DVD名称：<input style="margin-top: 10px" type=" text" name="dvdname" id="dvdId"/><br>
    <input type="file" name="fname" /><br>
<input type="submit" class="button" value="添加">
</form>

<script>
    function check(){
        //1.取值
        var  name=document.getElementById("dvdId").value;
        //2.判断
        if(name!=""){
            return true;
        }else{
            alert("亲，DVD名称不能为空哦~");
            return false;
        }
    }
</script>
</body>
</html>
