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
    <h3>----修改信息----</h3>
</div>
<form action="../../../../modify.do" method="post">
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
            <th><input name="no" type="text" title="" value="<%= Integer.parseInt(request.getParameter("no")) %>" readonly style = "color:gray;"/></th>
        </tr>
        <tr>
            <th width=150px>书名：</th>
            <th><input name="name" type="text" title="" value="<%= dvd.getName() %>" /></th>
        </tr>
        <tr>
            <th width=150px>总数：</th>
            <th><input name="state" type="text" title="" value="<%= dvd.getState() %>"/></th>
        </tr>
        <tr>
            <th width=150px>借出：</th>
            <th><input name="borrow" type="text" title="" value="<%= dvd.getBorrow() %>"/></th>
        </tr>
        <%
                }
            }
        %>
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
