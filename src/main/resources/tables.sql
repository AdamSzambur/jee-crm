-- MySQL dump 10.13  Distrib 5.7.26, for Linux (x86_64)
--
-- Host: localhost    Database: jeecrm
-- ------------------------------------------------------
-- Server version	5.7.26-0ubuntu0.18.04.1

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `firstname` varchar(45) COLLATE utf8_polish_ci DEFAULT NULL,
                            `lastname` varchar(45) COLLATE utf8_polish_ci DEFAULT NULL,
                            `birth_date` timestamp NULL DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Adam','Szamburski','1978-12-03 23:00:00'),(2,'Michał','Szamburski','1980-08-31 22:00:00');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employee` (
                            `id` int(11) NOT NULL AUTO_INCREMENT,
                            `firstname` varchar(45) COLLATE utf8_polish_ci DEFAULT NULL,
                            `lastname` varchar(45) COLLATE utf8_polish_ci DEFAULT NULL,
                            `address` varchar(250) COLLATE utf8_polish_ci DEFAULT NULL,
                            `phone_number` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
                            `note` text COLLATE utf8_polish_ci,
                            `hour_cost` double DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,'Marek','Krzeszowski','Kąty Wrocławskie ul. Buraczana 13','662066932','Najlepszy kierownik na świecie',15);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `date_delivered_to_repair` timestamp NULL DEFAULT NULL,
                          `date_planned_repair` timestamp NULL DEFAULT NULL,
                          `date_started_repair` timestamp NULL DEFAULT NULL,
                          `employee_id` int(11) DEFAULT NULL,
                          `problem_description` text COLLATE utf8_polish_ci,
                          `repair_description` text COLLATE utf8_polish_ci,
                          `status_id` int(11) DEFAULT NULL,
                          `vehicle_id` int(11) DEFAULT NULL,
                          `repair_cost_for_customer` double DEFAULT NULL,
                          `cost_of_parts` double DEFAULT NULL,
                          `cost_of_man_hour` double DEFAULT NULL,
                          `man_hour` int(11) DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          KEY `fk_order_1_idx` (`vehicle_id`),
                          KEY `fk_order_2_idx` (`employee_id`),
                          CONSTRAINT `fk_order_1` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
                          CONSTRAINT `fk_order_2` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (4,'2019-04-30 22:00:00','2019-05-31 22:00:00','2019-05-31 22:00:00',1,'Wymiana oleju','',3,1,0,0,15,0);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `status` (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `value` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'Przyjęty'),(2,'Zatwierdzone koszty naprawy'),(3,'W naprawie'),(4,'Gotowy do odbioru'),(5,'Rezygnacja');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle`
--

DROP TABLE IF EXISTS `vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vehicle` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `model` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
                           `car_brand` varchar(45) COLLATE utf8_polish_ci DEFAULT NULL,
                           `production_year` int(11) DEFAULT NULL,
                           `registration_number` varchar(45) COLLATE utf8_polish_ci DEFAULT NULL,
                           `next_technical_inspection` timestamp NULL DEFAULT NULL,
                           `customer_id` int(11) DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           KEY `fk_vehicle_1_idx` (`customer_id`),
                           CONSTRAINT `fk_vehicle_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle`
--

LOCK TABLES `vehicle` WRITE;
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
INSERT INTO `vehicle` VALUES (1,'E46','BMW',2001,'DW 3455J','2019-10-10 22:00:00',1),(2,'A4','Audi',2018,'ETM 34564','2019-12-31 23:00:00',2);
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-18 20:14:08