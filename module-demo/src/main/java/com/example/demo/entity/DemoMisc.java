package com.example.demo.entity;

import com.example.common.annotation.Excel;
import lombok.Data;
import com.example.common.base.BaseEntity;

/**
 * 示例模块对象 demo_misc
 *
 * @author example
 * @date 2020-07-29
 */
@Data
public class DemoMisc extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;




}
