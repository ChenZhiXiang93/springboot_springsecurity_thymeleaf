package com.bonc.springboot_springsecurity_thymeleaf.authorize;

import com.bonc.springboot_springsecurity_thymeleaf.response.CommResponseEnum;
import com.bonc.springboot_springsecurity_thymeleaf.response.GlobalException;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Iterator;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 15:31 2018/9/5
 * @Modified By:
 */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {

    //  decide 方法是判定是否拥有权限的决策方法，
    //  authentication是MyUserDetailsService中循环添加到GrantedAuthority对象中的权限信息集合.
    //object 包含客户端发起的请求的requset信息，可转换为 HttpServletRequest request =
    //((FilterInvocation) object).getHttpRequest();
    //  configAttributes 为MyInvocationSecurityMetadataSource的getAttributes(Object object)
    //这个方法返回的结果，此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给decide 方法，
    //用来判定用户是否有此权限。如果不在权限表中则放行。

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) {
        if (null == configAttributes || configAttributes.size() < 0){
            return;
        }
        ConfigAttribute c;
        String needRole;
        for (Iterator<ConfigAttribute> iterator = configAttributes.iterator();iterator.hasNext();){
            c = iterator.next();
            needRole = c.getAttribute();
            //authentication 为在注释1中循环添加到 GrantedAuthority 对象中的权限信息集合
            for (GrantedAuthority grantedAuthority : authentication.getAuthorities()){
                if (needRole.trim().equals(grantedAuthority.getAuthority())){
                    return;
                }
            }
        }
        /*throw new GlobalException(CommResponseEnum.USER2);*/
        /*HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();*/
        throw new AccessDeniedException("沒有權限");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
