package com.liujiayi.clasip.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liujiayi.clasip.pojo.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 创建时间: 2020-10-12 17:07
 * 文件备注:
 * 编写人员: 杨伯益
 */

@Repository
@Mapper
public interface StudentDao extends BaseMapper<Student> {

    @Select("select sid from Student")
    public List<String> selectSid();


    @Delete("delete from StudentClass where sid=#{sid} and cid=#{cid}")
    public void tuike(@Param("sid")String sid,@Param("cid")String cid);

}
