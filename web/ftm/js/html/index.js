//全局变量
let $loginPage =  $('#login-page');
let $registerPage =  $('#register-page');

//layui
layui.use('layer', function () {
    let layer = layui.layer;
});

//绑定登录点击事件
$('#login-submit').click(function () {

    let email = $("[name=email]").val();
    let password = $("#user-password").val();

    //非空判断
    if (isNull(email) || isNull(password)) {
        let errorInfo = "邮箱或密码不能为空！";
        layer.msg("登录失败！" + errorInfo, {
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
        $.post('AccountCheckServlet.do', {
            email: email,
            password: password
        }, function (ret) {
            //解析ret
            ret = eval("(" + ret + ")");

            //关闭动画
            setTimeout(function () {
                $loginPage.removeClass('test');
                $('.login div').fadeOut(123);
            }, 100);


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

                let isFirstLogin = ret['data']['isFirstLogin'];

                let MenuURL = 'html/menu.jsp';

                if (isFirstLogin === '1'){
                    MenuURL += '?first=1';
                }

                window.location.href = MenuURL;

            } else if (ret['error'] === 1) {
                var errorInfo = ret['errorInfo'];

                layer.msg("登录失败！" + errorInfo, {
                    anim: 6
                });

                setTimeout(function () {
                    location.reload();
                }, 2000);
            }

        });
    }
});

$('input[type="text"],input[type="password"]').focus(function () {
    $(this).prev().animate({'opacity': '1'}, 200);
});
$('input[type="text"],input[type="password"]').blur(function () {
    $(this).prev().animate({'opacity': '.5'}, 200);
});
$('input[type="text"],input[type="password"]').keyup(function () {
    if (!$(this).val() == '') {
        $(this).next().animate({
            'opacity': '1',
            'right': '30'
        }, 200);
    } else {
        $(this).next().animate({
            'opacity': '0',
            'right': '20'
        }, 200);
    }
});
var open = 0;
$('.tab').click(function () {
    $(this).fadeOut(200, function () {
        $(this).parent().animate({'left': '0'});
    });
});

