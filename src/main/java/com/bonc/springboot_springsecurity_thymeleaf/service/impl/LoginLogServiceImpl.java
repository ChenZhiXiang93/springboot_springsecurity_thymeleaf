package com.bonc.springboot_springsecurity_thymeleaf.service.impl;

import com.bonc.springboot_springsecurity_thymeleaf.mapper.LoginLogInfoMapper;
import com.bonc.springboot_springsecurity_thymeleaf.pojo.LoginLogInfo;
import com.bonc.springboot_springsecurity_thymeleaf.service.LoginLogInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 21:31 2018/8/28
 * @Modified By:
 */
@Service
public class LoginLogServiceImpl implements LoginLogInfoService {

    @Autowired
    private LoginLogInfoMapper loginLogInfoMapper;

    @Override
    public void saveLoginLogInfo(LoginLogInfo loginLogInfo) {
        loginLogInfoMapper.saveLoginLogInfo(loginLogInfo);
    }
}
