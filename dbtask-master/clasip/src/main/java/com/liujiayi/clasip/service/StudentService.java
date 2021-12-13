package com.liujiayi.clasip.service;

import com.liujiayi.clasip.pojo.Class;
import com.liujiayi.clasip.pojo.SignUp;
import com.liujiayi.clasip.pojo.Student;
import com.liujiayi.clasip.pojo.association.OpenClass;

import java.util.List;

/**
 * @author 刘斯昊
 * @date 2020/10/15
 */
public interface StudentService {

    /**
     * 获取某个班级的所有学生
     * @param cid   班级id
     * @return 学生列表
     */
    List<Student> getAllStudentByCid(String cid);

    /**
     * 根据学号获取指定学生
     * @param sid   学生学号
     * @return 某特定学生
     */
    Student getStudentBySid(String sid);

    /**
     * 学生端登录方法
     * @param token 学生学号、密码
     * @return  登录信息
     */
    boolean login(String token);

    /**
     * 学生加入课程方法
     * @param sid 学生学号
     * @param cid 课程号
     * @return  登录信息
     */
    boolean addStudentToClass(String sid, String cid);

    /**
     * 学生端注册方法
     * @param studentInfo 学生信息
     * @return  登录信息
     */
    boolean register(String studentInfo);

    /**
     * 学生端获取已加入的所有课程列表
     * @param sid   学号
     * @return  所有的课程
     */
    List<Class> getAllClassBySid(String sid);

    /**
     * 学生端获取某个课程信息
     * @param cid 课程id
     * @return  返回课程信息
     */
    Class getClassByCid(String cid);

    /**
     * 学生端签到接口
     * @param signUp 签到信息
     * @return  返回签到成功信息
     */
    boolean signUp(SignUp signUp);

    /**
     * 获取进行中的签到接口
     * @param cid 课程号
     * @return 返回签到实体
     */
    List<OpenClass> getSign(String cid);
}
