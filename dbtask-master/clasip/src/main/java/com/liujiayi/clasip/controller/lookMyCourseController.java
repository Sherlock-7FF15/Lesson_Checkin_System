package com.liujiayi.clasip.controller;

import com.liujiayi.clasip.dao.ClassStudentDao;
import com.liujiayi.clasip.pojo.Class;
import com.liujiayi.clasip.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 创建时间: 2020-12-12 23:24
 * 文件备注:
 * 编写人员: 杨伯益
 */

@Controller
public class lookMyCourseController {

    @Autowired
    ClassStudentDao classStudentDao;


    @ResponseBody
    @GetMapping("/mycourse/{sid}")
    public Object getMyClass(@PathVariable("sid")String sid){
        //int mysid = Integer.valueOf(sid);
        List<Class> myClass = classStudentDao.getMyClass(sid);
        String str = "";
        for(Class c : myClass){
            str+=c.getClassName();
            str+="\n";
        }
        return Result.successs2(str);
    }

}
