package com.imooc.project.service.impl;

import com.imooc.project.entity.Account;
import com.imooc.project.mapper.AccountMapper;
import com.imooc.project.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账号表 服务实现类
 * </p>
 *
 * @author XXX
 * @since 2021-02-20
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

}
