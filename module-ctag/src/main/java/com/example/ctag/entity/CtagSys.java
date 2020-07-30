package com.example.ctag.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.example.common.annotation.Excel;
import lombok.Data;
import com.example.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;


/**
 * 特征体系对象 ctag_sys
 *
 * @author example
 * @date 2020-07-29
 */
@Data
public class CtagSys extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */


        @TableId
        private Long id;

    /** 系统类型(1: 品茗标准体系;2: 本地标准体系) */

    @Excel(name = "系统类型(1: 品茗标准体系;2: 本地标准体系)")


        private Integer type;

    /** 品茗标准体系省别ID/本地标准体系的特征标准 */

    @Excel(name = "品茗标准体系省别ID/本地标准体系的特征标准")


        private Long provinceId;

    /** 省别(冗余) */

    @Excel(name = "省别(冗余)")


        private String provinceName;

    /** 体系名 */

    @Excel(name = "体系名")


        private String sysName;

    /** 体系编码 */

    @Excel(name = "体系编码")


        private String sysCode;

    /** 是否被删除(0: 否; 1: 是) */

    @Excel(name = "是否被删除(0: 否; 1: 是)")


        private Integer isDeleted;

    /** 创建时间 */

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")


        private Date gmtCreate;

    /** 修改时间 */

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")


        private Date gmtModified;




}
