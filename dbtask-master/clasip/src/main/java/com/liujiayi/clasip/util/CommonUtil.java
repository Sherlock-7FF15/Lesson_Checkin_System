package com.liujiayi.clasip.util;

import java.util.HashMap;
import java.util.Map;

public class CommonUtil {
    public static Object errorJson(ErrorEnum errorEnum) {
        Map<String, String> result = new HashMap<>();
        result.put(Constants.RESULT_CODE,errorEnum.getErrorCode());
        result.put(Constants.RESULT_MESSAGE,errorEnum.getErrorMsg());
        return result;
    }
}
