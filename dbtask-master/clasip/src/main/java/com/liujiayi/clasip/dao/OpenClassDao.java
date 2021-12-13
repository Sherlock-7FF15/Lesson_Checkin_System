package com.liujiayi.clasip.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liujiayi.clasip.pojo.association.OpenClass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 创建时间: 2020-11-22 16:52
 * 文件备注:
 * 编写人员: 杨伯益
 */

@Repository
@Mapper
public interface OpenClassDao extends BaseMapper<OpenClass> {

    @Select("select * from OpenClass where cid=#{cid} order by start_time desc")
    public List<OpenClass> getSign(@Param("cid")String cid);

}
