package com.dormitory.entity;

public class Student {
    private Integer studentId;
    private Integer userId;
    private String sno;
    private String name;
    private String gender;
    private String className;
    private Integer roomId;
    private String phone;

    // 无参构造
    public Student() {}

    // 全参构造
    public Student(Integer studentId, Integer userId, String sno, String name, String gender, String className, Integer roomId, String phone) {
        this.studentId = studentId;
        this.userId = userId;
        this.sno = sno;
        this.name = name;
        this.gender = gender;
        this.className = className;
        this.roomId = roomId;
        this.phone = phone;
    }

    // --- Getter / Setter 方法 ---
    public Integer getStudentId() { return studentId; }
    public void setStudentId(Integer studentId) { this.studentId = studentId; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public String getSno() { return sno; }
    public void setSno(String sno) { this.sno = sno; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }
    public Integer getRoomId() { return roomId; }
    public void setRoomId(Integer roomId) { this.roomId = roomId; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}