/*
Navicat MySQL Data Transfer

Source Server         : locahost(本地)
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : pmfeature

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2020-07-29 15:08:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ctag_oss_project
-- ----------------------------
DROP TABLE IF EXISTS `ctag_oss_project`;
CREATE TABLE `ctag_oss_project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `oss_id` bigint(20) DEFAULT NULL COMMENT '文件ID',
  `project_id` bigint(20) DEFAULT NULL COMMENT '工程ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '工程与OSS文件匹配表';

-- ----------------------------
-- Records of ctag_oss_project
-- ----------------------------

-- ----------------------------
-- Table structure for ctag_project
-- ----------------------------
DROP TABLE IF EXISTS `ctag_project`;
CREATE TABLE `ctag_project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `guid` varchar(255) DEFAULT NULL COMMENT '工程编码(唯一)',
  `name` varchar(255) DEFAULT NULL COMMENT '项目名称',
  `city` varchar(255) DEFAULT NULL COMMENT '工程地市',
  `year` int(11) DEFAULT NULL COMMENT '年份',
  `pricing_basis` varchar(255) DEFAULT NULL COMMENT '计价依据',
  `local_sys_id` bigint(20) DEFAULT NULL COMMENT '对应本地标准体系',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态(0: 初始状态；1: 解析成功; 2: 解析失败)',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `gmt_data_uploaded` datetime DEFAULT NULL COMMENT '数据上传完成时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '特征标记工程';

-- ----------------------------
-- Records of ctag_project
-- ----------------------------

-- ----------------------------
-- Table structure for ctag_province
-- ----------------------------
DROP TABLE IF EXISTS `ctag_province`;
CREATE TABLE `ctag_province` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '省别/特征标准',
  `type` tinyint(1) DEFAULT NULL COMMENT '类型(1: 品茗标准体系; 2:本地标准体系)',
  `pid` bigint(20) DEFAULT NULL,
  `level` int(255) DEFAULT NULL COMMENT '层级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '省别';

-- ----------------------------
-- Records of ctag_province
-- ----------------------------

-- ----------------------------
-- Table structure for ctag_sys
-- ----------------------------
DROP TABLE IF EXISTS `ctag_sys`;
CREATE TABLE `ctag_sys` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `type` tinyint(1) NOT NULL COMMENT '系统类型(1: 品茗标准体系;2: 本地标准体系)',
  `province_id` bigint(20) NOT NULL COMMENT '品茗标准体系省别ID/本地标准体系的特征标准',
  `province_name` varchar(255) DEFAULT NULL COMMENT '省别(冗余)',
  `sys_name` varchar(255) DEFAULT NULL COMMENT '体系名',
  `sys_code` varchar(255) DEFAULT NULL COMMENT '体系编码',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '是否被删除(0: 否; 1: 是)',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='特征体系';

-- ----------------------------
-- Records of ctag_sys
-- ----------------------------

-- ----------------------------
-- Table structure for ctag_sys_item
-- ----------------------------
DROP TABLE IF EXISTS `ctag_sys_item`;
CREATE TABLE `ctag_sys_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) DEFAULT NULL COMMENT '特征项名称',
  `pid` bigint(20) DEFAULT NULL COMMENT '父级ID',
  `level` int(11) DEFAULT NULL COMMENT '层级',
  `item_type` tinyint(1) DEFAULT NULL COMMENT '特征项类型(1: 字符串;2: 数值;3:枚举)',
  `item_remark` varchar(255) DEFAULT NULL COMMENT '特征项备注',
  `item_str_length_limit` int(11) DEFAULT NULL COMMENT '字符串类型的长度限制',
  `item_num_allow_decimal` tinyint(1) DEFAULT '1' COMMENT '是否允许有小数(0: 否;1: 是)',
  `item_enum_allow_multi` tinyint(1) DEFAULT NULL COMMENT '枚举型是否允许多选',
  `sys_id` bigint(20) DEFAULT NULL COMMENT '所属的体系',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='特征项';

-- ----------------------------
-- Records of ctag_sys_item
-- ----------------------------

-- ----------------------------
-- Table structure for ctag_sys_item_enum
-- ----------------------------
DROP TABLE IF EXISTS `ctag_sys_item_enum`;
CREATE TABLE `ctag_sys_item_enum` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(255) DEFAULT NULL COMMENT '特征项的枚举名',
  `sys_item_id` bigint(20) DEFAULT NULL COMMENT '所属的特征项ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='特征项枚举';

-- ----------------------------
-- Records of ctag_sys_item_enum
-- ----------------------------

-- ----------------------------
-- Table structure for ctag_sys_match
-- ----------------------------
DROP TABLE IF EXISTS `ctag_sys_match`;
CREATE TABLE `ctag_sys_match` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `local_sys_id` bigint(20) NOT NULL COMMENT '本地标准体系ID',
  `pm_sys_id` bigint(20) NOT NULL COMMENT '品茗标准体系ID',
  `is_updated` tinyint(1) DEFAULT '0' COMMENT '是否更新(0: 否；1:是)',
  `gmt_standard_updated` datetime DEFAULT NULL COMMENT '标准更新时间',
  `gmt_match_modified` datetime DEFAULT NULL COMMENT '匹配更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='特征项匹配';

-- ----------------------------
-- Records of ctag_sys_match
-- ----------------------------

-- ----------------------------
-- Table structure for ctag_sys_match_detail
-- ----------------------------
DROP TABLE IF EXISTS `ctag_sys_match_detail`;
CREATE TABLE `ctag_sys_match_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `project_id` bigint(20) NOT NULL COMMENT '工程ID',
  `local_sys_id` bigint(20) DEFAULT NULL COMMENT '所属的本地标准体系ID(冗余)',
  `local_sys_item_id` bigint(20) NOT NULL COMMENT '本地标准体系特征项ID',
  `pm_sys_item_id` bigint(20) NOT NULL COMMENT '品茗标准体系特征项ID',
  `ctag_value` varchar(255) DEFAULT NULL COMMENT '填充值',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='特征项匹配详情';

-- ----------------------------
-- Records of ctag_sys_match_detail
-- ----------------------------
