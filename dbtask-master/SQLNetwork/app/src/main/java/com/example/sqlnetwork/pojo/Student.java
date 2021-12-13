package com.example.sqlnetwork.pojo;

public class Student {
    /**
     * sid
     * 数据库主键 学生学号以及登录账号
     */
    String sid;

    String pwd;
    String name;
    String major;
    String grade;

    public Student(String sid, String pwd, String name, String major, String grade) {
        this.sid = sid;
        this.pwd = pwd;
        this.name = name;
        this.major = major;
        this.grade = grade;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
