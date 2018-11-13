<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\10\18 0018
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传</title>
    <%--JS--%>
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
    <%--JQ--%>
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
<img src="../javaWebDvd/image/20181023165018png.png" width="60px" height="50px">
<img src="../javaWebDvd/image/20181023165018png.png" width="60px" height="50px">
<form>
    <input type="button" id="btn" onclick="settime(this)" value="获取验证码">
</form>
<script type="text/javascript">
    if(<%= request.getAttribute("MSG")!=null %>){
        alert('<%=request.getAttribute("MSG") %>');
    }
    var countdown=5;
    function settime(val) {
        if (countdown == 0) {
            val.removeAttribute("disabled");
            val.value="获取验证码";
            countdown = 5;
        } else {
            val.setAttribute("disabled", true);
            val.value="重新发送(" + countdown + ")";
            countdown--;
        }
        setTimeout(function() {
            settime(val)
        },1000)
    }
</script>
</body>
</html>