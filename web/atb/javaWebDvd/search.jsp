<%@ page import="java.util.List" %>
<%@ page import="com.antianbao.javaWebDvd.dvd.JDBCUtilDvd" %>
<%@ page import="com.antianbao.javaWebDvd.dvd.Dvd" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\10\13 0013
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查看DVD</title>
</head>
<body>
<h1 align='center'>----欢迎进入DVD Mgr 6.0 管理系统----</h1>
<div align='center'>
    <form action='./search.jsp' method='post'>
        搜关键字：<input type='text' title="序号书名状态" name='search' />
        <input type='submit' value='搜索' />
    </form>
</div>

<form action='../../delete.do' method='post'>
    <table border="1" width="700" align = "center">
        <tr>
            <th width=70px>选择</th>
            <th width=70px>序号</th>
            <th width=280px>书名</th>
            <th width=70px>库存</th>
            <th width=70px>操作</th>
            <th width=70px>操作</th>
            <th width=70px>操作</th>
        </tr>
        <%
            List<Dvd> dvds;
            JDBCUtilDvd jdbcUtilDvd = new JDBCUtilDvd();
            request.setCharacterEncoding("utf-8");
            if(request.getParameter("search") == null){
                dvds = jdbcUtilDvd.queryStu();
            }else{
                //post解决中文乱码
                request.setCharacterEncoding("utf-8");
                //遍历结果集
                dvds = jdbcUtilDvd.search(request.getParameter("search"));
            }
            for (Dvd dvd : dvds) {
        %>
        <tr>
            <!-- 输出结果集 -->
            <td align="center"><input type='checkbox' name='check' title='choice' value='<%= dvd.getNo() %>'/></td>
            <td align="center"><%= dvd.getNo() %></td>
            <td align="center"><%= dvd.getName() %></td>
            <%--<td align="center"><%= dvd.getState()-dvd.getBorrow() %></td>--%>
            <%
                if(dvd.getState()-dvd.getBorrow() == 0){
            %>
            <td style="background-color: red" align="center">
                <%= dvd.getState()-dvd.getBorrow() %>
            </td>
            <%
            }else if(dvd.getBorrow() == 0){
            %>
            <td style="background-color: green" align="center">
                <%= dvd.getState()-dvd.getBorrow() %>
            </td>
            <%
            }else{
            %>
            <td align="center">
                <%= dvd.getState()-dvd.getBorrow() %>
            </td>
            <%
            }
            %>
            <td align="center"><a href = '../../lend.do?no=<%= dvd.getNo() %>'>借出</a></td>
            <td align="center"><a href = '../../return.do?no=<%= dvd.getNo() %>'>归还</a></td>
            <td align="center"><a href = './jsp/choice/modify.jsp?no=<%= dvd.getNo() %>'>编辑</a></td>
        </tr>
        <%
            }
        %>
        <tr>
            <td colspan = '1' align="center"><input type="submit" value="删除" style="text-align: left;"></td>
            <td colspan = '1' align="center"><a href = "./jsp/choice/add.jsp">添加</a></td>
            <td colspan = '1' align="center"><a href = "../../login.do">主页</a></td>
            <td colspan = '1' align="center"></td>
            <td colspan = '2' align="center"><a href = "./jsp/choice/data.jsp">数据显示</a></td>
            <td colspan = '1' align="center"><a href = "./login.jsp">退出</a></td>
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
