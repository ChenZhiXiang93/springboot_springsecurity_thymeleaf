package com.bonc.springboot_springsecurity_thymeleaf.security;

import com.bonc.springboot_springsecurity_thymeleaf.excption.LoginException;
import com.bonc.springboot_springsecurity_thymeleaf.pojo.Permission;
import com.bonc.springboot_springsecurity_thymeleaf.pojo.User;
import com.bonc.springboot_springsecurity_thymeleaf.service.PermissionService;
import com.bonc.springboot_springsecurity_thymeleaf.service.UserService;
import com.bonc.springboot_springsecurity_thymeleaf.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author:ChenZhiXiang
 * @Description: SpringSecurity中用户查询类   不在这里判断密码错误的原因是密码这个参数没有
 * @Date:Created in 10:35 2018/8/24
 * @Modified By:
 */
@Component("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s){
        User user = userService.getUserByName(s);
        if (user == null){
            throw new LoginException("用户不存在");
        }
        //密码过期
        if (Integer.parseInt(user.getExpired()) < Integer.parseInt(DateUtils.beforeMonth(-1))){
            throw new LoginException("密码过期");
        }
        if (user.getErrorCount() >= 5 && Long.parseLong(user.getLastModifyTime()) > (System.currentTimeMillis()-3600*24*1000)){
            //  超出失败次数，停用账户
            throw new LoginException("用户密码错误超过5次,请次日再试");
        }

        //加的權限
        List<Permission> permissions = permissionService.findByAdminUserId(user.getId());
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Permission permission : permissions){
            if (permission != null && permission.getName() != null){
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
                //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象
                grantedAuthorities.add(grantedAuthority);
            }
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                passwordEncoder.encode(user.getPassword()),
                grantedAuthorities);
        //AuthorityUtils.commaSeparatedStringToAuthorityList("admin")
    }
}
