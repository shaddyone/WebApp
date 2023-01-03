/*
SQLyog Community v9.30 
MySQL - 5.6.25-log : Database - librarymgt
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`librarymgt` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `librarymgt`;

/*Table structure for table `li_book` */

DROP TABLE IF EXISTS `li_book`;

CREATE TABLE `li_book` (
  `ID` bigint(20) NOT NULL,
  `bookCode` bigint(20) DEFAULT NULL,
  `bookName` varchar(225) DEFAULT NULL,
  `writerName` varchar(225) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `li_book` */

insert  into `li_book`(`ID`,`bookCode`,`bookName`,`writerName`,`createdBy`,`modifiedBy`,`createdDatetime`,`modifiedDatetime`) values (1,191001,'COmic','Hariom Mukati','Hariom@gmail.com','Hariom@gmail.com','2019-01-08 11:51:35','2019-01-08 11:51:08'),(2,191002,'Amir Chaney','Aspernatur quia autem a dolorum irure laboriosam sed aliquam voluptatum aspernatur consectetur velit nisi ea qui','Hariom@gmail.com','Hariom@gmail.com','2019-01-08 18:39:23','2019-01-08 18:39:23'),(3,191003,'Levi Shields','Expedita qui modi soluta proident aut eu eos aut rerum quia','Hariom@gmail.com','Hariom@gmail.com','2019-01-08 18:39:40','2019-01-08 18:39:40'),(4,191004,'Kamal Love','Mollitia tempor ullamco irure perspiciatis obcaecati','Hariom@gmail.com','Hariom@gmail.com','2019-01-08 18:39:44','2019-01-08 18:39:44');

/*Table structure for table `li_issubook` */

DROP TABLE IF EXISTS `li_issubook`;

CREATE TABLE `li_issubook` (
  `ID` bigint(20) NOT NULL,
  `studentLibCode` bigint(20) DEFAULT NULL,
  `studentName` varchar(225) DEFAULT NULL,
  `bookName` varchar(225) DEFAULT NULL,
  `writerName` varchar(225) DEFAULT NULL,
  `issuDate` date DEFAULT NULL,
  `returnDate` date DEFAULT NULL,
  `bookCode` bigint(20) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `modifiedDatetime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `li_issubook` */

insert  into `li_issubook`(`ID`,`studentLibCode`,`studentName`,`bookName`,`writerName`,`issuDate`,`returnDate`,`bookCode`,`createdBy`,`modifiedBy`,`createdDatetime`,`modifiedDatetime`) values (2,1902,'Kalia Rose','Amir Chaney','Aspernatur quia autem a dolorum irure laboriosam sed aliquam voluptatum aspernatur consectetur velit nisi ea qui','2019-10-01','2020-03-01',191002,'Hariom@gmail.com','Hariom@gmail.com','2019-01-10 16:02:35','2019-01-10 16:02:35');

/*Table structure for table `li_user` */

DROP TABLE IF EXISTS `li_user`;

CREATE TABLE `li_user` (
  `ID` bigint(20) NOT NULL,
  `firstName` varchar(225) DEFAULT NULL,
  `lastName` varchar(225) DEFAULT NULL,
  `login` varchar(225) DEFAULT NULL,
  `password` varchar(225) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `mobileNo` varchar(225) DEFAULT NULL,
  `libraryCode` bigint(20) DEFAULT NULL,
  `emailId` varchar(225) DEFAULT NULL,
  `Gender` varchar(225) DEFAULT NULL,
  `RoleId` bigint(20) DEFAULT NULL,
  `createdBy` varchar(225) DEFAULT NULL,
  `modifiedBy` varchar(225) DEFAULT NULL,
  `createdDatetime` varchar(225) DEFAULT NULL,
  `modifiedDatetime` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `li_user` */

insert  into `li_user`(`ID`,`firstName`,`lastName`,`login`,`password`,`dob`,`mobileNo`,`libraryCode`,`emailId`,`Gender`,`RoleId`,`createdBy`,`modifiedBy`,`createdDatetime`,`modifiedDatetime`) values (1,'Laurel','Harper','Hariom@gmail.com','Hari@123','1998-04-06','9165415598',1901,'kojer@mailinator.net','Male',1,'root','root','2019-01-07 23:47:08','2019-01-07 23:47:08'),(2,'Kalia','Rose','Shubham@gmail.com','Shubh@123','1995-12-06','9926884703',1902,'gico@mailinator.net','Male',2,'root','root','2019-01-07 23:51:26','2019-01-07 23:51:26'),(3,'Gail','Richmond','asd@gmail.com','Pa$$w0rd!','1997-10-16','8782589632',1903,'bajevetesu@mailinator.com','Male',2,'root','root','2019-01-11 11:31:22','2019-01-11 11:31:55');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
