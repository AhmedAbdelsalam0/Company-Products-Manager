-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 18, 2022 at 05:52 AM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `products_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `price` float NOT NULL,
  `added_date` date DEFAULT NULL,
  `image_url` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`id`, `name`, `price`, `added_date`, `image_url`) VALUES
(10, 'Iphone 11 pro', 15000, '2021-03-22', 'C:/Users/TRO-JAN/Desktop/FX/DB1/2021-3-22-02-15-18.jpg'),
(11, 'Iphone 12 pro', 22000, '2021-03-22', 'C:/Users/TRO-JAN/Desktop/FX/DB1/2021-3-22-02-16-55.jpg'),
(12, 'Realme 3 pro', 3000, '2021-03-22', 'C:/Users/TRO-JAN/Desktop/FX/DB1/2021-3-22-02-17-48.jpg'),
(13, 'Realme 5 pro', 4000, '2021-03-22', 'C:/Users/TRO-JAN/Desktop/FX/DB1/2021-3-22-02-18-11.jpg'),
(14, 'Realme 7 pro', 5000, '2021-03-22', 'C:/Users/TRO-JAN/Desktop/FX/DB1/2021-3-22-02-18-31.jpg'),
(15, 'Samsung A30', 2500, '2021-03-22', 'C:/Users/TRO-JAN/Desktop/FX/DB1/2021-3-22-02-19-25.jpg'),
(16, 'Samsung A50', 4200, '2021-03-22', 'C:/Users/TRO-JAN/Desktop/FX/DB1/2021-3-22-02-19-57.jpg'),
(17, 'Samsung A70', 5000, '2021-03-22', 'C:/Users/TRO-JAN/Desktop/FX/DB1/2021-3-22-02-20-22.jpg'),
(18, 'Redmi Note7 pro', 3700, '2021-03-22', 'C:/Users/TRO-JAN/Desktop/FX/DB1/2021-3-22-02-21-46.jpg'),
(19, 'Redmi Note8 pro', 4500, '2021-03-22', 'C:/Users/TRO-JAN/Desktop/FX/DB1/2021-3-22-02-22-25.jpg'),
(20, 'Redmi Note9 pro', 5800, '2021-03-23', 'C:/Users/TRO-JAN/Desktop/FX/DB1/2021-3-22-02-22-57.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
