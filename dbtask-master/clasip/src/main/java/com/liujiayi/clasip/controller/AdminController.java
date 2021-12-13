package com.liujiayi.clasip.controller;

import com.liujiayi.clasip.dao.ClassDao;
import com.liujiayi.clasip.dao.ClassStudentDao;
import com.liujiayi.clasip.pojo.Class;
import com.liujiayi.clasip.pojo.Student;
import com.liujiayi.clasip.pojo.Teacher;
import com.liujiayi.clasip.pojo.association.ClassStudent;
import com.liujiayi.clasip.service.AdminService;
import com.liujiayi.clasip.service.StudentService;
import com.liujiayi.clasip.service.TeacherService;
import com.liujiayi.clasip.util.Constants;
import com.liujiayi.clasip.util.ErrorEnum;
import com.liujiayi.clasip.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * web端管理员接口
 * @author 刘斯昊
 * @date 2020/10/21
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    StudentService studentService;

    @Autowired
    AdminService adminService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    ClassDao classDao;

    @Autowired
    ClassStudentDao classStudentDao;

    /**
     * 获取所有课程
     * @return 课程列表
     */
    @GetMapping("/getAllClass")
    public Object getAllClass(){

        List<Class> allClass = adminService.getAllClass();
        if(allClass==null || allClass.isEmpty()){
            return Result.failure(Constants.SUCCESS_CODE_MSG,Constants.THERE_IS_NOCLASS,allClass);
        }
        return Result.successs(adminService.getAllClass());
    }

    /**
     * 根据课程号获取所有课程信息，包括任课教师，所有学生
     * @param cid 课程号
     * @return  课程详细信息
     */
    @GetMapping("/getClassInfoByCid/{cid}")
    public Object getClassInfoByCid(@PathVariable("cid") String cid){
        Class classByCid = studentService.getClassByCid(cid);
        System.out.println(classByCid.getCid());
        if(classByCid == null){
            return Result.failure(ErrorEnum.E_90004);
        }
        Teacher teacherByCid = adminService.getTeacherByCid(cid);
        System.out.println(teacherByCid.getTid());
        if (teacherByCid.getTid() == null) {
            return Result.failure(ErrorEnum.E_90004);
        }
        List<Student> studentList = classDao.cidToStus(cid);
        HashMap<String, Object> result = new HashMap<>();
        result.put(Constants.STUDENTS,studentList);
        result.put(Constants.TEACHER,teacherByCid);
        result.put(Constants.CLASS_INFO,classByCid);

        return Result.successs(result);
    }

    /**
     * 获取所有的教师列表
     * @return 教师列表
     */
    @GetMapping("/getAllTeacher")
    public Object getAllTeacher(){
        return Result.successs(teacherService.getAllTeacher());
    }

    /**
     * 根据cid删除课程
     * @param cid 课程id
     * @return 删除状态
     */
    @GetMapping("/deleteClassByCid/{cid}")
    public Object deleteClassByCid(@PathVariable("cid") String cid){
        int i = adminService.deleteClassByCid(cid);
        return i>0 ? Result.successs("删除成功"):Result.failure(ErrorEnum.E_90004);
    }

    /**
     * 添加学生
     * @param student 学生实体
     * @return 添加状态
     */
    @PostMapping("/addStudent")
    public Object addStudent(@RequestBody String student){
        int i = adminService.addStudent(student);
        return i>0 ? Result.successs("添加成功"):Result.failure(ErrorEnum.E_10009);
    }

    @GetMapping("/deleteStudent/{sid}")
    public Object deleteStudent(@PathVariable("sid") String sid){
        int i = adminService.deleteStudent(sid);
        return i>0 ? Result.successs("删除成功"):Result.failure(ErrorEnum.E_90004);
    }

    @GetMapping("/login/{id}/{pwd}")
    public Object login(@PathVariable("id") String id,@PathVariable("pwd") String pwd){
        if("admin".equals(id)&&"admin".equals(pwd)){
            return Result.successs(Constants.LOGIN_SUCCESS);
        }
        return Result.failure(ErrorEnum.E_401);
    }

    @GetMapping("/getAllClassByTid/{tid}")
    public Object getAllClassByTid(@PathVariable("tid") String tid){
        Teacher teacherByTid = adminService.getTeacherByTid(tid);
        if (teacherByTid == null){
            return Result.failure(ErrorEnum.E_90004);
        }
        List<Class> allClassByTid = adminService.getAllClassByTid(tid);
        HashMap<String, Object> resultMap = new HashMap<>(2);
        resultMap.put(Constants.TEACHER,teacherByTid);
        resultMap.put(Constants.CLASS_INFO,allClassByTid);
        return Result.successs(resultMap);
    }

    @GetMapping("/addTeacher/{tid}/{name}/{pwd}/{info}")
    public Object addTeacher(@PathVariable("tid")String tid,@PathVariable("name")String name,@PathVariable("pwd")String pwd,@PathVariable("info")String info){
        Teacher teacher = new Teacher(tid, name,pwd, info);
        int i = adminService.addTeacher(teacher);
        return i>0 ? Result.successs("添加成功"):Result.failure(ErrorEnum.E_10009);
    }


    //仿照这个
//    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping("/addStudentToClass4/{sid}/{cid}")
    public Object addStudentToClass3(@PathVariable("sid") String sid,@PathVariable("cid") String cid){
        classStudentDao.insert(new ClassStudent(sid,cid));
        return Result.successs("加入课程成功！");
    }




//    String cid;
//    String className;
//    String teacher;
//    String time;
//    String info;

    @GetMapping ("/addClass/{cid}/{className}/{teacher}/{time}/{info}")
    public Object addClass(@PathVariable("cid")String cid,@PathVariable("className")String className,@PathVariable("teacher")String teacher,@PathVariable("time")String time,@PathVariable("info")String info){
        Class aClass = new Class(cid, className, teacher, time, info);
        int i = adminService.addClass(aClass);
        return i>0 ? Result.successs("添加成功"):Result.failure(ErrorEnum.E_10009);
    }

}
