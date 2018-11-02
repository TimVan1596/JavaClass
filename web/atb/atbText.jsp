<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\11\1 0001
  Time: 15:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>日期</title>
    <style type="text/css">
        h1{
            font-family: 微软雅黑;
            color: red;
        }
        div{
            font-size: 20px;
            color: #00aeef;
        }
        button {
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
<script type="text/javascript">
    function press() {
        document.getElementById("timeShow").innerHTML = "安天宝";
    }
    var t = null;
    function time(){
        dt = new Date();
        document.getElementById("timeShow").innerHTML=dt;
        t = setTimeout(time,1000);
    }
    window.onload=function(){time()}
</script>
<h1>当前日期</h1>
<div id="timeShow"></div>
<button onclick="press()">显示名字</button>
</body>
</html>
