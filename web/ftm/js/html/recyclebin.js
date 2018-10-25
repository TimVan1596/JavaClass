//注销页面
function logout() {
    window.location.href = '../LogOutServlet.do';
}

//pageNum = 当前页
//pageSize = 每页行数
let pageNum = 1;
let pageSize = 5;
let query = "";

//模块化初始化 LayUI 框架
layui.use(['layer','element'], function(){
    var layer = layui.layer;
    var element = layui.element;


    //初始化页面
    $(function () {

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
        getAllHiddenDVDs(query,pageNum,pageSize);
    });

    //绑定输入Dalalist模糊搜索事件
    let $query_input =  $('#query_input');
    $query_input.bind('input propertychange', function () {
        let query = $query_input.val();

        $.post('menu/GetAllHiddenDVDs.do',
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

});


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
        getAllHiddenDVDs(query,pageNum,pageSize);

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
function getAllHiddenDVDs(query, pageNum, pageSize){
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

    $.post('menu/GetAllHiddenDVDs.do', {
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

                //还原标识
                let $dvdBtnReturn = $('.dvd-btn-return:last');
                $dvdBtnReturn.attr("value", id);
                $dvdBtnReturn.attr("name", name);

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

//重置搜索结果
function refreshSearch(){
    window.location.replace("recyclebin.jsp");
}

//从回收站还原
function returnShow(obj) {
    let dvdID = obj.getAttribute("value");
    let dvdName = obj.getAttribute("name");

    //询问框

    layer.confirm('是否还原'+dvdID+"-《"+dvdName+"》？", {
        btn: ['确定还原','取消'], //按钮
        shade: [0.1,'#fff'],
        offset: '170px'
    }, function(){

        let loading = layer.load(1, {
            //0.1透明度的白色背景
            shade: [0.1,'#fff'],
            offset: '170px'
        });

        $.post('menu/ReturnManyDVD.do', { ids:dvdID}
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
}
