-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 03. Dez 2019 um 10:33
-- Server-Version: 10.1.37-MariaDB
-- PHP-Version: 7.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `seatreservation`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `category`
--

CREATE TABLE `category` (
  `CategoryId` int(11) NOT NULL,
  `Name` varchar(45) NOT NULL,
  `Price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `category`
--

INSERT INTO `category` (`CategoryId`, `Name`, `Price`) VALUES
(1, 'A', 120),
(2, 'B', 90),
(3, 'C', 70);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `game`
--

CREATE TABLE `game` (
  `GameId` int(11) NOT NULL,
  `TeamAId` int(11) NOT NULL,
  `TeamBId` int(11) NOT NULL,
  `PlayingDate` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `game`
--

INSERT INTO `game` (`GameId`, `TeamAId`, `TeamBId`, `PlayingDate`) VALUES
(2, 1, 3, '2019-11-13 14:30:00'),
(3, 1, 4, '2019-11-14 15:00:00'),
(4, 2, 3, '2019-11-15 15:30:00'),
(5, 2, 4, '2019-11-16 16:00:00'),
(6, 3, 4, '2019-11-17 16:30:00'),
(7, 1, 2, '2020-04-21 13:00:00');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `guest`
--

CREATE TABLE `guest` (
  `GuestId` int(11) NOT NULL,
  `FirstName` varchar(45) NOT NULL,
  `LastName` varchar(45) NOT NULL,
  `StreetAndNumber` varchar(45) NOT NULL,
  `Zip` varchar(45) NOT NULL,
  `Domicile` varchar(45) NOT NULL,
  `Country` varchar(45) NOT NULL,
  `Phone` varchar(45) DEFAULT NULL,
  `Mobile` varchar(45) DEFAULT NULL,
  `Email` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `guest`
--

INSERT INTO `guest` (`GuestId`, `FirstName`, `LastName`, `StreetAndNumber`, `Zip`, `Domicile`, `Country`, `Phone`, `Mobile`, `Email`) VALUES
(1, 'Hans', 'Murer', 'Pius Rickenmannstrasse 4', '8640', 'Rapperswil', 'Schweiz', '055 132 34 56', '079 576 14 89', 'hans.murer@gmail.com'),
(2, 'Ash', 'Ketchum', 'Holzwiesstrasse 17', '8645', 'Jona', 'Schweiz', '055 568 93 17', '079 476 28 69', 'ash.ketchup@gmail.com'),
(3, 'Kirase', 'Kuroma', 'Alt Ferrachstrasse 30a', '8630', 'Rueti', 'Schweiz', '055 653 75 25', '079 253 11 35', 'yugiohover777@gmail.com');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `reservation`
--

CREATE TABLE `reservation` (
  `ReservationId` int(11) NOT NULL,
  `GuestId` int(11) NOT NULL,
  `SeatId` int(11) NOT NULL,
  `GameId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `reservation`
--

INSERT INTO `reservation` (`ReservationId`, `GuestId`, `SeatId`, `GameId`) VALUES
(1, 3, 46, 2),
(2, 3, 47, 2),
(3, 3, 48, 2),
(4, 2, 60, 6),
(5, 2, 61, 6),
(7, 1, 71, 5),
(8, 1, 73, 5),
(9, 1, 75, 5),
(10, 1, 80, 3),
(11, 2, 41, 2);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `seat`
--

CREATE TABLE `seat` (
  `SeatId` int(11) NOT NULL,
  `SeatNumber` int(11) NOT NULL,
  `CategoryId` int(11) NOT NULL,
  `SeatRow` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `seat`
--

INSERT INTO `seat` (`SeatId`, `SeatNumber`, `CategoryId`, `SeatRow`) VALUES
(40, 1, 1, 1),
(41, 2, 1, 1),
(42, 3, 1, 1),
(43, 4, 1, 1),
(44, 5, 1, 1),
(45, 6, 1, 1),
(46, 7, 1, 1),
(47, 8, 1, 1),
(48, 9, 1, 1),
(49, 10, 1, 1),
(50, 11, 1, 1),
(51, 12, 1, 1),
(52, 13, 1, 1),
(53, 14, 1, 1),
(54, 15, 1, 1),
(55, 16, 1, 1),
(56, 17, 1, 1),
(57, 18, 1, 1),
(58, 19, 1, 1),
(59, 20, 1, 1),
(60, 21, 2, 2),
(61, 22, 2, 2),
(62, 23, 2, 2),
(63, 24, 2, 2),
(64, 25, 2, 2),
(65, 26, 2, 2),
(66, 27, 2, 2),
(67, 28, 2, 2),
(68, 29, 2, 2),
(69, 30, 2, 2),
(70, 31, 2, 2),
(71, 32, 2, 2),
(72, 33, 2, 2),
(73, 34, 2, 2),
(74, 35, 2, 2),
(75, 36, 2, 2),
(76, 37, 2, 2),
(77, 38, 2, 2),
(78, 39, 2, 2),
(79, 40, 2, 2),
(80, 41, 3, 3),
(81, 42, 3, 3),
(82, 43, 3, 3),
(83, 44, 3, 3),
(84, 45, 3, 3),
(85, 46, 3, 3),
(86, 47, 3, 3),
(87, 48, 3, 3),
(88, 49, 3, 3),
(89, 50, 3, 3);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `team`
--

CREATE TABLE `team` (
  `TeamId` int(11) NOT NULL,
  `Name` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Daten für Tabelle `team`
--

INSERT INTO `team` (`TeamId`, `Name`) VALUES
(1, 'FC ST.Gallen'),
(2, 'FC Basel'),
(3, 'FC Zürich'),
(4, 'FC Schwyz');

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`CategoryId`);

--
-- Indizes für die Tabelle `game`
--
ALTER TABLE `game`
  ADD PRIMARY KEY (`GameId`),
  ADD KEY `TeamAId` (`TeamAId`),
  ADD KEY `TeamBId` (`TeamBId`);

--
-- Indizes für die Tabelle `guest`
--
ALTER TABLE `guest`
  ADD PRIMARY KEY (`GuestId`);

--
-- Indizes für die Tabelle `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`ReservationId`),
  ADD KEY `GuestId` (`GuestId`),
  ADD KEY `SeatId` (`SeatId`),
  ADD KEY `GameId` (`GameId`);

--
-- Indizes für die Tabelle `seat`
--
ALTER TABLE `seat`
  ADD PRIMARY KEY (`SeatId`),
  ADD KEY `CategoryId` (`CategoryId`);

--
-- Indizes für die Tabelle `team`
--
ALTER TABLE `team`
  ADD PRIMARY KEY (`TeamId`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `category`
--
ALTER TABLE `category`
  MODIFY `CategoryId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT für Tabelle `game`
--
ALTER TABLE `game`
  MODIFY `GameId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT für Tabelle `guest`
--
ALTER TABLE `guest`
  MODIFY `GuestId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT für Tabelle `reservation`
--
ALTER TABLE `reservation`
  MODIFY `ReservationId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT für Tabelle `seat`
--
ALTER TABLE `seat`
  MODIFY `SeatId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=90;

--
-- AUTO_INCREMENT für Tabelle `team`
--
ALTER TABLE `team`
  MODIFY `TeamId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `game`
--
ALTER TABLE `game`
  ADD CONSTRAINT `game_ibfk_1` FOREIGN KEY (`TeamAId`) REFERENCES `team` (`TeamId`),
  ADD CONSTRAINT `game_ibfk_2` FOREIGN KEY (`TeamBId`) REFERENCES `team` (`TeamId`);

--
-- Constraints der Tabelle `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`GuestId`) REFERENCES `guest` (`GuestId`),
  ADD CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`GuestId`) REFERENCES `guest` (`GuestId`),
  ADD CONSTRAINT `reservation_ibfk_3` FOREIGN KEY (`SeatId`) REFERENCES `seat` (`SeatId`),
  ADD CONSTRAINT `reservation_ibfk_4` FOREIGN KEY (`GameId`) REFERENCES `game` (`GameId`);

--
-- Constraints der Tabelle `seat`
--
ALTER TABLE `seat`
  ADD CONSTRAINT `seat_ibfk_1` FOREIGN KEY (`CategoryId`) REFERENCES `category` (`CategoryId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
