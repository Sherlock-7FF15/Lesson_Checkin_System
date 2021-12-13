package com.liujiayi.clasip.pojo;

import lombok.*;

import java.io.Serializable;

/**
 * @author 刘斯昊
 * @date 2020/10/20
 * 登录通用凭证
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Token implements Serializable {
    private String account;
    private String password;
}
