-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: zjl
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `zjl_account`
--

DROP TABLE IF EXISTS `zjl_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zjl_account` (
  `Zjl_account_id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `Zjl_role_id` bigint unsigned DEFAULT NULL COMMENT '角色id',
  `Zjl_username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户名',
  `Zjl_password` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '密码',
  `Zjl_salt` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '加密盐',
  `Zjl_real_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '真实姓名',
  `Zjl_sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '性别',
  `Zjl_email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '邮箱',
  `Zjl_create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `Zjl_modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  `Zjl_create_account_id` bigint unsigned DEFAULT NULL COMMENT '创建人',
  `Zjl_modified_account_id` bigint unsigned DEFAULT NULL COMMENT '修改人',
  `Zjl_deleted` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除标识(0、否 1、是)',
  PRIMARY KEY (`Zjl_account_id`) USING BTREE,
  KEY `FK_Zjl_account_Zjl_role_id` (`Zjl_role_id`),
  CONSTRAINT `FK_Zjl_account_Zjl_role_id` FOREIGN KEY (`Zjl_role_id`) REFERENCES `zjl_role` (`Zjl_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='账号表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zjl_account`
--

LOCK TABLES `zjl_account` WRITE;
/*!40000 ALTER TABLE `zjl_account` DISABLE KEYS */;
INSERT INTO `zjl_account` VALUES (1,1,'mp','17a1640916cfa8356adc4336a72ac75d','ecbe5fac60d1499595fbb98dfa854501','程序牛人','男','mp@126.com','2020-11-10 13:46:32','2020-11-15 17:09:28',NULL,1,0),(11,1,'user1','password1','salt1','张三','男','user1@example.com','2024-03-30 10:00:00','2024-03-30 10:00:00',1,1,0),(12,2,'user2','password2','salt2','李四','女','user2@example.com','2024-03-30 10:01:00','2024-03-30 10:01:00',1,1,0),(13,1,'user3','password3','salt3','王五','男','user3@example.com','2024-03-30 10:02:00','2024-03-30 10:02:00',1,1,0),(14,2,'user4','password4','salt4','赵六','女','user4@example.com','2024-03-30 10:03:00','2024-03-30 10:03:00',1,1,0),(15,1,'user5','password5','salt5','钱七','男','user5@example.com','2024-03-30 10:04:00','2024-03-30 10:04:00',1,1,0),(16,2,'user6','password6','salt6','孙八','女','user6@example.com','2024-03-30 10:05:00','2024-03-30 10:05:00',1,1,0),(17,1,'user7','password7','salt7','周九','男','user7@example.com','2024-03-30 10:06:00','2024-03-30 10:06:00',1,1,0),(18,2,'user8','password8','salt8','吴十','女','user8@example.com','2024-03-30 10:07:00','2024-03-30 10:07:00',1,1,0),(19,1774788472302407688,'user9','password9','salt9','郑十一','男','user9@example.com','2024-03-30 10:08:00','2024-04-03 13:54:08',1,1,0),(20,2,'ql','4b8a7f4206220d9bfa42e8a8a972d2ca','7c4b83e980064e50920bcc6b7711309d','zql','男','z@usj.cc','2024-03-30 22:05:34','2024-03-31 18:39:24',1,1,0),(22,2,'ui','d0a6e6839541ed3794a8c1e829daf8dc','c7c430918f4446b7b871456e2c239f01','iu','男','2@qq.com','2024-03-31 19:05:20','2024-03-31 23:33:36',1,1,0),(26,2,'212','f0b779174bbb7ec9db5d476a3df8b886','f8d71104a9754da7b3d36a65db36e686','31','男','z@usj.cc','2024-03-31 23:33:59',NULL,1,NULL,1),(27,1774788472302407689,'io','6e57f3cbdaebbf79015170a5421327ca','aa24fdf78d854fdeb650f1f204944248','io','男','z@usj.cc','2024-03-31 23:44:10','2024-04-03 13:53:58',NULL,1,0),(28,1774788472302407688,'z','b14fe9a3b540396f4e1f527515a15859','613b2b66b7694a11bbeb3a398f735d12','z','男','11@qq.com','2024-04-02 16:44:12','2024-04-03 13:53:51',1,1,0);
/*!40000 ALTER TABLE `zjl_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zjl_books`
--

DROP TABLE IF EXISTS `zjl_books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zjl_books` (
  `book_id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `author` varchar(50) NOT NULL,
  `publication_year` int DEFAULT NULL,
  `publisher` varchar(50) DEFAULT NULL,
  `isbn` varchar(20) DEFAULT NULL,
  `genre` varchar(50) DEFAULT NULL,
  `quantity` int NOT NULL DEFAULT '0',
  `available_quantity` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zjl_books`
--

LOCK TABLES `zjl_books` WRITE;
/*!40000 ALTER TABLE `zjl_books` DISABLE KEYS */;
INSERT INTO `zjl_books` VALUES (3,'1984','George Orwell',1949,'Secker & Warburg','9780451524935','Dystopian',4,4),(4,'Pride and Prejudice','Jane Austen',1813,'T. Egerton, Whitehall','9780141199078','Romance',6,6),(5,'The Catcher in the Rye','J.D. Salinger',1951,'Little, Brown and Company','9780316769488','Novel',2,90),(6,'The Lord of the Rings','J.R.R. Tolkien',1954,'George Allen & Unwin','9780618640157','Fantasy',8,8),(7,'Harry Potter and the Philosopher\'s Stone','J.K. Rowling',1997,'Bloomsbury Publishing','9780747532743','Fantasy',7,7),(8,'Animal Farm','George Orwell',1945,'Secker & Warburg','9780141036137','Political satire',5,5),(9,'The Hobbit','J.R.R. Tolkien',1937,'George Allen & Unwin','9780547928227','Fantasy',4,4),(10,'The Da Vinci Code','Dan Brown',2003,'Doubleday','9780307474278','Thriller',3,3),(11,'小人书','无名氏',2021,'北京大学出版社','9780307474798','漫画',213,789);
/*!40000 ALTER TABLE `zjl_books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zjl_cart`
--

DROP TABLE IF EXISTS `zjl_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zjl_cart` (
  `cart_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `book_id` int NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`cart_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zjl_cart`
--

LOCK TABLES `zjl_cart` WRITE;
/*!40000 ALTER TABLE `zjl_cart` DISABLE KEYS */;
INSERT INTO `zjl_cart` VALUES (1,1,101,3123123),(2,1,102,3),(3,2,103,1),(4,2,104,2),(5,3,105,2),(6,3,106,1),(11,21,32,21);
/*!40000 ALTER TABLE `zjl_cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zjl_customer`
--

DROP TABLE IF EXISTS `zjl_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zjl_customer` (
  `Zjl_customer_id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `Zjl_real_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '真实姓名',
  `Zjl_sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '性别',
  `Zjl_age` tinyint unsigned DEFAULT NULL COMMENT '年龄',
  `Zjl_email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '邮箱',
  `Zjl_phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '手机号码',
  `Zjl_address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '地址',
  `Zjl_create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `Zjl_modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  `Zjl_create_account_id` bigint unsigned DEFAULT NULL COMMENT '创建人',
  `Zjl_modified_account_id` bigint unsigned DEFAULT NULL COMMENT '修改人',
  `Zjl_deleted` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除标识(0、否 1、是)',
  PRIMARY KEY (`Zjl_customer_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='客户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zjl_customer`
--

LOCK TABLES `zjl_customer` WRITE;
/*!40000 ALTER TABLE `zjl_customer` DISABLE KEYS */;
INSERT INTO `zjl_customer` VALUES (1,'Alice','F',30,'alice@example.com','12345678901','123 Main St','2024-03-20 21:12:36','2024-03-20 21:12:36',1,1,0),(2,'Bob','M',25,'bob@example.com','12345678902','456 Oak St','2024-03-20 21:12:36','2024-03-20 21:12:36',2,2,0),(3,'Charlie','M',22,'charlie@example.com','12345678903','789 Pine St','2024-03-20 21:12:36','2024-03-20 21:12:36',3,3,0),(12,'华新武','男',26,'hxw@qq.com','18871008016','家里蹲',NULL,NULL,NULL,NULL,0),(13,'小赵同学','男',56,'z@usj.cc','18871006548','测试',NULL,NULL,NULL,NULL,0),(14,'小赵同学','女',23,'z@usj.cc','18871006548','测试','2024-03-22 16:39:13','2024-03-27 09:14:40',NULL,NULL,0),(15,'小赵同学','男',212,'z@usj.cc','18871006548','测试','2024-03-22 16:39:38','2024-03-22 16:39:38',NULL,NULL,0),(16,'jiang xh','男',32,'recioe@qq.com','18871008016','测试','2024-03-22 16:40:49','2024-03-22 16:40:49',NULL,NULL,0),(17,'小赵同学','男',23,'z@usj.cc','18871006548','测试','2024-03-22 16:49:45','2024-03-22 16:49:45',NULL,NULL,1),(18,'小赵同学','男',32,'z@usj.cc','18871006548','测试','2024-03-22 16:50:08','2024-03-22 16:50:08',NULL,NULL,0),(19,'小赵同学','男',43,'z@usj.cc','18871006548','测试','2024-03-22 16:50:22','2024-03-27 10:42:49',NULL,1,0),(20,'华新武','男',21,'2121@qq.cc','18871006548','测试','2024-03-26 15:52:23','2024-03-27 09:14:35',NULL,NULL,0),(21,'华狗子','女',84,'z@usj.cc','18871006548','312312321','2024-03-26 16:17:45','2024-03-27 10:28:38',NULL,1,0),(22,'华狗子','女',212,'z@usj.cc','18871006548','测试','2024-03-26 21:13:49','2024-03-27 09:14:25',NULL,NULL,0),(23,' 而4饿','男',212,'z@usj.cc','18871006548','3213123','2024-03-27 09:10:19','2024-03-27 10:47:02',NULL,1,0),(24,'小赵同学','男',42,'z@usj.cc','18871006548','测试','2024-03-27 10:42:57','2024-03-27 10:42:57',1,NULL,0),(25,'小赵同学','男',54,'z@usj.cc','18871006548','测试','2024-03-27 10:44:57','2024-03-27 10:44:57',1,NULL,0),(26,'32','男',32,'z@usj.cc','18871006548','测试','2024-03-27 10:45:30','2024-03-31 18:25:25',1,1,0),(27,'小赵同学','男',23,'z@usj.cc','18871006548','测试','2024-03-27 10:47:26','2024-03-27 10:47:26',1,NULL,1),(28,'212','男',12,'z@usj.cc','18871006548','测试','2024-03-27 10:47:46','2024-03-27 10:47:46',1,NULL,1),(29,'小赵21','男',21,'212121212@qq.com','18871006548','测试','2024-03-27 10:48:19','2024-03-27 10:48:26',1,1,1),(30,'小赵同学','男',12,'z@usj.cc','18871006548','测试','2024-03-29 09:50:58','2024-03-29 09:51:21',1,1,1);
/*!40000 ALTER TABLE `zjl_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zjl_resource`
--

DROP TABLE IF EXISTS `zjl_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zjl_resource` (
  `Zjl_resource_id` bigint unsigned NOT NULL COMMENT '主键',
  `Zjl_parent_id` bigint unsigned DEFAULT NULL COMMENT '父id',
  `Zjl_resource_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '资源名称',
  `Zjl_resource_type` tinyint DEFAULT NULL COMMENT '资源类型(0、目录 1、菜单 2、按钮)',
  `Zjl_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '请求地址',
  `Zjl_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '权限标识码',
  `Zjl_sort` int unsigned DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`Zjl_resource_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='资源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zjl_resource`
--

LOCK TABLES `zjl_resource` WRITE;
/*!40000 ALTER TABLE `zjl_resource` DISABLE KEYS */;
INSERT INTO `zjl_resource` VALUES (1,NULL,'系统管理',0,NULL,NULL,1),(2,NULL,'客户管理',0,NULL,NULL,2),(3,NULL,'其他管理',0,'',NULL,3),(11,1,'角色管理',1,'role/toList',NULL,1),(12,1,'账号管理',1,'account/toList',NULL,2),(21,2,'客户管理',1,'customer/toList','customer',1),(33,3,'图书管理',1,'books/toList',NULL,1),(34,3,'购物车',1,'cart/toList',NULL,2);
/*!40000 ALTER TABLE `zjl_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zjl_role`
--

DROP TABLE IF EXISTS `zjl_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zjl_role` (
  `Zjl_role_id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `Zjl_role_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '角色名称',
  `Zjl_remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  `Zjl_create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `Zjl_modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  `Zjl_create_account_id` bigint unsigned DEFAULT NULL COMMENT '创建人',
  `Zjl_modified_account_id` bigint unsigned DEFAULT NULL COMMENT '修改人',
  `Zjl_deleted` tinyint unsigned DEFAULT '0' COMMENT '逻辑删除标识(0、否 1、是)',
  PRIMARY KEY (`Zjl_role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1774788472302407690 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zjl_role`
--

LOCK TABLES `zjl_role` WRITE;
/*!40000 ALTER TABLE `zjl_role` DISABLE KEYS */;
INSERT INTO `zjl_role` VALUES (1,'练习角色','练习角色',NULL,NULL,NULL,NULL,0),(2,'大佬','大佬',NULL,NULL,NULL,NULL,0),(3,'研发部经理','主要管理研发的人员','2024-04-01 18:52:55',NULL,1,NULL,1),(4,'程序猿','打代码的打工狗','2024-04-01 18:56:34',NULL,NULL,NULL,1),(5,'管理员','最高管理权限','2024-04-01 21:35:26',NULL,1,NULL,1),(1774788472302407688,'qq','1','2024-04-02 15:56:43',NULL,1,NULL,0),(1774788472302407689,'z','z','2024-04-02 16:43:53',NULL,1,NULL,0);
/*!40000 ALTER TABLE `zjl_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zjl_role_resource`
--

DROP TABLE IF EXISTS `zjl_role_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zjl_role_resource` (
  `Zjl_role_resource_id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `Zjl_role_id` bigint unsigned DEFAULT NULL COMMENT '角色id',
  `Zjl_resource_id` bigint unsigned DEFAULT NULL COMMENT '资源id',
  PRIMARY KEY (`Zjl_role_resource_id`) USING BTREE,
  KEY `FK_Zjl_role_resource_Zjl_role_id` (`Zjl_role_id`),
  KEY `FK_Zjl_role_resource_Zjl_resource_id` (`Zjl_resource_id`),
  CONSTRAINT `FK_Zjl_role_resource_Zjl_resource_id` FOREIGN KEY (`Zjl_resource_id`) REFERENCES `zjl_resource` (`Zjl_resource_id`),
  CONSTRAINT `FK_Zjl_role_resource_Zjl_role_id` FOREIGN KEY (`Zjl_role_id`) REFERENCES `zjl_role` (`Zjl_role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色资源表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zjl_role_resource`
--

LOCK TABLES `zjl_role_resource` WRITE;
/*!40000 ALTER TABLE `zjl_role_resource` DISABLE KEYS */;
INSERT INTO `zjl_role_resource` VALUES (1,1,1),(2,1,2),(3,1,11),(4,1,12),(5,1,21),(6,1,3),(8,1,33),(9,1,34),(50,1774788472302407688,1),(51,1774788472302407688,11),(52,1774788472302407688,12),(53,1774788472302407689,2),(54,1774788472302407689,21);
/*!40000 ALTER TABLE `zjl_role_resource` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-19  9:13:39
