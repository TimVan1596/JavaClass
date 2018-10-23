<%--
  主菜单页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>菜单 - BiggerDVD</title>
    <link rel="stylesheet"
          href="../style/html/menu.css"
          media="all">

    <meta http-equiv="content-type" content="text/html; charset=utf-8">

    <!-- DVD信息（列表填充模板 template）-->
    <script type="text/html" id="DVD_TEMPLATE">
        <tr class="dvd-tr-line">
            <td class="dvd-radio">
                <input class="dvd-radio-input" name="dvd-radio"
                       type="checkbox" value=""/>
            </td>
            <td class="dvd-id"></td>
            <td>
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

    <!-- DVD信息（列表填充模板 template）-->
    <script type="text/html" id="DVD_TABLE_TH_TEMPLATE">
        <tr>
            <th></th>
            <th width="80">编号</th>
            <th width="100">预览</th>
            <th width="200">名称</th>
            <th width="100">操作</th>
        </tr>
    </script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="../../common/util/layui/layui.js"></script>
    <script src="../js/html/menu.js"></script>
    <script src="../js/common-js.js"></script>
</head>

<body style="text-align: center;">

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

<%--标题头 start--%>
<section>

    <h1>**********<span>
    <%=userName%>
</span>，你好！欢迎进入系统菜单 **************</h1>

    <br>

    <button onclick="addDVD()">添加</button>
    <button onclick="editDVD()">编辑</button>
    <button onclick="deleteDVD()">一键删除</button>
    <button onclick="getStatistics()">信息统计</button>

    <%--绑定事件（JS代码中）--%>
    <input list="dvdList"  id="query_input"
           style="width:12%;height: 24px;margin-left: 10%;">
    <datalist id="dvdList"></datalist>
    <button onclick="search()">搜索</button>

</section>
<%--标题头 结束--%>

<%--表格部分 start--%>
<section style="margin:0 auto">
    <br>
    <table border="1" id="DVDsTable" style="margin-left: 30%;">


        <!--填充模板区-->

    </table>

    <br>
    <div>
        <ul class="pagination" id="table-pagination">
        </ul>
    </div>
</section>
<%--表格部分 结束--%>

<%--注销 start--%>
<section style="margin-top: 2%">
    <button onclick="logout()">注销</button>
</section>
<%--注销 结束--%>

</body>

</html>
