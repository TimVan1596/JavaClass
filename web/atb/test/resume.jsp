<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\11\9 0009
  Time: 9:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>动态简历</title>
    <style>
        .float{
            float:left;
            width : 200px;
            height: 200px;
            overflow: hidden;
            border: 1px solid #CCCCCC;
            border-radius: 10px;
            padding: 5px;
            margin: 5px;
        }
        img{
            position: relative;
        }
        .result{
            width: 200px;
            height: 200px;
            text-align: center;
            box-sizing: border-box;
        }

        #file_input{
            display: none;
        }

        .delete{
            width: 200px;
            height:200px;
            position: absolute;
            text-align: center;
            line-height: 200px;
            z-index: 10;
            font-size: 30px;
            background-color: rgba(255,255,255,0.8);
            color: #777;
            opacity: 0;
            transition-duration:0.7s;
            -webkit-transition-duration: 0.7s;
        }

        .delete:hover{
            cursor: pointer;
            opacity: 1;
        }

        div.page {
            width: 900px;
            margin: 30px auto;
            font-family:SimHei;
        }

        div.top {
            background-color: #343a40;
            height: 35px;
            line-height: 35px;
            text-align: center;
            color: #fff;
            font-size: 18px;
        }

        /*.footer {*/
            /*height: 30px;*/
            /*line-height: 30px;*/
            /*position: fixed;*/
            /*bottom: 0;*/
            /*width: 100%;*/
            /*text-align: center;*/
            /*background: #333;*/
            /*color: #fff;*/
            /*font-size: 12px;*/
            /*letter-spacing: 1px;*/
        /*}*/
        .footer {
            /*position: fixed;*/
            /*bottom: 0;*/
            /*width: 100%;*/
            /*text-align: center;*/
            /*background: #333;*/
            /*color: #fff;*/
            /*font-size: 12px;*/
            /*letter-spacing: 1px;*/
        }

        .title {
            background-color: #343a40;
            color: #fff;
            height: 100%;
            line-height: 35px;
            font-size: 24px;
            display: inline-block;
            padding: 0px 70px;
        }

        li {
            list-style-type: none;
        }

        li.topContact {
            display: inline-block;
            padding: 0px 70px;
        }

        /* Panel 是简历信息具体的内容面板，即可伸缩面板 */
        .panel {
            margin-top: 20px;
            padding: 0px 0px 20px 0px;
        }

        .panelTitle {
            height: 35px;
            width: 100%;
            border-bottom: 2px solid #343a40;
        }

        .panelContent {
            width: 500px;
        }

        div.colorBlock, div.title {
            float: left;
        }

        .colorBlock {
            height: 100%;
            width: 25px;
            margin-right: 15px;
            background-color: #343a40;
        }

    </style>
    <script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
    <script src="../../common/util/layui/layui.js"></script>
    <link rel="stylesheet" href="../../common/util/layui/css/layui.css">
    <%--伸缩和省市--%>
    <script type="text/javascript">
        var citys = '[{ "name": "北京", "cities": ["西城", "东城", "崇文", "宣武", "朝阳", "海淀", "丰台", "石景山", "门头沟", "房山", "通州", "顺义", "大兴", "昌平", "平谷", "怀柔", "密云", "延庆"] },'
            + '{ "name": "天津", "cities": ["青羊", "河东", "河西", "南开", "河北", "红桥", "塘沽", "汉沽", "大港", "东丽", "西青", "北辰", "津南", "武清", "宝坻", "静海", "宁河", "蓟县", "开发区"] },'
            + '{ "name": "河北", "cities": ["石家庄", "秦皇岛", "廊坊", "保定", "邯郸", "唐山", "邢台", "衡水", "张家口", "承德", "沧州", "衡水"] },'
            + '{ "name": "山西", "cities": ["太原", "大同", "长治", "晋中", "阳泉", "朔州", "运城", "临汾"] },'
            + '{ "name": "内蒙古", "cities": ["呼和浩特", "赤峰", "通辽", "锡林郭勒", "兴安"] },'
            + '{ "name": "辽宁", "cities": ["大连", "沈阳", "鞍山", "抚顺", "营口", "锦州", "丹东", "朝阳", "辽阳", "阜新", "铁岭", "盘锦", "本溪", "葫芦岛"] },'
            + '{ "name": "吉林", "cities": ["长春", "吉林", "四平", "辽源", "通化", "延吉", "白城", "辽源", "松原", "临江", "珲春"] },'
            + '{ "name": "黑龙江", "cities": ["哈尔滨", "齐齐哈尔", "大庆", "牡丹江", "鹤岗", "佳木斯", "绥化"] },'
            + '{ "name": "上海", "cities": ["浦东", "杨浦", "徐汇", "静安", "卢湾", "黄浦", "普陀", "闸北", "虹口", "长宁", "宝山", "闵行", "嘉定", "金山", "松江", "青浦", "崇明", "奉贤", "南汇"] },'
            + '{ "name": "江苏", "cities": ["南京", "苏州", "无锡", "常州", "扬州", "徐州", "南通", "镇江", "泰州", "淮安", "连云港", "宿迁", "盐城", "淮阴", "沐阳", "张家港"] },'
            + '{ "name": "浙江", "cities": ["杭州", "金华", "宁波", "温州", "嘉兴", "绍兴", "丽水", "湖州", "台州", "舟山", "衢州"] },'
            + '{ "name": "安徽", "cities": ["合肥", "马鞍山", "蚌埠", "黄山", "芜湖", "淮南", "铜陵", "阜阳", "宣城", "安庆", "淮北"] },'
            + '{ "name": "福建", "cities": ["福州", "厦门", "泉州", "漳州", "南平", "龙岩", "莆田", "三明", "宁德"] },'
            + '{ "name": "江西", "cities": ["南昌", "景德镇", "上饶", "萍乡", "九江", "吉安", "宜春", "鹰潭", "新余", "赣州"] },'
            + '{ "name": "山东", "cities": ["青岛", "济南", "淄博", "烟台", "泰安", "临沂", "日照", "德州", "威海", "东营", "荷泽", "济宁", "潍坊", "枣庄", "聊城"] },'
            + '{ "name": "河南", "cities": ["郑州", "洛阳", "开封", "平顶山", "濮阳", "安阳", "许昌", "南阳", "信阳", "周口", "新乡", "焦作", "三门峡", "商丘"] },'
            + '{ "name": "湖北", "cities": ["武汉", "襄樊", "孝感", "十堰", "荆州", "黄石", "宜昌", "黄冈", "恩施", "鄂州", "江汉", "随枣", "荆沙", "咸宁"] },'
            + '{ "name": "湖南", "cities": ["长沙", "湘潭", "岳阳", "株洲", "怀化", "永州", "益阳", "张家界", "常德", "衡阳", "湘西", "邵阳", "娄底", "郴州"] },'
            + '{ "name": "广东", "cities": ["广州", "深圳", "东莞", "佛山", "珠海", "汕头", "韶关", "江门", "梅州", "揭阳", "中山", "河源", "惠州", "茂名", "湛江", "阳江", "潮州", "云浮", "汕尾", "潮阳", "肇庆", "顺德", "清远"] },'
            + '{ "name": "广西", "cities": ["南宁", "桂林", "柳州", "梧州", "来宾", "贵港", "玉林", "贺州"] },'
            + '{ "name": "海南", "cities": ["海口", "三亚"] },'
            + '{ "name": "重庆", "cities": ["渝中", "大渡口", "江北", "沙坪坝", "九龙坡", "南岸", "北碚", "万盛", "双桥", "渝北", "巴南", "万州", "涪陵", "黔江", "长寿"] },'
            + '{ "name": "四川", "cities": ["成都", "达州", "南充", "乐山", "绵阳", "德阳", "内江", "遂宁", "宜宾", "巴中", "自贡", "康定", "攀枝花"] },'
            + '{ "name": "贵州", "cities": ["贵阳", "遵义", "安顺", "黔西南", "都匀"] },'
            + '{ "name": "云南", "cities": ["昆明", "丽江", "昭通", "玉溪", "临沧", "文山", "红河", "楚雄", "大理"] },'
            + '{ "name": "西藏", "cities": ["拉萨", "林芝", "日喀则", "昌都"] },'
            + '{ "name": "陕西", "cities": ["西安", "咸阳", "延安", "汉中", "榆林", "商南", "略阳", "宜君", "麟游", "白河"] },'
            + '{ "name": "甘肃", "cities": ["兰州", "金昌", "天水", "武威", "张掖", "平凉", "酒泉"] },'
            + '{ "name": "青海", "cities": ["黄南", "海南", "西宁", "海东", "海西", "海北", "果洛", "玉树"] },'
            + '{ "name": "宁夏", "cities": ["银川", "吴忠"] },'
            + '{ "name": "新疆", "cities": ["乌鲁木齐", "哈密", "喀什", "巴音郭楞", "昌吉", "伊犁", "阿勒泰", "克拉玛依", "博尔塔拉"] },'
            + '{ "name": "香港", "cities": ["中西区", "湾仔区", "东区", "南区", "九龙-油尖旺区", "九龙-深水埗区", "九龙-九龙城区", "九龙-黄大仙区", "九龙-观塘区", "新界-北区", "新界-大埔区", "新界-沙田区", "新界-西贡区", "新界-荃湾区", "新界-屯门区", "新界-元朗区", "新界-葵青区", "新界-离岛区"] },'
            + '{ "name": "澳门", "cities": ["花地玛堂区", "圣安多尼堂区", "大堂区", "望德堂区", "风顺堂区", "嘉模堂区", "圣方济各堂区", "路氹城"]}]';

        var prov;

        $(document).ready(function(){
            //code
            prov = JSON.parse(citys);
            initForm();
            initProvince();
            bindIconClick();
            bindProvinceChange();
        });

        // 初始化日期下拉框数据
        function initForm() {
            var item = null,year = 1970;
            for(var i=48;i>=0;i--){
                item = $('<option value="'+(year+i)+'">'+(year+i)+'</option>');
                $('.brithYear').append(item);
            }
            for(var i=1;i<=12;i++){
                item = $('<option value="'+i+'">'+i+'</option>');
                $('.brithMonth').append(item);
            }
        }

        // 绑定省数据
        function initProvince() {
            var i,item;
            for(i=0;i<prov.length;i++){
                item = $('<option value="'+prov[i].name+'">'+prov[i].name+'</option>');
                $('.province').append(item);
            }
        }

        // 绑定省份选择框事件
        function bindProvinceChange() {
            $('.province').change(function(){
                $($('.city').children().get(0)).siblings().remove();
                var i,item,cities;
                for(i=0;i<prov.length;i++){
                    if(prov[i].name==$('.province').val()){
                        cities = prov[i].cities;
                        break;
                    }
                }
                for(i=0;i<cities.length;i++){
                    item = $('<option value="'+cities[i]+'">'+cities[i]+'</option>');
                    $('.city').append(item);
                }
            });
        }

        // 绑定伸缩面板事件
        function bindIconClick() {
            $('div.title').click(function(event){
                // $('span.scroIcon').click(function(event){
                var eventIcon=$(event.target);
                var contentPanel=eventIcon.parents('.panelTitle').next();
                contentPanel.toggle();
            });
        }
    </script>
    <%--图片--%>
    <script type="text/javascript">

        window.onload = function(){
            var input = document.getElementById("file_input");
            var result;
            var dataArr = []; // 储存所选图片的结果(文件名和base64数据)
            var fd;  //FormData方式发送请求
            var oSelect = document.getElementById("select");
            var oAdd = document.getElementById("add");
            var oSubmit = document.getElementById("submit");
            var oInput = document.getElementById("file_input");

            if(typeof FileReader==='undefined'){
                alert("抱歉，你的浏览器不支持 FileReader");
                input.setAttribute('disabled','disabled');
            }else{
                input.addEventListener('change',readFile,false);
            }　　　　　//handler


            function readFile(){
                fd = new FormData();
                var iLen = this.files.length;
                var index = 0;
                for(var i=0;i<iLen;i++){
                    if (!input['value'].match(/.jpg|.gif|.png|.jpeg|.bmp/i)){　　//判断上传文件格式
                        return alert("上传的图片格式不正确，请重新选择");
                    }
                    var reader = new FileReader();
                    reader.index = i;
                    fd.append(i,this.files[i]);
                    reader.readAsDataURL(this.files[i]);  //转成base64
                    reader.fileName = this.files[i].name;


                    reader.onload = function(e){
                        var imgMsg = {
                            name : this.fileName,//获取文件名
                            base64 : this.result   //reader.readAsDataURL方法执行完后，base64数据储存在reader.result里
                        };
                        dataArr.push(imgMsg);
                        result = '<div class="delete">删除</div><div class="result"><img src="'+this.result+'" alt=""/></div>';
                        var div = document.createElement('div');
                        div.innerHTML = result;
                        div['className'] = 'float';
                        div['index'] = index;
                        document.getElementsByTagName('body')[0].appendChild(div);  　　//插入dom树
                        var img = div.getElementsByTagName('img')[0];
                        img.onload = function(){
                            var nowHeight = ReSizePic(this); //设置图片大小
                            this.parentNode.style.display = 'block';
                            var oParent = this.parentNode;
                            if(nowHeight){
                                oParent.style.paddingTop = (oParent.offsetHeight - nowHeight)/2 + 'px';
                            }
                        };
                        div.onclick = function(){
                            this.remove();                  // 在页面中删除该图片元素
                            delete dataArr[this.index];  // 删除dataArr对应的数据
                        };
                        index++;
                    }
                }
            }

            function send(){
                var submitArr = [];
                for (var i = 0; i < dataArr.length; i++) {
                    if (dataArr[i]) {
                        submitArr.push(dataArr[i]);
                    }
                }
                // console.log('提交的数据：'+JSON.stringify(submitArr))
                $.ajax({
                    url : 'http://123.206.89.242:9999',
                    type : 'post',
                    data : JSON.stringify(submitArr),
                    dataType: 'json',
                    //processData: false,   用FormData传fd时需有这两项
                    //contentType: false,
                    success : function(data){
                        console.log('返回的数据：'+JSON.stringify(data))
                    }
                })
            }

            oSelect.onclick=function(){
                oInput.value = "";   // 先将oInput值清空，否则选择图片与上次相同时change事件不会触发
                //清空已选图片
                $('.float').remove();
                dataArr = [];
                index = 0;
                oInput.click();
            };

            oAdd.onclick=function(){
                oInput.value = "";   // 先将oInput值清空，否则选择图片与上次相同时change事件不会触发
                oInput.click();
            };

            oSubmit.onclick=function(){
                if(!dataArr.length){
                    return alert('请先选择文件');
                }
                send();
            }
        };
        /*
         用ajax发送fd参数时要告诉jQuery不要去处理发送的数据，
         不要去设置Content-Type请求头才可以发送成功，否则会报“Illegal invocation”的错误，
         也就是非法调用，所以要加上“processData: false,contentType: false,”
         * */
        function ReSizePic(ThisPic) {
            var RePicWidth = 200; //这里修改为您想显示的宽度值
            var TrueWidth = ThisPic.width; //图片实际宽度
            var TrueHeight = ThisPic.height; //图片实际高度
            if(TrueWidth>TrueHeight){
                //宽大于高
                var reWidth = RePicWidth;
                ThisPic.width = reWidth;
                //垂直居中
                var nowHeight = TrueHeight * (reWidth/TrueWidth);
                return nowHeight;  //将图片修改后的高度返回，供垂直居中用
            }else{
                //宽小于高
                var reHeight = RePicWidth;
                ThisPic.height = reHeight;
            }
        }
    </script>
    <%--layui监听--%>
    <script>
        //Demo
        layui.use('form', function(){
            var form = layui.form;
            //监听提交
            form.on('submit(formDemo)', function(data){
                layer.msg(JSON.stringify(data.field));
                return false;
            });
        });
    </script>
</head>
<body>
<%--<div class="page">--%>
<div class="top">
    <ul>
        <li class="topContact">动态简历</li>
    </ul>
</div>
<form class="layui-form" action="">
    <div class="panel">
        <div class="panelTitle">
            <div class="colorBlock"></div>
            <div class="title">
                <span>个人信息</span>
            </div>
        </div>
        <div class="panelContent">
            <div class="layui-form-item">
                <label class="layui-form-label">姓名</label>
                <div class="layui-input-block">
                    <input type="text" name="title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label class="layui-form-label">出生年月</label>
                    <div class="layui-inline" style="width: 150px;">
                        <select class="brithYear">
                            <option value="">--请选择--</option>
                        </select>
                    </div>
                    &nbsp;年&nbsp;
                    <div class="layui-inline" style="width: 150px;">
                        <select class="brithMonth" >
                            <option value="">--请选择--</option>
                        </select>
                    </div>
                    &nbsp;月&nbsp;
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">户籍所在地</label>
                <div class="layui-inline" style="width: 150px;">
                    <select class="province">
                        <option value="">--请选择--</option>
                    </select>
                </div>
                <div class="layui-inline" style="width: 150px;">
                    <select class="city">
                        <option value="">--请选择--</option>
                    </select>
                </div>
            </div>
        </div>
    </div>

    <div class="panel">
        <div class="panelTitle">
            <div class="colorBlock"></div>
            <div class="title">
                <span>联系方式</span>
            </div>
        </div>
        <div class="panelContent">
            <div class="layui-form-item">
                <label class="layui-form-label">手机号码</label>
                <div class="layui-input-block">
                    <input type="text" name="title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">电子邮箱</label>
                <div class="layui-input-block">
                    <input type="text" name="title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">现居地址</label>
                <div class="layui-input-block">
                    <input type="text" name="title" required  lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
    </div>

    <div class="panel">
        <div class="panelTitle">
            <div class="colorBlock"></div>
            <div class="title">
                <span>求职意向</span>
            </div>
        </div>
        <div class="panelContent">
            <div class="layui-form-item">
                <label class="layui-form-label">求职状态</label>
                <div class="layui-input-block">
                    <select>
                        <option value="">--请选择--</option>
                        <option value="1">工作中</option>
                        <option value="2">正在找工作</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">目标薪资</label>
                <div class="layui-inline" style="width: 150px;">
                    <select>
                        <option value="">--请选择--</option>
                        <option value="1">月薪</option>
                    </select>
                </div>
                <div class="layui-inline" style="width: 150px;">
                    <select>
                        <option value="">--请选择--</option>
                        <option value="1">3K~4K</option>
                        <option value="2">5K~6K</option>
                        <option value="2">5K~6K</option>
                        <option value="2">7K~8K</option>
                        <option value="2">9K~10K</option>
                        <option value="2">11K~15K</option>
                        <option value="2">16K~20K</option>
                        <option value="2">20K以上</option>
                    </select>
                </div>
            </div>
        </div>
    </div>

    <div class="panel">
        <div class="panelTitle">
            <div class="colorBlock"></div>
            <div class="title">
                <span>证书照片</span>
            </div>
        </div>
        <div class="panelContent">
            <div class="layui-form-item">
                <label class="layui-form-label">选择图片：</label>
                <button id="select">(重新)选择图片</button>
                <button id="add">(追加)图片</button>
                <input type="file" id="file_input" multiple/>
                <!--用input标签并选择type=file，记得带上multiple，不然就只能单选图片了-->
                <button id="submit">提交</button>
            </div>
        </div>
    </div>
<div class="footer">
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
    <%--<ul>--%>
        <%--<li class="topContact">安徽信息工程学院 2016级 JAVA①班 安天宝 动态简历</li>--%>
    <%--</ul>--%>
</div>
</form>
<%--</div>--%>
</body>
</html>
