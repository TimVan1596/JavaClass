<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/8 0008
  Time: 16:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>动态简历</title>

    <script type="text/javascript" src="jquery-2.2.3.js"></script>
    <script type="text/javascript" src="script.js"></script>
    <link href="style.css" rel="stylesheet">
</head>
<body>
<div class="page">
    <div class="top">
        <ul>
            <li class="topContact">CELL: 158 5151 1985</li>
            <li class="topContact">EMAIL: mail@domain.com</li>
        </ul>
    </div>
    <div class="header">
        <div class="name">
            <span>大&nbsp;漠&nbsp;孤&nbsp;烟</span>
        </div>
        <div class="job">
            <span>Front End Web Developer</span>
        </div>
    </div>
    <div class="content">
        <div class="panel">
            <div class="panelTitle">
                <div class="colorBlock"></div>
                <div class="title">
                    <span>个人信息</span>
                </div>
                <div class="scrollBtn">
                    <span class="scroIcon"></span>
                </div>
                <div class="clear"></div>
            </div>
            <div class="panelContent">
                <ul>
                    <li class="resumeItem"><span>姓名</span><input type="text" /></li>
                    <li class="resumeItem">
                        <span>性别</span><select>
                        <option value="">--请选择--</option>
                        <option value="男">男</option>
                        <option value="女">女</option>
                    </select>
                    </li>
                    <li class="resumeItem">
                        <span>出生年月</span><select class="brithYear">
                        <option value="">--请选择--</option>
                    </select>&nbsp;年&nbsp;
                        <select class="brithMonth">
                            <option value="">--请选择--</option>
                        </select>&nbsp;月&nbsp;
                    </li>
                    <li class="resumeItem">
                        <span>户籍所在地</span><select class="province" onchange="getCity()">
                        <option value="">--请选择--</option>
                        <!-- 利用js把省份添加到下拉列表里-->
                        <%--<script type="text/javascript"  charset="UTF-8">--%>
                            <%--for(var i=0;i<provinceArr.length;i++) {--%>
                                <%--document.write("<option value='"+i+"'>"+provinceArr[i]+"</option>");--%>
                            <%--}--%>
                        <%--</script>--%>
                    </select>
                        <select class="city" >
                            <option value="">--请选择--</option>
                        </select>
                    </li>
                </ul>
            </div>
        </div>

        <div class="panel">
            <div class="panelTitle">
                <div class="colorBlock"></div>
                <div class="title">
                    <span>联系方式</span>
                </div>
                <div class="scrollBtn">
                    <span class="scroIcon"></span>
                </div>
                <div class="clear"></div>
            </div>
            <div class="panelContent">
                <ul>
                    <li class="resumeItem"><span>手机号码</span><input type="text" /></li>
                    <li class="resumeItem"><span>电子邮箱</span><input type="text" /></li>
                    <li class="resumeItem"><span>现居地址</span><input type="text" /></li>
                </ul>
            </div>
        </div>

        <div class="panel">
            <div class="panelTitle">
                <div class="colorBlock"></div>
                <div class="title">
                    <span>求职意向</span>
                </div>
                <div class="scrollBtn">
                    <span class="scroIcon"></span>
                </div>
                <div class="clear"></div>
            </div>
            <div class="panelContent">
                <ul>
                    <li class="resumeItem">
                        <span>求职状态</span><select>
                        <option value="">--请选择--</option>
                        <option value="1">工作中</option>
                        <option value="2">正在找工作</option>
                    </select>
                    </li>
                    <li class="resumeItem">
                        <span>目标薪资</span><select>
                        <option value="">--请选择--</option>
                        <option value="1">月薪</option>
                    </select>
                        <select>
                            <option value="">--请选择--</option>
                            <option value="1">3K~4K</option>
                            <option value="2">5K~6K</option>
                            <option value="2">5K~6K</option>
                            <option value="2">7K~8K</option>
                            <option value="2">9K~10K</option>
                            <option value="2">11K~15K</option>
                            <option value="2">16K~20K</option>
                            <option value="2">20K以上</option>
                        </select>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="footer">

    </div>
</div>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script>

    $(function(){
        $(".scroIcon").eq(0).click(function(){
        $(".panelContent").eq(0).toggle();
        });
        $(".scroIcon").eq(1).click(function(){
            $(".panelContent").eq(1).toggle();
        });
        $(".scroIcon").eq(2).click(function(){
            $(".panelContent").eq(2).toggle();
        });
    });

    $(function() {
        var current = new Date();
        var cur_year = current.getFullYear();
        for (var i = 1990; i <=cur_year; i++) {
            $(".brithYear").append("<option>"+i+"</option>");
        }
    });

    $(function(){
        for (var i=1;i<=12;i++) {
            $(".brithMonth").append("<option>"+i+"</option>")
        }
    });

    $(function() {
        //初始化省份下拉列表
        for (var i = 0; i < provinceArr.length; i++) {
            $(".province").append($("<option></option>").val(i + 1).html(provinceArr[i]));
        }
    });

    $(".province").change(function(){
        var index=$(this).val()-1;//获取当前省的下标
        $(".city").prop("length",1);//清空原有的数据
        for(var i=1;i<cityArr[index].length;i++){//重新为市赋值
            $(".city").append($("<option>").val(i+1).html(cityArr[index][i]));
        }
    });

</script>
</body>
</html>
