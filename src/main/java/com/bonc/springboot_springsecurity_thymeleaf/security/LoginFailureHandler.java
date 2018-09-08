package com.bonc.springboot_springsecurity_thymeleaf.security;

import com.bonc.springboot_springsecurity_thymeleaf.excption.ValidateCodeException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 22:59 2018/8/30
 * @Modified By:
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    private static final String NOT_NULL = "验证码不能为空";
    private static final String NOT_MATCH = "验证码不匹配";
    private static final String NOT_EXPIRE = "验证码已过期";

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) {

        System.out.println("异常"+exception);
        if (exception.toString().indexOf(NOT_NULL) != -1){
            throw new ValidateCodeException(NOT_NULL);
        }
        if (exception.toString().indexOf(NOT_MATCH) != -1){
            throw new ValidateCodeException(NOT_MATCH);
        }
        if (exception.toString().indexOf(NOT_EXPIRE) != -1){
            throw new ValidateCodeException(NOT_EXPIRE);
        }
    }
}
