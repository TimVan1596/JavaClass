<%@ page import="com.antianbao.javaWebDvd.dvd.JDBCUtilDvd" %>
<%@ page import="com.antianbao.javaWebDvd.dvd.Dvd" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\10\12 0012
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>显示DVD</title>
</head>
<body>
<h1 align='center'>----欢迎进入DVD Mgr 6.0 管理系统----</h1>
<div align='center'>
    <form action='./atb/javaWebDvd/search.jsp' method='post'>
        搜关键字：<input type='text' title="序号书名库存" name='search' />
        <input type='submit' value='搜索' />
    </form>
</div>
<form action='./delete.do' method='post' onsubmit="return GL()">
    <table border="1" width="730" align = "center">
        <tr>
            <th width=50px>选择</th>
            <th width=60px>序号</th>
            <th width=60px>图片</th>
            <th width=280px>名称</th>
            <th width=70px>库存</th>
            <th width=70px>操作</th>
            <th width=70px>操作</th>
            <th width=70px>操作</th>
        </tr>
        <%
            //遍历结果集
            JDBCUtilDvd jdbcUtilDvd = new JDBCUtilDvd();
            List<Dvd> dvds;
            //数据总数
            //查询的总条数num和页数pages和页面选择page1
            int num = 0,pages,page1;
            num = jdbcUtilDvd.findCount();
            if(num % Dvd.PAGE_SIZE == 0){
                pages = num/Dvd.PAGE_SIZE;
            }else{
                pages = num/Dvd.PAGE_SIZE + 1;
            }

            if(request.getAttribute("page") == null){
                page1 = 1;
                dvds = jdbcUtilDvd.find(1);
            }else{
                page1 = (Integer) request.getAttribute("page");
                dvds = jdbcUtilDvd.find(page1);
            }
            for (Dvd dvd : dvds) {
        %>
        <tr>
            <!-- 输出结果集 -->
            <td align="center"><input type='checkbox' name='check' title='choice' value='<%= dvd.getNo() %>'/></td>
            <td align="center"><%= dvd.getNo() %></td>
            <td align="center"><img src="<%= dvd.getImage() %>" width="60px" height="50px"></td>
            <td align="center"><%= dvd.getName() %></td>
            <%
                if(dvd.getState()-dvd.getBorrow() == 0){
            %>
            <td style="background-color: red" align="center">
                <%= dvd.getState()-dvd.getBorrow() %>
            </td>
            <%
            }else if(dvd.getBorrow() == 0){
            %>
            <td style="background-color: #007DDB" align="center">
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
            <td align="center">
                <a href = './lend.do?no=<%= dvd.getNo() %>'>借出</a>
                <%--<a href = './lend.do?no=<%= dvd.getNo() %>'>借出</a>--%>
            </td>
            <td align="center" >
                <a href = './return.do?no=<%= dvd.getNo() %>'>归还</a>
            </td>
            <td align="center">
                <a href = './atb/javaWebDvd/jsp/choice/modify.jsp?no=<%= dvd.getNo() %>'>编辑</a>
            </td>
        </tr>
        <%
            }
        %>
        <tr>
            <td colspan = '8' align="right">
                <a href = './add.do?page=1'>首页</a>
                <%
                    if(page1 == 1){
                %>
                <a href = './add.do?page=1'>上一页</a>
                <%
                }else{
                %>
                <a href = './add.do?page=<%= page1-1%>'>上一页</a>
                <%
                    }
                %>
                <%
                    for(int i=1;i<pages+1;i++){
                %>
                <a href = './add.do?page=<%= i %>'><%= i %></a>
                <%
                    }
                %>
                <%
                    if(page1 == pages){
                %>
                <a href = './add.do?page=<%= pages%>'>下一页</a>
                <%
                }else{
                %>
                <a href = './add.do?page=<%= page1+1%>'>下一页</a>
                <%
                    }
                %>
                <a href = './add.do?page=<%= pages%>'>尾页</a>
            </td>
        </tr>
        <tr>
            <td colspan = '1' align="center"><input type="submit" value="删除" style="text-align: left;"></td>
            <td colspan = '1' align="center"><a href = "./atb/javaWebDvd/jsp/choice/add.jsp">添加</a></td>
            <td colspan = '1' align="center"><a href = "./login.do">主页</a></td>
            <td colspan = '2' align="center"></td>
            <td colspan = '2' align="center"><a href = "./atb/javaWebDvd/jsp/choice/data.jsp">数据显示</a></td>
            <td colspan = '1' align="center"><a href = "./atb/javaWebDvdLogin.jsp">退出</a></td>
        </tr>
        <script type="text/javascript">
            if(<%= request.getAttribute("MSG")!=null %>){
                alert('<%=request.getAttribute("MSG") %>');
            }
            /**
             * @return {boolean}
             */
            function GL(){
                var theResponse = window.prompt("想删库请输入：","请在此输入删库密码");
                // var truthBeTold = window.confirm("单击“确定”继续。单击“取消”停止。");
                if (theResponse == 123) {
                    window.alert("又删我库,很难受！");
                    return true;
                } else{
                    window.alert("小兄弟，库不能随便删的啊！");
                    return false;
                }
            }
        </script>
    </table>
</form>
</body>
</html>
