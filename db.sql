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
  `ASSISTANT` varchar(255) DEFAULT NULL,
  `COURSENAME` varchar(255) DEFAULT NULL,
  `OUTLINE` varchar(255) DEFAULT NULL,
  `PROFESSOR` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'3','Operating System','Details: XXXxxxXXX','5'),(2,'3','CS 101','Details: sdfxxxXXX','5');
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file`
--

LOCK TABLES `file` WRITE;
/*!40000 ALTER TABLE `file` DISABLE KEYS */;
INSERT INTO `file` VALUES (1,'test.txt',0,NULL),(2,'ROP Quiz.rar',1,NULL),(3,'ROP Quiz.rar',1,'2017-12-29 09:43:53'),(4,'test.txt',0,'2017-12-29 14:52:27'),(5,'Problem Framing.pdf',0,'2017-12-30 10:26:10'),(6,'test.txt',1,'2017-12-30 10:32:23');
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hw`
--

LOCK TABLES `hw` WRITE;
/*!40000 ALTER TABLE `hw` DISABLE KEYS */;
INSERT INTO `hw` VALUES (1,1,'2017-12-31 00:00:00','Hello World!',0,'XXXXXX');
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'103820004','50201of5537','student','Michael'),(2,'11111111','11111111','student','Esther Han'),(3,'ta','password','ta','Sophia Yi'),(4,'admin','password','admin','test'),(5,'teacher','password','teacher','老師1'),(6,'s','s','student','s'),(7,'wef','wef','student','wef'),(8,'we','wef','student','wef'),(9,'wef','wef','student','wfe');
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
  `USERID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usercourse`
--

LOCK TABLES `usercourse` WRITE;
/*!40000 ALTER TABLE `usercourse` DISABLE KEYS */;
INSERT INTO `usercourse` VALUES (1,1,1),(2,1,3);
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
  `UPDATED_AT` datetime DEFAULT NULL,
  `USERID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userhw`
--

LOCK TABLES `userhw` WRITE;
/*!40000 ALTER TABLE `userhw` DISABLE KEYS */;
INSERT INTO `userhw` VALUES (1,'This is a description.','2017-12-29 09:42:09',2,13,0,'2017-12-29 09:42:33',1),(2,'This is a description.','2017-12-29 09:43:53',3,13,0,'2017-12-29 09:43:53',2),(3,'swrfsh','2017-12-29 14:52:27',6,1,0,'2017-12-30 10:32:23',1);
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

-- Dump completed on 2017-12-30 20:26:13
