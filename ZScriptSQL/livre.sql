-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  lun. 06 avr. 2020 à 14:56
-- Version du serveur :  10.4.8-MariaDB
-- Version de PHP :  7.3.11

SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `mabiblimsbibliotheque`
--

-- --------------------------------------------------------

--
-- Structure de la table `livre`
--

CREATE TABLE `livre` (
  `id_livre` bigint(20) NOT NULL,
  `auteur` varchar(255) DEFAULT NULL,
  `decription` varchar(255) DEFAULT NULL,
  `editeur` varchar(255) DEFAULT NULL,
  `isbn` varchar(255) DEFAULT NULL,
  `nombre_pages` int(11) NOT NULL,
  `quantite_dispo` int(11) NOT NULL,
  `titre` varchar(255) DEFAULT NULL,
  `url_image` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `livre`
--

INSERT INTO `livre` (`id_livre`, `auteur`, `decription`, `editeur`, `isbn`, `nombre_pages`, `quantite_dispo`, `titre`, `url_image`) VALUES
(49, 'Marc Elsberg', 'Par une froide soirée d’hiver, le réseau électrique européen commence à lâcher. De nombreux pays s’enfoncent dans l’obscurité ...', 'Piranha', 'string', 350, 10, 'Black out', 'https://www.livredepoche.com/sites/default/files/styles/manual_crop_269_435/public/images/livres/couv/9782253098690-001-T.jpeg?itok=oWgyCHjg'),
(51, 'Lina Bengtsdotter', 'En quittant Gullspång à l’âge de quatorze ans, Charlie Lager s’était juré de ne plus jamais y retourner. Mais cette petite ville perdue en plein cœur de la Suèd', 'Marabout', 'string', 416, 5, 'Annabelle', 'https://www.livredepoche.com/sites/default/files/styles/manual_crop_269_435/public/images/livres/couv/9782253181200-001-T.jpeg?itok=JXCEBSEb'),
(52, 'Vincent Hauuy', 'Isabel Gros est une miraculée. Seule survivante d’une fusillade, elle a passé deux semaines dans le coma. Contrainte d’abandonner ...', 'Hugo et Compagnie', 'string', 408, 6, 'Dans la toile', 'https://www.livredepoche.com/sites/default/files/styles/manual_crop_269_435/public/images/livres/couv/9782253241560-001-T.jpeg?itok=WnYFqqsi'),
(53, 'Antoine Albertini', 'Qui a assassiné une randonneuse sur le sentier des crêtes, près de Bastia, en Corse ? Pourquoi un homme a-t-il tué sa femme et sa petite fille avant de tenter de se suicider ? L’enquête est confiée à un capitaine de police un peu ...', 'JC Lattès', 'string', 320, 20, 'Malamorte', 'https://www.livredepoche.com/sites/default/files/styles/manual_crop_269_435/public/images/livres/couv/9782253181460-001-T.jpeg?itok=rYC7IFuC'),
(55, 'Alain Lallemand', '« En redescendant en ville, ils se dévoilèrent un peu, se découvrirent un âge commun – vingt-six ans – et une rage partagée pour le siècle, l’argent-roi, les indignations faciles sur les réseaux sociaux. Elle l’emmena ...', 'Le livre de poche', 'string', 288, 3, 'Et dans la jungle, Dieu dansait', 'https://www.livredepoche.com/sites/default/files/styles/manual_crop_269_435/public/images/livres/couv/9782253262008-001-T.jpeg?itok=2tuq5T6G');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `livre`
--
ALTER TABLE `livre`
  ADD PRIMARY KEY (`id_livre`);
SET FOREIGN_KEY_CHECKS=1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
