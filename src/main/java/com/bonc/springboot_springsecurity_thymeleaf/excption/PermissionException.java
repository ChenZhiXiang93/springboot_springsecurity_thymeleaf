package com.bonc.springboot_springsecurity_thymeleaf.excption;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 22:56 2018/8/30
 * @Modified By:
 */
public class PermissionException extends AuthenticationException {

    private static final long serialVersionUID = -7932793974645209799L;

    public PermissionException(String msg) {
        super(msg);
    }
}
