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
%>
<html>
<head>
    <title>显示DVD</title>
    <style>
        p{
            color:blue;
            font-family: 楷体;
            font-size: x-large;
              }
    </style>
    <link rel="stylesheet" href="/common/util/layui/css/layui.css"  media="all">
</head>
<body>
<jsp:include page="navigationBar.jsp"></jsp:include>
<h3 style="margin-left: 3px">欢迎你:<%=session.getAttribute("loginName")%></h3>
<p id = "timeP"></p>
<%--<p id = "nameP"></p>--%>
<%--<input type="button" id="change" value="显示名字" />--%>

<div  style="text-align:center;margin: 0 auto">
    <form action="/fy/servlet/toShowDvd" method="get" >
        <input  style="margin-left: 20px;margin-top: 20px;height:36px;"  type="text" name="selectDVD" id="select"/>
        <input  type="submit" class="layui-btn layui-btn-normal" value="查询">
    </form>

  <table   class="layui-table" style="text-align:center;width:1000px;margin: 0 auto" border="1">
   <tr><th><input name="selectall" type="checkbox" id="selectall" >全选</th><th>DVD编号</th><th>DVD预览</th><th>DVD名称</th><th>DVD状态</th><th>操作</th><th>修改操作</th></tr>
        <% for(DVD dvd:list){ %>
        <tr>
            <td style="width:110px"><input name="atask" type="checkbox" id="ask" value="<%=dvd.getId() %>" class="ck"></td>
            <td style="width:110px"><%=dvd.getId() %></td>
            <td style="width:110px"><img style="width:110px;height:80px" src="<%=dvd.getPicture() %>" /></td>
            <td style="width:110px"> <%=dvd.getDvdname() %></td>
            <% if(dvd.getState().equals("可以借")){%><td style="width:110px;background-color: limegreen"><%=dvd.getState() %></td><% }%>
            <% if(dvd.getState().equals("已借出")){%><td style="width:110px;background-color: red"><%=dvd.getState() %></td><% }%>
            <td style="width:110px"><a  href="/fy/servlet/loanreturn?idstate=<%=dvd.getId() %>,<%=dvd.getState() %>&page=<%=pag%>"><%if(dvd.getState().equals("可以借")){%>借出<%}else{%>归还<%} %></a></td>
            <%--<td><input type="button"  class="button" value="修改" onclick='window.location.href="changeDVD.jsp?id=<%=dvd.getId() %>&name=<%=dvd.getDvdname()%>&state=<%=dvd.getState()%>"'></td>--%>
            <td style="width:110px"><a class="layui-btn" href="changeDVD.jsp?id=<%=dvd.getId() %>&name=<%=dvd.getDvdname()%>&state=<%=dvd.getState()%>" target="mainFrame" >修改</a></td>
        </tr>
   <%  }%>
    </table>

        <div >
        <a  class="layui-btn layui-btn-normal" href="/fy/servlet/toShowDvd?page=<%=0%>">&lt;&lt; 首页 </a>
        <a  class="layui-btn layui-btn-normal" href="/fy/servlet/toShowDvd?page=<%=pag-1 %>">    &lt; 上一页 </a>
        <strong>第<%=pag+1 %>页/共<%=pageNumber+1 %>页</strong>
        <a  class="layui-btn layui-btn-normal" href="/fy/servlet/toShowDvd?page=<%=pag+1 %>">下一页 &gt;</a>
        <a  class="layui-btn layui-btn-normal" href="/fy/servlet/toShowDvd?page=<%=pageNumber %>">末页 &gt;&gt;</a>
       </div>

</div>
<div style="width: 800px;margin: 0 auto">
    <div style="width:100px;float:left">
        <br>
        <button class="layui-btn layui-btn-normal" onclick='return tishi()'><i class="layui-icon" ></i>-删除-  </button><br><br>
        <%--<input  type='button' id='sc' class="layui-btn layui-btn-sm layui-btn-normal" value='批量 删除' onclick='return tishi()' ><i class="layui-icon"></i>--%>
        <a class="layui-btn layui-btn-normal" href="addDVD.jsp" target="mainFrame" >添加DVD</a><br>
    </div>
    <iframe style="width:550px;height:200px;margin-left:30px" name="mainFrame" frameborder="0"></iframe>
</div>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script src="/common/util/layui/layui.js" charset="utf-8"></script>
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
        if(str==""){
            alert("你未选择要删除的项");
            return false;
        }
        if(confirm("确定要删除么："+str+"")){
            window.location.href="deleteDVD.jsp?no="+str;
        }
    }

    function time(){
           document.getElementById("timeP").innerText=new Date();
           setTimeout(time,1000);
        }

    $(function(){
            time();
        });

</script>

</body>
</html>
