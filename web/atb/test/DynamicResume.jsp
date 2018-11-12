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
        div.page {
            width: 800px;
            height: 842px;
            margin: 50px auto;
            font-family:SimHei;
        }

        div.top, div.footer {
            background-color: #2BA4DD;
        }

        div.top {
            height: 35px;
            line-height: 35px;
            text-align: center;
            color: #fff;
            font-size: 18px;
        }

        div.footer {
            height: 15px;
            margin-top: 30px;
        }

        li {
            list-style-type: none;
        }

        li.topContact {
            display: inline-block;
            padding: 0px 70px;
        }

        div.header {
            height: 130px;
            padding: 30px 0px;
            text-align: center;
        }

        div.name {
            height: 100px;
            line-height: 100px;
            font-size: 50px;
        }

        div.job {
            height: 30px;
            line-height: 30px;
            font-size: 20px;
        }

        div.content {

        }

        /* Panel 是简历信息具体的内容面板，即可伸缩面板 */
        .panel {
            margin-top: 20px;
            padding: 0px 0px 20px 0px;
        }

        .panelTitle {
            height: 35px;
            width: 100%;
            border-bottom: 2px solid #2BA4DD;
        }

        div.colorBlock, div.title {
            float: left;
        }

        div.scrollBtn {
            float: right;
        }

        /* 伸缩面板的伸缩按钮 */
        span.scroIcon {
            height:20px; width:40px; display:block; position:relative;
            cursor: pointer;
        }

        .scroIcon:before{
            content:''; height:0; width:0; display:block; border:15px transparent double;
            border-bottom-width:0; border-top-color:#2BA4DD; position:absolute;
            top:0px; left:0px; }

        .colorBlock {
            height: 100%;
            width: 25px;
            margin-right: 15px;
            background-color: #2BA4DD;
        }

        .title {
            width: 400px;
            height: 100%;
            line-height: 35px;
            font-size: 24px;
        }

        /* 简历项 */
        li.resumeItem {
            padding: 5px 0px;
        }

        li.resumeItem span, input {
            display: inline-block;
        }

        li.resumeItem span {
            width: 150px;
        }

        li.resumeItem select {
            width: 100px;
        }

        .clear {
            clear:both;
        }

    </style>
    <script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
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
            $('span.scroIcon').click(function(event){
                var eventIcon=$(event.target);
                var contentPanel=eventIcon.parents('.panelTitle').next();
                contentPanel.toggle();
            });
        }
    </script>
</head>
<body>
<div class="page">
    <div class="top">
        <ul>
            <li class="topContact">CELL: 178 5692 5180</li>
            <li class="topContact">EMAIL: 32336077@qq.com</li>
        </ul>
    </div>
    <div class="header">
        <div class="name">
            <span>动&nbsp;态&nbsp;简&nbsp;历</span>
        </div>
        <div class="job">
            <span>Dynamic Resume</span>
        </div>
    </div>
    <div class="content">
        <div class="panel">
            <div class="panelTitle">
                <div class="colorBlock"></div>
                <div class="title">
                    <span>个人信息</span>
                </div>
                <div class="scrollBtn">
                    <span class="scroIcon"></span>
                </div>
                <div class="clear"></div>
            </div>
            <div class="panelContent">
                <ul>
                    <li class="resumeItem">
                        <span>姓名</span>
                        <input type="text" />
                    </li>
                    <li class="resumeItem">
                        <span>性别</span>
                        <select>
                            <option value="">--请选择--</option>
                            <option value="男">男</option>
                            <option value="女">女</option>
                        </select>
                    </li>
                    <li class="resumeItem">
                        <span>出生年月</span>
                        <select class="brithYear">
                            <option value="">--请选择--</option>
                        </select>&nbsp;年&nbsp;
                        <select class="brithMonth">
                            <option value="">--请选择--</option>
                        </select>&nbsp;月&nbsp;
                    </li>
                    <li class="resumeItem">
                        <span>户籍所在地</span>
                        <select class="province">
                            <option value="">--请选择--</option>
                        </select>
                        <select class="city">
                            <option value="">--请选择--</option>
                        </select>
                    </li>
                </ul>
            </div>
        </div>

        <div class="panel">
            <div class="panelTitle">
                <div class="colorBlock"></div>
                <div class="title">
                    <span>联系方式</span>
                </div>
                <div class="scrollBtn">
                    <span class="scroIcon"></span>
                </div>
                <div class="clear"></div>
            </div>
            <div class="panelContent">
                <ul>
                    <li class="resumeItem"><span>手机号码</span><input type="text" /></li>
                    <li class="resumeItem"><span>电子邮箱</span><input type="text" /></li>
                    <li class="resumeItem"><span>现居地址</span><input type="text" /></li>
                </ul>
            </div>
        </div>

        <div class="panel">
            <div class="panelTitle">
                <div class="colorBlock"></div>
                <div class="title">
                    <span>求职意向</span>
                </div>
                <div class="scrollBtn">
                    <span class="scroIcon"></span>
                </div>
                <div class="clear"></div>
            </div>
            <div class="panelContent">
                <ul>
                    <li class="resumeItem">
                        <span>求职状态</span><select>
                        <option value="">--请选择--</option>
                        <option value="1">工作中</option>
                        <option value="2">正在找工作</option>
                    </select>
                    </li>
                    <li class="resumeItem">
                        <span>目标薪资</span><select>
                        <option value="">--请选择--</option>
                        <option value="1">月薪</option>
                    </select>
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
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <div class="footer">
        <h6 align='center' style="color: white">安&nbsp;天&nbsp;宝</h6>
    </div>
</div>
</body>
</html>
