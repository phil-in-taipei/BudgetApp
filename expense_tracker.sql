CREATE DATABASE  IF NOT EXISTS `expense_tracker` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `expense_tracker`;
-- MySQL dump 10.13  Distrib 8.0.29, for Linux (x86_64)
--
-- Host: localhost    Database: expense_tracker
-- ------------------------------------------------------
-- Server version	8.0.31-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `idaccount` int NOT NULL AUTO_INCREMENT,
  `bankId` int NOT NULL,
  `userId` int NOT NULL,
  `balance` decimal(12,2) NOT NULL,
  PRIMARY KEY (`idaccount`),
  KEY `bankId_idx` (`bankId`),
  KEY `userId_idx` (`userId`),
  CONSTRAINT `bankId` FOREIGN KEY (`bankId`) REFERENCES `bank` (`bank_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userId` FOREIGN KEY (`userId`) REFERENCES `user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,1,1,6247.94),(2,2,4,91022.87),(3,1,1,3253.54),(4,6,2,3258.78),(5,3,5,30509.44),(7,1,6,345.00),(8,5,3,4545.00);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bank`
--

DROP TABLE IF EXISTS `bank`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bank` (
  `bank_id` int NOT NULL AUTO_INCREMENT,
  `bank_name` varchar(255) NOT NULL,
  PRIMARY KEY (`bank_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank`
--

LOCK TABLES `bank` WRITE;
/*!40000 ALTER TABLE `bank` DISABLE KEYS */;
INSERT INTO `bank` VALUES (1,'Taiwan Bank'),(2,'MegaBank'),(3,'CitiBank'),(4,'ChangHua Bank'),(5,'HSBC'),(6,'Bank of America');
/*!40000 ALTER TABLE `bank` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `deposit`
--

DROP TABLE IF EXISTS `deposit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `deposit` (
  `iddeposit` int NOT NULL AUTO_INCREMENT,
  `amount` decimal(7,2) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `incomeSourceId` int NOT NULL,
  `accountId` int NOT NULL,
  PRIMARY KEY (`iddeposit`),
  KEY `sourceId_idx` (`incomeSourceId`),
  KEY `accountId_idx` (`accountId`),
  CONSTRAINT `accountId` FOREIGN KEY (`accountId`) REFERENCES `account` (`idaccount`),
  CONSTRAINT `income_source_id` FOREIGN KEY (`incomeSourceId`) REFERENCES `incomeSource` (`idincomeSource`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deposit`
--

LOCK TABLES `deposit` WRITE;
/*!40000 ALTER TABLE `deposit` DISABLE KEYS */;
INSERT INTO `deposit` VALUES (15,400.00,'2022-11-17 21:24:48',1,2),(16,2332.23,'2022-11-17 22:01:05',1,1),(17,45.24,'2022-11-17 22:01:43',2,4),(18,6452.21,'2022-11-17 22:06:17',4,5),(19,4567.21,'2022-11-17 22:06:58',2,5),(20,3453.30,'2022-11-17 22:08:14',1,5),(21,4562.00,'2022-11-17 22:18:28',1,5),(22,342.53,'2022-11-17 22:23:28',2,5),(23,33523.20,'2022-11-17 22:28:27',1,5),(24,34.20,'2022-11-17 22:31:25',3,3),(25,1000.00,'2022-11-18 12:51:44',3,1),(26,10000.00,'2022-11-18 13:00:12',2,1),(27,3400.00,'2022-11-18 16:05:57',4,3),(28,340.00,'2022-11-18 16:28:20',1,3),(29,45.00,'2022-11-18 16:32:05',2,1),(30,245.00,'2022-11-18 16:39:53',2,8),(31,200.00,'2022-11-18 16:57:29',5,8),(32,200.00,'2022-11-18 17:18:58',4,1),(33,200.00,'2022-11-18 17:24:30',1,8),(34,200.00,'2022-11-18 17:26:06',1,4),(35,30.00,'2022-11-18 17:30:36',2,8),(36,4000.00,'2022-11-18 17:33:03',5,8),(37,345.00,'2022-11-18 17:35:04',5,7),(38,500.00,'2022-11-18 20:20:42',5,5),(39,200.00,'2022-11-19 22:17:23',3,3);
/*!40000 ALTER TABLE `deposit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expense`
--

DROP TABLE IF EXISTS `expense`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `expense` (
  `idexpense` int NOT NULL AUTO_INCREMENT,
  `expense_name` varchar(300) NOT NULL,
  `user` int NOT NULL,
  PRIMARY KEY (`idexpense`),
  KEY `user_idx` (`user`),
  CONSTRAINT `user` FOREIGN KEY (`user`) REFERENCES `user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expense`
--

LOCK TABLES `expense` WRITE;
/*!40000 ALTER TABLE `expense` DISABLE KEYS */;
INSERT INTO `expense` VALUES (1,'Rent',1),(2,'Coffee',2),(3,'Gym',3),(4,'Movie',4),(5,'Gym Membership Fee',5),(6,'Car Maintenance',1);
/*!40000 ALTER TABLE `expense` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `incomeSource`
--

DROP TABLE IF EXISTS `incomeSource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `incomeSource` (
  `idincomeSource` int NOT NULL AUTO_INCREMENT,
  `income_name` varchar(300) NOT NULL,
  `user` int NOT NULL,
  PRIMARY KEY (`idincomeSource`),
  KEY `user_id_idx` (`user`),
  CONSTRAINT `user_id` FOREIGN KEY (`user`) REFERENCES `user` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `incomeSource`
--

LOCK TABLES `incomeSource` WRITE;
/*!40000 ALTER TABLE `incomeSource` DISABLE KEYS */;
INSERT INTO `incomeSource` VALUES (1,'Ivy English',2),(2,'Joe\'s Translation',4),(3,'Translation for Chen',3),(4,'Editing for MRT',6),(5,'Coding Class',7),(6,'Courier',6);
/*!40000 ALTER TABLE `incomeSource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spendingRecord`
--

DROP TABLE IF EXISTS `spendingRecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spendingRecord` (
  `spendingRecordId` int NOT NULL AUTO_INCREMENT,
  `expense_id` int NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `amount` decimal(7,2) NOT NULL,
  PRIMARY KEY (`spendingRecordId`),
  KEY `expense_id_idx` (`expense_id`),
  CONSTRAINT `expense_id` FOREIGN KEY (`expense_id`) REFERENCES `expense` (`idexpense`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spendingRecord`
--

LOCK TABLES `spendingRecord` WRITE;
/*!40000 ALTER TABLE `spendingRecord` DISABLE KEYS */;
INSERT INTO `spendingRecord` VALUES (1,1,'2022-11-18 19:08:29',200.00),(2,2,'2022-11-18 19:10:24',5.50),(3,5,'2022-11-18 19:13:54',200.00),(4,1,'2022-11-20 16:51:54',534.22),(5,6,'2022-11-20 16:53:45',2333.21);
/*!40000 ALTER TABLE `spendingRecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `given_name` varchar(16) NOT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `email` varchar(32) NOT NULL,
  `userid` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('sweeney','phil','sweeney.phil@gmail.com',1),('sarah','trudy','sarah@gmx.com',2),('Larry','DaBomb','larry@hotmail.com',3),('Joe','Meek','meek2@yahoo.com',4),('Bob','Jobs','jobs@yahoo.com',5),('Laura','Jameson','lj@gmx.com',6),('Janus','Begotten','JB@gmx.com',7),('Alex','Chilton','dsjds',9);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `withdraw`
--

DROP TABLE IF EXISTS `withdraw`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `withdraw` (
  `idwithdraw` int NOT NULL AUTO_INCREMENT,
  `accountId` int NOT NULL,
  `amount` decimal(7,2) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`idwithdraw`),
  KEY `accountID_idx` (`accountId`),
  CONSTRAINT `account_id` FOREIGN KEY (`accountId`) REFERENCES `account` (`idaccount`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `withdraw`
--

LOCK TABLES `withdraw` WRITE;
/*!40000 ALTER TABLE `withdraw` DISABLE KEYS */;
INSERT INTO `withdraw` VALUES (1,1,300.00,'2022-11-17 13:14:09'),(2,5,300.00,'2022-11-17 13:15:10'),(3,1,300.00,'2022-11-17 13:16:50'),(4,2,1000.35,'2022-11-17 22:13:12'),(5,1,1000.00,'2022-11-18 12:50:03'),(6,2,10000.00,'2022-11-18 12:52:55'),(7,1,10000.00,'2022-11-18 12:54:59'),(8,2,20000.00,'2022-11-18 13:01:15'),(9,3,200.00,'2022-11-18 15:57:40'),(10,5,1500.00,'2022-11-18 16:02:04'),(11,5,2000.00,'2022-11-18 16:33:44'),(12,8,10.00,'2022-11-18 16:46:00'),(13,8,10.00,'2022-11-18 17:03:34'),(14,8,10.00,'2022-11-18 17:05:45'),(15,5,300.00,'2022-11-18 17:07:00'),(16,4,400.00,'2022-11-18 17:09:08'),(17,3,400.00,'2022-11-18 17:10:39'),(18,2,200.00,'2022-11-18 17:11:59'),(19,8,100.00,'2022-11-18 17:14:15'),(20,3,1000.00,'2022-11-18 17:17:34');
/*!40000 ALTER TABLE `withdraw` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-21 20:52:42
