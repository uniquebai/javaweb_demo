package com.xyq.web;

import com.xyq.dao.FansDao;
import com.xyq.pojo.Fans;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author cwtt
 */
@WebServlet("/fans/*")
public class FansServlet extends BaseServlet{
    private FansDao fansDao = new FansDao();

    public void addF(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        //1.接收数据
        int uid = Integer.parseInt(req.getParameter("uid"));
        int fid = Integer.parseInt(req.getParameter("fid"));
        Fans fans = new Fans();
        fans.setuId(uid);
        fans.setfId(fid);
        //2.调用Dao添加关注记录
        fansDao.add(fans);
        //3.响应成功标识
        resp.getWriter().write("success");
    }
}
