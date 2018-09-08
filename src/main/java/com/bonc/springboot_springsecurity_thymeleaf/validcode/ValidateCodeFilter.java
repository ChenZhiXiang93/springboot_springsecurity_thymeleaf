package com.bonc.springboot_springsecurity_thymeleaf.validcode;


import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 22:57 2018/8/30
 * @Modified By:
 */
public class ValidateCodeFilter extends AbstractAuthenticationProcessingFilter {


    private String servletPath;
    public ValidateCodeFilter(String servletPath, String failureUrl) {
        super(servletPath);
        this.servletPath = servletPath;
        setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler(failureUrl));
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        if ("POST".equalsIgnoreCase(request.getMethod()) && servletPath.equals(request.getServletPath())) {
            ImageCode imageCode = (ImageCode) request.getSession().getAttribute(ValidController.SESSION_KEY);
            if (imageCode.getCode() != null && !imageCode.getCode().equalsIgnoreCase(req.getParameter("imageCode"))) {
                unsuccessfulAuthentication(request, response, new InsufficientAuthenticationException("输入的验证码不正确"));
                return;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        return null;
    }

    /*private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request, ValidController.SESSION_KEY);

        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");
        System.out.println(codeInRequest+" "+codeInSession.getCode());
        if (StringUtils.isBlank(codeInRequest) || codeInSession == null) {
            throw new ValidateCodeException("验证码不能为空");
        }

        if (codeInSession.isExpire()) {
            sessionStrategy.removeAttribute(request, ValidController.SESSION_KEY);
            throw new ValidateCodeException("验证码已过期");
        }

        if (!StringUtils.equals(codeInSession.getCode().toLowerCase(), codeInRequest.toLowerCase())) {
            throw new ValidateCodeException("验证码不匹配");
        }

        sessionStrategy.removeAttribute(request, ValidController.SESSION_KEY);}*/


}
