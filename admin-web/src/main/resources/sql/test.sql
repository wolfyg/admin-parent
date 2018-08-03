/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2018-08-03 19:04:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for am_admin
-- ----------------------------
DROP TABLE IF EXISTS `am_admin`;
CREATE TABLE `am_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `nickName` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `locked` tinyint(255) DEFAULT NULL,
  `loginCount` int(255) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  `lastLoginTime` int(11) DEFAULT NULL,
  `lastLoginip` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of am_admin
-- ----------------------------
INSERT INTO `am_admin` VALUES ('1', 'admin', 'A66ABB5684C45962D887564F08346E8D', null, '超级管理员', '2017-11-13 15:27:08', '1', null, '1', null, null);
INSERT INTO `am_admin` VALUES ('3', 'testyg', '403C7E6C61BAA0B32474B4C6D4B7A123', null, '管理员', '2018-05-07 17:52:02', '1', null, '2', null, null);

-- ----------------------------
-- Table structure for am_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `am_admin_role`;
CREATE TABLE `am_admin_role` (
  `adminId` int(11) NOT NULL,
  `roleId` int(11) DEFAULT NULL,
  PRIMARY KEY (`adminId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色';

-- ----------------------------
-- Records of am_admin_role
-- ----------------------------
INSERT INTO `am_admin_role` VALUES ('1', '1');
INSERT INTO `am_admin_role` VALUES ('3', '2');

-- ----------------------------
-- Table structure for am_dic
-- ----------------------------
DROP TABLE IF EXISTS `am_dic`;
CREATE TABLE `am_dic` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL,
  `description` varchar(40) DEFAULT NULL,
  `pid` int(10) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `delFlag` tinyint(1) DEFAULT '1' COMMENT '1正常 2删除',
  `code` char(20) DEFAULT NULL,
  `count` int(11) DEFAULT '0' COMMENT '标签使用记录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Records of am_dic
-- ----------------------------
INSERT INTO `am_dic` VALUES ('0', '字典管理', '字典管理', '-1', '1', '1', null, '0');
INSERT INTO `am_dic` VALUES ('1', '汽油类型', '根节点误删除', '0', '1', '1', '1000', '0');
INSERT INTO `am_dic` VALUES ('2', '93号汽油', '212', '1', '1', '2', '211111', null);
INSERT INTO `am_dic` VALUES ('3', '柴油', '柴油', '1', '3', '1', '1003', '0');
INSERT INTO `am_dic` VALUES ('4', '97号汽油', '97号汽油', '1', '2', '1', '1002', '0');
INSERT INTO `am_dic` VALUES ('5', '地区', '根节点误删除', '0', '1', '1', '2000', '0');
INSERT INTO `am_dic` VALUES ('7', '合肥市', '市', '5', '1', '1', '200101', '0');
INSERT INTO `am_dic` VALUES ('8', '六安市', '市', '5', '2', '1', '200102', '0');
INSERT INTO `am_dic` VALUES ('9', '路况类型', '根节点误删除', '0', '1', '1', '3000', '0');
INSERT INTO `am_dic` VALUES ('10', '事故', '事故路段,2', '9', '8', '1', '3001', '0');
INSERT INTO `am_dic` VALUES ('11', '故障', '故障路段,2', '9', '3', '1', '3002', '0');
INSERT INTO `am_dic` VALUES ('12', '拥堵', '拥堵,1', '9', '6', '1', '3003', '0');
INSERT INTO `am_dic` VALUES ('13', '积水', '积水路段,2', '9', '2', '1', '3004', '0');
INSERT INTO `am_dic` VALUES ('14', '施工', '施工路段,1', '9', '4', '1', '3005', '0');
INSERT INTO `am_dic` VALUES ('15', '管制', '管制路段,1', '9', '5', '1', '3006', '0');
INSERT INTO `am_dic` VALUES ('34', '11', '11', '9', '1', '2', '11111', '0');
INSERT INTO `am_dic` VALUES ('35', '1', '11', '9', '1', '2', '11111', '0');
INSERT INTO `am_dic` VALUES ('37', '22', '22', '9', '2', '2', '22222', '0');

-- ----------------------------
-- Table structure for am_sys_func
-- ----------------------------
DROP TABLE IF EXISTS `am_sys_func`;
CREATE TABLE `am_sys_func` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '功能名',
  `url` varchar(255) DEFAULT NULL COMMENT '功能地址',
  `menuId` int(11) DEFAULT NULL COMMENT '菜单id',
  `isShow` tinyint(4) DEFAULT NULL COMMENT '是否显示',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='系统功能';

-- ----------------------------
-- Records of am_sys_func
-- ----------------------------
INSERT INTO `am_sys_func` VALUES ('6', '系统菜单列表', 'manger/permission/list', '128', '1', '1');
INSERT INTO `am_sys_func` VALUES ('11', '首页', 'manger/homepage', '137', '1', '2');
INSERT INTO `am_sys_func` VALUES ('13', '添加菜单', 'manger/permission/edit', '128', '0', '3');
INSERT INTO `am_sys_func` VALUES ('14', '获取列表数据', 'manger/permission/getData', '128', '0', '2');
INSERT INTO `am_sys_func` VALUES ('15', '删除系统菜单', 'manger/permission/del', '128', '0', '4');
INSERT INTO `am_sys_func` VALUES ('16', '系统功能列表', 'manger/func/list', '129', '1', '1');
INSERT INTO `am_sys_func` VALUES ('17', '系统功能编辑', 'manger/func/edit', '129', '0', '2');
INSERT INTO `am_sys_func` VALUES ('18', '系统功能数据', 'manger/func/getData', '129', '0', '3');
INSERT INTO `am_sys_func` VALUES ('19', '系统功能删除', 'manger/func/del', '129', '0', '4');
INSERT INTO `am_sys_func` VALUES ('24', '管理员列表', 'manger/sysuser/list', '135', '1', '1');
INSERT INTO `am_sys_func` VALUES ('25', '编辑用户', 'manger/sysuser/edit', '135', '0', '2');
INSERT INTO `am_sys_func` VALUES ('26', '用户数据', 'manger/sysuser/getData', '135', '0', '3');
INSERT INTO `am_sys_func` VALUES ('27', '删除用户', 'manger/sysuser/del', '135', '0', '4');
INSERT INTO `am_sys_func` VALUES ('28', '角色列表', 'manger/sysrole/list', '136', '1', '1');
INSERT INTO `am_sys_func` VALUES ('29', '角色编辑', 'manger/sysrole/edit', '136', '0', '2');
INSERT INTO `am_sys_func` VALUES ('30', '角色数据', 'manger/sysrole/getData', '136', '0', '3');
INSERT INTO `am_sys_func` VALUES ('31', '角色删除', 'manger/sysrole/del', '136', '0', '4');
INSERT INTO `am_sys_func` VALUES ('32', '字典列表', 'manger/dic/list', '138', '1', '1');
INSERT INTO `am_sys_func` VALUES ('34', '编辑字典', 'manger/dic/edit', '138', '0', '3');
INSERT INTO `am_sys_func` VALUES ('35', '删除字典', 'manger/dic/batchDel', '138', '0', '4');
INSERT INTO `am_sys_func` VALUES ('36', '字典数据', 'manger/dic/getData', '138', '0', '4');
INSERT INTO `am_sys_func` VALUES ('37', '字典表格数据', 'manger/dic/getTableData', '138', '0', '4');

-- ----------------------------
-- Table structure for am_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `am_sys_menu`;
CREATE TABLE `am_sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `parentId` int(11) DEFAULT '0',
  `sort` varchar(255) DEFAULT NULL,
  `isShow` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL COMMENT '图标',
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8 COMMENT='系统菜单';

-- ----------------------------
-- Records of am_sys_menu
-- ----------------------------
INSERT INTO `am_sys_menu` VALUES ('6', '设置', '0', '3', '1', 'fa fa-paper-plane', '2017-11-02 15:28:23');
INSERT INTO `am_sys_menu` VALUES ('7', '首页', '0', '1', '1', 'fa fa-home', '2017-12-01 15:28:10');
INSERT INTO `am_sys_menu` VALUES ('127', '用户', '0', '2', '1', 'fa fa-user', '2017-11-10 16:42:50');
INSERT INTO `am_sys_menu` VALUES ('128', '系统菜单管理', '6', '1', '1', null, null);
INSERT INTO `am_sys_menu` VALUES ('129', '系统功能管理', '6', '1', '1', null, null);
INSERT INTO `am_sys_menu` VALUES ('135', '用户管理', '127', '1', '1', null, null);
INSERT INTO `am_sys_menu` VALUES ('136', '角色管理', '127', '1', '1', null, null);
INSERT INTO `am_sys_menu` VALUES ('137', '系统首页', '7', '1', '1', null, null);
INSERT INTO `am_sys_menu` VALUES ('138', '数据字典管理', '6', '3', '1', 'null', '2018-05-07 11:23:49');

-- ----------------------------
-- Table structure for am_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `am_sys_role`;
CREATE TABLE `am_sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `isSys` tinyint(4) DEFAULT NULL COMMENT '0',
  `sort` varchar(255) DEFAULT NULL COMMENT '1',
  `createTime` datetime DEFAULT NULL,
  `modifyTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统角色';

-- ----------------------------
-- Records of am_sys_role
-- ----------------------------
INSERT INTO `am_sys_role` VALUES ('1', '超级管理员', '超级管理员', '1', null, '2017-11-02 15:29:28', '2018-05-07 13:48:34');
INSERT INTO `am_sys_role` VALUES ('2', '运营管理', '系统设置测试角色', null, null, '2018-05-07 17:49:28', '2018-05-08 12:15:25');

-- ----------------------------
-- Table structure for am_sys_role_func
-- ----------------------------
DROP TABLE IF EXISTS `am_sys_role_func`;
CREATE TABLE `am_sys_role_func` (
  `roleId` int(11) NOT NULL,
  `funcId` int(255) NOT NULL,
  PRIMARY KEY (`roleId`,`funcId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='功能角色';

-- ----------------------------
-- Records of am_sys_role_func
-- ----------------------------
INSERT INTO `am_sys_role_func` VALUES ('1', '6');
INSERT INTO `am_sys_role_func` VALUES ('1', '11');
INSERT INTO `am_sys_role_func` VALUES ('1', '13');
INSERT INTO `am_sys_role_func` VALUES ('1', '14');
INSERT INTO `am_sys_role_func` VALUES ('1', '15');
INSERT INTO `am_sys_role_func` VALUES ('1', '16');
INSERT INTO `am_sys_role_func` VALUES ('1', '17');
INSERT INTO `am_sys_role_func` VALUES ('1', '18');
INSERT INTO `am_sys_role_func` VALUES ('1', '19');
INSERT INTO `am_sys_role_func` VALUES ('1', '24');
INSERT INTO `am_sys_role_func` VALUES ('1', '25');
INSERT INTO `am_sys_role_func` VALUES ('1', '26');
INSERT INTO `am_sys_role_func` VALUES ('1', '27');
INSERT INTO `am_sys_role_func` VALUES ('1', '28');
INSERT INTO `am_sys_role_func` VALUES ('1', '29');
INSERT INTO `am_sys_role_func` VALUES ('1', '30');
INSERT INTO `am_sys_role_func` VALUES ('1', '31');
INSERT INTO `am_sys_role_func` VALUES ('1', '32');
INSERT INTO `am_sys_role_func` VALUES ('1', '34');
INSERT INTO `am_sys_role_func` VALUES ('1', '35');
INSERT INTO `am_sys_role_func` VALUES ('1', '36');
INSERT INTO `am_sys_role_func` VALUES ('1', '37');
INSERT INTO `am_sys_role_func` VALUES ('2', '6');
INSERT INTO `am_sys_role_func` VALUES ('2', '11');
INSERT INTO `am_sys_role_func` VALUES ('2', '14');
INSERT INTO `am_sys_role_func` VALUES ('2', '16');
INSERT INTO `am_sys_role_func` VALUES ('2', '18');
INSERT INTO `am_sys_role_func` VALUES ('2', '24');
INSERT INTO `am_sys_role_func` VALUES ('2', '26');
INSERT INTO `am_sys_role_func` VALUES ('2', '28');
INSERT INTO `am_sys_role_func` VALUES ('2', '30');
INSERT INTO `am_sys_role_func` VALUES ('2', '32');
INSERT INTO `am_sys_role_func` VALUES ('2', '36');
INSERT INTO `am_sys_role_func` VALUES ('2', '37');

-- ----------------------------
-- Table structure for logs
-- ----------------------------
DROP TABLE IF EXISTS `logs`;
CREATE TABLE `logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `num` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logs
-- ----------------------------
INSERT INTO `logs` VALUES ('1', '1');
INSERT INTO `logs` VALUES ('2', '1');
INSERT INTO `logs` VALUES ('3', '1');
INSERT INTO `logs` VALUES ('4', '4');
INSERT INTO `logs` VALUES ('5', '4');
INSERT INTO `logs` VALUES ('6', '7');

-- ----------------------------
-- Table structure for task_schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `task_schedule_job`;
CREATE TABLE `task_schedule_job` (
  `jobId` bigint(20) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `jobName` varchar(255) DEFAULT NULL COMMENT '任务名称',
  `jobGroup` varchar(255) DEFAULT NULL COMMENT '任务分组',
  `jobStatus` varchar(255) DEFAULT NULL COMMENT '任务状态 是否启动任务',
  `cronExpression` varchar(255) NOT NULL COMMENT 'cron表达式',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `beanClass` varchar(255) DEFAULT NULL COMMENT '任务执行时调用哪个类的方法 包名+类名',
  `isConcurrent` varchar(255) DEFAULT NULL COMMENT '任务是否有状态',
  `springId` varchar(255) DEFAULT NULL COMMENT 'spring bean',
  `methodName` varchar(255) NOT NULL COMMENT '任务调用的方法名',
  PRIMARY KEY (`jobId`),
  UNIQUE KEY `name_group` (`jobName`,`jobGroup`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='计划任务';

-- ----------------------------
-- Records of task_schedule_job
-- ----------------------------
INSERT INTO `task_schedule_job` VALUES ('1', null, '2014-08-08 14:17:34', 'test', 'test', '0', '0/1 * * * * ?', 'test', 'com.snailxr.base.task.TaskTest', '0', null, 'run1');
INSERT INTO `task_schedule_job` VALUES ('2', null, '2014-04-25 14:17:19', 'test1', 'test', '0', '0/5 * * * * ?', 'test2', null, '1', 'taskTest', 'run');
INSERT INTO `task_schedule_job` VALUES ('10', '2014-04-25 16:52:17', '2014-08-08 14:17:27', '中间', '1111', '0', '0/1 * * * * ?', 'xxx', 'com.snailxr.base.task.TaskTest', '1', '', 'run');

-- ----------------------------
-- Procedure structure for even
-- ----------------------------
DROP PROCEDURE IF EXISTS `even`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `even`(OUT even INT)
BEGIN
	DECLARE even INT DEFAULT 0;
	SET even=even+(SELECT id FROM test LIMIT 1);
	SELECT even;
	END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for evena
-- ----------------------------
DROP PROCEDURE IF EXISTS `evena`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `evena`(in p INT)
BEGIN
	SELECT * FROM test WHERE a=p;
	SELECT p;
	END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for insert_test_val
-- ----------------------------
DROP PROCEDURE IF EXISTS `insert_test_val`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_test_val`(in num_limit int,in rand_limit int)
BEGIN
 
DECLARE i int default 1;
DECLARE a int default 1;
DECLARE b int default 1;
DECLARE c int default 1;
 
WHILE i<=num_limit do
 
set a = FLOOR(rand()*rand_limit);
set b = FLOOR(rand()*rand_limit);
set c = FLOOR(rand()*rand_limit);
INSERT into test.test values (null,a,b,c);
set i = i + 1;
 
END WHILE;
 
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for mycursor
-- ----------------------------
DROP PROCEDURE IF EXISTS `mycursor`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `mycursor`()
BEGIN
	DECLARE f_a VARCHAR(10);
	DECLARE f_b INT DEFAULT 0;
	DECLARE f_c INT DEFAULT 0;
	declare allName INT DEFAULT 0;
  DECLARE FOUND BOOLEAN DEFAULT TRUE;
	DECLARE cur1 CURSOR FOR SELECT `name` FROM am_sys_func;
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET FOUND=FALSE;
	OPEN  cur1;
  WHILE FOUND DO
		FETCH  cur1 INTO f_a;
		SELECT  f_a;
   END WHILE;

	CLOSE  cur1;
END
;;
DELIMITER ;
