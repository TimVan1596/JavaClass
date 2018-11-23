<%@ page import="com.antianbao.text.day3.JDBC" %>
<%@ page import="com.antianbao.text.day3.attendance" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\10\12 0012
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理数据</title>
</head>
<body>
<div align='center'>
    <form action='./atb/javaWebDvd/search.jsp' method='post'>
        <h4>搜关键字：<input type='text' title="" name='search' style="width: 150px; height: 30px"/>
            <input type='submit' value='搜索' style="width: 50px; height: 30px"/>
        </h4>
    </form>
</div>
<form action='./atbdelete.do' method='post'>
    <table border="1" width="690" align = "center">
        <tr>
            <th width=50px>选择</th>
            <th width=50px>序号</th>
            <th width=70px>姓名</th>
            <th width=150px>入学日期</th>
            <th width=70px>考勤情况</th>
            <th width=150px>备注</th>
            <th width=150px>考勤日期</th>
        </tr>
        <%
            //遍历结果集
            JDBC jdbc = new JDBC();
            List<attendance> atts = jdbc.queryStu();;
            for (attendance att : atts) {
        %>
        <tr>
            <!-- 输出结果集 -->
            <td align="center">
                <input type='checkbox' name='check' title='choice' value='<%= att.getNo() %>'/>
            </td>
            <td align="center">
                <%= att.getNo() %>
            </td>
            <td align="center">
                <%= att.getName() %>
            </td>
            <td align="center">
                <%= att.getTime() %>
            </td>
            <td align="center">
                <%= att.getState() %>
            </td>
            <td align="center">
                <%= att.getNote() %>
            </td>
            <td align="center">
                <%= att.getDate() %>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</form>
</body>
</html>
