
$(function () {
    slide();
});

//layui
layui.use(['layer','form'], function () {
    let layer = layui.layer;
    let form = layui.form;

});


//修改账号密码
function resetPassword() {

    let name = $("[name=name]").val();
    let password = $("[name=password]").val();
    let rePassword = $("[name=re-password]").val();

    if (password !== rePassword) {
        let errorInfo = "两次输入的密码不同！";
        layer.msg(errorInfo, {
            anim: 6
        });
    }
    else {
        let uploadLoading = layer.msg('发送邮件中', {
            icon: 16
            ,shade: 0.01
        });

        //通过ajax检查是否正常登录
        $.post('resetPassword.do', {
            name: name,
            password: password
        }, function (ret) {
            //解析ret
            ret = eval("(" + ret + ")");

            if (ret['error'] === 0) {

                let info = "密码修改成功!";
                layer.msg(info);
                setTimeout(function () {
                    window.location.href = '../index.html?username='+name;
                }, 2000);


            } else if (ret['error'] === 1) {
                let errorInfo = ret['errorInfo'];
                errorInfo = "修改失败！" + errorInfo;

                layer.msg(errorInfo, {
                    anim: 6
                });
                setTimeout(function () {
                    location.reload();
                }, 2000);
            }

            //关闭上传loading
            layer.closeAll();

        });
    }

}
