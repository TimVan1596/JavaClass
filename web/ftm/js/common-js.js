/**
 * 保存每个页面都会用到的js方法
 * @date 2018年10月8日17:41:44
 * @author TimVan
 * */

/**
 * 判断输入字符串是否为空或者全部都是空格
 **/
function isNull(str) {
    if (str === "") return true;
    else if ("undefined" === typeof(str)){
        return true;
    }
    var regu = "^[ ]+$";
    var re = new RegExp(regu);
    return re.test(str);
}


/**
 * js获取GET参数(返回 $_GET 对象, 仿PHP模式)
 * 使用时, 可以直接 $_GET['get参数'], 就直接获得GET参数的值
 **/
var $_GET = (function(){
    var url = window.document.location.href.toString();
    var u = url.split("?");
    if(typeof(u[1]) === "string"){
        u = u[1].split("&");
        var get = {};
        for(var i in u){
            var j = u[i].split("=");
            get[j[0]] = j[1];
        }
        return get;
    } else {
        return {};
    }
})();

/**
 * 判断JS邮箱格式验证-正则验证
 **/
function checkEmail(email){
    var reg = new RegExp("^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$"); //正则表达式
    //正则验证不通过，格式不对
     if(!reg.test(email)){
        return false;
    }else{
        return true;
    }
}

