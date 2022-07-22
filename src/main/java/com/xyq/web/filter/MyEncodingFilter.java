package com.xyq.web.filter;

import com.xyq.pojo.MyRequest;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author cwtt
 */
@WebFilter("/*")
public class MyEncodingFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request =(HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;

        //1,设置POST请求中文乱码的问题
        request.setCharacterEncoding("utf-8");
        //2.同理用response设置响应
        response.setContentType("text/html;charset=utf-8");

        //3.获取请求类型  如果是post 进行设置  如果是get 则无需设置 只有post有中文乱码  get无
        String method = request.getMethod();

        //4.判断是否是post equalsIgnoreCase不区分大小写
        if ("post".equalsIgnoreCase(method)){
            //如果是post请求 则设置编码类型
            request.setCharacterEncoding("utf-8");
            //设置完 放行
            chain.doFilter(request, response);
        } else {
            //如果不是  就是get  也放行
            chain.doFilter(request, response);
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
