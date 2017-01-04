-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.15


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema ordermgmtdb
--

CREATE DATABASE IF NOT EXISTS ordermgmtdb;
USE ordermgmtdb;

--
-- Definition of table `customer_details`
--

DROP TABLE IF EXISTS `customer_details`;
CREATE TABLE `customer_details` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1007 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer_details`
--

/*!40000 ALTER TABLE `customer_details` DISABLE KEYS */;
INSERT INTO `customer_details` (`id`,`name`,`address`,`email`) VALUES 
 (1001,'Ankur','1,Indore,MP,10','ankur_mehta@persistent.co.in'),
 (1002,'Suyash','2,Indore,MP,10','s_m@per.no'),
 (1003,'Aabhas','3,Khargone,MP,16','a@abac.com'),
 (1004,'Tejas','4,Nasik,MH,48','tj@abac.com'),
 (1005,'Nilesh','5,Bhopal,MP,51','nilesh_muley@persistent.co.in'),
 (1006,'Aakash','6,Bhopal,MP,21','ak47@abc.com');
/*!40000 ALTER TABLE `customer_details` ENABLE KEYS */;


--
-- Definition of table `customer_order_details`
--

DROP TABLE IF EXISTS `customer_order_details`;
CREATE TABLE `customer_order_details` (
  `customer_no` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `order_no` int(10) unsigned NOT NULL,
  PRIMARY KEY (`customer_no`,`order_no`),
  KEY `FK_customer_order_details_2` (`order_no`),
  CONSTRAINT `FK_customer_order_details_1` FOREIGN KEY (`customer_no`) REFERENCES `customer_details` (`id`),
  CONSTRAINT `FK_customer_order_details_2` FOREIGN KEY (`order_no`) REFERENCES `ordereditem_details` (`orderno`)
) ENGINE=InnoDB AUTO_INCREMENT=1007 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer_order_details`
--

/*!40000 ALTER TABLE `customer_order_details` DISABLE KEYS */;
INSERT INTO `customer_order_details` (`customer_no`,`order_no`) VALUES 
 (1001,2001),
 (1003,2002),
 (1002,2003),
 (1005,2003),
 (1002,2004),
 (1006,2004),
 (1002,2005),
 (1004,2006);
/*!40000 ALTER TABLE `customer_order_details` ENABLE KEYS */;


--
-- Definition of table `ordereditem_details`
--

DROP TABLE IF EXISTS `ordereditem_details`;
CREATE TABLE `ordereditem_details` (
  `orderno` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id` int(10) unsigned NOT NULL,
  `quantity` int(10) unsigned NOT NULL,
  PRIMARY KEY (`orderno`),
  KEY `id` (`id`),
  CONSTRAINT `id` FOREIGN KEY (`id`) REFERENCES `stockitem_details` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2009 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ordereditem_details`
--

/*!40000 ALTER TABLE `ordereditem_details` DISABLE KEYS */;
INSERT INTO `ordereditem_details` (`orderno`,`id`,`quantity`) VALUES 
 (2001,1231,10),
 (2002,1231,12),
 (2003,1232,10),
 (2004,1233,5),
 (2005,1231,3),
 (2006,1234,5),
 (2007,1235,5),
 (2008,1236,1);
/*!40000 ALTER TABLE `ordereditem_details` ENABLE KEYS */;


--
-- Definition of table `purchaseorder_details`
--

DROP TABLE IF EXISTS `purchaseorder_details`;
CREATE TABLE `purchaseorder_details` (
  `orderno` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `order_date` date NOT NULL,
  `ship_date` date NOT NULL,
  PRIMARY KEY (`orderno`)
) ENGINE=InnoDB AUTO_INCREMENT=2009 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `purchaseorder_details`
--

/*!40000 ALTER TABLE `purchaseorder_details` DISABLE KEYS */;
INSERT INTO `purchaseorder_details` (`orderno`,`order_date`,`ship_date`) VALUES 
 (2001,'2016-12-07','2016-12-08'),
 (2002,'2016-12-05','2016-12-08'),
 (2003,'2016-12-08','2016-12-08'),
 (2004,'2016-12-01','2016-12-31'),
 (2005,'2016-11-28','2016-11-30'),
 (2006,'2016-10-25','2016-10-29'),
 (2007,'2016-01-01','2017-01-01'),
 (2008,'2015-01-25','2016-12-10');
/*!40000 ALTER TABLE `purchaseorder_details` ENABLE KEYS */;


--
-- Definition of table `shipped_order_details`
--

DROP TABLE IF EXISTS `shipped_order_details`;
CREATE TABLE `shipped_order_details` (
  `customer_no` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `order_no` int(10) unsigned NOT NULL,
  `order_date` date NOT NULL,
  `ship_date` date NOT NULL,
  `stockitemid` int(10) unsigned NOT NULL,
  `ship_qty` int(10) unsigned NOT NULL,
  `shipped_by_person` varchar(45) NOT NULL,
  PRIMARY KEY (`customer_no`,`order_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1007 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `shipped_order_details`
--

/*!40000 ALTER TABLE `shipped_order_details` DISABLE KEYS */;
INSERT INTO `shipped_order_details` (`customer_no`,`order_no`,`order_date`,`ship_date`,`stockitemid`,`ship_qty`,`shipped_by_person`) VALUES 
 (1001,2001,'2016-12-07','2016-12-08',1231,10,'bhau'),
 (1002,2003,'2016-12-08','2016-12-08',1232,10,'abc'),
 (1002,2004,'2016-12-01','2016-12-31',1233,5,'bhau'),
 (1002,2005,'2016-11-28','2016-11-30',1231,3,'bhau'),
 (1003,2002,'2016-12-05','2016-12-08',1231,12,'bhau'),
 (1004,2006,'2016-10-25','2016-10-29',1234,5,'bhau'),
 (1005,2003,'2016-12-08','2016-12-08',1232,10,'xyz'),
 (1006,2004,'2016-12-01','2016-12-31',1233,5,'a13');
/*!40000 ALTER TABLE `shipped_order_details` ENABLE KEYS */;


--
-- Definition of table `stockitem_details`
--

DROP TABLE IF EXISTS `stockitem_details`;
CREATE TABLE `stockitem_details` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `item_des` varchar(45) DEFAULT NULL,
  `unit` varchar(10) NOT NULL,
  `price per unit` int(10) unsigned NOT NULL,
  `Mfg_date` date NOT NULL,
  `Best_before_date` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1237 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stockitem_details`
--

/*!40000 ALTER TABLE `stockitem_details` DISABLE KEYS */;
INSERT INTO `stockitem_details` (`id`,`name`,`item_des`,`unit`,`price per unit`,`Mfg_date`,`Best_before_date`) VALUES 
 (1231,'Onion','good for summer','kg',60,'2016-11-11','2016-10-16'),
 (1232,'Tomato_kechup','mixed','grams',1,'2016-12-12','2017-12-12'),
 (1233,'crocine','medicine','nos',5,'2016-12-16','2017-12-16'),
 (1234,'mobile','iPhone','nos',50000,'2015-10-15','2020-01-15'),
 (1235,'petrol','plok','gallon',72,'0885-11-10','2050-12-02'),
 (1236,'oil','palm','gallon',141,'2015-01-02','2020-01-02');
/*!40000 ALTER TABLE `stockitem_details` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
