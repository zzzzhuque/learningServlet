package com.xxglxt.service;

import com.xxglxt.pojo.User;

import java.util.List;

public interface UserService {
    /**
     * 校验用户登录
     * @param uname 用户名
     * @param pwd 密码
     * @return 返回查询到的用户信息
     */
    User checkUserLoginService(String uname, String pwd);

    /**
     * 修改用户密码
     * @param newPwd 新密码
     * @param uid 用户ID
     * @return 为true表示修改成功，为false表示修改失败
     */
    boolean userChangePwdService(String newPwd, int uid);

    /**
     * 获取所有用户信息
     * @return 返回List封装的用户信息
     */
    List<User> userShowService();

    /**
     * 注册新用户
     * @param u 注册用户信息
     * @return true表示注册成功，false表示注册失败
     */
    boolean userRegService(User u);
}
