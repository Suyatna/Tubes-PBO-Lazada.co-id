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
('','Jam Tangan, Kaca Mata, dan Per'),
('01','Komputer dan Laptop'),
('02','Media, Musik, dan Buku'),
('04','Alat Tulis dan Kerajinan');

/*Table structure for table `pelanggan` */

DROP TABLE IF EXISTS `pelanggan`;

CREATE TABLE `pelanggan` (
  `Id_pel` varchar(10) NOT NULL COMMENT 'id pelanggan',
  `Nama_pel` varchar(30) NOT NULL COMMENT 'nama pelanggan',
  `Email` varchar(26) NOT NULL COMMENT 'email pelanggan',
  `No_telp` varchar(13) DEFAULT NULL COMMENT 'nomor telp pelanggan',
  `Alamat` varchar(30) NOT NULL,
  PRIMARY KEY (`Id_pel`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `pelanggan` */

insert  into `pelanggan`(`Id_pel`,`Nama_pel`,`Email`,`No_telp`,`Alamat`) values 
('1110','A','asd','09098','8klsadjklasjdljl');

/*Table structure for table `pembayaran` */

DROP TABLE IF EXISTS `pembayaran`;

CREATE TABLE `pembayaran` (
  `No_pay` varchar(10) NOT NULL COMMENT 'nomor pembayaran',
  `Tgl_pay` date NOT NULL COMMENT 'tanggal pembayaran',
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
  `id_pengirim` varchar(10) NOT NULL COMMENT 'id pengiriman',
  `No_kurir` varchar(10) NOT NULL COMMENT 'nomor kurir',
  `Tgl_kirim` date NOT NULL COMMENT 'tanggal pengiriman',
  `No_pay` varchar(10) NOT NULL,
  PRIMARY KEY (`id_pengirim`),
  KEY `No_pay` (`No_pay`),
  CONSTRAINT `pengiriman_ibfk_1` FOREIGN KEY (`No_pay`) REFERENCES `pembayaran` (`No_pay`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `pengiriman` */

/*Table structure for table `pesanan` */

DROP TABLE IF EXISTS `pesanan`;

CREATE TABLE `pesanan` (
  `Id_pesan` varchar(10) NOT NULL,
  `Id_pel` varchar(10) NOT NULL,
  `Tgl_pesan` date NOT NULL,
  `Jml_pesan` int(100) NOT NULL,
  PRIMARY KEY (`Id_pesan`),
  KEY `Id_pel` (`Id_pel`),
  CONSTRAINT `pesanan_ibfk_1` FOREIGN KEY (`Id_pel`) REFERENCES `pelanggan` (`Id_pel`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `pesanan` */

/*Table structure for table `pesanan_produk_retail` */

DROP TABLE IF EXISTS `pesanan_produk_retail`;

CREATE TABLE `pesanan_produk_retail` (
  `Id_produk` varchar(10) NOT NULL,
  `Id_retail` varchar(10) NOT NULL,
  `Id_pesanan` varchar(10) NOT NULL,
  KEY `Id_produk` (`Id_produk`),
  KEY `Id_retail` (`Id_retail`),
  KEY `Id_pesanan` (`Id_pesanan`),
  CONSTRAINT `pesanan_produk_retail_ibfk_1` FOREIGN KEY (`Id_produk`) REFERENCES `produk` (`Id_produk`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pesanan_produk_retail_ibfk_2` FOREIGN KEY (`Id_retail`) REFERENCES `retail` (`Id_retail`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pesanan_produk_retail_ibfk_3` FOREIGN KEY (`Id_pesanan`) REFERENCES `pesanan` (`Id_pesan`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `pesanan_produk_retail` */

/*Table structure for table `produk` */

DROP TABLE IF EXISTS `produk`;

CREATE TABLE `produk` (
  `Id_produk` varchar(10) NOT NULL COMMENT 'id produk',
  `Nama_produk` varchar(20) NOT NULL COMMENT 'nama produk',
  `Harga` int(10) NOT NULL COMMENT 'harga produk',
  `Id_cate` varchar(10) NOT NULL,
  PRIMARY KEY (`Id_produk`),
  KEY `Id_cate` (`Id_cate`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `produk` */

insert  into `produk`(`Id_produk`,`Nama_produk`,`Harga`,`Id_cate`) values 
('0922','Jaket Parka Pink Dus',29716,''),
('1011','Lenovo Ideapad 110 8',3599000,'1011'),
('1022','Acer Switch one SW1',3475000,''),
('1090','Asus X540YA-BX101T',3449000,''),
('1100','Numark Party MIx',1800000,''),
('1101','Ukulele Mini Guitar ',149800,''),
('1102','JBS Capo Guitar ',23500,''),
('1103','Gitar Yamaha - FG 50',200000,''),
('1104','Hartke HD15 Bass Amp',1171902,''),
('1105','Erlangga Fokus UN 20',84000,''),
('1106','Novel Kau Yang Terba',66000,''),
('1107','Keyboard Piano techn',740000,''),
('1108','Buku Dongeng Anak',62000,''),
('1109','Cahaya Abadi Gendng ',450000,''),
('1145','Sneakers EraCore Nav',92500,''),
('1167','Modem Bolt Aquila Ma',299800,''),
('1186','Epson Printer L360',1995000,''),
('1198','Xiaomi Air',13200000,''),
('1199','Alena Flat Shoes Hit',35000,''),
('1229','Celana Jeans Wanita ',870000,''),
('1233','Dell Inspiron  15 75',2600000,''),
('1277','Huion H420 USB Drawi',353000,''),
('1340','Macbook Air13 Corei5',3050000,''),
('1445','Pasmina Instan SALA',30000,''),
('1455','Flashdisk HP 32GB Si',50000,''),
('1477','Sweater Hoodie Arche',41000,''),
('1882','Jaket Jeans Denim Wa',100000,''),
('1990','Baju Tidur Anak',17000,''),
('2311','SmartWatch A1 Camera',94000,''),
('2312','Polarized Sunglasses',68000,''),
('2313','CHIC Kecil Gold Love',27000,''),
('2314','Vee Gelang Terapi Bl',68000,''),
('2315','Round Glasses Dress ',27000,''),
('2316','Jam Tangan Kuarsa',39000,''),
('2317','Cincin Berlian Gaya ',93100,''),
('2318','Cincin Batu Akik Kec',59000,''),
('2319','YBC Girls Watch Tran',36000,''),
('2320','Kacamata Perlindunga',26000,''),
('3356','Mukena Bali Pastel',89000,''),
('5672','Cooling pad cooler',76630,''),
('6291','Pulpen Gel Kepala',14800,''),
('6292','Stick Note Memo Book',36000,''),
('6293','5Pcs Refill Lem Temb',13900,''),
('6294','Paper Cutter A4R',193500,''),
('6295','Stabilo BOSS Paket M',54000,''),
('6296','Yingwei 10 buah kuas',51000,''),
('6297','Xiaomi Mi Powerbank ',213000,''),
('6667','Trafix Kaos Pria Pre',15900,'');

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

/*Table structure for table `retail` */

DROP TABLE IF EXISTS `retail`;

CREATE TABLE `retail` (
  `Id_retail` varchar(10) NOT NULL COMMENT 'id retail',
  `Nama_retail` varchar(30) NOT NULL COMMENT 'nama retail',
  `Kontak_pers` varchar(13) NOT NULL COMMENT 'kontak person retail',
  `Email` varchar(26) NOT NULL COMMENT 'email retail',
  PRIMARY KEY (`Id_retail`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `retail` */

insert  into `retail`(`Id_retail`,`Nama_retail`,`Kontak_pers`,`Email`) values 
('','','','');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
