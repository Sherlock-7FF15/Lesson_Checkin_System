package com.liujiayi.clasip.service;


import com.liujiayi.clasip.pojo.Class;
import com.liujiayi.clasip.pojo.Teacher;

import java.util.List;

/**
 * @author 刘斯昊
 */
public interface AdminService {

    /**
     * 获取所有课程列表
     * @return  课程列表
     */
    List<Class> getAllClass();

    /**
     * 获取课程对应的教师信息
     * @param cid 课程id
     * @return 教师实体类
     */
    Teacher getTeacherByCid(String cid);

    /**
     * 通过cid删除课程
     * @param cid 课程id
     * @return 删除状态
     */
    int deleteClassByCid(String cid);

    /**
     * 添加学生
     * @param student 学生实体
     * @return 添加状态
     */
    int addStudent(String student);

    /**
     * 删除学生
     * @param sid 学生id
     * @return 删除状态
     */
    int deleteStudent(String sid);

    /**
     * 管理端登录接口
     * @param token token
     * @return 登录状态
     */
    Boolean login(String token);

    /**
     * 通过tid获取所有的课程
     * @param tid 教工号
     * @return 所有的课程
     */
    List<Class> getAllClassByTid(String tid);

    /**
     * 通过tid获取教师
     * @param tid 教工号
     * @return 教师
     */
    Teacher getTeacherByTid(String tid);

    /**
     * 添加教师
     * @param teacher 教师实体
     * @return 返回添加状态
     */
    int addTeacher(Teacher teacher);

    /**
     * 添加课程
     * @param addClass 课程对象
     * @return 返回添加状态
     */
    int addClass(Class addClass);

}
