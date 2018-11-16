//pageNum = 当前页
//pageSize = 每页行数
let pageNum = 1;
let pageSize = 5;
let query = "";

//注销页面
function logout() {
    window.location.href = '../LogOutServlet.do';
}

//模块化初始化 LayUI 框架
layui.use(['layer','element'], function(){
    var layer = layui.layer;
    var element = layui.element;

    //初始化页面
    $(function () {

        //判断是否首次登录，若首次则弹出公告窗口
        if (!isNull($_GET['first'])){
            popUpdateNews();
        }

        //获取当前页
        if (!isNull($_GET['pageNum'])){
            pageNum = $_GET['pageNum'];
        }
        if (!isNull($_GET['query'])){
            query = $_GET['query'];

            //获取【重置搜索】按钮，并让其显示
            let $refreshSearchBtn =  $('#refresh-Search-btn');
            $refreshSearchBtn.removeClass('hidden');

        }

        query = decodeURI(query);

        //通过ajax查询DVD，并插入到表格里
        getAllDVDs(query,pageNum,pageSize);
    });

    //绑定输入Dalalist模糊搜索事件
    let $query_input =  $('#query_input');
    $query_input.bind('input propertychange', function () {
        let query = $query_input.val();

        $.post('menu/GetAllDVDs.do',
            {   query: query,
                pageNum: 1,
                pageSize: 5
            }, function (ret) {

                //解析ret
                ret = eval("(" + ret + ")");

                let $dvdList =  $('#dvdList');
                $dvdList.empty();
                if (parseInt(ret['error']) === 0) {
                    let dvdArr = ret['data']['list'];
                    for (let i = 0; i < dvdArr.length; i++) {
                        let id = dvdArr[i]['id'];
                        let name = dvdArr[i]['name'];
                        let status = dvdArr[i]['status'];

                         let statusText =
                            status ? '归还' : '可借'
                        ;

                        let $option = $("<option>" + id + "-" + name
                            + "-" + statusText + "</option>");
                        $dvdList.append($option);
                    }
                }
                else {
                    layer.msg('查询失败！', {
                    });
                }
            });

    });

    let $authority = ajaxGetAuthority();

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
function editDVD(obj) {
    let dvdID = obj.getAttribute("value");
    let dvdName = obj.getAttribute("name");
    let dvdPreview = obj.getAttribute("preview");

    //添加DVD iframe层
    layer.open({
        type: 2,
        title: '编辑DVD信息',
        shadeClose: true,
        shade: 0.3,
        area: ['380px', '63%'],
        offset: ['110px', '35%'],
        content: 'menu/editDVD.jsp?id='+dvdID
        +'&name='+dvdName+'&preview='+dvdPreview//iframe的url
    });


    //判断用户是否输入内容
    if (name) {
        let loading = layer.load(1, {
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
                let errorInfo = ret['errorInfo'];
                alert("编辑失败！" + errorInfo);
            }
            //关闭loading
            layer.close(loading);
        });

    }
}

//删除DVD信息
function deleteDVD(){

    //jquery获取复选框值
    let chkValStr = " ";
    let cnt = 0;
    //遍历每一个名字为dvd-radio的复选框
    //其中选中的执行函数
    $('input[name="dvd-radio"]:checked')
        .each(function(){
            if (cnt !== 0){
                chkValStr += ',';
            }
            chkValStr += $(this).val();
            cnt++;

    });

    if (cnt> 0 ){
        //警告询问框
        layer.confirm('警告！是否确认删除选中的'+cnt+'项内容？'

            , {
            btn: ['是的','取消'], //按钮
            //0.1透明度的白色背景
            shade: [0.1,'#fff'],
            offset: '170px'
        }, function(){

            layer.confirm('选中数据将被无条件移入回收站（忽略借出情况），您可在10日内还原数据', {
                btn: ['确定删除','取消'], //按钮
                shade: [0.1,'#fff'],
                offset: '170px'
            }, function(){

                let loading = layer.load(1, {
                    //0.1透明度的白色背景
                    shade: [0.1,'#fff'],
                    offset: '170px'
                });

                $.post('menu/DeleteManyDVD.do', { ids:chkValStr}
                    , function (ret) {
                        //解析ret
                        ret = eval("(" + ret + ")");

                        if (ret['error'] === 0) {
                            window.location.reload();
                        }
                        else {
                            let errorInfo = ret['errorInfo'];
                            alert("删除失败！" + errorInfo);
                        }
                        //关闭loading
                        layer.close(loading);
                    });

            }, function(){

            });

        }, function(){

        });
    }
    else{
        alert("您还未选中任何一行");
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
    layer.open({
        type: 2,
        title: '信息统计',
        shadeClose: true,
        shade: 0.6,
        offset: '100px',
        area: ['640px', '460px'],
        content: 'menu/statistics.html' //iframe的url
    });
}

//模糊搜索
function searchToggle(obj, evt){
        let container = $(obj).closest('.search-wrapper');

        if(!container.hasClass('active')){
            container.addClass('active');
            evt.preventDefault();
        }
        else if(container.hasClass('active') && $(obj).closest('.input-holder').length === 0){
            container.removeClass('active');
            // clear input
            container.find('.search-input').val('');
            // clear and hide result container when we press close
            container.find('.result-container')
                .fadeOut(100, function(){$(this).empty();});
        }
    }

function submitFn(obj, evt){
    let value = $(obj).find('.search-input')
        .val().trim();

    if(value.length){
        let info = value.split('-');
        let query = info[0];
        getAllDVDs(query,pageNum,pageSize);

        //获取【重置搜索】按钮，并让其显示
        let $refreshSearchBtn =  $('#refresh-Search-btn');
        $refreshSearchBtn.removeClass('hidden');
    }

    evt.preventDefault();
}

/**Post方法获取DVD信息
 * 通过ajax查询DVD，并插入到表格里
 * @param query 查询条件
 * @param pageNum 分页当前页
 * @param pageSize 分页一页显示多少行
 * */
function getAllDVDs(query, pageNum, pageSize){
    let loading = layer.load(1, {
        //0.1透明度的白色背景
        shade: [0.1,'#fff']
    });

    //获取DVD表格
    let $dvdTable = $("#DVDsTable");
    //清空表格
    $dvdTable.empty();
    //创建表头(th)
    let $th = $("#DVD_TABLE_TH_TEMPLATE").html();
    $dvdTable.append($th);

    $.post('menu/GetAllDVDs.do', {
        pageNum: pageNum,
        pageSize: pageSize,
        query: query
    }, function (ret) {
        //解析ret
        ret = eval("(" + ret + ")");

        if (ret['error'] === 0) {
            let dvdArr = ret['data']['list'];
            let total = ret['data']['total'];

            //插入列表
            for (let i = 0; i < dvdArr.length; i++) {
                //获取到DVD的信息
                let DVD = dvdArr[i];
                let id = DVD['id'];
                let name = DVD['name'];
                let status = DVD['status'];
                let preview = DVD['preview'];

                //创造DVD节点(tr)
                let $tr = $("#DVD_TEMPLATE").html();

                $("#DVDsTable").append($tr);

                //填充文字
                $('.dvd-id:last').text(id);

                //单选框补id
                var $dvdRadio = $('.dvd-radio-input:last');
                $dvdRadio.attr("value", id);

                //预览图加载
                $('.dvd-preview:last').attr("src", preview);
                $('.dvd-name:last').text(name);

                //归还借出标识
                let $dvdBtnLand = $('.dvd-btn-land:last');
                $dvdBtnLand.text(
                    status ? '归还' : '可借'
                );
                if (!status){
                    $('.dvd-tr-line:last').css("background-color"
                        ,"#7ef38296");
                }
                //修改借出归还按钮样式
                $dvdBtnLand.addClass(
                    status ? 'layui-btn-warm'
                        : 'layui-btn-normal'
                );
                $dvdBtnLand.attr("value", id);

                //编辑ID标识
                let $dvdBtnEdit = $('.dvd-btn-edit:last');
                $dvdBtnEdit.attr("value", id);
                $dvdBtnEdit.attr("name", name);
                $dvdBtnEdit.attr("preview", preview);
            }

            //插入分页
            let $tablePagination =  $('#table-pagination');
            $tablePagination.empty();
            for (i = 0; i < total; i++){
                var retPageNum = i+1;
                //创造分页按钮节点(li)
                let $li = '<li><a ';
                if (pageNum == retPageNum){
                    $li += 'class = "active" ';
                }
                $li += ' href= "';
                let url = '?pageNum='+retPageNum;
                if (!isNull(query)){
                    url += '&query='+query;
                }
                url = encodeURI(url);

                $li +=url+ '">'
                    +retPageNum+'</a></li>';
                $tablePagination.append($li);
            }
        }
        else {
            let errorInfo = ret['errorInfo'];
            alert("载入失败！" + errorInfo);
        }
        //关闭loading
        layer.close(loading);
    });
}

//弹出本次[更新内容]窗口
function popUpdateNews(){
    //弹出本次[更新内容]窗口
    layer.open({
        type: 2,
        title: 'BiggerDVD 6.1.0 更新内容',
        shadeClose: true,
        shade: 0.6,
        area: ['380px', '90%'],
        content: 'menu/updateNews.html', //iframe的url
        cancel: function(index){
            layer.close(index);
            window.location.replace("menu.jsp");
            return false;
        }
    });
}

//重置搜索结果
function refreshSearch(){
    window.location.replace("menu.jsp");
}

//打开黑科技
function openLab() {
    //黑科技 iframe层
    layer.open({
        type: 2,
        title: '黑科技实验室',
        shade: 0.3,
        area: ['500px', '75' +
        '%'],
        offset: ['110px', '35%'],
        content: 'menu/lab.html' //iframe的url
    });


}

//放大封面
function onclickImg(obj){
    let src = obj.getAttribute("src");


    let $displayImg = $('#display-img');
    $displayImg.attr("src",src);

    //默认的边框间距
    let SMALL_SCALE = 0.5;

    let conWidth = $(window).width();

    let conHeight  = $(window).height();

    let imgWidth = $displayImg.width();
    let imgHeight = $displayImg.height();
    //原图的宽长比
    let imgRatio = imgWidth / imgHeight;
    //最终输出宽和长
    let reImgWidth = 0;
    let reImgHeight = 0;


    //若原图的宽小于控件宽
    if (imgWidth < conWidth) {
        if (imgHeight < conHeight && imgWidth >= imgHeight) {
            reImgWidth = conWidth * SMALL_SCALE;
            reImgHeight = reImgWidth / imgRatio;
        } else {

            reImgHeight = conHeight * SMALL_SCALE;
            reImgWidth = reImgHeight * imgRatio;

        }
    }
    //若原图的宽大于控件宽
    else {
        if (imgHeight < conHeight) {
            reImgWidth = conWidth * SMALL_SCALE;
            reImgHeight = reImgWidth / imgRatio;
        }
        //若原图的长宽同时大于控件的长宽，最复杂的情况
        else {
            //控件的长比宽大
            let conRatio = conWidth / conHeight;

            if (imgRatio < conRatio) {
                reImgHeight = conHeight * SMALL_SCALE;
                reImgWidth = reImgHeight * imgRatio;
            } else {
                reImgWidth = conWidth * SMALL_SCALE;
                reImgHeight = reImgWidth / imgRatio;
            }
        }
    }



    layer.open({
        type: 1,
        title: false,
        closeBtn: 0,
        shade:0.4,
        offset : ["15%"],
        shadeClose: true,
        area: [reImgWidth, reImgHeight], //宽高
        content: "<img height="+reImgHeight+" width="
        +reImgWidth+" src=" + src + "   />"
    });
}

//申请管理员 applyAdmin
function applyAdmin() {
    alert("申请管理员");
}

//ajax获取当前用户权限值
function ajaxGetAuthority() {
    //权限值
    let $authority = 1;
    $.post('GetAuthority.do', {}, function (ret) {

        //解析ret
        ret = eval("(" + ret + ")");

        if (ret['error'] === 0) {
            $authority = ret['data']['authority'];
            alert("$authority = " +$authority);

        }
        else {

            layer.msg(ret['errorInfo'], function(){
                logout();
            });

        }
    });
    return $authority;
}




