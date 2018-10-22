package com.timvanx.biggerdvd.util.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.UnsupportedEncodingException;
import java.util.Map;

class MyRequest extends HttpServletRequestWrapper {

    /**
     * flag = 标记是否getParameterMap方法还未被调用过
     * （如果在同个servlet中调用了2次getParameter等方法2次
     * ，没有用flag做标记的话，会对参数进行2次编码
     * ，结果第2次得到的参数会是乱码）
     * */
    private HttpServletRequest req;
    private boolean flag = true;

    public MyRequest(HttpServletRequest request) {
        super(request);
        req = request;
    }

    @Override
    public String getParameter(String name) {
        return getParameterMap().get(name)[0];
    }

    @Override
    public String[] getParameterValues(String name) {
        return getParameterMap().get(name);
    }

    @Override
    public Map<String, String[]> getParameterMap() {

        Map<String, String[]> map = req.getParameterMap();

        if (flag) {
            for (Map.Entry<String, String[]> entry : map.entrySet()) {
                String[] value = entry.getValue();
                for (int i = 0; i < value.length; i++) {
                    try {
                        value[i] = new String(value[i].getBytes("iso-8859-1"),
                                "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
            flag = false;
        }

        return map;
    }

}
