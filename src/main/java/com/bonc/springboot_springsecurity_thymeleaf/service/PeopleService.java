package com.bonc.springboot_springsecurity_thymeleaf.service;

import com.bonc.springboot_springsecurity_thymeleaf.pojo.People;

import java.util.List;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 9:40 2018/9/5
 * @Modified By:
 */
public interface PeopleService {

    People getPeopleById(int id);

    List<People> getAll();
}
