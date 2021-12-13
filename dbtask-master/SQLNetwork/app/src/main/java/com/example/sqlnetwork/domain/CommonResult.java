package com.example.sqlnetwork.domain;

public class CommonResult {
    /**
     * code
     * 业务代码
     */
    private String code;
    /**
     * message
     * 请求返回消息
     */
    private String message;
    /**
     * data
     * 返回数据
     */
    private Object data;

    public CommonResult(String code, String message, Object data) {
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
