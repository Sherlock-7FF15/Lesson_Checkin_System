package com.liujiayi.clasip.pojo.association;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springframework.stereotype.Repository;

/**
 * 课程教师中间表
 * @author 刘斯昊
 * @date 2020/10/22
 */
@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Repository
@TableName("TeacherClass")
public class TeacherClass {
    private String tid;
    private String cid;
}
