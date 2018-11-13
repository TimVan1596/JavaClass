//全局变量
let $loginPage =  $('#login-page');


//注册账号
function register() {

    let password = $("[name=password]").val();
    let rePassword = $("[name=re-password]").val();
    let email = $("[name=email]").val();

    if (password !== rePassword) {

        let errorInfo = "您两次输入的密码不同";
        layer.msg(errorInfo, {
            anim: 6
        });

    }
    else if(!checkEmail(email)){
        let errorInfo = "请输入正确格式的邮箱";
        layer.msg(errorInfo, {
            anim: 6
        });
    }
    else {

        //动画 - 收起登录页面，展开认证中
        $loginPage.addClass('test');
        setTimeout(function () {
            $loginPage.addClass('testtwo');
        }, 300);
        setTimeout(function () {
            $('.authent').show().animate({right: -320}, {
                easing: 'easeOutQuint',
                duration: 600,
                queue: false
            });
            $('.authent').animate({opacity: 1}, {
                duration: 200,
                queue: false
            }).addClass('visible');
        }, 200);


        //通过ajax检查是否正常登录
        $.post('../registerAccount.do', {
            email:email,
            password: password
        }, function (ret) {
            //解析ret
            ret = eval("(" + ret + ")");

            if (ret['error'] === 0) {
                //运行成功动画
                setTimeout(function () {
                    $('.success').fadeIn();
                }, 3200);
                setTimeout(function () {
                    $('.authent').show().animate({right: 90}, {
                        easing: 'easeOutQuint',
                        duration: 600,
                        queue: false
                    });
                    $('.authent').animate({opacity: 0}, {
                        duration: 200,
                        queue: false
                    }).addClass('visible');
                    $loginPage.removeClass('testtwo');
                }, 2500);
                setTimeout(function () {
                    $('.login').removeClass('test');
                    $('.login div').fadeOut(123);
                }, 2800);
                setTimeout(function () {
                    $('.success').fadeIn();
                }, 3200);

                alert("注册成功！");
                window.location.href = '../index.html?username='+name;

            } else if (ret['error'] === 1) {
                var errorInfo = ret['errorInfo'];
                alert("注册失败！" + errorInfo);
                location.reload();
            }


        });
    }

}