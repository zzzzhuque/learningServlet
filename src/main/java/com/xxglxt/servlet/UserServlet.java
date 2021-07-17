package com.xxglxt.servlet;

import com.xxglxt.pojo.User;
import com.xxglxt.service.UserService;
import com.xxglxt.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * Servlet重定向路径总结
 * 相对路径：从当前请求的路径查找资源的路径。如果servlet别名包含目录，则jsp先提交form表单
 *         再sendRedirect重定向时，会造成路径冗余，资源查找失败
 * 绝对路径：/虚拟项目名/资源路径，第一个/表示服务器根目录，即http://localhost:8080/
 *
 * Servlet请求转发路径总结
 * 绝对路径：/资源路径，第一个/表示项目根目录，即http://localhost:8080/Manager_war_exploded
 *         注意和重定向绝对路径的区分
 */

public class UserServlet extends HttpServlet {
    // 声明日志对象
    Logger logger = Logger.getLogger(UserServlet.class);
    // 获取service层对象，全局创建
    UserService us = new UserServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置请求编码格式
        req.setCharacterEncoding("utf-8");
        // 设置响应编码格式
        resp.setContentType("text/html;charset=utf-8");
        // 获取操作符
        String oper = req.getParameter("oper"); // getParameter可获取表单数据
        if ("login".equals(oper)) {
            // 调用登录处理方法
            checkUserLogin(req, resp);
        } else if ("out".equals(oper)) {
            // 调用退出功能
            userOut(req, resp);
        } else if ("reg".equals(oper)) {
            // 调用注册功能
            userReg(req, resp);
        } else if ("show".equals(oper)) {
            // 调用显示所有用户功能
            userShow(req, resp);
        } else if ("pwd".equals(oper)) {
            // 修改用户密码
            userChangePwd(req, resp);
        } else {
            logger.debug("没有找到对应的操作符：" + oper);
        }
    }

    // 创建注册功能
    private void userReg(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 获取请求信息
        String uname = req.getParameter("uname");
        String pwd = req.getParameter("pwd");
        String sex = req.getParameter("sex");
        int age = "".equals(req.getParameter("age")) ? 0 : Integer.parseInt(req.getParameter("age"));
        String birth = req.getParameter("birth");
        User u = new User(0, uname, pwd, sex, age, birth);  // 0表示uid，因为是自增的，所以随便填
        // 处理请求信息
        boolean flag = us.userRegService(u);
        // 响应处理结果
        if (flag) {
            // session
            HttpSession hs = req.getSession();
            hs.setAttribute("reg", "true");
            // 重定向，一定要重定向，避免刷新一次注册一次
            resp.sendRedirect("/Manager_war_exploded/login.jsp");
        }
    }

    // 显示所有用户信息
    private void userShow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 处理请求
        // 调用service
        List<User> lu = us.userShowService();
        if (lu != null) {
            // 将查询的用户数据存储到request对象
            req.setAttribute("lu", lu);
            // 请求转发
            req.getRequestDispatcher("/user/showUser.jsp").forward(req, resp);
        }
    }

    // 用户修改密码
    private void userChangePwd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 获取新密码数据
        String newPwd = req.getParameter("newPwd");
        // 从session获取用户信息
        User u = (User) req.getSession().getAttribute("user");
        int uid = u.getUid();
        // 处理请求
        // 调用service处理，index为1表示修改成功，为0表示修改失败
        boolean flag = us.userChangePwdService(newPwd, uid);
        if (flag) {
            // 获取session，这里的session仍然是之前登录的，重新登录成功时更新session中的user
            HttpSession hs = req.getSession();
            hs.setAttribute("pwd", "true");
            // 重定向到登录页面
            resp.sendRedirect("/Manager_war_exploded/login.jsp");
        }
    }

    // 处理退出
    private void userOut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 获取session对象
        HttpSession hs = req.getSession();
        // 强制销毁session
        hs.invalidate();
        // 重定向到登录页面
        resp.sendRedirect("/Manager_war_exploded/login.jsp");
    }

    // 处理登录
    private void checkUserLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // 获取请求信息
        String uname = req.getParameter("uname");
        String pwd = req.getParameter("pwd");
        // 处理请求信息
            // 获取service层对象，全局创建

            // 校验，检索到该用户，返回全部用户信息；为检索到则返回null
        User u = us.checkUserLoginService(uname, pwd);
        // 响应处理结果
        if (u != null) {    // 登录成功
            // 获取session对象，session和重定向是一对好兄弟
            HttpSession hs = req.getSession();
            // 将用户数据存储到session中
            hs.setAttribute("user", u);
            // 重定向
            resp.sendRedirect("/Manager_war_exploded/main/main.jsp"); // 相对路径：相当于前面接了http://localhost:8080/
            return;
        } else {            // 登录失败
            // 添加登录失败标识符
            req.setAttribute("flag", "false");
            // 请求转发
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }
    }
}
