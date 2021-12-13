package com.liujiayi.clasip.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liujiayi.clasip.pojo.association.TeacherClass;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author 刘斯昊
 */
@Mapper
@Repository
public interface ClassTeacherDao extends BaseMapper<TeacherClass> {
}
