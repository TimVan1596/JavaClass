/**
 * 保存每个页面都会用到的js方法
 * @date 2018年10月8日17:41:44
 * @author TimVan
 * */



/**
 * 判断输入字符串是否为空或者全部都是空格
 **/
function isNull(str) {
    if (str == "") return true;
    var regu = "^[ ]+$";
    var re = new RegExp(regu);
    return re.test(str);
}
