/*
SQLyog Ultimate v12.4.3 (64 bit)
MySQL - 5.5.27 : Database - lazada
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`lazada` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `lazada`;

/*Table structure for table `bank` */

DROP TABLE IF EXISTS `bank`;

CREATE TABLE `bank` (
  `Kode_bank` varchar(3) NOT NULL COMMENT 'kode bank',
  `Nama_bank` varchar(15) NOT NULL COMMENT 'nama bank',
  PRIMARY KEY (`Kode_bank`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `bank` */

insert  into `bank`(`Kode_bank`,`Nama_bank`) values 
('002','BRI'),
('008','MANDIRI'),
('009','BNI'),
('011','BANK DANAMON'),
('014','BCA'),
('022','CIMB NIAGA'),
('031','CITY BANK'),
('095','BANK CENTURY'),
('421','BANK MEGA'),
('491','BANK MITRANIAGA');

/*Table structure for table `kategori` */

DROP TABLE IF EXISTS `kategori`;

CREATE TABLE `kategori` (
  `Id_cate` varchar(10) NOT NULL COMMENT 'id kategori',
  `Nama_cate` varchar(30) NOT NULL COMMENT 'nama kategori',
  PRIMARY KEY (`Id_cate`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `kategori` */

insert  into `kategori`(`Id_cate`,`Nama_cate`) values 
('01','Jam Tangan, Kaca Mata, dan Per'),
('02','Komputer dan Laptop'),
('03','Media, Musik, dan Buku'),
('04','Alat Tulis dan Kerajinan');

/*Table structure for table `pelanggan` */

DROP TABLE IF EXISTS `pelanggan`;

CREATE TABLE `pelanggan` (
  `Id_pel` varchar(10) NOT NULL COMMENT 'id pelanggan',
  `Nama_pel` varchar(30) NOT NULL COMMENT 'nama pelanggan',
  `No_telp` varchar(13) DEFAULT NULL COMMENT 'nomor telp pelanggan',
  `Alamat` varchar(30) NOT NULL,
  `Email` varchar(30) NOT NULL,
  `Pass` varchar(30) NOT NULL,
  PRIMARY KEY (`Id_pel`),
  UNIQUE KEY `Email` (`Email`),
  UNIQUE KEY `Pass` (`Pass`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `pelanggan` */

insert  into `pelanggan`(`Id_pel`,`Nama_pel`,`No_telp`,`Alamat`,`Email`,`Pass`) values 
('1','syt','085123321','dago','a@email.com','007'),
('2','ats','081612267','tubagus','b@email.com','001');

/*Table structure for table `pembayaran` */

DROP TABLE IF EXISTS `pembayaran`;

CREATE TABLE `pembayaran` (
  `No_pay` varchar(10) NOT NULL COMMENT 'nomor pembayaran',
  `Tgl_pay` datetime NOT NULL COMMENT 'tanggal pembayaran',
  `Total_pay` int(10) NOT NULL COMMENT 'total pembayaran',
  `Id_pesan` varchar(10) NOT NULL,
  `No_rek` varchar(10) NOT NULL,
  PRIMARY KEY (`No_pay`),
  KEY `Id_pesan` (`Id_pesan`),
  KEY `No_rek` (`No_rek`),
  CONSTRAINT `pembayaran_ibfk_1` FOREIGN KEY (`Id_pesan`) REFERENCES `pesanan` (`Id_pesan`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pembayaran_ibfk_2` FOREIGN KEY (`No_rek`) REFERENCES `rekening` (`No_rek`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `pembayaran` */

/*Table structure for table `pengiriman` */

DROP TABLE IF EXISTS `pengiriman`;

CREATE TABLE `pengiriman` (
  `Id_pengirim` varchar(10) NOT NULL COMMENT 'id pengiriman',
  `Nama_kurir` varchar(10) NOT NULL,
  `No_kurir` varchar(10) NOT NULL COMMENT 'nomor kurir',
  `Tgl_kirim` datetime NOT NULL COMMENT 'tanggal pengiriman',
  `No_pay` varchar(10) NOT NULL,
  PRIMARY KEY (`Id_pengirim`),
  KEY `No_pay` (`No_pay`),
  CONSTRAINT `pengiriman_ibfk_1` FOREIGN KEY (`No_pay`) REFERENCES `pembayaran` (`No_pay`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `pengiriman` */

/*Table structure for table `pesanan` */

DROP TABLE IF EXISTS `pesanan`;

CREATE TABLE `pesanan` (
  `Id_pesan` varchar(10) NOT NULL,
  `Id_pel` varchar(10) NOT NULL,
  `Jml_pesan` int(100) NOT NULL,
  `Tgl_pesan` datetime DEFAULT NULL,
  PRIMARY KEY (`Id_pesan`),
  KEY `Id_pel` (`Id_pel`),
  CONSTRAINT `pesanan_ibfk_1` FOREIGN KEY (`Id_pel`) REFERENCES `pelanggan` (`Id_pel`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `pesanan` */

insert  into `pesanan`(`Id_pesan`,`Id_pel`,`Jml_pesan`,`Tgl_pesan`) values 
('1','1',4,'2018-01-18 20:46:09');

/*Table structure for table `pesanan_produk_retail` */

DROP TABLE IF EXISTS `pesanan_produk_retail`;

CREATE TABLE `pesanan_produk_retail` (
  `Id_produk` varchar(10) NOT NULL,
  `Id_pesan` varchar(10) DEFAULT NULL,
  `Id_retail` varchar(10) NOT NULL,
  KEY `Id_produk` (`Id_produk`),
  KEY `Id_pesan` (`Id_pesan`),
  KEY `pesanan_produk_retail_ibfk_2` (`Id_retail`),
  CONSTRAINT `pesanan_produk_retail_ibfk_1` FOREIGN KEY (`Id_produk`) REFERENCES `produk_retail` (`Id_produk`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pesanan_produk_retail_ibfk_2` FOREIGN KEY (`Id_retail`) REFERENCES `produk_retail` (`Id_retail`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pesanan_produk_retail_ibfk_3` FOREIGN KEY (`Id_pesan`) REFERENCES `pesanan` (`Id_pesan`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `pesanan_produk_retail` */

insert  into `pesanan_produk_retail`(`Id_produk`,`Id_pesan`,`Id_retail`) values 
('1011','1','10001'),
('1022',NULL,'10002'),
('1090',NULL,'10001'),
('1145',NULL,'10002'),
('1103',NULL,'10001'),
('1104',NULL,'10004'),
('1105',NULL,'10002'),
('1106',NULL,'10003'),
('1107',NULL,'10002'),
('1108',NULL,'10003'),
('1109',NULL,'10003'),
('1145',NULL,'10002'),
('1167',NULL,'10002'),
('1186',NULL,'10001'),
('1198',NULL,'10004'),
('1199',NULL,'10002'),
('1229',NULL,'10002'),
('1233',NULL,'10002'),
('1277',NULL,'10002'),
('1340',NULL,'10002'),
('1445',NULL,'10002'),
('1455',NULL,'10003'),
('1477',NULL,'10004'),
('7049',NULL,'10005');

/*Table structure for table `produk` */

DROP TABLE IF EXISTS `produk`;

CREATE TABLE `produk` (
  `Id_produk` varchar(10) NOT NULL COMMENT 'id produk',
  `Nama_produk` varchar(20) NOT NULL COMMENT 'nama produk',
  `Harga` int(10) NOT NULL COMMENT 'harga produk',
  `Id_cate` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`Id_produk`),
  KEY `FK_id_cate` (`Id_cate`),
  CONSTRAINT `FK_id_cate` FOREIGN KEY (`Id_cate`) REFERENCES `kategori` (`Id_cate`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `produk` */

insert  into `produk`(`Id_produk`,`Nama_produk`,`Harga`,`Id_cate`) values 
('1011','Lenovo Ideapad 110 8',3599000,'02'),
('1022','Acer Switch one SW1',3475000,'02'),
('1090','Asus X540YA-BX101T',3449000,'02'),
('1100','Numark Party MIx',1800000,'02'),
('1101','Ukulele Mini Guitar ',149800,'03'),
('1102','JBS Capo Guitar ',23500,'03'),
('1103','Gitar Yamaha - FG 50',200000,'03'),
('1104','Hartke HD15 Bass Amp',1171902,'03'),
('1105','Erlangga Fokus UN 20',84000,'04'),
('1106','Novel Kau Yang Terba',66000,'04'),
('1107','Keyboard Piano techn',740000,'03'),
('1108','Buku Dongeng Anak',62000,'04'),
('1109','Cahaya Abadi Gendng ',450000,'04'),
('1145','Sneakers EraCore Nav',92500,'01'),
('1167','Modem Bolt Aquila Ma',299800,'02'),
('1186','Epson Printer L360',1995000,'02'),
('1198','Xiaomi Air',13200000,'02'),
('1199','Alena Flat Shoes Hit',35000,'01'),
('1229','Celana Jeans Wanita ',870000,'01'),
('1233','Dell Inspiron  15 75',2600000,'02'),
('1277','Huion H420 USB Drawi',353000,'02'),
('1340','Macbook Air13 Corei5',3050000,'02'),
('1445','Pasmina Instan SALA',30000,'02'),
('1455','Flashdisk HP 32GB Si',50000,'02'),
('1477','Sweater Hoodie Arche',41000,'01'),
('1882','Jaket Jeans Denim Wa',100000,'01'),
('1990','Baju Tidur Anak',17000,'01'),
('2311','SmartWatch A1 Camera',94000,'02'),
('2312','Polarized Sunglasses',68000,'01'),
('2313','CHIC Kecil Gold Love',27000,'01'),
('2314','Vee Gelang Terapi Bl',68000,'01'),
('2315','Round Glasses Dress ',27000,'01'),
('2316','Jam Tangan Kuarsa',39000,'01'),
('2317','Cincin Berlian Gaya ',93100,'01'),
('2318','Cincin Batu Akik Kec',59000,'01'),
('2319','YBC Girls Watch Tran',36000,'01'),
('2320','Kacamata Perlindunga',26000,'01'),
('3356','Mukena Bali Pastel',89000,'01'),
('5672','Cooling pad cooler',76630,'02'),
('600','Jaket Parka Pink Dus',29716,'01'),
('6291','Pulpen Gel Kepala',14800,'04'),
('6292','Stick Note Memo Book',36000,'04'),
('6293','5Pcs Refill Lem Temb',13900,'04'),
('6294','Paper Cutter A4R',193500,'04'),
('6295','Stabilo BOSS Paket M',54000,'04'),
('6296','Yingwei 10 buah kuas',51000,'04'),
('6297','Xiaomi Mi Powerbank ',213000,'02'),
('6667','Trafix Kaos Pria Pre',15900,'01'),
('7049','Lenovo A7 Plus',1500000,'03');

/*Table structure for table `produk_retail` */

DROP TABLE IF EXISTS `produk_retail`;

CREATE TABLE `produk_retail` (
  `Id_produk` varchar(10) NOT NULL,
  `Id_retail` varchar(10) NOT NULL,
  KEY `Id_produk` (`Id_produk`),
  KEY `Id_retail` (`Id_retail`),
  CONSTRAINT `produk_retail_ibfk_1` FOREIGN KEY (`Id_produk`) REFERENCES `produk` (`Id_produk`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `produk_retail_ibfk_2` FOREIGN KEY (`Id_retail`) REFERENCES `retail` (`Id_retail`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `produk_retail` */

insert  into `produk_retail`(`Id_produk`,`Id_retail`) values 
('1011','10001'),
('1022','10002'),
('1090','10001'),
('1100','10004'),
('1101','10001'),
('1102','10003'),
('1103','10001'),
('1104','10004'),
('1105','10002'),
('1106','10003'),
('1107','10002'),
('1108','10003'),
('1109','10003'),
('1145','10002'),
('1167','10002'),
('1186','10001'),
('1198','10004'),
('1199','10002'),
('1229','10002'),
('1233','10002'),
('1277','10002'),
('1340','10002'),
('1445','10002'),
('1455','10003'),
('1477','10004'),
('7049','10005');

/*Table structure for table `rekening` */

DROP TABLE IF EXISTS `rekening`;

CREATE TABLE `rekening` (
  `No_rek` varchar(10) NOT NULL COMMENT 'nomor rekening pihak lazada',
  `Atas_nama` varchar(30) NOT NULL COMMENT 'atas nama pada pihak lazada',
  `Kode_bank` varchar(10) NOT NULL,
  PRIMARY KEY (`No_rek`),
  KEY `Kode_bank` (`Kode_bank`),
  CONSTRAINT `rekening_ibfk_1` FOREIGN KEY (`Kode_bank`) REFERENCES `bank` (`Kode_bank`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `rekening` */

insert  into `rekening`(`No_rek`,`Atas_nama`,`Kode_bank`) values 
('102293817','Lazada BRI','002'),
('109019230','Lazada Mitraniaga','491'),
('123108298','Lazada Cimb Niaga','022'),
('233718821','Lazada Mandiri','008'),
('324324912','Lazada BNI','009'),
('445321239','Lazada Danamon','011'),
('532123234','Lazada BCA','014'),
('789123234','Lazada City Bank','031'),
('821379420','Lazada Bank Century','095'),
('992139821','Lazada Bank Mega','421');

/*Table structure for table `retail` */

DROP TABLE IF EXISTS `retail`;

CREATE TABLE `retail` (
  `Id_retail` varchar(10) NOT NULL,
  `Nama_retail` varchar(30) NOT NULL,
  `Email` varchar(26) NOT NULL,
  `Pass` varchar(16) NOT NULL,
  `Alamat` varchar(30) NOT NULL,
  `Kontak_person` varchar(13) NOT NULL,
  PRIMARY KEY (`Id_retail`),
  UNIQUE KEY `Email` (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `retail` */

insert  into `retail`(`Id_retail`,`Nama_retail`,`Email`,`Pass`,`Alamat`,`Kontak_person`) values 
('10001','Happy collection','happy.collection@gmail.com','selaluhappy211','Jl Bengawan no 30, Bandung','085129920312'),
('10002','Mega Comp','mega_computer@gmail.com','megakomputer12','Jl Pelajar no 14, Surabaya','087785521011'),
('10003','Gloomy Store','gloomystore13@gmail.com','gloomysemarang00','Jl Kebon Jeruk no 78, Semarang','089856643211'),
('10004','Fun and Fres co.','funandfresh.co@gmail.com','terlengkapsekali','Jl Karang Setra no 5, Serang','082152239077'),
('10005','antasuy','ats@gail.com','007','jl. tubagus ismail','08123631723');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
