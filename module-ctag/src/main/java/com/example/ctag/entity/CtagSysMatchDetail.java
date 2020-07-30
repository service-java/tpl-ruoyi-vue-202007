package com.example.ctag.entity;

import com.example.common.annotation.Excel;
import lombok.Data;
import com.example.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;


/**
 * 特征项匹配详情对象 ctag_sys_match_detail
 *
 * @author example
 * @date 2020-07-29
 */
@Data
public class CtagSysMatchDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */


        @TableId
        private Long id;

    /** 工程ID */

    @Excel(name = "工程ID")


        private Long projectId;

    /** 所属的本地标准体系ID(冗余) */

    @Excel(name = "所属的本地标准体系ID(冗余)")


        private Long localSysId;

    /** 本地标准体系特征项ID */

    @Excel(name = "本地标准体系特征项ID")


        private Long localSysItemId;

    /** 品茗标准体系特征项ID */

    @Excel(name = "品茗标准体系特征项ID")


        private Long pmSysItemId;

    /** 填充值 */

    @Excel(name = "填充值")


        private String ctagValue;




}
