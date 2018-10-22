//注销页面
function logout() {
    window.location.href = '../LogOutServlet.do';
}

//模块化初始化 LayUI 框架
layui.use('layer', function(){
    var layer = layui.layer;

    //初始化页面
    $(function () {

        var loading = layer.load(1, {
            //0.1透明度的白色背景
            shade: [0.1,'#fff']
        });

        var pageNum = 1;
        if (!isNull($_GET['pageNum'])){
            pageNum = $_GET['pageNum'];
        }

        //通过ajax获取每一行
        $.post('menu/GetAllDVDs.do', {
            pageNum: pageNum,
            pageSize: 6
        }, function (ret) {
            //解析ret
            ret = eval("(" + ret + ")");

            if (ret['error'] === 0) {
                var dvdArr = ret['data']['list'];
                var total = ret['data']['total'];

                //插入列表
                for (var i = 0; i < dvdArr.length; i++) {
                    //获取到DVD的信息
                    var DVD = dvdArr[i];
                    var id = DVD['id'];
                    var name = DVD['name'];
                    var status = DVD['status'];
                    var preview = DVD['preview'];

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

                    //预览图加载
                    $('.dvd-preview:last').attr("src", preview);
                    $('.dvd-name:last').text(name);

                    //归还借出标识
                    var $dvdBtnLand = $('.dvd-btn-land:last');
                    $dvdBtnLand.text(
                        status ? '归还' : '可借'
                    );
                    if (!status){
                        $('.dvd-tr-line:last').css("background-color"
                            ,"#7ef38296");
                    }
                    $dvdBtnLand.attr("value", id);
                }

                //插入分页
                for (i = 0; i < total; i++){
                    var retPageNum = i+1;
                    //创造分页按钮节点(li)
                    var $li = '<li><a ';
                    if (pageNum == retPageNum){
                        $li += 'class = "active"';
                    }
                    $li += 'href="?pageNum='+retPageNum+'">'
                        +retPageNum+'</a></li>';
                    $('#table-pagination').append($li);
                }
            }
            else {
                var errorInfo = ret['errorInfo'];
                alert("载入失败！" + errorInfo);
            }
            //关闭loading
            layer.close(loading);
        });
    });

});

//添加DVD
function addDVD() {

    //添加DVD iframe层
    layer.open({
        type: 2,
        title: '添加新的DVD',
        shadeClose: true,
        shade: 0.3,
        area: ['380px', '63%'],
        offset: ['110px', '35%'],
        content: 'menu/addDVD.jsp' //iframe的url
    });
}

//编辑DVD信息
function editDVD() {
    var dvdID = $('input[name="dvd-radio"]:checked').val();
    var name = prompt("请输入DVD的新名称");

    //判断用户是否输入内容
    if (name) {
        var loading = layer.load(1, {
            //0.1透明度的白色背景
            shade: [0.1,'#fff']
        });
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
            //关闭loading
            layer.close(loading);
        });

    }
}

//删除DVD信息
function deleteDVD(){
    var dvdID = $('input[name="dvd-radio"]:checked').val();

    var deleteConfirm =confirm("是否确认删除DVD信息");
    if ( deleteConfirm === true){

        var loading = layer.load(1, {
            //0.1透明度的白色背景
            shade: [0.1,'#fff'],
            offset: '170px'
        });


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
            //关闭loading
            layer.close(loading);
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
        var loading = layer.load(1, {
            //0.1透明度的白色背景
            shade: [0.1,'#fff'],
            offset: '170px'
        });
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
            //关闭loading
            layer.close(loading);
        });
    }

}

//信息统计
function getStatistics() {
    window.open("menu/statistics.html");
}
