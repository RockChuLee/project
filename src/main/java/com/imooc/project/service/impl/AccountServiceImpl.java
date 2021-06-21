package com.imooc.project.service.impl;

import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.imooc.project.dto.LoginDTO;
import com.imooc.project.entity.Account;
import com.imooc.project.dao.AccountMapper;
import com.imooc.project.entity.Customer;
import com.imooc.project.service.AccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public LoginDTO login(String username, String password) {
        LoginDTO loginDTO = new LoginDTO();
        //默认重定向。配合webconfig，使“/” 跳转到login界面
        loginDTO.setPath("redirect:/");
        Account account = lambdaQuery().eq(Account::getUsername, username).one();
        if (account == null) {
            loginDTO.setError("用户不存在");
            return loginDTO;
        }
        //hutu生成带盐值的MD5
        MD5 md5 = new MD5(account.getSalt().getBytes());
        String digestHex = md5.digestHex(password);
        if (!digestHex.equals(account.getPassword())) {
            loginDTO.setError("密码错误");
            return loginDTO;
        }
        //防止session入侵到service层，所以设置近返回值
        loginDTO.setAccount(account);
        //跳转到login/mian.html
        loginDTO.setPath("/login/main");
        return loginDTO;
    }
}
