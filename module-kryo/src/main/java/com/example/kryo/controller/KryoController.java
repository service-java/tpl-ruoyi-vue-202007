package com.example.kryo.controller;

import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.example.common.base.BaseController;
import com.example.framework.config.KryoHttpMessageConverter;
import com.example.kryo.entity.KryoParams;
import com.example.kryo.entity.KryoResult;
import com.example.kryo.entity.Person;
import com.example.kryo.service.KryoRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 示例模块Controller
 *
 * @author example
 * @date 2020-07-29
 */
@RestController
@RequestMapping("/kryo")
public class KryoController extends BaseController {

    //    final static String URL = "http://192.168.100.120:8080/kryo2/receive";
    final static String URL = "http://localhost:8080/kryo/post";

    @Autowired
    KryoRedisService redisClient;

    @GetMapping("/remote")
    public Object remote() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(Arrays.asList(new KryoHttpMessageConverter()));

        KryoParams body = new KryoParams();
        body.setCode("001212").setName("hahha");

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(KryoHttpMessageConverter.KRYO));
        HttpEntity<KryoParams> entity = new HttpEntity(body, headers);

        ResponseEntity<KryoResult> response = restTemplate.exchange(
                URL,
                HttpMethod.POST,
                entity,
                KryoResult.class);

        KryoResult resource = response.getBody();
        Console.log(resource);

//        Object person_1 = redisClient.getObject("person_1");
        return resource;
    }

    @GetMapping("/remote2")
    public Object remote2(@RequestParam String code, @RequestParam String name) {
        JSONObject paramMap = JSONUtil.createObj();
        paramMap.set("code", code);
        paramMap.set("name", name);

        String result = HttpRequest
                .post(URL)
//                .header("Content-Type","application/kryo") // 不是这种
                .header("application", "kryo")
                .body(paramMap.toString())
                .execute()
                .body();

        KryoResult response = JSON.parseObject(result, KryoResult.class);

        return response;
    }


    @GetMapping("/remote3")
    public Object remote3() {

        List<KryoParams> list = new ArrayList<>();

        KryoParams query = new KryoParams();

        KryoParams params = null;
        for (int i = 0; i < 3; i++) {
            params = new KryoParams();
            params.setName("name" + i);
            params.setCode("code" + i);
            list.add(params);
        }

        query.setCode("code123").setName("name-123");
        query.setChildren(list);


        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(Arrays.asList(new KryoHttpMessageConverter()));

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(KryoHttpMessageConverter.KRYO));
        HttpEntity<KryoParams> entity = new HttpEntity(query, headers);

        ResponseEntity<KryoResult> response = restTemplate.exchange(URL,
                HttpMethod.POST, entity, KryoResult.class);
        KryoResult resource = response.getBody();

        return resource;
    }


    @PostMapping(
            value = "/post"
//            , produces = {"application/x-kryo"}
    )
    @ResponseBody
    public KryoResult post(@RequestBody KryoParams params) {
        KryoResult apiSynCategoryResult = new KryoResult();
        apiSynCategoryResult.setAreaCode(params.getCode())
                .setAttrList(params.getChildren())
                .setMemo(params.getName());
        return apiSynCategoryResult;
    }


    @GetMapping("/set")
    public String set() {
        Person person = new Person();
        person.setAge(11).setId(1).setName("hhaha");
        redisClient.set("person_1", person);
        return "ok";
    }

}
