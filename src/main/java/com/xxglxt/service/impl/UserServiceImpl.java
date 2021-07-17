package com.xxglxt.service.impl;

import com.xxglxt.dao.UserDao;
import com.xxglxt.dao.impl.UserDaoImpl;
import com.xxglxt.pojo.User;
import com.xxglxt.service.UserService;
import org.apache.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {
    // 声明日志对象
    Logger logger = Logger.getLogger(UserServiceImpl.class);
    // 声明Dao层对象
    UserDao ud = new UserDaoImpl();

    // 用户登录
    @Override
    public User checkUserLoginService(String uname, String pwd) {
        // 打印日志
        logger.debug(uname + "发起登录请求");
        // 检索到该用户，返回全部用户信息；为检索到则返回null
        User u = ud.checkUserLoginDao(uname, pwd);
        if (u != null) {
            logger.debug(uname + "登录成功");
        } else {
            logger.debug(uname + "登录失败");
        }
        return u;
    }

    // 修改用户密码，返回值为1表示修改成功，为0表示修改失败
    @Override
    public boolean userChangePwdService(String newPwd, int uid) {
        logger.debug(uid + ":发起密码修改请求");
        boolean flag = ud.userChangePwdDao(newPwd, uid);
        if (flag) {
            logger.debug(uid + ":密码修改成功");
        } else {
            logger.debug(uid + ":密码修改失败");
        }
        return flag;
    }

    // 获取所有用户信息
    @Override
    public List<User> userShowService() {
        List<User> lu = ud.userShowDao();
        logger.debug("显示所有用户信息：" + lu);
        return lu;
    }

    // 注册新用户
    @Override
    public boolean userRegService(User u) {
        return ud.userRegDao(u);
    }
}
