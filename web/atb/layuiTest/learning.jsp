<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\11\9 0009
  Time: 9:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>布局</title>
    <script src="../../common/util/layui/layui.js"></script>
    <link rel="stylesheet" href="../../common/util/layui/css/layui.css">
</head>
<body>
<%--1-布局--%>
<%--<div class="layui-container">--%>
    <%--常规布局（以中型屏幕桌面为例）：--%>
    <%--<div class="layui-row">--%>
        <%--<div class="layui-col-md9">--%>
            <%--你的内容 9/12--%>
        <%--</div>--%>
        <%--<div class="layui-col-md3">--%>
            <%--你的内容 3/12--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</div>--%>

<%--2-颜色--%>
<%--<div class="layui-bg-red">23132</div>--%>

<%--3-图标--%>
<%--<div class="layui-bg-red">23132</div>--%>

<%--4-动画--%>
<%--<div class="layui-anim layui-anim-up"><i class="layui-icon layui-icon-login-qq" style="font-size: 100px; color: #1E9FFF;"></i></div>--%>

<%--5-按钮--%>
<%--<button class="layui-btn">一个标准的按钮</button>--%>
<%--<a href="http://www.layui.com" class="layui-btn">一个可跳转的按钮</a>--%>

<%--6-表单--%>
<form class="layui-form" action="">
    <div class="layui-form-item">
        <label class="layui-form-label">输入框</label>
        <div class="layui-input-block">
            <input type="text" name="title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">密码框</label>
        <div class="layui-input-inline">
            <input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux">辅助文字</div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">选择框</label>
        <div class="layui-input-block">
            <select name="city" lay-verify="required">
                <option value=""></option>
                <option value="0">北京</option>
                <option value="1">上海</option>
                <option value="2">广州</option>
                <option value="3">深圳</option>
                <option value="4">杭州</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">复选框</label>
        <div class="layui-input-block">
            <input type="checkbox" name="like[write]" title="写作">
            <input type="checkbox" name="like[read]" title="阅读" checked>
            <input type="checkbox" name="like[dai]" title="发呆">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">开关</label>
        <div class="layui-input-block">
            <input type="checkbox" name="switch" lay-skin="switch">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">单选框</label>
        <div class="layui-input-block">
            <input type="radio" name="sex" value="男" title="男">
            <input type="radio" name="sex" value="女" title="女" checked>
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">文本域</label>
        <div class="layui-input-block">
            <textarea name="desc" placeholder="请输入内容" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script>
    //Demo
    layui.use('form', function(){
        var form = layui.form;
        //监听提交
        form.on('submit(formDemo)', function(data){
            layer.msg(JSON.stringify(data.field));
            return false;
        });
    });
</script>

<%--7-导航--%>

</body>
</html>
