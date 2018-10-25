<%@ page import="com.antianbao.javaWebDvd.dvd.JDBCUtilDvd" %>
<%@ page import="com.antianbao.javaWebDvd.dvd.Dvd" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\10\12 0012
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>显示DVD</title>
    <style type="text/css">
        ul{
            margin:0 auto;
            list-style: none;
            width: 750px;
            padding: 0;
            overflow: hidden;
            background-color: #eee;
        }
        li {
            float: left;
            width: 150px;
            height: 40px;
            text-align: center;
        }
        li a {
            display: block;
            padding: 10px 15px;
            color: black;
            text-align: center;
            text-decoration: none;
        }
        li a:hover {
            background-color: red;
        }
        #footer {
            height: 40px;
            line-height: 40px;
            position: fixed;
            bottom: 0;
            width: 100%;
            text-align: center;
            background: #333;
            color: #fff;
            font-size: 12px;
            letter-spacing: 1px;
        }
    </style>
</head>
<body background="./atb/javaWebDvd/images/banner.jpg">
<h1 align='center' style="color: white">----欢迎进入DVD Mgr 6.0 管理系统----</h1>
<h5 align='center' style="color: red">介绍：搜索（关键字）,管理数据（操作数据）,编辑（修改信息,图片可改可不改）,分页（数据分页）,删除（多删且要密码）,添加（添加信息需加图片）,数据显示（借阅情况柱状图）,退出（返回登录页面）</h5>
<div align='center'>
    <form action='./atb/javaWebDvd/search.jsp' method='post'>
        <h4 style="color: white">搜关键字：<input type='text' title="序号书名库存" name='search' style="width: 150px; height: 30px"/>
            <input type='submit' value='搜索' style="width: 50px; height: 30px"/>
        </h4>
    </form>
</div>
<div align='center'>
    <ul>
        <li><a href="./atblogin.do">主页</a></li>
        <li><a href="./atblogin.do">管理数据</a></li>
        <li><a href="./atb/javaWebDvd/jsp/choice/data.jsp">借阅情况</a></li>
        <li><a href="./atblogin.do">撤回数据</a></li>
        <li><a href="./atbdescription.do">说明介绍</a></li>
    </ul>
</div>
<div align='center'>
    <h4 align='center' style="color: white">
        <br>输入注意：账号和密码为字母和数字组成、库存和手机号为数字组成
        <br>
        <br>1.注册（账号：检测是否存在、密码：检测两次密码是否相同、手机号：检测是否为手机号（13+任意数,15+除4的任意数,18+除1和4的任意数,17+除9的任意数,147））
        <br>2.修改密码（验证：账号和手机号，确认后跳转更改界面，显示账号，密码两次相同）
        <br>3.登陆（验证：账号和密码）
        <br>4.增删改查（序号自增和添加图片（书名不可重复）、批量删除：删除密码（123）、编辑时原数据显示和修改图片、模糊查询：可分页）
        <br>5.分页功能（每页八条、页数会随着总数增加：如7条数据共一页，12条数据共两页）
        <br>6.借阅情况柱状图（添加数据会同步），库存：一本未借蓝色（不可归还）、剩余为零红色（不可借出）
        <br>7.历史数据（管理数据中删除的数据存放处（回收站），数据可恢复，可彻底删除（不可找回）密码：123）
        <br>
        <br>待完成功能：主页：显示最近借阅书本和库存为0的书本信息，用户反馈，搜索下拉
        <br>
        <br>就要玩到BUG：1.注册和改密失败后无法返回,正常可以（已修复：失败后再操作路径不对）
        <br>2.搜索汉字不能点首页尾页（已修复：href传中文到Servlet不用解决乱码）。
        <br>3.搜索一次后进行下一页操作不能再次搜索(已修复：路径错误)
    </h4>
</div>
<div id="footer">安徽信息工程学院 2016级 JAVA①班 安天宝 JavaWeb项目作业</div>
</body>
</html>
