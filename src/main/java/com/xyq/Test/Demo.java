//package com.xyq.Test;
//
//import com.alibaba.druid.pool.DruidDataSourceFactory;
//import com.xyq.pojo.Article;
//import com.xyq.pojo.User;
//import org.junit.jupiter.api.DynamicTest;
//import org.junit.jupiter.api.Test;
//
//import javax.sql.DataSource;
//import java.io.FileInputStream;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//
///**
// * @author cwtt
// */
//public class Demo {
//    @Test
//    public void selectByUsername() throws Exception {
//        String u = "zhaoliu";
//        //1.加载配置文件
//        Properties prop = new Properties();
//        prop.load(new FileInputStream("D:/java/LastDemo/src/druid.properties"));
//        //2.获取连接对象
//        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
//        //3.获取数据库连接
//        Connection connection = dataSource.getConnection();
//        //4.定义sql
//        String sql = "select * from tb_user where username = ?";
//        //5.获取pstmt对象
//        PreparedStatement pstmt = connection.prepareStatement(sql);
//        //6.设置参数
//        pstmt.setString(1,u);
//        //7.执行pstmt
//        ResultSet rs = pstmt.executeQuery();
//        User user = new User();
//        while (rs.next()){
//            user.setId(rs.getInt("id"));
//            user.setNickname(rs.getString("nickname"));
//            user.setUsername(rs.getString("username"));
//            user.setPassword(rs.getString("password"));
//            user.setStatus(rs.getInt("status"));
//        }
//        //8.释放资源
//        rs.close();
//        pstmt.close();
//        connection.close();
//        System.out.println(user);
////        return user;
//    }
//
//    @Test
//    public void add() throws Exception {
//        User user = new User();
//        user.setNickname("wolf");
//        user.setUsername("liuliu");
//        user.setPassword("12345");
//        //1.加载配置文件
//        Properties prop = new Properties();
//        prop.load(new FileInputStream("D:/java/LastDemo/src/druid.properties"));
//        //2.获取连接对象
//        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
//        //3.获取数据库连接
//        Connection connection = dataSource.getConnection();
//        //4.定义sql
//        String sql = "insert into tb_user(id, username, nickname , password ,status) VALUES (?,?,?,?,?)";
//        //5.获取pstmt对象
//        PreparedStatement pstmt = connection.prepareStatement(sql);
//        //6.设置参数
//        pstmt.setInt(1,0);
//        pstmt.setString(2,user.getUsername());
//        pstmt.setString(3,user.getNickname());
//        pstmt.setString(4,user.getPassword());
//        pstmt.setInt(5,0);
//        //7.执行pstmt
//        pstmt.executeUpdate();
//        //8.释放资源
//        pstmt.close();
//        connection.close();
//    }
//
//    @Test
//    public void selectByNameAndPassword() throws Exception {
//        String name = "zhaoliu";
//        String pass = "123";
//        //1.加载配置文件
//        Properties prop = new Properties();
//        prop.load(new FileInputStream("D:/java/LastDemo/src/druid.properties"));
//        //2.获取连接对象
//        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
//        //3.获取数据库连接
//        Connection conn = dataSource.getConnection();
//        //4.定义sql
//        String sql = "select * from tb_user where username = ? and password = ?";
//        //5.获取pstmt对象
//        PreparedStatement pstmt = conn.prepareStatement(sql);
//        //6.设置参数
//        pstmt.setString(1,name);
//        pstmt.setString(2,pass);
//        //7.执行pstmt
//        ResultSet rs = pstmt.executeQuery();
//        User user = new User();
//        while (rs.next()){
//            user.setId(rs.getInt("id"));
//            user.setNickname(rs.getString("nickname"));
//            user.setUsername(rs.getString("username"));
//            user.setPassword(rs.getString("password"));
//            user.setStatus(rs.getInt("status"));
//        }
//        //8.释放资源
//        rs.close();
//        pstmt.close();
//        conn.close();
//        System.out.println(user);
////        return user;
//    }
//
//    @Test
//    public void selectByPageAndCondition() throws Exception {
//        int begin = 0;
//        int size = 5;
//        Article article = new Article();
//        article.setContent("%之%");
////        article.setNickname("%wolf%");
////        article.setLabel("%古%");
//        //1.加载配置文件
//        Properties prop = new Properties();
//        prop.load(new FileInputStream("D:/java/LastDemo/src/druid.properties"));
//        //2.获取连接对象
//        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
//        //3.获取数据库连接
//        Connection conn = dataSource.getConnection();
//        //4.定义sql
//        StringBuffer sql = new StringBuffer("select * from tb_article where 1 = 1 ");
//        ArrayList<String> al = new ArrayList<>();
//        //5.判断article是否有数据,若则有增长字符串
//        if(article.getTitle() != null && article.getTitle().length() > 0){
////            sql.append("and title like '").append(article.getTitle()).append("' ");
//            sql.append("and title like ? ");
//            al.add(article.getTitle());
//        }
//        if(article.getContent() != null && article.getContent().length() > 0){
////            sql.append("and content like '").append(article.getContent()).append("' ");
//            sql.append("and content like ? ");
//            al.add(article.getContent());
//        }
//        if(article.getNickname() != null && article.getNickname().length() > 0){
////            sql.append("and nickname like '").append(article.getNickname()).append("' ");
//            sql.append("and nickname like ? ");
//            al.add(article.getNickname());
//        }
//        if(article.getLabel() != null && article.getLabel().length() > 0){
////            sql.append("and label like '").append(article.getLabel()).append("' ");
//            sql.append("and label like ? ");
//            al.add(article.getLabel());
//        }
//        // 判断结束后，加上分页查询
//        sql.append("limit ?,?");
//        System.out.println(sql);
//        //6.获取pstmt对象
//        PreparedStatement pstmt = conn.prepareStatement(String.valueOf(sql));
//        //7.设置参数
//        int count = al.size();
//        System.out.println(count);
//        for(int i=1;i<=count;i++){
//            pstmt.setString(i,al.get(i-1));
//        }
//        pstmt.setInt(count+1,begin);
//        pstmt.setInt(count+2,size);
//        //8.执行pstmt
//        ResultSet rs = pstmt.executeQuery();
//        //9.处理结果 List<Article> 封装Brand对象,获取List集合
//        List<Article> articles = new ArrayList<>();
//        Article a = null;
//        while (rs.next()){
//            a = new Article();
//            a.setId(rs.getInt("id"));
//            a.setTitle(rs.getString("title"));
//            a.setContent(rs.getString("content"));
//            a.setNickname(rs.getString("nickname"));
//            a.setLabel(rs.getString("label"));
//            //装载集合
//            articles.add(a);
//        }
//        //10.释放资源
//        rs.close();
//        pstmt.close();
//        conn.close();
//        System.out.println(articles);
//    }
//}
