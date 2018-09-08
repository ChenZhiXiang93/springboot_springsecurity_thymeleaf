package com.bonc.springboot_springsecurity_thymeleaf;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bonc.springboot_springsecurity_thymeleaf.mapper")
public class SpringbootSpringsecurityThymeleafApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSpringsecurityThymeleafApplication.class, args);
    }
}
