CREATE DATABASE  IF NOT EXISTS `jpa_basic_crud_find` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `jpa_basic_crud_find`;
-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: jpa_basic_crud_find
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'1@gildong.com','010-0000-0001','홍길동1'),(2,'1@gildong.com','010-0000-0002','홍길동1'),(3,'3@gildong.com','010-0000-0003','홍길동3'),(4,'4@gildong.com','010-0000-0004','홍길동4'),(5,'5@gildong.com','010-0000-0005','홍길동5'),(6,'6@gildong.com','010-0000-0006','홍길동6'),(7,'7@gildong.com','010-0000-0007','홍길동7'),(8,'8@gildong.com','010-0000-0008','홍길동8'),(9,'9@gildong.com','010-0000-0009','홍길동9'),(10,'10@gildong.com','010-0000-0010','홍길동10'),(11,'11@gildong.com','010-0000-0011','홍길동11'),(12,'12@gildong.com','010-0000-0012','홍길동12'),(13,'13@gildong.com','010-0000-0013','홍길동13'),(14,'14@gildong.com','010-0000-0014','홍길동14'),(15,'15@gildong.com','010-0000-0015','홍길동15'),(16,'16@gildong.com','010-0000-0016','홍길동16'),(17,'17@gildong.com','010-0000-0017','홍길동17'),(18,'18@gildong.com','010-0000-0018','홍길동18'),(19,'19@gildong.com','010-0000-0019','홍길동19'),(20,'20@gildong.com','010-0000-0020','홍길동20'),(21,'21@gildong.com','010-0000-0021','홍길동21'),(22,'22@gildong.com','010-0000-0022','홍길동22'),(23,'23@gildong.com','010-0000-0023','홍길동23'),(24,'24@gildong.com','010-0000-0024','홍길동24'),(25,'25@gildong.com','010-0000-0025','홍길동25'),(26,'26@gildong.com','010-0000-0026','홍길동26'),(27,'27@gildong.com','010-0000-0027','홍길동27'),(28,'28@gildong.com','010-0000-0028','홍길동28'),(29,'29@gildong.com','010-0000-0029','홍길동29'),(30,'30@gildong.com','010-0000-0030','홍길동30'),(31,'31@gildong.com','010-0000-0031','홍길동31'),(32,'32@gildong.com','010-0000-0032','홍길동32'),(33,'33@gildong.com','010-0000-0033','홍길동33'),(34,'34@gildong.com','010-0000-0034','홍길동34'),(35,'35@gildong.com','010-0000-0035','홍길동35'),(36,'36@gildong.com','010-0000-0036','홍길동36'),(37,'37@gildong.com','010-0000-0037','홍길동37'),(38,'38@gildong.com','010-0000-0038','홍길동38'),(39,'39@gildong.com','010-0000-0039','홍길동39'),(40,'40@gildong.com','010-0000-0040','홍길동40'),(41,'41@gildong.com','010-0000-0041','홍길동41'),(42,'42@gildong.com','010-0000-0042','홍길동42'),(43,'43@gildong.com','010-0000-0043','홍길동43'),(44,'44@gildong.com','010-0000-0044','홍길동44'),(45,'45@gildong.com','010-0000-0045','홍길동45'),(46,'46@gildong.com','010-0000-0046','홍길동46'),(47,'47@gildong.com','010-0000-0047','홍길동47'),(48,'48@gildong.com','010-0000-0048','홍길동48'),(49,'49@gildong.com','010-0000-0049','홍길동49'),(50,'50@gildong.com','010-0000-0050','홍길동50'),(51,'51@gildong.com','010-0000-0051','홍길동51'),(52,'52@gildong.com','010-0000-0052','홍길동52'),(53,'53@gildong.com','010-0000-0053','홍길동53'),(54,'54@gildong.com','010-0000-0054','홍길동54'),(55,'55@gildong.com','010-0000-0055','홍길동55'),(56,'56@gildong.com','010-0000-0056','홍길동56'),(57,'57@gildong.com','010-0000-0057','홍길동57'),(58,'58@gildong.com','010-0000-0058','홍길동58'),(59,'59@gildong.com','010-0000-0059','홍길동59'),(60,'60@gildong.com','010-0000-0060','홍길동60'),(61,'61@gildong.com','010-0000-0061','홍길동61'),(62,'62@gildong.com','010-0000-0062','홍길동62'),(63,'63@gildong.com','010-0000-0063','홍길동63'),(64,'64@gildong.com','010-0000-0064','홍길동64'),(65,'65@gildong.com','010-0000-0065','홍길동65'),(66,'66@gildong.com','010-0000-0066','홍길동66'),(67,'67@gildong.com','010-0000-0067','홍길동67'),(68,'68@gildong.com','010-0000-0068','홍길동68'),(69,'69@gildong.com','010-0000-0069','홍길동69'),(70,'70@gildong.com','010-0000-0070','홍길동70'),(71,'71@gildong.com','010-0000-0071','홍길동71'),(72,'72@gildong.com','010-0000-0072','홍길동72'),(73,'73@gildong.com','010-0000-0073','홍길동73'),(74,'74@gildong.com','010-0000-0074','홍길동74'),(75,'75@gildong.com','010-0000-0075','홍길동75'),(76,'76@gildong.com','010-0000-0076','홍길동76'),(77,'77@gildong.com','010-0000-0077','홍길동77'),(78,'78@gildong.com','010-0000-0078','홍길동78'),(79,'79@gildong.com','010-0000-0079','홍길동79'),(80,'80@gildong.com','010-0000-0080','홍길동80'),(81,'81@gildong.com','010-0000-0081','홍길동81'),(82,'82@gildong.com','010-0000-0082','홍길동82'),(83,'83@gildong.com','010-0000-0083','홍길동83'),(84,'84@gildong.com','010-0000-0084','홍길동84'),(85,'85@gildong.com','010-0000-0085','홍길동85'),(86,'86@gildong.com','010-0000-0086','홍길동86'),(87,'87@gildong.com','010-0000-0087','홍길동87'),(88,'88@gildong.com','010-0000-0088','홍길동88'),(89,'89@gildong.com','010-0000-0089','홍길동89'),(90,'90@gildong.com','010-0000-0090','홍길동90'),(91,'91@gildong.com','010-0000-0091','홍길동91'),(92,'92@gildong.com','010-0000-0092','홍길동92'),(93,'93@gildong.com','010-0000-0093','홍길동93'),(94,'94@gildong.com','010-0000-0094','홍길동94'),(95,'95@gildong.com','010-0000-0095','홍길동95'),(96,'96@gildong.com','010-0000-0096','홍길동96'),(97,'97@gildong.com','010-0000-0097','홍길동97'),(98,'98@gildong.com','010-0000-0098','홍길동98'),(99,'99@gildong.com','010-0000-0099','홍길동99'),(100,'100@gildong.com','010-0000-0100','홍길동100');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-27  2:02:03
