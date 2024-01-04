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
 * @Date 2022/3/6 16:38
 * @Description 注册servlet
 */
@WebServlet(name = "/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPut(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        // id不需要传进来，角色默认注册是普通用户，无法注册管理员
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        UserDao userDao = new UserDaoImpl();
        if (userDao.register(user)) {
            System.out.println("注册成功");
            // 注册成功，跳转登录页面
            req.getRequestDispatcher("register-success.jsp").forward(req,resp);
        } else {
            System.out.println("注册失败");
            req.setAttribute("message", "注册失败");
            req.getRequestDispatcher("register.jsp").forward(req,resp);
        }

    }
}