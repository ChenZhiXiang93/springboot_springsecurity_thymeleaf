package com.bonc.springboot_springsecurity_thymeleaf.controller;

import com.bonc.springboot_springsecurity_thymeleaf.response.ResponseData;
import com.bonc.springboot_springsecurity_thymeleaf.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 9:52 2018/9/5
 * @Modified By:
 */
@RestController
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    @GetMapping("/getPeopleById")
    public String getPeopleById(@RequestParam("id") Integer id) throws Exception {

        return ResponseData.turnResponse(peopleService.getPeopleById(id));
    }

    @GetMapping("/getAll")
    public String getAll() throws Exception {

        return ResponseData.turnResponse(peopleService.getAll());
    }
}
