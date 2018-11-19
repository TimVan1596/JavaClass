<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/19/019
  Time: 8:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>奖牌榜</title>
    <%--layui引用--%>
    <script src="../../common/util/layui/layui.js"></script>
    <link rel="stylesheet" href="../../common/util/layui/css/layui.css">
</head>
<body>
<%--表格--%>
<table class="layui-hide" id="test" lay-filter="test"></table>
<script>
    layui.use('table', function(){
        var table = layui.table;
        //展示已知数据
        table.render({
            elem: '#test'
            ,cellMinWidth: 80
            ,toolbar: true
            ,cols: [[ //标题栏
                {field: 'countries', title: '国家', sort: true, edit: 'text'}
                ,{field: 'nationalFlag', title: '国旗'}
                ,{field: 'gold', title: '金牌', sort: true, edit: 'text'}
                ,{field: 'silver', title: '银牌', sort: true, edit: 'text'}
                ,{field: 'copper', title: '铜牌', sort: true, edit: 'text'}
                ,{field: 'number', title: '总数', sort: true, edit: 'text'}
            ]]
            ,data: [{
                "countries": "美国"
                ,"nationalFlag": "<img src=\"http://www.sinaimg.cn/ty/08ay/data/logo/new/USA.jpg\" width=\"30\" alt=\"美国\">"
                ,"gold": "46"
                ,"silver": "37"
                ,"copper": "38"
                ,"number": "121"
            }, {
                "countries": "英国"
                ,"nationalFlag": "<img src=\"http://www.sinaimg.cn/ty/08ay/data/logo/new/GBR.jpg\" width=\"30\" alt=\"英国\">"
                ,"gold": "27"
                ,"silver": "23"
                ,"copper": "17"
                ,"number": "67"
            }, {
                "countries": "中国"
                ,"nationalFlag": "<img src=\"http://www.sinaimg.cn/ty/08ay/data/logo/new/CHN.jpg\" width=\"30\" alt=\"中国\">"
                ,"gold": "26"
                ,"silver": "18"
                ,"copper": "26"
                ,"number": "70"
            }, {
                "countries": "俄罗斯"
                ,"nationalFlag": "<img src=\"http://www.sinaimg.cn/ty/08ay/data/logo/new/RUS.jpg\" width=\"30\" alt=\"俄罗斯\">"
                ,"gold": "19"
                ,"silver": "18"
                ,"copper": "19"
                ,"number": "56"
            }, {
                "countries": "德国"
                ,"nationalFlag": "<img src=\"http://www.sinaimg.cn/ty/08ay/data/logo/new/GER.jpg\" width=\"30\" alt=\"德国\">"
                ,"gold": "17"
                ,"silver": "10"
                ,"copper": "15"
                ,"number": "42"
            }, {
                "countries": "日本"
                ,"nationalFlag": "<img src=\"http://www.sinaimg.cn/ty/08ay/data/logo/new/JPN.jpg\" width=\"30\" alt=\"日本\">"
                ,"gold": "12"
                ,"silver": "8"
                ,"copper": "21"
                ,"number": "41"
            }, {
                "countries": "法国"
                ,"nationalFlag": "<img src=\"http://www.sinaimg.cn/ty/08ay/data/logo/new/FRA.jpg\" width=\"30\" alt=\"法国\">"
                ,"gold": "10"
                ,"silver": "18"
                ,"copper": "14"
                ,"number": "42"
            }, {
                "countries": "韩国"
                ,"nationalFlag": "<img src=\"http://www.sinaimg.cn/ty/08ay/data/logo/new/KOR.jpg\" width=\"30\" alt=\"韩国\">"
                ,"gold": "9"
                ,"silver": "3"
                ,"copper": "9"
                ,"number": "21"
            }, {
                "countries": "意大利"
                ,"nationalFlag": "<img src=\"http://www.sinaimg.cn/ty/08ay/data/logo/new/ITA.jpg\" width=\"30\" alt=\"意大利\">"
                ,"gold": "8"
                ,"silver": "12"
                ,"copper": "8"
                ,"number": "28"
            }]
            ,even: true
        });

        //监听单元格编辑
        table.on('edit(test)', function(obj){
            var value = obj.value //得到修改后的值
                ,data = obj.data //得到所在行所有键值
                ,field = obj.field; //得到字段
            layer.msg('[国家: '+ data.countries +'] ' + field + ' 更改为：'+ value);
        });
    });
</script>
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