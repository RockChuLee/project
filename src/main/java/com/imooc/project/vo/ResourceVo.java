package com.imooc.project.vo;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @Author: dh
 * @Date: 2021/6/21 10:48
 */
@Data
public class ResourceVo {

    /**
     * 主键
     */
    private Long resourceId;

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 请求地址
     */
    private String url;

    /**
     * 下级资源
     */
    private List<ResourceVo> subs;

}
