package com.bonc.springboot_springsecurity_thymeleaf.mapper;

import com.bonc.springboot_springsecurity_thymeleaf.pojo.Permission;

import java.util.List;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 15:15 2018/9/5
 * @Modified By:
 */
public interface PermissionMapper {

    List<Permission> findAll();
    List<Permission> findByAdminUserId(int userId);
}
