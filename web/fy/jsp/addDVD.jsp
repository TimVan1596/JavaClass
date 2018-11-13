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
<form action="/fy/servlet/toAddDvd" method="post" enctype="multipart/form-data" onsubmit="return check()">
    <fieldset>
        <legend>添加</legend>
     DVD名称：<input style="margin-top: 10px" type="text" name="dvdname" id="dvdId"/><br>
    <input type="file" name="file" id="file0"/><br>
        <img src="" id="img0" style="width:100px;height:100px;"><br>
    <span style="color: red">PS:如果不加图片，将会添加默认图片</span>
    <input type="submit" class="button" value="添加">

    </fieldset>
</form>

<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<script>
    $("#file0").change(function(){
        var objUrl = getObjectURL(this.files[0]) ;//获取文件信息
        console.log("objUrl = "+objUrl);
        if (objUrl) {
            $("#img0").attr("src", objUrl);
        }
    }) ;

    function getObjectURL(file) {
        var url = null;
        if (window.createObjectURL != undefined) {
            url = window.createObjectURL(file);
        } else if (window.URL != undefined) { // mozilla(firefox)
            url = window.URL.createObjectURL(file);
        } else if (window.webkitURL != undefined) { // webkit or chrome
            url = window.webkitURL.createObjectURL(file);
        }
        return url;
    }

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
