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
<div id="main" style="width: 1000px;height:600px;"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    // 指定图表的配置项和数据
    <%
    JDBCUtilDvd jdbcUtilDvd = new JDBCUtilDvd();
    List<Dvd> dvds = jdbcUtilDvd.queryStu();
    %>
    var option = {
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            data: ['借出', '剩余']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis:  {
            type: 'value'
        },
        yAxis: {
            type: 'category',
            <%
            String name = null;
            for (Dvd dvd : dvds) {
                if(dvd.getNo() == 1000){
                    name = "'" + dvd.getName() + "'";
                }else{
                    name = name + ",'" + dvd.getName() + "'";
                }
            }
            %>
            data :[<%= name %>]
            //data: ['周一','周二','周三','周四','周五','周六','周日']
        },
        series: [
            {
                name: '借出',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                <%
                String borrow = null;
                for (Dvd dvd : dvds) {
                    if(dvd.getNo() == 1000){
                        borrow = "'" + dvd.getBorrow() + "'";
                    }else{
                        borrow = borrow + ",'" + dvd.getBorrow() + "'";
                    }
                }
                %>
                data :[<%= borrow %>]
                //data: [320, 302, 301, 334, 390, 330, 320]
            },
            {
                name: '剩余',
                type: 'bar',
                stack: '总量',
                label: {
                    normal: {
                        show: true,
                        position: 'insideRight'
                    }
                },
                <%
                String state = null;
                for (Dvd dvd : dvds) {
                    if(dvd.getNo() == 1000){
                        state = "'" + (dvd.getState()-dvd.getBorrow()) + "'";
                    }else{
                        state = state + ",'" + (dvd.getState()-dvd.getBorrow()) + "'";
                    }
                }
                %>
                data :[<%= state %>]
                //data: [120, 132, 101, 134, 90, 230, 210]
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
</script>
<a href = '../../../../login.do'><button>返回</button></a>
</body>
</html>
