//绑定登录点击事件
$('input[type="submit"]').click(function () {

    let name = $("#user-name").val();
    let password = $("#user-password").val();



    //非空判断
    if (isNull(name) || isNull(password)) {
        alert("用户名或密码不能为空！");
    }
    else {

        //动画 - 收起登录页面，展开认证中
        $('.login').addClass('test');
        setTimeout(function () {
            $('.login').addClass('testtwo');
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
            name: name,
            password: password
        }, function (ret) {
            //解析ret
            ret = eval("(" + ret + ")");

            //关闭动画
            setTimeout(function () {
                $('.login').removeClass('test');
                $('.login div').fadeOut(123);
            }, 100);


            if (ret['error'] === 0) {
                window.location.href = 'html/menu.jsp';

            } else if (ret['error'] === 1) {
                var errorInfo = ret['errorInfo'];
                alert("登录失败！" + errorInfo);
                location.reload();
            }

        });
    }

    let isSuccess = false;
    if (isSuccess){


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
            $('.login').removeClass('testtwo');
        }, 2500);

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