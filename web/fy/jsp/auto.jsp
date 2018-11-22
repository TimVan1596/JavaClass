<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/9 0009
  Time: 8:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>jQuery </title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.9.1.js"></script>
    <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <script language="javascript" src="/fy/js/jquery-1.4.4.min.js"></script>
    <script language="javascript" src="/fy/js/jquery.jqprint-0.3.js"></script>
    <link rel="stylesheet" href="http://jqueryui.com/resources/demos/style.css">
    <link rel="stylesheet" href="/common/util/layui/css/layui.css"  media="all">
    <script>
        var th="<tr>\n" +
            "        <th  style=\"background-color: #00F7DE \">排名</th>\n" +
            "        <th  style=\"background-color: #00F7DE \">国家</th>\n" +
            "        <th  style=\"background-color: #00F7DE \">国旗</th>\n" +
            "        <th  style=\"background-color: #00F7DE \" >金牌</th>\n" +
            "        <th  style=\"background-color: #00F7DE \">银牌</th>\n" +
            "        <th  style=\"background-color: #00F7DE \">铜牌</th>\n" +
            "        <th  style=\"background-color: #00F7DE \">总数</th>\n" +
            "    </tr>";


        $(function(){
            $("#jin").click(function(){
                $.ajax({
                    url:"/auto/show?ol=jin",
                    type:"GET",
                    dataType:"json",
                    success:function(myReturnResult){
                        var s = "";
                        $("table").html("");
                        $("#ta").append(th);
                        for (var i = 0; i < myReturnResult.length; i++) {
                            s = "<tbody><tr><td>" +(i+1)+ "</td><td>" + myReturnResult[i].country + "</td><td>" + "<img src="+myReturnResult[i].photo+">" + "</td><td>" +myReturnResult[i].goldMedal + "</td><td>" + myReturnResult[i].silverMedal + "</td><td>" +myReturnResult[i].bronzeMedal + "</td><td>" + myReturnResult[i].total +"</td></tr></tbody>";
                            $("#ta").append(s);
                        }
                    }
                });
            });

            $("#yin").click(function(){
                $.ajax({
                    url:"/auto/show?ol=yin",
                    type:"GET",
                    dataType:"json",
                    success:function(myReturnResult){
                        var s = "";
                        $("table").html("");
                        $("#ta").append(th);
                        for (var i = 0; i < myReturnResult.length; i++) {
                                s = "<tr><td>" + (i + 1) + "</td><td>" + myReturnResult[i].country + "</td><td>" + "<img src=" + myReturnResult[i].photo + ">" + "</td><td>" + myReturnResult[i].goldMedal + "</td><td>" + myReturnResult[i].silverMedal + "</td><td>" + myReturnResult[i].bronzeMedal + "</td><td>" + myReturnResult[i].total + "</td></tr>";
                            $("#ta").append(s);
                        }
                    }
                });
            });

            $("#ton").click(function(){
                $.ajax({
                    url:"/auto/show?ol=ton",
                    type:"GET",
                    dataType:"json",
                    success:function(myReturnResult){
                        var s = "";
                        $("table").html("");
                        $("#ta").append(th);
                        for (var i = 0; i < myReturnResult.length; i++) {
                            s = "<tr><td>" +(i+1)+ "</td><td>" + myReturnResult[i].country + "</td><td>" + "<img src="+myReturnResult[i].photo+">" + "</td><td>" +myReturnResult[i].goldMedal + "</td><td>" + myReturnResult[i].silverMedal + "</td><td>" +myReturnResult[i].bronzeMedal + "</td><td>" + myReturnResult[i].total +"</td></tr>";
                            $("#ta").append(s);
                        }
                    }
                });
            });

            $("#zong").click(function(){
                $.ajax({
                    url:"/auto/show?ol=zong",
                    type:"GET",
                    dataType:"json",
                    success:function(myReturnResult){
                        var s = "";
                        $("table").html("");
                        $("#ta").append(th);
                        for (var i = 0; i < myReturnResult.length; i++) {
                            s = "<tr><td>" +(i+1)+ "</td><td>" + myReturnResult[i].country + "</td><td>" + "<img src="+myReturnResult[i].photo+">" + "</td><td>" +myReturnResult[i].goldMedal + "</td><td>" + myReturnResult[i].silverMedal + "</td><td>" +myReturnResult[i].bronzeMedal + "</td><td>" + myReturnResult[i].total +"</td></tr>";
                            $("#ta").append(s);
                        }
                    }
                });
            });

            $("#hh").click(function() {
                $.ajax({
                    url: "/auto/show?ol=hh",
                    type: "GET",
                    dataType: "json",
                    success: function (myReturnResult) {
                        // $( "#tags" ).autocomplete({
                        //     source: myReturnResult
                        // });
                        var s="";
                        $("table").html("");
                        $("#ta").append(th);
                        for (var i = 0; i < myReturnResult.length; i++) {
                            s = "<tbody><tr><td>" +(i+1)+ "</td><td>" + myReturnResult[i].country + "</td><td>" + "<img src="+myReturnResult[i].photo+">" + "</td><td>" +myReturnResult[i].goldMedal + "</td><td>" + myReturnResult[i].silverMedal + "</td><td>" +myReturnResult[i].bronzeMedal + "</td><td>" + myReturnResult[i].total +"</td></tr></tbody>";
                            $("#ta").append(s);
                        }
                    }
                });
            });
        });

    </script>

</head>
<body>

<div id="ddd">
    <input type="button" onclick="aa()" value="打印"/>
<div>
    <input  type="button" class="layui-btn" value="默认显示" style="margin-left: 43%" id="hh"/>
    <input  type="button" class="layui-btn" value="金牌降序"  id="jin"/>
    <%--<input  type="button" class="layui-btn" value="银牌升降"  id="yin"/>--%>
    <%--<input  type="button" class="layui-btn" value="铜牌升降"  id="ton"/>--%>
    <%--<input  type="button" class="layui-btn" value="总奖牌升降"  id="zong"/>--%>
<table  border="1" id="ta" class="layui-table heroinfo" style="text-align:center;width:1000px;margin: 0 auto" summary="user infomation table" >
    <tr>
        <th  style="background-color: #00F7DE ">排名</th>
        <th  style="background-color: #00F7DE ">国家</th>
        <th  style="background-color: #00F7DE ">国旗</th>
        <th  style="background-color: #00F7DE " >金牌</th>
        <th  style="background-color: #00F7DE ">银牌</th>
        <th  style="background-color: #00F7DE ">铜牌</th>
        <th  style="background-color: #00F7DE ">总数</th>
    </tr>
</table>
    <div id="main" style="width: 100%;height: 500px">
    </div>
</div>
</div>
<script type="text/javascript" src="/fy/js/echarts.min.js"> </script>
<script type="text/javascript">
    // 初始化图表标签
    var myChart = echarts.init(document.getElementById('main'));
    var options={
        //定义一个标题
        title:{
            text:'2016奥运'
        },
        legend:{
            data:['金牌', '银牌', '铜牌', '总数']
        },
        //X轴设置
        xAxis:{
            data:['美国', '英国', '中国', '俄罗斯',
                '德国', '日本', '法国', '韩国', '意大利']
        },
        yAxis:{
        },

        series: [
            {
                name: '金牌',
                type: 'bar',
                data: [46, 27, 26, 19, 17, 12, 10, 9, 8]
            },
            {
                name: '银牌',
                type: 'bar',
                data: [37, 23, 18, 18, 10, 18, 18, 3, 12]
            },
            {
                name: '铜牌',
                type: 'bar',
                data: [38, 17, 26, 19, 15, 21, 14, 9, 8]
            },
            {
                name: '总数',
                type: 'bar',
                data: [121, 67, 70, 56, 42, 41, 42, 21, 28]
            }
        ]

    };
    myChart.setOption(options);

    function aa(){
        $("#ddd").jqprint();
    }
</script>

</body>
</html>
