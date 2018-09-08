package com.bonc.springboot_springsecurity_thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 10:37 2018/9/4
 * @Modified By:
 */
@Controller
public class HomeController {

    @RequestMapping("/login")
    public String login(){
        return "login";
    }
}
