<%--
  主菜单页面
  User: TimVan
  Date: 2018/10/8
  Time: 9:53
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>菜单 - BiggerDVD</title>
    <style>
        td {
            text-align: center;
            white-space: nowrap;
        }

        .dvd-preview {
            width: 100px;
            height: 100px;
        }

        .dvd-name{
            font-weight: bold;
        }

    </style>

    <!-- DVD信息（列表填充模板 template）-->
    <script type="text/html" id="DVD_TEMPLATE">
        <tr class="dvd-tr-line">
            <td class="dvd-radio">
                <input class="dvd-radio-input" name="dvd-radio"
                       type="radio" value=""/>
            </td>
            <td class="dvd-id"></td>
            <td >
                <img class="dvd-preview"
                     src="https://cloud.timvanx.com/timg.jpg">
            </td>
            <td class="dvd-name"></td>
            <td>
                <button class="dvd-btn-land"
                        onclick="loanOrReturnDVD(this)"></button>
            </td>
        </tr>
    </script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="../../common/util/layui/layui.js"></script>
    <script src="../js/html/menu.js"></script>
</head>

<body>

<%
    //初始化常量信息
    String userName = "非法用户";

    if (session.getAttribute("userName") != null) {
        userName = (String) session.getAttribute("userName");
    } else {
        response.sendRedirect("../biggerdvd.jsp");
        return;
    }

%>


<h1>**********<span>
    <%=userName%>
</span>，你好！欢迎进入系统菜单 **************</h1>

<section style="margin-bottom: 1%;">

    <button onclick="addDVD()">添加</button>
    <button onclick="editDVD()">编辑</button>
    <button onclick="deleteDVD()">删除</button>
    <button onclick="getStatistics()">信息统计</button>

</section>

<table border="1" id="DVDsTable">
    <tr>
        <th></th>
        <th width="80">编号</th>
        <th width="100">预览</th>
        <th width="200">名称</th>
        <th width="100">操作</th>
    </tr>
    <!--填充模板区-->
</table>

<section style="margin-top: 2%">
    <button onclick="logout()">注销</button>
</section>

</body>

</html>
