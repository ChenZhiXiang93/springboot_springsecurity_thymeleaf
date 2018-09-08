package com.bonc.springboot_springsecurity_thymeleaf.mapper;


import com.bonc.springboot_springsecurity_thymeleaf.pojo.Permission;
import com.bonc.springboot_springsecurity_thymeleaf.pojo.User;
import org.apache.ibatis.annotations.Param;
import java.util.Set;


/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 10:42 2018/8/24
 * @Modified By:
 */
public interface UserMapper {

    User getUserByName(@Param("username") String username);

    void updateUser(User user);

    void updateLastTimeByErrorCount(String lastModifyTime);
}
