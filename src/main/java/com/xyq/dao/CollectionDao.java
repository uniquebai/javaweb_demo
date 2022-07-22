package com.xyq.dao;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.xyq.pojo.Collection;
import com.xyq.pojo.Fans;
import com.xyq.pojo.User;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * @author cwtt
 */
public class CollectionDao {
    public void add(Collection collection) throws Exception {
        //1.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("D:/java/LastDemo/src/druid.properties"));
        //2.获取连接对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //3.获取数据库连接
        Connection connection = dataSource.getConnection();
        //4.定义sql
        String sql = "insert into tb_collection(userId,articleId) VALUES (?,?)";
        //5.获取pstmt对象
        PreparedStatement pstmt = connection.prepareStatement(sql);
        //6.设置参数
        pstmt.setInt(1,collection.getUserId());
        pstmt.setInt(2,collection.getArticleId());
        //7.执行pstmt
        int i = pstmt.executeUpdate();
        //8.释放资源
        pstmt.close();
        connection.close();
    }

    public int[] selectById(int id) throws Exception {
        //1.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("D:/java/LastDemo/src/druid.properties"));
        //2.获取连接对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //3.获取数据库连接
        Connection conn = dataSource.getConnection();
        //4.定义sql
        String sql = "select * from tb_collection where userId = ?";
        //5.获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //6.设置参数
        pstmt.setInt(1,id);
        //7.执行pstmt
        ResultSet rs = pstmt.executeQuery();
        int[] ids = new int[100];
        int count = 0;
        while (rs.next()){
            ids[count] = rs.getInt("articleId");
            count++;
        }
        //8.释放资源
        rs.close();
        pstmt.close();
        conn.close();
        return ids;
    }

    /**
     * 获取收藏总数
     * @param id
     * @return
     * @throws Exception
     */
    public int selectTotalCountById(int id) throws Exception {
        //1.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("D:/java/LastDemo/src/druid.properties"));
        //2.获取连接对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //3.获取数据库连接
        Connection conn = dataSource.getConnection();
        //4.定义sql
        String sql = "select count(1) from tb_collection where userId = ?";
        //5.获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //6.设置参数
        pstmt.setInt(1,id);
        //7.执行pstmt
        ResultSet rs = pstmt.executeQuery();
        int count = 0;
        while (rs.next()){
            count = rs.getInt(1);
        }
        //8.释放资源
        rs.close();
        pstmt.close();
        conn.close();
        return count;
    }
}
