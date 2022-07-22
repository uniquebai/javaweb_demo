package com.xyq.web.filter;

import com.xyq.pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author cwtt
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;

        //判断资源是否与登录注册相关
        String[] urls = {"/login.html","/register.html","/imgs/","/css/","/js/","/user/","/article/","/collection/","/fans/","/element-ui/lib/",};
        //获取当前访问路径
        String url = request.getRequestURL().toString();

        //循环判断
        for(String u : urls){
            if (url.contains(u)){
                //找到了放行
                chain.doFilter(req, resp);
                return;
                //这里用return是因为，他去访问完资源后，还会回来继续执行放行后的代码，但是已经不需要执行了，所以就执行return；结束方法，直接响应回去页面
            }
        }

        //1.判断session中是否有user
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        //2.判断user是否为null
        if(user != null){
            //登陆过了，放行
            chain.doFilter(req, resp);
        } else {
            //没有登陆过，跳转到登陆页面
            request.getRequestDispatcher("/login.html").forward(request,resp);
        }

    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
