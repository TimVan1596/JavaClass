<%@ page import="java.util.Map" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Enumeration" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/12/012
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>request2</title>
</head>
<body>
<%
    String name=request.getParameter("userName");
    //out输出内容
    out.print(name);
    out.print("<br>");
    String password = request.getParameter("userPassword");
    out.print(password);
    out.print("<br>");
    String[] favs = request.getParameterValues("fav");
    for (String fav:favs)
    {
        out.print(fav);
        out.print("<br>");
    }
   //获取到所以的参数
    Map pMaps = request.getParameterMap();
    Iterator iterator = pMaps.keySet().iterator();
    while(iterator.hasNext()){
        String key = iterator.next().toString();
        out.print(key);
        out.print("<br>");
        String[] values =(String[]) pMaps.get(key);
        out.print(values[0]);
        out.print("<br>");
    }
//获取请求头信息
    out.print("头部信息");
    out.print("<br>");
    Enumeration enums = request.getHeaderNames();
    while (enums.hasMoreElements()){
        String headerName =  enums.nextElement().toString();
        out.print(headerName);
        out.print("<br>");
    }
%>
</body>
</html>
