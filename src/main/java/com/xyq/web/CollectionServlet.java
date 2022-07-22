package com.xyq.web;

import com.xyq.dao.CollectionDao;
import com.xyq.pojo.Collection;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author cwtt
 */
@WebServlet("/collection/*")
public class CollectionServlet extends BaseServlet{
    private CollectionDao cd = new CollectionDao();

    public void addC(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1.接收数据
        int uid = Integer.parseInt(req.getParameter("uid"));
        int aid = Integer.parseInt(req.getParameter("aid"));
        Collection collection = new Collection();
        collection.setUserId(uid);
        collection.setArticleId(aid);
        //2.调用dao添加收藏记录
        cd.add(collection);
        //3.响应成功标识
        resp.getWriter().write("success");
    }
}
