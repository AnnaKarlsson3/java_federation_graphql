-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.6.37 - MySQL Community Server (GPL)
-- Server OS:                    Win32
-- HeidiSQL Version:             10.1.0.5464
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping structure for table federated_project.moon
CREATE TABLE IF NOT EXISTS `moon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `size` int(11) NOT NULL,
  `planet` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_moon_planet` (`planet`),
  CONSTRAINT `FK_moon_planet` FOREIGN KEY (`planet`) REFERENCES `planet` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table federated_project.moon: ~1 rows (approximately)
/*!40000 ALTER TABLE `moon` DISABLE KEYS */;
INSERT INTO `moon` (`id`, `name`, `size`, `planet`) VALUES
	(1, 'colas', 32, 1),
	(2, 'Richo', 54, 2);
/*!40000 ALTER TABLE `moon` ENABLE KEYS */;

-- Dumping structure for table federated_project.planet
CREATE TABLE IF NOT EXISTS `planet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `size` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

-- Dumping data for table federated_project.planet: ~1 rows (approximately)
/*!40000 ALTER TABLE `planet` DISABLE KEYS */;
INSERT INTO `planet` (`id`, `name`, `size`) VALUES
	(1, 'Jupiter', 234),
	(2, 'Mars', 54);
/*!40000 ALTER TABLE `planet` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
