<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/16 0016
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SELECT</title>
</head>
<body>
<form action="/fy/servlet/toSelectDvd" method="get" onsubmit="return checkSelect()">
<input  style="margin-left: 20px;margin-top: 20px" type="text" name="selectDVD" id="select"/>
<input type="submit" value="查询">
</form>
<div>
    <table id="tb" style="text-align:center;margin-left: 12px" border="1" width="500">
        <tr><th>DVD编号</th><th>DVD名称</th><th>DVD状态</th><th>删除操作</th></tr>
        <!-- 这里使用标签遍历输出 集合数据 -->
        <c:forEach items="${selectlistDVD }" var="dvd" >
            <tr>
                <td>${dvd.id }</td>
                <td>${dvd.dvdname }</td>
                <c:if test="${dvd.state.equals('可以借')}"><td style="background-color: limegreen"}>${dvd.state }</td></c:if>
                <c:if test="${dvd.state.equals('已借出')}"><td style="background-color: red"}>${dvd.state }</td></c:if>
                <td><input type="button"  value="删除" onclick='window.location.href="/fy/jsp/deleteDVD.jsp?no=${dvd.id }"'></td>
            </tr>
        </c:forEach>

    </table>
</div>
<script>

    function checkSelect() {
        var data=document.getElementById("select").value;
        if(data==""){
            return false;
        }else{
            return true;
        }
    }
</script>
</body>
</html>
