<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/10/28 0028
  Time: 12:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/book/addbook" method="get"  onsubmit="return check()">
    <fieldset>
        <legend>添加</legend>
        图书名称:<input type="text" name="name"  id="name" /><br>
        图书作者:<input type="text" name="author"  id="author"/><br>
        图书价格:<input type="text" name="price"  id="price"/><br>
        图书日期:<input type="date" name="date"  id="date"/><br>
        图书类型:<select name="type" >
        <option>仙侠</option>
        <option>玄幻</option>
        <option>科幻</option>
    </select><br>
        <input type="submit"  value="添加">

    </fieldset>
</form>

<script>
    function check(){
        //1.取值
        var  name=document.getElementById("name").value;
        var  author=document.getElementById("author").value;
        var  price=document.getElementById("price").value;
        var  date=document.getElementById("date").value;
        //2.判断
        if(name!=""){
            if(author!=""){
                if(price!=""){
                    if(date!=""){

                        return true;
                    }else{
                        alert("亲，BOOK日期不能为空哦~");
                        return false;
                    }

                }else{
                    alert("亲，BOOK价格不能为空哦~");
                    return false;
                }

            }else{
                alert("亲，BOOK作者不能为空哦~");
                return false;
            }

        }else{
            alert("亲，BOOK名称不能为空哦~");
            return false;
        }
    }
</script>
</body>
</html>
