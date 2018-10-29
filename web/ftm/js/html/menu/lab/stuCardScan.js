
layui.use(['upload','form'], function(){
    let upload = layui.upload;
    let form = layui.form;

    //DVD预览封面上传
    let uploadInst = upload.render({
        elem: '#preview-upload'
        ,url: '/ftm/html/menu/lab/StuCardScan.do'
        ,before: function(obj){

            let uploadLoading = layer.msg('识别中', {
                icon: 16
                ,shade: 0.01
            });

            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                $('#preview-upload-img').attr('src', result);
                //图片链接（base64）
            });
        }
        ,done: function(ret){

            let data = ret['data'];
            if (ret['error'] === 0) {
                $('#stu-name').html(data['name']);
                $('#stu-dept').html(data['dept']);
                $('#stu-id').html(data['id']);
            }
            else {
                //演示失败状态，并实现重传
                let demoText = $('#demoText');
                demoText.html('<span style="color: #FF5722;">' +
                    '识别出了一点问题</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                demoText.find('.demo-reload').on('click', function(){
                    uploadInst.upload();
                });
            }


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
        alert("进行提交");

        //阻止表单跳转
        return false;
    });

});

function back() {
    window.location.href="../lab.html";
}

