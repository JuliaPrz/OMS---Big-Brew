-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
-- Author: Perez, Julia (11-2023)
-- Host: 127.0.0.1    Database: bigbrew_db
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `iced_coffee`
--

DROP TABLE IF EXISTS `iced_coffee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `iced_coffee` (
  `Product Name` varchar(50) NOT NULL,
  `Medio Price` decimal(5,2) DEFAULT NULL,
  `Grande Price` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`Product Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iced_coffee`
--

LOCK TABLES `iced_coffee` WRITE;
/*!40000 ALTER TABLE `iced_coffee` DISABLE KEYS */;
INSERT INTO `iced_coffee` VALUES ('Kape Brusko',29.00,39.00),('Kape Caramel',29.00,39.00),('Kape Fudge',29.00,39.00),('Kape Macch',29.00,39.00),('Kape Macchiato',29.00,39.00),('Kape Matcha',29.00,39.00),('Kape Moca',29.00,39.00),('Kape Spanish',29.00,39.00),('Kape Vanilla',29.00,39.00),('Kape Vietnamese',29.00,39.00),('Kape White Chocolate',29.00,39.00);
/*!40000 ALTER TABLE `iced_coffee` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-30 11:12:06
