<%@ page import="web.atb.javaWebDvd.dvd.JDBCUtilDvd" %>
<%@ page import="web.atb.javaWebDvd.dvd.Dvd" %>
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
<h3>------修改信息------</h3>
<form action="../../../../modify.do" method="post">
    <%
        //遍历结果集
        JDBCUtilDvd jdbcUtilDvd = new JDBCUtilDvd();
        List<Dvd> dvds = jdbcUtilDvd.queryStu();
        for (Dvd dvd : dvds) {
            if(Integer.parseInt(request.getParameter("no")) == dvd.getNo()){
    %>
    <p>
        序号：<input name="no" type="text" value="<%= Integer.parseInt(request.getParameter("no")) %>" readonly style = "color:gray" style="text-align: left; width: 150px;"/>
    </p>
    <p>
        书名：<input name="name" type="text" value="<%= dvd.getName() %>" style="text-align: left; width: 150px;"/>
    </p>
    <p>
        <%
            if(dvd.getState().equals("可以借")){
        %>
        状态：<input type="radio" value="可以借" name="state" checked>可以借
        <input type="radio" value="已借出" name="state">已借出
        <%
        }else{
        %>
        状态：<input type="radio" value="可以借" name="state">可以借
        <input type="radio" value="已借出" name="state" checked>已借出
        <%
            }
        %>
    </p>
    <%
            }
        }
    %>
    <p>
        <input type="submit" value="提交" style="text-align: left;">
    </p>
    <script type="text/javascript">
        if(<%= request.getAttribute("MSG")!=null %>){
            alert('<%=request.getAttribute("MSG") %>');
        }
    </script>
</form>
</body>
</html>
