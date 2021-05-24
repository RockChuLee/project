package com.imooc.project.dto;

import com.imooc.project.entity.Account;
import lombok.Data;

/**
 * @Description:
 * @Author: dh
 * @Date: 2021/5/24 9:56
 */
@Data
public class LoginDTO {

    /**
     * 重定向或者跳转的路径
     */
    private String path;

    /**
     * 错误提示信息
     */
    private String error;

    /**
     * 当前登录人的信息
     */
    private Account account;

}
