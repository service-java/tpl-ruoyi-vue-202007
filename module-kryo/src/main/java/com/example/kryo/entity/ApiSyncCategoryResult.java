package com.example.kryo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName ApiSynCategoryResult
 * @Description //TODO
 * @Date 2020/8/31 16:34
 * @Version 1.0
 **/
@Data
public class ApiSyncCategoryResult implements Serializable  {

    private static final long serialVersionUID = -2170234753636661555L;

    private String areaCode;
    private String memo;
    private List<ApiCategoryAttr> attrList;
}
