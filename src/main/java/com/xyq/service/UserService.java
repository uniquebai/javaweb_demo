package com.xyq.service;

import com.xyq.dao.UserDao;
import com.xyq.pojo.User;

/**
 * @author cwtt
 */
public class UserService {
    private UserDao ud = new UserDao();

    /**
     * 注册
     * @param username
     * @return
     * @throws Exception
     */
    public boolean register(String username) throws Exception {
        //1.根据用户名查询用户是否存在
        User u = ud.selectByUsername(username);
        //2.判断是否存在
        if(u.getId() != null){
            //用户名存在，注册失败
            return true;
        }
        //用户名不存在，可以注册
        return false;
    }

//    public User login(String name,String pass) throws Exception {
//        User user = ud.selectByNameAndPassword(name, pass);
//        if(user.getId() != null){
//            //登陆成功，进入论文网站
//            return user;
//        } else {
//            //登陆失败
//            return false;
//        }
//    }
}
