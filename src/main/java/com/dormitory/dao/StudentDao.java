package com.dormitory.dao;

import com.dormitory.entity.Student;
import com.dormitory.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    // 查询所有学生信息
    public List<Student> findAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Student> list = new ArrayList<>();

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM student";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Student s = new Student();
                s.setStudentId(rs.getInt("student_id"));
                s.setUserId(rs.getInt("user_id"));
                s.setSno(rs.getString("sno"));
                s.setName(rs.getString("name"));
                s.setGender(rs.getString("gender"));
                s.setClassName(rs.getString("class_name"));
                s.setRoomId(rs.getInt("room_id"));
                s.setPhone(rs.getString("phone"));
                list.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, stmt, conn);
        }
        return list;
    }

    // 添加学生信息
    public boolean addStudent(Student student) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean isSuccess = false;

        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO student (user_id, sno, name, gender, class_name, phone) VALUES (?, ?, ?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, student.getUserId());
            stmt.setString(2, student.getSno());
            stmt.setString(3, student.getName());
            stmt.setString(4, student.getGender());
            stmt.setString(5, student.getClassName());
            stmt.setString(6, student.getPhone());

            int rows = stmt.executeUpdate();
            if (rows > 0) isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(null, stmt, conn);
        }
        return isSuccess;
    }
    public boolean delete(int studentId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean isSuccess = false;
        try {
            conn = DBUtil.getConnection();
            // 注意：因为有外键约束，严格来说应该先删 sys_user 里的账号，再删 student
            // 但如果我们在数据库建表时设置了 ON DELETE CASCADE (级联删除)，删主表会自动删从表
            // 这里我们先只删 student，如果报错，说明我们要先查出 userId 删 user 表
            String sql = "DELETE FROM student WHERE student_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, studentId);

            int rows = stmt.executeUpdate();
            if (rows > 0) isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(null, stmt, conn);
        }
        return isSuccess;
    }
    // 1. 根据 ID 查询单个学生（用于修改页面回显）
    public Student findById(int studentId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Student s = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM student WHERE student_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, studentId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                s = new Student();
                s.setStudentId(rs.getInt("student_id"));
                s.setSno(rs.getString("sno"));
                s.setName(rs.getString("name"));
                s.setGender(rs.getString("gender"));
                s.setClassName(rs.getString("class_name"));
                s.setPhone(rs.getString("phone"));
                // 注意：userId 和 roomId 这里暂时不用改，也可以查出来备用
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, stmt, conn);
        }
        return s;
    }

    // 2. 更新学生信息
    public boolean update(Student s) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean isSuccess = false;
        try {
            conn = DBUtil.getConnection();
            // 这里我们不修改学号(sno)，因为它是关联账号，改起来比较麻烦，暂时只改基本信息
            String sql = "UPDATE student SET name=?, gender=?, class_name=?, phone=? WHERE student_id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, s.getName());
            stmt.setString(2, s.getGender());
            stmt.setString(3, s.getClassName());
            stmt.setString(4, s.getPhone());
            stmt.setInt(5, s.getStudentId()); // 别忘了最后这个 WHERE 条件

            int rows = stmt.executeUpdate();
            if (rows > 0) isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(null, stmt, conn);
        }
        return isSuccess;
    }
}