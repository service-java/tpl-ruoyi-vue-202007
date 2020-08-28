package com.example.common.enums;

/**
 * Description: 模块功能描述
 * <p>
 * User: luo0412
 * Date: 2020-07-30 9:34
 */
public class OSSEnums {
    /**
     * 云服务商
     */
    public enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 腾讯云
         */
        QCLOUD(3);

        private int value;

        CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
