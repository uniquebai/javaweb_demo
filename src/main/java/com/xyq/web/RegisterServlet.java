package com.xyq.web;

import com.xyq.pojo.User;
import com.xyq.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author cwtt
 */
@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    private UserService service = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //获取用户输入的验证码
        String checkCode = request.getParameter("checkCode");
        //程序生成的验证码从session中获取
        HttpSession session = request.getSession();
        String checkCodeGen = (String) session.getAttribute("checkCodeGen");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        //验证码比对
        if(!checkCodeGen.equalsIgnoreCase(checkCode)){
            //若错误，则提示验证码错误
            request.setAttribute("register_msg","验证码错误");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
            return;
        }

        //2.调用service注册
        boolean flag = false;
        try {
            flag = service.register(user.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //3.判断注册成功与否
        if(flag){
            //注册功能，跳转登录页面
            request.setAttribute("register_msg","注册成功，请登录");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        } else {
            //注册失败，跳转到注册页面
            request.setAttribute("register_msg","用户名已存在");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
