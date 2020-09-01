package com.example.kryo.controller;

import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.example.common.base.BaseController;
import com.example.framework.config.KryoHttpMessageConverter;
import com.example.kryo.entity.ApiCategoryAttr;
import com.example.kryo.entity.ApiSyncCategoryResult;
import com.example.kryo.entity.Person;
import com.example.kryo.service.KryoRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/fakeRemote")
    public Object remote() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(Arrays.asList(new KryoHttpMessageConverter()));

        ApiCategoryAttr body = new ApiCategoryAttr();
        body.setCode("001212").setName("hahha");

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(KryoHttpMessageConverter.KRYO));
        HttpEntity<ApiCategoryAttr> entity = new HttpEntity<ApiCategoryAttr>(body, headers);

        ResponseEntity<ApiSyncCategoryResult> response = restTemplate.exchange(
                "http://localhost:8080/kryo/post",
                HttpMethod.POST,
                entity,
                ApiSyncCategoryResult.class);

        ApiSyncCategoryResult resource = response.getBody();
        Console.log(resource);

//        Object person_1 = redisClient.getObject("person_1");
        return resource;
    }

    @GetMapping("/fakeRemote2")
    public Object remote2(@RequestParam String code,@RequestParam String name) {
        JSONObject paramMap = JSONUtil.createObj();
        paramMap.set("code", code);
        paramMap.set("name", name);

        String result = HttpRequest
                .post("http://localhost:8080/kryo/post")
//                .header("Content-Type","application/kryo") // 不是这种
                .header("application", "kryo")
                .body(paramMap.toString())
                .execute()
                .body();

        ApiSyncCategoryResult response = JSON.parseObject(result, ApiSyncCategoryResult.class);

        return response;
    }



    @PostMapping(
            value = "/post"
//            , produces = {"application/x-kryo"}
            )
    @ResponseBody
    public ApiSyncCategoryResult post(@RequestBody ApiCategoryAttr params) {
        ApiSyncCategoryResult apiSynCategoryResult = new ApiSyncCategoryResult();
        apiSynCategoryResult.setAreaCode(params.getCode()).setAttrList(null).setMemo(params.getName());
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
