package com.liujiayi.clasip.controller;

import com.liujiayi.clasip.dao.ClassDao;
import com.liujiayi.clasip.dao.ClassStudentDao;
import com.liujiayi.clasip.pojo.Class;
import com.liujiayi.clasip.pojo.Student;
import com.liujiayi.clasip.pojo.association.ClassStudent;
import com.liujiayi.clasip.util.ErrorEnum;
import com.liujiayi.clasip.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 创建时间: 2020-12-13 14:18
 * 文件备注:
 * 编写人员: 杨伯益
 */

@Controller
public class ClassController {

    @Autowired
    ClassDao classDao;

    @Autowired
    ClassStudentDao classStudentDao;

    //搜索接口
    @ResponseBody
    @GetMapping("/search/{info}")
    public Object mysearch(@PathVariable("info")String info){
        List<Class> classList = classDao.mysearch("%"+info+"%");
        if(classList.size()==0){
            return Result.failure(ErrorEnum.E_90004);
        }else {
            return Result.successs2(classList);
        }
//        return classList;
    }


    //传入cid返回所有学生
    @ResponseBody
    @GetMapping("/cidToAllStus/{cid}")
    public Object getAll(@PathVariable("cid")String cid){
        List<Student> studentList = classDao.cidToStus(cid);
        if(studentList.size()==0){
            return Result.failure(ErrorEnum.E_90004);
        }else{
            return Result.successs2(studentList);
        }
    }

    //传入cid返回所有学生
    @ResponseBody
    @GetMapping("/cidToAllStus2/{cid}")
    public Object getAll2(@PathVariable("cid")String cid){
        List<Student> studentList = classDao.cidToStus(cid);
        if(studentList.size()==0){
            return Result.failure(ErrorEnum.E_90004);
        }else{
            return Result.successs2(studentList);
        }
    }

    @ResponseBody
    @GetMapping("/classes")
    public Object getAllClasses(){
        List<Class> aa = classDao.getAll();
        if(aa.size()==0){
            return Result.failure(ErrorEnum.E_90004);
        }else{
            return Result.successs2(aa);
        }
    }





    //测试接口
    @ResponseBody
    @GetMapping("/test2/{sid}/{cid}")
    public Object test2(@PathVariable("sid") String sid,@PathVariable("cid") String cid){
        //classStudentDao.insert(new ClassStudent(sid,cid));
        return Result.successs2(new ClassStudent(sid,cid));
    }


}
