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
    <h2>----找回密码----</h2>
    <h6 style="color: red">账号为字母和数字组成，手机号格式（十一位）：13+任意数,15+除4的任意数,18+除1和4的任意数,17+除9的任意数,147</h6>
</div>
<form action="../../../../atbRetrieve.do" method="post">
    <table border="1" width="300" align="center">
        <tr>
            <th width=150px>用户账号：</th>
            <th><input name="name" type="text" title="" style="text-align: center; width: 150px;"
                       onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"/></th>
        </tr>
        <tr>
            <th width=150px>绑定手机：</th>
            <th><input name="phone" type="text" title="" style="text-align: center; width: 150px;"
                       onkeyup="this.value=this.value.replace(/\D/g,'')"/></th>
        </tr>
        <tr>
            <%
                if(request.getAttribute("FH") == null){
            %>
            <th colspan = '1'><a href = '../../../javaWebDvdLogin.jsp'>返回</a></th>
            <%
            }else{
            %>
            <th colspan = '1'><a href = './atb/javaWebDvdLogin.jsp'>返回</a></th>
            <%
                }
            %>
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
