package com.bonc.springboot_springsecurity_thymeleaf.authorize;

import com.bonc.springboot_springsecurity_thymeleaf.util.HTTPUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 17:37 2018/9/5
 * @Modified By:
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler{

    @Override public void handle(HttpServletRequest httpServletRequest, HttpServletResponse resp, AccessDeniedException e) throws IOException, ServletException {
        resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.write("{\"status\":\"error\",\"msg\":\"权限不足，请联系管理员!\"}");
        out.flush(); out.close(); }
}
