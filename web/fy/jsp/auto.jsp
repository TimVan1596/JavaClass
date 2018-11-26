<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/9 0009
  Time: 8:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>奖牌榜</title>
    <link rel="stylesheet" href="/common/util/layui/css/layui.css"  media="all">
</head>
<body>
<div style="margin-left: 25%">
<table  class="layui-hide" id="test" >
</table>
</div>
    <div id="main" style="width: 100%;height: 500px">
    </div>
</div>
</div>
<script type="text/javascript" src="/fy/js/echarts.min.js"> </script>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script src="/common/util/layui/layui.js" charset="utf-8"></script>
<script>
    layui.use('table', function(){
        var table = layui.table;
        table.render({
            elem: '#test'
            ,url:'/auto/show'
            ,toolbar: true
            ,totalRow: true
            ,width:928
            ,cols: [[
                {type:'numbers'}
                ,{field:'country', title:'国家', width:130}
                ,{field:'photo', title:'国旗', width:110,templet:'<div><img src="{{ d.photo}}"></div>'}
                ,{field:'goldMedal', title:'金牌', width:160,sort: true}
                ,{field:'silverMedal', title:'银牌', width:160,sort: true}
                ,{field:'bronzeMedal', title:'铜牌', width:160,sort: true}
                ,{field:'total', title:'总数', width:160,sort: true}
            ]]
        });
    });
</script>
<script type="text/javascript">
    // 初始化图表标签
    var myChart = echarts.init(document.getElementById('main'));
    var options={
        //定义一个标题
        title:{
            text:'2016奥运'
        },
        legend:{
            data:['金牌', '银牌', '铜牌', '总数']
        },
        //X轴设置
        xAxis:{
            data:['美国', '英国', '中国', '俄罗斯',
                '德国', '日本', '法国', '韩国', '意大利']
        },
        yAxis:{
        },

        series: [
            {
                name: '金牌',
                type: 'bar',
                data: [46, 27, 26, 19, 17, 12, 10, 9, 8]
            },
            {
                name: '银牌',
                type: 'bar',
                data: [37, 23, 18, 18, 10, 18, 18, 3, 12]
            },
            {
                name: '铜牌',
                type: 'bar',
                data: [38, 17, 26, 19, 15, 21, 14, 9, 8]
            },
            {
                name: '总数',
                type: 'bar',
                data: [121, 67, 70, 56, 42, 41, 42, 21, 28]
            }
        ]

    };
    myChart.setOption(options);

</script>

</body>
</html>
