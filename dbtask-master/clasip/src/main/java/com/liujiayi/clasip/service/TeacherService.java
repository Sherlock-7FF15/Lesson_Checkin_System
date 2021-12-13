package com.liujiayi.clasip.service;

import com.liujiayi.clasip.pojo.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author 刘斯昊
 * @date 2020/11/9
 */
public interface TeacherService {
    /**
     * 获取所有教师
     * @return 教师列表
     */
    List<Teacher> getAllTeacher();

    /**
     * 根据教工号获取指定教师
     * @param id 教工号
     * @return  教师信息
     */
    Teacher getTeacherById(String id);
}
