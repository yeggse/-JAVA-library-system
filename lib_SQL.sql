CREATE DATABASE `psersonalprojet` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
CREATE TABLE `book` (
  `book_no` varchar(45) NOT NULL,
  `book_title` varchar(45) NOT NULL,
  `book_publisher` varchar(45) NOT NULL,
  `book_author` varchar(45) NOT NULL,
  `book_location` varchar(45) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `id` varchar(45) DEFAULT NULL,
  `lentdate` varchar(45) DEFAULT NULL,
  `backdate` varchar(45) DEFAULT NULL,
  `book_pas` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`book_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `people` (
  `id` varchar(45) NOT NULL,
  `pw` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `gender` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `royal` varchar(45) DEFAULT NULL,
  `birth` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `seat` (
  `seat_no` int NOT NULL,
  `seat_lent` varchar(45) DEFAULT NULL,
  `id` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`seat_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
