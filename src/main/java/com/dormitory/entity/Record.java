package com.dormitory.entity;

public class Record {
    private Integer recordId;
    private Integer studentId;
    private String studentName; // 数据库没这列，但我们需要显示
    private String sno;         // 数据库没这列，但我们需要显示
    private String time;        // 存格式化后的时间字符串
    private String type;
    private String remark;

    // --- Getter / Setter ---
    public Integer getRecordId() { return recordId; }
    public void setRecordId(Integer recordId) { this.recordId = recordId; }
    public Integer getStudentId() { return studentId; }
    public void setStudentId(Integer studentId) { this.studentId = studentId; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public String getSno() { return sno; }
    public void setSno(String sno) { this.sno = sno; }
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getRemark() { return remark; }
    public void setRemark(String remark) { this.remark = remark; }
}