package com.xyq.web.filter;

import com.xyq.pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author cwtt
 */
@WebFilter("/adminSpace.html")
public class JudgeFilter2 implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        //获取session中是user数据
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");

        //是管理员才能登录
        if(user.getStatus() == 1){
            chain.doFilter(req, resp);
        } else {
            request.getRequestDispatcher("/login.html").forward(request,resp);
        }

    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
