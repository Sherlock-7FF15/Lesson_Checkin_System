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
 *  班级实体类
 */
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Repository
@TableName("Class")
public class Class implements Serializable {
    @TableId("cid")
    String cid;
    String className;
    String teacher;
    String time;
    String info;

}
