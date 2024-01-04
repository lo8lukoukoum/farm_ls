package com.java.servlet;

import com.java.dao.UserDao;
import com.java.dao.impl.UserDaoImpl;
import com.java.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author shaonaiyi@163.com
 * @Date 2022/3/6 12:40
 * @Description 登录Servlet
 */
@WebServlet(name = "/login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        // 获取前端页面传过来的值
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        System.out.println(username+password);

        // 执行查询数据库逻辑
        UserDao userDao = new UserDaoImpl();
        User user = userDao.login(username, password);

        // 如果根据用户名和密码能查得到值
        if (user != null) {
            System.out.println("hello");
            resp.sendRedirect("/index.html");
            int roleDb = user.getRole();
            // 权限的选择跟数据库不匹配时，且不是管理员用户时，返回无权限
            if (roleDb != Integer.parseInt(role) && roleDb != 0) {
                req.setAttribute("message", "无权限登录");
                req.getRequestDispatcher("/defeat.jsp").forward(req, resp);
            } else {
                req.setAttribute("user", user);
                req.getRequestDispatcher("/index.html").forward(req, resp);
            }
        } else {    // 用户名或者密码错误执行以下代码
            req.setAttribute("message", "用户名或者密码错误");
            req.getRequestDispatcher("/defeat.jsp").forward(req, resp);
        }

    }
}
