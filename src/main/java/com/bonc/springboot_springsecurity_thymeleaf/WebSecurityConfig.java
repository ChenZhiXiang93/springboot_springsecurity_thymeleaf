package com.bonc.springboot_springsecurity_thymeleaf;

import com.bonc.springboot_springsecurity_thymeleaf.authorize.MyAccessDeniedHandler;
import com.bonc.springboot_springsecurity_thymeleaf.authorize.MyFilterSecurityInterceptor;
import com.bonc.springboot_springsecurity_thymeleaf.security.LoginSuccessHandler;
import com.bonc.springboot_springsecurity_thymeleaf.security.MyPasswordEncoder;
import com.bonc.springboot_springsecurity_thymeleaf.security.MyUserDetailsService;
import com.bonc.springboot_springsecurity_thymeleaf.validcode.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 10:41 2018/9/4
 * @Modified By:
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("myUserDetailsService")
    MyUserDetailsService myUserDetailsService;

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new MyPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(myUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter("/form/login", "/login?error");
        http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
        http
                .addFilterBefore(validateCodeFilter,UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/valid/*","/login").permitAll()
                .anyRequest().authenticated()
                .and().logout().permitAll()
                .and()
                .formLogin()
                .loginPage("/login").failureUrl("/login?error").permitAll()
                .loginProcessingUrl("/form/login")
                .successHandler(loginSuccessHandler).and().csrf().disable()
                .exceptionHandling().accessDeniedHandler(myAccessDeniedHandler);
    }
}
