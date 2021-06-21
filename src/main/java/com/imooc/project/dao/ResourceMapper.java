package com.imooc.project.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.imooc.project.entity.Resource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imooc.project.vo.ResourceVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资源表 Mapper 接口
 * </p>
 *
 * @author XXX
 * @since 2021-02-20
 */
public interface ResourceMapper extends BaseMapper<Resource> {

    /**
     * 查询当前登陆人的资源
     * @param wrapper
     * @return
     */
    List<ResourceVo> listResource(@Param(Constants.WRAPPER) Wrapper<Resource> wrapper);

}
