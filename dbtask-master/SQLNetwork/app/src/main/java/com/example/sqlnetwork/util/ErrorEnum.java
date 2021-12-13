package com.example.sqlnetwork.util;


/**
 * 错误信息类
 * @author 刘斯昊
 */
public enum ErrorEnum {
    /*
     * 错误信息
     * */
    E_400("400", "请求处理异常，请稍后再试"),
    E_401("401","登录失败"),
    E_500("500", "请求方式有误,请检查 GET/POST"),
    E_501("501", "请求路径不存在"),

    E_10009("10009", "账户已存在"),
    E_10008("10008","主键重复"),

    E_20011("20011", "登陆已过期,请重新登陆"),

    E_90003("90003", "缺少必填参数"),
    E_90004("90004","输入参数对应数据不存在");

    private String errorCode;

    private String errorMsg;

    ErrorEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

}
