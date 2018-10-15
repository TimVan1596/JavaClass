<%@ page import="web.atb.javaWebDvd.dvd.JDBCUtilDvd" %>
<%@ page import="web.atb.javaWebDvd.dvd.Dvd" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\10\15 0015
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>数据显示</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/echarts/4.1.0.rc2/echarts.min.js"></script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    // 指定图表的配置项和数据
    var OnLoan = 0;
    var CanBorrow = 0;
    <%
    JDBCUtilDvd jdbcUtilDvd = new JDBCUtilDvd();
    List<Dvd> dvds = jdbcUtilDvd.queryStu();
    for (Dvd dvd : dvds) {
        if (dvd.getState().equals("可以借")) {
    %>
            CanBorrow++;
    <%
        }else if (dvd.getState().equals("已借出")) {
    %>
            OnLoan++;
    <%
        }
    }
    %>
    var option = {
        title: {
            text: '图书状态'
        },
        tooltip: {},
        legend: {
            data:['数量']
        },
        xAxis: {
            data: ["已借出","可以借"]
        },
        yAxis: {},
        series: [{
            name: '数量',
            type: 'bar',
            data: [OnLoan, CanBorrow]
        }]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>
<a href = '../../../../login.do'><button>返回</button></a>
</body>
</html>
