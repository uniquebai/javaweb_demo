package com.xyq.dao;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.xyq.pojo.Article;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author cwtt
 */
public class ArticleDao {
    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    public List<Article> selectAll() throws Exception {
        //1.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("D:/java/LastDemo/src/druid.properties"));
        //2.获取连接对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //3.获取数据库连接
        Connection conn = dataSource.getConnection();
        //4.定义sql
        String sql = "select * from tb_article";
        //5.获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //6.执行pstmt
        ResultSet rs = pstmt.executeQuery();
        //7.处理结果 List<Article> 封装Brand对象,获取List集合
        List<Article> articles = new ArrayList<>();
        Article article = null;
        while (rs.next()){
            article = new Article();
            article.setId(rs.getInt("id"));
            article.setTitle(rs.getString("title"));
            article.setContent(rs.getString("content"));
            article.setNickname(rs.getString("nickname"));
            article.setLabel(rs.getString("label"));
            //装载集合
            articles.add(article);
        }
        //8.释放资源
        rs.close();
        pstmt.close();
        conn.close();
        return articles;
    }

    /**
     * 新增文章数据
     * @param article
     * @throws Exception
     */
    public void add(Article article) throws Exception {
        //1.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("D:/java/LastDemo/src/druid.properties"));
        //2.获取连接对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //3.获取数据库连接
        Connection connection = dataSource.getConnection();
        //4.定义sql
        String sql = "insert into tb_article(title,content,nickname,label) VALUES (?,?,?,?)";
        //5.获取pstmt对象
        PreparedStatement pstmt = connection.prepareStatement(sql);
        //6.设置参数
        pstmt.setString(1,article.getTitle());
        pstmt.setString(2,article.getContent());
        pstmt.setString(3,article.getNickname());
        pstmt.setString(4,article.getLabel());
        //7.执行pstmt
        int i = pstmt.executeUpdate();
        //8.释放资源
        pstmt.close();
        connection.close();
    }

    /**
     * 根据id删除文章
     * @param id
     * @throws Exception
     */
    public void deleteById(int id) throws Exception {
        //1.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("D:/java/LastDemo/src/druid.properties"));
        //2.获取连接对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //3.获取数据库连接
        Connection connection = dataSource.getConnection();
        //4.定义sql
        String sql = "DELETE from tb_article WHERE id = ?";
        //5.获取pstmt对象
        PreparedStatement pstmt = connection.prepareStatement(sql);
        //6.设置参数
        pstmt.setInt(1,id);
        //7.执行pstmt
        int i = pstmt.executeUpdate();
        //8 删除数据后，把id删掉后再重新排序，避免id断层
        //8.1定义sql语句
        String sql1 = "ALTER TABLE `tb_article` DROP `id`;";
        String sql2 = "ALTER TABLE `tb_article`  ADD `id` int( 11 ) NOT NULL FIRST;";
        String sql3 = "ALTER TABLE `tb_article`  MODIFY COLUMN `id` int(11) NOT NULL AUTO_INCREMENT,ADD PRIMARY KEY(id);";
        //8.2执行sql语句
        pstmt.executeUpdate(sql1);
        pstmt.executeUpdate(sql2);
        pstmt.executeUpdate(sql3);
        //8.释放资源
        pstmt.close();
        connection.close();
    }

    /**
     * 根据id修改文章
     * @param article
     * @throws Exception
     */
    public void updateById(Article article) throws Exception {
        //1.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("D:/java/LastDemo/src/druid.properties"));
        //2.获取连接对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //3.获取数据库连接
        Connection conn = dataSource.getConnection();
        //4.定义sql
        String sql = "update tb_article\n" +
                "            set title = ?,\n" +
                "            content = ?,\n" +
                "            nickname = ?,\n" +
                "            label = ?\n" +
                "        where id = ?;";
        //5.获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //6.设置参数
        pstmt.setString(1,article.getTitle());
        pstmt.setString(2,article.getContent());
        pstmt.setString(3,article.getNickname());
        pstmt.setString(4,article.getLabel());
        pstmt.setInt(5,article.getId());
        //7.执行pstmt
        int i = pstmt.executeUpdate();
        //8.释放资源
        pstmt.close();
        conn.close();
    }

    /**
     * 分页查询
     * @param begin
     * @param size
     * @return
     * @throws Exception
     */
    public List<Article> selectByPage(int begin,int size) throws Exception {
        //1.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("D:/java/LastDemo/src/druid.properties"));
        //2.获取连接对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //3.获取数据库连接
        Connection conn = dataSource.getConnection();
        //4.定义sql
        String sql = "select * from tb_article limit ?,?";
        //5.获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //6.设置参数
        pstmt.setInt(1,begin);
        pstmt.setInt(2,size);
        //7.执行pstmt
        ResultSet rs = pstmt.executeQuery();
        //8.处理结果 List<Article> 封装Brand对象,获取List集合
        List<Article> articles = new ArrayList<>();
        Article article = null;
        while (rs.next()){
            article = new Article();
            article.setId(rs.getInt("id"));
            article.setTitle(rs.getString("title"));
            article.setContent(rs.getString("content"));
            article.setNickname(rs.getString("nickname"));
            article.setLabel(rs.getString("label"));
            //装载集合
            articles.add(article);
        }
        //9.释放资源
        rs.close();
        pstmt.close();
        conn.close();
        return articles;
    }

    /**
     * 查询总文章数
     * @return
     * @throws Exception
     */
    public int selectTotalCount() throws Exception {
        int count = 0;
        //1.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("D:/java/LastDemo/src/druid.properties"));
        //2.获取连接对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //3.获取数据库连接
        Connection conn = dataSource.getConnection();
        //4.定义sql
        String sql = "select count(1) from tb_article";
        //5.获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //6.执行pstmt
        ResultSet rs = pstmt.executeQuery();
        //7.处理结果
        while (rs.next()){
            count = rs.getInt(1);
        }
        //8.释放资源
        rs.close();
        pstmt.close();
        conn.close();
        return count;
    }

    /**
     * 分页加条件查询
     * @param begin
     * @param size
     * @param article
     * @return
     * @throws Exception
     */
    public List<Article> selectByPageAndCondition(int begin,int size,Article article) throws Exception {
        //1.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("D:/java/LastDemo/src/druid.properties"));
        //2.获取连接对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //3.获取数据库连接
        Connection conn = dataSource.getConnection();
        //4.定义sql
        StringBuffer sql = new StringBuffer("select * from tb_article where 1 = 1 ");
        ArrayList<String> al = new ArrayList<>();
        //5.判断article是否有数据,若则有增长字符串
        if(article.getTitle() != null && article.getTitle().length() > 0){
//            sql.append("and title like '").append(article.getTitle()).append("' ");
            sql.append("and title like ? ");
            al.add(article.getTitle());
        }
        if(article.getContent() != null && article.getContent().length() > 0){
//            sql.append("and content like '").append(article.getContent()).append("' ");
            sql.append("and content like ? ");
            al.add(article.getContent());
        }
        if(article.getNickname() != null && article.getNickname().length() > 0){
//            sql.append("and nickname like '").append(article.getNickname()).append("' ");
            sql.append("and nickname like ? ");
            al.add(article.getNickname());
        }
        if(article.getLabel() != null && article.getLabel().length() > 0){
//            sql.append("and label like '").append(article.getLabel()).append("' ");
            sql.append("and label like ? ");
            al.add(article.getLabel());
        }
        // 判断结束后，加上分页查询
        sql.append("limit ?,?");
        //6.获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(String.valueOf(sql));
        //7.设置参数
        int count = al.size();
        for(int i=1;i<=count;i++){
            pstmt.setString(i,al.get(i-1));
        }
        pstmt.setInt(count+1,begin);
        pstmt.setInt(count+2,size);
        //8.执行pstmt
        ResultSet rs = pstmt.executeQuery();
        //9.处理结果 List<Article> 封装Brand对象,获取List集合
        List<Article> articles = new ArrayList<>();
        Article a = null;
        while (rs.next()){
            a = new Article();
            a.setId(rs.getInt("id"));
            a.setTitle(rs.getString("title"));
            a.setContent(rs.getString("content"));
            a.setNickname(rs.getString("nickname"));
            a.setLabel(rs.getString("label"));
            //装载集合
            articles.add(a);
        }
        //10.释放资源
        rs.close();
        pstmt.close();
        conn.close();
        return articles;
    }

    public int selectTotalCountByCondition(Article article) throws Exception {
        int count = 0;
        //1.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("D:/java/LastDemo/src/druid.properties"));
        //2.获取连接对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //3.获取数据库连接
        Connection conn = dataSource.getConnection();
        //4.定义sql
        StringBuffer sql = new StringBuffer("select count(1) from tb_article where 1 = 1");
        ArrayList<String> al = new ArrayList<>();
        //5.判断article是否有数据,若则有增长字符串
        if(article.getTitle() != null && article.getTitle().length() > 0){
            sql.append(" and title like ?");
            al.add(article.getTitle());
        }
        if(article.getContent() != null && article.getContent().length() > 0){
            sql.append(" and content like ?");
            al.add(article.getContent());
        }
        if(article.getNickname() != null && article.getNickname().length() > 0){
            sql.append(" and nickname like ?");
            al.add(article.getNickname());
        }
        if(article.getLabel() != null && article.getLabel().length() > 0){
            sql.append(" and label like ?");
            al.add(article.getLabel());
        }
        //5.获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(String.valueOf(sql));
        int c = al.size();
        for(int i=1;i<=c;i++){
            pstmt.setString(i,al.get(i-1));
        }
        //6.执行pstmt
        ResultSet rs = pstmt.executeQuery();
        //7.处理结果
        while (rs.next()){
            count = rs.getInt(1);
        }
        //8.释放资源
        rs.close();
        pstmt.close();
        conn.close();
        return count;
    }

    public List<Article> selectByIds(int[] ids) throws Exception {
        //1.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("D:/java/LastDemo/src/druid.properties"));
        //2.获取连接对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //3.获取数据库连接
        Connection conn = dataSource.getConnection();
        List<Article> articles = new ArrayList<>();
        for(int i=0;i<ids.length;i++){
            //4.定义sql
            String sql = "select * from tb_article where id = ?";
            //6.获取pstmt对象
            PreparedStatement pstmt = conn.prepareStatement(sql);
            //7.设置参数
            pstmt.setInt(1,ids[i]);
            //8.执行pstmt
            ResultSet rs = pstmt.executeQuery();
            Article a = null;
            while (rs.next()){
                a = new Article();
                a.setId(rs.getInt("id"));
                a.setTitle(rs.getString("title"));
                a.setContent(rs.getString("content"));
                a.setNickname(rs.getString("nickname"));
                a.setLabel(rs.getString("label"));
                //装载集合
                articles.add(a);
            }
            //释放资源
            rs.close();
            pstmt.close();
        }
        //10.释放资源
        conn.close();
        return articles;
    }
}
