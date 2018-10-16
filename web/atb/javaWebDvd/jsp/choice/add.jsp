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
</head>
<body>
<div align='center'>
    <h3>----添加DVD----</h3>
</div>
<form action="../../../../add.do" method="post">
    <table border="1" width="300" align="center">
        <tr>
            <th width=150px>书名：</th>
            <th><input type="text" name="name" title="name" style="text-align: left; width: 150px;"/></th>
        </tr>
        <tr>
            <th colspan = '1'><a href = '../../../../login.do'>返回</a></th>
            <th colspan = '1'><input type="submit" value="提交" ></th>
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
