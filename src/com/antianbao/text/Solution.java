package com.antianbao.text;

public class Solution {
    public static void main(String[] args){
        String str = "We Are Happy";
        StringBuffer s = new StringBuffer(str);
        Solution cs = new Solution();
        String string = cs.replaceSpace(s);
        System.out.println(string);
    }
    /**
     * StringBuffer类中的方法主要偏重于对于字符串的变化，
     * 例如追加、插入和删除等，这个也是StringBuffer和String类的主要区别。
     * */
    public String replaceSpace(StringBuffer str){
        //indexOf() 如果要检索的字符串值没有出现，则该方法返回 -1
        int index = str.indexOf(" ");
        while(index != -1){
            str.replace(index,index+1,"%20");
            index = str.indexOf(" ",index);
        }
        String string = new String(str);
        return string;
    }
}
