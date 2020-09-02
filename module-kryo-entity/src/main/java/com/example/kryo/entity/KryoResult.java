package com.example.kryo.entity;

import lombok.Data;

import java.util.List;

/**
 * @ClassName ApiSynCategoryResult
 * @Description //TODO
 * @Date 2020/8/31 16:34
 * @Version 1.0
 **/
@Data
public class KryoResult {


    private String areaCode;
    private String memo;

    private List<KryoParams> attrList;
}
