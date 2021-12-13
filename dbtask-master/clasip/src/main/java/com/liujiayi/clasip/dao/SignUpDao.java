package com.liujiayi.clasip.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liujiayi.clasip.pojo.SignUp;
import com.liujiayi.clasip.pojo.Student;
//import jdk.internal.net.http.common.SSLTube;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 创建时间: 2020-10-14 21:01
 * 文件备注:
 * 编写人员: 杨伯益
 */

@Mapper
@Repository
public interface SignUpDao extends BaseMapper<SignUp> {


    @Select("select a.* from clasip.Student a,clasip.SignUp b where a.sid=b.sid and b.version=#{version}")
    public List<Student> getSigned(@Param("version")String version);
}
