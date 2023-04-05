/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : wage-management-sys

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2023-04-06 02:29:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `depart` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `depart_boss` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dec` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('研发部', '1', '李珊珊', '牛逼的人');

-- ----------------------------
-- Table structure for dep_user
-- ----------------------------
DROP TABLE IF EXISTS `dep_user`;
CREATE TABLE `dep_user` (
  `udid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `depid` int(11) DEFAULT NULL,
  `userid` int(11) DEFAULT NULL,
  PRIMARY KEY (`udid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of dep_user
-- ----------------------------

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
) ENGINE=InnoDB AUTO_INCREMENT=151 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', 'Layout', '/sys', '/sys/user', 'sysManage', '系统管理', 'el-icon-s-tools', '0', 'N');
INSERT INTO `menu` VALUES ('2', 'sys/user', 'user', null, 'userList', '用户管理', 'user', '1', 'Y');
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
INSERT INTO `menu` VALUES ('13', 'mypay/mypayroll', 'mypayroll', '', '工资明细', '工资明细', 'form', '11', 'Y');
INSERT INTO `menu` VALUES ('14', 'sys/department', 'department', null, 'depList', '部门管理', 'el-icon-key', '1', 'Y');
INSERT INTO `menu` VALUES ('15', 'sys/post', 'post', null, 'postList', '职位管理', 'user', '1', 'Y');

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post` (
  `post_id` int(10) NOT NULL AUTO_INCREMENT,
  `depid` int(11) DEFAULT NULL,
  `dsc` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `post_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`post_id`),
  KEY `dpfk` (`depid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES ('1', '1', null, '实习生');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of post_dep_user
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roledec` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `rolename` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `roletag` (`roledec`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('7', '111', '超级管理员');
INSERT INTO `role` VALUES ('8', '2', '财务管理');
INSERT INTO `role` VALUES ('13', '3', '员工');

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
) ENGINE=InnoDB AUTO_INCREMENT=197 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
  KEY `ufk` (`user_id`),
  KEY `sfk` (`state`),
  CONSTRAINT `sfk` FOREIGN KEY (`state`) REFERENCES `state` (`state_id`),
  CONSTRAINT `ufk` FOREIGN KEY (`user_id`) REFERENCES `users` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of staff_wage
-- ----------------------------
INSERT INTO `staff_wage` VALUES ('1', '12000', '980.28', '1008', '252', '25.2', '1197', '126', '1512', '200', '600', '100', '1', '100', '8822.52', '0', '0', '5', null, null, null);
INSERT INTO `staff_wage` VALUES ('2', '8000', '200.72', '688', '172', '17.2', '817', '86', '1032', '300', '300', '0', '0', '0', '6490.08', '0', '0', '9', null, null, null);
INSERT INTO `staff_wage` VALUES ('3', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '12', null, null, null);

-- ----------------------------
-- Table structure for state
-- ----------------------------
DROP TABLE IF EXISTS `state`;
CREATE TABLE `state` (
  `state_id` int(25) unsigned NOT NULL AUTO_INCREMENT,
  `state_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `state_dec` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`state_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of state
-- ----------------------------
INSERT INTO `state` VALUES ('0', '初始', null);
INSERT INTO `state` VALUES ('1', '待处理', null);
INSERT INTO `state` VALUES ('2', '处理中', null);
INSERT INTO `state` VALUES ('3', '已完成', null);

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
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `depid` int(11) DEFAULT NULL,
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
  PRIMARY KEY (`userId`),
  KEY `defk` (`depid`),
  KEY `psfk` (`postid`),
  CONSTRAINT `defk` FOREIGN KEY (`depid`) REFERENCES `department` (`id`),
  CONSTRAINT `psfk` FOREIGN KEY (`postid`) REFERENCES `post` (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('3', 'admin', '$2a$10$F8ZZsvjHp0Q5ehJ/yVGDDOZTsp2OKmTfPGRoPZSneIRdepjnGzFGe', '1', '13128281923', '599345199@qq.com', '1', '1', '18', '优秀', '广州', '1', '1999-01-08', '2023-04-02 17:21:17', '欧阳洪健', '12345646879789', '441625199901083338', '\"https://img1.baidu.com/it/u=4096959686,4145726040&fm=253&fmt=auto&app=138&f=JPEG?sec=1683017670&t=88663d7cd6f8d44dcd99a2d02f38584f\"');
INSERT INTO `users` VALUES ('5', '213s', '23123231', '0', '312312', '599345199@qq.com', null, null, null, null, null, null, null, '2023-04-02 16:55:28', null, null, null, 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fci.xiaohongshu.com%2Fae5bfc20-a24e-6a39-ed96-69f438b25bbf%3FimageView2%2F2%2Fw%2F1080%2Fformat%2Fjpg&refer=http%3A%2F%2Fci.xiaohongshu.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?w=500&h=500');
INSERT INTO `users` VALUES ('6', '321312', '$2a$10$rfi0pj.0nmWjsh2RvMNLCuaecsTL5ZFKVnsxu4LHPTNjCplBqxp62', '0', null, '599345199@qq.com', null, null, null, null, null, null, null, '2023-04-02 16:55:23', null, null, null, 'sec=1683017670&t=88663d7cd6f8d44dcd99a2d02f38584f');
INSERT INTO `users` VALUES ('7', 'sdasd', 'sdasdad', '1', '1312223232322', '599345199@qq.com', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('8', 'dasda', 'sda', '1', '1312223232322', '599345199@qq.com', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('9', 'sadas', '123', '1', '1312223232322', '599345199@qq.com', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('10', 'sdasd', 'sda', '1', '1312223232322', '599345199@qq.com', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('11', 'sdasd', 'sdasda', '1', '1312223232322', '599345199@qq.com', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('12', '123456', '$2a$10$wjKoLVSaF4vRzPC3ZSmLB.jnYV/rbEHYfVgKFLfj4J42BidB822A6', '1', null, '123456@qq.com', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('15', '123', '$2a$10$.jL2qTrI3lPeNRxvTvJKt.RZn7d4gpXU3dkBfxENrATYPE/J9elui', '1', null, '1@qq.com', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('16', '123', '1323', '1', '13', '13', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('17', 'lss', '$2a$10$OSCf7u/VwW8fZuYJJzYZsuK4rAszKMiSjtf6ZnTiE/DZYlI6uqOly', '1', null, '1@qq.com', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('21', '123', '$2a$10$HYJIY81L3Rf7gUeqGtufwO.WJkVK7OINDNzHQouIxDF2XzqFDi4pG', '0', null, '1@qq.com', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('22', '1232', '$2a$10$ug/riUXFsa7arkNk01LliO/f6gVzgbIZBPuYBQcTjIZ9MsnuTn.B2', '1', null, '1@qq.com', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('23', 'qwqw', '$2a$10$jNpOl0nUPXgLNePEbvTJseYDGbtZUl2lMXyrHP464KAbzPTgssBwq', '1', null, '1@qq.com', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('24', '111', '$2a$10$zvtvd6rhqrtxL9jAUXYVMOaSrW/DS5N6yZRrMTCxTTsoAWSzUXp3S', '1', '', '123@qq.com', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `users` VALUES ('27', 'oyhj', '$2a$10$qrl251fKgB4A28ZV9Zaef.ehc9LX.vKvF8oPj8WCWRQyUrLIjYDi2', '1', '', '123123@qq.com', null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `roleid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('6', '27', '7');
INSERT INTO `user_role` VALUES ('7', '21', '8');
INSERT INTO `user_role` VALUES ('10', '3', '7');

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
