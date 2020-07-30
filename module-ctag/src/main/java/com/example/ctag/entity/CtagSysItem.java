package com.example.ctag.entity;

import com.example.common.annotation.Excel;
import lombok.Data;
import com.example.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;


/**
 * 特征项对象 ctag_sys_item
 *
 * @author example
 * @date 2020-07-29
 */
@Data
public class CtagSysItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */


        @TableId
        private Long id;

    /** 特征项名称 */

    @Excel(name = "特征项名称")


        private String name;

    /** 父级ID */

    @Excel(name = "父级ID")


        private Long pid;

    /** 层级 */

    @Excel(name = "层级")


        private Long level;

    /** 特征项类型(1: 字符串;2: 数值;3:枚举) */

    @Excel(name = "特征项类型(1: 字符串;2: 数值;3:枚举)")


        private Integer itemType;

    /** 特征项备注 */

    @Excel(name = "特征项备注")


        private String itemRemark;

    /** 字符串类型的长度限制 */

    @Excel(name = "字符串类型的长度限制")


        private Long itemStrLengthLimit;

    /** 是否允许有小数(0: 否;1: 是) */

    @Excel(name = "是否允许有小数(0: 否;1: 是)")


        private Integer itemNumAllowDecimal;

    /** 枚举型是否允许多选 */

    @Excel(name = "枚举型是否允许多选")


        private Integer itemEnumAllowMulti;

    /** 所属的体系 */

    @Excel(name = "所属的体系")


        private Long sysId;




}
