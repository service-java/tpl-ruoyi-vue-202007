package com.example.ctag.entity;

import com.example.common.annotation.Excel;
import lombok.Data;
import com.example.common.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;


/**
 * 工程与OSS文件匹配对象 ctag_oss_project
 *
 * @author example
 * @date 2020-07-29
 */
@Data
public class CtagOssProject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */


        @TableId
        private Long id;

    /** 文件ID */

    @Excel(name = "文件ID")


        private Long ossId;

    /** 工程ID */

    @Excel(name = "工程ID")


        private Long projectId;




}
