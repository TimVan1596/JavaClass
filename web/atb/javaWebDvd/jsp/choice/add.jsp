<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\10\12 0012
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增界面</title>
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
    <h2>----添加DVD----</h2>
    <h6 style="color: red">库存需为数字，图片格式支持png/jpg/gif/bmp</h6>
</div>
<form action="../../../../atbadd.do" method="post" enctype="multipart/form-data">
    <table border="1" width="300" align="center">
        <tr>
            <th width=150px>书名：</th>
            <th><input type="text" name="name" title="书名" style="text-align: center; width: 150px;"/></th>
        </tr>
        <tr>
            <th width=150px>库存：</th>
            <th><input type="text" name="state" title="库存" style="text-align: center; width: 150px;"
                       onkeyup="this.value=this.value.replace(/\D/g,'')"/></th>
        </tr>
        <tr>
            <th width=150px>图片：</th>
            <th><input type="file" name="file" style="text-align: center; width: 150px;"/></th>
        </tr>
        <tr>
            <th colspan = '1'><a href = '../../../../atblogin.do'>返回</a></th>
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
