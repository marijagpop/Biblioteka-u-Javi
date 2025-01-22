/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 10.4.18-MariaDB : Database - database
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`biblioteka` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `biblioteka`;



DROP TABLE IF EXISTS `Administrator`;

CREATE TABLE `Administrator` (
  `AdministratorID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(30) NOT NULL,
  `Prezime` VARCHAR(30) NOT NULL,
  `Username` VARCHAR(30) NOT NULL,
  `Password` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`AdministratorID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `Administrator` VALUES 
(1,'Marija','Popovic','marija','marija');


DROP TABLE IF EXISTS `Clan`;

CREATE TABLE `Clan` (
  `ClanID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Ime` VARCHAR(20) NOT NULL,
  `Prezime` VARCHAR(30) NOT NULL,
  `Email` VARCHAR(50) NOT NULL,
  `Nivo` VARCHAR(30),
  PRIMARY KEY (`ClanID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


INSERT  INTO `Clan` VALUES 
(1,'Milica', 'Petrovic', 'milica@gmail.com', 'PREMIUM'),
(2,'Tamara', 'Jakovljevic', 'tamara@gmail.com', 'SILVER'),
(3,'Jelena', 'Panic', 'jelena@gmail.com', 'FREE');


DROP TABLE IF EXISTS `Zanr`;


CREATE TABLE `Zanr` (
  `ZanrID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`ZanrID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


INSERT  INTO `Zanr` VALUES 
(1,'Klasika'),
(2,'Duhovna knjizevnost'),
(3,'Psihologija');


DROP TABLE IF EXISTS `Knjiga`;

CREATE TABLE `Knjiga` (
  `KnjigaID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `Naziv` VARCHAR(70) NOT NULL,
  `Autor` VARCHAR(70) NOT NULL,
  `Opis` VARCHAR(200) NOT NULL,
  `CenaPoDanu` DECIMAL(10,2) NOT NULL,
  `ZanrID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`KnjigaID`),
  CONSTRAINT `fk_zanr_id` FOREIGN KEY (`ZanrID`) REFERENCES `Zanr` (`ZanrID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;


INSERT  INTO `Knjiga` VALUES 
(1,'Braca Karamazovi', 'Fjodor Mihailovic Dostojevski', 'Najbolja knjiga na svetu!', 50, 1),
(2,'Vreme smrti', 'Dobrica Cosic', 'Istorijski klasik.', 40, 1),
(3,'Reci o svecoveku', 'Nikolaj Velimirovic', 'Obavezno procitati!', 40, 2),
(4,'Kakve su ti misli, takav ti je zivot', 'Starac Tadej', 'Mudra knjiga.', 50, 2),
(5,'Umece ljubavi', 'Erih From', 'Solidna.', 30, 3),
(6,'O ljubavi', 'Vladeta Jerotic', 'Najbolji savremeni filozof.', 50, 3);


DROP TABLE IF EXISTS `Rezervacija`;

CREATE TABLE `Rezervacija` (
  `RezervacijaID` BIGINT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `DatumOd` DATE NOT NULL,
  `DatumDo` DATE NOT NULL,
  `Cena` DECIMAL(10,2) NOT NULL,
  `Popust` DECIMAL(10,2) NOT NULL,
  `CenaSaPopustom` DECIMAL(10,2) NOT NULL,
  `ClanID` BIGINT(10) UNSIGNED NOT NULL,
  `AdministratorID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`RezervacijaID`),
  CONSTRAINT `fk_clan_id` FOREIGN KEY (`ClanID`) REFERENCES `Clan` (`ClanID`),
  CONSTRAINT `fk_administrator_id` FOREIGN KEY (`AdministratorID`) REFERENCES `Administrator` (`AdministratorID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


INSERT  INTO `Rezervacija` VALUES 
(1,'2024-01-20','2024-01-30', 900, 20, 720, 1, 1);


DROP TABLE IF EXISTS `StavkaRezervacije`;

CREATE TABLE `StavkaRezervacije` (
  `RezervacijaID` BIGINT(10) UNSIGNED NOT NULL,
  `Rb` INT(7) NOT NULL,
  `Cena` DECIMAL(10,2) NOT NULL,
  `KnjigaID` BIGINT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`RezervacijaID`,`Rb`),
  CONSTRAINT `fk_rez_id` FOREIGN KEY (`RezervacijaID`) REFERENCES `Rezervacija` (`RezervacijaID`) ON DELETE CASCADE,
  CONSTRAINT `fk_knjiga_id` FOREIGN KEY (`KnjigaID`) REFERENCES `Knjiga` (`KnjigaID`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



INSERT  INTO `StavkaRezervacije` VALUES 
(1,1,500,1),
(1,2,400,2);



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
