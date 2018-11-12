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
<%--<form class="layui-form" action="">--%>
    <%--<div class="layui-form-item">--%>
        <%--<label class="layui-form-label">输入框</label>--%>
        <%--<div class="layui-input-block">--%>
            <%--<input type="text" name="title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="layui-form-item">--%>
        <%--<label class="layui-form-label">密码框</label>--%>
        <%--<div class="layui-input-inline">--%>
            <%--<input type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">--%>
        <%--</div>--%>
        <%--<div class="layui-form-mid layui-word-aux">辅助文字</div>--%>
    <%--</div>--%>
    <%--<div class="layui-form-item">--%>
        <%--<label class="layui-form-label">选择框</label>--%>
        <%--<div class="layui-input-block">--%>
            <%--<select name="city" lay-verify="required">--%>
                <%--<option value=""></option>--%>
                <%--<option value="0">北京</option>--%>
                <%--<option value="1">上海</option>--%>
                <%--<option value="2">广州</option>--%>
                <%--<option value="3">深圳</option>--%>
                <%--<option value="4">杭州</option>--%>
            <%--</select>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="layui-form-item">--%>
        <%--<label class="layui-form-label">复选框</label>--%>
        <%--<div class="layui-input-block">--%>
            <%--<input type="checkbox" name="like[write]" title="写作">--%>
            <%--<input type="checkbox" name="like[read]" title="阅读" checked>--%>
            <%--<input type="checkbox" name="like[dai]" title="发呆">--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="layui-form-item">--%>
        <%--<label class="layui-form-label">开关</label>--%>
        <%--<div class="layui-input-block">--%>
            <%--<input type="checkbox" name="switch" lay-skin="switch">--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="layui-form-item">--%>
        <%--<label class="layui-form-label">单选框</label>--%>
        <%--<div class="layui-input-block">--%>
            <%--<input type="radio" name="sex" value="男" title="男">--%>
            <%--<input type="radio" name="sex" value="女" title="女" checked>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="layui-form-item layui-form-text">--%>
        <%--<label class="layui-form-label">文本域</label>--%>
        <%--<div class="layui-input-block">--%>
            <%--<textarea name="desc" placeholder="请输入内容" class="layui-textarea"></textarea>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="layui-form-item">--%>
        <%--<div class="layui-input-block">--%>
            <%--<button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>--%>
            <%--<button type="reset" class="layui-btn layui-btn-primary">重置</button>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</form>--%>
<%--<script>--%>
    <%--//Demo--%>
    <%--layui.use('form', function(){--%>
        <%--var form = layui.form;--%>
        <%--//监听提交--%>
        <%--form.on('submit(formDemo)', function(data){--%>
            <%--layer.msg(JSON.stringify(data.field));--%>
            <%--return false;--%>
        <%--});--%>
    <%--});--%>
<%--</script>--%>

<%--7-导航--%>
<%--<ul class="layui-nav" lay-filter="">--%>
    <%--<li class="layui-nav-item"><a href="">最新活动</a></li>--%>
    <%--<li class="layui-nav-item layui-this"><a href="">产品</a></li>--%>
    <%--<li class="layui-nav-item"><a href="">大数据</a></li>--%>
    <%--<li class="layui-nav-item">--%>
        <%--<a href="javascript:;">解决方案</a>--%>
        <%--<dl class="layui-nav-child"> <!-- 二级菜单 -->--%>
            <%--<dd><a href="">移动模块</a></dd>--%>
            <%--<dd><a href="">后台模版</a></dd>--%>
            <%--<dd><a href="">电商平台</a></dd>--%>
        <%--</dl>--%>
    <%--</li>--%>
    <%--<li class="layui-nav-item"><a href="">社区</a></li>--%>
<%--</ul>--%>
<%--<script>--%>
    <%--//注意：导航 依赖 element 模块，否则无法进行功能性操作--%>
    <%--layui.use('element', function(){--%>
        <%--var element = layui.element;--%>

        <%--//…--%>
    <%--});--%>
<%--</script>--%>

<%--8-选项卡--%>
<%--<div class="layui-tab layui-tab-card">--%>
    <%--<ul class="layui-tab-title">--%>
        <%--<li class="layui-this">网站设置</li>--%>
        <%--<li>用户管理</li>--%>
        <%--<li>权限分配</li>--%>
        <%--<li>商品管理</li>--%>
        <%--<li>订单管理</li>--%>
    <%--</ul>--%>
    <%--<div class="layui-tab-content" style="height: 100px;">--%>
        <%--<div class="layui-tab-item layui-show">1</div>--%>
        <%--<div class="layui-tab-item">2</div>--%>
        <%--<div class="layui-tab-item">3</div>--%>
        <%--<div class="layui-tab-item">4</div>--%>
        <%--<div class="layui-tab-item">5</div>--%>
        <%--<div class="layui-tab-item">6</div>--%>
    <%--</div>--%>
<%--</div>--%>
<%--<script>--%>
    <%--//注意：选项卡 依赖 element 模块，否则无法进行功能性操作--%>
    <%--layui.use('element', function(){--%>
        <%--var element = layui.element;--%>

        <%--//…--%>
    <%--});--%>
<%--</script>--%>

<%--9-进度条--%>
<%--<div class="layui-progress layui-progress-big" lay-showPercent="yes">--%>
    <%--<div class="layui-progress-bar layui-bg-green" lay-percent="50%"></div>--%>
<%--</div>--%>
<%--<script>--%>
    <%--//注意进度条依赖 element 模块，否则无法进行正常渲染和功能性操作--%>
    <%--layui.use('element', function(){--%>
        <%--var element = layui.element;--%>
    <%--});--%>
<%--</script>--%>

<%--10-面板--%>
<%--<div class="layui-collapse">--%>
    <%--<div class="layui-colla-item">--%>
        <%--<h2 class="layui-colla-title">杜甫</h2>--%>
        <%--<div class="layui-colla-content layui-show">1</div>--%>
    <%--</div>--%>
    <%--<div class="layui-colla-item">--%>
        <%--<h2 class="layui-colla-title">李清照</h2>--%>
        <%--<div class="layui-colla-content layui-show">2</div>--%>
    <%--</div>--%>
    <%--<div class="layui-colla-item">--%>
        <%--<h2 class="layui-colla-title">鲁迅</h2>--%>
        <%--<div class="layui-colla-content layui-show">3</div>--%>
    <%--</div>--%>
<%--</div>--%>
<%--<script>--%>
    <%--//注意：折叠面板 依赖 element 模块，否则无法进行功能性操作--%>
    <%--layui.use('element', function(){--%>
        <%--var element = layui.element;--%>

        <%--//…--%>
    <%--});--%>
<%--</script>--%>

<%--11-表格--%>
<%--<table class="layui-table">--%>
    <%--<colgroup>--%>
        <%--<col width="150">--%>
        <%--<col width="200">--%>
        <%--<col>--%>
    <%--</colgroup>--%>
    <%--<thead>--%>
    <%--<tr>--%>
        <%--<th>昵称</th>--%>
        <%--<th>加入时间</th>--%>
        <%--<th>签名</th>--%>
    <%--</tr>--%>
    <%--</thead>--%>
    <%--<tbody>--%>
    <%--<tr>--%>
        <%--<td>贤心</td>--%>
        <%--<td>2016-11-29</td>--%>
        <%--<td>人生就像是一场修行</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
        <%--<td>许闲心</td>--%>
        <%--<td>2016-11-28</td>--%>
        <%--<td>于千万人之中遇见你所遇见的人，于千万年之中，时间的无涯的荒野里…</td>--%>
    <%--</tr>--%>
    <%--</tbody>--%>
<%--</table>--%>

<%--12-徽章--%>


</body>
</html>
