package com.liujiayi.clasip.controller;

import com.liujiayi.clasip.dao.ClassStudentDao;
import com.liujiayi.clasip.dao.SignUpDao;
import com.liujiayi.clasip.dao.StudentDao;
import com.liujiayi.clasip.pojo.Class;
import com.liujiayi.clasip.pojo.SignUp;
import com.liujiayi.clasip.pojo.Student;
import com.liujiayi.clasip.pojo.association.ClassStudent;
import com.liujiayi.clasip.pojo.association.OpenClass;
import com.liujiayi.clasip.service.StudentService;
import com.liujiayi.clasip.util.Constants;
import com.liujiayi.clasip.util.ErrorEnum;
import com.liujiayi.clasip.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Vector;

/**
 * @author 刘斯昊
 * @date 2020/10/15
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;


    @Autowired
    StudentDao studentDao;

    @Autowired
    ClassStudentDao classStudentDao;

    @Autowired
    SignUpDao signUpDao;

    @ResponseBody
    @GetMapping("/getSignedStus/{version}")
    public Object signed(@PathVariable("version") String version){
//        boolean code = studentService.login(token);
        List<Student> studentList = signUpDao.getSigned(version);

        if(studentList.size()==0){
            return Result.failure(ErrorEnum.E_90004);
        }else{
            return Result.successs2(studentList);
        }
    }








    /**
     * 登录方法，post请求，接受前端传来的token
     * @param token   学生账号密码
     * @return 返回登录信息
     */

    @ResponseBody
    @PostMapping("/login")
    public Object login(@RequestBody String token){
        boolean code = studentService.login(token);
        if(code){
            return Result.successs(Constants.LOGIN_SUCCESS);
        }else{
            return Result.failure(ErrorEnum.E_401);
        }
    }

    /**
     * 学生端注册方法
     * @param studentInfo 学生信息
     * @return 返回注册信息
     */
    @ResponseBody
    @PostMapping("/register")
    public Object register(@RequestBody String studentInfo){
        System.out.println(studentInfo);
        boolean code = studentService.register(studentInfo);
        if(code){
            return Result.successs(Constants.REGISTER_SUCCESS);
        }else{
            return Result.failure(ErrorEnum.E_10009);
        }
    }


    /**
     * 根据学号获取学生信息
     * @param sid   学号
     * @return  学生信息
     */
    @ResponseBody
    @GetMapping("/getStudentBySid/{sid}")
    public Object getStudentBySid(@PathVariable("sid") String sid){
        Student studentBySid = studentService.getStudentBySid(sid);
        return Result.successs(studentBySid);
    }

    /**
     * 获取某课程的所有学生信息列表
     * @param cid 课程id
     * @return  某课堂的所有学生信息
     */
//    @ResponseBody
//    @GetMapping("getAllStudentByCid/{cid}")
//    public Object getAllStudentByCid(@PathVariable("cid") String cid){
//        List<Student> allStudentByCid = studentService.getAllStudentByCid(cid);
//        return Result.successs(allStudentByCid);
//    }

    @ResponseBody
    @GetMapping("getAllStudentByCid/{cid}")
    public Object getAllStudentByCid(@PathVariable("cid") String cid){
//        List<Student> allStudentByCid = studentService.getAllStudentByCid(cid);
        List<Student> allStudentByCid = classStudentDao.getAllSTus(cid);
        if(allStudentByCid.size()==0){
            return Result.failure(ErrorEnum.E_90004);
        }
        return Result.successs(allStudentByCid);

    }

    /**
     * 加入课程方法
     * @param sid   学生学号
     * @param cid     课程号
     * @return  加入课程消息
     */

    @ResponseBody
    @GetMapping("/addStudentToClass/{sid}/{cid}")
    public Object addStudentToClass(@PathVariable("sid") String sid,@PathVariable("cid") String cid){
        boolean code = studentService.addStudentToClass(sid, cid);
        if(code == true){
            return Result.successs("加入课程成功！");
        }else{
            return Result.failure(Constants.SUCCESS_CODE_MSG,"加入课程失败，请检查课程码是否正确！");
        }
    }


    /**
     * 获取某学生的所有课程
     * @param sid   学号
     * @return  所有课程列表
     */
    @ResponseBody
    @GetMapping("/getAllClassBySid/{sid}")
    public Object getAllClassBySid(@PathVariable("sid") String sid){
        List<Class> allClass = studentService.getAllClassBySid(sid);
        return Result.successs(allClass);
    }

    /**
     * 根据某一课程id获取课程消息
     * @param cid   课程id
     * @return  课程信息
     */
    @ResponseBody
    @GetMapping("/getClassInfoByCid/{cid}")
    public Object getClassInfoByCid(@PathVariable("cid") String cid){
        Class classByCid = studentService.getClassByCid(cid);
        return Result.successs(classByCid);
    }



    @ResponseBody
    @GetMapping("/signUp/{sid}/{cid}/{version}/{lng}/{lat}")
    public Object signUp(@PathVariable("sid") String sid, @PathVariable("cid") String cid, @PathVariable("version")Integer version,@PathVariable("lng")Double lng,@PathVariable("lat")Double lat){
        Timestamp tm = new Timestamp(System.currentTimeMillis());
        SignUp signUp = new SignUp(sid,cid,version,tm,lng,lat);
        boolean b = studentService.signUp(signUp);
        return b?Result.successs("签到成功"):Result.failure("203","签到失败");
    }

    @ResponseBody
    @GetMapping("/signUp2/{sid}/{cid}/{version}")
    public Object signUp2(@PathVariable("sid") String sid, @PathVariable("cid") String cid, @PathVariable("version")Integer version){
        Timestamp tm = new Timestamp(System.currentTimeMillis());
        SignUp signUp = new SignUp(sid,cid,version,tm,null,null);
        boolean b = studentService.signUp(signUp);
        return b?Result.successs("签到成功"):Result.failure("203","签到失败");
    }

    @ResponseBody
    @GetMapping("getSign/{cid}")
    public Object getSign(@PathVariable("cid") String cid){
        List<OpenClass> sign = studentService.getSign(cid);
        return Result.successs(sign);
    }

    @ResponseBody
    @GetMapping("/delete/{sid}/{cid}")
    public Object delete(@PathVariable("sid") String sid,@PathVariable("cid") String cid){
        studentDao.tuike(sid,cid);
        return Result.successs("退课成功！");
    }


}
