<%@ page import="com.antianbao.javaWebDvd.dvd.JDBCUtilDvd" %>
<%@ page import="com.antianbao.javaWebDvd.dvd.Dvd" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\10\12 0012
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改信息</title>
</head>
<body>
<div align='center'>
    <h2>----修改信息----</h2>
    <h6 style="color: red">修改图片请选择新图片，若不选择则图片不变</h6>
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
</div>
<form action="../../../../modify.do" method="post" enctype="multipart/form-data">
    <table border="1" width="300" align="center">
        <%
            //遍历结果集
            JDBCUtilDvd jdbcUtilDvd = new JDBCUtilDvd();
            List<Dvd> dvds = jdbcUtilDvd.queryStu();
            for (Dvd dvd : dvds) {
                if(Integer.parseInt(request.getParameter("no")) == dvd.getNo()){
        %>
        <tr>
            <th width=150px>序号：</th>
            <th>
                <input name="no" type="text" title=""
                       value="<%= Integer.parseInt(request.getParameter("no")) %>"
                       readonly style = "text-align: center; width: 150px; color:gray;"/>
            </th>
        </tr>
        <tr>
            <th width=150px>图片：</th>
            <th>
                <img src="../../image/<%=dvd.getImage()%>" width="60px" height="50px">
                <input type="file" name="file" style="text-align: center; width: 150px;" value="<%= dvd.getImage() %>"/>
            </th>
        </tr>
        <tr>
            <th width=150px>书名：</th>
            <th><input type="text" name="name" title="" style="text-align: center; width: 150px;" value="<%= dvd.getName() %>" /></th>
        </tr>
        <tr>
            <th width=150px>总数：</th>
            <th><input name="state" type="text" title="" style="text-align: center; width: 150px;" onkeyup="this.value=this.value.replace(/\D/g,'')"
                       value="<%= dvd.getState() %>"/></th>
        </tr>
        <tr>
            <th width=150px>借出：</th>
            <th><input name="borrow" type="text" title="" style="text-align: center; width: 150px;" onkeyup="this.value=this.value.replace(/\D/g,'')"
                       value="<%= dvd.getBorrow() %>"/></th>
        </tr>
        <%
                }
            }
        %>
        <tr>
            <th colspan = '1'><a href = '../../../../login.do'>返回</a></th>
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
