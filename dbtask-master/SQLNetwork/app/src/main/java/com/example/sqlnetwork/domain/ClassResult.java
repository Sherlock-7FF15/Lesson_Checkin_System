package com.example.sqlnetwork.domain;

import java.io.Serializable;
import java.util.List;

public class ClassResult {
    private String code;
    private String message;
    private MyData data;

    public ClassResult(String code, String message, MyData data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MyData getData() {
        return data;
    }

    public void setData(MyData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ClassResult{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public class MyData{
        private List<Class> classInfo;
        private Teacher teacher;

        public MyData(List<Class> classList, Teacher teacher) {
            this.classInfo = classList;
            this.teacher = teacher;
        }

        public List<Class> getClassList() {
            return classInfo;
        }

        public void setClassList(List<Class> classList) {
            this.classInfo = classList;
        }

        public Teacher getTeacher() {
            return teacher;
        }

        public void setTeacher(Teacher teacher) {
            this.teacher = teacher;
        }

        @Override
        public String toString() {
            return "MyData{" +
                    "classList=" + classInfo +
                    ", teacher=" + teacher +
                    '}';
        }
    }

    public class Teacher {
        private String tid;
        private String pwd;
        private String name;
        private String info;

        public Teacher(String tid, String pwd, String name, String info) {
            this.tid = tid;
            this.pwd = pwd;
            this.name = name;
            this.info = info;
        }

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
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

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "Teacher{" +
                    "tid='" + tid + '\'' +
                    ", pwd='" + pwd + '\'' +
                    ", name='" + name + '\'' +
                    ", info='" + info + '\'' +
                    '}';
        }
    }

    public class Class {
        private String cid;
        private String className;
        private String teacher;
        private String time;
        private String info;

        public Class(String cid, String className, String teacher, String time, String info) {
            this.cid = cid;
            this.className = className;
            this.teacher = teacher;
            this.time = time;
            this.info = info;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getTeacher() {
            return teacher;
        }

        public void setTeacher(String teacher) {
            this.teacher = teacher;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        @Override
        public String toString() {
            return "Class{" +
                    "cid='" + cid + '\'' +
                    ", className='" + className + '\'' +
                    ", teacher='" + teacher + '\'' +
                    ", time='" + time + '\'' +
                    ", info='" + info + '\'' +
                    '}';
        }
    }


}
