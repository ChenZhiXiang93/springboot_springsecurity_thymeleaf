package com.bonc.springboot_springsecurity_thymeleaf.service.impl;

import com.bonc.springboot_springsecurity_thymeleaf.mapper.UserMapper;
import com.bonc.springboot_springsecurity_thymeleaf.pojo.Permission;
import com.bonc.springboot_springsecurity_thymeleaf.pojo.User;
import com.bonc.springboot_springsecurity_thymeleaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 10:47 2018/8/24
 * @Modified By:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User getUserByName(String username) {
        return userMapper.getUserByName(username);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void updateLastTimeByErrorCount() {
        String lastModifyTime = String.valueOf(System.currentTimeMillis());
        userMapper.updateLastTimeByErrorCount(lastModifyTime);
    }
}
