<%--
  编辑DVD页面 （iFrame）
  User: TimVan
  Date: 2018/10/26
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet"
          href="../../../common/util/layui/css/layui.css"
          media="all">
    <style>
        .layui-upload-img{
            width: 92px; height: 92px;
            margin: 0 10px 10px 0;
        }
    </style>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="../../../common/util/layui/layui.js"></script>
    <script src="../../js/common-js.js"></script>
</head>
<body>
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
    <form style="margin: 5%" class="layui-form layui-form-pane">
            <br>
            <%--DVD名称--%>
                <div class="layui-form-item">
            <label class="layui-form-label">修改名称
            </label>
            <div class="layui-input-block">
                <input type="text" name="name"
                       autocomplete="off" lay-verify="name"
                       placeholder="请不要输入特殊字符"
                       class="layui-input" id="user-name">
            </div>
        </div>
            <%--上传封面--%>
                <div class="layui-upload">

                        <button type="button"
                                class="layui-btn  layui-btn-normal"
                                id="preview-upload">
                            修改图片</button>
                        <div class="layui-upload-list">
                            <img class="layui-upload-img"                                   id="preview-upload-img">
                            <p id="demoText"></p>
                        </div>

                    <input type="hidden" name="preview" id="preview-hidden">
                    <input type="hidden" name="id"
                           id="id-hidden">
                    </div>
            <%--提交按钮--%>
                <div style="margin-top: 1%">
                    <button class="layui-btn  layui-btn-normal
                    layui-btn-radius layui-btn-fluid"
                            lay-filter = "addSubmmit"
                            lay-submit="" >提交</button>
                </div>
    </form>

    <script src="../../js/html/editDVD.js"></script>
</body>
</html>
