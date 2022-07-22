package com.xyq.dao;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.xyq.pojo.Article;
import com.xyq.pojo.User;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author cwtt
 */
public class UserDao {
    /**
     * 查询所有用户数据的Dao
     * @return
     * @throws Exception
     */
    public List<User> selectAll() throws Exception {
        //1.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("D:/java/LastDemo/src/druid.properties"));
        //2.获取连接对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //3.获取数据库连接
        Connection connection = dataSource.getConnection();
        //4.定义sql
        String sql = "select * from tb_user";
        //5.获取pstmt对象
        PreparedStatement pstmt = connection.prepareStatement(sql);
        //7.执行pstmt
        ResultSet rs = pstmt.executeQuery();
        ArrayList<User> users = new ArrayList<>();
        User user = null;
        while (rs.next()){
            user = new User();
            user.setId(rs.getInt("id"));
            user.setNickname(rs.getString("nickname"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setHobby(rs.getString("hobby"));
            user.setLocation(rs.getString("location"));
            user.setStatus(rs.getInt("status"));
            //装载集合
            users.add(user);
        }
        //8.释放资源
        rs.close();
        pstmt.close();
        connection.close();
        return users;
    }
    /**
     * 根据用户名查询用户
     * @param u
     * @return
     * @throws Exception
     */
    public User selectByUsername(String u) throws Exception {
        //1.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("D:/java/LastDemo/src/druid.properties"));
        //2.获取连接对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //3.获取数据库连接
        Connection connection = dataSource.getConnection();
        //4.定义sql
        String sql = "select * from tb_user where username = ?";
        //5.获取pstmt对象
        PreparedStatement pstmt = connection.prepareStatement(sql);
        //6.设置参数
        pstmt.setString(1,u);
        //7.执行pstmt
        ResultSet rs = pstmt.executeQuery();
        User user = new User();
        while (rs.next()){
            user.setId(rs.getInt("id"));
            user.setNickname(rs.getString("nickname"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setHobby(rs.getString("hobby"));
            user.setLocation(rs.getString("location"));
            user.setStatus(rs.getInt("status"));
        }
        //8.释放资源
        rs.close();
        pstmt.close();
        connection.close();
        return user;
    }

    /**
     * 根据昵称查询用户
     * @param u
     * @return
     * @throws Exception
     */
    public User selectByNickname(String u) throws Exception {
        //1.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("D:/java/LastDemo/src/druid.properties"));
        //2.获取连接对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //3.获取数据库连接
        Connection connection = dataSource.getConnection();
        //4.定义sql
        String sql = "select * from tb_user where nickname = ?";
        //5.获取pstmt对象
        PreparedStatement pstmt = connection.prepareStatement(sql);
        //6.设置参数
        pstmt.setString(1,u);
        //7.执行pstmt
        ResultSet rs = pstmt.executeQuery();
        User user = new User();
        while (rs.next()){
            user.setId(rs.getInt("id"));
            user.setNickname(rs.getString("nickname"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setHobby(rs.getString("hobby"));
            user.setLocation(rs.getString("location"));
            user.setStatus(rs.getInt("status"));
        }
        //8.释放资源
        rs.close();
        pstmt.close();
        connection.close();
        return user;
    }

    /**
     * 添加用户
     * @param user
     * @throws Exception
     */
    public void add(User user) throws Exception {
        //1.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("D:/java/LastDemo/src/druid.properties"));
        //2.获取连接对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //3.获取数据库连接
        Connection connection = dataSource.getConnection();
        //4.定义sql
        String sql = "insert into tb_user(username,nickname,password,hobby,location,status) VALUES (?,?,?,?,?,?)";
        //5.获取pstmt对象
        PreparedStatement pstmt = connection.prepareStatement(sql);
        //6.设置参数
        pstmt.setString(1,user.getUsername());
        pstmt.setString(2,user.getNickname());
        pstmt.setString(3,user.getPassword());
        pstmt.setString(4,user.getHobby());
        pstmt.setString(5,user.getLocation());
        pstmt.setInt(6,0);
        //7.执行pstmt
        int i = pstmt.executeUpdate();
        //8.释放资源
        pstmt.close();
        connection.close();
    }

    /**
     * 修改用户数据的DAO
     * @param user
     * @throws Exception
     */
    public void updateById(User user) throws Exception {
        //1.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("D:/java/LastDemo/src/druid.properties"));
        //2.获取连接对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //3.获取数据库连接
        Connection conn = dataSource.getConnection();
        //4.定义sql
        String sql = "update tb_user\n" +
                "            set nickname = ?,\n" +
                "            username = ?,\n" +
                "            hobby = ?,\n" +
                "            location = ?\n" +
                "        where id = ?;";
        //5.获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //6.设置参数
        pstmt.setString(1,user.getNickname());
        pstmt.setString(2,user.getUsername());
        pstmt.setString(3,user.getHobby());
        pstmt.setString(4,user.getLocation());
        pstmt.setInt(5,user.getId());
        //7.执行pstmt
        int i = pstmt.executeUpdate();
        //8.释放资源
        pstmt.close();
        conn.close();
    }

    public void deleteById(int id) throws Exception {
        //1.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("D:/java/LastDemo/src/druid.properties"));
        //2.获取连接对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //3.获取数据库连接
        Connection conn = dataSource.getConnection();
        //4.定义sql
        String sql = "delete from tb_user where id = ?";
        //5.获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //6.设置参数
        pstmt.setInt(1,id);
        //7.执行pstmt
        int i = pstmt.executeUpdate();
        //8.释放资源
        pstmt.close();
        conn.close();
    }

    /**
     * 根据用户名和密码查询用户
     * @param name
     * @param pass
     * @return
     * @throws Exception
     */
    public User selectByNameAndPassword(String name,String pass) throws Exception {
        //1.加载配置文件
        Properties prop = new Properties();
        prop.load(new FileInputStream("D:/java/LastDemo/src/druid.properties"));
        //2.获取连接对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        //3.获取数据库连接
        Connection conn = dataSource.getConnection();
        //4.定义sql
        String sql = "select * from tb_user where username = ? and password = ?";
        //5.获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //6.设置参数
        pstmt.setString(1,name);
        pstmt.setString(2,pass);
        //7.执行pstmt
        ResultSet rs = pstmt.executeQuery();
        User user = new User();
        while (rs.next()){
            user.setId(rs.getInt("id"));
            user.setNickname(rs.getString("nickname"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setHobby(rs.getString("hobby"));
            user.setLocation(rs.getString("location"));
            user.setStatus(rs.getInt("status"));
        }
        //8.释放资源
        rs.close();
        pstmt.close();
        conn.close();
        return user;
    }
}
