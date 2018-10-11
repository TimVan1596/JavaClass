package web.ftm.java;

import java.io.PrintWriter;

/**
 * 一些servlet共有的方法或属性
 *
 * @author TimVan
 * @date 2018年10月10日08:46:23
 */
public class CommonUtil {

    /**
     * 弹出提示框
     **/
    static public void popAlert(PrintWriter out, String ctx) {
        popAlert(out, ctx, null);
    }

    /**
     * 弹出提示框，点击确认后跳转其他页面
     **/
    static public void popAlert(PrintWriter out, String ctx, String href) {
        StringBuilder sb =
                new StringBuilder("<script>");
        //弹出提示框
        sb.append(" alert('" + ctx + "'); ");
        //判断是否有网址跳转
        if (href != null) {
            sb.append("window.location.href='"
                    + href + "'");
        }
        sb.append("</script>");
        out.print(sb.toString());
    }

}
