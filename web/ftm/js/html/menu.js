function logout() {
    window.location.href = '../LogOutServlet.do';
}

//初始化页面
$(function () {

    var ALL_STATUS = -1;

    //通过ajax获取每一行
    $.post('menu/GetAllDVDs.do', {
        status: ALL_STATUS
    }, function (ret) {
        //解析ret
        ret = eval("(" + ret + ")");

        if (ret['error'] === 0) {
            var dvdArr = ret['dvdArr'];
            for (var i = 0; i < dvdArr.length; i++) {
                //获取到DVD的信息
                var DVD = dvdArr[i];
                var id = DVD['id'];
                var name = DVD['name'];
                var status = DVD['status'];

                //创造DVD节点(tr)
                var $tr = $("#DVD_TEMPLATE").html();

                $("#DVDsTable").append($tr);


                //填充文字
                $('.dvd-id:last').text(id);

                //单选框补id
                var $dvdRadio = $('.dvd-radio-input:last');
                $dvdRadio.attr("value", id);
                //单选框第一个checked
                if (i === 0) {
                    $dvdRadio.attr("checked", "checked");
                }

                $dvdRadio.attr("value", id);

                $('.dvd-name:last').text(name);
                $('.dvd-status:last').text(
                    status ? '已借出' : '未借出'
                );

                //归还借出标识
                var $dvdBtnLand = $('.dvd-btn-land:last');
                $dvdBtnLand.text(
                    status ? '归还' : '借出'
                );
                if (!status){
                    $('.dvd-tr-line:last').css("background-color"
                        ,"#7ef38296");

                }
                $dvdBtnLand.attr("value", id);


            }

        }
        else {
            var errorInfo = ret['errorInfo'];
            alert("载入失败！" + errorInfo);
        }
    });
});

//添加DVD
function addDVD() {

    var name = prompt("请输入要添加的DVD名称？");

    //判断用户是否输入内容
    if (name) {
        $.post('menu/AddDVD.do', {
            name: name
        }, function (ret) {
            //解析ret
            ret = eval("(" + ret + ")");

            if (ret['error'] === 0) {
                window.location.reload();
            }
            else {
                var errorInfo = ret['errorInfo'];
                alert("添加失败！" + errorInfo);
            }
        });

    }

}

//编辑DVD信息
function editDVD() {
    var dvdID = $('input[name="dvd-radio"]:checked').val();
    var name = prompt("请输入DVD的新名称");

    //判断用户是否输入内容
    if (name) {
        $.post('menu/EditDVD.do', {
            name: name,
            id:dvdID
        }, function (ret) {
            //解析ret
            ret = eval("(" + ret + ")");

            if (ret['error'] === 0) {
                window.location.reload();
            }
            else {
                var errorInfo = ret['errorInfo'];
                alert("编辑失败！" + errorInfo);
            }
        });

    }
}

//删除DVD信息
function deleteDVD(){
    var dvdID = $('input[name="dvd-radio"]:checked').val();

    var deleteConfirm =confirm("是否确认删除DVD信息");
    if ( deleteConfirm === true){
        $.post('menu/DeleteDVD.do', { id:dvdID}, function (ret) {
            //解析ret
            ret = eval("(" + ret + ")");

            if (ret['error'] === 0) {
                window.location.reload();
            }
            else {
                var errorInfo = ret['errorInfo'];
                alert("删除失败！" + errorInfo);
            }
        });
    }

}

//借入or还回DVD操作
function loanOrReturnDVD(obj){
    var dvdID = obj.getAttribute("value");

    var LorRConfirmTips =
        (obj.innerHTML === '借出' ? '归还':'借出');

    var LorRConfirm =confirm('是否确定'+LorRConfirmTips+'DVD');
    if ( LorRConfirm === true){
        $.post('menu/LoanOrReturnDVD.do', { id:dvdID}, function (ret) {

            //解析ret
            ret = eval("(" + ret + ")");

            if (ret['error'] === 0) {
                window.location.reload();
            }
            else {
                var errorInfo = ret['errorInfo'];
                alert("操作失败！" + errorInfo);
            }

        });
    }

}

//信息统计
function getStatistics() {
    window.location.href = 'menu/statistics.html';
}

//进入菜单（JSP/JSTL版）
function displayJSTL() {
    window.location.href = 'menu/GetAllDVDs4JSTL';
}