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
    <%--layui引用--%>
    <script src="../../common/util/layui/layui.js"></script>
    <link rel="stylesheet" href="../../common/util/layui/css/layui.css">
</head>
<body>
<div id="view-member-div" style="padding: 1% 5%;display: none">
    <table class="layui-table"  id="view-member-table"></table>
</div>

<!--弹出-->
<script>
    $(".layui-table").click(function () {
        layer.open({
            type: 1,
            content: $('#view-member-div'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
            title : '查看会员',
            area: ['800px', '420px'],
            success: function(layero, index){
                layui.use('table', function(){
                    var table = layui.table;
                    var cols =  [[ //标题栏
                        {field:'num', title: '序号',align: 'center'}
                        ,{field:'cardNum', title: '会员卡号',align: 'center'}
                        ,{field:'Name', title: '会员姓名',align: 'center'}
                        ,{field:'sex', title: '性别',align: 'center'}
                        ,{field:'grade', title: '会员等级',align: 'center'}
                        ,{field:'tel', title: '手机号码',align: 'center'}
                        ,{field:'balance',title: '剩余金额',align: 'center'}
                        ,{field:'remainingPoints', title: '剩余积分',align: 'center'}
                        ,{field:'time', title: '领卡时间',align: 'center'}
                    ]]
                    //展示已知数据
                    table.render({
                        elem: '#view-member-table'
                        , url: './user'
                        ,size:'sm'
                        ,cols:cols
                        ,even: true
                        , height: '300'
                        ,page: true //是否显示分页
                    });

                });
            }

        });
    })
</script>
</body>
</html>
