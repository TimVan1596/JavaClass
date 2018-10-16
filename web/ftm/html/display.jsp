<%--
  Menu菜单的JSP版本（JSTL）
  User: r
  Date: 2018/10/15
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>菜单(JSTL版) - biggerDVD</title>
    <style>
        td {
            text-align: center;
            white-space: nowrap;
        }
    </style>
</head>

<body>

<h1>********** 你好！欢迎进入系统菜单(JSTL版) **************</h1>

<table border="1" id="DVDsTable">
    <tr>
        <th></th>
        <th>编号</th>
        <th width="200">名称</th>
        <th width="100">状态</th>
        <th width="100">操作</th>
    </tr>
    <!--填充模板区-->

    <c:forEach var="dvd" items="${requestScope.dvdArr}" >
        <%--判断 if 条件 ，根据status --%>
        <tr style="background-color:
        <c:if test="${dvd.status == true}">
            <c:out value="#f44336ad"/>
                </c:if>">
            <td class="dvd-radio">
                <input class="dvd-radio-input" name="dvd-radio"
                       type="radio" value=""/>
            </td>
            <td class="dvd-id">${dvd.id}</td>
            <td class="dvd-name">${dvd.name}</td>
            <td class="dvd-status">
                <c:if test="${dvd.status == true}">
                    <c:out value="已借出"/>
                </c:if>
                <c:if test="${dvd.status == false}">
                    <c:out value="未借出"/>
                </c:if>
            </td>

            <td>
                <button class="dvd-btn-land"
                        onclick="loanOrReturnDVD(this)">
                    <c:if test="${dvd.status == true}">
                        <c:out value="归还"/>
                    </c:if>
                    <c:if test="${dvd.status == false}">
                        <c:out value="借出"/>
                    </c:if>
                </button>
            </td>
        </tr>
    </c:forEach>


</table>


<section style="margin-top: 2%">
    <button onclick="backToMenu()">返回</button>
</section>
<script>
    function backToMenu() {
        window.location.href = '../menu.jsp';
    }
</script>

</body>
</html>
