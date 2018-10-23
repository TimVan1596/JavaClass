<%--
  主菜单页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>菜单 - BiggerDVD</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">

    <link rel="stylesheet"
          href="../style/html/menu.css"
          media="all">
    <link rel="stylesheet"
          href="../../common/util/layui/css/layui.css"
          media="all">

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

    <!-- DVD表格的表头（列表填充模板 template）-->
    <script type="text/html" id="DVD_TABLE_TH_TEMPLATE">
        <tr>
            <th width="30"></th>
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

    <ul class="layui-nav" style="text-align: left;">

        <li class="layui-nav-item">
            <a href="./menu/statistics.html">信息统计<span class="layui-badge-dot"></span></a>
        </li>
        <li class="layui-nav-item" >
            <a href=""><img src="//t.cn/RCzsdCq" class="layui-nav-img"><%=userName%></a>
            <dl class="layui-nav-child">
                <dd><a href="javascript:;" onclick="logout();">退出登录</a></dd>
            </dl>
        </li>
    </ul>


    <br>
    <div class="layui-btn-group">
        <button class="layui-btn layui-btn-normal"  onclick="addDVD()">
            <i class="layui-icon">&#xe608;</i> 添加
        </button>

        <button class="layui-btn layui-btn-danger" onclick="deleteDVD()">
            <i class="layui-icon">&#xe640;</i>一键删除
        </button>

    </div>

    <button onclick="editDVD()">编辑</button>

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


</body>

</html>
