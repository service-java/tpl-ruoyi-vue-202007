package com.example.ctag.entity;

import com.example.common.annotation.Excel;
import lombok.Data;
import com.example.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;


/**
 * 省别对象 ctag_province
 *
 * @author example
 * @date 2020-07-29
 */
@Data
public class CtagProvince extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */


        @TableId
        private Long id;

    /** 省别/特征标准 */

    @Excel(name = "省别/特征标准")


        private String name;

    /** 类型(1: 品茗标准体系; 2:本地标准体系) */

    @Excel(name = "类型(1: 品茗标准体系; 2:本地标准体系)")


        private Integer type;

    /** $column.columnComment */

    @Excel(name = "类型(1: 品茗标准体系; 2:本地标准体系)")


        private Long pid;

    /** 层级 */

    @Excel(name = "层级")


        private Long level;




}
