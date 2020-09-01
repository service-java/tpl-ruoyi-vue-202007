package com.example.kryo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName ApiCategoryAttr
 * @Description //TODO
 * @Date 2020/8/31 16:27
 * @Version 1.0
 **/
@Data
public class ApiCategoryAttr implements Serializable {

    private static final long serialVersionUID = 3920592371672192840L;
    private String name;
    private String code;
    private Integer level;
    private Boolean isAttr;
    private List<ApiCategoryAttr> children;
}
