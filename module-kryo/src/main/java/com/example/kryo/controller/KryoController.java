package com.example.kryo.controller;

import cn.hutool.core.lang.Console;
import com.example.common.base.BaseController;
import com.example.framework.config.KryoHttpMessageConverter;
import com.example.kryo.entity.Person;
import com.example.kryo.service.KryoRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * 示例模块Controller
 *
 * @author example
 * @date 2020-07-29
 */
@RestController
@RequestMapping("/kryo")
public class KryoController extends BaseController {

    @Autowired
    KryoRedisService redisClient;

    @GetMapping("/get")
    public Object get() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(Arrays.asList(new KryoHttpMessageConverter()));

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(KryoHttpMessageConverter.KRYO));
        HttpEntity<String> entity = new HttpEntity<String>(headers);

        ResponseEntity<Person> response = restTemplate.exchange("http://localhost:8080/kryo/getKryo",
                HttpMethod.GET, entity, Person.class);
        Person resource = response.getBody();
        Console.log(resource);

//        Object person_1 = redisClient.getObject("person_1");
        return resource;
    }

    @GetMapping(
            value = "/getKryo"
//            , produces = {"application/x-kryo"}
            )
    @ResponseBody
    public Person getKryo() {
        Person person = new Person();
        person.setAge(11).setId(1).setName("hhaha");
        return person;
    }

    @GetMapping("/add")
    public void add() {
        Person person = new Person();
        person.setAge(11).setId(1).setName("hhaha");
        redisClient.set("person_1", person);
    }




}
