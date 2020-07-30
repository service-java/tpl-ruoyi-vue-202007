package com.example.ctag.entity;

import com.example.common.annotation.Excel;
import lombok.Data;
import com.example.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;


/**
 * 特征项枚举对象 ctag_sys_item_enum
 *
 * @author example
 * @date 2020-07-29
 */
@Data
public class CtagSysItemEnum extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */


        @TableId
        private Long id;

    /** 特征项的枚举名 */

    @Excel(name = "特征项的枚举名")


        private String name;

    /** 所属的特征项ID */

    @Excel(name = "所属的特征项ID")


        private Long sysItemId;




}
