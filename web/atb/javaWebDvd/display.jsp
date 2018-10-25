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
    <title>管理数据</title>
    <style type="text/css">
        ul{
            margin:0 auto;
            list-style: none;
            width: 750px;
            padding: 0;
            overflow: hidden;
            background-color: #eee;
        }
        li {
            float: left;
            width: 150px;
            height: 40px;
            text-align: center;
        }
        li a {
            display: block;
            padding: 10px 15px;
            color: black;
            text-align: center;
            text-decoration: none;
        }
        li a:hover {
            background-color: red;
        }
        #footer {
            height: 40px;
            line-height: 40px;
            position: fixed;
            bottom: 0;
            width: 100%;
            text-align: center;
            background: #333;
            color: #fff;
            font-size: 12px;
            letter-spacing: 1px;
        }
    </style>
</head>
<body background="./atb/javaWebDvd/images/banner.jpg">
<h1 align='center' style="color: white">----欢迎进入DVD Mgr 6.0 管理系统----</h1>
<h5 align='center' style="color: red">介绍：搜索（关键字）,管理数据（操作数据）,数据显示（借阅情况柱状图）,退出（返回登录页面）</h5>
<h5 align='center' style="color: red">界面操作：编辑（修改信息,图片可改可不改）,分页（数据分页）,删除（多删可恢复但要密码）,添加（添加信息需加图片）</h5>
<div align='center'>
    <form action='./atb/javaWebDvd/search.jsp' method='post'>
        <h4 style="color: white">搜关键字：<input type='text' title="序号书名库存" name='search' style="width: 150px; height: 30px"/>
            <input type='submit' value='搜索' style="width: 50px; height: 30px"/>
        </h4>
    </form>
</div>
<div align='center'>
    <ul>
        <li><a href="./atblogin.do">主页</a></li>
        <li><a href="./atblogin.do">管理数据</a></li>
        <li><a href="./atb/javaWebDvd/jsp/choice/data.jsp">借阅情况</a></li>
        <li><a href="./atbrevoke.do">历史数据</a></li>
        <li><a href="./atbdescription.do">说明介绍</a></li>
    </ul>
</div>
<form action='./atbdelete.do' method='post' onsubmit="return GL()">
    <table border="1" width="750" align = "center" style="background-color: white">
        <tr>
            <th width=50px>选择</th>
            <th width=60px>序号</th>
            <th width=60px>图片</th>
            <th width=280px>名称</th>
            <th width=75px>库存</th>
            <th width=75px>操作</th>
            <th width=75px>操作</th>
            <th width=75px>操作</th>
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
            <td align="center">
                <img src="./atb/javaWebDvd/image/<%=dvd.getImage()%>" width="60px" height="50px">
                <%--<img src="<%= dvd.getImage() %>" width="60px" height="50px">--%>
            </td>
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
                <a href = './atblend.do?no=<%= dvd.getNo() %>'>借出</a>
                <%--<a href = './lend.do?no=<%= dvd.getNo() %>'>借出</a>--%>
            </td>
            <td align="center" >
                <a href = './atbreturn.do?no=<%= dvd.getNo() %>'>归还</a>
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
                <a href = './atbadd.do?page=1'>首页</a>
                <%
                    if(page1 == 1){
                %>
                <a href = './atbadd.do?page=1'>上一页</a>
                <%
                }else{
                %>
                <a href = './atbadd.do?page=<%= page1-1%>'>上一页</a>
                <%
                    }
                %>
                <%
                    for(int i=1;i<pages+1;i++){
                %>
                <a href = './atbadd.do?page=<%= i %>'><%= i %></a>
                <%
                    }
                %>
                <%
                    if(page1 == pages){
                %>
                <a href = './atbadd.do?page=<%= pages%>'>下一页</a>
                <%
                }else{
                %>
                <a href = './atbadd.do?page=<%= page1+1%>'>下一页</a>
                <%
                    }
                %>
                <a href = './atbadd.do?page=<%= pages%>'>尾页</a>
            </td>
        </tr>
        <tr>
            <td colspan = '1' align="center"><input type="submit" value="删除" style="text-align: left;"></td>
            <td colspan = '1' align="center"><a href = "./atb/javaWebDvd/jsp/choice/add.jsp">添加</a></td>
            <td colspan = '1' align="center"><a href = "./atblogin.do">主页</a></td>
            <td colspan = '2' align="center" style="color: red">主页:返回首页  库存:蓝色未借出,红色已借完</td>
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
                var theResponse = window.prompt("想删库请输入密码：","请在此输入删库密码");
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
<div id="footer">安徽信息工程学院 2016级 JAVA①班 安天宝 JavaWeb项目作业</div>
</body>
</html>
