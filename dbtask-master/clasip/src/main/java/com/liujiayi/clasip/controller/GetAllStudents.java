package com.liujiayi.clasip.controller;

import com.liujiayi.clasip.dao.StudentDao;
import com.liujiayi.clasip.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * 创建时间: 2020-10-12 17:07
 * 文件备注:
 * 编写人员: 杨伯益
 */

@Controller
public class GetAllStudents {

    @Autowired
    StudentDao studentDao;

    @GetMapping("/allstudents")
    @ResponseBody
    public Object test1(){
        return Result.successs(studentDao.selectList(null));
    }


}
