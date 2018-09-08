package com.bonc.springboot_springsecurity_thymeleaf.security;

import com.bonc.springboot_springsecurity_thymeleaf.excption.LoginException;
import com.bonc.springboot_springsecurity_thymeleaf.pojo.LoginLogInfo;
import com.bonc.springboot_springsecurity_thymeleaf.service.LoginLogInfoService;
import com.bonc.springboot_springsecurity_thymeleaf.util.DateUtils;
import com.bonc.springboot_springsecurity_thymeleaf.util.IPUnitls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author:ChenZhiXiang
 * @Description: 自定义登录成功处理
 * @Date:Created in 21:13 2018/8/28
 * @Modified By:
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private LoginLogInfoService loginLogInfoService;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
       //调用记录日志
        this.saveLoginInfo(request,authentication);
        //登录成功后改变SessionId
        HttpSession session = request.getSession(false);
        /*System.out.println("久"+session.getId());*/
        //清空session
        session.invalidate();
        //重新获得session
        session = request.getSession(true);
        System.out.println("新"+session.getId());
        //重定向到首页
        redirectStrategy.sendRedirect(request,response,"/html/home.html");
    }
    /**
     *@Author:ChenZhiXiang
     *@Description: 记录登录日志
     *@Date: 10:36 2018/8/29
     */
    public void saveLoginInfo(HttpServletRequest request,Authentication authentication){
        try {
            LoginLogInfo loginLogInfo = new LoginLogInfo();
            loginLogInfo.setId(0);
            loginLogInfo.setLoginName(authentication.getName());
            loginLogInfo.setLoginTime(DateUtils.getNowTime());
            loginLogInfo.setLoginIp(IPUnitls.getIpAddress(request));
            loginLogInfoService.saveLoginLogInfo(loginLogInfo);
        }catch (DataAccessException e){
            throw new LoginException("登录日志入库失败");
        }

    }
}
