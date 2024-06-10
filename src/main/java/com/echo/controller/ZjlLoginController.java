package com.echo.controller;

import com.echo.pojo.zjlAccount;
import com.echo.pojo.zjlLoginDTO;
import com.echo.pojo.zjlResourceVo;
import com.echo.service.ZjlMenuService;
import com.echo.service.impl.ZjlLoginServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("auth")
public class ZjlLoginController {

    @Autowired
    private ZjlLoginServiceImpl zjlLoginServiceImpl;

    @Autowired
    private ZjlMenuService zjlMenuService;

    @RequestMapping("login")

    public String login(@Param("") String username, String password, RedirectAttributes attributes,
                        HttpSession session, Model model){
        System.out.println("用户名是："+username+"密码是："+password);
        zjlLoginDTO zjlLoginDTO = zjlLoginServiceImpl.Login(username, password);
        System.out.println(zjlLoginDTO);
        String error = zjlLoginDTO.getError();

        if (error != null){
            attributes.addFlashAttribute("Error",error);
        }else {
            session.setAttribute("account",zjlLoginDTO.getZjlAccount());
            zjlAccount zjlAccount = zjlLoginDTO.getZjlAccount();
            System.out.println(zjlAccount);
            //根据用户角色查出用户可以看到的模块
            List<zjlResourceVo> resourceVos = zjlMenuService.modelCanLookByRoleId(zjlLoginDTO.getZjlAccount().getZjlRoleId());

            model.addAttribute("resources",resourceVos);
        }
        return zjlLoginDTO.getPath();
    }

    @RequestMapping("logout")
    public String logout(){
        return  "redirect:/";
    }
}
