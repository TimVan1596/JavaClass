<%@ page import="com.smallfangyu.bookjavaweb.Book" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/27 0027
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%
        if(session.getAttribute("loginName")==null){
            response.sendRedirect("login.jsp");
        }
        session.getAttribute("listDVD");
        //把SESSION的dvd数据放进集合里
        ArrayList<Book> list=(ArrayList<Book>)session.getAttribute("listDVD");
        int pag= (int) session.getAttribute("page");
        int pageNumber= (int) session.getAttribute("pageNumber");
    %>
</head>
<body>
<div  style="text-align:center">
<h1>图书列表</h1>
<form action="/book/showbook" method="get" >
    <input  style="margin-left: 20px;margin-top: 20px;height:25px;" type="text" name="selectDVD" id="select"/>
    <input type="submit"  class="button" value="查询">
    <input type="button"   value="添加" onclick='window.location.href="bookadd.jsp?"'>
    <input type='button' id='sc' value='批量删除' onclick='return tishi()' />
</form>

<table id="tb" style="text-align:center;margin: 0 auto " border="1" >
    <tr><th><input name="selectall" type="checkbox" id="selectall" value="101" class="np">全选</th><th>BOOK编号</th><th>BOOK名称</th><th>BOOK作者</th><th>图书价格</th><th>出版日期</th><th>图书分类</th><th>操作</th></tr>
    <% for(Book book:list){ %>
    <tr>
        <td style="width:110px"><input name="atask" type="checkbox" id="ask" value="<%=book.getId() %>" class="ck"></td>
        <td style="width:110px"><%=book.getId() %></td>
        <td style="width:110px"><%= book.getName()%></td>
        <td style="width:110px"> <%=book.getAuthor()%></td>
        <td style="width:110px"> <%=book.getPrice()%></td>
        <td style="width:110px"> <%=book.getDate()%></td>
        <td style="width:110px"> <%=book.getType()%></td>
        <td style="width:110px"><input type="button"  class="button" value="修改" onclick='window.location.href="bookupdate.jsp?no=<%=book.getId() %>&&name=<%= book.getName()%>&&author=<%=book.getAuthor()%>&&price=<%=book.getPrice()%>&&date=<%=book.getDate()%>&&type=<%=book.getType()%>"'></td>
    </tr>
    <%  }%>

</table>
<p>
    <a href="/book/showbook?page=<%=0%>">&lt;&lt; 首页 </a>
    <a href="/book/showbook?page=<%=pag-1 %>">    &lt; 上一页 </a>
    <strong>第<%=pag+1 %>页/共<%=pageNumber+1 %>页</strong>
    <a href="/book/showbook?page=<%=pag+1 %>">下一页 &gt;</a>
    <a href="/book/showbook?page=<%=pageNumber %>">末页 &gt;&gt;</a>
</p>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script type="text/javascript">

    $(document).ready(function(){
        $("#selectall").click(
            function(){
                if(this.checked){
                    $("input[name='atask']").each(function(){this.checked=true;});
                }else{
                    $("input[name='atask']").each(function(){this.checked=false;});
                }
            });
    });

    function tishi()
    {
        //找所有选中项
        var ck = document.getElementsByClassName("ck");

        var str = "";

        for(var i=0;i<ck.length;i++)
        {
            if(ck[i].checked)
            {
                str += ck[i].value+",";
            }
        }
         str=str.substring(0,str.length-1);
        if(confirm("确定要删除么："+str+"")){
            window.location.href="bookdelete.jsp?no="+str;
        }
    }


</script>
</body>
</html>
