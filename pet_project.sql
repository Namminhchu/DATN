-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: pet_project
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `idbooking` int NOT NULL AUTO_INCREMENT,
  `service_id` int NOT NULL COMMENT 'id bảng dịch vụ',
  `service_worker_id` int NOT NULL COMMENT 'id bảng thợ',
  `appointment_date` datetime DEFAULT NULL COMMENT '''Ngày hẹn làm dịch vụ''',
  `create_time` datetime DEFAULT NULL,
  `client_id` int NOT NULL COMMENT 'id khách hàng',
  `pet_name` varchar(45) DEFAULT NULL COMMENT '''tên thú cưng''',
  `pet_type` varchar(45) DEFAULT NULL COMMENT '''Loại thú cưng''',
  `phone` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL COMMENT '    0: ''Đang chờ xác nhận'',\\n    1: ''Đã xác nhận'',\\n    2: ''Đã hoàn thành''',
  PRIMARY KEY (`idbooking`),
  KEY `service_id_idx` (`service_id`),
  KEY `service_worker_id_idx` (`service_worker_id`),
  KEY `client_id_idx` (`client_id`),
  CONSTRAINT `client_id` FOREIGN KEY (`client_id`) REFERENCES `user` (`id`),
  CONSTRAINT `service_id` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`),
  CONSTRAINT `service_worker_id` FOREIGN KEY (`service_worker_id`) REFERENCES `service_worker` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COMMENT='Bảng đặt lịch';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,2,1,'2022-08-14 03:08:00','2022-08-25 21:02:04',56,'Mực','Chó','0987654321',2),(2,2,1,'2022-08-28 04:08:00','2022-08-25 21:03:24',56,'Bông','Chó','0987654321',1),(3,2,16,'2022-09-08 16:09:00','2022-08-25 21:39:36',56,'Nam :D','Meo','0987654321',0),(4,2,16,'2022-09-10 06:09:00','2022-08-25 22:57:47',56,'trtrt','trttttt','0987654321',2),(5,3,13,'2023-10-01 03:09:00','2023-09-17 20:57:26',56,'meo','chó','0987654321',0),(6,2,1,'2023-09-20 04:09:00','2023-09-17 21:04:14',56,'meo','chó','0987654321',0);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Chó'),(2,'Mèo'),(3,'Hamster');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_product`
--

DROP TABLE IF EXISTS `order_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_product` int NOT NULL,
  `id_order` int NOT NULL,
  `quantity` int DEFAULT NULL,
  `price` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_product` (`id_product`),
  KEY `id_order` (`id_order`),
  CONSTRAINT `order_product_ibfk_1` FOREIGN KEY (`id_product`) REFERENCES `product` (`id`),
  CONSTRAINT `order_product_ibfk_2` FOREIGN KEY (`id_order`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=393 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_product`
--

LOCK TABLES `order_product` WRITE;
/*!40000 ALTER TABLE `order_product` DISABLE KEYS */;
INSERT INTO `order_product` VALUES (382,37,177,1,220000),(383,39,178,1,120000),(384,37,179,1,220000),(385,39,179,1,120000),(386,37,180,1,220000),(387,37,181,1,220000),(388,37,182,1,220000),(390,37,183,2,440000),(391,37,184,3,660000);
/*!40000 ALTER TABLE `order_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_user` int NOT NULL,
  `totalprice` float DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone_number` varchar(10) DEFAULT NULL,
  `name_user` varchar(100) DEFAULT NULL,
  `orderDate` datetime DEFAULT CURRENT_TIMESTAMP,
  `order_date` datetime DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=186 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (177,56,220000,'Long An','0987654321','0987654321','2022-08-25 20:34:35','2022-08-25 00:00:00',3),(178,56,120000,'Long An','0987654321','0987654321','2023-03-05 17:57:19','2023-03-05 00:00:00',2),(179,56,340000,'Long An','0987654321','0987654321','2023-03-05 21:40:49','2023-03-05 00:00:00',3),(180,56,220000,'Long An','0987654321','0987654321','2023-03-11 19:49:19','2023-03-11 00:00:00',1),(181,56,220000,'Long An','0987654321','0987654321','2023-03-15 21:10:03','2023-03-15 00:00:00',1),(182,56,220000,'Long An','0987654321','0987654321','2023-03-22 17:39:44','2023-03-22 00:00:00',2),(183,56,440000,'Long An','0987654321','0987654321','2023-03-22 17:43:31','2023-03-22 00:00:00',2),(184,56,660000,'Long An','0987654321','0987654321','2023-09-17 20:56:23','2023-09-17 00:00:00',2);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_category` int NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `description` text,
  `price` float DEFAULT NULL,
  `image` text,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_category` (`id_category`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`id_category`) REFERENCES `category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (36,1,'Lược chải lông chó mèo bấm nút DELE M006 Round Head','<p>M&ocirc; tả</p>\n\n<p>Lược chải l&ocirc;ng ch&oacute; m&egrave;o bấm n&uacute;t&nbsp;<a href=\"https://www.petmart.vn/thuong-hieu/dele\" title=\"DELE\">DELE</a>&nbsp;M006 Round Head ph&ugrave; hợp với tất cả c&aacute;c giống ch&oacute;. Bao gồm cả c&aacute;c giống ch&oacute; nhỏ v&agrave; ch&oacute; lớn như Poodle, Phốc s&oacute;c, Samoyel, Alaska&hellip;</p>\n\n<h2>Lợi &iacute;ch ch&iacute;nh</h2>\n\n<p>Lược chải l&ocirc;ng ch&oacute; m&egrave;o bấm n&uacute;t DELE M006 Round Head c&oacute; tay cầm rất thoải m&aacute;i, an to&agrave;n v&agrave; chắc chắn. Khi sử dụng bạn chỉ cần bấm v&agrave;o n&uacute;t tr&ograve;n. C&oacute; n&uacute;t bấm kết hợp với c&aacute;c răng lược sẽ tự động được đẩy ra. L&ocirc;ng rụng sẽ được đẩy theo ra ngo&agrave;i. Kh&ocirc;ng cần sử dụng tay để gỡ. Điều n&agrave;y gi&uacute;p cho việc loại bỏ những l&ocirc;ng rụng, l&ocirc;ng thừa hay l&ocirc;ng chết dễ d&agrave;ng v&agrave; nhanh ch&oacute;ng hơn.&nbsp; Đảm bảo cho ch&oacute; m&egrave;o cưng c&oacute; một bộ l&ocirc;ng mềm mượt, kh&ocirc;ng xơ rối. Sản phẩm được thiết kế tinh xảo với th&eacute;p kh&ocirc;ng gỉ, chất lượng cao. L&agrave; vật dụng rất cần thiết trong nh&agrave; bất k&igrave; ai nu&ocirc;i th&uacute; cưng.</p>\n\n<p>&nbsp;</p>\n\n<h2>Pet Mart tại H&agrave; Nội</h2>\n\n<ul>\n	<li><strong>3 Đại Cồ Việt</strong>, Phường Cầu Dền, Quận Hai B&agrave; Trưng</li>\n	<li><strong>476 Minh Khai</strong>, Phường Vĩnh Tuy, Quận Hai B&agrave; Trưng</li>\n	<li><strong>83 Nghi T&agrave;m</strong>, Phường Y&ecirc;n Phụ, Quận T&acirc;y Hồ</li>\n	<li><strong>206 Kim M&atilde;</strong>, Phường Kim M&atilde;, Quận Ba Đ&igrave;nh</li>\n	<li><strong>18 Chả C&aacute;</strong>, Phường H&agrave;ng Đ&agrave;o, Quận Ho&agrave;n Kiếm</li>\n	<li><strong>242 Nguyễn Tr&atilde;i</strong>, Phường Thanh Xu&acirc;n Trung, Quận Thanh Xu&acirc;n</li>\n	<li><strong>290 Nguyễn Văn Cừ</strong>, Phường Bồ Đề, Quận Long Bi&ecirc;n</li>\n	<li><strong>Villa E10 Đỗ Đ&igrave;nh Thiện</strong>, Phường Mỹ Đ&igrave;nh, Quận Nam Từ Li&ecirc;m</li>\n	<li><strong>81 Quang Trung</strong>, Phường Quang Trung, Quận H&agrave; Đ&ocirc;ng</li>\n</ul>\n',140000,'6f7ac290-8e46-4bef-9bce-0b7a24ab4866.jpg','2022-08-11 23:09:22'),(37,1,'Khay vệ sinh cho chó MAKAR Dog Toilet Trays Small','<p>M&ocirc; tả</p>\n\n<p><a href=\"https://www.petmart.vn/danh-muc/cho/khay-ve-sinh-cho\" title=\"Khay vệ sinh cho chó\">Khay vệ sinh cho ch&oacute;</a>&nbsp;<a href=\"https://www.petmart.vn/thuong-hieu/makar\" title=\"MAKAR\">MAKAR</a>&nbsp;Dog Toilet Trays Small với thiết kế th&ocirc;ng minh được l&agrave;m từ 100% nhựa tổng hợp cao cấp an to&agrave;n với th&uacute; cưng v&agrave; m&ocirc;i trường. Ph&ugrave; hợp với tất cả c&aacute;c giống ch&oacute; v&agrave; giới t&iacute;nh đực c&aacute;i c&oacute; trọng lượng dưới 5kg.</p>\n\n<h2>Lợi &iacute;ch ch&iacute;nh</h2>\n\n<p>Khay vệ sinh cho ch&oacute; MAKAR Dog Toilet Trays Small với tấm lưới l&oacute;t để nước tiểu kh&ocirc;ng bị vung v&atilde;i khắp nh&agrave; v&agrave; l&agrave;m ướt l&ocirc;ng th&uacute; cưng. Phần khay lưới b&ecirc;n dưới chắc v&agrave; kh&ocirc;ng bị ăn m&ograve;n. Những ch&uacute; ch&oacute; con c&oacute; thể đứng tr&ecirc;n khay để đi vệ sinh dễ d&agrave;ng v&agrave; thuận tiện. Phần th&agrave;nh cao chống lại vi khuẩn, c&ocirc;n tr&ugrave;ng v&agrave; ngăn nước tiểu bắn ra ngo&agrave;i. Đặc biệt, c&uacute;n con sẽ đ&aacute;nh hơi được dễ d&agrave;ng v&agrave; x&aacute;c định được vị tr&iacute; để thải ra nhanh hơn. Dễ d&agrave;ng trong việc hướng dẫn v&agrave; huấn luyện.</p>\n\n<p>Khay vệ sinh cho ch&oacute; MAKAR Dog Toilet Trays Small c&oacute; thiết kế nhỏ gọn, đa dạng về kiểu d&aacute;ng v&agrave; m&agrave;u sắc. Bạn c&oacute; thể lựa chọn gam m&agrave;u ph&ugrave; hợp với kh&ocirc;ng gian nơi ở của m&igrave;nh. Việc vệ sinh cũng trở l&ecirc;n đơn giản hơn với chất liệu nhựa. Bạn c&oacute; thể dễ d&agrave;ng khử m&ugrave;i v&agrave; l&agrave;m sạch ch&uacute;ng. V&agrave; y&ecirc;n t&acirc;m v&igrave; c&uacute;n cưng sẽ kh&ocirc;ng c&ograve;n đi vệ sinh bừa b&atilde;i nữa</p>\n',220000,'58f6b37a-d81c-408a-a1cc-8961e5ac5d7d.jpg','2022-08-11 23:12:51'),(39,3,'Nhà gỗ lâu đài sắc màu','<p>Với mong muốn mang lại sự h&agrave;i l&ograve;ng cho qu&yacute; kh&aacute;ch khi mua h&agrave;ng, ch&uacute;ng t&ocirc;i c&oacute; những quy định trong vận chuyển, nhằm đảm bảo rằng những sản phẩm qu&yacute; kh&aacute;ch mua l&agrave; sản phẩm m&agrave; vừa &yacute; nhất.<br />\n1. Ch&uacute;ng t&ocirc;i sẽ được thực hiện v&agrave; chuyển ph&aacute;t dựa tr&ecirc;n mẫu kh&aacute;ch h&agrave;ng đ&atilde; chọn. Trường hợp kh&ocirc;ng c&oacute; đ&uacute;ng sản phẩm Qu&yacute; kh&aacute;ch y&ecirc;u cầu ch&uacute;ng t&ocirc;i sẽ gọi điện x&aacute;c nhận gửi sản phẩm tương tự thay thế.<br />\n2. Thời gian chuyển ph&aacute;t ti&ecirc;u chuẩn cho một đơn h&agrave;ng l&agrave; 12 giờ kể từ l&uacute;c đặt h&agrave;ng. Chuyển ph&aacute;t sản phẩm đến c&aacute;c khu vực nội th&agrave;nh th&agrave;nh phố tr&ecirc;n to&agrave;n quốc từ 4 giờ kể từ khi nhận h&agrave;ng, chuyển ph&aacute;t ngay trong ng&agrave;y đến c&aacute;c v&ugrave;ng l&acirc;n cận (b&aacute;n k&iacute;nh từ 10km &ndash; 50km).<br />\n3. C&aacute;c đơn h&agrave;ng gửi đi quốc tế: kh&ocirc;ng đảm bảo thời gian được ch&iacute;nh x&aacute;c như y&ecirc;u cầu, kh&ocirc;ng đảm bảo thời gian nếu thời điểm chuyển ph&aacute;t tr&ugrave;ng với c&aacute;c ng&agrave;y lễ, tết v&agrave; chủ nhật tại khu vực nơi đến.<br />\n4. Trường hợp kh&ocirc;ng li&ecirc;n lạc được với người nhận, người nhận đi vắng:<br />\n- Nếu chưa r&otilde; địa chỉ ch&uacute;ng t&ocirc;i sẽ lưu lại trong v&ograve;ng 6 tiếng v&agrave; li&ecirc;n lạc lại với người nhận, trong trường hợp ko li&ecirc;n lạc được đơn h&agrave;ng sẽ bị hủy v&agrave; kh&ocirc;ng được ho&agrave;n lại thanh to&aacute;n.<br />\n- Nếu địa chỉ l&agrave; c&ocirc;ng ty, văn ph&ograve;ng, nh&agrave; ở&hellip; Ch&uacute;ng t&ocirc;i sẽ gửi đồng nghiệp, người th&acirc;n nhận hộ v&agrave; k&yacute; x&aacute;c nhận<br />\n- Để tại một nơi an to&agrave;n người nhận dễ nhận thấy tại nh&agrave;, văn ph&ograve;ng, c&ocirc;ng ty&hellip; Trường hợp n&agrave;y kh&ocirc;ng c&oacute; k&yacute; nhận.<br />\n5. Trường hợp người nhận kh&ocirc;ng nhận đơn h&agrave;ng:<br />\n- Ch&uacute;ng t&ocirc;i sẽ hủy bỏ đơn h&agrave;ng. Trường hợp n&agrave;y sẽ kh&ocirc;ng được ho&agrave;n trả thanh to&aacute;n.<br />\n6. Trường hợp kh&ocirc;ng đ&uacute;ng địa chỉ, thay đổi địa chỉ:<br />\n- Kh&ocirc;ng đ&uacute;ng địa chỉ: trường hợp sai địa chỉ ch&uacute;ng t&ocirc;i sẽ lưu lại 6 tiếng v&agrave; li&ecirc;n lạc với người gửi v&agrave; người nhận để thỏa thuận về địa điểm, thời gian, nếu địa chỉ mới kh&ocirc;ng qu&aacute; 3km sẽ ph&aacute;t miễn ph&iacute;. Trường hợp địa chỉ mới xa hơn 3km sẽ t&iacute;nh th&ecirc;m ph&iacute; theo quy định chuyển ph&aacute;t.<br />\n7. Trường hợp kh&ocirc;ng tồn tại người nhận tại địa chỉ y&ecirc;u cầu: đơn h&agrave;ng sẽ được hủy v&agrave; kh&ocirc;ng được ho&agrave;n lại thanh to&aacute;n.<br />\n8. Ch&uacute;ng t&ocirc;i kh&ocirc;ng vận chuyển sản phẩm đến c&aacute;c địa chỉ tr&ecirc;n t&agrave;u hỏa, m&aacute;y bay, t&agrave;u thủy, khu vực nguy hiểm, c&aacute;c khu vực cấm&hellip;</p>\n',120000,'ccf632da-34df-4049-9a6f-fc206935f105.jpg','2022-08-11 23:16:52'),(40,1,'Lồng vận chuyển chó mèo','<p><em>54545454555</em></p>\n',55555600,'584e10e1-06ff-42a5-8d9f-59d60450f3ec.jpeg','2022-08-24 21:37:43');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ADMIN'),(2,'USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `price` decimal(15,0) NOT NULL,
  `description` longtext,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (2,'Dịch vụ cắt tỉa lông cho chó mèo',400000,'<h1>DỊCH VỤ CẮT TỈA L&Ocirc;NG CH&Oacute; M&Egrave;O H&Agrave; NỘI, TPHCM, Đ&Agrave; NẴNG, HẢI PH&Ograve;NG</h1>\n\n<p>Bạn đang t&igrave;m kiếm địa chỉ cung cấp&nbsp;<strong>dịch vụ cắt tỉa l&ocirc;ng ch&oacute; m&egrave;o</strong>&nbsp;(<a href=\"https://en.wikipedia.org/wiki/Dog_grooming\" rel=\"noopener\" target=\"_blank\">pet grooming</a>) chuy&ecirc;n nghiệp tại&nbsp;<strong>H&agrave; Nội &ndash; TP.HCM &ndash; Đ&agrave; Nẵng &ndash; Hải Ph&ograve;ng</strong>? Tại Anipat&nbsp;, ch&uacute;ng t&ocirc;i cung cấp đầy đủ tất cả c&aacute;c loại h&igrave;nh dịch vụ chăm s&oacute;c v&agrave; l&agrave;m đẹp trọn g&oacute;i tốt nhất d&agrave;nh cho th&uacute; cưng.</p>\n\n<p>Cắt l&ocirc;ng cho ch&oacute; m&egrave;o l&agrave; một vấn đề rất quan trọng. Việc đ&oacute; đảm bảo cho sự ph&aacute;t triển về sức khỏe, thể chất v&agrave; tinh thần cho th&uacute; cưng của bạn. Những th&uacute; cưng kh&ocirc;ng được chăm s&oacute;c, cắt tỉa v&agrave; l&agrave;m đẹp thường c&oacute; nguy cơ gặp phải bọ ch&eacute;t, ve rận, k&yacute; sinh tr&ugrave;ng v&agrave; c&aacute;c vấn đề về vi&ecirc;m da kh&aacute;c. Việc sử dụng dịch vụ cắt tỉa l&ocirc;ng ch&oacute; m&egrave;o tại Pet Mart định kỳ v&agrave; thường xuy&ecirc;n sẽ đem lại nhiều lợi &iacute;ch thiết thực cho vật nu&ocirc;i của bạn. H&atilde;y lập kế hoạch đưa th&uacute; cưng của bạn đến với ch&uacute;ng t&ocirc;i mỗi tuần.</p>\n'),(3,'Dịch vụ tắm cho chó mèo',500000,'<h1>DỊCH VỤ TẮM SPA TRỌN G&Oacute;I, CẠO L&Ocirc;NG CHO CH&Oacute; M&Egrave;O</h1>\n\n<p><strong>Dịch vụ tắm spa cho ch&oacute; m&egrave;o</strong>&nbsp;tại Anipat&nbsp;<strong>H&agrave; Nội, TP.HCM, Đ&agrave; Nẵng v&agrave; Hải Ph&ograve;ng</strong>&nbsp;với c&aacute;c phương ph&aacute;p chăm s&oacute;c to&agrave;n diện từ A &ndash; Z. Bao gồm: tắm, vắt tuyến h&ocirc;i, sấy kh&ocirc;, chải l&ocirc;ng rối, nhổ l&ocirc;ng tai, vệ sinh tai, cắt m&agrave;i m&oacute;ng ch&acirc;n, cạo l&ocirc;ng theo y&ecirc;u cầu. Tất cả đều với với mong muốn mang tới cho th&uacute; cưng của bạn một cuộc sống khỏe mạnh v&agrave; hạnh ph&uacute;c nhất.</p>\n\n<p>Với&nbsp;<a href=\"https://www.petmart.vn/tuyen-dung\">đội ngũ nh&acirc;n vi&ecirc;n&nbsp;</a>&nbsp;gi&agrave;u kinh nghiệm, kiến thức chuy&ecirc;n s&acirc;u sẽ tư vấn v&agrave; cung cấp cho bạn những g&oacute;i&nbsp;<a href=\"https://www.petmart.vn/dich-vu-tam-spa-cho-cho-meo\">dịch vụ tắm spa cho ch&oacute; m&egrave;o&nbsp;</a>&nbsp;chất lượng nhất. Những ch&uacute; c&uacute;n v&agrave; m&egrave;o cưng sẽ nhanh ch&oacute;ng được t&uacute;t lại nhan sắc trở l&ecirc;n xinh đẹp v&agrave; đ&aacute;ng y&ecirc;u hơn.</p>\n'),(4,'Dịch vụ spa chó mèo',200000,'<p>&nbsp;Ng&agrave;y nay, th&uacute; cưng đ&atilde; trở th&agrave;nh một người bạn tri kỷ, đồng h&agrave;nh của con người. Ch&iacute;nh v&igrave; vậy, việc ch&uacute;ng được chăm s&oacute;c sức khỏe l&agrave; điều rất cần thiết. Bạn đang cần t&igrave;m dịch vụ spa cho th&uacute; cưng nh&agrave; m&igrave;nh những chưa biết ở đ&acirc;u tốt? H&ocirc;m nay, nội dung b&agrave;i viết sau đ&acirc;y sẽ giới thiệu đến bạn cơ sở chuy&ecirc;n dịch vụ spa cho th&uacute; cưng uy t&iacute;n tại TPHCM. Mời bạn c&ugrave;ng tham khảo v&agrave; lựa chọn tốt nhất nh&eacute;!</p>\n\n<p>Dịch vụ spa cho th&uacute; cưng uy t&iacute;n<br />\n&nbsp;</p>\n\n<p>Dịch vụ spa cho th&uacute; cưng uy t&iacute;n<br />\n&nbsp;</p>\n\n<p>&nbsp; &nbsp; &nbsp; &nbsp; Dịch vụ spa th&uacute; cưng tại với c&aacute;c phương ph&aacute;p chăm s&oacute;c th&uacute; cưng to&agrave;n diện. Dịch vụ bao gồm c&aacute;c c&ocirc;ng việc như massage, cắt tỉa l&ocirc;ng, goorming, tắm, vắt tuyến h&ocirc;i, sấy kh&ocirc;, chải l&ocirc;ng rối, nhổ l&ocirc;ng tai, vệ sinh tai, cắt m&agrave;i m&oacute;ng ch&acirc;n, cạo l&ocirc;ng theo y&ecirc;u cầu,....</p>\n\n<p>&nbsp;</p>\n\n<p>&nbsp; &nbsp; &nbsp; &nbsp; Cụ thể ch&iacute;nh l&agrave; ngo&agrave;i việc chăm lo cho c&aacute;c b&eacute; th&uacute; cưng những nhu cầu cơ bản như ăn ngủ, c&aacute;c chủ nu&ocirc;i c&ograve;n muốn pet cưng của m&igrave;nh được vệ sinh, chăm s&oacute;c cho thật sạch sẽ, xinh đẹp v&agrave; thoải m&aacute;i nhất. Thế nhưng, kh&ocirc;ng phải ai cũng c&oacute; đủ thời gian để thực hiện việc vệ sinh, chăm s&oacute;c th&uacute; cưng của m&igrave;nh đ&uacute;ng c&aacute;ch. V&igrave; thế, một dịch vụ spa cho th&uacute; cưng uy t&iacute;n chuy&ecirc;n nghiệp, b&agrave;i bản ch&iacute;nh l&agrave; nhu cầu m&agrave; chủ nu&ocirc;i cần c&oacute; hiện nay.</p>\n');
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_worker`
--

DROP TABLE IF EXISTS `service_worker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_worker` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `description` longtext,
  `fullname` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `avatar` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_worker`
--

LOCK TABLES `service_worker` WRITE;
/*!40000 ALTER TABLE `service_worker` DISABLE KEYS */;
INSERT INTO `service_worker` VALUES (1,'Ha Noi ','<p>Tốt nghiệp tại trường Đại học văn Lang</p>\n\n<p>C&oacute; 3 năm kinh nghiệm trong c&aacute;c lĩnh vực tạo mẫu</p>\n\n<p>Đ&atilde; tửng đạt được nhiều giải thưởng trong th&agrave;nh phố về nh&agrave; tạo mẫu cho th&uacute; cưng</p>\n\n<p>T&iacute;nh t&igrave;nh năng nổ, gi&uacute;p cho kh&aacute;ch h&agrave;ng c&oacute; thể chọn ra được kiểu mẫu ph&ugrave; hợp cho th&uacute; cưng của m&igrave;nh</p>\n\n<p>Dịch vụ bao gồm : Tạo mẫu, l&agrave;m spa, chăm s&oacute;c th&uacute; cưng</p>\n\n<p>Để được tư vấn cụ thể sớm nhất, kh&aacute;ch h&agrave;ng c&oacute; thể li&ecirc;n hệ hệ trực tiếp tại cửa h&agrave;ng hoặc li&ecirc;n hệ trực tiếp với thợ th&ocirc;ng qua zalo</p>\n\n<p>&nbsp;</p>\n','Nguyen Tuan Anh','0123456789','c5956ea9-625e-443d-b937-81c642a287ac.jpg'),(13,'Nghe An','<p>Tốt nghiệp tại trường Đại học văn Lang</p>\n\n<p>C&oacute; 3 năm kinh nghiệm trong c&aacute;c lĩnh vực tạo mẫu</p>\n\n<p>Đ&atilde; tửng đạt được nhiều giải thưởng trong th&agrave;nh phố về nh&agrave; tạo mẫu cho th&uacute; cưng</p>\n\n<p>T&iacute;nh t&igrave;nh năng nổ, gi&uacute;p cho kh&aacute;ch h&agrave;ng c&oacute; thể chọn ra được kiểu mẫu ph&ugrave; hợp cho th&uacute; cưng của m&igrave;nh</p>\n\n<p>Dịch vụ bao gồm : Tạo mẫu, l&agrave;m spa, chăm s&oacute;c th&uacute; cưng</p>\n\n<p>Để được tư vấn cụ thể sớm nhất, kh&aacute;ch h&agrave;ng c&oacute; thể li&ecirc;n hệ hệ trực tiếp tại cửa h&agrave;ng hoặc li&ecirc;n hệ trực tiếp với thợ th&ocirc;ng qua zalo</p>\n','Nguyen Ba Dung','0123456789','6777b412-4f88-4400-88e9-ba7d6a9aef2a.jpg'),(15,'Thái Bình','<p>Tốt nghiệp tại trường Đại học văn Lang</p>\n\n<p>C&oacute; 3 năm kinh nghiệm trong c&aacute;c lĩnh vực tạo mẫu</p>\n\n<p>Đ&atilde; tửng đạt được nhiều giải thưởng trong th&agrave;nh phố về nh&agrave; tạo mẫu cho th&uacute; cưng</p>\n\n<p>T&iacute;nh t&igrave;nh năng nổ, gi&uacute;p cho kh&aacute;ch h&agrave;ng c&oacute; thể chọn ra được kiểu mẫu ph&ugrave; hợp cho th&uacute; cưng của m&igrave;nh</p>\n\n<p>Dịch vụ bao gồm : Tạo mẫu, l&agrave;m spa, chăm s&oacute;c th&uacute; cưng</p>\n\n<p>Để được tư vấn cụ thể sớm nhất, kh&aacute;ch h&agrave;ng c&oacute; thể li&ecirc;n hệ hệ trực tiếp tại cửa h&agrave;ng hoặc li&ecirc;n hệ trực tiếp với thợ th&ocirc;ng qua zalo</p>\n','Nguyễn Thanh Tùng','0123456788','7865e6a8-f612-4571-bc6d-c4a6d331775d.jpg'),(16,'TP Hồ Chí Minh','<p>Tốt nghiệp tại trường Đại học văn Lang</p>\n\n<p>C&oacute; 3 năm kinh nghiệm trong c&aacute;c lĩnh vực tạo mẫu</p>\n\n<p>Đ&atilde; tửng đạt được nhiều giải thưởng trong th&agrave;nh phố về nh&agrave; tạo mẫu cho th&uacute; cưng</p>\n\n<p>T&iacute;nh t&igrave;nh năng nổ, gi&uacute;p cho kh&aacute;ch h&agrave;ng c&oacute; thể chọn ra được kiểu mẫu ph&ugrave; hợp cho th&uacute; cưng của m&igrave;nh</p>\n\n<p>Dịch vụ bao gồm : Tạo mẫu, l&agrave;m spa, chăm s&oacute;c th&uacute; cưng</p>\n\n<p>Để được tư vấn cụ thể sớm nhất, kh&aacute;ch h&agrave;ng c&oacute; thể li&ecirc;n hệ hệ trực tiếp tại cửa h&agrave;ng hoặc li&ecirc;n hệ trực tiếp với thợ th&ocirc;ng qua zalo</p>\n','Long Long','0222222222','26e9bf48-bc9b-4b24-a215-9c9d19927c7e.jpg'),(17,'Quận 10 TP Hồ Chí Minh','<p>Tốt nghiệp tại trường Đại học văn Lang</p>\n\n<p>C&oacute; 3 năm kinh nghiệm trong c&aacute;c lĩnh vực tạo mẫu</p>\n\n<p>Đ&atilde; tửng đạt được nhiều giải thưởng trong th&agrave;nh phố về nh&agrave; tạo mẫu cho th&uacute; cưng</p>\n\n<p>T&iacute;nh t&igrave;nh năng nổ, gi&uacute;p cho kh&aacute;ch h&agrave;ng c&oacute; thể chọn ra được kiểu mẫu ph&ugrave; hợp cho th&uacute; cưng của m&igrave;nh</p>\n\n<p>Dịch vụ bao gồm : Tạo mẫu, l&agrave;m spa, chăm s&oacute;c th&uacute; cưng</p>\n\n<p>Để được tư vấn cụ thể sớm nhất, kh&aacute;ch h&agrave;ng c&oacute; thể li&ecirc;n hệ hệ trực tiếp tại cửa h&agrave;ng hoặc li&ecirc;n hệ trực tiếp với thợ th&ocirc;ng qua zalo</p>\n','Duy Vo','0211112356','fe015f16-f0fc-412b-823a-99917c16579e.jpg');
/*!40000 ALTER TABLE `service_worker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `fullname` varchar(100) DEFAULT NULL,
  `phone_number` varchar(50) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `image` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (49,'0399617063','$2a$12$dm909cg/9nwa5lCzPISLQ./pBZa1HOOedLD9xb//hYt85696m5BwC','Luong Tran Thanh Sang','4343444444','Tân An','2022-08-13 11:18:28',NULL,NULL),(52,'0971132155','$2a$12$PhwPAYuxh.CizJL.q4pPiOe1ZYgmz05Zvqq5alS4KYhCghZ6FzICq','Nguyen Hoang Nam',NULL,'Long an','2022-08-19 13:19:17',NULL,NULL),(53,'0938022856','$2a$12$EWnMzttIGuLcb66H3V8KDO0NKCmt2Kz1GS2jopVD7NylHzN4FcyvS','Tran Hue Man',NULL,'Quan 10 TP Hồ Chí Minh','2022-08-19 13:26:07',NULL,NULL),(55,'0399617064','$2a$12$7b/ttswW80Q1lbUiWFyWXutQyVMSFYXLGWkO6Me/L48w/LSISgUjO','Nguyen Hoang Nam','0399617064','Quan 10','2022-08-24 23:35:02',NULL,NULL),(56,'0987654321','$2a$12$Hm/gemU89jV53q3P5MWF8uuliU3Fzamjou.jwFxWsMZmSKCl2250i','Nguyễn Thị Ngọc Châu','0987654321','Long An','2022-08-25 18:54:39',NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_user` int NOT NULL,
  `id_role` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_role` (`id_role`),
  KEY `id_user` (`id_user`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`id_role`) REFERENCES `role` (`id`),
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (78,49,1),(81,52,1),(82,53,2),(84,55,2),(85,56,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-21 22:15:45
