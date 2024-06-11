/*
SQLyog Ultimate v10.00 Beta1
MySQL - 8.0.28 : Database - library
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`library` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `library`;

/*Table structure for table `books_info` */

DROP TABLE IF EXISTS `books_info`;

CREATE TABLE `books_info` (
  `book_id` varchar(7) NOT NULL COMMENT '图书编号',
  `book_name` varchar(20) NOT NULL COMMENT '书名',
  `type` varchar(20) NOT NULL COMMENT '类型',
  `author` varchar(20) DEFAULT NULL COMMENT '作者',
  `date` datetime DEFAULT NULL COMMENT '入库日期',
  `amount` bigint DEFAULT NULL COMMENT '数量',
  `about` mediumtext COMMENT '书的简介',
  `publisher` varchar(20) DEFAULT NULL COMMENT '出版社',
  `getbor_Times` bigint DEFAULT NULL COMMENT '被借阅次数',
  PRIMARY KEY (`book_id`,`book_name`,`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `books_info` */

insert  into `books_info`(`book_id`,`book_name`,`type`,`author`,`date`,`amount`,`about`,`publisher`,`getbor_Times`) values ('004','jdbc引入','JAVA书','JAVA编程者','2015-10-20 00:00:00',0,NULL,NULL,NULL),('01','第一本书哦','你好呀','kk','2022-09-20 14:13:05',2,'呵呵呵欢迎借阅','人民出版社',1),('aa','阿萨大师大多','HAVA书','哒哒哒哒','2022-09-20 15:35:10',0,NULL,NULL,NULL);

/*Table structure for table `borrowrecord` */

DROP TABLE IF EXISTS `borrowrecord`;

CREATE TABLE `borrowrecord` (
  `borrower` varchar(20) DEFAULT NULL COMMENT '借阅者',
  `borrowed_book` varchar(20) DEFAULT NULL COMMENT '借阅书名',
  `borrow_date` date DEFAULT NULL COMMENT '借阅日期',
  `isReturn` varchar(10) DEFAULT NULL COMMENT '图书是否归还',
  `whole_record` longtext COMMENT '完整借阅记录'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `borrowrecord` */

insert  into `borrowrecord`(`borrower`,`borrowed_book`,`borrow_date`,`isReturn`,`whole_record`) values ('t1(ID:02)','第一本书哦','2022-09-20','待归还','用户t1(ID为：02)于2022-09-20 14:13:05借走图书《第一本书哦》(编号为01)书籍一本');

/*Table structure for table `userinfo` */

DROP TABLE IF EXISTS `userinfo`;

CREATE TABLE `userinfo` (
  `user_Name` varchar(20) NOT NULL COMMENT '用户名',
  `user_type` varchar(20) NOT NULL COMMENT '用户类型',
  `user_id` varchar(20) NOT NULL COMMENT '用户id',
  `user_password` varchar(15) NOT NULL COMMENT '密码',
  `user_about` varchar(30) DEFAULT NULL COMMENT '关于用户',
  `sign_date` datetime DEFAULT NULL COMMENT '注册日期',
  `user_Sta` varchar(10) DEFAULT NULL COMMENT '用户状态',
  `record` longtext COMMENT '借阅记录',
  `owned_bk` longtext COMMENT '持有书籍',
  `borrow_time` bigint DEFAULT NULL COMMENT '借阅次数',
  PRIMARY KEY (`user_Name`,`user_type`,`user_id`,`user_password`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

/*Data for the table `userinfo` */

insert  into `userinfo`(`user_Name`,`user_type`,`user_id`,`user_password`,`user_about`,`sign_date`,`user_Sta`,`record`,`owned_bk`,`borrow_time`) values ('kk','管理员','01','123','第一个用户','2022-09-20 14:12:33','非冻结状态','','',0),('t1','读者','02','12','12121212','2022-09-20 14:15:34','非冻结状态','\n用户t1(ID为：02)于2022-09-20 14:13:05借走图书《第一本书哦》(编号为01)书籍一本','\n第一本书哦',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
