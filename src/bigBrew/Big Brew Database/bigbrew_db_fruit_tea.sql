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
-- Table structure for table `fruit_tea`
--

DROP TABLE IF EXISTS `fruit_tea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fruit_tea` (
  `Product Name` varchar(50) NOT NULL,
  `Medio Price` decimal(5,2) DEFAULT NULL,
  `Grande Price` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`Product Name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fruit_tea`
--

LOCK TABLES `fruit_tea` WRITE;
/*!40000 ALTER TABLE `fruit_tea` DISABLE KEYS */;
INSERT INTO `fruit_tea` VALUES ('Blueberry',29.00,39.00),('Green Apple',29.00,39.00),('Honey Peach',29.00,39.00),('Kiwi',29.00,39.00),('Lemon',29.00,39.00),('Lychee',29.00,39.00),('Mango',29.00,39.00),('Passion Fruit',29.00,39.00),('Strawberry',29.00,39.00),('Watermelon',29.00,39.00);
/*!40000 ALTER TABLE `fruit_tea` ENABLE KEYS */;
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
