/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : eorder

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2014-11-27 23:28:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `function`
-- ----------------------------
DROP TABLE IF EXISTS `function`;
CREATE TABLE `function` (
  `function_name` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'the name of function',
  `function_desc` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'detail information',
  `function_display` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'content of display on the GUI or menu',
  `function_path` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'the access path to this function',
  `function_parent` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'parent function',
  `function_order` varchar(6) COLLATE utf8_bin NOT NULL COMMENT 'use to organize menu in order\r\n            the top function should be a integer as such 100000, 140000\r\n            the first 2 bit is for top function',
  `function_enable` tinyint(1) NOT NULL,
  PRIMARY KEY (`function_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='define system functions & path';

-- ----------------------------
-- Records of function
-- ----------------------------
INSERT INTO `function` VALUES ('aaaaa', '', '', '', '', '', '0');
INSERT INTO `function` VALUES ('abc', 'abc', 'abc', 'abc', 'abc', 'abc', '0');
INSERT INTO `function` VALUES ('abcd', 'abcdxxx', 'abcd', 'abcd', 'abcd', 'abcd', '0');
INSERT INTO `function` VALUES ('doAdminFileUploadTransaction', 'doFileUploadTransaction', 'File Upload Transaction', '/admin/doFileUploadTransaction.action', 'doAdministration', '020400', '1');
INSERT INTO `function` VALUES ('doAdministration', 'doAdministration', 'Administration', null, null, '020000', '1');
INSERT INTO `function` VALUES ('doBankPayment', 'doBankPayment', 'Bank Payment', '/admin/doBankPayment.action', 'doAdministration', '020300', '1');
INSERT INTO `function` VALUES ('doBankProfileSetup', 'doBankProfileSetup', 'Bank Profile Setup', '/admin/doBankProfileSetup.action', 'doAdministration', '020200', '1');
INSERT INTO `function` VALUES ('doBankRegistration', 'doBankRegistration', 'Bank Registration', '/admin/doBankRegistration.action', 'doAdministration', '020100', '1');
INSERT INTO `function` VALUES ('doFileUpload', 'doFileUpload', 'File Upload', '/reportentity/doFileUpload.action', 'doReportEntity', '010100', '1');
INSERT INTO `function` VALUES ('doFunction', 'doFunction', '功能管理', '/function/doFunction.action', 'doAdministration', '020600', '1');
INSERT INTO `function` VALUES ('doReportEntity', 'doReportEntity', 'Report Entity', null, null, '010000', '1');
INSERT INTO `function` VALUES ('doReportFileUploadTransaction', 'doFileUploadTransaction', 'File Upload Transaction', '/reportentity/doFileUploadTransaction.action', 'doReportEntity', '010200', '1');
INSERT INTO `function` VALUES ('doRole', 'doRole', '角色管理', '/role/doRole.action', 'doAdministration', '020700', '1');
INSERT INTO `function` VALUES ('doRoleFunction', 'doRoleFunction', '角色功能关联', '/rolefunction/doRoleFunction.action', 'doAdministration', '020900', '1');
INSERT INTO `function` VALUES ('doSystemSettings', 'doSystemSettings', 'System Settings', '/admin/doSystemSettings.action', 'doAdministration', '020500', '1');
INSERT INTO `function` VALUES ('doUser', 'doUser', '用户管理', '/user/doUser.action', 'doAdministration', '020800', '1');
INSERT INTO `function` VALUES ('doUserRole', 'doUserRole', '用户角色关联', '/userrole/doUserRole.action', 'doAdministration', '021000', '1');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `role_name` varchar(20) COLLATE utf8_bin NOT NULL,
  `role_desc` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `role_enable` tinyint(1) NOT NULL,
  PRIMARY KEY (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('ROLE_admin', '管理员', '1');
INSERT INTO `role` VALUES ('ROLE_normal', '普通用户', '1');
INSERT INTO `role` VALUES ('abcd', 'abcd', '0');

-- ----------------------------
-- Table structure for `role_function`
-- ----------------------------
DROP TABLE IF EXISTS `role_function`;
CREATE TABLE `role_function` (
  `role_function_name` varchar(200) COLLATE utf8_bin NOT NULL COMMENT 'role_function = role_name + "_" + function_name',
  `role_name` varchar(100) COLLATE utf8_bin NOT NULL,
  `function_name` varchar(100) COLLATE utf8_bin NOT NULL COMMENT 'the name of function',
  PRIMARY KEY (`role_function_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of role_function
-- ----------------------------
INSERT INTO `role_function` VALUES ('ROLE_admindoAdministration', 'ROLE_admin', 'doAdministration');
INSERT INTO `role_function` VALUES ('ROLE_admindoFunction', 'ROLE_admin', 'doFunction');
INSERT INTO `role_function` VALUES ('ROLE_admindoRole', 'ROLE_admin', 'doRole');
INSERT INTO `role_function` VALUES ('ROLE_admindoRoleFunction', 'ROLE_admin', 'doRoleFunction');
INSERT INTO `role_function` VALUES ('ROLE_admindoUser', 'ROLE_admin', 'doUser');
INSERT INTO `role_function` VALUES ('ROLE_admindoUserRole', 'ROLE_admin', 'doUserRole');
INSERT INTO `role_function` VALUES ('ROLE_normaldoFileUpload', 'ROLE_normal', 'doFileUpload');
INSERT INTO `role_function` VALUES ('ROLE_normaldoFileUploadTransaction', 'ROLE_normal', 'doReportFileUploadTransaction');
INSERT INTO `role_function` VALUES ('ROLE_normaldoFunction', 'ROLE_normal', 'doFunction');
INSERT INTO `role_function` VALUES ('ROLE_normaldoRole', 'ROLE_normal', 'doRole');
INSERT INTO `role_function` VALUES ('ROLE_normaldoRoleFunction', 'ROLE_normal', 'doRoleFunction');
INSERT INTO `role_function` VALUES ('abcddoFunction', 'abcd', 'doFunction');
INSERT INTO `role_function` VALUES ('abcddoRole', 'abcd', 'doRole');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_name` varchar(20) COLLATE utf8_bin NOT NULL,
  `user_password` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `report_entity_name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `user_enable` tinyint(1) DEFAULT NULL,
  `user_register_date` date DEFAULT NULL,
  `user_expiration_date` date DEFAULT NULL,
  `user_passwd_exp_date` date DEFAULT NULL,
  `user_email` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `user_question1` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `user_answer1` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `user_question2` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `user_answer2` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `user_question3` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `user_answer3` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='user''s basic information';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('aaa', '851836affb5d9b3dd7938c9b7ff7cb15', null, '1', null, null, null, 'aaa@qq.com', null, null, null, null, null, null);
INSERT INTO `user` VALUES ('abcd', '3e8c66c245c9cf2769741a3bb2264776', null, '1', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `user` VALUES ('admin', 'ceb4f32325eda6142bd65215f4c0f371', null, '1', '2012-02-22', '2012-09-29', '2012-09-08', 'admin@hp.com', null, null, null, null, null, null);
INSERT INTO `user` VALUES ('test', '889255f1c9c8f12a353be255f78a848b', null, '1', '2012-02-22', '2012-09-29', '2012-09-08', 'linx@hp.com', null, null, null, null, null, null);
INSERT INTO `user` VALUES ('user01', '6f808b877e68ef68525656c97220d719', null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_role_name` varchar(40) COLLATE utf8_bin NOT NULL COMMENT 'user_role = user_name + "_" + role_name',
  `user_name` varchar(20) COLLATE utf8_bin NOT NULL,
  `role_name` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`user_role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('adminROLE_aaa', 'aaa', 'ROLE_admin');
INSERT INTO `user_role` VALUES ('adminROLE_admin', 'admin', 'ROLE_admin');
INSERT INTO `user_role` VALUES ('normalROLE_test', 'test', 'ROLE_normal');
