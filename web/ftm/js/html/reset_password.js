$(function () {
    $('#mpanel1').slideVerify({
        type : 1,		//类型
        vOffset : 5,	//误差量，根据需求自行调整
        barSize : {
            width : '80%',
            height : '40px',
        },
        ready : function() {
        },
        success : function() {
            alert('验证成功，添加你自己的代码！');
            //......后续操作
        },
        error : function() {
//		        	alert('验证失败！');
        }

    });
});

//修改账号密码
function resetPassword() {

    let name = $("[name=name]").val();
    let password = $("[name=password]").val();
    let rePassword = $("[name=re-password]").val();

    if (password !== rePassword) {
        alert("两次输入的密码不同！");
    }
    else {
        //通过ajax检查是否正常登录
        $.post('../resetPassword.do', {
            name: name,
            password: password
        }, function (ret) {
            //解析ret
            ret = eval("(" + ret + ")");

            if (ret['error'] === 0) {
                alert("密码修改成功！");
                window.location.href = '../index.html?username='+name;

            } else if (ret['error'] === 1) {
                let errorInfo = ret['errorInfo'];
                alert("修改失败！" + errorInfo);
                location.reload();
            }


        });
    }

}