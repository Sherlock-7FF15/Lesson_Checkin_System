package com.example.sqlnetwork.domain;

import java.time.LocalDateTime;
import java.util.List;

public class OpenClassResult {
    private String code;
    private String msg;
    private List<OpenClass> data;


    public OpenClassResult(String code, String msg, List<OpenClass> data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<OpenClass> getData() {
        return data;
    }

    public void setData(List<OpenClass> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "OpenClassResult{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public class OpenClass{
        private String tid;
        private String cid;
        private Integer version;
        private LocalDateTime start_time;

        public OpenClass(String tid, String cid, Integer version, LocalDateTime start_time) {
            this.tid = tid;
            this.cid = cid;
            this.version = version;
            this.start_time = start_time;
        }

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public Integer getVersion() {
            return version;
        }

        public void setVersion(Integer version) {
            this.version = version;
        }

        public LocalDateTime getStart_time() {
            return start_time;
        }

        public void setStart_time(LocalDateTime start_time) {
            this.start_time = start_time;
        }

        @Override
        public String toString() {
            return "OpenClass{" +
                    "tid='" + tid + '\'' +
                    ", cid='" + cid + '\'' +
                    ", version=" + version +
                    ", start_time=" + start_time +
                    '}';
        }
    }

}
