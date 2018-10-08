<%--
  Created by IntelliJ IDEA.
  User: r
  Date: 2018/9/29
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>


<%@ page import="com.timvanx.biggerdvd.util.Constants" %>
<%!
    private int initVar=0;
    private int serviceVar=0;
    private int destroyVar=0;

    String VERSION =  Constants.VERSION;


    @Override
    public void jspInit(){
        initVar++;
        System.out.println("jspInit(): JSP被初始化了"+initVar+"次");
    }
    @Override
    public void jspDestroy(){
        destroyVar++;
        System.out.println("jspDestroy(): JSP被销毁了"+destroyVar+"次");
    }
%>

<%
    serviceVar++;
    System.out.println("_jspService(): JSP共响应了"+serviceVar+"次请求");

    String content1="初始化次数 : "+initVar;
    String content2="响应客户请求次数 : "+serviceVar;
    String content3="销毁次数 : "+destroyVar;
%>

<html>
<head>
    <title>BiggerDVD <%=VERSION%></title>
</head>
<body>
<h1>********** 欢迎来到BiggerDVD 管理系统 **************</h1>
<h3>**版本号 <%=VERSION %></h3>
<p><%=content1 %></p>
<p><%=content2 %></p>
<p><%=content3 %></p>

</body>
</html>