<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/8 0008
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<% if(session.getAttribute("loginName")==null){
    response.sendRedirect("login.jsp");
}
%>
<html>
<head>
    <title>显示DVD</title>
    <script type="text/javascript" src="/fy/js/echarts.simple.min.js"> </script>
</head>
<body>
<div style="text-align:center">
    <h3>DVD列表</h3>
    <table id="tb" style="text-align:center;margin: 0 auto " border="1" width="500">
        <tr><th>DVD编号</th><th>DVD名称</th><th>DVD状态</th><th>删除操作</th></tr>
        <!-- i,j分别为可以借和已借出的初始值>
        <%  int i=0;
            int j=0;%>
        <!-- 这里使用标签遍历输出 集合数据 -->
        <c:forEach items="${listDVD }" var="dvd" >
            <tr>
                <td>${dvd.id }</td>
                <td>${dvd.dvdname }</td>
                <c:if test="${dvd.state.equals('可以借')}"><%i++;%><td style="background-color: limegreen"}>${dvd.state }</td></c:if>
                <c:if test="${dvd.state.equals('已借出')}"><%j++;%><td style="background-color: red"}>${dvd.state }</td></c:if>
                <td><input type="button"  value="删除" onclick='window.location.href="/fy/jsp/deleteDVD.jsp?no=${dvd.id }"'></td>
            </tr>
        </c:forEach>

    </table>
</div>
<div style="width: 800px;margin: 0 auto">
    <div style="width:100px;float:left">
        <br>
        <a href="addDVD.jsp" target="mainFrame" >添加DVD</a><br>
        <a href="changeDVD.jsp" target="mainFrame" >修改DVD</a><br>
        <a href="selectDVD.jsp" target="mainFrame" >查询DVD</a><br>
    </div>
    <iframe style="width:550px;height:200px;margin-left:30px"   name="mainFrame" frameborder="0"></iframe>
</div>
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
            data: [<%=i%>,<%=j%>],
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
