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
                    <li class="resumeItem"><span>姓名</span><input type="text" id="name" /></li>
                    <div id="yname" style="color: red"></div>
                    <li class="resumeItem">
                        <span>性别</span><select id="sex">
                        <option value="">--请选择--</option>
                        <option value="男">男</option>
                        <option value="女">女</option>
                    </select>
                        <div id="ysex" style="color: red"></div>
                    </li>
                    <li class="resumeItem">
                        <span>出生年月</span><select id="by" class="brithYear">
                        <option value="">--请选择--</option>
                    </select>&nbsp;年&nbsp;
                        <select id="bm" class="brithMonth">
                            <option value="">--请选择--</option>
                        </select>&nbsp;月&nbsp;
                    </li>
                    <div id="yYM" style="color: red"></div>

                    <li class="resumeItem">
                        <span>户籍所在地</span>
                        <select id="pro" class="province" onchange="getCity()">
                             <option value="">--请选择--</option>
                        </select>
                        <select id="city" class="city" >
                            <option value="">--请选择--</option>
                        </select>
                    </li>
                    <div id="ycity" style="color: red"></div>
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
                    <li class="resumeItem"><span>手机号码</span><input id="phone" type="text" /></li>
                    <div id="yphone" style="color: red"></div>
                    <li class="resumeItem"><span>电子邮箱</span><input id="email" type="text" /></li>
                    <div id="yemail" style="color: red"></div>
                    <li class="resumeItem"><span>现居地址</span><input id="adress" type="text" /></li>
                    <div id="yadress" style="color: red"></div>
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
                        <span>求职状态</span><select id="qz">
                        <option value="">--请选择--</option>
                        <option value="1">工作中</option>
                        <option value="2">正在找工作</option>
                    </select>
                        <div id="yqz" style="color: red"></div>
                    </li>
                    <li class="resumeItem">
                        <span>目标薪资</span><select id="xz">
                        <option value="">--请选择--</option>
                        <option value="1">月薪</option>
                    </select>
                        <select id="money">
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
                        <div id="ymoney" style="color: red"></div>
                    </li>
                </ul>
            </div>
            <input type="button" id="button" style="margin-left: 45%" value="提交"/>
        </div>
    </div>
    <div class="footer">

    </div>
</div>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script>

    $(function(){
        $("#name").blur(function() {
            if ($("#name").val() == "") {
                $("#yname").html("姓名不能为空");
            }else{
                if(2<($("#name").val().length)<8){
                    $("#yname").html("姓名要在3到7个字符之间");
                }else{
                $("#yname").html("");}
            }
        });
    });

    $(function(){
        $("#sex").blur(function() {
            if ($("#sex").val() == "") {
                $("#ysex").html("请选择性别");
            }else{
                $("#ysex").html("");
            }
        });
    });

    $(function(){
        $("#by").blur(function() {
            if ($("#by").val() == "") {
                $("#yYM").html("请选择年份");
            }else{
                $("#yYM").html("");
            }
        });

        $("#bm").blur(function() {
            if ($("#bm").val() == "") {
                $("#yYM").html("请选择月份");
            }else{
                $("#yYM").html("");
            }
        });

        $("#pro").blur(function() {
            if ($("#pro").val() == "") {
                $("#ycity").html("请选择省份");
            }else{
                $("#ycity").html("");
            }
        });

        $("#city").blur(function() {
            if ($("#city").val() == "") {
                $("#ycity").html("请选择城市");
            }else{
                $("#ycity").html("");
            }
        });

        $("#phone").blur(function() {
            if ($("#phone").val() == "") {
                $("#yphone").html("请填写手机号");
            }else{
                if(($("#phone").val().length)!=11){
                    $("#yphone").html("手机号格式不正确");
                }else{
                $("#yphone").html("");}
            }
        });

        $("#email").blur(function() {
            if ($("#email").val() == "") {
                $("#yemail").html("请填写邮箱");
            }else{
                var atpos = $("#email").val().indexOf("@");
                var dotpos = $("#email").val().lastIndexOf(".");
                if(atpos<1 || dotpos<atpos + 2 ) {
                    $("#yemail").html("邮箱格式不正确");
               }else{
                $("#yemail").html("");}
            }
        });

        $("#adress").blur(function() {
            if ($("#adress").val() == "") {
                $("#yadress").html("请填写地址");
            }else{
                $("#yadress").html("");
            }
        });

        $("#qz").blur(function() {
            if ($("#qz").val() == "") {
                $("#yqz").html("请选择求职状态");
            }else{
                $("#yqz").html("");
            }
        });

        $("#xz").blur(function() {
            if ($("#xz").val() == "") {
                $("#ymoney").html("请选择薪资");
            }else{
                $("#ymoney").html("");
            }
        });

        $("#money").blur(function() {
            if ($("#money").val() == "") {
                $("#ymoney").html("请选择薪资");
            }else{
                $("#ymoney").html("");
            }
        });

        $("#button").click(function(){
            if(($("#name").val() == "")|| ($("#sex").val() == "")|| ($("#by").val() == "")|| ($("#bm").val() == "")|| ($("#pro").val() == "")|| ($("#city").val() == "")|| ($("#phone").val() == "")|| ($("#email").val() == "")|| ($("#adress").val() == "")|| ($("#qz").val() == "")|| ($("#xz").val() == "")|| ($("#money").val() == "")){
                alert("你还有信息未填写");
            }else{
                alert("提交成功");
            }
        });
    });



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
        $(".city").prop("length",1);//清空当前数据
        for(var i=1;i<cityArr[index].length;i++){//重新为市赋值
            $(".city").append($("<option></option>").val(i+1).html(cityArr[index][i]));
        }
    });

</script>
</body>
</html>
