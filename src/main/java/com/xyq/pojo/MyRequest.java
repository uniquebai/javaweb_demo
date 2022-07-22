package com.xyq.pojo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * @author cwtt
 */
public class MyRequest extends HttpServletRequestWrapper {

    private HttpServletRequest request;

    //是否已经是utf-8编码的标志
    private boolean isEncoding = false;
    public MyRequest(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    @Override
    public String getParameter(String name) {
        return getParameterMap().get(name)[0];
    }

    @Override
    public Map<String, String[]> getParameterMap() {

        Map<String, String[]> map = request.getParameterMap();
        if(isEncoding == true) {
            return map;
        }
        //遍历value，改成utf-8编码
        for(Map.Entry<String,String[]> entry : map.entrySet()) {
            //取数组值
            String[] values = entry.getValue();
            for(int i=0;i<values.length;i++) {
                try {
                    values[i] = new String(values[i].getBytes("ISO-8859-1"),"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        isEncoding = true;

        return map;
    }
}
