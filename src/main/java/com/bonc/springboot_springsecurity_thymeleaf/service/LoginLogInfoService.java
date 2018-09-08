package com.bonc.springboot_springsecurity_thymeleaf.service;


import com.bonc.springboot_springsecurity_thymeleaf.pojo.LoginLogInfo;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 21:31 2018/8/28
 * @Modified By:
 */
public interface LoginLogInfoService {

    void saveLoginLogInfo(LoginLogInfo loginLogInfo);
}
