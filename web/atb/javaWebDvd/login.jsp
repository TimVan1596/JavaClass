<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\10\8 0008
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>DVD管理系统</title>
    <style>
        .button {
            background-color: #4183c4;
            border: none;
            color: white;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            width: 150px;
            height: 30px;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div align='center'>
    <h2>----欢迎进入DVD Mgr 6.0 管理系统----</h2>
</div>
<form action="../../login.do" method="post">
    <table border="1" width="300" align="center">
        <tr>
            <th width=150px>账号：</th>
            <th><input type="text" name="username" title="username" style="text-align: center; width: 150px;"
                       onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"/></th>
        </tr>
        <tr>
            <th width=150px>密码：</th>
            <th><input type="password" name="userpassword" title="userpassword" style="text-align: center; width: 150px;"
                       onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"/></th>
        </tr>
        <tr>
            <th><a style="text-decoration-line: underline;color: blue;width: 150px;" href="./atb/javaWebDvd/jsp/retrieve/retrieve.jsp">找回密码</a></th>
            <th><a style="text-decoration-line: underline;color: blue;width: 150px;" href="./atb/javaWebDvd/jsp/register/register.jsp">注册账号</a></th>
        </tr>
        <tr>
            <th colspan = '2'>
                <button type="submit" class="button">登陆</button>
                <%--<input type="submit" value="登陆" style="text-align: center;width: 50px;">--%>
            </th>
        </tr>
        <script type="text/javascript">
            if(<%= request.getAttribute("MSG")!=null %>){
                alert('<%=request.getAttribute("MSG") %>');
            }
        </script>
    </table>
</form>
<div align='center'>
    <br>输入注意：账号和密码为字母和数字组成、库存和手机号为数字组成
    <br>1.注册（账号：检测是否存在、密码：检测两次密码是否相同、手机号：检测是否为手机号（13+任意数,15+除4的任意数,18+除1和4的任意数,17+除9的任意数,147））
    <br>2.修改密码（账号、手机号）
    <br>3.登陆（账号、密码）
    <br>4.增删改查（序号自增和添加图片、批量删除：删除密码、编辑时原数据显示和修改图片、模糊查询：分页）
    <br>5.分页功能（每页八条、页数会随着总数增加：如7条数据共一页，12条数据共两页）
    <br>6.借书情况柱状图（添加数据同步），库存：一本未借蓝色（不可归还）、剩余为零红色（不可借出）
    <br>待完成功能：界面美化，用户反馈，搜索下拉
    <br>BUG：1.搜索汉字不能点首页尾页（已修复：href传中文参数到Servlet不用解决乱码）。2.搜索一次后进行下一页操作不能再次搜索(已修复：路径错误)
</div>
</body>
</html>
