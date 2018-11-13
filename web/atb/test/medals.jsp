<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\11\13 0013
  Time: 8:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>奖牌排序</title>
    <%--jquery--%>
    <script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
    <%--layui引用--%>
    <script src="../../common/util/layui/layui.js"></script>
    <link rel="stylesheet" href="../../common/util/layui/css/layui.css">
    <%--JS获取th--%>
    <script type="text/javascript">
        //查找表格的<th>元素，让它们可单击
        function makeSortable(table) {
            var headers=table.getElementsByTagName("th");
            for(var i=0;i<headers.length;i++){
                (function(n){
                    var flag=false;
                    headers[n].onclick=function(){
                        // sortrows(table,n);
                        var tbody=table.tBodies[0];//第一个<tbody>
                        var rows=tbody.getElementsByTagName("tr");//tbody中的所有行
                        rows=Array.prototype.slice.call(rows,0);//真实数组中的快照

                        //基于第n个<td>元素的值对行排序
                        rows.sort(function(row1,row2){
                            var cell1=row1.getElementsByTagName("td")[n];//获得第n个单元格
                            var cell2=row2.getElementsByTagName("td")[n];
                            var val1=cell1.textContent||cell1.innerText;//获得文本内容
                            var val2=cell2.textContent||cell2.innerText;

                            if(val1<val2){
                                return -1;
                            }else if(val1>val2){
                                return 1;
                            }else{
                                return 0;
                            }
                        });
                        if(flag){
                            rows.reverse();
                        }
                        //在tbody中按它们的顺序把行添加到最后
                        //这将自动把它们从当前位置移走，故没必要预先删除它们
                        //如果<tbody>还包含了除了<tr>的任何其他元素，这些节点将会悬浮到顶部位置
                        for(var i=0;i<rows.length;i++){
                            tbody.appendChild(rows[i]);
                        }

                        flag=!flag;
                    }
                }(i));
            }
        }

        window.onload=function(){
            var table=document.getElementsByTagName("table")[0];
            makeSortable(table);
        }
    </script>
    <%--JQ排序--%>
    <script type="text/javascript">
        ;(function($){
            $.fn.extend({
                "makeSortable":function(){
                    var table=$(this),
                        headers=table.find('th');
                    for(var i=0,len=headers.length;i<len;i++){
                        (function(n){
                            var flag=false;
                            headers.eq(n).click(function() {
                                var tbody=table.children('tbody').eq(0);
                                var rows=tbody.children('tr');
                                rows=Array.prototype.slice.call(rows,0);
                                rows.sort(function(row1,row2){
                                    var val1=$(row1).children('td').eq(n).text();
                                    var val2=$(row2).children('td').eq(n).text();
                                    if(val1>val2){
                                        return 1;
                                    }else if(val1<val2){
                                        return -1;
                                    }else{
                                        return 0;
                                    }
                                });
                                if(flag){
                                    rows.reverse();
                                }
                                tbody.append(rows);
                                flag=!flag;
                            });
                        }(i));
                    }
                    return this;
                }
            });
        })(jQuery);
        $(function(){
            $(".heroinfo").makeSortable();
        });
    </script>
</head>
<body>
<%--表格--%>
<table class="layui-table heroinfo">
    <colgroup>
        <col width="150">
        <col width="150">
        <col width="150">
        <col width="150">
        <col width="150">
        <col width="150">
        <col>
    </colgroup>
    <thead title="点击排序">
    <tr>
        <th>国家</th>
        <th>国旗</th>
        <th>金牌</th>
        <th>银牌</th>
        <th>铜牌</th>
        <th>总数</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>美国</td>
        <td><img src="http://www.sinaimg.cn/ty/08ay/data/logo/new/USA.jpg" width="30" alt="美国"></td>
        <td>46</td>
        <td>37</td>
        <td>38</td>
        <td>121</td>
    </tr>
    <tr>
        <td>英国</td>
        <td><img src="http://www.sinaimg.cn/ty/08ay/data/logo/new/GBR.jpg" width="30" alt="英国"></td>
        <td>27</td>
        <td>23</td>
        <td>17</td>
        <td>067</td>
    </tr>
    <tr>
        <td>中国</td>
        <td><img src="http://www.sinaimg.cn/ty/08ay/data/logo/new/CHN.jpg" width="30" alt="中国"></td>
        <td>26</td>
        <td>18</td>
        <td>26</td>
        <td>070</td>
    </tr>
    <tr>
        <td>俄罗斯</td>
        <td><img src="http://www.sinaimg.cn/ty/08ay/data/logo/new/RUS.jpg" width="30" alt="俄罗斯"></td>
        <td>19</td>
        <td>18</td>
        <td>19</td>
        <td>056</td>
    </tr>
    <tr>
        <td>德国</td>
        <td><img src="http://www.sinaimg.cn/ty/08ay/data/logo/new/GER.jpg" width="30" alt="德国"></td>
        <td>17</td>
        <td>10</td>
        <td>15</td>
        <td>042</td>
    </tr>
    <tr>
        <td>日本</td>
        <td><img src="http://www.sinaimg.cn/ty/08ay/data/logo/new/JPN.jpg" width="30" alt="日本"></td>
        <td>12</td>
        <td>08</td>
        <td>21</td>
        <td>041</td>
    </tr>
    <tr>
        <td>法国</td>
        <td><img src="http://www.sinaimg.cn/ty/08ay/data/logo/new/FRA.jpg" width="30" alt="法国"></td>
        <td>10</td>
        <td>18</td>
        <td>14</td>
        <td>042</td>
    </tr>
    <tr>
        <td>韩国</td>
        <td><img src="http://www.sinaimg.cn/ty/08ay/data/logo/new/KOR.jpg" width="30" alt="韩国"></td>
        <td>09</td>
        <td>03</td>
        <td>09</td>
        <td>021</td>
    </tr>
    <tr>
        <td>意大利</td>
        <td><img src="http://www.sinaimg.cn/ty/08ay/data/logo/new/ITA.jpg" width="30" alt="意大利"></td>
        <td>08</td>
        <td>12</td>
        <td>08</td>
        <td>028</td>
    </tr>
    </tbody>
</table>
<%--柱状图显示--%>
<div id="container" style="height: 70%"></div>
<%--引用echarts--%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/echarts/4.1.0.rc2/echarts.min.js"></script>
<%--柱状图 echarts设置--%>
<script type="text/javascript">
    var dom = document.getElementById("container");
    var myChart = echarts.init(dom);
    var app = {};
    option = null;
    var posList = [
        'left', 'right', 'top', 'bottom',
        'inside',
        'insideTop', 'insideLeft', 'insideRight', 'insideBottom',
        'insideTopLeft', 'insideTopRight', 'insideBottomLeft', 'insideBottomRight'
    ];

    app.configParameters = {
        rotate: {
            min: -90,
            max: 90
        },
        align: {
            options: {
                left: 'left',
                center: 'center',
                right: 'right'
            }
        },
        verticalAlign: {
            options: {
                top: 'top',
                middle: 'middle',
                bottom: 'bottom'
            }
        },
        position: {
            options: echarts.util.reduce(posList, function (map, pos) {
                map[pos] = pos;
                return map;
            }, {})
        },
        distance: {
            min: 0,
            max: 100
        }
    };

    app.config = {
        rotate: 90,
        align: 'left',
        verticalAlign: 'middle',
        position: 'insideBottom',
        distance: 15,
        onChange: function () {
            var labelOption = {
                normal: {
                    rotate: app.config.rotate,
                    align: app.config.align,
                    verticalAlign: app.config.verticalAlign,
                    position: app.config.position,
                    distance: app.config.distance
                }
            };
            myChart.setOption({
                series: [{
                    label: labelOption
                }, {
                    label: labelOption
                }, {
                    label: labelOption
                }, {
                    label: labelOption
                }]
            });
        }
    };


    var labelOption = {
        normal: {
            show: true,
            position: app.config.position,
            distance: app.config.distance,
            align: app.config.align,
            verticalAlign: app.config.verticalAlign,
            rotate: app.config.rotate,
            formatter: '{c}  {name|{a}}',
            fontSize: 16,
            rich: {
                name: {
                    textBorderColor: '#fff'
                }
            }
        }
    };

    option = {
        color: ['#003366', '#006699', '#4cabce', '#e5323e'],
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        legend: {
            data: ['金牌', '银牌', '铜牌', '总数']
        },
        toolbox: {
            show: true,
            orient: 'vertical',
            left: 'right',
            top: 'center',
            feature: {
                mark: {show: true},
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        calculable: true,
        xAxis: [
            {
                type: 'category',
                axisTick: {show: false},
                data: ['美国', '英国', '中国', '俄罗斯',
                    '德国', '日本', '法国', '韩国', '意大利']
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                name: '金牌',
                type: 'bar',
                barGap: 0,
                label: labelOption,
                data: [46, 27, 26, 19, 17, 12, 10, 9, 8]
            },
            {
                name: '银牌',
                type: 'bar',
                label: labelOption,
                data: [37, 23, 18, 18, 10, 18, 18, 3, 12]
            },
            {
                name: '铜牌',
                type: 'bar',
                label: labelOption,
                data: [38, 17, 26, 19, 15, 21, 14, 9, 8]
            },
            {
                name: '总数',
                type: 'bar',
                label: labelOption,
                data: [121, 67, 70, 56, 42, 41, 42, 21, 28]
            }
        ]
    };
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
</script>
</body>
</html>
