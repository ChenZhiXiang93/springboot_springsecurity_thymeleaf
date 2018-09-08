package com.bonc.springboot_springsecurity_thymeleaf.service.impl;

import com.bonc.springboot_springsecurity_thymeleaf.mapper.PeopleMapper;
import com.bonc.springboot_springsecurity_thymeleaf.pojo.People;
import com.bonc.springboot_springsecurity_thymeleaf.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 9:41 2018/9/5
 * @Modified By:
 */
@Service
public class PeopleServiceImpl implements PeopleService {

    @Autowired
    private PeopleMapper peopleMapper;

    @Override
    public People getPeopleById(int id) {
        People people = peopleMapper.getPeopleById(id);
        if (people.getSex().equals("1")){
            people.setSex("男");
        }
        if (people.getSex().equals("0")){
            people.setSex("女");
        }
        return people;
    }

    @Override
    public List<People> getAll() {
        List<People> repList = peopleMapper.getAll();
        for (People people : repList) {
            if (people.getSex().equals("1")){
                people.setSex("男");
            }
            if (people.getSex().equals("0")){
                people.setSex("女");
            }
        }
        return repList;
    }
}
