package com.example.ctag.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.example.common.annotation.Excel;
import lombok.Data;
import com.example.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;


/**
 * 特征标记工程对象 ctag_project
 *
 * @author example
 * @date 2020-07-29
 */
@Data
public class CtagProject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */


        @TableId
        private Long id;

    /** 工程编码(唯一) */

    @Excel(name = "工程编码(唯一)")


        private String guid;

    /** 项目名称 */

    @Excel(name = "项目名称")


        private String name;

    /** 工程地市 */

    @Excel(name = "工程地市")


        private String city;

    /** 年份 */

    @Excel(name = "年份")


        private Long year;

    /** 计价依据 */

    @Excel(name = "计价依据")


        private String pricingBasis;

    /** 对应本地标准体系 */

    @Excel(name = "对应本地标准体系")


        private Long localSysId;

    /** 状态(0: 初始状态；1: 解析成功; 2: 解析失败) */

    @Excel(name = "状态(0: 初始状态；1: 解析成功; 2: 解析失败)")


        private Integer status;

    /** 创建时间 */

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")


        private Date gmtCreate;

    /** 修改时间 */

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")


        private Date gmtModified;

    /** 数据上传完成时间 */

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "数据上传完成时间", width = 30, dateFormat = "yyyy-MM-dd")


        private Date gmtDataUploaded;




}
