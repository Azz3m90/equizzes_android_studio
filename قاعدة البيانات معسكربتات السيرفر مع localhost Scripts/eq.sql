-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 23, 2018 at 02:11 AM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `eq`
--

-- --------------------------------------------------------

--
-- Table structure for table `donequizzes`
--

CREATE TABLE `donequizzes` (
  `userid` int(11) NOT NULL,
  `quizid` int(11) NOT NULL,
  `TotalMarks` int(11) NOT NULL,
  `QDate` varchar(59) NOT NULL,
  `Time` varchar(59) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `donequizzes`
--

INSERT INTO `donequizzes` (`userid`, `quizid`, `TotalMarks`, `QDate`, `Time`) VALUES
(6, 1, 100, '21-06-2018', '10:57'),
(7, 8, 20, '22-06-2018', '04:29'),
(7, 2, 25, '22-06-2018', '04:29'),
(8, 1, 75, '22-06-2018', '04:30'),
(10, 1, 75, '22-06-2018', '04:31'),
(10, 8, 0, '22-06-2018', '04:31'),
(9, 8, 40, '22-06-2018', '04:32'),
(12, 1, 50, '22-06-2018', '04:33'),
(6, 8, 40, '22-06-2018', '09:01');

-- --------------------------------------------------------

--
-- Table structure for table `options`
--

CREATE TABLE `options` (
  `id` int(11) NOT NULL,
  `subQuizid` int(11) NOT NULL,
  `choices` varchar(255) NOT NULL,
  `mark` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `options`
--

INSERT INTO `options` (`id`, `subQuizid`, `choices`, `mark`) VALUES
(1, 1, 'PL/1', 0),
(2, 1, 'FORTRAN', 0),
(3, 1, 'BASIC', 0),
(4, 1, 'PASCAL', 25),
(5, 1, 'None of the above', 0),
(6, 2, 'Data transmission', 0),
(7, 2, 'Data flow', 25),
(8, 2, 'Data capture', 0),
(9, 2, 'Data processing', 0),
(10, 2, 'None of the above', 0),
(11, 3, 'ALU', 0),
(12, 3, 'Memory', 0),
(13, 3, 'CPU', 25),
(14, 3, 'Control unit', 0),
(15, 3, 'None of the above', 0),
(16, 4, 'Technological advancement', 25),
(17, 4, 'Scientific code', 0),
(18, 4, 'Object Oriented Programming', 0),
(19, 4, 'All of the above', 0),
(20, 4, 'None of the above', 0),
(21, 5, '16 bits', 0),
(22, 5, '8 bits', 25),
(23, 5, '24', 0),
(24, 5, '12 bits', 0),
(25, 6, 'Central Processing Unit', 25),
(26, 6, 'Critical Processing Unit.\r\n', 0),
(27, 6, 'Crucial Processing Unit', 0),
(28, 6, 'Central Printing Unit', 0),
(29, 7, 'Start up point\r\n', 0),
(30, 7, 'Booting\r\n', 25),
(31, 7, 'Connecting', 0),
(32, 7, 'Resetting', 0),
(33, 8, 'Transistor', 0),
(34, 8, 'Processor', 25),
(35, 8, 'Bios', 0),
(36, 8, 'Computer Chip\r\n', 0),
(37, 9, 'Windows', 0),
(38, 9, 'Mac', 0),
(39, 9, 'Linux', 20),
(40, 9, 'Contiki', 0),
(41, 10, 'View System', 0),
(42, 10, 'Content Providers', 20),
(43, 10, 'Activity Manager', 0),
(44, 10, 'Notifications Manager', 0),
(45, 11, 'android.text', 0),
(46, 11, 'android.os', 0),
(47, 11, 'android.view', 0),
(48, 11, 'android.webkit', 20),
(49, 12, 'Content Providers', 0),
(50, 12, 'View System', 0),
(51, 12, 'Notifications Manager', 20),
(52, 12, 'Activity Manager', 0),
(53, 13, 'Activity Manager', 20),
(54, 13, 'View System', 0),
(55, 13, 'Notifications Manager', 0),
(56, 13, 'Content Providers', 0),
(100, 31, 'test', 0),
(200, 33, 'test', 0);

-- --------------------------------------------------------

--
-- Table structure for table `quiz`
--

CREATE TABLE `quiz` (
  `id` int(11) NOT NULL,
  `quiztype` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `quiz`
--

INSERT INTO `quiz` (`id`, `quiztype`) VALUES
(1, 'Computer'),
(2, 'IT'),
(4, 'java quiz'),
(8, 'android quiz'),
(10, 'testupdated');

-- --------------------------------------------------------

--
-- Table structure for table `subquiz`
--

CREATE TABLE `subquiz` (
  `id` int(11) NOT NULL,
  `quiztid` int(11) NOT NULL,
  `Questions` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `subquiz`
--

INSERT INTO `subquiz` (`id`, `quiztid`, `Questions`) VALUES
(1, 1, 'which of the following languages is more suited to a structured program?'),
(2, 1, 'A computer assisted method for the recording and analyzing of existing or hypothetical systems is :'),
(3, 1, 'The brain of any computer system is'),
(4, 1, 'What difference does the 5th generation computer have from other generation computers?'),
(5, 2, 'How many bits make a byte?'),
(6, 2, 'What is the meaning of (CPU)?'),
(7, 2, 'The process of starting or restarting a computer is called:'),
(8, 2, 'One component of the motherboard is:'),
(9, 8, 'MCQ. The Dalvik Virtual Machine (DVM) actually uses core features of '),
(10, 8, 'MCQ. A type of service provided by android that allows sharing and publishing of data to other applications is'),
(11, 8, 'MCQ. The android library that provides access to UI pre-built elements such as buttons, lists, views etc. is'),
(12, 8, 'MCQ. A type of service provided by android that shows messages and alerts to user is'),
(13, 8, 'MCQ. A type of service provided by android that controls application lifespan and activity pile is'),
(33, 10, 'test'),
(35, 10, 'test');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `tel` varchar(50) NOT NULL,
  `login` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `isAdmin` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `tel`, `login`, `password`, `isAdmin`) VALUES
(5, 'Azzam', '963-991576641', 'False', 'master', 'True'),
(6, 'burhan', '963-999030191', 'False', 'master', 'False'),
(7, 'Allam', '+963-991775855', 'False', '123', 'False'),
(8, 'Ali', '+963-9915778652', 'False', '123', 'False'),
(9, 'Ahmad', '+963-991775858', 'False', '123', 'False'),
(10, 'Noha', '+961-9915766412', 'False', '123', 'False'),
(11, 'Roba', '+996-991586642', 'False', '123', 'False'),
(12, 'Rasha', '+965-887523341', 'False', '123', 'False'),
(13, 'test', 'test', 'False', 'test', 'False');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `options`
--
ALTER TABLE `options`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `quiz`
--
ALTER TABLE `quiz`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `subquiz`
--
ALTER TABLE `subquiz`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `options`
--
ALTER TABLE `options`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=201;
--
-- AUTO_INCREMENT for table `quiz`
--
ALTER TABLE `quiz`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `subquiz`
--
ALTER TABLE `subquiz`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
