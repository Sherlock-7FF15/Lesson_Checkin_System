package com.liujiayi.clasip.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author 刘斯昊
 * @date 2020/10/15
 * 签到表实体类
 */
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Repository
@TableName("SignUp")
public class SignUp implements Serializable {
    private String sid;
    private String cid;
    private Integer version;
    private Timestamp signupTime;
    Double lng;//经度
    Double lat;//纬度
}
