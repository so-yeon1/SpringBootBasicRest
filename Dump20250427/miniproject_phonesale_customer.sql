CREATE DATABASE  IF NOT EXISTS `miniproject_phonesale` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `miniproject_phonesale`;
-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: miniproject_phonesale
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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `cust_id` int NOT NULL AUTO_INCREMENT,
  `cust_name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cust_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'민준혁','강원도 양구군 석촌호수882거리 (미정김리)','054-669-2951'),(2,'이종수','충청남도 연천군 서초중앙로 (광수김동)','033-385-4669'),(3,'김명숙','강원도 안양시 만안구 봉은사가 (정숙김마을)','010-5908-5255'),(4,'이건우','대구광역시 노원구 압구정101가','018-029-9668'),(5,'윤정숙','제주특별자치도 홍천군 가락로','043-488-6052'),(6,'박지훈','서울특별시 종로구 안국동 (혜정길)','010-2591-4837'),(7,'정재훈','경기도 수원시 권선구 호매실로','031-543-7631'),(8,'배수현','부산광역시 해운대구 해운대해변로','051-223-2548'),(9,'장성호','인천광역시 남동구 구월로','032-784-4230'),(10,'김미진','경기도 고양시 일산동구 일산로','031-411-1223'),(11,'최민수','대전광역시 유성구 봉명동','042-586-7213'),(12,'오민정','울산광역시 남구 옥동로','052-722-1589'),(13,'김수지','서울특별시 동작구 상도동','010-3356-4789'),(14,'이상우','경상북도 포항시 북구','054-234-6570'),(15,'홍지영','광주광역시 서구 상무대로','062-489-7225'),(16,'이희정','경기도 의정부시 신곡로','031-787-2495'),(17,'임진영','전라북도 전주시 덕진구','063-237-5423'),(18,'정상윤','충청북도 청주시 청원구','043-268-3764'),(19,'양지혜','서울특별시 강서구 공항대로','010-5621-9321'),(20,'허윤아','부산광역시 부산진구 전포동','051-716-9483');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-27  2:01:59
