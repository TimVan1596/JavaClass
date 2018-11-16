<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\11\13 0013
  Time: 8:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Layui表格</title>
    <style>
        body{margin: 10px;}
        .demo-carousel{height: 200px; line-height: 200px; text-align: center;}
    </style>
    <%--layui引用--%>
    <script src="../../common/util/layui/layui.js"></script>
    <link rel="stylesheet" href="../../common/util/layui/css/layui.css">
</head>
<body>
<%--轮播--%>
<div class="layui-carousel" id="test10">
    <div carousel-item="">
        <div><p class="layui-bg-green demo-carousel">哎喂吧得！</p></div>
        <div><p class="layui-bg-red demo-carousel">苦酒韩瑟！</p></div>
        <div><p class="layui-bg-blue demo-carousel">噔噔噔！</p></div>
        <div><p class="layui-bg-orange demo-carousel">索雷亚卡通</p></div>
        <div><p class="layui-bg-cyan demo-carousel">孤儿亚索卡顿E</p></div>
    </div>
</div>
<%--表格--%>
<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="getCheckData">
            <i class="layui-icon layui-icon-add-1"></i>添加数据
        </button>
    </div>
</script>
<table class="layui-hide" id="test" lay-filter="test"></table>
<script>
    layui.use(['table','carousel'], function(){
        let table = layui.table,
            carousel = layui.carousel;
        //图片轮播
        carousel.render({
            elem: '#test10'
            ,width: '100%' //设置容器宽度
            ,height: 200
            ,anim: 'fade' //切换动画方式
        });

        //展示已知数据
        table.render({
            elem: '#test'
            ,cellMinWidth: 80
            // ,toolbar: true
            ,toolbar: '#toolbarDemo'
            ,cols: [[ //标题栏
                {field: 'countries', title: '国家', sort: true}
                ,{field: 'nationalFlag', title: '国旗'}
                ,{field: 'gold', title: '金牌', sort: true, edit: 'text'}
                ,{field: 'silver', title: '银牌', sort: true, edit: 'text'}
                ,{field: 'copper', title: '铜牌', sort: true, edit: 'text'}
                ,{field: 'number', title: '总数', sort: true, edit: 'text'}
            ]]
            ,data: [{
                "countries": "美国"
                ,"nationalFlag": "<img src=\"http://www.sinaimg.cn/ty/08ay/data/logo/new/USA.jpg\" width=\"30\" alt=\"美国\">"
                ,"gold": "46"
                ,"silver": "37"
                ,"copper": "38"
                ,"number": "121"
            }, {
                "countries": "英国"
                ,"nationalFlag": "<img src=\"http://www.sinaimg.cn/ty/08ay/data/logo/new/GBR.jpg\" width=\"30\" alt=\"英国\">"
                ,"gold": "27"
                ,"silver": "23"
                ,"copper": "17"
                ,"number": "67"
            }, {
                "countries": "中国"
                ,"nationalFlag": "<img src=\"http://www.sinaimg.cn/ty/08ay/data/logo/new/CHN.jpg\" width=\"30\" alt=\"中国\">"
                ,"gold": "26"
                ,"silver": "18"
                ,"copper": "26"
                ,"number": "70"
            }, {
                "countries": "俄罗斯"
                ,"nationalFlag": "<img src=\"http://www.sinaimg.cn/ty/08ay/data/logo/new/RUS.jpg\" width=\"30\" alt=\"俄罗斯\">"
                ,"gold": "19"
                ,"silver": "18"
                ,"copper": "19"
                ,"number": "56"
            }, {
                "countries": "德国"
                ,"nationalFlag": "<img src=\"http://www.sinaimg.cn/ty/08ay/data/logo/new/GER.jpg\" width=\"30\" alt=\"德国\">"
                ,"gold": "17"
                ,"silver": "10"
                ,"copper": "15"
                ,"number": "42"
            }, {
                "countries": "日本"
                ,"nationalFlag": "<img src=\"http://www.sinaimg.cn/ty/08ay/data/logo/new/JPN.jpg\" width=\"30\" alt=\"日本\">"
                ,"gold": "12"
                ,"silver": "8"
                ,"copper": "21"
                ,"number": "41"
            }, {
                "countries": "法国"
                ,"nationalFlag": "<img src=\"http://www.sinaimg.cn/ty/08ay/data/logo/new/FRA.jpg\" width=\"30\" alt=\"法国\">"
                ,"gold": "10"
                ,"silver": "18"
                ,"copper": "14"
                ,"number": "42"
            }, {
                "countries": "韩国"
                ,"nationalFlag": "<img src=\"http://www.sinaimg.cn/ty/08ay/data/logo/new/KOR.jpg\" width=\"30\" alt=\"韩国\">"
                ,"gold": "9"
                ,"silver": "3"
                ,"copper": "9"
                ,"number": "21"
            }, {
                "countries": "意大利"
                ,"nationalFlag": "<img src=\"http://www.sinaimg.cn/ty/08ay/data/logo/new/ITA.jpg\" width=\"30\" alt=\"意大利\">"
                ,"gold": "8"
                ,"silver": "12"
                ,"copper": "8"
                ,"number": "28"
            }]
            ,even: true
        });

        //头工具栏事件
        table.on('toolbar(test)', function(obj){
            switch(obj.event){
                case 'getCheckData':
                    layer.open({
                        type: 2,
                        title: '添加',
                        shadeClose: true,
                        shade: 0.3,
                        area: ['50%', '50%'],
                        content: './layuiForm.jsp' //iframe的url
                    });
                    break;
            }
        });

        //监听单元格编辑
        table.on('edit(test)', function(obj){
            let value = obj.value //得到修改后的值
                ,data = obj.data //得到所在行所有键值
                ,field = obj.field; //得到字段
            layer.msg('[国家: '+ data.countries +'] ' + field + ' 更改为：'+ value);
        });
    });
</script>
</body>
</html>
