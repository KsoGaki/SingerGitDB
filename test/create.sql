-- --------------------------------------------------------
-- Hôte :                        127.0.0.1
-- Version du serveur:           5.5.61-log - MySQL Community Server (GPL)
-- SE du serveur:                Win64
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Export de la structure de la base pour singers
DROP DATABASE IF EXISTS `singers`;
CREATE DATABASE IF NOT EXISTS `singers` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `singers`;

-- Export de la structure de la table singers. singer
DROP TABLE IF EXISTS `singer`;
CREATE TABLE IF NOT EXISTS `singer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `prenom` varchar(50) NOT NULL,
  `nom` varchar(50) NOT NULL,
  `salaire` int(7) NOT NULL,
  `age` int(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Export de données de la table singers.singer : ~0 rows (environ)
/*!40000 ALTER TABLE `singer` DISABLE KEYS */;
INSERT INTO `singer` (`id`, `prenom`, `nom`, `salaire`, `age`) VALUES
	(1, 'Michael', 'Jackson', 3, 101),
	(2, 'Jean Jacques', 'Goldman', 100000, 68),
	(3, 'Céline ', 'Dion', 68000, 60),
	(4, 'Pétula', 'Clarck', 10000, 43);
/*!40000 ALTER TABLE `singer` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
