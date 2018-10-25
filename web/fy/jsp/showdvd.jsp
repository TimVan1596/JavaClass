<%@ page import="java.util.ArrayList" %>
<%@ page import="com.smallfangyu.data.DVD" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/8 0008
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    session.getAttribute("listDVD");
    //把SESSION的dvd数据放进集合里
    ArrayList<DVD> list=(ArrayList<DVD>)session.getAttribute("listDVD");
    int pag= (int) session.getAttribute("page");
    int pageNumber= (int) session.getAttribute("pageNumber");
    int cSize=(int)session.getAttribute("cSize");
    int ncSize=(int)session.getAttribute("ncSize");
%>
<html>
<head>
    <title>显示DVD</title>
    <script type="text/javascript" src="/fy/js/echarts.simple.min.js"> </script>
</head>
<body>
<jsp:include page="navigationBar.jsp"></jsp:include>
<div  style="text-align:center">
    <h3>DVD列表</h3>

    <form action="/fy/servlet/toShowDvd" method="get" onsubmit="return checkSelect()">
        <input  style="margin-left: 20px;margin-top: 20px" type="text" name="selectDVD" id="select"/>
        <input type="submit" value="查询">
    </form>

    <table id="tb" style="text-align:center;margin: 0 auto " border="1" >
        <tr><th>DVD编号</th><th>DVD预览</th><th>DVD名称</th><th>DVD状态</th><th>删除操作</th></tr>
        <% for(DVD dvd:list){ %>
        <tr>
            <td style="width:100px"><%=dvd.getId() %></td>
            <td><img style="width:100px;height:80px" src="<%= dvd.getPicture()%>" /></td>
            <td style="width:100px"> <%=dvd.getDvdname() %></td>
            <% if(dvd.getState().equals("可以借")){%><td style="width:100px;background-color: limegreen"><%=dvd.getState() %></td><% }%>
            <% if(dvd.getState().equals("已借出")){%><td style="width:100px;background-color: red"><%=dvd.getState() %></td><% }%>
            <td style="width:100px"><input type="button"  value="删除" onclick='window.location.href="/fy/jsp/deleteDVD.jsp?no=<%=dvd.getId() %>"'></td>
        </tr>
   <%  }%>
        <!-- 这里使用标签遍历输出 集合数据 -->
        <%--<c:forEach items="${listDVD }" var="dvd" >--%>
            <%--<tr>--%>
                <%--<td style="width:100px">${dvd.id }</td>--%>
                <%--<td><img style="width:100px;height:80px" src="${dvd.picture }" /></td>--%>
                <%--<td style="width:100px">${dvd.dvdname }</td>--%>
                <%--<c:if test="${dvd.state.equals('可以借')}"><td style="width:100px;background-color: limegreen"}>${dvd.state }</td></c:if>--%>
                <%--<c:if test="${dvd.state.equals('已借出')}"><td style="width:100px;background-color: red"}>${dvd.state }</td></c:if>--%>
                <%--<td style="width:100px"><input type="button"  value="删除" onclick='window.location.href="/fy/jsp/deleteDVD.jsp?no=${dvd.id }"'></td>--%>
            <%--</tr>--%>
        <%--</c:forEach>--%>
    </table>
    <p>
        <a href="/fy/servlet/toShowDvd?page=<%=0%>">&lt;&lt; 首页 </a>
        <a href="/fy/servlet/toShowDvd?page=<%=pag-1 %>">    &lt; 上一页 </a>
        <strong>第<%=pag+1 %>页/共<%=pageNumber+1 %>页</strong>
        <a href="/fy/servlet/toShowDvd?page=<%=pag+1 %>">下一页 &gt;</a>
        <a href="/fy/servlet/toShowDvd?page=<%=pageNumber %>">末页 &gt;&gt;</a>
    </p>
</div>
<div style="width: 800px;margin: 0 auto">
    <div style="width:100px;float:left">
        <br>
        <a href="addDVD.jsp" target="mainFrame" >添加DVD</a><br>
        <a href="changeDVD.jsp" target="mainFrame" >修改DVD</a><br>
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

    function checkSelect() {
        var data=document.getElementById("select").value;
        if(data==""){
            alert("查询内容不能为空");
            return false;
        }else{
            return true;
        }
    }

</script>

</body>
</html>
