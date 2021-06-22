package com.imooc.project.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.project.entity.Customer;
import com.imooc.project.service.CustomerService;
import com.imooc.project.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 客户表 前端控制器
 * </p>
 *
 * @author XXX
 * @since 2021-02-20
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    /**
     * 进入列表页
     *
     * @return
     */
    @GetMapping("/toList")
    public String toList() {
        return "customer/customerList";
    }

    /**
     * 查询方法
     *
     * @param realName
     * @param phone
     * @param page
     * @param limit
     * @return
     */

    @GetMapping("/list")
    @ResponseBody
    public R<Map<String, Object>> list(String realName, String phone, Long page, Long limit) {
        LambdaQueryWrapper<Customer> queryWrapper = Wrappers.<Customer>lambdaQuery().like(StringUtils.isNotBlank(realName), Customer::getRealName, realName)
                .like(StringUtils.isNotBlank(phone), Customer::getPhone, phone)
                .orderByDesc(Customer::getCustomerId);
        Page<Customer> myPage = customerService.page(new Page<>(page, limit), queryWrapper);
        return ResultUtil.buildPageR(myPage);
    }

    /**
     * 进入新增页
     *
     * @return
     */
    @GetMapping("/toAdd")
    public String toAdd() {
        return "customer/customerAdd";
    }


    /**
     * 新增客户
     * @param customer
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public R<Object> add(@RequestBody Customer customer) {
        return ResultUtil.buildR(customerService.save(customer));
    }
}
