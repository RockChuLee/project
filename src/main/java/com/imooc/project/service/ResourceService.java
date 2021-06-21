package com.imooc.project.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.imooc.project.entity.Resource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.project.vo.ResourceVo;

import java.util.List;

/**
 * <p>
 * 资源表 服务类
 * </p>
 *
 * @author XXX
 * @since 2021-02-20
 */
public interface ResourceService extends IService<Resource> {

    /**
     *  根据角色Id，查询该角色所具有的资源
     * @param roleId
     * @return
     */
    List<ResourceVo> listResourceByRoleId(Long roleId);
}
