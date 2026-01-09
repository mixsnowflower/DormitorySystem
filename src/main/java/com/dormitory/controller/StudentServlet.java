package com.dormitory.controller;
import com.dormitory.dao.UserDao;
import com.dormitory.entity.User;
import com.dormitory.dao.StudentDao;
import com.dormitory.entity.Student;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {

    private StudentDao studentDao = new StudentDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp); // 无论 get 还是 post 都交给 doPost 处理
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        // 获取操作类型，例如：student?method=list
        String method = req.getParameter("method");
        System.out.println("DEBUG: 接收到的 method 是: [" + method + "]");

        if ("list".equals(method)) {
            list(req, resp);
        } else {
            // 默认查列表
            list(req, resp);
        }
        // StudentServlet.java 的 doPost 方法里

        if ("list".equals(method)) {
            list(req, resp);
        } else if ("add".equals(method)) {
            add(req, resp);
        } else if ("delete".equals(method)) {
            delete(req, resp);
        } else if ("update".equals(method)) {
            update(req, resp);
        } else {
            list(req, resp);
        }
    }

    // 处理查询列表请求
    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 调用 DAO 查数据
        List<Student> students = studentDao.findAll();

        // 2. 把数据存到 request 里
        req.setAttribute("studentList", students);

        // 3. 转发到 JSP 页面显示
        req.getRequestDispatcher("student_list.jsp").forward(req, resp);
    }
    // 记得导入 UserDao 和 User 类

// ... 在类里面添加这个方法 ...

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. 获取表单数据
        String sno = req.getParameter("sno");
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        String className = req.getParameter("className");
        String phone = req.getParameter("phone");

        // 2. 先创建账号 (User)
        User user = new User();
        user.setUsername(sno);    // 账号就是学号
        user.setPassword("123456"); // 默认密码

        // 注意：这里需要你实例化一个 UserDao
        UserDao userDao = new UserDao();
        int newUserId = userDao.addUser(user); // 这一步会往 sys_user 表插数据

        if (newUserId != -1) {
            // 3. 账号创建成功后，再创建学生档案
            Student s = new Student();
            s.setUserId(newUserId); // 绑定刚才生成的ID
            s.setSno(sno);
            s.setName(name);
            s.setGender(gender);
            s.setClassName(className);
            s.setPhone(phone);

            studentDao.addStudent(s); // 这一步往 student 表插数据

            // 4. 全部完成后，回到列表页
            resp.sendRedirect("student?method=list");
        } else {
            // 账号重复或失败
            resp.getWriter().write("添加失败，可能是学号已存在！");
        }
    }

    private void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 1. 获取要删除的学生 ID
        String idStr = req.getParameter("id");
        if (idStr != null) {
            int studentId = Integer.parseInt(idStr);

            // 2. 调用 DAO
            studentDao.delete(studentId);
        }

        // 3. 删完之后，刷新列表（重新跳回 list）
        resp.sendRedirect("student?method=list");
    }
    private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain;charset=UTF-8"); // 告诉前端我回的是纯文本

        String idStr = req.getParameter("studentId");

        // 1. 简单的判空保护
        if (idStr == null || idStr.trim().isEmpty()) {
            resp.setStatus(400); // 设置状态码：请求错误
            resp.getWriter().write("ID丢失");
            return; // 🔴 必须加 return，防止代码继续往下跑
        }

        try {
            // 2. 封装数据
            Student s = new Student();
            s.setStudentId(Integer.parseInt(idStr));
            s.setName(req.getParameter("name"));
            s.setGender(req.getParameter("gender"));
            s.setClassName(req.getParameter("className"));
            s.setPhone(req.getParameter("phone"));

            // 3. 更新数据库
            boolean success = studentDao.update(s);

            if (success) {
                // 4. 🔴 关键修改：不要 sendRedirect！直接回“成功”
                resp.setStatus(200);
                resp.getWriter().write("success");
            } else {
                resp.setStatus(500);
                resp.getWriter().write("fail");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500); // 设置状态码：服务器内部错误
            resp.getWriter().write("error");
        }
    }
}
