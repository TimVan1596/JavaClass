<%--
  回收站
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>回收站 - BiggerDVD</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">

    <link rel="stylesheet"
          href="../style/html/menu.css"
          media="all">
    <link rel="stylesheet"
          href="../../common/util/layui/css/layui.css"
          media="all">
    <link rel="stylesheet"
          href="../style/search-form.css"
          media="all">
    <style>
        .hidden{
            display: none;
        }
        tr:hover{
            background-color: rgba(0, 129, 255, 0.3);
        }

        table th {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #666666;
            background-color: #dedede;
        }
    </style>

    <!-- DVD信息（列表填充模板 template）-->
    <script type="text/html" id="DVD_TEMPLATE">
        <tr class="dvd-tr-line">
            <td class="dvd-id"></td>
            <td height="110">
                <img class="dvd-preview"
                     src="https://cloud.timvanx.com/timg.jpg">
            </td>
            <td class="dvd-name"></td>
            <td>
                <button onclick="returnShow(this)"
                        class="layui-btn layui-btn-sm
                            dvd-btn-return layui-btn-warm">
                    <i class="layui-icon">&#x1005;</i>还原
                </button>

            </td>
        </tr>
    </script>

    <!-- DVD表格的表头（列表填充模板 template）-->
    <script type="text/html" id="DVD_TABLE_TH_TEMPLATE">
        <tr >
            <th width="100" >编号</th>
            <th width="110">封面</th>
            <th width="250">名称</th>
            <th width="150">操作</th>
        </tr>
    </script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="../../common/util/layui/layui.js"></script>
    <script src="../js/html/recyclebin.js"></script>
    <script src="../js/common-js.js"></script>
</head>

<body style="text-align: center;">

<%
    //初始化常量信息
    String userName = "非法用户";

    if (session.getAttribute("userName") != null) {
        userName = (String) session.getAttribute("userName");
    } else {
        response.sendRedirect("../index.html");
        return;
    }
%>

<%--标题头 start--%>
<section>

    <ul class="layui-nav" style="text-align: left;">
        <li class="layui-nav-item">
            <a href="menu.jsp">
                <i class="layui-icon">&#xe65c;</i>
                回到菜单</a>
        </li>
        <li class="layui-nav-item" >
            <a href=""><img src="https://cloud.timvanx.com/Fu7yXecEjIMj2_g2CPRmYEKGuIK3" class="layui-nav-img"><%=userName%></a>
            <dl class="layui-nav-child">
                <dd><a href="javascript:;" onclick="logout();">退出登录</a></dd>
            </dl>
        </li>
    </ul>

    <br>
    <div  style="display: inline-block;margin-left: -24%;">
        <div class="layui-btn-group">
            <button class="layui-btn layui-btn-normal hidden"
                    id="refresh-Search-btn"
                    onclick="refreshSearch()">
                <i class="layui-icon">&#xe9aa;</i> 重置搜索
            </button>
        </div>
        <form onsubmit="submitFn(this, event);"
              style="display: inline-block;">
            <div class="search-wrapper" style="margin-left: 24%;">
                <div class="input-holder">
                    <%--绑定事件（JS代码中）--%>
                    <input type="text" class="search-input"
                           list="dvdList"  id="query_input"
                           placeholder="" style="margin-top: -2%">
                    <datalist id="dvdList"></datalist>
                    <button class="search-icon"
                            onclick="searchToggle(this, event);"
                    ><span></span>
                    </button>


                </div>
                <span class="close" onclick="searchToggle(this, event);"></span>
                <div class="result-container">

                </div>
            </div>
        </form>
    </div>
</section>
<%--标题头 结束--%>

<%--表格部分 start--%>
<section style="margin:0 auto">
    <br>
    <br>
    <table border="1" id="DVDsTable" style="margin-left: 25%;">
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
