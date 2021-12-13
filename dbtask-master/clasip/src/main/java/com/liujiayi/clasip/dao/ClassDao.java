package com.liujiayi.clasip.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liujiayi.clasip.pojo.Class;
import com.liujiayi.clasip.pojo.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 创建时间: 2020-10-14 21:00
 * 文件备注:
 * 编写人员: 杨伯益
 */

@Mapper
@Repository
public interface ClassDao extends BaseMapper<Class> {

    //搜索接口
    @Select("select * from Class where cid like #{info} or class_name like #{info} or teacher like #{info} or time like #{info} or info like #{info}")
    public List<Class> mysearch(@Param("info")String info);

    @Select("select a.* from Student as a,StudentClass as b where a.sid=b.sid and b.cid=#{cid}")
    public List<Student> cidToStus(@Param("cid")String cid);

    @Select("select * from Class")
    public List<Class> getAll();

}
