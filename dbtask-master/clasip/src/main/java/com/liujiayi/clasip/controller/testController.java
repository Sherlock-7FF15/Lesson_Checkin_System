package com.liujiayi.clasip.controller;

import com.liujiayi.clasip.dao.TeacherDao;
import com.liujiayi.clasip.pojo.Teacher;
import com.liujiayi.clasip.util.Result;
//import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 创建时间: 2020-12-08 21:40
 * 文件备注:
 * 编写人员: 杨伯益
 */

@Controller
public class testController {

    @Autowired
    TeacherDao teacherDao;

    @ResponseBody
    @GetMapping("/test")
    public Object test1(){

        return Result.successs2("这是一段测试文字~");
    }


    @ResponseBody
    @GetMapping("/test2/{str}")
    public Object test1(@PathVariable("str")String str){


        return Result.successs2("这是你请求发过来的的内容： "+str);
    }



//    @ResponseBody
    @RequestMapping("/addtea2")
    public ModelAndView test1 ( String tid,String name,String pwd,String  info, ModelAndView modelAndView){
//        String tid,name,pwd,info;
//        tid = req.getParameter("tid");
//        name = req.getParameter("name");
//        pwd = req.getParameter("pwd");
//        info = req.getParameter("info");
        String[] aaa = tid.split(",");
        System.out.println(name);
        if(tid==null){
            modelAndView.setViewName("err.html");
            return modelAndView;
        }
        Teacher t1 = new Teacher();
        t1.setInfo(aaa[3]);
        t1.setName(aaa[1]);
        t1.setPwd(aaa[2]);
        t1.setTid(aaa[0]);
        System.out.println(t1.toString());
        teacherDao.insert(t1);
        modelAndView.setViewName("OK.html");
        return modelAndView;
    }

    @GetMapping("/index")
    public ModelAndView index(ModelAndView modelAndView){
        modelAndView.setViewName("index.html");
        return modelAndView;
    }

    @GetMapping("/")
    public ModelAndView index2(ModelAndView modelAndView){
        modelAndView.setViewName("index.html");
        return modelAndView;
    }

    @GetMapping("/addtea")
    public ModelAndView addtea(ModelAndView modelAndView){
        modelAndView.setViewName("addtea.html");
        return modelAndView;
    }

    @GetMapping("/upcsv1")
    public ModelAndView upcsv1(ModelAndView modelAndView){
        modelAndView.setViewName("test.html");
        return modelAndView;
    }

    @GetMapping("/upcsv2")
    public ModelAndView upcsv2(ModelAndView modelAndView){
        modelAndView.setViewName("test2.html");
        return modelAndView;
    }
}
