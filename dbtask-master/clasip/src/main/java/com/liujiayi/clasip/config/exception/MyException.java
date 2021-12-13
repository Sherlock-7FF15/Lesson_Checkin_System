package com.liujiayi.clasip.config.exception;

import com.liujiayi.clasip.util.CommonUtil;
import com.liujiayi.clasip.util.ErrorEnum;

public class MyException extends RuntimeException {
    private Object result;

    MyException(ErrorEnum errorEnum){
        this.result = CommonUtil.errorJson(errorEnum);
    }

}
