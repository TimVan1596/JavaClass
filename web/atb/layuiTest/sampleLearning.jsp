<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\11\12 0012
  Time: 22:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>示例学习</title>
    <meta name="renderer" content="webkit">
    <script src="../../common/util/layui/layui.js" charset="utf-8"></script>
    <link rel="stylesheet" href="../../common/util/layui/css/layui.css" media="all">
</head>
<body>
<%--表单--%>
<%--<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">--%>
    <%--<legend>表单集合演示</legend>--%>
<%--</fieldset>--%>
<%--<form class="layui-form" action="">--%>
    <%--<div class="layui-form-item">--%>
        <%--<label class="layui-form-label">姓名</label>--%>
        <%--<div class="layui-input-block">--%>
            <%--<input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="请输入姓名" class="layui-input">--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="layui-form-item">--%>
        <%--<div class="layui-inline">--%>
            <%--<label class="layui-form-label">验证手机</label>--%>
            <%--<div class="layui-input-inline">--%>
                <%--<input type="tel" name="phone" lay-verify="required|phone" autocomplete="off" class="layui-input">--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="layui-inline">--%>
            <%--<label class="layui-form-label">验证邮箱</label>--%>
            <%--<div class="layui-input-inline">--%>
                <%--<input type="text" name="email" lay-verify="email" autocomplete="off" class="layui-input">--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="layui-form-item">--%>
        <%--<div class="layui-inline">--%>
            <%--<label class="layui-form-label">验证日期</label>--%>
            <%--<div class="layui-input-inline">--%>
                <%--<input type="text" name="date" id="date" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>

    <%--<div class="layui-form-item">--%>
        <%--<label class="layui-form-label">验证身份证</label>--%>
        <%--<div class="layui-input-block">--%>
            <%--<input type="text" name="identity" lay-verify="identity" placeholder="" autocomplete="off" class="layui-input">--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="layui-form-item">--%>
        <%--<div class="layui-inline">--%>
            <%--<label class="layui-form-label">搜索选择框</label>--%>
            <%--<div class="layui-input-inline">--%>
                <%--<select name="modules" lay-verify="required" lay-search="">--%>
                    <%--<option value="">直接选择或搜索选择</option>--%>
                    <%--<option value="1">layer</option>--%>
                    <%--<option value="2">form</option>--%>
                    <%--<option value="3">layim</option>--%>
                    <%--<option value="4">element</option>--%>
                    <%--<option value="5">laytpl</option>--%>
                    <%--<option value="6">upload</option>--%>
                    <%--<option value="7">laydate</option>--%>
                    <%--<option value="8">laypage</option>--%>
                    <%--<option value="9">flow</option>--%>
                    <%--<option value="10">util</option>--%>
                    <%--<option value="11">code</option>--%>
                    <%--<option value="12">tree</option>--%>
                    <%--<option value="13">layedit</option>--%>
                    <%--<option value="14">nav</option>--%>
                    <%--<option value="15">tab</option>--%>
                    <%--<option value="16">table</option>--%>
                    <%--<option value="17">select</option>--%>
                    <%--<option value="18">checkbox</option>--%>
                    <%--<option value="19">switch</option>--%>
                    <%--<option value="20">radio</option>--%>
                <%--</select>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="layui-form-item">--%>
        <%--<label class="layui-form-label">复选框</label>--%>
        <%--<div class="layui-input-block">--%>
            <%--<input type="checkbox" name="like[write]" title="写作">--%>
            <%--<input type="checkbox" name="like[read]" title="阅读">--%>
            <%--<input type="checkbox" name="like[game]" title="游戏" checked="">--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="layui-form-item">--%>
        <%--<label class="layui-form-label">单选框</label>--%>
        <%--<div class="layui-input-block">--%>
            <%--<input type="radio" name="sex" value="男" title="男" checked="">--%>
            <%--<input type="radio" name="sex" value="女" title="女">--%>
            <%--<input type="radio" name="sex" value="禁" title="禁用" disabled="">--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="layui-form-item layui-form-text">--%>
        <%--<label class="layui-form-label">普通文本域</label>--%>
        <%--<div class="layui-input-block">--%>
            <%--<textarea placeholder="请输入内容" class="layui-textarea"></textarea>--%>
        <%--</div>--%>
    <%--</div>--%>
    <%--<div class="layui-form-item">--%>
        <%--<div class="layui-input-block">--%>
            <%--<button class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>--%>
            <%--<button type="reset" class="layui-btn layui-btn-primary">重置</button>--%>
        <%--</div>--%>
    <%--</div>--%>
<%--</form>--%>
<%--<script>--%>
    <%--layui.use(['form', 'layedit', 'laydate'], function(){--%>
        <%--var form = layui.form--%>
            <%--,layer = layui.layer--%>
            <%--,layedit = layui.layedit--%>
            <%--,laydate = layui.laydate;--%>

        <%--//日期--%>
        <%--laydate.render({--%>
            <%--elem: '#date'--%>
        <%--});--%>
        <%--laydate.render({--%>
            <%--elem: '#date1'--%>
        <%--});--%>

        <%--//创建一个编辑器--%>
        <%--var editIndex = layedit.build('LAY_demo_editor');--%>

        <%--//自定义验证规则--%>
        <%--form.verify({--%>
            <%--title: function(value){--%>
                <%--if(value.length < 2){--%>
                    <%--return '标题至少得2个字符啊';--%>
                <%--}--%>
            <%--}--%>
            <%--,pass: [/(.+){6,12}$/, '密码必须6到12位']--%>
            <%--,content: function(value){--%>
                <%--layedit.sync(editIndex);--%>
            <%--}--%>
        <%--});--%>

        <%--//监听提交--%>
        <%--form.on('submit(demo1)', function(data){--%>
            <%--layer.alert(JSON.stringify(data.field), {--%>
                <%--title: '最终的提交信息'--%>
            <%--});--%>
            <%--return false;--%>
        <%--});--%>
    <%--});--%>
<%--</script>--%>

<%--导航--%>
<%--<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">--%>
    <%--<legend>水平导航菜单</legend>--%>
<%--</fieldset>--%>

<%--<ul class="layui-nav">--%>
    <%--<li class="layui-nav-item"><a href="">最新活动</a></li>--%>
    <%--<li class="layui-nav-item layui-this">--%>
        <%--<a href="javascript:;">产品</a>--%>
        <%--<dl class="layui-nav-child">--%>
            <%--<dd><a href="">选项1</a></dd>--%>
            <%--<dd><a href="">选项2</a></dd>--%>
            <%--<dd><a href="">选项3</a></dd>--%>
        <%--</dl>--%>
    <%--</li>--%>
    <%--<li class="layui-nav-item"><a href="">大数据</a></li>--%>
    <%--<li class="layui-nav-item">--%>
        <%--<a href="javascript:;">解决方案</a>--%>
        <%--<dl class="layui-nav-child">--%>
            <%--<dd><a href="">移动模块</a></dd>--%>
            <%--<dd><a href="">后台模版</a></dd>--%>
            <%--<dd class="layui-this"><a href="">选中项</a></dd>--%>
            <%--<dd><a href="">电商平台</a></dd>--%>
        <%--</dl>--%>
    <%--</li>--%>
    <%--<li class="layui-nav-item"><a href="">社区</a></li>--%>
<%--</ul>--%>
<%--<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">--%>
    <%--<legend>导航带徽章和图片</legend>--%>
<%--</fieldset>--%>

<%--<ul class="layui-nav">--%>
    <%--<li class="layui-nav-item">--%>
        <%--<a href="">控制台<span class="layui-badge">9</span></a>--%>
    <%--</li>--%>
    <%--<li class="layui-nav-item">--%>
        <%--<a href="">个人中心<span class="layui-badge-dot"></span></a>--%>
    <%--</li>--%>
    <%--<li class="layui-nav-item" lay-unselect="">--%>
        <%--<a href="javascript:;"><img src="//t.cn/RCzsdCq" class="layui-nav-img">我</a>--%>
        <%--<dl class="layui-nav-child">--%>
            <%--<dd><a href="javascript:;">修改信息</a></dd>--%>
            <%--<dd><a href="javascript:;">安全管理</a></dd>--%>
            <%--<dd><a href="javascript:;">退了</a></dd>--%>
        <%--</dl>--%>
    <%--</li>--%>
<%--</ul>--%>

<%--<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">--%>
    <%--<legend>更多导航主题</legend>--%>
<%--</fieldset>--%>

<%--<ul class="layui-nav layui-bg-cyan">--%>
    <%--<li class="layui-nav-item"><a href="">藏青导航</a></li>--%>
    <%--<li class="layui-nav-item layui-this"><a href="">产品</a></li>--%>
    <%--<li class="layui-nav-item"><a href="">大数据</a></li>--%>
    <%--<li class="layui-nav-item">--%>
        <%--<a href="javascript:;">解决方案</a>--%>
        <%--<dl class="layui-nav-child">--%>
            <%--<dd><a href="">移动模块</a></dd>--%>
            <%--<dd><a href="">后台模版</a></dd>--%>
            <%--<dd><a href="">电商平台</a></dd>--%>
        <%--</dl>--%>
    <%--</li>--%>
    <%--<li class="layui-nav-item"><a href="">社区</a></li>--%>
<%--</ul>--%>
<%--<br>--%>
<%--<ul class="layui-nav layui-bg-green">--%>
    <%--<li class="layui-nav-item"><a href="">墨绿导航</a></li>--%>
    <%--<li class="layui-nav-item layui-this"><a href="">产品</a></li>--%>
    <%--<li class="layui-nav-item"><a href="">大数据</a></li>--%>
    <%--<li class="layui-nav-item">--%>
        <%--<a href="javascript:;">解决方案</a>--%>
        <%--<dl class="layui-nav-child">--%>
            <%--<dd><a href="">移动模块</a></dd>--%>
            <%--<dd><a href="">后台模版</a></dd>--%>
            <%--<dd><a href="">电商平台</a></dd>--%>
        <%--</dl>--%>
    <%--</li>--%>
    <%--<li class="layui-nav-item"><a href="">社区</a></li>--%>
<%--</ul>--%>
<%--<br>--%>
<%--<ul class="layui-nav layui-bg-blue">--%>
    <%--<li class="layui-nav-item"><a href="">艳蓝导航</a></li>--%>
    <%--<li class="layui-nav-item layui-this"><a href="">产品</a></li>--%>
    <%--<li class="layui-nav-item"><a href="">大数据</a></li>--%>
    <%--<li class="layui-nav-item">--%>
        <%--<a href="javascript:;">解决方案</a>--%>
        <%--<dl class="layui-nav-child">--%>
            <%--<dd><a href="">移动模块</a></dd>--%>
            <%--<dd><a href="">后台模版</a></dd>--%>
            <%--<dd><a href="">电商平台</a></dd>--%>
        <%--</dl>--%>
    <%--</li>--%>
    <%--<li class="layui-nav-item"><a href="">社区</a></li>--%>
<%--</ul>--%>

<%--<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">--%>
    <%--<legend>垂直导航菜单</legend>--%>
<%--</fieldset>--%>

<%--<ul class="layui-nav layui-nav-tree layui-inline" lay-filter="demo" style="margin-right: 10px;">--%>
    <%--<li class="layui-nav-item layui-nav-itemed">--%>
        <%--<a href="javascript:;">默认展开</a>--%>
        <%--<dl class="layui-nav-child">--%>
            <%--<dd><a href="javascript:;">选项一</a></dd>--%>
            <%--<dd><a href="javascript:;">选项二</a></dd>--%>
            <%--<dd><a href="javascript:;">选项三</a></dd>--%>
            <%--<dd><a href="">跳转项</a></dd>--%>
        <%--</dl>--%>
    <%--</li>--%>
    <%--<li class="layui-nav-item">--%>
        <%--<a href="javascript:;">解决方案</a>--%>
        <%--<dl class="layui-nav-child">--%>
            <%--<dd><a href="">移动模块</a></dd>--%>
            <%--<dd><a href="">后台模版</a></dd>--%>
            <%--<dd><a href="">电商平台</a></dd>--%>
        <%--</dl>--%>
    <%--</li>--%>
    <%--<li class="layui-nav-item"><a href="">云市场</a></li>--%>
    <%--<li class="layui-nav-item"><a href="">社区</a></li>--%>
<%--</ul>--%>
<%--<ul class="layui-nav layui-nav-tree layui-bg-cyan layui-inline" lay-filter="demo">--%>
    <%--<li class="layui-nav-item layui-nav-itemed">--%>
        <%--<a href="javascript:;">默认展开</a>--%>
        <%--<dl class="layui-nav-child">--%>
            <%--<dd><a href="javascript:;">选项一</a></dd>--%>
            <%--<dd><a href="javascript:;">选项二</a></dd>--%>
            <%--<dd><a href="javascript:;">选项三</a></dd>--%>
            <%--<dd><a href="">跳转项</a></dd>--%>
        <%--</dl>--%>
    <%--</li>--%>
    <%--<li class="layui-nav-item">--%>
        <%--<a href="javascript:;">解决方案</a>--%>
        <%--<dl class="layui-nav-child">--%>
            <%--<dd><a href="">移动模块</a></dd>--%>
            <%--<dd><a href="">后台模版</a></dd>--%>
            <%--<dd><a href="">电商平台</a></dd>--%>
        <%--</dl>--%>
    <%--</li>--%>
    <%--<li class="layui-nav-item"><a href="">云市场</a></li>--%>
    <%--<li class="layui-nav-item"><a href="">社区</a></li>--%>
<%--</ul>--%>

<%--<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">--%>
    <%--<legend>默认面包屑</legend>--%>
<%--</fieldset>--%>

<%--<span class="layui-breadcrumb">--%>
  <%--<a href="/">首页</a>--%>
  <%--<a href="/demo/">演示</a>--%>
  <%--<a><cite>导航元素</cite></a>--%>
<%--</span>--%>

<%--<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">--%>
    <%--<legend>自定义分隔符的面包屑</legend>--%>
<%--</fieldset>--%>

<%--<span class="layui-breadcrumb" lay-separator="—">--%>
  <%--<a href="">首页</a>--%>
  <%--<a href="">国际新闻</a>--%>
  <%--<a href="">亚太地区</a>--%>
  <%--<a><cite>正文</cite></a>--%>
<%--</span>--%>

<%--<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">--%>
    <%--<legend>还可以用于门户频道的面包屑</legend>--%>
<%--</fieldset>--%>

<%--<span class="layui-breadcrumb" lay-separator="|">--%>
  <%--<a href="">娱乐</a>--%>
  <%--<a href="">八卦</a>--%>
  <%--<a href="">体育</a>--%>
  <%--<a href="">搞笑</a>--%>
  <%--<a href="">视频</a>--%>
  <%--<a href="">游戏</a>--%>
  <%--<a href="">综艺</a>--%>
<%--</span>--%>
<%--<script>--%>
    <%--layui.use('element', function(){--%>
        <%--var element = layui.element; //导航的hover效果、二级菜单等功能，需要依赖element模块--%>
        <%--//监听导航点击--%>
        <%--element.on('nav(demo)', function(elem){--%>
            <%--//console.log(elem)--%>
            <%--layer.msg(elem.text());--%>
        <%--});--%>
    <%--});--%>
<%--</script>--%>

<%--时间线--%>
<%--<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">--%>
    <%--<legend>常规时间线</legend>--%>
<%--</fieldset>--%>
<%--<ul class="layui-timeline">--%>
    <%--<li class="layui-timeline-item">--%>
        <%--<i class="layui-icon layui-timeline-axis"></i>--%>
        <%--<div class="layui-timeline-content layui-text">--%>
            <%--<h3 class="layui-timeline-title">8月18日</h3>--%>
            <%--<p>--%>
                <%--layui 2.0 的一切准备工作似乎都已到位。发布之弦，一触即发。--%>
                <%--<br>不枉近百个日日夜夜与之为伴。因小而大，因弱而强。--%>
                <%--<br>无论它能走多远，抑或如何支撑？至少我曾倾注全心，无怨无悔 <i class="layui-icon"></i>--%>
            <%--</p>--%>
        <%--</div>--%>
    <%--</li>--%>
    <%--<li class="layui-timeline-item">--%>
        <%--<i class="layui-icon layui-timeline-axis"></i>--%>
        <%--<div class="layui-timeline-content layui-text">--%>
            <%--<h3 class="layui-timeline-title">8月16日</h3>--%>
            <%--<p>杜甫的思想核心是儒家的仁政思想，他有<em>“致君尧舜上，再使风俗淳”</em>的宏伟抱负。个人最爱的名篇有：</p>--%>
            <%--<ul>--%>
                <%--<li>《登高》</li>--%>
                <%--<li>《茅屋为秋风所破歌》</li>--%>
            <%--</ul>--%>
        <%--</div>--%>
    <%--</li>--%>
    <%--<li class="layui-timeline-item">--%>
        <%--<i class="layui-icon layui-timeline-axis"></i>--%>
        <%--<div class="layui-timeline-content layui-text">--%>
            <%--<h3 class="layui-timeline-title">8月15日</h3>--%>
            <%--<p>--%>
                <%--中国人民抗日战争胜利日--%>
                <%--<br>常常在想，尽管对这个国家有这样那样的抱怨，但我们的确生在了最好的时代--%>
                <%--<br>铭记、感恩--%>
                <%--<br>所有为中华民族浴血奋战的英雄将士--%>
                <%--<br>永垂不朽--%>
            <%--</p>--%>
        <%--</div>--%>
    <%--</li>--%>
    <%--<li class="layui-timeline-item">--%>
        <%--<i class="layui-icon layui-timeline-axis"></i>--%>
        <%--<div class="layui-timeline-content layui-text">--%>
            <%--<div class="layui-timeline-title">过去</div>--%>
        <%--</div>--%>
    <%--</li>--%>
<%--</ul>--%>

<%--<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">--%>
    <%--<legend>简约时间线：大事记</legend>--%>
<%--</fieldset>--%>
<%--<ul class="layui-timeline">--%>
    <%--<li class="layui-timeline-item">--%>
        <%--<i class="layui-icon layui-timeline-axis"></i>--%>
        <%--<div class="layui-timeline-content layui-text">--%>
            <%--<div class="layui-timeline-title">2018年，layui 5.0 发布。并发展成为中国最受欢迎的前端UI框架（期望）</div>--%>
        <%--</div>--%>
    <%--</li>--%>
    <%--<li class="layui-timeline-item">--%>
        <%--<i class="layui-icon layui-timeline-axis"></i>--%>
        <%--<div class="layui-timeline-content layui-text">--%>
            <%--<div class="layui-timeline-title">2017年，layui 里程碑版本 2.0 发布</div>--%>
        <%--</div>--%>
    <%--</li>--%>
    <%--<li class="layui-timeline-item">--%>
        <%--<i class="layui-icon layui-timeline-axis"></i>--%>
        <%--<div class="layui-timeline-content layui-text">--%>
            <%--<div class="layui-timeline-title">2016年，layui 首个版本发布</div>--%>
        <%--</div>--%>
    <%--</li>--%>
    <%--<li class="layui-timeline-item">--%>
        <%--<i class="layui-icon layui-timeline-axis"></i>--%>
        <%--<div class="layui-timeline-content layui-text">--%>
            <%--<div class="layui-timeline-title">2015年，layui 孵化</div>--%>
        <%--</div>--%>
    <%--</li>--%>
    <%--<li class="layui-timeline-item">--%>
        <%--<i class="layui-icon layui-anim layui-anim-rotate layui-anim-loop layui-timeline-axis"></i>--%>
        <%--<div class="layui-timeline-content layui-text">--%>
            <%--<div class="layui-timeline-title">更久前，轮子时代。维护几个独立组件：layer等</div>--%>
        <%--</div>--%>
    <%--</li>--%>
<%--</ul>--%>

<%--辅助元素--%>
<%--<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">--%>
    <%--<legend>纯圆角</legend>--%>
<%--</fieldset>--%>
<%--<div class="layui-inline">--%>
    <%--<img src="//t.cn/RCzsdCq" class="layui-nav-img">--%>
    <%--<img src="//t.cn/RCzsdCq" class="layui-circle">--%>
<%--</div>--%>

<%--弹出层--%>
<%--<div class="site-demo-button" id="layerDemo" style="margin-bottom: 0;">--%>
    <%--<button data-method="notice" class="layui-btn">示范一个公告层</button>--%>
<%--</div>--%>
<%--<script>--%>
    <%--layui.use('layer', function(){ //独立版的layer无需执行这一句--%>
        <%--var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句--%>
        <%--//触发事件--%>
        <%--var active = {--%>
            <%--notice: function(){--%>
                <%--//示范一个公告层--%>
                <%--layer.open({--%>
                    <%--type: 1--%>
                    <%--,title: false //不显示标题栏--%>
                    <%--,closeBtn: false--%>
                    <%--,area: '300px;'--%>
                    <%--,shade: 0.8--%>
                    <%--,id: 'LAY_layuipro' //设定一个id，防止重复弹出--%>
                    <%--,btn: ['火速围观', '残忍拒绝']--%>
                    <%--,btnAlign: 'c'--%>
                    <%--,moveType: 1 //拖拽模式，0或者1--%>
                    <%--,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">你知道吗？亲！<br>layer ≠ layui<br><br>layer只是作为Layui的一个弹层模块，由于其用户基数较大，所以常常会有人以为layui是layerui<br><br>layer虽然已被 Layui 收编为内置的弹层模块，但仍然会作为一个独立组件全力维护、升级。<br><br>我们此后的征途是星辰大海 ^_^</div>'--%>
                    <%--,success: function(layero){--%>
                        <%--var btn = layero.find('.layui-layer-btn');--%>
                        <%--btn.find('.layui-layer-btn0').attr({--%>
                            <%--href: 'http://www.layui.com/'--%>
                            <%--,target: '_blank'--%>
                        <%--});--%>
                    <%--}--%>
                <%--});--%>
            <%--}--%>
            <%--,offset: function(othis){--%>
                <%--var type = othis.data('type')--%>
                    <%--,text = othis.text();--%>
                <%--layer.open({--%>
                    <%--type: 1--%>
                    <%--,offset: type //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset--%>
                    <%--,id: 'layerDemo'+type //防止重复弹出--%>
                    <%--,content: '<div style="padding: 20px 100px;">'+ text +'</div>'--%>
                    <%--,btn: '关闭全部'--%>
                    <%--,btnAlign: 'c' //按钮居中--%>
                    <%--,shade: 0 //不显示遮罩--%>
                    <%--,yes: function(){--%>
                        <%--layer.closeAll();--%>
                    <%--}--%>
                <%--});--%>
            <%--}--%>
        <%--};--%>
        <%--$('#layerDemo .layui-btn').on('click', function(){--%>
            <%--var othis = $(this), method = othis.data('method');--%>
            <%--active[method] ? active[method].call(this, othis) : '';--%>
        <%--});--%>
    <%--});--%>
<%--</script>--%>

<%--数据表格--%>
<%--<table class="layui-hide" id="test" lay-filter="test"></table>--%>
<%--<script type="text/html" id="barDemo">--%>
    <%--<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>--%>
    <%--<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>--%>
<%--</script>--%>
<%--<script>--%>
    <%--layui.use('table', function(){--%>
        <%--var table = layui.table;--%>

        <%--table.render({--%>
            <%--elem: '#test'--%>
            <%--,url:'./user'--%>
            <%--,cellMinWidth: 80--%>
            <%--,toolbar: true--%>
            <%--,title: '用户数据表'--%>
            <%--,cols: [[--%>
                <%--{type: 'checkbox', fixed: 'left'}--%>
                <%--,{field:'id', title:'ID', fixed: 'left', unresize: true, sort: true}--%>
                <%--,{field:'username', title:'用户名', edit: 'text'}--%>
                <%--,{field:'email', title:'邮箱', edit: 'text', templet: function(res){--%>
                        <%--return '<em>'+ res.email +'</em>'--%>
                    <%--}}--%>
                <%--,{field:'sex', title:'性别', edit: 'text', sort: true}--%>
                <%--,{field:'city', title:'城市'}--%>
                <%--,{field:'sign', title:'签名'}--%>
                <%--,{field:'experience', title:'积分', sort: true}--%>
                <%--,{field:'ip', title:'IP'}--%>
                <%--,{field:'logins', title:'登入次数', sort: true}--%>
                <%--,{field:'joinTime', title:'加入时间'}--%>
                <%--,{fixed: 'right', title:'操作', toolbar: '#barDemo'}--%>
            <%--]]--%>
            <%--,page: true--%>
        <%--});--%>

        <%--//监听单元格编辑--%>
        <%--table.on('edit(test)', function(obj){--%>
            <%--var value = obj.value //得到修改后的值--%>
                <%--,data = obj.data //得到所在行所有键值--%>
                <%--,field = obj.field; //得到字段--%>
            <%--layer.msg('[ID: '+ data.id +'] ' + field + ' 字段更改为：'+ value);--%>
        <%--});--%>

        <%--//监听行工具事件--%>
        <%--table.on('tool(test)', function(obj){--%>
            <%--var data = obj.data;--%>
            <%--//console.log(obj)--%>
            <%--if(obj.event === 'del'){--%>
                <%--layer.confirm('真的删除行么', function(index){--%>
                    <%--obj.del();--%>
                    <%--layer.close(index);--%>
                <%--});--%>
            <%--} else if(obj.event === 'edit'){--%>
                <%--layer.prompt({--%>
                    <%--formType: 2--%>
                    <%--,value: data.email--%>
                <%--}, function(value, index){--%>
                    <%--obj.update({--%>
                        <%--email: value--%>
                    <%--});--%>
                    <%--layer.close(index);--%>
                <%--});--%>
            <%--}--%>
        <%--});--%>
    <%--});--%>
<%--</script>--%>

<%--分页--%>
<%--<div id="demo20"></div>--%>
<%--<ul id="biuuu_city_list"></ul>--%>
<%--<script>--%>
    <%--layui.use(['laypage', 'layer'], function(){--%>
        <%--var laypage = layui.laypage;--%>
        <%--//将一段数组分页展示--%>
        <%--//测试数据--%>
        <%--var data = [--%>
            <%--'北京',--%>
            <%--'上海',--%>
            <%--'广州',--%>
            <%--'深圳',--%>
            <%--'杭州',--%>
            <%--'长沙',--%>
            <%--'合肥',--%>
            <%--'宁夏',--%>
            <%--'成都',--%>
            <%--'西安',--%>
            <%--'南昌',--%>
            <%--'上饶',--%>
            <%--'沈阳',--%>
            <%--'济南',--%>
            <%--'厦门',--%>
            <%--'福州',--%>
            <%--'九江',--%>
            <%--'宜春',--%>
            <%--'赣州',--%>
            <%--'宁波',--%>
            <%--'绍兴',--%>
            <%--'无锡',--%>
            <%--'苏州',--%>
            <%--'徐州',--%>
            <%--'东莞',--%>
            <%--'佛山',--%>
            <%--'中山',--%>
            <%--'成都',--%>
            <%--'武汉',--%>
            <%--'青岛',--%>
            <%--'天津',--%>
            <%--'重庆',--%>
            <%--'南京',--%>
            <%--'九江',--%>
            <%--'香港',--%>
            <%--'澳门',--%>
            <%--'台北'--%>
        <%--];--%>
        <%--//调用分页--%>
        <%--laypage.render({--%>
            <%--elem: 'demo20'--%>
            <%--,count: data.length--%>
            <%--,jump: function(obj){--%>
                <%--//模拟渲染--%>
                <%--document.getElementById('biuuu_city_list').innerHTML = function(){--%>
                    <%--var arr = []--%>
                        <%--,thisData = data.concat().splice(obj.curr*obj.limit - obj.limit, obj.limit);--%>
                    <%--layui.each(thisData, function(index, item){--%>
                        <%--arr.push('<li>'+ item +'</li>');--%>
                    <%--});--%>
                    <%--return arr.join('');--%>
                <%--}();--%>
            <%--}--%>
        <%--});--%>
    <%--});--%>
<%--</script>--%>

<%--颜色选择器--%>
<%--<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">--%>
    <%--<legend>预定义颜色项</legend>--%>
<%--</fieldset>--%>
<%--<div style="margin-left: 30px;">--%>
    <%--<div id="test8"></div>--%>
<%--</div>--%>
<%--<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">--%>
    <%--<legend>全功能和回调的使用</legend>--%>
<%--</fieldset>--%>
<%--<div style="margin-left: 30px;">--%>
    <%--<input type="hidden" name="color" value="" id="test-all-input">--%>
    <%--<div id="test-all"></div>--%>
<%--</div>--%>
<%--<script>--%>
    <%--layui.use('colorpicker', function(){--%>
        <%--var $ = layui.$--%>
            <%--,colorpicker = layui.colorpicker;--%>
        <%--//预定义颜色项--%>
        <%--colorpicker.render({--%>
            <%--elem: '#test8'--%>
            <%--,color: '#c71585'--%>
            <%--,predefine: true // 开启预定义颜色--%>
        <%--});--%>
        <%--//开启全功能--%>
        <%--colorpicker.render({--%>
            <%--elem: '#test-all'--%>
            <%--,color: 'rgba(7, 155, 140, 1)'--%>
            <%--,format: 'rgb'--%>
            <%--,predefine: true--%>
            <%--,alpha: true--%>
            <%--,done: function(color){--%>
                <%--$('#test-all-input').val(color); //向隐藏域赋值--%>
                <%--layer.tips('给指定隐藏域设置了颜色值：'+ color, this.elem);--%>

                <%--color || this.change(color); //清空时执行 change--%>
            <%--}--%>
            <%--,change: function(color){--%>
                <%--//给当前页面头部和左侧设置主题色--%>
                <%--$('.header-demo,.layui-side .layui-nav').css('background-color', color);--%>
            <%--}--%>
        <%--});--%>
    <%--});--%>
<%--</script>--%>


</body>
</html>
