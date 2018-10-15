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
            <th><input name="no" type="text" title="" value="<%= Integer.parseInt(request.getParameter("no")) %>" readonly style = "color:gray; width: 150px;"/></th>
        </tr>
        <tr>
            <th width=150px>书名：</th>
            <th><input name="name" type="text" title="" value="<%= dvd.getName() %>" style="text-align: left; width: 150px;"/></th>
        </tr>
        <tr>
            <%
                if(dvd.getState().equals("可以借")){
            %>
            <th width=150px>状态：</th>
            <th>
                <input type="radio" value="可以借" title="" name="state" checked>可以借
                <input type="radio" value="已借出" title="" name="state">已借出
            </th>
            <%
            }else{
            %>
            <th width=150px>状态：</th>
            <th>
                <input type="radio" value="可以借" title="" name="state">可以借
                <input type="radio" value="已借出" title="" name="state" checked>已借出
            </th>
            <%
                }
            %>
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
