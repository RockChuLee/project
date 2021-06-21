package com.imooc.project.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: dh
 * @Date: 2021/6/21 17:39
 */
public class ResultUtil {

    /**
     * 构建分页查询的返回结果
     * @param page
     * @return
     */
    public static R<Map<String, Object>> buildPageR(IPage<?> page) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("count", page.getTotal());
        data.put("records", page.getRecords());
        return R.ok(data);
    }
}
