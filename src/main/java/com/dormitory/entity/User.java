package com.dormitory.entity;

public class User {
    private Integer userId;
    private String username;
    private String password;
    private Integer role; // 0-管理员, 1-宿管, 2-学生

    public User() {}

    public User(Integer userId, String username, String password, Integer role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // --- Getter 和 Setter 方法 ---
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Integer getRole() { return role; }
    public void setRole(Integer role) { this.role = role; }
}