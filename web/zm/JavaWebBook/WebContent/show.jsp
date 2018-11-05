<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>图书信息</title>
	<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">  
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<h1>欢迎您,${LOGIN.username}</h1>
<form action="CheckBook?param=search" method="post">
<input type="text" name="searchs" > <span class="glyphicon glyphicon-search"></span>
<input type="submit" value="搜一搜">
</form>

 
       
<a href="addbook.jsp">新增</a>	
<form action="CheckBook?param=delete" method="post">
<table class="table">
	<caption><em><b>图书信息</b></em></caption>
	<thead>
		<tr>
			<th class="col-md-2"><span class="glyphicon glyphicon-check"></span>选择</th>
			<th><span class="glyphicon glyphicon-leaf">编号</span></th>
			<th><span class="glyphicon glyphicon-registration-mark">类型</span></th>
			<th><span class="glyphicon glyphicon-adjust">图书名</span></th>
			<th><span class="glyphicon glyphicon-user">作者</span></th>
			<th><span class="glyphicon glyphicon-usd">价格</span></th>
			<th><span class="glyphicon glyphicon-briefcase">出版日期</span></th>
			<th><span class="glyphicon glyphicon-edit">操作</span></th>
		</tr>
	</thead>
	<tbody>

<c:forEach var="book" items="${BOOKS}">
<tr>
<td><input type="checkbox" name="select" value="${book.bookid}"><span class="glyphicon glyphicon-check"></span></td>
<td>${book.bookid}</td>
<td>${book.type.typename}</td>
<td>${book.bookname}</td>
<td>${book.bookauthor}</td>
<td>${book.bookprice}</td>
<td>${book.booktime}</td>
<td><a href="CheckBook?id=${book.bookid}">修改</a></td>

</tr>
</c:forEach>
<tr>
<td>
   <input type="submit" value="删除">
   </td>
   </tr>
    
	</tbody>
</table>
</form>
</body>
</html>

