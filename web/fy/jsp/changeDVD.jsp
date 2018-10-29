<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/12 0012
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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
    <%
        String id=request.getParameter("id");
        String name=request.getParameter("name");
        name=name.substring(1,name.length()-1);
        String state=request.getParameter("state");

        %>
</head>
<body>
<form action="/fy/servlet/toChangeDvd" method="get" onsubmit="return check()">
    <fieldset>
        <legend>修改</legend>
    <br>
    你选择要修改的DVD编号<input style="color:	#D0D0D0" type=" text" name="dvdid" value="<%=id%>" id="dvdId" readonly="readonly"/><br>
    请输入修改后的DVD名称<input type=" text" name="dvdname" value="<%=name%>" id="dvdName"/><br>
    请选择修改后的DVD状态
  <select name="dvdstate">
    <option>可以借</option>
    <option>已借出</option>
</select><br>
    <input type="submit" class="button" value="修改" />
    </fieldset>
</form>
<script>
    function check(){
        var id=document.getElementById("dvdId").value;
        var name=document=document.getElementById("dvdName").value;
            if(id==""){
               alert("DVD编号不能为空");
               return false;
            }else{
                if(name==""){
                    alert("DVD名称不能为空");
                    return false;
                }
                return true;
             }

    }
</script>
</body>
</html>
