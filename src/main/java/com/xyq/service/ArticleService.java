package com.xyq.service;

import com.xyq.dao.ArticleDao;
import com.xyq.dao.CollectionDao;
import com.xyq.pojo.Article;
import com.xyq.pojo.Collection;
import com.xyq.pojo.PageBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cwtt
 */
public class ArticleService {
    private ArticleDao ad = new ArticleDao();
    private CollectionDao cd = new CollectionDao();

    /**
     * 分页查询service
     * @param currentPage
     * @param pageSize
     * @return
     * @throws Exception
     */
    public PageBean<Article> selectByPage(int currentPage,int pageSize) throws Exception {
        //1.计算开始索引和得到查询条数
        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;
        //2.调用dao查询当前页数据
        List<Article> rows = ad.selectByPage(begin,size);
        //3.查询总记录数
        int totalCount = ad.selectTotalCount();
        //4.封装PageBean对象
        PageBean<Article> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        //5.返回PageBean对象
        return pageBean;
    }

    /**
     * 分页条件查询service
     * @param currentPage
     * @param pageSize
     * @param article
     * @return
     * @throws Exception
     */
    public PageBean<Article> selectByPageAndCondition(int currentPage,int pageSize,Article article) throws Exception {
        //1.计算开始索引和得到查询条数
        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;
        //2.处理article数据，变成模糊查询
        if(article.getTitle() != null && article.getTitle().length() > 0){
            article.setTitle("%"+ article.getTitle() +"%");
        }
        if(article.getContent() != null && article.getContent().length() > 0){
            article.setContent("%"+ article.getContent() +"%");
        }
        if(article.getNickname() != null && article.getNickname().length() > 0){
            article.setNickname("%"+ article.getNickname() +"%");
        }
        if(article.getLabel() != null && article.getLabel().length() > 0){
            article.setLabel("%"+ article.getLabel() +"%");
        }
        //3.调用dao查询当前页数据
        List<Article> rows = ad.selectByPageAndCondition(begin,size,article);
        //4.查询总记录数
        int totalCount = ad.selectTotalCountByCondition(article);
        //5.封装PageBean对象
        PageBean<Article> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        //6.返回PageBean对象
        return pageBean;
    }

    public PageBean<Article> selectByPageAndIds(int currentPage,int pageSize,int uid) throws Exception {
        //1.计算开始索引和得到查询条数
        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;
        //2.首先调用collectionDao把文章的id获取
        int[] ids = cd.selectById(uid);
        //3.再调用articleDao把文章获取
        List<Article> articles = ad.selectByIds(ids);
        List<Article> rows = new ArrayList<>();
        //4.查询总记录数
        int totalCount = cd.selectTotalCountById(uid);
        //5.得到当前页数据
        for(int i=begin;i<totalCount;i++){
            rows.add(articles.get(i));
        }
        //6.封装PageBean对象
        PageBean<Article> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);
        //7.返回PageBean对象
        return pageBean;
    }
}
