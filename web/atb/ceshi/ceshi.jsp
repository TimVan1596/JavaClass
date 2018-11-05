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