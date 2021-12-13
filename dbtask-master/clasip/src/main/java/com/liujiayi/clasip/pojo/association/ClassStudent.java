package com.liujiayi.clasip.pojo.association;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springframework.stereotype.Repository;

/**
 * 学生课程关联表
 * @author 刘斯昊
 * @date 2020/10/22
 */
@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
//@NoArgsConstructor
@Repository
@TableName("StudentClass")
public class ClassStudent {
    private String sid;
    private String cid;

    public ClassStudent() {
    }
}
