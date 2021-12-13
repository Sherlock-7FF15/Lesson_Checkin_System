package com.liujiayi.clasip.service.Impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liujiayi.clasip.dao.ClassDao;
import com.liujiayi.clasip.dao.ClassTeacherDao;
import com.liujiayi.clasip.dao.StudentDao;
import com.liujiayi.clasip.dao.TeacherDao;
import com.liujiayi.clasip.pojo.Class;
import com.liujiayi.clasip.pojo.Student;
import com.liujiayi.clasip.pojo.Teacher;
import com.liujiayi.clasip.pojo.association.TeacherClass;
import com.liujiayi.clasip.pojo.Token;
import com.liujiayi.clasip.service.AdminService;
import com.liujiayi.clasip.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘斯昊
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    ClassDao classDao;

    @Autowired
    ClassTeacherDao classTeacherDao;

    @Autowired
    TeacherDao teacherDao;

    @Autowired
    StudentDao studentDao;

    @Override
    public List<Class> getAllClass() {
        return classDao.selectList(null);
    }

    @Override
    public Teacher getTeacherByCid(String cid) {
        QueryWrapper<TeacherClass> classTeacherQueryWrapper = new QueryWrapper<>();
        classTeacherQueryWrapper.eq(Constants.CLASS_ID,cid);
        TeacherClass classTeacher = classTeacherDao.selectOne(classTeacherQueryWrapper);
        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.eq("tid",classTeacher.getTid());
        Teacher teacher = teacherDao.selectOne(teacherQueryWrapper);
        return teacher;
    }

    @Override
    public int deleteClassByCid(String cid) {
        QueryWrapper<Class> classQueryWrapper = new QueryWrapper<>();
        classQueryWrapper.eq(Constants.CLASS_ID,cid);
        int delete = classDao.delete(classQueryWrapper);

        return delete;
    }

    @Override
    public int addStudent(String student) {
        Student addStudent = JSON.parseObject(student, Student.class);
        int insert = studentDao.insert(addStudent);
        return insert;
    }

    @Override
    public int deleteStudent(String sid) {
        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.eq(Constants.STUDENT_ID,sid);
        int delete = studentDao.delete(studentQueryWrapper);
        return delete;
    }

    @Override
    public Boolean login(String token) {
        Token token1 = JSON.parseObject(token, Token.class);
        if(Constants.ADMIN_ACCOUNT.equals(token1.getAccount())&&Constants.ADMIN_PASSWORD.equals(token1.getPassword())){
            return true;
        }
        return false;
    }

    @Override
    public List<Class> getAllClassByTid(String tid) {
        QueryWrapper<TeacherClass> teacherClassQueryWrapper = new QueryWrapper<>();
        teacherClassQueryWrapper.eq(Constants.TEACHER_ID,tid);
        List<TeacherClass> teacherClasses = classTeacherDao.selectList(teacherClassQueryWrapper);
        ArrayList<Class> classes = new ArrayList<>();
        for (TeacherClass teacherClass:teacherClasses) {
            QueryWrapper<Class> classQueryWrapper = new QueryWrapper<>();
            classQueryWrapper.eq(Constants.CLASS_ID,teacherClass.getCid());
            Class aClass = classDao.selectOne(classQueryWrapper);
            classes.add(aClass);
        }
        return classes;
    }

    @Override
    public Teacher getTeacherByTid(String tid) {
        Teacher teacher = teacherDao.selectById(tid);
        return teacher;
    }

    @Override
    public int addTeacher(Teacher teacher) {
        int insert = teacherDao.insert(teacher);
        return insert;
    }

    @Override
    public int addClass(Class addClass) {
        int insert = classDao.insert(addClass);
        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
        teacherQueryWrapper.eq("name",addClass.getTeacher());
        Teacher teacher = teacherDao.selectOne(teacherQueryWrapper);
        int insert1 = classTeacherDao.insert(new TeacherClass(teacher.getTid(), addClass.getCid()));
        return insert>0&&insert1>0 ? 1 : 0;
    }


}
