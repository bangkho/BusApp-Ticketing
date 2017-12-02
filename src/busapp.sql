-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 02, 2017 at 12:58 PM
-- Server version: 10.1.26-MariaDB
-- PHP Version: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `busapp`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id_admin` int(11) NOT NULL,
  `nama_admin` varchar(50) NOT NULL,
  `user_admin` varchar(20) NOT NULL,
  `pass_admin` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id_admin`, `nama_admin`, `user_admin`, `pass_admin`) VALUES
(1, 'Ahmad Khoirudin', 'admin', 'adminkhoi');

-- --------------------------------------------------------

--
-- Table structure for table `penumpang`
--

CREATE TABLE `penumpang` (
  `no_ktp` varchar(20) NOT NULL,
  `nama_penumpang` varchar(30) NOT NULL,
  `alamat` text NOT NULL,
  `tgl_lahir` date NOT NULL,
  `jenis_kelamin` enum('L','P') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `penumpang`
--

INSERT INTO `penumpang` (`no_ktp`, `nama_penumpang`, `alamat`, `tgl_lahir`, `jenis_kelamin`) VALUES
('05783826', 'Fahris', 'Jl. Jalan', '2000-12-08', 'L'),
('12345', 'reza', 'tangung', '2017-11-23', 'L'),
('1234555', 'Zhabiyan', 'Tuban', '2000-11-29', 'L'),
('1993818', 'Aditya', 'Kediri', '2000-11-13', 'L'),
('37246192', 'Umar', 'Jl. sfaddafg', '2017-11-30', 'L'),
('378194', 'Doyok', 'Malang', '2017-11-30', 'L'),
('384128418', 'Zidane', 'Malang', '2000-11-13', 'L'),
('3994838', 'Shafira', 'Jombang', '2001-11-16', 'P'),
('484779228', 'Rangga', 'Jl. Jalan', '2017-12-28', 'L'),
('48593659', 'Angger', 'Pucung', '2017-11-15', 'L'),
('5735792', 'Sheva', 'Jl. Poso', '2017-11-01', 'L'),
('57362802', 'Umar Satrio', 'Malang', '2017-11-15', 'L'),
('827383647', 'Ahmad Khoirudin', 'Malang', '2000-09-22', 'L'),
('853731', 'Fiqi', 'Pasuruan', '2017-11-17', 'L'),
('9858392', 'Febri', 'Jl. LLL', '2017-11-14', 'L');

-- --------------------------------------------------------

--
-- Table structure for table `tiket`
--

CREATE TABLE `tiket` (
  `id_tiket` int(11) NOT NULL,
  `no_ktp` varchar(20) NOT NULL,
  `nama_bus` varchar(50) NOT NULL,
  `trayek` varchar(50) NOT NULL,
  `jam` varchar(20) NOT NULL,
  `tgl` date NOT NULL,
  `tarif` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tiket`
--

INSERT INTO `tiket` (`id_tiket`, `no_ktp`, `nama_bus`, `trayek`, `jam`, `tgl`, `tarif`) VALUES
(1, '1234555', 'Rosalia Indah', 'Malang - Surabaya', '10.00', '2017-11-30', '30.000'),
(2, '1993818', 'Harapan Jaya', 'Tulungagung - Surabaya', '16.00', '2017-11-28', '35.000'),
(3, '3994838', 'Bagong', 'Solo - Surabaya', '16.00', '2017-11-29', '100.000'),
(4, '384128418', 'Lorena', 'Solo - Surabaya', '13.00', '2017-12-20', '100.000'),
(5, '37246192', 'Rosalia Indah', 'Jombang - Malang', '10.00', '2017-11-21', '35.000'),
(6, '57362802', 'Lorena', 'Solo - Surabaya', '10.00', '2017-11-29', '100.000'),
(7, '378194', 'Harapan Jaya', 'Malang - Surabaya', '13.00', '2017-12-07', '30.000'),
(8, '12345', 'Harapan Jaya', 'Jombang - Malang', '10.00', '2017-11-23', '30.000'),
(11, '12345', 'Lorena', 'Tulungagung - Surabaya', '16.00', '2017-11-23', '35.000'),
(12, '48593659', 'Restu', 'Solo - Surabaya', '13.00', '2017-11-27', '100.000'),
(13, '5735792', 'Bagong', 'Malang - Surabaya', '10.00', '2017-11-30', '35.000'),
(14, '853731', 'Harapan Jaya', 'Blitar - Surabaya', '20.00', '2017-11-29', '30.000'),
(15, '9858392', 'Harapan Jaya', 'Malang - Surabaya', '10.00', '2017-11-30', '20.000'),
(16, '827383647', 'Restu', 'Tulungagung - Surabaya', '13.00', '2017-11-30', '35.000'),
(17, '484779228', 'Restu', 'Tulungagung - Surabaya', '13.00', '2017-12-26', '35.000'),
(18, '05783826', 'Restu', 'Tulungagung - Surabaya', '13.00', '2017-12-20', '30.000');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`);

--
-- Indexes for table `penumpang`
--
ALTER TABLE `penumpang`
  ADD PRIMARY KEY (`no_ktp`);

--
-- Indexes for table `tiket`
--
ALTER TABLE `tiket`
  ADD PRIMARY KEY (`id_tiket`),
  ADD KEY `ktp` (`no_ktp`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id_admin` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tiket`
--
ALTER TABLE `tiket`
  MODIFY `id_tiket` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tiket`
--
ALTER TABLE `tiket`
  ADD CONSTRAINT `tiket_fk` FOREIGN KEY (`no_ktp`) REFERENCES `penumpang` (`no_ktp`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
