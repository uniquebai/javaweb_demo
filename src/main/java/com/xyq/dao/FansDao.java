package com.xyq.dao;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.xyq.pojo.Fans;
import com.xyq.pojo.User;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

/**
 * @author cwtt
 */
public class FansDao {
    /**
     * 添加关注记录的DAO
     * @param fans
     * @throws Exception
     */
    public void add(Fans fans) throws Exception {
        //1.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("D:/java/LastDemo/src/druid.properties"));
        //2.获取连接对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //3.获取数据库连接
        Connection connection = dataSource.getConnection();
        //4.定义sql
        String sql = "insert into tb_fans(uid,fid) VALUES (?,?)";
        //5.获取pstmt对象
        PreparedStatement pstmt = connection.prepareStatement(sql);
        //6.设置参数
        pstmt.setInt(1,fans.getuId());
        pstmt.setInt(2,fans.getfId());
        //7.执行pstmt
        int i = pstmt.executeUpdate();
        //8.释放资源
        pstmt.close();
        connection.close();
    }
}
