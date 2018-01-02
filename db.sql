-- MySQL dump 10.13  Distrib 5.5.58, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: softeng
-- ------------------------------------------------------
-- Server version	5.7.20-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ASSISTANTACCOUNT` varchar(255) DEFAULT NULL,
  `COURSEID` int(11) DEFAULT NULL,
  `COURSENAME` varchar(255) DEFAULT NULL,
  `CREDITS` float DEFAULT NULL,
  `HOURS` int(11) DEFAULT NULL,
  `OUTLINE` varchar(255) DEFAULT NULL,
  `PROFESSORACCOUNT` varchar(255) DEFAULT NULL,
  `SEMESTER` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (11,'assistant',220201,'物件導向設計',3,3,'Details: xxxx','teacher','105-2'),(12,'ta',221205,'CS 101',3,2,'Details: xxxx','teacher2','106-1'),(13,'ta',220207,'Java應用',1,3,'Details: xxxx','teacher','105-2'),(14,'assistant',221203,'作業系統',1.5,3,'Details: xxxx','teacher2','106-1'),(15,'ta3',222207,'Java實作課程',1,1,'Details: xxxx','teacher','106-2');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `file`
--

DROP TABLE IF EXISTS `file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `file` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `FILENAME` varchar(255) DEFAULT NULL,
  `ISACTIVE` tinyint(1) DEFAULT NULL,
  `TIMESTAMP` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file`
--

LOCK TABLES `file` WRITE;
/*!40000 ALTER TABLE `file` DISABLE KEYS */;
INSERT INTO `file` VALUES (1,'test.txt',0,NULL),(2,'ROP Quiz.rar',1,NULL),(3,'ROP Quiz.rar',1,'2017-12-29 09:43:53'),(4,'test.txt',0,'2017-12-29 14:52:27'),(5,'Problem Framing.pdf',0,'2017-12-30 10:26:10'),(6,'test.txt',0,'2017-12-30 10:32:23'),(7,'userCourse.csv',1,'2018-01-01 19:35:12'),(11,'userCourse.csv',0,'2018-01-01 20:42:14'),(12,'userCourse.csv',1,'2018-01-01 20:43:12'),(13,'course-encoded.csv',1,'2018-01-01 20:44:59'),(14,'test_1.zip',1,'2018-01-01 22:19:12'),(15,'test (1).txt',1,'2018-01-01 22:26:36'),(16,'account.xlsx',0,'2018-01-02 00:13:36'),(17,'Problem Framing.pdf',1,'2018-01-02 00:14:14'),(18,'course-encoded.csv',1,'2018-01-02 00:22:17'),(19,'course-encoded.csv',1,'2018-01-02 00:22:17'),(20,'account.xlsx',0,'2018-01-02 00:26:56'),(21,'test.zip',0,'2018-01-02 09:01:17'),(22,'test.zip',0,'2018-01-02 09:05:34'),(23,'fail.zip',0,'2018-01-02 09:05:49'),(24,'test.zip',0,'2018-01-02 09:10:13'),(25,'test.zip',0,'2018-01-02 09:10:33'),(26,'fail.zip',0,'2018-01-02 09:10:47'),(27,'README.md',0,'2018-01-02 09:12:36'),(28,'test.zip',0,'2018-01-02 09:12:49'),(29,'README.md',0,'2018-01-02 09:15:17'),(30,'test.zip',0,'2018-01-02 09:16:56'),(31,'fail.zip',0,'2018-01-02 09:20:25'),(32,'test.zip',0,'2018-01-02 09:20:37'),(33,'test.zip',0,'2018-01-02 09:22:09'),(34,'test.zip',0,'2018-01-02 09:24:29'),(35,'test.zip',0,'2018-01-02 09:25:32'),(36,'test.zip',0,'2018-01-02 10:02:13'),(37,'test.zip',0,'2018-01-02 10:05:53'),(38,'README.md',1,'2018-01-02 10:06:02'),(39,'fail.zip',1,'2018-01-02 10:06:14'),(40,'fail.zip',0,'2018-01-02 10:06:14'),(41,'test.zip',0,'2018-01-02 10:06:39'),(42,'fail.zip',1,'2018-01-02 10:06:50'),(43,'test.zip',0,'2018-01-02 10:36:24'),(44,'test.zip',0,'2018-01-02 10:37:24'),(45,'test.zip',1,'2018-01-02 10:50:51'),(46,'test.zip',0,'2018-01-02 10:50:51'),(47,'test.zip',0,'2018-01-02 10:53:36'),(48,'test.zip',0,'2018-01-02 10:54:25'),(49,'test.zip',0,'2018-01-02 10:55:50'),(50,'test.zip',0,'2018-01-02 10:58:47'),(51,'test.zip',1,'2018-01-02 10:59:47');
/*!40000 ALTER TABLE `file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hw`
--

DROP TABLE IF EXISTS `hw`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hw` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `COURSEID` int(11) DEFAULT NULL,
  `DEADLINE` datetime DEFAULT NULL,
  `HWNAME` varchar(255) DEFAULT NULL,
  `LATE` tinyint(1) DEFAULT NULL,
  `OUTLINE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hw`
--

LOCK TABLES `hw` WRITE;
/*!40000 ALTER TABLE `hw` DISABLE KEYS */;
INSERT INTO `hw` VALUES (1,220201,'2018-01-01 20:54:36','Hello World!',1,'XXXXXX'),(3,221203,'2018-01-01 00:01:00','HW1',1,'xx1'),(4,221203,'2018-01-19 00:01:00','HW2',1,'xx1'),(5,221203,'2018-01-31 00:01:00','HW3',0,'xx1'),(6,220201,'2018-01-05 00:01:00','HW4',0,'xx1'),(7,220201,'2018-01-03 00:01:00','9x9 乘法表',0,'這不難，請加油！'),(8,222207,'2017-01-29 00:11:00','期末考猜題',0,'這是optional的作業~'),(9,221205,'2018-01-14 00:11:00','Homework A',1,'Test'),(10,220207,'2018-01-11 00:01:00','功課1',0,'好難的說呢！'),(11,220207,'2018-01-02 00:01:00','隨便的作業',0,'很難喔'),(12,220207,'2018-01-03 00:01:00','心理測驗',0,'請做心理測驗'),(13,222207,'2018-01-06 00:01:00','Test 2',1,'作業說明');
/*!40000 ALTER TABLE `hw` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACCOUNT` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `ROLE` varchar(255) DEFAULT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (75,'103820005','password','student','學生3'),(76,'103820004','password','student','學生2'),(77,'ta','password','ta','助教1'),(78,'teacher','password','teacher','老師1'),(79,'teacher2','password','teacher','老師2'),(80,'assistant','password','ta','助教2'),(81,'student','password','student','學生1'),(82,'admin','password','admin','管理員1');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usercourse`
--

DROP TABLE IF EXISTS `usercourse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usercourse` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `COURSEID` int(11) DEFAULT NULL,
  `USERACCOUNT` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usercourse`
--

LOCK TABLES `usercourse` WRITE;
/*!40000 ALTER TABLE `usercourse` DISABLE KEYS */;
INSERT INTO `usercourse` VALUES (46,220201,'103820004'),(47,220201,'103820005'),(48,221203,'student'),(49,221203,'103820005'),(50,220207,'103820004'),(51,220207,'student'),(52,220201,'student'),(53,221205,'103820004'),(54,222207,'103820005');
/*!40000 ALTER TABLE `usercourse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userhw`
--

DROP TABLE IF EXISTS `userhw`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userhw` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `CREATED_AT` datetime DEFAULT NULL,
  `FILEID` int(11) DEFAULT NULL,
  `HWID` int(11) DEFAULT NULL,
  `SCORE` int(11) DEFAULT NULL,
  `USERACCOUNT` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userhw`
--

LOCK TABLES `userhw` WRITE;
/*!40000 ALTER TABLE `userhw` DISABLE KEYS */;
INSERT INTO `userhw` VALUES (1,'This is a description.','2017-12-29 09:42:09',2,13,0,'student'),(2,'This is a description.','2017-12-29 09:43:53',3,13,0,'student'),(3,'','2017-12-29 14:52:27',7,1,0,NULL),(4,'忘記注記姓名 -10','2018-01-01 20:42:14',12,1,91,'student'),(5,'','2018-01-01 20:44:59',13,10,0,'student'),(6,'','2018-01-01 22:19:12',14,6,0,'student'),(7,'','2018-01-01 22:26:36',15,7,0,'student'),(8,'','2018-01-02 00:13:36',38,11,0,'student'),(9,'','2018-01-02 00:14:14',17,12,0,'student'),(12,'','2018-01-02 00:26:56',42,5,0,'student'),(13,'','2018-01-02 09:01:17',51,4,0,'student');
/*!40000 ALTER TABLE `userhw` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-02 11:05:30
