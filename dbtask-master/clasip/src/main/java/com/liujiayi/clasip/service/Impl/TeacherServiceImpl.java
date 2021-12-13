package com.liujiayi.clasip.service.Impl;

import com.liujiayi.clasip.dao.TeacherDao;
import com.liujiayi.clasip.pojo.Teacher;
import com.liujiayi.clasip.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘斯昊
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherDao teacherDao;

    @Override
    public List<Teacher> getAllTeacher() {
        return teacherDao.selectList(null);
    }

    @Override
    public Teacher getTeacherById(String id) {
        return teacherDao.selectById(id);
    }
}
