layui.use(['table','carousel'], function(){
    let carousel = layui.carousel;
    //图片轮播
    carousel.render({
        elem: '#atb-shuffling'
        ,width: '100%' //设置容器宽度
        ,height: 250
        // ,height: '22%'
        ,anim: 'fade' //切换动画方式
    });
});