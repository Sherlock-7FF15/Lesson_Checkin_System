package com.liujiayi.clasip.pojo.association;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Repository
@TableName("OpenClass")
public class OpenClass {
    private String tid;
    private String cid;
    private Integer version;
    private Timestamp startTime;
}
