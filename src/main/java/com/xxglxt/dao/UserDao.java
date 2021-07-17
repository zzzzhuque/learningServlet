package com.xxglxt.dao;

import com.xxglxt.pojo.User;

import java.util.List;

public interface UserDao {
    /**
     * 根据用户名和密码查询用户信息
     * @param uname 用户名
     * @param pwd 密码
     * @return 返回查询到的用户信息
     */
    User checkUserLoginDao(String uname, String pwd);

    /**
     * 根据用户ID修改用户密码
     * @param newPwd 新密码
     * @param uid 用户ID
     * @return 为true表示修改成功，为false表示修改失败
     */
    boolean userChangePwdDao(String newPwd, int uid);

    /**
     * 获取所有用户信息
     * @return 返回List封装的用户信息
     */
    List<User> userShowDao();

    /**
     * 注册新用户
     * @param u 注册用户信息
     * @return true表示注册成功，false表示注册失败
     */
    boolean userRegDao(User u);
}
