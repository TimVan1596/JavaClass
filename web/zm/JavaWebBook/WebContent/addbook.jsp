<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>新增图书信息</h1>
<form action="CheckBook?param=add" method="post" >
<table>
<tr>
<td>图书名</td>
<td> <input type="text" name="bookname"></td>
</tr>
<tr>
<td>作者</td>
<td><input type="text" name="bookauthor"></td>
</tr>
<tr>
<td>价格</td>
<td><input type="text" name="bookprice"></td>
</tr>
<tr>
<td>出版日期</td>
<td><input type="text" name="booktime"></td>
<tr>
<tr>
<td>
<select name="type">
<option value="1">文学类</option>
<option value="2">小说类</option>
<option value="3">自然科学类</option>
<option value="4">哲学类</option>
<option value="5">军事科技类</option>
</select>
</td>
</tr>
<tr>
<td>
<input type="submit" value="确定">
</td>
</tr>
</table>
</form>
</body>
</html>