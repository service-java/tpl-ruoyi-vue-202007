package com.example.kyro.controller;

import com.example.common.base.BaseController;
import com.example.kyro.entity.Person;
import com.example.kyro.service.KryoRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 示例模块Controller
 *
 * @author example
 * @date 2020-07-29
 */
@RestController
@RequestMapping("/kryo")
public class KryoController extends BaseController
{

    @Autowired
    KryoRedisService redisClient;

    @GetMapping("/get")
    public Object get() {
        Object person_1 = redisClient.getObject("person_1");
        return person_1;
    }

    @GetMapping("/add")
    public void add() {
        Person person = new Person();
        person.setAge(11).setId(1).setName("hhaha嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻嘻");
        redisClient.set("person_1", person);
    }

}