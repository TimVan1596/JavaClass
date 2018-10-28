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
    <title>Title</title>
    <script type="text/javascript" src="/fy/js/echarts.simple.min.js"> </script>
</head>
<body>
<% int cSize=(int)session.getAttribute("cSize");
    int ncSize=(int)session.getAttribute("ncSize");%>
<jsp:include page="navigationBar.jsp"></jsp:include>
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
