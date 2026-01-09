package com.dormitory.dao;

import com.dormitory.entity.User;
import com.dormitory.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
    public User login(String username, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User user = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM sys_user WHERE username = ? AND password = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getInt("role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, stmt, conn);
        }
        return user;
    }
    public int addUser(User user) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int newId = -1;

        try {
            conn = DBUtil.getConnection();
            // RETURN_GENERATED_KEYS 是关键，告诉数据库我们要拿回自增 ID
            String sql = "INSERT INTO sys_user (username, password, role) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setInt(3, 2); // 默认为学生角色

            stmt.executeUpdate();

            // 获取数据库自动生成的 ID
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                newId = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, stmt, conn);
        }
        return newId;
    }
}