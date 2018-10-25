<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\10\9 0009
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户注册</title>
</head>
<body>
<div align='center'>
    <h2>----欢迎来到注册界面----</h2>
    <h6 style="color: red">账号和密码为字母和数字组成，手机号格式（十一位）：13+任意数,15+除4的任意数,18+除1和4的任意数,17+除9的任意数,147</h6>
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
            cursor: pointer;
        }
    </style>
    <span id="tishi1"></span>
</div>
<form action="../../../../atbRegister.do" method="post">
    <table border="1" width="300" align="center">
        <tr>
            <th width=150px>用户账号：</th>
            <th><input type="text" name="name" id="name" title="" style="text-align: center; width: 150px;"
                       onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"/></th>
        </tr>
        <tr>
            <th width=150px>账号密码：</th>
            <th><input name="password" id="password" type="password" title="" style="text-align: center; width: 150px;"
                       onkeyup="value=value.replace(/[^\w\.\/]/ig,'')" /></th>
        </tr>
        <tr>
            <th width=150px>确认密码：</th>
            <th><input name="cpassword" id="cpassword" type="password" title="" style="text-align: center; width: 150px;"
                       onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"/></th>
        </tr>
        <tr>
            <th width=150px>绑定手机：</th>
            <th><input name="phone" id="phone" type="text" title="" style="text-align: center; width: 150px;"
                       onkeyup="this.value=this.value.replace(/\D/g,'')"/></th>
        </tr>
        <tr>
            <%
                if(request.getAttribute("FH") == null){
            %>
            <th colspan = '1'><a href = '../../../javaWebDvdLogin.jsp'>返回</a></th>
            <%
            }else{
            %>
            <th colspan = '1'><a href = './atb/javaWebDvdLogin.jsp'>返回</a></th>
            <%
                }
            %>
            <th colspan = '1'><button type="submit" class="button">提交</button></th>
        </tr>
        <script type="text/javascript">
            if(<%= request.getAttribute("MSG")!=null %>){
                alert('<%=request.getAttribute("MSG") %>');
            }
        </script>
    </table>
</form>
<script src="jquery-3.3.1.min.js" type="text/javascript"></script>
<script>
    //先将提示隐藏起来
    $("#tishi1").hide();
    $(function() {
        //当输入注册名的输入框获得焦点后就先隐藏提示语
        $("#name").focus(function cls() {
            $("#tishi1").hide();
        });
        //当输入框失去焦点就通过AJAX将数据传递给后端，在后端验证是否已存在该用户名
        $("#name").blur(
            function() {
                var name = $(this).val();
                name = $.trim(name);
                if (name != "") {
                    var url = "../../../../Register.do?";
                    name="name="+name;
                    url = url + name;
                    //$.get()方法能够返回一个JQuery XMLHttpRequest对象
                    var jqxhr = $.get(url, callback);
                    //若执行JQuery出现错误则提示错误信息
                    //在JQuery3.0以后需要用done()、fail()、alwayls()代替success()、error()、complete();
                    jqxhr.fail(function(xhr, error, throwerror) {
                        alert("error" + xhr.status + " error=" + error + " throwerror:" + throwerror);
                    });
                }
            });
    });
    //先将提示隐藏起来
    $("#tishi1").hide();
    $(function() {
        //当输入注册名的输入框获得焦点后就先隐藏提示语
        $("#cpassword").focus(function cls() {
            $("#tishi1").hide();
        });
        //当输入框失去焦点就通过AJAX将数据传递给后端，在后端验证是否已存在该用户名
        $("#cpassword").blur(
            function() {
                var cpassword = $(this).val();
                cpassword = $.trim(cpassword);
                var password = $("[name=password]").val();
                password = $.trim(password);
                if (cpassword != "") {
                    var url = "../../../../modify.do?";
                    cpassword="cpassword="+cpassword;
                    password="&password="+password;
                    url = url + cpassword + password;
                    //$.get()方法能够返回一个JQuery XMLHttpRequest对象
                    var jqxhr = $.get(url, callback);
                    //若执行JQuery出现错误则提示错误信息
                    //在JQuery3.0以后需要用done()、fail()、alwayls()代替success()、error()、complete();
                    jqxhr.fail(function(xhr, error, throwerror) {
                        alert("error" + xhr.status + " error=" + error + " throwerror:" + throwerror);
                    });
                }
            });
    });
    //先将提示隐藏起来
    $("#tishi1").hide();
    $(function() {
        //当输入注册名的输入框获得焦点后就先隐藏提示语
        $("#phone").focus(function cls() {
            $("#tishi1").hide();
        });
        //当输入框失去焦点就通过AJAX将数据传递给后端，在后端验证是否已存在该用户名
        $("#phone").blur(
            function() {
                var phone = $(this).val();
                phone = $.trim(phone);
                if (phone != "") {
                    var url = "../../../../delete.do?";
                    phone="phone="+phone;
                    url = url + phone;
                    //$.get()方法能够返回一个JQuery XMLHttpRequest对象
                    var jqxhr = $.get(url, callback);
                    //若执行JQuery出现错误则提示错误信息
                    //在JQuery3.0以后需要用done()、fail()、alwayls()代替success()、error()、complete();
                    jqxhr.fail(function(xhr, error, throwerror) {
                        alert("error" + xhr.status + " error=" + error + " throwerror:" + throwerror);
                    });
                }
            });
    });
    //ajax的回调函数
    function callback(data, status) {
        $("#tishi1").show();
        $("#tishi1").html(data);
    }
</script>
</body>
</html>
