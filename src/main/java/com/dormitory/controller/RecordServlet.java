package com.dormitory.controller;

import com.dormitory.dao.RecordDao;
import com.dormitory.dao.StudentDao; // 需要用来查学生列表供选择
import com.dormitory.entity.Record;
import com.dormitory.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/record")
public class RecordServlet extends HttpServlet {
    private RecordDao recordDao = new RecordDao();
    private StudentDao studentDao = new StudentDao(); // 借用一下

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String method = req.getParameter("method");

        if ("list".equals(method) || method == null) {
            list(req, resp);
        } else if ("add".equals(method)) {
            add(req, resp);
        }
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Record> records = recordDao.findAll();
        // 我们还需要查出所有学生，以便在页面上的“添加弹窗”里让用户选择是谁在打卡
        List<Student> students = studentDao.findAll();

        req.setAttribute("recordList", records);
        req.setAttribute("studentList", students);
        req.getRequestDispatcher("record_list.jsp").forward(req, resp);
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Record r = new Record();
        r.setStudentId(Integer.parseInt(req.getParameter("studentId")));
        r.setType(req.getParameter("type")); // "到达" 或 "离开"
        r.setRemark(req.getParameter("remark"));

        recordDao.add(r);
        resp.sendRedirect("record?method=list");
    }
}