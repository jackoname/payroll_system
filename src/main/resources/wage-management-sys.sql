/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : wage-management-sys

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2023-04-10 01:30:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for about_edu
-- ----------------------------
DROP TABLE IF EXISTS `about_edu`;
CREATE TABLE `about_edu` (
  `edu_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `mean` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dsc` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `flag` int(11) DEFAULT NULL,
  PRIMARY KEY (`edu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of about_edu
-- ----------------------------
INSERT INTO `about_edu` VALUES ('1', '高中及以下', '教育程度', '1');
INSERT INTO `about_edu` VALUES ('2', '专科', '教育程度', '1');
INSERT INTO `about_edu` VALUES ('3', '本科', '教育程度', '1');
INSERT INTO `about_edu` VALUES ('4', '硕士研究生', '教育程度', '1');
INSERT INTO `about_edu` VALUES ('5', '学士学位', '学位', '2');
INSERT INTO `about_edu` VALUES ('6', '硕士学位', '学位', '2');
INSERT INTO `about_edu` VALUES ('7', '博士学位', '学位', '2');
INSERT INTO `about_edu` VALUES ('8', '博士研究生', '教育程度', '1');
INSERT INTO `about_edu` VALUES ('9', '群众', '政治面貌', '3');
INSERT INTO `about_edu` VALUES ('10', '团员', '政治面貌', '3');
INSERT INTO `about_edu` VALUES ('11', '中共党员', '政治面貌', '3');

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `depart` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `depart_boss` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dsc` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('研发部', '1', '欧阳洪健', '牛逼的人ss');
INSERT INTO `department` VALUES ('销售部', '2', '李珊珊', null);
INSERT INTO `department` VALUES ('实施部', '3', '李大炮', null);

-- ----------------------------
-- Table structure for dep_user
-- ----------------------------
DROP TABLE IF EXISTS `dep_user`;
CREATE TABLE `dep_user` (
  `udid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `depid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`udid`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of dep_user
-- ----------------------------
INSERT INTO `dep_user` VALUES ('2', '28', '1');
INSERT INTO `dep_user` VALUES ('12', '29', '1');
INSERT INTO `dep_user` VALUES ('20', '1', '29');
INSERT INTO `dep_user` VALUES ('23', '1', '28');
INSERT INTO `dep_user` VALUES ('26', '1', '3');
INSERT INTO `dep_user` VALUES ('27', '30', '1');
INSERT INTO `dep_user` VALUES ('29', '31', '1');
INSERT INTO `dep_user` VALUES ('30', '3', '31');
INSERT INTO `dep_user` VALUES ('31', '32', '1');
INSERT INTO `dep_user` VALUES ('32', '33', '1');
INSERT INTO `dep_user` VALUES ('33', '1', '32');
INSERT INTO `dep_user` VALUES ('34', '1', '33');
INSERT INTO `dep_user` VALUES ('36', '35', '1');
INSERT INTO `dep_user` VALUES ('37', '1', '30');

-- ----------------------------
-- Table structure for list_type
-- ----------------------------
DROP TABLE IF EXISTS `list_type`;
CREATE TABLE `list_type` (
  `list_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dsc` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `createtime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`list_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of list_type
-- ----------------------------
INSERT INTO `list_type` VALUES ('1', '', null, null);

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `menuid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `component` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `path` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `redirect` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `icon` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `isleaf` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`menuid`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', 'Layout', '/sys', '/sys/user', 'sysManage', '公司管理', 'el-icon-office-building', '0', 'N');
INSERT INTO `menu` VALUES ('2', 'sys/user', 'user', null, 'userList', '职工管理', 'el-icon-user', '1', 'Y');
INSERT INTO `menu` VALUES ('3', 'sys/role', 'role', null, 'roleList', '角色管理', 'el-icon-key', '1', 'Y');
INSERT INTO `menu` VALUES ('4', 'Layout', '/syspay', '/syspay/payroll', '工资管理模块', '工资管理', 'el-icon-s-finance\r\nel-icon-s-finance\r\nel-icon-s-finance\r\nel-icon-s-finance\r\nel-icon-s-finance', '0', 'N');
INSERT INTO `menu` VALUES ('5', 'syspay/payroll', 'payroll', '', '薪资结构管理', '薪资结构管理', 'form', '4', 'Y');
INSERT INTO `menu` VALUES ('6', 'syspay/performance', 'performance', '', '绩效管理', '绩效管理', 'form', '4', 'Y');
INSERT INTO `menu` VALUES ('7', 'syspay/attendance', 'attendance', '', '考勤管理', '考勤管理', 'form', '4', 'Y');
INSERT INTO `menu` VALUES ('8', 'Layout', '/mypay', '/mypay/mypayroll', '我的工资', '我的工资', 'form', '0', 'N');
INSERT INTO `menu` VALUES ('9', 'mypay/mypayroll', 'mypayroll', '', '工资明细', '工资明细', 'form', '8', 'Y');
INSERT INTO `menu` VALUES ('10', 'mypay/mypayroll', 'mypayroll', '', '工资明细', '工资明细', 'form', '8', 'Y');
INSERT INTO `menu` VALUES ('11', 'Layout', '/mypay', '/mypay/mypayroll', '我的工资', '我的工资', 'form', '0', 'N');
INSERT INTO `menu` VALUES ('12', 'mypay/mypayroll', 'mypayroll', '', '工资明细', '工资明细', 'form', '11', 'Y');
INSERT INTO `menu` VALUES ('13', 'mypay/mypayroll', 'mypayroll', '', '工资明细', '工资明细', 'suitcase-', '11', 'Y');
INSERT INTO `menu` VALUES ('14', 'sys/department', 'department', null, 'depList', '部门管理', 'el-icon-suitcase', '1', 'Y');
INSERT INTO `menu` VALUES ('15', 'sys/post', 'post', null, 'postList', '职位管理', 'el-icon-monitor', '1', 'Y');

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `post_id` int(10) NOT NULL AUTO_INCREMENT,
  `depid` int(11) NOT NULL,
  `dsc` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `post_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `isuse` int(1) unsigned DEFAULT NULL,
  PRIMARY KEY (`post_id`),
  KEY `dpfk` (`depid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES ('1', '1', '', '实习生', null);
INSERT INTO `post` VALUES ('2', '1', null, 'JAVA开发工程师', null);
INSERT INTO `post` VALUES ('3', '1', null, '前端工程师', null);
INSERT INTO `post` VALUES ('4', '3', '', '产品经理', null);
INSERT INTO `post` VALUES ('7', '1', '', 'c++开发工程师', null);

-- ----------------------------
-- Table structure for post_dep_user
-- ----------------------------
DROP TABLE IF EXISTS `post_dep_user`;
CREATE TABLE `post_dep_user` (
  `pduid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `postid` int(11) DEFAULT NULL,
  `depid` int(11) DEFAULT NULL,
  PRIMARY KEY (`pduid`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of post_dep_user
-- ----------------------------
INSERT INTO `post_dep_user` VALUES ('24', '29', '3', '1');
INSERT INTO `post_dep_user` VALUES ('27', '28', '2', '1');
INSERT INTO `post_dep_user` VALUES ('30', '3', '2', '1');
INSERT INTO `post_dep_user` VALUES ('34', '31', '4', '3');
INSERT INTO `post_dep_user` VALUES ('37', '32', '1', '1');
INSERT INTO `post_dep_user` VALUES ('38', '33', '1', '1');
INSERT INTO `post_dep_user` VALUES ('39', '34', '1', '1');
INSERT INTO `post_dep_user` VALUES ('40', '35', '7', '1');
INSERT INTO `post_dep_user` VALUES ('41', '30', '7', '1');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roledec` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `rolename` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `roletag` (`roledec`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('7', '111', '超级管理员');
INSERT INTO `role` VALUES ('8', '2', '财务管理员');
INSERT INTO `role` VALUES ('13', '3', '普通职工');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `role_id` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=208 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('105', '3', '2');
INSERT INTO `role_menu` VALUES ('106', '3', '5');
INSERT INTO `role_menu` VALUES ('107', '3', '6');
INSERT INTO `role_menu` VALUES ('108', '3', '1');
INSERT INTO `role_menu` VALUES ('109', '3', '4');
INSERT INTO `role_menu` VALUES ('123', '8', '2');
INSERT INTO `role_menu` VALUES ('124', '8', '5');
INSERT INTO `role_menu` VALUES ('125', '8', '1');
INSERT INTO `role_menu` VALUES ('126', '8', '4');
INSERT INTO `role_menu` VALUES ('131', '3', '3');
INSERT INTO `role_menu` VALUES ('132', '3', '7');
INSERT INTO `role_menu` VALUES ('182', '7', '1');
INSERT INTO `role_menu` VALUES ('183', '7', '2');
INSERT INTO `role_menu` VALUES ('184', '7', '3');
INSERT INTO `role_menu` VALUES ('185', '7', '14');
INSERT INTO `role_menu` VALUES ('186', '7', '15');
INSERT INTO `role_menu` VALUES ('187', '7', '4');
INSERT INTO `role_menu` VALUES ('188', '7', '5');
INSERT INTO `role_menu` VALUES ('189', '7', '6');
INSERT INTO `role_menu` VALUES ('190', '7', '7');
INSERT INTO `role_menu` VALUES ('191', '7', '8');
INSERT INTO `role_menu` VALUES ('192', '7', '9');
INSERT INTO `role_menu` VALUES ('193', '7', '10');
INSERT INTO `role_menu` VALUES ('194', '7', '11');
INSERT INTO `role_menu` VALUES ('195', '7', '12');
INSERT INTO `role_menu` VALUES ('196', '7', '13');
INSERT INTO `role_menu` VALUES ('203', '13', '1');
INSERT INTO `role_menu` VALUES ('204', '13', '2');
INSERT INTO `role_menu` VALUES ('205', '13', '3');
INSERT INTO `role_menu` VALUES ('206', '13', '14');
INSERT INTO `role_menu` VALUES ('207', '13', '15');

-- ----------------------------
-- Table structure for staff_wage
-- ----------------------------
DROP TABLE IF EXISTS `staff_wage`;
CREATE TABLE `staff_wage` (
  `wage_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '员工id和信息id一致',
  `basic_wage` double DEFAULT '0' COMMENT '基本工资',
  `per_in_tax` double DEFAULT '0' COMMENT '个人所得税',
  `endowment_in` double DEFAULT '0' COMMENT '基本养老保险',
  `medical_in` double DEFAULT '0' COMMENT '基本医疗保险',
  `unemployment_in` double DEFAULT '0' COMMENT '失业保险',
  `employment_injury_in` double DEFAULT '0' COMMENT '工商保险',
  `maternity_in` double DEFAULT '0' COMMENT '生育保险',
  `housing_acc_fund` double DEFAULT '0' COMMENT '住房公积金',
  `performance` double DEFAULT '0' COMMENT '当月绩效',
  `bonus` double DEFAULT '0' COMMENT '奖金',
  `fine` double DEFAULT '0' COMMENT '罚款金额',
  `leave_days` int(10) NOT NULL DEFAULT '0' COMMENT '缺勤天数',
  `other_deduct` double DEFAULT '0' COMMENT '其他扣除',
  `Comprehensive_salary` double DEFAULT '0' COMMENT '综合工资',
  `hand_out` int(2) DEFAULT '0' COMMENT '工资发放状态',
  `state` int(25) unsigned DEFAULT '0' COMMENT '状态',
  `user_id` int(11) unsigned DEFAULT NULL,
  `begin` datetime DEFAULT NULL,
  `end` datetime DEFAULT NULL,
  `creatTime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`wage_id`),
  KEY `sfk` (`state`),
  KEY `ufk` (`user_id`),
  CONSTRAINT `sfk` FOREIGN KEY (`state`) REFERENCES `state` (`state_id`),
  CONSTRAINT `ufk` FOREIGN KEY (`user_id`) REFERENCES `users` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of staff_wage
-- ----------------------------

-- ----------------------------
-- Table structure for state
-- ----------------------------
DROP TABLE IF EXISTS `state`;
CREATE TABLE `state` (
  `state_id` int(25) unsigned NOT NULL AUTO_INCREMENT,
  `state_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `state_dec` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`state_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of state
-- ----------------------------
INSERT INTO `state` VALUES ('0', '初始', null);
INSERT INTO `state` VALUES ('1', '待处理', null);
INSERT INTO `state` VALUES ('2', '处理中', null);
INSERT INTO `state` VALUES ('3', '已完成', null);

-- ----------------------------
-- Table structure for taxandsafe
-- ----------------------------
DROP TABLE IF EXISTS `taxandsafe`;
CREATE TABLE `taxandsafe` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tax_me` double(11,5) DEFAULT NULL,
  `submoney` double(11,5) DEFAULT NULL,
  `dsc` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `flag` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `tax_com` double(11,5) DEFAULT NULL,
  `lowmoney` double(11,5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of taxandsafe
-- ----------------------------
INSERT INTO `taxandsafe` VALUES ('1', '0.00000', '5000.00000', '工资范围在1-5000元之间的，包括5000元，适用个人所得税税率为0%；', '个税', '1', null, '1.00000');
INSERT INTO `taxandsafe` VALUES ('2', '0.03000', '8000.00000', '工资范围在5000-8000元之间的，包括8000元，适用个人所得税税率为3%；', '个税', '1', '0.00000', '5000.00000');
INSERT INTO `taxandsafe` VALUES ('3', '0.10000', '17000.00000', '工资范围在8000-17000元之间的，包括17000元，适用个人所得税税率为10%；', '个税', '1', null, '8000.00000');
INSERT INTO `taxandsafe` VALUES ('4', '0.20000', '30000.00000', '工资范围在17000-30000元之间的，包括30000元，适用个人所得税税率为20%；', '个税', '1', null, '17000.00000');
INSERT INTO `taxandsafe` VALUES ('5', '0.25000', '40000.00000', '工资范围在30000-40000元之间的，包括40000元，适用个人所得税税率为25%；', '个税', '1', null, '30000.00000');
INSERT INTO `taxandsafe` VALUES ('6', '0.30000', '60000.00000', '工资范围在40000-60000元之间的，包括60000元，适用个人所得税税率为30%；', '个税', '1', null, '40000.00000');
INSERT INTO `taxandsafe` VALUES ('7', '0.35000', '85000.00000', '工资范围在60000-85000元之间的，包括85000元，适用个人所得税税率为35%；', '个税', '1', null, '60000.00000');
INSERT INTO `taxandsafe` VALUES ('8', '0.45000', null, '工资范围在85000元以上的，适用个人所得税税率为45%。', '个税', '1', null, '85000.00000');
INSERT INTO `taxandsafe` VALUES ('9', '0.08000', null, '养老保险：用人单位按照20%的基数进行缴纳，个人按照8%的比例进行扣缴', '五险一金', '2', '0.20000', null);
INSERT INTO `taxandsafe` VALUES ('10', '0.02000', null, '医疗保险：用人单位按照10%的比例进行缴费；个人按照2%的比例扣缴；', '五险一金', '2', '0.10000', null);
INSERT INTO `taxandsafe` VALUES ('11', '0.00500', null, '失业保险：用人单位按照1.5%的比例缴费，个人按照0.5%的比例扣缴；', '五险一金', '2', '0.01500', null);
INSERT INTO `taxandsafe` VALUES ('12', '0.01000', null, '工伤保险：用人单位按照1%的比例缴费，个人不需要缴纳；', '五险一金', '2', '0.00000', null);
INSERT INTO `taxandsafe` VALUES ('13', '0.00800', null, '生育保险：用人单位按照0.8%比例缴费，个人不需要缴纳；', '五险一金', '2', '0.00000', null);
INSERT INTO `taxandsafe` VALUES ('14', '0.08000', null, '公积金：个人所需承担的缴费基数与用人单位是一致的，都是8%。', '五险一金', '2', '0.08000', null);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `userId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `isuse` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `depid` int(11) NOT NULL,
  `postid` int(11) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `remarks` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `sex` int(1) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `createtime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `bankno` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idcardno` varchar(18) COLLATE utf8_unicode_ci DEFAULT NULL,
  `photo` varchar(5000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `education` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `school` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `education_xw` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pappreance` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `edu_major` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`userId`),
  KEY `defk` (`depid`),
  KEY `psfk` (`postid`),
  CONSTRAINT `defk` FOREIGN KEY (`depid`) REFERENCES `department` (`id`),
  CONSTRAINT `psfk` FOREIGN KEY (`postid`) REFERENCES `post` (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('3', 'admin', '$2a$10$F8ZZsvjHp0Q5ehJ/yVGDDOZTsp2OKmTfPGRoPZSneIRdepjnGzFGe', '1', '13128281923', '599345199@qq.com', '1', '2', '18', '优秀', '广州', '1', '1999-01-08', '2023-04-09 19:19:20', '欧阳洪健', '12345646879789', '441625199901083338', '\"https://img1.baidu.com/it/u=4096959686,4145726040&fm=253&fmt=auto&app=138&f=JPEG?sec=1683017670&t=88663d7cd6f8d44dcd99a2d02f38584f\"', null, null, null, null, null);
INSERT INTO `users` VALUES ('28', 'ces', '$2a$10$zwwyQErLiMNUPwuX7UqLDO5M9ywEW8diTMfwYY73QGtkItB2b1gkK', '1', '13128596471', '599345199@qq.com', '1', '2', '12', '', '威威', '1', '2022-04-05', '2023-04-08 14:44:23', '海绵宝宝', '123232313123', '123321232321312', null, null, null, null, null, null);
INSERT INTO `users` VALUES ('29', 'yanjie', '$2a$10$0SjUHAyJ8EUww33ECDF79.CozmI78WkLAPKbhLtLWEdOslIgjagTu', '0', '13569594632', '515@qq.com', '1', '3', '28', null, null, '2', '2023-04-19', '2023-04-09 15:19:39', '燕飞', '489654125633', '445698777155535563', null, null, null, null, null, null);
INSERT INTO `users` VALUES ('30', 'htl', '$2a$10$/Ilet1ElY.AFQM53OwDTvO2KQkaQsod6NA9POwgjmhSrNIH4fqdOS', '1', '15896969165', '4545646@163.com', '1', '7', '33', null, null, '1', '2016-05-11', '2023-04-09 22:45:17', '灰太狼', '74588484994', '114561112528888627', null, null, null, null, null, null);
INSERT INTO `users` VALUES ('31', 'pdx', '$2a$10$ZBmKx0BPfQ5rbQMoZ/5PpOSB5swQzBQos45GRXBbAwalAG6zfuzUO', '0', '13255599632', '5895959@ww.com', '3', '4', '17', null, null, '1', '2023-04-21', '2023-04-09 19:34:06', '派大星', '2323535253251', '488523699987441', null, null, null, null, null, null);
INSERT INTO `users` VALUES ('32', 'zyg', '$2a$10$gfRHJYVXSO4EX8580DRyLuMi9NaO/vRXpqJ0Y5Pl7.r9wDc8F4Z1K', '1', '15233369654', '85526@99.com', '1', '1', '15', null, null, '2', '2023-04-10', '2023-04-09 22:17:58', '章鱼哥', '85465542250255', '478541233695545', null, null, null, null, null, null);
INSERT INTO `users` VALUES ('33', 'xlb', '$2a$10$1fXG/kf.RBvJCFmwuXOSGOEMILXzL1XQWyaMbSNjNbWoRsNQcuoBm', '1', '13558621555', '45445@qq.com', '1', '1', '32', 'dfd', null, '1', '2023-04-05', '2023-04-09 22:18:10', '蟹老板', '856654645456', '131596484564441414', null, null, null, null, null, null);
INSERT INTO `users` VALUES ('35', 'ggBoy', '$2a$10$XEOLQGNUip83xAK52uJ9SOEzTvt2WmOTn.t1Erkw2if76n0Ydm9M2', '0', '13128281923', '1@qq.com', '1', '7', '23', '', '', '1', '2023-04-06', null, '猪猪侠', '3123231232323', '122233331232332312', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `roleid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rk` (`roleid`),
  CONSTRAINT `rk` FOREIGN KEY (`roleid`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('34', '29', '8');
INSERT INTO `user_role` VALUES ('37', '28', '8');
INSERT INTO `user_role` VALUES ('40', '3', '7');
INSERT INTO `user_role` VALUES ('44', '31', '8');
INSERT INTO `user_role` VALUES ('47', '32', '8');
INSERT INTO `user_role` VALUES ('48', '33', '8');
INSERT INTO `user_role` VALUES ('50', '35', '8');
INSERT INTO `user_role` VALUES ('51', '30', '13');

-- ----------------------------
-- Table structure for usestate
-- ----------------------------
DROP TABLE IF EXISTS `usestate`;
CREATE TABLE `usestate` (
  `isusid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `usestate` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `enable` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`isusid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of usestate
-- ----------------------------
INSERT INTO `usestate` VALUES ('1', '正常', '1');
INSERT INTO `usestate` VALUES ('2', '禁用', '0');
INSERT INTO `usestate` VALUES ('3', '危险', '0');
INSERT INTO `usestate` VALUES ('4', '已离职', '4');

-- ----------------------------
-- Table structure for wage_list
-- ----------------------------
DROP TABLE IF EXISTS `wage_list`;
CREATE TABLE `wage_list` (
  `list_id` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `wage_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `wage` double(255,0) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `user_id` int(11) unsigned DEFAULT NULL,
  `creattime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `endtime` datetime DEFAULT NULL,
  `state` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `des` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `listfk` (`list_id`),
  CONSTRAINT `listfk` FOREIGN KEY (`list_id`) REFERENCES `list_type` (`list_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of wage_list
-- ----------------------------
INSERT INTO `wage_list` VALUES ('1', '1', '基本工资', '2000', '1', '1', null, null, null, null);
