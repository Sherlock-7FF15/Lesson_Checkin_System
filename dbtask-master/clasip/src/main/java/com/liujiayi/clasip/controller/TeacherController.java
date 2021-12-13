package com.liujiayi.clasip.controller;

import com.liujiayi.clasip.dao.OpenClassDao;
import com.liujiayi.clasip.dao.TeacherDao;
import com.liujiayi.clasip.pojo.Class;
import com.liujiayi.clasip.pojo.Teacher;
import com.liujiayi.clasip.pojo.association.OpenClass;
import com.liujiayi.clasip.util.ErrorEnum;
import com.liujiayi.clasip.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

import java.util.Random;

/**
 * @author 刘斯昊
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    TeacherDao teacherDao;

    @Autowired
    OpenClassDao openClassDao;


    @ResponseBody
    @GetMapping("teacherlogin/{tid}/{pwd}")
    public Object teaLogin(@PathVariable("tid")String tid,@PathVariable("pwd")String pwd){
        List<Teacher> teachers = teacherDao.tealogin(tid,pwd);
        if(teachers.size()==1){
            return Result.successs2("登录成功！");
        }else {
            return Result.failure(ErrorEnum.E_401);
        }

    }


    @ResponseBody
    @GetMapping("search/{info}")
    public Object sear(@PathVariable("info")String info){
        info = "%"+info+"%";
        List<Teacher> teachers = teacherDao.search(info);
        if(teachers.size()==0){
            return Result.failure(ErrorEnum.E_90004);
        }else {
            return Result.successs2(teachers);
        }
    }


    @ResponseBody
    @GetMapping("/sign/{tid}/{cid}")
    public Object sign(@PathVariable("tid")String tid,@PathVariable("cid")String cid){
        Random rand = new Random();
        //生成签到码
        int version =rand.nextInt(999999 - 100000 + 1) + 100000;
//        LocalDateTime localDateTime = LocalDateTime.now();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        OpenClass openClass = new OpenClass(tid,cid,version,timestamp);
        openClassDao.insert(openClass);
        return Result.successs(String.valueOf(version));
    }


    @ResponseBody
    @GetMapping("/getsign2/{cid}")
    public Object gets2(@PathVariable("cid")String cid) throws ParseException {
        List<OpenClass> sign = openClassDao.getSign(cid);
        if(sign.size()==0){
            return Result.failure(ErrorEnum.E_90004);
        }else{
//            System.out.println(sign.get(0).getStartTime()+"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            //判断是否在十分钟内
            Timestamp tmSign = sign.get(0).getStartTime();
            Timestamp tmNow = new Timestamp(System.currentTimeMillis());
            System.out.println("当前时间："+tmNow.getTime());
            System.out.println("发签到时间："+tmSign.getTime());
            int a = Integer.valueOf((int) tmNow.getTime());
            int b = (int) tmSign.getTime();
            System.out.println("间隔了");
            System.out.println((a-b)/1000.0/60.0+"分钟");
            if(((a-b)/1000.0/60.0)>=10.0000){
                return Result.failure(ErrorEnum.E_999);
            }

            //判读多个签到，只取最新那个

            List<OpenClass> sign2 = openClassDao.getSign(cid);
            sign2.clear();
            sign2.add(sign.get(0));
            System.out.println(sign2.get(0).getVersion());
            //sign有签到
            return Result.successs2(sign2);
        }
    }





}