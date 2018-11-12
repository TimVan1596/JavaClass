<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/26 0026
  Time: 21:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>柱形图</title>
    <script type="text/javascript" src="/fy/js/echarts.simple.min.js"> </script>
    <link rel="stylesheet" href="/common/util/layui/css/layui.css"  media="all">
    <style>
        ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
            overflow: hidden;
            border: 1px solid #e7e7e7;
            background-color: #f3f3f3;
        }
        li {
            float:left;
        }
        li a{
            display: block;
            color: #666;
            text-align: center;
            padding: 24px 34px;
            text-decoration-line: none;
        }
        li a:hover{
            background-color: #ddd;
        }
        li a.active{
            color:white;
            background-color: #4CAF50;
        }
    </style>
</head>
<body>
<% int cSize=(int)session.getAttribute("cSize");
    int ncSize=(int)session.getAttribute("ncSize");%>

<ul>
    <li><a href="/fy/servlet/toShowDvd" >主页</a></li>
    <li><a class="active" href="column.jsp">柱形图</a></li>
    <li><a href="/fy/recycle">回收站<span class="layui-badge"><%=(int)session.getAttribute("recycle")%></span></a></li>
    <li><a href="/fy/servlet/toCancelling" style="margin-left:1200px;">退出登录</a></li>
</ul>
<h3 style="margin-left: 240px">DVD状态柱形图</h3>
<div id="main" style="width: 400px;height:350px;margin-left: 190px;">

</div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    // 指定图表的配置项和数据
    var option = {
        title: {
            text: '柱形图'
        },
        tooltip: {},
        legend: {
            data:['数量']
        },
        xAxis: {
            data: ["可以借","已借出"]
        },
        yAxis: {},
        series: [{
            name: '数量',
            type: 'bar',
            data:[<%=cSize%>,<%=ncSize%>],
            itemStyle:{
                normal:{
                    color: function (params){
                        var colorList = ['green','red'];
                        return colorList[params.dataIndex];
                    }
                }
            }
        }]
    };
    // 使用刚指定的配置项和数据显示图表
    myChart.setOption(option);
</script>

</body>
</html>
