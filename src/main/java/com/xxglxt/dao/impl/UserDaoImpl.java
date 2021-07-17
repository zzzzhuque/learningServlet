package com.xxglxt.dao.impl;

import com.xxglxt.dao.UserDao;
import com.xxglxt.pojo.User;
import com.xxglxt.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    // 声明日志对象
    Logger logger = Logger.getLogger(UserServiceImpl.class);

    // 根据用户名和密码查询用户信息
    @Override
    public User checkUserLoginDao(String uname, String pwd) {
        // 声明JDBC对象
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        // 声明数据存储对象
        User u = null;
        try {
            // 加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 获取连接对象
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tomcat?useUnicode=true&characterEncoding=utf-8&useSSL=false", "root", "123456789");
            // 创建sql命令
            String sql = "select * from t_user where uname=? and pwd=?";
            // 创建sql命令对象
            ps = conn.prepareStatement(sql);
            // 给占位符赋值
            ps.setString(1, uname);
            ps.setString(2, pwd);
            // 执行
            rs = ps.executeQuery(); // 查用executeQuery
            // 遍历执行结果
            while (rs.next()) {
                // 给变量赋值
                u = new User();
                u.setUid(rs.getInt("uid"));
                u.setUname(rs.getString("uname"));
                u.setPwd(rs.getString("pwd"));
                u.setSex(rs.getString("sex"));
                u.setAge(rs.getInt("age"));
                u.setBirth(rs.getString("birth"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 返回，若没有找到用户，则返回u=null
        return u;
    }

    // 根据用户ID修改用户密码
    @Override
    public boolean userChangePwdDao(String newPwd, int uid) {
        // 声明JDBC对象
        Connection conn = null;
        PreparedStatement ps = null;
        // 声明返回值
        boolean flag = false;
        try {
            // 加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 获取连接对象
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tomcat?useUnicode=true&characterEncoding=utf-8&useSSL=false", "root", "123456789");
            // 创建sql命令
            String sql = "UPDATE t_user SET pwd = ? where uid=?";
            // 创建sql命令对象
            ps = conn.prepareStatement(sql);
            // 给占位符赋值
            ps.setString(1, newPwd);
            ps.setInt(2, uid);
            // 执行
            // ps.executeQuery();
            flag = ps.execute();   // 增删改用execute
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 修改完成返回
        return !flag;
    }

    // 获取所有用户信息
    @Override
    public List<User> userShowDao() {
        // 声明JDBC对象
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        // 声明数据存储对象
        List<User> lu = null;
        try {
            // 加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 获取连接对象
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tomcat?useUnicode=true&characterEncoding=utf-8&useSSL=false", "root", "123456789");
            // 创建sql命令
            String sql = "select * from t_user";
            // 创建sql命令对象
            ps = conn.prepareStatement(sql);
            // 给占位符赋值

            // 执行
            rs = ps.executeQuery(); // 查用executeQuery
            // 给集合赋值
            lu = new ArrayList<User>();
            // 遍历执行结果
            while (rs.next()) {
                // 给变量赋值
                User u = new User();
                u.setUid(rs.getInt("uid"));
                u.setUname(rs.getString("uname"));
                u.setPwd(rs.getString("pwd"));
                u.setSex(rs.getString("sex"));
                u.setAge(rs.getInt("age"));
                u.setBirth(rs.getString("birth"));
                lu.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 返回所有用户信息
        return lu;
    }

    @Override
    public boolean userRegDao(User u) {
        // 声明JDBC对象
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 获取连接对象
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tomcat?useUnicode=true&characterEncoding=utf-8&useSSL=false", "root", "123456789");
            // 创建sql命令
            String sql = "INSERT INTO t_user(uid, uname, pwd, sex, age, birth) VALUES(default, ?, ?, ?, ?, ?)";
            // 创建sql命令对象
            ps = conn.prepareStatement(sql);
            // 给占位符赋值
            ps.setString(1, u.getUname());
            ps.setString(2, u.getPwd());
            ps.setString(3, u.getSex());
            ps.setInt(4, u.getAge());
            String birth = u.getBirth();
            String newBirth = "0000-00-00";
            if (birth != null) {
                String[] splitBirth = birth.split("/");
                newBirth = splitBirth[2] + "-" + splitBirth[0] + "-" + splitBirth[1];
            }
            ps.setString(5, newBirth);
            // 执行
            ps.execute(); // 增删改用execute
            // 遍历执行结果

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
