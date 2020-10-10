/*
 Navicat Premium Data Transfer

 Source Server         : exercise
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : exercise

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 10/10/2020 12:34:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_downloadlist
-- ----------------------------
DROP TABLE IF EXISTS `t_downloadlist`;
CREATE TABLE `t_downloadlist`  (
  `id` int NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `size` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `star` int NULL DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `time` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_downloadlist
-- ----------------------------
INSERT INTO `t_downloadlist` VALUES (1, 'web教案', 'download/web应用开发教案.docx', '图文并茂，内容详实，引导初学者一步步走进web世界', '2048', 5, 'fileIcon/doc.png', '2020-09-29');
INSERT INTO `t_downloadlist` VALUES (2, '文档2', 'download/教案文档.docx', '补充文档，对web知识体系进行了进一步的扩充', '3000', 4, 'fileIcon/doc.png', '2020-10-01');

-- ----------------------------
-- Table structure for t_resouce
-- ----------------------------
DROP TABLE IF EXISTS `t_resouce`;
CREATE TABLE `t_resouce`  (
  `resouceId` int NOT NULL,
  `resouceName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`resouceId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_resouce
-- ----------------------------
INSERT INTO `t_resouce` VALUES (1, 'getDownloadlist.do', '/getDownloadlist.do');
INSERT INTO `t_resouce` VALUES (2, 'download.do', '/download.do');
INSERT INTO `t_resouce` VALUES (3, 'download.jsp', '/download.jsp');
INSERT INTO `t_resouce` VALUES (4, 'main.jsp', '/main.jsp');
INSERT INTO `t_resouce` VALUES (5, 'manageUser.jsp', '/manageUser.jsp');
INSERT INTO `t_resouce` VALUES (6, 'manageResouce.jsp', '/manageResouce.jsp');
INSERT INTO `t_resouce` VALUES (7, 'self.jsp', '/self.jsp');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `roleId` int NOT NULL,
  `roleName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`roleId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, 'admin');
INSERT INTO `t_role` VALUES (2, 'user');

-- ----------------------------
-- Table structure for t_role_resouce
-- ----------------------------
DROP TABLE IF EXISTS `t_role_resouce`;
CREATE TABLE `t_role_resouce`  (
  `id` int NOT NULL,
  `resouceId` int NULL DEFAULT NULL,
  `roleId` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_resouce
-- ----------------------------
INSERT INTO `t_role_resouce` VALUES (1, 1, 1);
INSERT INTO `t_role_resouce` VALUES (2, 2, 1);
INSERT INTO `t_role_resouce` VALUES (3, 3, 1);
INSERT INTO `t_role_resouce` VALUES (4, 4, 1);
INSERT INTO `t_role_resouce` VALUES (5, 5, 1);
INSERT INTO `t_role_resouce` VALUES (6, 6, 1);
INSERT INTO `t_role_resouce` VALUES (7, 7, 1);
INSERT INTO `t_role_resouce` VALUES (8, 1, 2);
INSERT INTO `t_role_resouce` VALUES (9, 2, 2);
INSERT INTO `t_role_resouce` VALUES (10, 3, 2);
INSERT INTO `t_role_resouce` VALUES (11, 4, 2);
INSERT INTO `t_role_resouce` VALUES (12, 7, 2);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `userName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `chrName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`userName`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('a', '222', '羊子');
INSERT INTO `t_user` VALUES ('b', '333', '张驰骋');
INSERT INTO `t_user` VALUES ('yang', '111', '杨洋');

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `id` int NOT NULL,
  `roleId` int NULL DEFAULT NULL,
  `userName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (1, 1, 'yang');
INSERT INTO `t_user_role` VALUES (2, 2, 'a');
INSERT INTO `t_user_role` VALUES (3, 2, 'b');

SET FOREIGN_KEY_CHECKS = 1;
