package com.example.sqlnetwork.domain;

import java.sql.Timestamp;
import java.util.List;

public class SignResult {
    private String code;
    private String message;
    private List<Sign> data;

    public SignResult(String code, String message, List<Sign> data) {
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

    public List<Sign> getData() {
        return data;
    }

    public void setData(List<Sign> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SignResult{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public class Sign {
        private String tid;
        private String cid;
        private Integer version;
        private String startTime;

        public Sign(String tid, String cid, Integer version, String startTime) {
            this.tid = tid;
            this.cid = cid;
            this.version = version;
            this.startTime = startTime;
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

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String start_time) {
            this.startTime = startTime;
        }

        @Override
        public String toString() {
            return "Sign{" +
                    "tid='" + tid + '\'' +
                    ", cid='" + cid + '\'' +
                    ", version=" + version +
                    ", start_time=" + startTime +
                    '}';
        }
    }
}
