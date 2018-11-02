<%--
  添加新DVD页面 （iFrame）
  User: TimVan
  Date: 2018/10/18
  Time: 10:26
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet"
          href="../../../common/util/layui/css/layui.css"
          media="all">
    <style>
        .layui-upload-img{
            width: 92px; height: 92px;
            margin: 0 10px 10px 0;}

    </style>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="../../../common/util/layui/layui.js"></script>
</head>
<body>
<%
    //初始化常量信息
    String userName = "非法用户";

    if (session.getAttribute("userName") != null) {
        userName = (String) session.getAttribute("userName");
    } else {
        response.sendRedirect("../index.html");
        return;
    }

%>
    <form style="margin: 5%" class="layui-form layui-form-pane">
            <br>
            <%--DVD名称--%>
                <div class="layui-form-item">
            <label class="layui-form-label">DVD名称
            </label>
            <div class="layui-input-block">
                <input type="text" name="name"
                       autocomplete="off" lay-verify="name"
                       placeholder="请不要输入特殊字符"
                       class="layui-input">
            </div>
        </div>
            <%--上传封面--%>
                <div class="layui-upload">

                        <button type="button"
                                class="layui-btn  layui-btn-normal"
                                id="preview-upload">
                            <i class="layui-icon">&#xe67c;</i>
                            上传封面</button>
                        <div class="layui-upload-list">
                            <img class="layui-upload-img"                                   id="preview-upload-img">
                            <p id="demoText"></p>
                        </div>

                    <input type="hidden" name="preview" id="preview-hidden">
                    </div>
            <%--提交按钮--%>
                <div style="margin-top: 1%">
                    <button class="layui-btn  layui-btn-normal
                    layui-btn-radius layui-btn-fluid"
                            lay-filter = "addSubmmit"
                            lay-submit="" >添加</button>
                </div>
    </form>

    <script>
        layui.use(['upload','form'], function(){
            var upload = layui.upload;
            var form = layui.form;

            //DVD预览封面上传
            var uploadInst = upload.render({
                elem: '#preview-upload'
                ,url:
                    '/java/ftm/html/menu/adddvd/UploadPreview.do'
                ,before: function(obj){

                    let uploadLoading = layer.msg('上传中', {
                        icon: 16
                        ,offset:['15%','15%']
                        ,shade: 0.01
                    });
                    //预读本地文件示例，不支持ie8
                    obj.preview(function(index, file, result){
                        $('#preview-upload-img').attr('src', result);                       //图片链接（base64）
                    });

                }
                ,done: function(ret){

                    let data = ret['data'];
                    $('#preview-hidden').val(data);
                    $('#preview-upload-img').attr('src', data);

                    //关闭上传loading
                    layer.closeAll();

                }
                ,error: function(){
                    //演示失败状态，并实现重传
                    var demoText = $('#demoText');
                    demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                    demoText.find('.demo-reload').on('click', function(){
                        uploadInst.upload();
                    });
                }
            });

            form.verify({
                name: function(value){ //value：表单的值、item：表单的DOM对象
                    if(!value){
                        return 'DVD名称不能为空';
                    }
                }

            });

            form.on('submit(addSubmmit)', function(data){

                var loading = layer.load(1, {
                    //0.1透明度的白色背景
                    shade: [0.1,'#fff'],
                    offset: '170px'
                });

                $.post('adddvd/AddDVD.do',data.field
                    , function (ret) {
                    //解析ret
                    ret = eval("(" + ret + ")");

                    if (ret['error'] === 0) {
                        alert("添加成功！");
                        //先得到当前iframe层的索引
                        var index = parent.layer
                            .getFrameIndex(window.name);
                        //再执行关闭
                        parent.layer.close(index);
                        parent.location.reload();
                    }
                    else {
                        var errorInfo = ret['errorInfo'];
                        alert("添加失败！" + errorInfo);
                    }

                    //关闭所有弹窗
                    layer.closeAll();

                });


                //阻止表单跳转
                return false;
            });

        });

    </script>
</body>
</html>
