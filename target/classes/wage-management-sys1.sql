/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : wage-management-sys

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2023-04-17 02:23:48
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
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of dep_user
-- ----------------------------
INSERT INTO `dep_user` VALUES ('2', '28', '1');
INSERT INTO `dep_user` VALUES ('12', '29', '1');
INSERT INTO `dep_user` VALUES ('20', '1', '29');
INSERT INTO `dep_user` VALUES ('23', '1', '28');
INSERT INTO `dep_user` VALUES ('27', '30', '1');
INSERT INTO `dep_user` VALUES ('29', '31', '1');
INSERT INTO `dep_user` VALUES ('30', '3', '31');
INSERT INTO `dep_user` VALUES ('31', '32', '1');
INSERT INTO `dep_user` VALUES ('32', '33', '1');
INSERT INTO `dep_user` VALUES ('33', '1', '32');
INSERT INTO `dep_user` VALUES ('34', '1', '33');
INSERT INTO `dep_user` VALUES ('36', '35', '1');
INSERT INTO `dep_user` VALUES ('37', '1', '30');
INSERT INTO `dep_user` VALUES ('38', '37', '1');

-- ----------------------------
-- Table structure for list_type
-- ----------------------------
DROP TABLE IF EXISTS `list_type`;
CREATE TABLE `list_type` (
  `list_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dsc` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `createtime` datetime NOT NULL,
  `flag` int(2) NOT NULL,
  `tag` int(11) DEFAULT NULL,
  `money` double(255,0) DEFAULT NULL,
  PRIMARY KEY (`list_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of list_type
-- ----------------------------
INSERT INTO `list_type` VALUES ('4', '早退', null, '2023-04-12 00:00:00', '0', '8', '-50');
INSERT INTO `list_type` VALUES ('5', '上班迟到', null, '2023-04-12 00:00:00', '0', '8', '-20');
INSERT INTO `list_type` VALUES ('6', '业绩突出', null, '2023-04-12 01:13:59', '1', '7', '1200');
INSERT INTO `list_type` VALUES ('7', '奖励', null, '2023-04-04 21:14:02', '999', '0', '0');
INSERT INTO `list_type` VALUES ('8', '罚款', null, '2023-04-14 21:14:30', '-999', '0', null);
INSERT INTO `list_type` VALUES ('15', '补贴', null, '2023-04-15 00:26:04', '999', '0', null);
INSERT INTO `list_type` VALUES ('16', '其它', null, '2023-04-16 00:30:23', '-78', '0', null);
INSERT INTO `list_type` VALUES ('18', '全勤奖', null, '2023-04-16 13:39:55', '0', '7', '300');
INSERT INTO `list_type` VALUES ('19', '缺勤', null, '2023-04-16 20:42:07', '0', '8', '-100');
INSERT INTO `list_type` VALUES ('9999', '其它', null, '2023-04-16 00:30:23', '-78', '16', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', 'Layout', '/sys', '/sys/user', 'sysManage', '公司管理', 'el-icon-office-building', '0', 'N');
INSERT INTO `menu` VALUES ('2', 'sys/user', 'user', null, 'userList', '职工管理', 'el-icon-user', '1', 'Y');
INSERT INTO `menu` VALUES ('3', 'sys/role', 'role', null, 'roleList', '角色管理', 'el-icon-key', '1', 'Y');
INSERT INTO `menu` VALUES ('4', 'Layout', '/syspay', '/syspay/payroll', '工资管理模块', '工资管理', 'el-icon-money', '0', 'N');
INSERT INTO `menu` VALUES ('5', 'syspay/wagemanger', 'wagemanger', '', '职工工资管理', '基本工资管理', 'el-icon-wallet', '4', 'Y');
INSERT INTO `menu` VALUES ('6', 'syspay/performance', 'performance', '', '绩效工资管理', '绩效工资管理', 'el-icon-s-marketing', '4', 'Y');
INSERT INTO `menu` VALUES ('7', 'syspay/addoption', 'syspay/addoption', '', '奖惩事项管理管理', '奖惩事项管理管理', 'el-icon-edit', '4', 'Y');
INSERT INTO `menu` VALUES ('8', 'Layout', '/mypay', '/mypay/mypayroll', '工资核算', '工资核算', 'el-icon-coordinate', '0', 'N');
INSERT INTO `menu` VALUES ('9', 'mypay/culwage', 'culwage', '', '工资计算', '工资计算', 'el-icon-monitor', '8', 'Y');
INSERT INTO `menu` VALUES ('10', 'mypay/mypayroll', 'mypayroll', '', '工资审核', '工资审核', 'form', '8', 'Y');
INSERT INTO `menu` VALUES ('11', 'Layout', '/mypay', '/mypay/mypayroll', '工资放发', '工资发放', 'el-icon-finished', '0', 'N');
INSERT INTO `menu` VALUES ('12', 'mypay/mypayroll', 'mypayroll', '', '工资明细', '工资明细', 'form', '11', 'Y');
INSERT INTO `menu` VALUES ('13', 'mypay/mypayroll', 'mypayroll', '', '工资明细', '工资明细', 'suitcase-', '11', 'Y');
INSERT INTO `menu` VALUES ('14', 'sys/department', 'department', null, 'depList', '部门管理', 'el-icon-suitcase', '1', 'Y');
INSERT INTO `menu` VALUES ('15', 'sys/post', 'post', null, 'postList', '职位管理', 'el-icon-monitor', '1', 'Y');
INSERT INTO `menu` VALUES ('16', 'Layout', '/reimbursement', '/reimbursement/payroll', '报销管理', '报销管理', 'form', '0', 'N');
INSERT INTO `menu` VALUES ('17', 'Layout', '/feedback', '/feedback/payroll', '反馈管理', '反馈管理', 'form', '0', 'N');
INSERT INTO `menu` VALUES ('18', 'Layout', '/wagelist', '/wagelist/payroll', '工资报表管理', '工资报表管理', 'form', '0', 'N');
INSERT INTO `menu` VALUES ('19', 'Layout', '/mywage', '/mywage/payroll', '我的工资', '我的工资', 'form', '0', 'N');
INSERT INTO `menu` VALUES ('20', 'Layout', '/myreimbursement', '/myreimbursement/payroll', '报销申请', '报销申请', 'form', '0', 'N');
INSERT INTO `menu` VALUES ('21', 'Layout', '/myadvice', '/myadvice/payroll', '反馈意见', '反馈意见', 'form', '0', 'N');
INSERT INTO `menu` VALUES ('22', 'myadvice/payroll', 'payroll', null, null, '反馈意见', 'form', '21', 'Y');
INSERT INTO `menu` VALUES ('23', 'myreimbursement/payroll', 'payroll', null, null, '报销申请', 'form', '20', 'Y');
INSERT INTO `menu` VALUES ('24', 'mywage/payroll', 'payroll', null, null, '我的工资', 'form', '19', 'Y');
INSERT INTO `menu` VALUES ('25', 'wagelist/payroll', 'payroll', null, null, '工资报表管理', 'form', '18', 'Y');
INSERT INTO `menu` VALUES ('26', 'feedback/payroll', 'payroll', null, null, '反馈管理', 'form', '17', 'Y');
INSERT INTO `menu` VALUES ('27', 'reimbursement/payroll', 'payroll', null, null, '报销管理', 'form', '16', 'Y');
INSERT INTO `menu` VALUES ('28', 'syspay/wagelist', 'wagelist', '', '奖励管理', '其它工资项管理', 'el-icon-document', '4', 'Y');
INSERT INTO `menu` VALUES ('31', 'syspay/safe', 'safe', '', '个税管理', '个税管理', 'el-icon-user', '4', 'Y');
INSERT INTO `menu` VALUES ('32', 'syspay/safefive', 'safefive', '', '五险一金管理', '五险一金管理', 'el-icon-house', '4', 'Y');

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
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
INSERT INTO `post_dep_user` VALUES ('42', '37', '2', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=328 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
INSERT INTO `role_menu` VALUES ('203', '13', '1');
INSERT INTO `role_menu` VALUES ('204', '13', '2');
INSERT INTO `role_menu` VALUES ('205', '13', '3');
INSERT INTO `role_menu` VALUES ('206', '13', '14');
INSERT INTO `role_menu` VALUES ('207', '13', '15');
INSERT INTO `role_menu` VALUES ('296', '7', '1');
INSERT INTO `role_menu` VALUES ('297', '7', '2');
INSERT INTO `role_menu` VALUES ('298', '7', '3');
INSERT INTO `role_menu` VALUES ('299', '7', '14');
INSERT INTO `role_menu` VALUES ('300', '7', '15');
INSERT INTO `role_menu` VALUES ('301', '7', '4');
INSERT INTO `role_menu` VALUES ('302', '7', '5');
INSERT INTO `role_menu` VALUES ('303', '7', '6');
INSERT INTO `role_menu` VALUES ('304', '7', '7');
INSERT INTO `role_menu` VALUES ('305', '7', '28');
INSERT INTO `role_menu` VALUES ('306', '7', '29');
INSERT INTO `role_menu` VALUES ('307', '7', '30');
INSERT INTO `role_menu` VALUES ('308', '7', '31');
INSERT INTO `role_menu` VALUES ('309', '7', '32');
INSERT INTO `role_menu` VALUES ('310', '7', '8');
INSERT INTO `role_menu` VALUES ('311', '7', '9');
INSERT INTO `role_menu` VALUES ('312', '7', '10');
INSERT INTO `role_menu` VALUES ('313', '7', '11');
INSERT INTO `role_menu` VALUES ('314', '7', '12');
INSERT INTO `role_menu` VALUES ('315', '7', '13');
INSERT INTO `role_menu` VALUES ('316', '7', '16');
INSERT INTO `role_menu` VALUES ('317', '7', '27');
INSERT INTO `role_menu` VALUES ('318', '7', '17');
INSERT INTO `role_menu` VALUES ('319', '7', '26');
INSERT INTO `role_menu` VALUES ('320', '7', '18');
INSERT INTO `role_menu` VALUES ('321', '7', '25');
INSERT INTO `role_menu` VALUES ('322', '7', '19');
INSERT INTO `role_menu` VALUES ('323', '7', '24');
INSERT INTO `role_menu` VALUES ('324', '7', '20');
INSERT INTO `role_menu` VALUES ('325', '7', '23');
INSERT INTO `role_menu` VALUES ('326', '7', '21');
INSERT INTO `role_menu` VALUES ('327', '7', '22');

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
  `allInsure` double(255,0) DEFAULT NULL,
  PRIMARY KEY (`wage_id`),
  KEY `sfk` (`state`),
  KEY `ufk` (`user_id`),
  CONSTRAINT `sfk` FOREIGN KEY (`state`) REFERENCES `state` (`state_id`),
  CONSTRAINT `ufk` FOREIGN KEY (`user_id`) REFERENCES `users` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
  `flag` int(255) DEFAULT NULL,
  `tax_com` double(11,5) DEFAULT NULL,
  `lowmoney` double(11,5) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
INSERT INTO `taxandsafe` VALUES ('9', '0.08000', null, '养老保险：用人单位按照20%的基数进行缴纳，个人按照8%的比例进行扣缴', '养老保险', '2', '0.20000', null);
INSERT INTO `taxandsafe` VALUES ('10', '0.02000', null, '医疗保险：用人单位按照10%的比例进行缴费；个人按照2%的比例扣缴；', '医疗保险', '3', '0.10000', null);
INSERT INTO `taxandsafe` VALUES ('11', '0.00500', null, '失业保险：用人单位按照1.5%的比例缴费，个人按照0.5%的比例扣缴；', '失业保险', '4', '0.01500', null);
INSERT INTO `taxandsafe` VALUES ('12', '0.00000', null, '工伤保险：用人单位按照1%的比例缴费，个人不需要缴纳；', '工伤保险', '5', '0.01000', null);
INSERT INTO `taxandsafe` VALUES ('13', '0.00000', null, '生育保险：用人单位按照0.8%比例缴费，个人不需要缴纳；', '生育保险', '6', '0.00800', null);
INSERT INTO `taxandsafe` VALUES ('14', '0.08000', null, '公积金：个人所需承担的缴费基数与用人单位是一致的，都是8%。', '公积金', '7', '0.08000', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('3', 'admin', '$2a$10$uMGze7F7ogeasqPE2oPyyuQDV9hRpLjfe3.MTILY8pvCcjjMyWntm', '1', '13128281923', '599345199@qq.com', '1', '2', '22', 'oyhj', '', '1', '2023-03-26', '2023-04-12 00:27:05', '欧阳洪健', '4564646546456', '441625444464664463', null, null, null, null, null, null);
INSERT INTO `users` VALUES ('28', 'ces', '$2a$10$zwwyQErLiMNUPwuX7UqLDO5M9ywEW8diTMfwYY73QGtkItB2b1gkK', '1', '13128596471', '599345199@qq.com', '1', '2', '12', '', '威威', '1', '2022-04-05', '2023-04-08 14:44:23', '海绵宝宝', '123232313123', '123321232321312', null, null, null, null, null, null);
INSERT INTO `users` VALUES ('29', 'yanjie', '$2a$10$0SjUHAyJ8EUww33ECDF79.CozmI78WkLAPKbhLtLWEdOslIgjagTu', '0', '13569594632', '515@qq.com', '1', '3', '28', null, null, '2', '2023-04-19', '2023-04-09 15:19:39', '燕飞', '489654125633', '445698777155535563', null, null, null, null, null, null);
INSERT INTO `users` VALUES ('30', 'htl', '$2a$10$/Ilet1ElY.AFQM53OwDTvO2KQkaQsod6NA9POwgjmhSrNIH4fqdOS', '1', '15896969165', '4545646@163.com', '1', '7', '33', null, null, '1', '2016-05-11', '2023-04-09 22:45:17', '灰太狼', '74588484994', '114561112528888627', null, null, null, null, null, null);
INSERT INTO `users` VALUES ('31', 'pdx', '$2a$10$ZBmKx0BPfQ5rbQMoZ/5PpOSB5swQzBQos45GRXBbAwalAG6zfuzUO', '0', '13255599632', '5895959@ww.com', '3', '4', '17', null, null, '1', '2023-04-21', '2023-04-09 19:34:06', '派大星', '2323535253251', '488523699987441', null, null, null, null, null, null);
INSERT INTO `users` VALUES ('32', 'zyg', '$2a$10$gfRHJYVXSO4EX8580DRyLuMi9NaO/vRXpqJ0Y5Pl7.r9wDc8F4Z1K', '1', '15233369654', '85526@99.com', '1', '1', '15', null, null, '2', '2023-04-10', '2023-04-09 22:17:58', '章鱼哥', '85465542250255', '478541233695545', null, null, null, null, null, null);
INSERT INTO `users` VALUES ('33', 'xlb', '$2a$10$1fXG/kf.RBvJCFmwuXOSGOEMILXzL1XQWyaMbSNjNbWoRsNQcuoBm', '1', '13558621555', '45445@qq.com', '1', '1', '32', 'dfd', null, '1', '2023-04-05', '2023-04-09 22:18:10', '蟹老板', '856654645456', '131596484564441414', null, null, null, null, null, null);
INSERT INTO `users` VALUES ('35', 'ggBoy', '$2a$10$XEOLQGNUip83xAK52uJ9SOEzTvt2WmOTn.t1Erkw2if76n0Ydm9M2', '0', '13128281923', '1@qq.com', '1', '7', '23', '', '', '1', '2023-04-06', null, '猪猪侠', '3123231232323', '122233331232332312', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for user_basewage
-- ----------------------------
DROP TABLE IF EXISTS `user_basewage`;
CREATE TABLE `user_basewage` (
  `wuid` int(11) NOT NULL AUTO_INCREMENT,
  `basewage` double(18,3) DEFAULT NULL,
  `dsc` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `userid` int(11) unsigned NOT NULL,
  `version` int(255) DEFAULT NULL,
  `creatdate` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`wuid`),
  KEY `wu` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_basewage
-- ----------------------------
INSERT INTO `user_basewage` VALUES ('1', '8500.000', '工资更新替换', '3', '0', '2023-04-11 22:48:56');
INSERT INTO `user_basewage` VALUES ('2', '5500.000', '工资更新替换', '28', '0', '2023-04-13 01:40:05');
INSERT INTO `user_basewage` VALUES ('3', '3200.000', '', '31', '1', null);
INSERT INTO `user_basewage` VALUES ('4', '4500.000', '工资更新替换', '32', '0', '2023-04-16 20:59:09');
INSERT INTO `user_basewage` VALUES ('5', '9500.000', '工资更新替换', '3', '0', '2023-04-11 22:48:57');
INSERT INTO `user_basewage` VALUES ('6', '10000.000', '工资更新替换', '3', '0', '2023-04-11 22:50:41');
INSERT INTO `user_basewage` VALUES ('7', '12500.000', '工资更新替换', '3', '0', '2023-04-13 00:08:23');
INSERT INTO `user_basewage` VALUES ('10', '12000.000', '工资删除', '3', '-1', '2023-04-16 21:04:19');
INSERT INTO `user_basewage` VALUES ('11', '5220.000', '工资更新替换', '28', '0', '2023-04-16 20:57:24');
INSERT INTO `user_basewage` VALUES ('12', '2566.000', null, '29', '1', null);
INSERT INTO `user_basewage` VALUES ('13', '2342.000', null, '30', '1', null);
INSERT INTO `user_basewage` VALUES ('14', '9566.000', null, '33', '1', null);
INSERT INTO `user_basewage` VALUES ('15', '3422.000', null, '35', '1', null);
INSERT INTO `user_basewage` VALUES ('16', '3432.000', null, '28', '1', null);
INSERT INTO `user_basewage` VALUES ('17', '4326.000', '', '32', '1', null);
INSERT INTO `user_basewage` VALUES ('18', '432.000', '工资删除', '3', '-1', '2023-04-16 21:04:19');
INSERT INTO `user_basewage` VALUES ('19', '323112.000', '', '3', '1', '2023-04-16 21:10:16');

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
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('34', '29', '8');
INSERT INTO `user_role` VALUES ('37', '28', '8');
INSERT INTO `user_role` VALUES ('44', '31', '8');
INSERT INTO `user_role` VALUES ('47', '32', '8');
INSERT INTO `user_role` VALUES ('48', '33', '8');
INSERT INTO `user_role` VALUES ('50', '35', '8');
INSERT INTO `user_role` VALUES ('51', '30', '13');
INSERT INTO `user_role` VALUES ('52', '3', '7');

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
  `list_id` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `wage_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `wage` double(255,0) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `user_id` int(11) unsigned DEFAULT NULL,
  `creattime` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `endtime` date NOT NULL,
  `state` int(2) DEFAULT NULL,
  `des` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `flag` int(2) unsigned zerofill DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `listfk` (`list_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of wage_list
-- ----------------------------
INSERT INTO `wage_list` VALUES (null, '1', '绩效工资', '1852', '1', '3', '2023-04-15 01:46:22', '2023-04-12', '1', 'sad', '欧阳洪健', '01');
INSERT INTO `wage_list` VALUES (null, '17', '绩效工资', '2231', null, '28', '2023-04-14 01:40:00', '2023-04-20', null, null, '海绵宝宝', '01');
INSERT INTO `wage_list` VALUES (null, '18', null, '1221', null, '29', '2023-04-15 01:49:44', '2023-04-13', null, '', '燕飞', '01');
INSERT INTO `wage_list` VALUES (null, '19', null, '2323', null, '30', '2023-04-15 01:49:46', '2023-04-04', null, null, '灰太狼', '01');
INSERT INTO `wage_list` VALUES (null, '20', null, '233', null, '32', '2023-04-15 01:49:48', '2023-04-12', null, null, '章鱼哥', '01');
INSERT INTO `wage_list` VALUES (null, '21', null, '2232', null, '33', '2023-04-15 01:49:50', '2023-04-11', null, null, '蟹老板', '01');
INSERT INTO `wage_list` VALUES (null, '24', '绩效工资', '22321', null, '31', '2023-04-14 01:26:41', '2023-04-11', null, null, '派大星', '01');
INSERT INTO `wage_list` VALUES ('6', '35', '业绩突出', '1200', null, '3', '2023-04-16 13:15:57', '2023-04-05', null, '', '欧阳洪健', '00');
INSERT INTO `wage_list` VALUES ('5', '37', '上班迟到', '-20', null, '3', '2023-04-16 13:16:56', '2023-04-05', null, null, '欧阳洪健', '00');
INSERT INTO `wage_list` VALUES ('9999', '39', '拍领导马屁领导开心', '50', null, '28', '2023-04-16 15:01:58', '2023-04-11', null, '', '海绵宝宝', '02');
INSERT INTO `wage_list` VALUES ('18', '40', '全勤奖', '300', null, '3', '2023-04-16 13:40:45', '2023-03-04', null, '', '欧阳洪健', '00');
INSERT INTO `wage_list` VALUES ('6', '41', '业绩突出', '1200', null, '28', '2023-04-16 15:13:43', '2023-04-04', null, '', '海绵宝宝', '00');
INSERT INTO `wage_list` VALUES ('4', '42', '早退', '-50', null, '29', '2023-04-16 15:21:07', '2023-04-06', null, '', '燕飞', '00');
