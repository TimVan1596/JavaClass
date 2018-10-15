<%@ page import="web.fy.DbUtil" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/8 0008
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% if(session.getAttribute("loginName")==null){
    response.sendRedirect("login.jsp");
}%>
<html>
<head>
    <title>显示DVD</title>
</head>
<body>
<div style="text-align:center">
<h3>DVD列表</h3>
<table id="tb" style="text-align:center;margin: 0 auto " border="1" width="500">
    <tr><th>DVD编号</th><th>DVD名称</th><th>DVD状态</th><th>删除操作</th></tr>
    <% DbUtil db = new DbUtil();
        String sql = "SELECT * FROM dvd ";
        ResultSet rs = db.executeQuery(sql, null);
//        try {
//        while(rs.next()){
//            out.print("<tr><td>"+rs.getString("dvdno")+"</td><td>"+rs.getString("dvdname")+"</td><td>"+rs.getString("state")+"</td><td> <button  onclick=remove()>删除</button> </td></tr>");
//            }
//        } catch (SQLException e) {
//                e.printStackTrace();
//            }
        int i=0;
        try {
        while(rs.next()){
            i++;
    %>
    <tr>
        <td><%=rs.getString("dvdno") %></td>
        <%--<td><%=rs.getString("dvdno")%></td>--%>
        <td><%=rs.getString("dvdname")%></td>
        <td><%=rs.getString("state")%></td>
        <td><input type="button"  value="删除" onclick='window.location.href="deleteDVD.jsp?no=<%=rs.getString("dvdno") %>"'></td>
    </tr>
    <% }
    } catch (SQLException e) {
                e.printStackTrace();
           }%>
</table>
</div>
<div style="width: 500px;margin: 0 auto">
    <div style="width:100px;float:left">
<br>
<a  href="addDVD.jsp" target="mainFrame" >添加DVD</a><br>
<a  href="changeDVD.jsp" target="mainFrame" >修改DVD</a><br>
    </div>
<iframe style="width:350px;height:300px;margin-left:30px"   name="mainFrame" frameborder="0"></iframe>
</div>


</body>
</html>
