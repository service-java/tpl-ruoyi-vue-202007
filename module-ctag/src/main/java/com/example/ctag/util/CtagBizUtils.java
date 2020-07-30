package com.example.ctag.util;

import com.example.common.constant.CtagConstants;

/**
 * Description: 模块功能描述
 * <p>
 * User: luo0412
 * Date: 2020-07-29 16:56
 */
public class CtagBizUtils {
    public static final String getMaxCode (String maxCode) {

        if (maxCode.contains(CtagConstants.SYS_CODE_PREFIX)) {
            int index = maxCode.indexOf(CtagConstants.SYS_CODE_PREFIX);
            maxCode = maxCode.substring(index + CtagConstants.SYS_CODE_PREFIX.length());
        }

        // 如果小于6位左边补0
        int num=Integer.parseInt(maxCode);
        num++;
        String str = String.format("%06d", num);
        return CtagConstants.SYS_CODE_PREFIX + str;
    }
}
