layui.use(['upload','form'], function(){
    var upload = layui.upload;
    var form = layui.form;

    //DVD预览封面上传
    var uploadInst = upload.render({
        elem: '#preview-upload'
        ,url: '/java/ftm/html/menu/adddvd/UploadPreview.do'
        ,before: function(obj){

            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                $('#preview-upload-img').attr('src', result);                       //图片链接（base64）
            });
        }
        ,done: function(ret){

            var data = ret['data'];
            $('#preview-hidden').val(data);

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

        $.post('editdvd/EditDVD.do',data.field
            , function (ret) {
                //解析ret
                ret = eval("(" + ret + ")");

                if (ret['error'] === 0) {
                    alert("修改成功！");
                    //先得到当前iframe层的索引
                    var index = parent.layer
                        .getFrameIndex(window.name);
                    //再执行关闭
                    parent.layer.close(index);
                    parent.location.reload();
                }
                else {
                    var errorInfo = ret['errorInfo'];
                    alert("修改失败！" + errorInfo);
                }

                //关闭所有弹窗
                layer.closeAll();

            });


        //阻止表单跳转
        return false;
    });

    $(function () {
        let loading = layer.load(1, {
            //0.1透明度的白色背景
            shade: [0.1,'#fff'],
            offset: '170px'
        });

        //获取编号和名称
        if (!isNull($_GET['id']) && !isNull($_GET['name'])){
            let id = $_GET['id'];
            let name = $_GET['name'];
            let preview = $_GET['preview'];

            name = decodeURI(name);

            $('#id-hidden').val(id);
            $('#user-name').val(name);

            $('#preview-upload-img').attr('src', preview);

        }

        //关闭转轮
        layer.close(loading);


    });



});
