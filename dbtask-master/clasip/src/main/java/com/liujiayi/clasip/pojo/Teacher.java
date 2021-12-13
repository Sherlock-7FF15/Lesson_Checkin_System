package com.liujiayi.clasip.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @author 刘斯昊
 * @date 2020/10/15
 * 老师实体类
 */
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Repository
@TableName("Teacher")
public class Teacher implements Serializable {
    /**
     * tid
     * 数据库主键，自创账号
     */
    @TableId("tid")
    String tid;
    String pwd;
    String name;
    String info;

}
