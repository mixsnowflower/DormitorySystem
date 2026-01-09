package com.dormitory.controller;

import com.dormitory.dao.UserDao;
import com.dormitory.entity.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private UserDao userDao = new UserDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        // 🔴 修改点：现在 addUser 返回的是 int (新用户ID)
        int newUserId = userDao.addUser(user);

        // 🔴 修改点：只要 ID 不等于 -1，就说明注册成功
        if (newUserId != -1) {
            req.setAttribute("msg", "注册成功，请登录！");
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } else {
            req.setAttribute("msg", "注册失败，用户名可能已存在！");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        }
    }
}