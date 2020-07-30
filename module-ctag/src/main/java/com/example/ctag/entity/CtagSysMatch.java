package com.example.ctag.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.example.common.annotation.Excel;
import lombok.Data;
import com.example.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;


/**
 * 特征项匹配对象 ctag_sys_match
 *
 * @author example
 * @date 2020-07-29
 */
@Data
public class CtagSysMatch extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */


        @TableId
        private Long id;

    /** 本地标准体系ID */

    @Excel(name = "本地标准体系ID")


        private Long localSysId;

    /** 品茗标准体系ID */

    @Excel(name = "品茗标准体系ID")


        private Long pmSysId;

    /** 是否更新(0: 否；1:是) */

    @Excel(name = "是否更新(0: 否；1:是)")


        private Integer isUpdated;

    /** 标准更新时间 */

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "标准更新时间", width = 30, dateFormat = "yyyy-MM-dd")


        private Date gmtStandardUpdated;

    /** 匹配更新时间 */

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "匹配更新时间", width = 30, dateFormat = "yyyy-MM-dd")


        private Date gmtMatchModified;




}
