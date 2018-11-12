//是否锁定
let locked;
$(function () {
    slide();
});

//layui
layui.use(['layer','form'], function () {
    let layer = layui.layer;
    let form = layui.form;

});



window.onresize = function () {
    if(locked==true){
        var boxWidth = $('#slide_box').width();
        $('#slide_xbox').width(boxWidth);
    }else{
        slide();
    }
};

//滑动解锁移动
function slide() {
    var slideBox = $('#slide_box')[0];
    var slideXbox = $('#slide_xbox')[0];
    var btn = $('#btn')[0];
    var slideBoxWidth = slideBox.offsetWidth;
    var btnWidth = btn.offsetWidth;
    //pc端
    btn.ondragstart = function () {
        return false;
    };
    btn.onselectstart = function () {
        return false;
    };
    btn.onmousedown = function (e) {
        var disX = e.clientX - btn.offsetLeft;
        document.onmousemove = function (e) {
            var objX = e.clientX - disX + btnWidth;
            if (objX < btnWidth) {
                objX = btnWidth
            }
            if (objX > slideBoxWidth) {
                objX = slideBoxWidth
            }
            $('#slide_xbox').width(objX + 'px');
        };
        document.onmouseup = function (e) {
            var objX = e.clientX - disX + btnWidth;
            if (objX < slideBoxWidth) {
                objX = btnWidth;
            } else {
                objX = slideBoxWidth;
                locked = true;
                $('#slide_xbox').html('验证通过<div id="btn"><i class="iconfont icon-xuanzhong" style="color: #dc6180;line-height: inherit;"></i></div>');
                slideSuccess();
            }
            $('#slide_xbox').width(objX + 'px');
            document.onmousemove = null;
            document.onmouseup = null;
        };
    };
    //移动端
    var cont = $("#btn");
    var startX = 0, sX = 0, moveX = 0,leftX = 0;
    cont.on({//绑定事件
        touchstart: function (e) {
            startX = e.originalEvent.targetTouches[0].pageX;//获取点击点的X坐标
            sX = $(this).offset().left;//相对于当前窗口X轴的偏移量
            leftX = startX - sX;//鼠标所能移动的最左端是当前鼠标距div左边距的位置
        },
        touchmove: function (e) {
            e.preventDefault();
            moveX = e.originalEvent.targetTouches[0].pageX;//移动过程中X轴的坐标
            var objX = moveX - leftX + btnWidth;
            if (objX < btnWidth) {
                objX = btnWidth
            }
            if (objX > slideBoxWidth) {
                objX = slideBoxWidth
            }
            $('#slide_xbox').width(objX + 'px');
        },
        touchend: function (e) {
            var objX = moveX - leftX + btnWidth;
            if (objX < slideBoxWidth) {
                objX = btnWidth;
            } else {
                objX = slideBoxWidth;
                locked = true;
                $('#slide_xbox').html('验证通过<div id="btn"><i class="iconfont icon-xuanzhong" style="color: #af4e67;"></i></div>');
                slideSuccess();
            }
            $('#slide_xbox').width(objX + 'px');
        }
    });
}

//修改账号密码
function resetPassword() {
    let email = $("#user-email").val();

    //非空判断
    if (!locked) {
        let errorInfo = "请按住滑块，拖动到最右边";
        layer.msg(errorInfo, {
            anim: 6
        });
    }
    else if (isNull(email)) {
        let errorInfo = "邮箱不能为空！";
        layer.msg(errorInfo, {
            anim: 6
        });
    }
    else if(!checkEmail(email)){
        let errorInfo = "邮箱格式有误！";
        layer.msg(errorInfo, {
            anim: 6
        });
    }
    else {

        //通过ajax检查是否正常登录
        $.post('../resetPassword.do', {
            name: name,
            email: email
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

//验证成功
function slideSuccess() {
    //显示提交按钮
    //测试代码
    let submitBTN = $('#login-submit');
    submitBTN.attr('type','submit');
}