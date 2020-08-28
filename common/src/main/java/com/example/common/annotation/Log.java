package com.example.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.example.common.enums.BusinessTypeEnums;
import com.example.common.enums.OperatorTypeEnums;

/**
 * 自定义操作日志记录注解
 *
 * @author ruoyi
 *
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log
{
    /**
     * 模块
     */
    public String title() default "";

    /**
     * 功能
     */
    public BusinessTypeEnums businessType() default BusinessTypeEnums.OTHER;

    /**
     * 操作人类别
     */
    public OperatorTypeEnums operatorType() default OperatorTypeEnums.MANAGE;

    /**
     * 是否保存请求的参数
     */
    public boolean isSaveRequestData() default true;
}
