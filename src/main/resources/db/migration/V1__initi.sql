-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: lojinha
-- ------------------------------------------------------
-- Server version	8.4.0

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
-- Table structure for table `access`
--

DROP TABLE IF EXISTS `access`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `access` (
                          `id` bigint NOT NULL AUTO_INCREMENT,
                          `description` varchar(255) NOT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `UF` varchar(255) NOT NULL,
                           `address_type` enum('BILL','DELIVERY') NOT NULL,
                           `city` varchar(255) NOT NULL,
                           `neighbor` varchar(255) NOT NULL,
                           `number` varchar(255) NOT NULL,
                           `street` varchar(255) NOT NULL,
                           `zipcode` varchar(255) NOT NULL,
                           `user_id` bigint NOT NULL,
                           `complement` varchar(255) DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           KEY `FK6i66ijb8twgcqtetl8eeeed6v` (`user_id`),
                           CONSTRAINT `FK6i66ijb8twgcqtetl8eeeed6v` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `description` varchar(255) DEFAULT NULL,
                        `dt_expire` date NOT NULL,
                        `dt_payment` date DEFAULT NULL,
                        `status` enum('CHARGE','EXPIRED','OPEN','PAYED') NOT NULL,
                        `value` decimal(38,2) NOT NULL,
                        `cart_id` bigint NOT NULL,
                        `address_id` bigint NOT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `UKasn74is742viy5crqhob8mwa8` (`cart_id`),
                        KEY `FK1f5an6dq4k8u0idf4oxthpf8t` (`address_id`),
                        CONSTRAINT `FK1f5an6dq4k8u0idf4oxthpf8t` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`),
                        CONSTRAINT `FK96cqdvvmplxou72492ggoveyi` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `base_price` decimal(38,2) NOT NULL,
                        `discount` decimal(38,2) DEFAULT NULL,
                        `shipping` decimal(38,2) NOT NULL,
                        `bill_id` bigint DEFAULT NULL,
                        `coupon_id` bigint DEFAULT NULL,
                        `order_tracking_id` bigint DEFAULT NULL,
                        `payment_method_id` bigint NOT NULL,
                        `user_id` bigint NOT NULL,
                        `address_id` bigint NOT NULL,
                        `invoice_id` bigint DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `UKia168x9g5wqfifch7hrek707f` (`bill_id`),
                        UNIQUE KEY `UK91xvykt8fypax2075tfvnlr3v` (`order_tracking_id`),
                        UNIQUE KEY `UK7ru18idwoox072r93pfllrh08` (`invoice_id`),
                        KEY `FKhssg4lq3na2fshourw22b5ny9` (`coupon_id`),
                        KEY `FK672lbhtgmx2wevhbdou5wlrq9` (`payment_method_id`),
                        KEY `FKg5uhi8vpsuy0lgloxk2h4w5o6` (`user_id`),
                        KEY `FKrgitg9w8ege609hlfahe29rjc` (`address_id`),
                        CONSTRAINT `FK4tl2se3i1af6faus9cnbonxbj` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id`),
                        CONSTRAINT `FK672lbhtgmx2wevhbdou5wlrq9` FOREIGN KEY (`payment_method_id`) REFERENCES `payment_method` (`id`),
                        CONSTRAINT `FKg5uhi8vpsuy0lgloxk2h4w5o6` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
                        CONSTRAINT `FKhssg4lq3na2fshourw22b5ny9` FOREIGN KEY (`coupon_id`) REFERENCES `coupon` (`id`),
                        CONSTRAINT `FKid4hdt92t65yym1vpmt6xsavf` FOREIGN KEY (`order_tracking_id`) REFERENCES `order_tracking` (`id`),
                        CONSTRAINT `FKlb0smauhby6uwlunid4fslmx1` FOREIGN KEY (`bill_id`) REFERENCES `bill` (`id`),
                        CONSTRAINT `FKrgitg9w8ege609hlfahe29rjc` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cart_item`
--

DROP TABLE IF EXISTS `cart_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_item` (
                             `id` bigint NOT NULL AUTO_INCREMENT,
                             `quantity` int NOT NULL,
                             `cart_id` bigint NOT NULL,
                             `product_id` bigint NOT NULL,
                             PRIMARY KEY (`id`),
                             KEY `FK1uobyhgl1wvgt1jpccia8xxs3` (`cart_id`),
                             KEY `FKjcyd5wv4igqnw413rgxbfu4nv` (`product_id`),
                             CONSTRAINT `FK1uobyhgl1wvgt1jpccia8xxs3` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`),
                             CONSTRAINT `FKjcyd5wv4igqnw413rgxbfu4nv` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `coupon`
--

DROP TABLE IF EXISTS `coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `coupon` (
                          `id` bigint NOT NULL AUTO_INCREMENT,
                          `code` varchar(255) NOT NULL,
                          `value` decimal(38,2) NOT NULL,
                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `image_product`
--

DROP TABLE IF EXISTS `image_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image_product` (
                                 `id` bigint NOT NULL AUTO_INCREMENT,
                                 `big_img_path` varchar(255) DEFAULT NULL,
                                 `short_img_path` varchar(255) DEFAULT NULL,
                                 `product_id` bigint NOT NULL,
                                 PRIMARY KEY (`id`),
                                 KEY `FKml4177k7ufupebm7e4wgmvpnj` (`product_id`),
                                 CONSTRAINT `FKml4177k7ufupebm7e4wgmvpnj` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `number` varchar(255) NOT NULL,
                           `pdf` text,
                           `series` varchar(255) NOT NULL,
                           `type` varchar(255) NOT NULL,
                           `xml` text,
                           `cart_id` bigint NOT NULL,
                           PRIMARY KEY (`id`),
                           UNIQUE KEY `UKsrok2ccltai9l9262g3c5gsst` (`cart_id`),
                           CONSTRAINT `FKnn4rb9dhqyfjnilc5ajlnhwj8` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `order_tracking`
--

DROP TABLE IF EXISTS `order_tracking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_tracking` (
                                  `id` bigint NOT NULL AUTO_INCREMENT,
                                  `date` date DEFAULT NULL,
                                  `status` varchar(255) DEFAULT NULL,
                                  `cart_id` bigint NOT NULL,
                                  PRIMARY KEY (`id`),
                                  UNIQUE KEY `UK339wjyw94cdsntp1nu0gcv05g` (`cart_id`),
                                  CONSTRAINT `FKh3rbdas3jh3lti9rtjue9msh5` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `payment_method`
--

DROP TABLE IF EXISTS `payment_method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment_method` (
                                  `id` bigint NOT NULL AUTO_INCREMENT,
                                  `type` varchar(255) NOT NULL,
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `available` tinyint(1) NOT NULL,
                           `description` varchar(255) NOT NULL,
                           `dimensions` varchar(255) NOT NULL,
                           `name` varchar(255) NOT NULL,
                           `value` decimal(38,2) NOT NULL,
                           `video_url` varchar(255) DEFAULT NULL,
                           `category_id` bigint NOT NULL,
                           PRIMARY KEY (`id`),
                           KEY `FK5cypb0k23bovo3rn1a5jqs6j4` (`category_id`),
                           CONSTRAINT `FK5cypb0k23bovo3rn1a5jqs6j4` FOREIGN KEY (`category_id`) REFERENCES `product_category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product_category`
--

DROP TABLE IF EXISTS `product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_category` (
                                    `id` bigint NOT NULL AUTO_INCREMENT,
                                    `name` varchar(255) NOT NULL,
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
                         `id` bigint NOT NULL AUTO_INCREMENT,
                         `CPF` varchar(255) NOT NULL,
                         `email` varchar(255) NOT NULL,
                         `name` varchar(255) NOT NULL,
                         `password` varchar(255) NOT NULL,
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users_access`
--

DROP TABLE IF EXISTS `users_access`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_access` (
                                `user_id` bigint NOT NULL,
                                `access_id` bigint NOT NULL,
                                UNIQUE KEY `unique_user_access` (`user_id`,`access_id`),
                                KEY `access_fk` (`access_id`),
                                CONSTRAINT `access_fk` FOREIGN KEY (`access_id`) REFERENCES `access` (`id`),
                                CONSTRAINT `user_fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-23 15:52:17
