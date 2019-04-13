-- MySQL dump 10.13  Distrib 8.0.13, for macos10.14 (x86_64)
--
-- Host: localhost    Database: film
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sys_category`
--

DROP TABLE IF EXISTS `sys_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_category` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_category`
--

LOCK TABLES `sys_category` WRITE;
/*!40000 ALTER TABLE `sys_category` DISABLE KEYS */;
INSERT INTO `sys_category` VALUES (1,'爱情'),(2,'校园'),(3,'古风'),(4,'喜剧'),(5,'恐怖'),(6,'动画');
/*!40000 ALTER TABLE `sys_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_film`
--

DROP TABLE IF EXISTS `sys_film`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_film` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `lable` varchar(50) DEFAULT NULL,
  `on_time` datetime DEFAULT NULL,
  `grade` double(5,0) DEFAULT NULL,
  `introduce` text,
  `category_id` int(10) DEFAULT NULL,
  `area` varchar(20) DEFAULT NULL,
  `route` varchar(100) DEFAULT NULL,
  `vip` int(1) DEFAULT NULL,
  `voidelink` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_film`
--

LOCK TABLES `sys_film` WRITE;
/*!40000 ALTER TABLE `sys_film` DISABLE KEYS */;
INSERT INTO `sys_film` VALUES (2,'lone ranger','爱情','2018-01-18 00:00:00',3,'一个夜黑风高的夜晚。。。',1,'欧美','images/4.jpg',0,'images/voide/voide1.mp4'),(3,'泰坦尼克号','喜剧','2018-10-04 00:00:00',3,'一个夜黑风高的夜晚。。。',4,'中国','images/1.jpg',1,'images/voide/voide2.mp4'),(5,'大地震','动画','2018-10-23 00:00:00',4,'一个夜黑风高的夜晚。。。',6,'中国','images/2.jpg',0,'images/voide/voide3.mp4'),(6,'记忆大师','爱情','2018-10-25 00:00:00',2,'一个夜黑风高的夜晚。。。',1,'中国','images/comedy/action1.jpg',1,'images/voide/voide4.mp4'),(7,'西虹市首富','爱情','2018-10-28 00:00:00',4,'一个夜黑风高的夜晚。。。',1,'中国','images/comedy/comedy1.jpg',0,'images/voide/voide1.mp4'),(8,'单身男女','喜剧','2018-10-20 00:00:00',3,'一个夜黑风高的夜晚。。。',4,'中国','images/comedy/comedy2.jpg',1,'images/voide/voide2.mp4'),(9,'唐人街探案2','喜剧','2018-10-12 00:00:00',3,'一个夜黑风高的夜晚。。。',4,'中国','images/comedy/comedy3.jpg',1,'images/voide/voide3.mp4'),(10,'人在囧途','喜剧','2018-10-19 00:00:00',5,'一个夜黑风高的夜晚。。。',4,'中国','images/comedy/comedy4.jpg',1,'images/voide/voide4.mp4'),(11,'宝贝计划','喜剧','2018-10-12 00:00:00',4,'一个夜黑风高的夜晚。。。',4,'中国','images/comedy/comedy5.jpg',0,'images/voide/voide1.mp4'),(12,'九品芝麻官','恐怖','2018-10-07 00:00:00',3,'一个夜黑风高的夜晚。。。',5,'中国','images/comedy/comedy7.jpg',0,'images/voide/voide2.mp4'),(13,'神偷奶爸','古风','2018-10-08 00:00:00',3,'一个夜黑风高的夜晚。。。',3,'中国','images/comedy/comedy6.jpg',0,'images/voide/voide3.mp4'),(14,'peter hase','爱情','2018-10-28 00:00:00',5,'一个夜黑风高的夜晚。。。',1,'中国','images/comedy/action2.jpg',0,'images/voide/voide4.mp4'),(15,'0.5的爱情','爱情','2018-10-05 00:00:00',4,'一个夜黑风高的夜晚。。。',1,'欧美','images/comedy/love1.jpg',0,'images/voide/voide1.mp4'),(16,'第三种爱情','爱情','2018-10-09 00:00:00',3,'一个夜黑风高的夜晚。。。',1,'中国','images/comedy/love2.jpg',1,'images/voide/voide2.mp4'),(17,'十二生肖','古风','2018-10-21 00:00:00',3,'一个夜黑风高的夜晚。。。',3,'中国','images/comedy/move8.jpg',1,'images/voide/voide3.mp4'),(18,'dylan obrien','恐怖','2018-10-06 00:00:00',4,'一个夜黑风高的夜晚。。。',5,'中国','images/comedy/move4.jpg',0,'images/voide/voide4.mp4'),(19,'幸运是我','古风','2018-10-20 00:00:00',4,'一个夜黑风高的夜晚。。。',3,'中国','images/comedy/romance1.jpg',1,'images/voide/voide1.mp4'),(20,'危笑时刻','动画','2018-10-21 00:00:00',2,'一个夜黑风高的夜晚。。。',6,'欧美','images/comedy/move9.jpg',0,'images/voide/voide3.mp4'),(21,'X战警','动画','2018-10-21 00:00:00',5,'一个夜黑风高的夜晚。。。',6,'欧美','images/comedy/move7.jpg',1,'images/voide/voide4.mp4'),(22,'测试1','校园','2018-01-18 14:00:00',2,'111',2,'南宁','C:\\fakepath\\WechatIMG13.jpeg',NULL,'C:\\fakepath\\大地震.mp4');
/*!40000 ALTER TABLE `sys_film` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_film_palyer`
--

DROP TABLE IF EXISTS `sys_film_palyer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_film_palyer` (
  `film_id` int(10) NOT NULL,
  `player_id` int(10) NOT NULL,
  PRIMARY KEY (`film_id`,`player_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_film_palyer`
--

LOCK TABLES `sys_film_palyer` WRITE;
/*!40000 ALTER TABLE `sys_film_palyer` DISABLE KEYS */;
INSERT INTO `sys_film_palyer` VALUES (1,1),(1,2),(2,3);
/*!40000 ALTER TABLE `sys_film_palyer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_log`
--

DROP TABLE IF EXISTS `sys_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_log` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `operation` varchar(20) DEFAULT NULL,
  `film_name` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_log`
--

LOCK TABLES `sys_log` WRITE;
/*!40000 ALTER TABLE `sys_log` DISABLE KEYS */;
INSERT INTO `sys_log` VALUES (10,'2018-10-23 00:00:00','查询电影名','十年'),(11,'2018-10-23 00:00:00','查询电影名','十年之约'),(12,'2018-10-23 00:00:00','查询电影名','十三娘'),(18,'2018-10-23 00:00:00','查询电影名','十三娘'),(19,'2018-10-23 00:00:00','查询电影名','十三娘'),(20,'2018-10-23 00:00:00','查询电影名','十年'),(21,'2018-10-23 00:00:00','查询电影名','十年之约'),(22,'2018-10-23 00:00:00','查询电影名','十年'),(23,'2018-10-23 00:00:00','查询电影名','十年之约'),(24,'2018-10-23 00:00:00','查询电影名','十年'),(25,'2018-10-23 00:00:00','查询电影名','十年之约'),(26,'2018-10-23 00:00:00','查询电影名','十三娘'),(27,'2018-10-23 00:00:00','查询电影名','十三娘'),(28,'2018-10-23 00:00:00','查询电影名','十三娘'),(29,'2018-10-23 00:00:00','收藏','暮光之城'),(30,'2018-10-23 00:00:00','收藏','暮光之城'),(31,'2018-10-23 00:00:00','收藏','记忆大师'),(32,'2018-10-23 00:00:00','查询电影名','十年'),(33,'2018-10-23 00:00:00','查询电影名','十年之约'),(34,'2018-10-23 00:00:00','下载','images/5.jpg'),(42,'2018-10-23 00:00:00','下载','ç¾å¹´å¶çº¦'),(43,'2018-10-23 00:00:00','下载','åå¹´'),(47,'2018-10-23 00:00:00','下载','暮光之城'),(48,'2018-10-23 00:00:00','下载','暮光之城'),(49,'2018-10-24 00:00:00','查询电影名','南京！南京！'),(50,'2018-10-24 00:00:00','收藏','????'),(51,'2018-10-24 00:00:00','收藏','?????'),(52,'2018-10-24 00:00:00','收藏','??????'),(53,'2018-10-24 00:00:00','收藏','?????'),(54,'2018-10-24 00:00:00','收藏','?????'),(55,'2018-10-24 00:00:00','收藏','????'),(56,'2018-10-24 00:00:00','收藏','????'),(57,'2018-10-24 00:00:00','收藏','????'),(58,'2018-10-24 00:00:00','收藏','????'),(59,'2018-10-24 00:00:00','收藏','?????'),(60,'2018-10-24 00:00:00','收藏','peter hase'),(61,'2018-10-24 00:00:00','收藏','0.5???'),(62,'2018-10-24 00:00:00','收藏','?????'),(63,'2018-10-24 00:00:00','收藏','????'),(64,'2018-10-24 00:00:00','查询电影名','人在囧途'),(65,'2018-10-24 00:00:00','查询电影名','十二生肖'),(66,'2018-10-24 00:00:00','查询电影名','唐人街探案2'),(67,'2018-10-24 00:00:00','查询电影名','人在囧途'),(68,'2018-10-24 00:00:00','下载','记忆大师'),(69,'2018-10-24 00:00:00','收藏','??????'),(70,'2018-10-24 00:00:00','下载','南京！南京！'),(71,'2018-10-24 00:00:00','下载','南京！南京！'),(72,'2018-10-24 00:00:00','下载','泰坦尼克号'),(73,'2018-10-24 00:00:00','收藏','?????'),(74,'2018-10-24 00:00:00','收藏','?????'),(75,'2018-10-24 00:00:00','查询电影名','十二生肖'),(76,'2018-10-24 00:00:00','下载','?????'),(77,'2018-10-24 00:00:00','查询电影名','唐人街探案2'),(78,'2018-10-24 00:00:00','查询电影名','人在囧途'),(79,'2018-10-25 00:00:00','查询电影名','南京！南京'),(80,'2019-03-30 20:32:54','收藏','???'),(81,'2019-03-30 20:35:07','查询电影名','宝贝计划'),(82,'2019-03-30 20:47:50','收藏','????');
/*!40000 ALTER TABLE `sys_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_player`
--

DROP TABLE IF EXISTS `sys_player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_player` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `gender` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_player`
--

LOCK TABLES `sys_player` WRITE;
/*!40000 ALTER TABLE `sys_player` DISABLE KEYS */;
INSERT INTO `sys_player` VALUES (1,'大头儿子','2018-10-02',1),(2,'小头爸爸','2018-10-11',1),(3,'狗','2018-10-01',0);
/*!40000 ALTER TABLE `sys_player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `password` varchar(100) NOT NULL,
  `salt` varchar(100) DEFAULT NULL,
  `vip` int(4) NOT NULL,
  `conservator` int(4) NOT NULL,
  `createUser` varchar(100) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateUser` varchar(100) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user`
--

LOCK TABLES `sys_user` WRITE;
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` VALUES (1,'admin','4ebd394fbd25e495e0753a7dc9889a8e','7adb778c-e7d3-4dd3-a3c5-5f80a158006d',1,1,NULL,'2018-10-21 00:00:00',NULL,'2018-10-21 00:00:00'),(4,'adr','05b5b7373ab1c3beeac829336095ac06','cf986694-0f8c-46c6-b6db-e4a9beeffd76',2,1,NULL,'2018-10-23 14:49:31','admin','2019-04-03 22:50:10'),(8,'admin2','25c1503333fce90cec65b858edede2c2','883ea4cf-226c-40ad-97ee-05d5be8cd1ab',1,1,'admin','2019-03-30 20:20:36',NULL,'2019-03-30 20:20:36'),(9,'admin3','4409b1d413cca15c043b10fd417a7257','e02c00b7-92fb-49d8-b5e2-18cd3504990f',5,1,'admin','2019-04-03 22:53:16',NULL,'2019-04-03 22:53:16'),(10,'admin4','d1c23b15e4413303576d94b65b9553c9','95e9cd75-9e4e-4ea1-a937-fd64baa2e061',5,1,'admin','2019-04-03 23:02:11',NULL,'2019-04-03 23:02:11'),(11,'user','ee11dcdbd5de45024be7af9d1f995827','04b58188-a176-4947-a128-f3d4b1a45703',2,0,'admin','2019-04-03 23:02:28',NULL,'2019-04-03 23:02:28'),(12,'user123','aa913730d6ca420bdf7b54735087f034','08e958e3-5b6f-4cf8-bc63-4183b954fb86',0,0,'admin','2019-04-05 15:20:27',NULL,'2019-04-05 15:20:27');
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_film`
--

DROP TABLE IF EXISTS `sys_user_film`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `sys_user_film` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) DEFAULT NULL,
  `film_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_film`
--

LOCK TABLES `sys_user_film` WRITE;
/*!40000 ALTER TABLE `sys_user_film` DISABLE KEYS */;
INSERT INTO `sys_user_film` VALUES (1,1,'十年'),(2,1,'宝贝计划'),(3,1,'南京！南京！'),(4,1,'南京！南京'),(5,1,'南京！南京'),(6,1,'大地震'),(7,1,'幸运是我'),(8,1,'记忆大师'),(9,1,'lone ranger');
/*!40000 ALTER TABLE `sys_user_film` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'film'
--

--
-- Dumping routines for database 'film'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-08 23:27:16
