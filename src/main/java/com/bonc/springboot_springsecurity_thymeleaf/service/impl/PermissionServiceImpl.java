package com.bonc.springboot_springsecurity_thymeleaf.service.impl;

import com.bonc.springboot_springsecurity_thymeleaf.mapper.PermissionMapper;
import com.bonc.springboot_springsecurity_thymeleaf.pojo.Permission;
import com.bonc.springboot_springsecurity_thymeleaf.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 15:18 2018/9/5
 * @Modified By:
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> findAll() {
        return permissionMapper.findAll();
    }

    @Override
    public List<Permission> findByAdminUserId(int userId) {
        return permissionMapper.findByAdminUserId(userId);
    }
}
