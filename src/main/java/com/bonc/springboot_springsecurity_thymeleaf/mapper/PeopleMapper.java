package com.bonc.springboot_springsecurity_thymeleaf.mapper;

import com.bonc.springboot_springsecurity_thymeleaf.pojo.People;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 9:29 2018/9/5
 * @Modified By:
 */
public interface PeopleMapper {

    People getPeopleById(@Param("id") int id);

    List<People> getAll();
}
