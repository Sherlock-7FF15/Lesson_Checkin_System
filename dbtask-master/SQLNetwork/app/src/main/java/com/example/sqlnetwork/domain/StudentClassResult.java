package com.example.sqlnetwork.domain;

import java.util.List;

public class StudentClassResult {
    private String code;
    private String message;
    private List<ClassResult.Class> data;

    public StudentClassResult(String code, String message, List<ClassResult.Class> data) {
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

    public List<ClassResult.Class> getData() {
        return data;
    }

    public void setData(List<ClassResult.Class> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "StudentClassResult{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
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
