layui.use(['table','carousel'], function(){
    let carousel = layui.carousel;
    //图片轮播
    carousel.render({
        elem: '#atb-shuffling'
        ,width: 1000 //设置容器宽度
        ,height: 200
        ,anim: 'fade' //切换动画方式
    });
});