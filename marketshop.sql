-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 28, 2023 at 08:35 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `marketshop`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `CategoryID` int(10) NOT NULL,
  `Name` varchar(30) NOT NULL,
  `Description` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`CategoryID`, `Name`, `Description`) VALUES
(1, 'Fruit', 'The kind that can be eaten without cooking'),
(2, 'Green Vegetables', 'The kind used to make salads or soups'),
(3, 'Spices', 'The kind used to enhance the taste of food'),
(4, 'Meat', 'You must cook it before');

-- --------------------------------------------------------

--
-- Table structure for table `orderdetail`
--

CREATE TABLE `orderdetail` (
  `OrderID` int(10) UNSIGNED NOT NULL,
  `ProductID` int(10) NOT NULL,
  `Quantity` tinyint(4) NOT NULL,
  `Price` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `orderdetail`
--

INSERT INTO `orderdetail` (`OrderID`, `ProductID`, `Quantity`, `Price`) VALUES
(1, 1, 1, 30000),
(1, 8, 1, 120000),
(2, 2, 1, 35000),
(2, 3, 1, 150000),
(2, 4, 1, 80000),
(3, 5, 1, 35000),
(3, 7, 2, 30000),
(4, 6, 2, 80000),
(35, 3, 1, 150000),
(41, 1, 2, 60000),
(42, 1, 1, 30000),
(42, 2, 5, 175000),
(42, 8, 1, 120000),
(43, 1, 4, 30000),
(43, 4, 1, 80000),
(44, 3, 1, 150000),
(44, 8, 1, 120000),
(45, 1, 3, 30000),
(46, 2, 3, 105000),
(47, 6, 2, 80000),
(48, 1, 1, 30000),
(48, 2, 3, 105000),
(48, 4, 3, 240000),
(63, 1, 3, 90000),
(63, 2, 3, 105000),
(64, 1, 5, 150000),
(64, 2, 5, 175000),
(65, 1, 1, 30000),
(65, 2, 1, 35000),
(66, 1, 1, 30000),
(66, 2, 1, 35000),
(67, 1, 1, 30000),
(68, 1, 1, 30000),
(71, 1, 1, 30000);

--
-- Triggers `orderdetail`
--
DELIMITER $$
CREATE TRIGGER `TR_Orderdetail_UpdateProductAmount` AFTER INSERT ON `orderdetail` FOR EACH ROW UPDATE product SET product.Amount = product.Amount - NEW.quantity WHERE NEW.orderID
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `OrderID` int(10) UNSIGNED NOT NULL,
  `CustomerID` int(10) NOT NULL,
  `Date` datetime NOT NULL DEFAULT current_timestamp(),
  `Total` float NOT NULL,
  `Note` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`OrderID`, `CustomerID`, `Date`, `Total`, `Note`) VALUES
(1, 1, '2021-08-15 00:00:00', 150000, 'Use environmental protection bags'),
(2, 2, '2021-08-16 00:00:00', 235000, 'Hello'),
(3, 3, '2021-08-16 00:00:00', 65000, 'Need fast delivery'),
(4, 3, '2021-08-17 00:00:00', 80000, ''),
(35, 2, '2023-04-14 00:00:00', 265000, ''),
(41, 2, '2023-04-15 00:00:00', 60000, ''),
(42, 11, '2023-05-16 00:00:00', 150000, ''),
(43, 11, '2023-05-16 00:00:00', 200000, 'Spring Boot tester'),
(44, 11, '2023-05-16 00:00:00', 270000, 'Spring Boot tester'),
(45, 11, '2023-05-16 00:00:00', 90000, 'Spring Boot tester'),
(46, 11, '2023-05-16 00:00:00', 315000, 'Spring Boot tester'),
(47, 8, '2023-05-16 00:00:00', 160000, 'Spring Boot tester'),
(48, 11, '2023-05-16 00:00:00', 1065000, ''),
(62, 11, '2023-07-28 02:25:08', 0, 'test API true'),
(63, 11, '2023-07-28 02:26:40', 195000, 'test API true 2'),
(64, 11, '2023-07-28 02:55:15', 325000, 'test API true 2'),
(65, 11, '2023-07-28 02:56:45', 65000, 'test API true 2'),
(66, 11, '2023-07-28 02:56:59', 65000, 'test API true 2'),
(67, 11, '2023-07-28 03:00:03', 30000, 'test API true 2'),
(68, 11, '2023-07-29 01:02:54', 30000, 'test API for negative quantity'),
(71, 11, '2023-07-29 01:28:49', 30000, 'test API for negative quantity');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `ProductID` int(10) NOT NULL,
  `product_name` varchar(30) NOT NULL,
  `CategoryID` int(10) NOT NULL,
  `Unit` varchar(20) NOT NULL,
  `Amount` int(10) UNSIGNED NOT NULL,
  `Image` varchar(255) NOT NULL,
  `Price` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`ProductID`, `product_name`, `CategoryID`, `Unit`, `Amount`, `Image`, `Price`) VALUES
(1, 'Tomato', 1, 'kg', 85, 'images/tomato.jpg', 30000),
(2, 'potato', 1, 'kg', 128, 'images/potato.jpg', 35000),
(3, 'Apple', 1, 'bag', 33, 'images/apple.jpg', 150000),
(4, 'Watermelon', 1, 'per fruit', 3, 'images/watermelon.jpg', 80000),
(5, 'Broccoli', 2, 'kg', 33, 'images/broccoli.jpg', 35000),
(6, 'Celery', 2, 'kg', 63, 'images/celery.jpg', 40000),
(7, 'Spring onion', 3, 'bunch', 33, 'images/springonion.jpg', 15000),
(8, 'Garlic', 3, 'kg', 13, 'images/garlic.jpg', 120000),
(15, 'Pepper', 1, 'kg', 33, '', 20000);

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `role_id` tinyint(4) NOT NULL,
  `role_name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`role_id`, `role_name`) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `UserID` int(10) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Fullname` varchar(40) DEFAULT NULL,
  `Address` varchar(50) DEFAULT NULL,
  `City` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`UserID`, `Email`, `Password`, `Fullname`, `Address`, `City`) VALUES
(1, '', 'Abcd1234', 'John Smith', '30 Broadway', 'London'),
(2, '', 'Abcd1234', 'Jonny English', '99 River View', 'Reading'),
(3, '', 'Abcd1234', 'Elizabeth', '23 Buckinghamshire', 'York'),
(4, '', 'Abcd1234', 'Beatrix', '66 Royal Crescent', 'Bath'),
(8, '', '2354', 'kozax5', 'abc', 'bbb'),
(11, 'kozaxlh@gmail.com', '$2a$12$f.7deNTYCHy4MlReOYrOm./wwxZssUovVOG4VDWdal8zSDbXhknAe', 'kozaxlh', 'Quan 6', 'Ho Chi Minh'),
(12, 'dolienhung@yahoo.com.vn', '$2a$12$c5jJUWvzPyy2vjlciSASv.84sBd5giclZEMbXr5jwdp628VqvwTXe', 'Hung', NULL, NULL),
(19, 'b@yahoo.com', '$2a$10$38mwj4NQiye9rSIdcF7gkuL1mBYtXckI/hS3tXYTEOrWr7Na3LDAO', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user_role`
--

CREATE TABLE `user_role` (
  `id` smallint(6) NOT NULL,
  `user_id` int(10) NOT NULL,
  `role_id` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_role`
--

INSERT INTO `user_role` (`id`, `user_id`, `role_id`) VALUES
(1, 11, 1),
(2, 11, 2),
(6, 12, 1),
(7, 19, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`CategoryID`);

--
-- Indexes for table `orderdetail`
--
ALTER TABLE `orderdetail`
  ADD PRIMARY KEY (`OrderID`,`ProductID`),
  ADD KEY `OrderID` (`OrderID`),
  ADD KEY `ProductID` (`ProductID`) USING BTREE;

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`OrderID`),
  ADD KEY `CustomerID` (`CustomerID`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`ProductID`),
  ADD KEY `FKg9uehtty4v2cfs5rkmho79fge` (`CategoryID`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`role_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`UserID`);

--
-- Indexes for table `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `role_id` (`role_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `CategoryID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `OrderID` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=72;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `ProductID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `role_id` tinyint(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `UserID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `user_role`
--
ALTER TABLE `user_role`
  MODIFY `id` smallint(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `orderdetail`
--
ALTER TABLE `orderdetail`
  ADD CONSTRAINT `orderdetail_ibfk_2` FOREIGN KEY (`OrderID`) REFERENCES `orders` (`OrderID`),
  ADD CONSTRAINT `orderdetail_ibfk_3` FOREIGN KEY (`ProductID`) REFERENCES `product` (`ProductID`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `users` (`UserID`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FKg9uehtty4v2cfs5rkmho79fge` FOREIGN KEY (`CategoryID`) REFERENCES `category` (`CategoryID`),
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`CategoryID`) REFERENCES `category` (`CategoryID`);

--
-- Constraints for table `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`UserID`),
  ADD CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
