package com.xyq.web;

import com.alibaba.fastjson.JSON;
import com.xyq.dao.UserDao;
import com.xyq.pojo.Article;
import com.xyq.pojo.User;
import com.xyq.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cwtt
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet{
    private UserService service = new UserService();
    private UserDao userDao = new UserDao();

    /**
     * 根据用户名判断是否已存在该用户
     * @param req
     * @param resp
     * @throws Exception
     */
    public void selectByName(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("text/text;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        //1.获取用户名
        String username = req.getParameter("username");
        //2.调用service查询User对象
        boolean flag = service.register(username);
        //3.响应标记
        resp.getWriter().write("" + flag);
    }

    /**
     * 判断昵称是否已存在
     * @param req
     * @param resp
     * @throws Exception
     */
    public void judgeNickname(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.接收数据
        String nickname = req.getParameter("nickname");
        //2.调用dao查询用户
        User user = userDao.selectByNickname(nickname);
        boolean flag;
        if(user.getId() != null){
            //用户名存在，注册失败
            flag = true;
        } else {
            flag = false;
        }
        //3.响应标记
        resp.getWriter().write("" + flag);
    }

    /**
     * 添加用户
     * @param req
     * @param resp
     * @throws Exception
     */
    public void addF(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("text/text;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        //1.接收数据
        //获取请求体数据
        BufferedReader br = req.getReader();
        String params = br.readLine();
        //将JSON字符串转为Java对象
        User user = JSON.parseObject(params, User.class);
        //2.调用Dao 添加用户
        userDao.add(user);
        //3.响应成功标识
        resp.getWriter().write("success");
    }

    /**
     * 根据id删除用户
     * @param req
     * @param resp
     * @throws Exception
     */
    public void deleteU(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.接收数据
        //获取请求体数据
        int uid = Integer.parseInt(req.getParameter("uid"));
        //2.调用Dao 添加用户
        userDao.deleteById(uid);
        //3.响应成功标识
        resp.getWriter().write("success");
    }

    /**
     * 修改用户个人信息
     * @param req
     * @param resp
     * @throws Exception
     */
    public void updateU(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("text/text;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        //1.接收数据
        //获取请求体数据
        BufferedReader br = req.getReader();
        String params = br.readLine();
        //将JSON字符串转为Java对象
        User user = JSON.parseObject(params, User.class);
        //2.调用Dao 删除文章
        userDao.updateById(user);
        //3.响应成功标识
        resp.getWriter().write("success");
    }

    /**
     * 根据用户名和密码判断是否登陆成功
     * @param req
     * @param resp
     * @throws Exception
     */
    public void selectByNameAndPass(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("text/text;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        //1.接收数据
        //获取请求体数据
        BufferedReader br = req.getReader();
        String params = br.readLine();
        //将JSON字符串转为Java对象
        User user = JSON.parseObject(params, User.class);
        //2.调用Dao 查询用户名和密码是否正确
        User u = userDao.selectByNameAndPassword(user.getUsername(), user.getPassword());
        //3.响应成功标识
        if(u.getId()!=null){
            //如果是管理员，则写"success"
            if(u.getStatus()==1){
                resp.getWriter().write("success");
            }
            //如果是普通用户，则写"succeed"
            else {
                resp.getWriter().write("succeed");
            }

            //将登录成功后的user对象，存储到session中
            HttpSession session = req.getSession();
            session.setAttribute("user",u);
//            String contextPath = req.getContextPath();
//            resp.sendRedirect(contextPath+"/user");
        }
    }

    /**
     * 获取session中user的数据
     * @param req
     * @param resp
     * @throws Exception
     */
    public void getUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.获取session中的user数据
        User user = (User)req.getSession().getAttribute("user");
        //2.将数据转为JSON数据
        //3.转为JSON数据
        String jsonString = JSON.toJSONString(user);
        //4.写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 查询所有用户数据
     * @param req
     * @param resp
     * @throws Exception
     */
    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.调用Dao 查询所有用户
        List<User> users = userDao.selectAll();
        //2.转为JSON数据
        String jsonString = JSON.toJSONString(users);
        //3.写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 根据昵称查询用户
     * @param req
     * @param resp
     * @throws Exception
     */
    public void selectByNickname(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.接收数据
        String nickname = req.getParameter("nickname");
        //2.调用dao查询用户
        List<User> users = new ArrayList<>();
        User user = userDao.selectByNickname(nickname);
        users.add(user);
        //3.转为JSON数据
        String jsonString = JSON.toJSONString(users);
        //4.写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 退出登录
     * @param req
     * @param resp
     * @throws Exception
     */
    public void exit(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.销毁session
        req.getSession().invalidate();
        //2.给出响应
        resp.getWriter().write("success");
    }
}
