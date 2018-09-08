package com.bonc.springboot_springsecurity_thymeleaf.service;

import com.bonc.springboot_springsecurity_thymeleaf.pojo.Permission;

import java.util.List;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 15:17 2018/9/5
 * @Modified By:
 */
public interface PermissionService {

    public List<Permission> findAll();
    public List<Permission> findByAdminUserId(int userId);
}
