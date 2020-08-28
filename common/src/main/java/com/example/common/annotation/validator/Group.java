package com.example.common.annotation.validator;

import javax.validation.GroupSequence;

/**
 * 定义校验顺序，如果AddGroup组失败
 * 则UpdateGroup组不会再校验
 *
 * <p>
 * User: luo0412
 * Date: 2020-08-19 14:21
 */
@GroupSequence({AddGroup.class, UpdateGroup.class})
public interface Group {

}

