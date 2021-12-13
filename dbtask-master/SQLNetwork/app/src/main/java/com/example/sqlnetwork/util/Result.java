package com.example.sqlnetwork.util;



/**
 * 统一结果返回
 * @author 刘斯昊
 * @date 2020/10/15
 */

public class Result {

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

    public Result(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result successs2(Object data){
        return new Result(Constants.SUCCESS_CODE,Constants.SUCCESS_MSG,data);
    }

    public static Result success(){
        return new Result(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG,null);
    }

    public static Result successs(String msg){
        return new Result(Constants.SUCCESS_CODE,msg,null);
    }

    public static Result successs(Object data){
        return new Result(Constants.SUCCESS_CODE, Constants.SUCCESS_MSG,data);
    }

    public static Result  failure(String code,String msg){
        return new Result(code,msg,null);
    }

    public static Result  failure(String code,String msg,Object data){
        return new Result(code,msg,data);
    }

    public static Result failure(ErrorEnum errorEnum){
        return new Result(errorEnum.getErrorCode(),errorEnum.getErrorMsg(),null);
    }

    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static Result failure(ErrorEnum errorEnum, Object data){
        return new Result(errorEnum.getErrorCode(),errorEnum.getErrorMsg(),data);
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
}
