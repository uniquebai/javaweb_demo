package com.xyq.web;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSON;
import com.xyq.dao.ArticleDao;
import com.xyq.dao.CollectionDao;
import com.xyq.pojo.Article;
import com.xyq.pojo.PageBean;
import com.xyq.pojo.User;
import com.xyq.service.ArticleService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cwtt
 */
@WebServlet("/article/*")
public class ArticleServlet extends BaseServlet{
    private ArticleService service = new ArticleService();
    private ArticleDao articleDao = new ArticleDao();
    private CollectionDao cd = new CollectionDao();

    /**
     * 查询所有文章数据
     * @param req
     * @param resp
     * @throws Exception
     */
    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        //1.调用jdbc查询数据，得到list集合
        List<Article> articles = articleDao.selectAll();
        //2.转为JSON数据
        String jsonString = JSON.toJSONString(articles);
        //3.写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }


    /**
     * 添加文章的方法
     * @param req
     * @param resp
     * @throws Exception
     */
    public void addA(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("text/text;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        //1.接收数据
        //获取请求体数据
        BufferedReader br = req.getReader();
        String params = br.readLine();
        //将JSON字符串转为Java对象
        Article article = JSON.parseObject(params, Article.class);
        //获取session中的user信息
        User user = (User)req.getSession().getAttribute("user");
        article.setNickname(user.getNickname());
        //2.调用Dao 添加文章
        articleDao.add(article);
        //3.响应成功标识
        resp.getWriter().write("success");
    }

    /**
     * 根据id删除文章
     * @param req
     * @param resp
     * @throws Exception
     */
    public void deleteA(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("text/text;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        //1.接收数据
        //获取请求体数据
        BufferedReader br = req.getReader();
        String params = br.readLine();
        //将JSON字符串转为Java对象
        Article article = JSON.parseObject(params, Article.class);
        //2.调用Dao 删除文章
        articleDao.deleteById(article.getId());
        //3.响应成功标识
        resp.getWriter().write("success");
    }

    /**
     * 根据id修改文章
     * @param req
     * @param resp
     * @throws Exception
     */
    public void updateA(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setContentType("text/text;charset=utf-8");
        resp.setCharacterEncoding("UTF-8");
        //1.接收数据
        //获取请求体数据
        BufferedReader br = req.getReader();
        String params = br.readLine();
        //将JSON字符串转为Java对象
        Article article = JSON.parseObject(params, Article.class);
        //2.调用Dao 删除文章
        articleDao.updateById(article);
        //3.响应成功标识
        resp.getWriter().write("success");
    }

    /**
     * 分页查询servlet
     * @param req
     * @param resp
     * @throws Exception
     */
    public void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        //1.获取 当前页码 和 每页显示条数
        int currentPage = Integer.parseInt(req.getParameter("currentPage"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        //2.调用service查询
        PageBean<Article> pageBean = service.selectByPage(currentPage, pageSize);
        //3.转为JSON数据
        String jsonString = JSON.toJSONString(pageBean);
        //4.写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 分页条件查询
     * @param req
     * @param resp
     * @throws Exception
     */
    public void selectByPageAndCondition(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        //1.获取 当前页码 和 每页显示条数
        int currentPage = Integer.parseInt(req.getParameter("currentPage"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        //2.获取请求体数据
        BufferedReader br = req.getReader();
        String params = br.readLine();
        Article article = JSON.parseObject(params,Article.class);
        //3.调用service查询
        PageBean<Article> pageBean = service.selectByPageAndCondition(currentPage, pageSize,article);
        //4.转为JSON数据
        String jsonString = JSON.toJSONString(pageBean);
        //5.写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    public void selectByPageAndId(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        //1.获取 当前页码 和 每页显示条数
        int currentPage = Integer.parseInt(req.getParameter("currentPage"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        int uid = Integer.parseInt(req.getParameter("uid"));
        //2.调用service查询
        PageBean<Article> pageBean = service.selectByPageAndIds(currentPage, pageSize, uid);
        //4.转为JSON数据
        String jsonString = JSON.toJSONString(pageBean);
        //5.写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }
    /**
     * 分页且根据昵称查询
     * @param req
     * @param resp
     * @throws Exception
     */
    public void selectByPageAndNickname(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        //1.获取 当前页码 和 每页显示条数
        int currentPage = Integer.parseInt(req.getParameter("currentPage"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize"));
        String nickname = req.getParameter("nickname");
        //2.获取请求体数据
        Article article = new Article();
        article.setNickname(nickname);
        //3.调用service查询
        PageBean<Article> pageBean = service.selectByPageAndCondition(currentPage, pageSize,article);
        //4.转为JSON数据
        String jsonString = JSON.toJSONString(pageBean);
        //5.写数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    /**
     * 下载文章到xls文件
     * @param req
     * @param resp
     * @throws Exception
     */
    public void downloadA(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        //1.获取请求体数据
        BufferedReader br = req.getReader();
        String params = br.readLine();
        //转成int[]
        int[] ids = JSON.parseObject(params, int[].class);
        //2.调用dao查询
        List<Article> articles = articleDao.selectByIds(ids);
        //3.将集合数据写入excel表格
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("文章数据列表", "文章数据"), Article.class, articles);
        FileOutputStream outputStream = new FileOutputStream("D://a.xls");
        workbook.write(outputStream);
        outputStream.close();
        workbook.close();
        //4.响应成功的标记
        resp.getWriter().write("success");
    }

    /**
     * 将文件上传到数据库
     * @param req
     * @param resp
     * @throws Exception
     */
    public void uploadA(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        //1.获取文件名
        String filename = req.getParameter("fileName");
        //2.excel导入
        ImportParams params = new ImportParams();
        //设置标题占的行数
        params.setTitleRows(1);
        //设置header占几行
        params.setHeadRows(1);
        List<Article> articles = ExcelImportUtil.importExcel(new FileInputStream("D://"+filename), Article.class, params);
        //3.将excel文件添加到数据库
        for(int i=0;i<articles.size();i++){
            articleDao.add(articles.get(i));
        }
        //4.响应成功标识
        resp.getWriter().write("success");
    }
}
