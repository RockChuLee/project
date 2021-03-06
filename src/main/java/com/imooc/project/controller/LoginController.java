package com.imooc.project.controller;

import com.imooc.project.dto.LoginDTO;
import com.imooc.project.service.AccountService;
import com.imooc.project.service.ResourceService;
import com.imooc.project.vo.ResourceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description:
 * @Author: dh
 * @Date: 2021/5/24 9:52
 */
@Controller
@RequestMapping("auth")
public class LoginController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ResourceService resourceService;

    /**
     *  用户登录
     * @param username
     * @param password
     * @return
     */
    @PostMapping("login")
    public String login(String username, String password, HttpSession session, RedirectAttributes attributes, Model model) {
        LoginDTO loginDTO = accountService.login(username, password);
        String error = loginDTO.getError();
        if (error == null) {
            //添加session
            session.setAttribute("account",loginDTO.getAccount());
            List<ResourceVo> resourceVos = resourceService.listResourceByRoleId(loginDTO.getAccount().getRoleId());
            model.addAttribute("resources", resourceVos);
        }else {
            //重定向属性设置
            attributes.addFlashAttribute("error", error);
        }
        return loginDTO.getPath();
    }

    /**
     *  登出方法
     * @param session
     * @return
     */
    @GetMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
