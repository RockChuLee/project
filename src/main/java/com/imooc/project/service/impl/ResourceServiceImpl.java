package com.imooc.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.project.dao.ResourceMapper;
import com.imooc.project.entity.Resource;
import com.imooc.project.service.ResourceService;
import com.imooc.project.vo.ResourceVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author XXX
 * @since 2021-02-20
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    @Override
    public List<ResourceVo> listResourceByRoleId(Long roleId) {
        QueryWrapper<Resource> query = Wrappers.<Resource>query();
        query.eq("rr.role_id", roleId).isNull("re.parent_id");
        List<ResourceVo> resourceVos = baseMapper.listResource(query);
        resourceVos.forEach(resourceVo -> {
            Long resourceId = resourceVo.getResourceId();
            QueryWrapper<Resource> subQuery = Wrappers.<Resource>query();
            subQuery.eq("rr.role_id", roleId).eq("re.parent_id", resourceId);
            List<ResourceVo> subResourceVos = baseMapper.listResource(subQuery);
            if (CollectionUtils.isNotEmpty(subResourceVos)) {
                resourceVo.setSubs(subResourceVos);
            }
        });
        return resourceVos;
    }
}
