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
</head>
<body>
<input type="text" name="registerName" id="registerName" placeholder="设置您的用户名">
<span id="tishi1"></span>
<br>
<input type="text" name="name" id="name" placeholder="测试">
<script src="../javaWebDvd/jsp/register/jquery-3.3.1.min.js" type="text/javascript"></script>
<script>
    //先将提示隐藏起来
    $("#tishi1").hide();

    $(function() {
        //当输入注册名的输入框获得焦点后就先隐藏提示语
        $("#registerName").focus(function cls() {
            $("#tishi1").hide();
        });
        //当输入框失去焦点就通过AJAX将数据传递给后端，在后端验证是否已存在该用户名
        $("#registerName").blur(
            function() {
                var registerName = $(this).val();
                registerName = $.trim(registerName);
                if (registerName != "") {
                    var url = "../../UploadServlet.do?";
                    registerName="registerName="+registerName;
                    url = url + registerName;
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