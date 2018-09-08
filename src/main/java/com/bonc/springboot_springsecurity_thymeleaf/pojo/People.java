package com.bonc.springboot_springsecurity_thymeleaf.pojo;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 9:32 2018/9/5
 * @Modified By:
 */
public class People {

    private Integer id;

    private String name;

    private String sex;

    private String age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
