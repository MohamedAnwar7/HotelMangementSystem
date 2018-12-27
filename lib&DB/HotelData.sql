-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Dec 17, 2018 at 12:33 PM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `HotelData`
--

-- --------------------------------------------------------

--
-- Table structure for table `Admin`
--

CREATE TABLE `Admin` (
  `UserName` varchar(40) NOT NULL,
  `PassWord` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Dumping data for table `Admin`
--

INSERT INTO `Admin` (`UserName`, `PassWord`) VALUES
('User', '123'),
('User1', '321');

-- --------------------------------------------------------

--
-- Table structure for table `Employee`
--

CREATE TABLE `Employee` (
  `id` int(11) NOT NULL,
  `FullName` varchar(80) NOT NULL,
  `Age` int(11) NOT NULL,
  `Gender` varchar(10) NOT NULL,
  `Phone` varchar(20) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Salary` int(11) NOT NULL,
  `UserName` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Dumping data for table `Employee`
--

INSERT INTO `Employee` (`id`, `FullName`, `Age`, `Gender`, `Phone`, `Email`, `Salary`, `UserName`) VALUES
(2, 'davdgbn', 56, '6y', 'dgdghhgd', 'ethgteh', 43434, 'gffgdg'),
(5, 'wrg', 2, 'Male', '90', '2', 2, '2'),
(7, '5', 5, 'Male', '5', '5', 5, '5');

-- --------------------------------------------------------

--
-- Table structure for table `Guest`
--

CREATE TABLE `Guest` (
  `id` varchar(14) NOT NULL,
  `FullName` varchar(50) NOT NULL,
  `Phone` varchar(20) NOT NULL,
  `Gender` varchar(10) NOT NULL,
  `Country` varchar(30) NOT NULL,
  `Status` varchar(20) NOT NULL,
  `NumberChildren` int(11) NOT NULL,
  `RoomID` int(11) NOT NULL,
  `EnterDate` date NOT NULL,
  `duration` int(11) NOT NULL,
  `Email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Dumping data for table `Guest`
--

INSERT INTO `Guest` (`id`, `FullName`, `Phone`, `Gender`, `Country`, `Status`, `NumberChildren`, `RoomID`, `EnterDate`, `duration`, `Email`) VALUES
('1', 'mohamed', '01110069571', 'male', 'egypt', 'sigle', 2, 1, '2019-01-01', 3, 'google@gmail.com'),
('2453464767858', 'srytkiyuluop', 'sretuyri', 'uryuy', 'djgyjuiti', 'gtyru', 23, 1, '2018-12-04', 10, 'srhtjtkyuk'),
('32546', 'Jack', '01205800986', 'dhjyjk', 'aghdth', 'tjtykj', 45, 1, '2018-12-11', 20, 'Micheal'),
('332235235', '235ggg', 'ergwethg', 'ehbeth', 'ettheth', 'getwgwet', 425, 2, '2018-12-19', 45, 'sdgsfhtd'),
('fwegehgryj', 'rhryj', 'egrhrhryj', 'etjt', 'etuu', 'ehtr', 3, 1, '2018-12-29', 5, 'm,');

-- --------------------------------------------------------

--
-- Table structure for table `Room`
--

CREATE TABLE `Room` (
  `RoomID` int(11) NOT NULL,
  `FoodCost` int(11) NOT NULL,
  `DrinkCost` int(11) NOT NULL,
  `TripCost` int(11) NOT NULL,
  `Payment` int(11) NOT NULL,
  `room_type` varchar(20) NOT NULL,
  `available` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Dumping data for table `Room`
--

INSERT INTO `Room` (`RoomID`, `FoodCost`, `DrinkCost`, `TripCost`, `Payment`, `room_type`, `available`) VALUES
(1, 40, 79, 3, 900, 'double', 3),
(2, 45, 55, 44, 3000, 'trible', 2);

-- --------------------------------------------------------

--
-- Table structure for table `Room_Cost`
--

CREATE TABLE `Room_Cost` (
  `room_type` varchar(20) NOT NULL,
  `room_cost` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

--
-- Dumping data for table `Room_Cost`
--

INSERT INTO `Room_Cost` (`room_type`, `room_cost`) VALUES
('Double', 98);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Admin`
--
ALTER TABLE `Admin`
  ADD PRIMARY KEY (`UserName`);

--
-- Indexes for table `Employee`
--
ALTER TABLE `Employee`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UserName` (`UserName`);

--
-- Indexes for table `Guest`
--
ALTER TABLE `Guest`
  ADD PRIMARY KEY (`id`),
  ADD KEY `RoomID` (`RoomID`);

--
-- Indexes for table `Room`
--
ALTER TABLE `Room`
  ADD PRIMARY KEY (`RoomID`);

--
-- Indexes for table `Room_Cost`
--
ALTER TABLE `Room_Cost`
  ADD PRIMARY KEY (`room_type`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Guest`
--
ALTER TABLE `Guest`
  ADD CONSTRAINT `Guest_ibfk_1` FOREIGN KEY (`RoomID`) REFERENCES `Room` (`RoomID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
