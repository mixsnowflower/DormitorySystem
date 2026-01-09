package com.dormitory.dao;

import com.dormitory.entity.Record;
import com.dormitory.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RecordDao {

    // 查所有记录 (关联学生表)
    public List<Record> findAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Record> list = new ArrayList<>();
        try {
            conn = DBUtil.getConnection();
            // 联表查询：把学生的名字和学号也查出来
            String sql = "SELECT r.*, s.name, s.sno " +
                    "FROM record r JOIN student s ON r.student_id = s.student_id " +
                    "ORDER BY r.record_date DESC"; // 按时间倒序，最新的在上面
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Record r = new Record();
                r.setRecordId(rs.getInt("record_id"));
                r.setStudentId(rs.getInt("student_id"));
                r.setStudentName(rs.getString("name")); // 从 student 表拿的
                r.setSno(rs.getString("sno"));         // 从 student 表拿的
                // 截取时间字符串，去掉毫秒
                String t = rs.getString("record_date");
                r.setTime(t != null && t.length() > 19 ? t.substring(0, 19) : t);
                r.setType(rs.getString("type"));
                r.setRemark(rs.getString("remark"));
                list.add(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, stmt, conn);
        }
        return list;
    }

    // 添加记录 (模拟打卡)
    public boolean add(Record r) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO record (student_id, type, remark) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, r.getStudentId());
            stmt.setString(2, r.getType());
            stmt.setString(3, r.getRemark());
            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            DBUtil.close(null, stmt, conn);
        }
    }
}