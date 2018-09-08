package com.bonc.springboot_springsecurity_thymeleaf.service;


import com.bonc.springboot_springsecurity_thymeleaf.pojo.Permission;
import com.bonc.springboot_springsecurity_thymeleaf.pojo.User;

import java.util.List;
import java.util.Set;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 10:46 2018/8/24
 * @Modified By:
 */
public interface UserService {

    User getUserByName(String username);

    void updateUser(User user);

    void updateLastTimeByErrorCount();
}
