/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : cat-adopt

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 04/01/2024 10:48:23
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '123');

-- ----------------------------
-- Table structure for forum
-- ----------------------------
DROP TABLE IF EXISTS `forum`;
CREATE TABLE `forum`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `reply` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `date` datetime(0) DEFAULT NULL,
  `uid` int(0) DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of forum
-- ----------------------------
INSERT INTO `forum` VALUES (9, '12312312', NULL, '2024-01-03 23:03:53', 1, 'http://localhost:8100/images/30b87a72-d8fc-4f63-aa38-142f24dd81e7.jpg');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `reply` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `uid` int(0) DEFAULT NULL,
  `date` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1, '12312', '12312312312312312', 1, NULL);
INSERT INTO `message` VALUES (2, '请问2uwe看懂【旁系j', NULL, 1, NULL);

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `way` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `uid` int(0) DEFAULT NULL,
  `pid` int(0) DEFAULT NULL,
  `nid` int(0) DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `tel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `addr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `date` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (2, '自提', '同意', 1, 4, NULL, '11111', NULL, NULL, NULL, '2022-04-09 03:39:43');
INSERT INTO `order` VALUES (3, '自提', '申请中', 0, 14, NULL, NULL, NULL, NULL, NULL, '2022-04-09 04:16:43');
INSERT INTO `order` VALUES (4, '自提', '申请中', 0, 14, NULL, NULL, NULL, NULL, NULL, '2022-04-09 04:20:42');
INSERT INTO `order` VALUES (5, '自提', '申请中', 2, 14, NULL, NULL, NULL, NULL, NULL, '2022-04-09 04:22:03');
INSERT INTO `order` VALUES (6, '自提', '申请中', 1, 3, NULL, '111', NULL, NULL, NULL, '2022-04-09 05:10:34');
INSERT INTO `order` VALUES (7, '自提', '申请中', 1, 16, 0, '123123', '张三', NULL, NULL, '2024-01-02 23:20:38');

-- ----------------------------
-- Table structure for pet
-- ----------------------------
DROP TABLE IF EXISTS `pet`;
CREATE TABLE `pet`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `weight` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `age` int(0) DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `height` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `addr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `character` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `demand` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `date` datetime(0) DEFAULT NULL,
  `uid` int(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pet
-- ----------------------------
INSERT INTO `pet` VALUES (3, '小猪蹄', 'http://localhost:8100/images/10e1121c-6fb4-4bdc-8de4-2c21ace8c55c.jpg', '蓝猫', '10斤', 2, '女', '健康', '上海市', '', '未领养', '困难', '2024-01-02 20:47:54', NULL);
INSERT INTO `pet` VALUES (4, '1', 'http://localhost:8100/images/10e1121c-6fb4-4bdc-8de4-2c21ace8c55c.jpg', '蓝猫', '1', 2, '女', '健康', '上海市', '', '未领养', '困难', '2024-01-02 20:47:54', NULL);
INSERT INTO `pet` VALUES (5, '洋1', 'http://localhost:8100/images/10e1121c-6fb4-4bdc-8de4-2c21ace8c55c.jpg', '蓝猫', '1', 2, '女', '健康', '上海市', '', '未领养', '困难', '2024-01-02 20:47:54', NULL);
INSERT INTO `pet` VALUES (6, '抓抓', 'http://localhost:8100/images/10e1121c-6fb4-4bdc-8de4-2c21ace8c55c.jpg', '蓝猫', '2', 2, '女', '健康', '上海市', '', '未领养', '困难', '2024-01-02 20:47:54', NULL);
INSERT INTO `pet` VALUES (7, '奥利奥', 'http://localhost:8100/images/10e1121c-6fb4-4bdc-8de4-2c21ace8c55c.jpg', '蓝猫', '12', 2, '女', '健康', '上海市', '', '未领养', '困难', '2024-01-02 20:47:54', NULL);
INSERT INTO `pet` VALUES (8, '奥利奥1', 'http://localhost:8100/images/10e1121c-6fb4-4bdc-8de4-2c21ace8c55c.jpg', '蓝猫', '12', 2, '女', '健康', '上海市', '', '未领养', '困难', '2024-01-02 20:47:54', NULL);
INSERT INTO `pet` VALUES (9, '奥利奥2', 'http://localhost:8100/images/10e1121c-6fb4-4bdc-8de4-2c21ace8c55c.jpg', '蓝猫', '12', 2, '女', '健康', '上海市', '', '未领养', '普通', '2024-01-02 20:47:54', NULL);
INSERT INTO `pet` VALUES (10, '奥利奥3', 'http://localhost:8100/images/10e1121c-6fb4-4bdc-8de4-2c21ace8c55c.jpg', '蓝猫', '12', 2, '女', '健康', '上海市', '', '未领养', '普通', '2024-01-02 20:47:54', NULL);
INSERT INTO `pet` VALUES (11, '奥利奥', 'http://localhost:8100/images/fac12f32-a91c-4455-b675-1ca8c2b64164.jpg', '蓝猫', '12', 2, '女', '健康', '上海市', '1', '未领养', '普通', '2024-01-02 20:47:54', NULL);
INSERT INTO `pet` VALUES (12, '奥利奥2', 'http://localhost:8100/images/4724f629-5b8d-47de-b1cf-519bc3b530b1.jpg', '蓝猫', '12', 2, '女', '健康', '上海市', '2', '未领养', '普通', '2024-01-02 20:47:54', NULL);
INSERT INTO `pet` VALUES (13, '奥利奥2', 'http://localhost:8100/images/1f421af4-fd09-4fd4-aee4-b3a43b883046.jpg', '蓝猫', '12', 2, '女', '健康', '上海市', '1', '未领养', '普通', '2024-01-02 20:47:54', NULL);
INSERT INTO `pet` VALUES (14, '小美', 'http://localhost:8100/images/53ee3cc4-f0d1-45f4-a1f6-2c23d99ea64e.jpg', '蓝猫', '3斤', 2, '女', '健康', '上海市', '1', '未领养', '简单', '2024-01-02 20:47:54', NULL);
INSERT INTO `pet` VALUES (15, '可乐', 'http://localhost:8100/images/7a191c81-7354-4591-b4c0-ab9639ca7aa5.jpg', '金渐层', '22', 2, '女', '健康', '上海市', '温顺', '已领养', '简单', '2024-01-02 20:47:54', NULL);
INSERT INTO `pet` VALUES (16, '小猪蹄', 'http://localhost:8100/images/10e1121c-6fb4-4bdc-8de4-2c21ace8c55c.jpg', '蓝猫', '11', 2, '女', '健康', '上海市', '1', '未领养', '简单', '2024-01-02 20:47:54', NULL);
INSERT INTO `pet` VALUES (17, '小妹', 'http://localhost:8100/images/7a66feb1-0921-4478-9fa7-626e52d31f7c.jpg', '橘猫', '11', 1, '男', '1', '11', '111', '未领养', '简单', '2024-01-04 00:00:23', 2);

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `answer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `qa` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `qb` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `qc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `qd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of question
-- ----------------------------
INSERT INTO `question` VALUES (1, '猫咪在哪里上厕所？', 'c', '厕所', '随便', '猫砂盆', '垃圾桶');
INSERT INTO `question` VALUES (2, '猫咪踩奶是什么行为？', 'a', '释放心情', '不知道干嘛', '发情', '无聊');
INSERT INTO `question` VALUES (3, '猫咪怎么洗脸', 'b', '不洗', '随便洗', '自己洗', '主人洗');
INSERT INTO `question` VALUES (4, '猫咪乱尿是因为?', 'd', '报复你', '喜欢随处拉', '找不到猫砂朋', '猫砂不好');
INSERT INTO `question` VALUES (5, '猫咪在哪里上厕所？', 'c', '厕所', '随便', '猫砂盆', '垃圾桶');
INSERT INTO `question` VALUES (6, '猫咪乱尿是因为?', 'd', '报复你', '喜欢随处拉', '找不到猫砂朋', '猫砂不好');
INSERT INTO `question` VALUES (7, '猫咪怎么洗脸', 'b', '不洗', '随便洗', '自己洗', '主人洗');
INSERT INTO `question` VALUES (8, '猫咪踩奶是什么行为？', 'a', '释放心情', '不知道干嘛', '发情', '无聊');
INSERT INTO `question` VALUES (9, '猫咪在哪里上厕所？', 'c', '厕所', '随便', '猫砂盆', '垃圾桶');
INSERT INTO `question` VALUES (10, '猫咪在哪里上厕所？', 'c', '厕所', '随便', '猫砂盆', '垃圾桶');
INSERT INTO `question` VALUES (11, '猫咪乱尿是因为?', 'd', '报复你', '喜欢随处拉', '找不到猫砂朋', '猫砂不好');
INSERT INTO `question` VALUES (12, '猫咪踩奶是什么行为？', 'a', '释放心情', '不知道干嘛', '发情', '无聊');

-- ----------------------------
-- Table structure for tips
-- ----------------------------
DROP TABLE IF EXISTS `tips`;
CREATE TABLE `tips`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tips
-- ----------------------------
INSERT INTO `tips` VALUES (3, '喂养', '11', '213123123', 'http://localhost:8100/images/74c451ff-b19f-4eed-9db2-cdcb55b3d3ac.jpg', '2024-01-02 21:01:07.129000');
INSERT INTO `tips` VALUES (4, '健康', '猫咪为什么会拉稀', '猫咪为什么会拉稀2131231222222222222222222222222222', 'http://localhost:8100/images/e622d9bc-2563-484b-b871-05c843022269.jpg', '2024-01-03 14:37:41.854000');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `tel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `addr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '张三', '123456', '男', '18021415698', '11', '上海路', '拉黑');
INSERT INTO `user` VALUES (2, '李四', '123', '男', '18021415698', NULL, NULL, '通过');

SET FOREIGN_KEY_CHECKS = 1;
