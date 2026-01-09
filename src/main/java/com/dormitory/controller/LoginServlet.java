package com.dormitory.controller;

import com.dormitory.dao.UserDao;
import com.dormitory.entity.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserDao userDao = new UserDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 设置编码，防止乱码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        // 2. 获取网页表单输入的数据
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // 3. 调用 DAO 查数据库
        User user = userDao.login(username, password);


        if (user != null) {
            // --- 登录成功 ---
            System.out.println("登录成功：" + username);

            // 存入 Session
            HttpSession session = req.getSession();
            session.setAttribute("currentUser", user);
            if (user.getRole() == 0) {
                // 如果是管理员 (role=0)
                resp.sendRedirect("main_admin.jsp");
            } else if (user.getRole() == 2) {
                // 如果是学生 (role=2)
                resp.sendRedirect("main_student.jsp");
            } else {
                // 其他角色(如宿管)，暂时也跳管理页，或者给个提示
                resp.sendRedirect("main_admin.jsp");
            }

        } else {
            // --- 登录失败 ---
            System.out.println("登录失败");
            // 存入错误信息
            req.setAttribute("msg", "用户名或密码错误！");
            // 转发回登录页 (转发可以带数据，重定向不行)
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}