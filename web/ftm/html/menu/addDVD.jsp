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
    <form style="margin: 5%"
            class="layui-form layui-form-pane" action="">

            <%--DVD名称--%>
                <div class="layui-form-item">
            <label class="layui-form-label">DVD名称 ：
            </label>
            <div class="layui-input-block">
                <input type="text" name="name"
                       autocomplete="off"
                       placeholder="请不要输入特殊字符"
                       class="layui-input">
            </div>
        </div>
            <%--上传封面--%>
                <div class="layui-upload">

                    <div style="">
                        <button type="button"
                                class="layui-btn"
                                id="preview-upload">
                            上传图片</button>
                        <div class="layui-upload-list">
                            <img class="layui-upload-img"                                   id="preview-upload-img">
                            <p id="demoText"></p>
                        </div>
                    </div>


                </div>

    </form>

    <script>
        layui.use('upload', function(){
            var upload = layui.upload;

            //DVD预览封面上传
            var uploadInst = upload.render({
                elem: '#preview-upload'
                ,url: '/upload/'
                ,before: function(obj){
                    //预读本地文件示例，不支持ie8
                    obj.preview(function(index, file, result){
                        $('#preview-upload-img').attr('src', result);        //图片链接（base64）
                    });
                }
                ,done: function(res){
                    //如果上传失败
                    if(res.code > 0){
                        return layer.msg('上传失败');
                    }
                    //上传成功
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
        });
    </script>
</body>
</html>
