package com.liujiayi.clasip.service.Impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liujiayi.clasip.dao.*;
import com.liujiayi.clasip.pojo.*;
import com.liujiayi.clasip.pojo.Class;
import com.liujiayi.clasip.pojo.association.ClassStudent;
import com.liujiayi.clasip.pojo.association.OpenClass;
import com.liujiayi.clasip.service.StudentService;
import com.liujiayi.clasip.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 刘斯昊
 * @date 2020/10/15
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired StudentDao studentDao;
    @Autowired ClassDao classDao;
    @Autowired ClassStudentDao classStudentDao;
    @Autowired SignUpDao signUpDao;
    @Autowired
    OpenClassDao openClassDao;

    @Override
    public List<Student> getAllStudentByCid(String cid) {
        QueryWrapper<ClassStudent> classStudentQueryWrapper = new QueryWrapper<>();
        classStudentQueryWrapper.eq("cid",cid);
        List<ClassStudent> classStudents = classStudentDao.selectList(classStudentQueryWrapper);
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();

        for (ClassStudent classStudent :
                classStudents) {
            studentQueryWrapper.eq("sid",classStudent.getSid()).or();
        }

        List<Student> students = studentDao.selectList(studentQueryWrapper);
        return students;
    }

    @Override
    public Student getStudentBySid(String sid) {
        return studentDao.selectById(sid);
    }

    @Override
    public boolean login(String token) {
        //缺少参数验证
        Token studentToken = JSON.parseObject(token, Token.class);
        HashMap<String, Object> condition = new HashMap<>();
        condition.put(Constants.STUDENT_ID,studentToken.getAccount());

        List<Student> students = studentDao.selectByMap(condition);


        return studentToken.getPassword().equals(students.get(0).getPwd());
    }

    @Override
    public boolean addStudentToClass(String sid, String cid) {
        HashMap<String, Object> conditions = new HashMap<>();
        conditions.put(Constants.CLASS_ID,cid);
        List<Class> classes = classDao.selectByMap(conditions);
        Student student = studentDao.selectById(sid);
        if(student == null){
            return false;
        }
        int i = classStudentDao.insert(new ClassStudent(classes.get(0).getCid(), sid));
        return i>0;
    }

    @Override
    public boolean register(String studentInfo) {
        //缺少参数验证
        Student student = JSON.parseObject(studentInfo, Student.class);
        int insert = studentDao.insert(student);
        return insert>0;
    }

    @Override
    public List<Class> getAllClassBySid(String sid) {
        HashMap<String, Object> conditions = new HashMap<>();
        conditions.put(Constants.STUDENT_ID,sid);
        List<ClassStudent> classStudents = classStudentDao.selectByMap(conditions);
        ArrayList<String> cids = new ArrayList<>();
        for(ClassStudent classStudent : classStudents){
            cids.add(classStudent.getCid());
        }
        QueryWrapper<Class> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(Constants.CLASS_ID,cids);
        List<Class> classes = classDao.selectList(queryWrapper);
        return classes;
    }

    @Override
    public Class getClassByCid(String cid) {
        QueryWrapper<Class> classQueryWrapper = new QueryWrapper<>();
        classQueryWrapper.eq(Constants.CLASS_ID,cid);
        Class aClass = classDao.selectOne(classQueryWrapper);
        return aClass;
    }

    @Override
    public boolean signUp(SignUp signUp) {
        int insert = signUpDao.insert(signUp);
        return insert>0;
    }

    @Override
    public List<OpenClass> getSign(String cid) {
        QueryWrapper<OpenClass> openClassQueryWrapper = new QueryWrapper<>();
        openClassQueryWrapper.eq("cid",cid);
        List<OpenClass> openClasses = openClassDao.selectList(openClassQueryWrapper);
        return openClasses;
    }


}

