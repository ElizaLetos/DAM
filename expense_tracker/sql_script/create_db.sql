CREATE DATABASE  IF NOT EXISTS `expense_tracker`;
USE `expense_tracker`;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Table structure for table `expense`
--

DROP TABLE IF EXISTS `expense`;

CREATE TABLE `expense` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `amount` double DEFAULT NULL,
  `category_id` int NOT NULL,
  `date` date DEFAULT NULL,
  `payment_type` varchar(45) DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (user_id) REFERENCES user(id),
  FOREIGN KEY (category_id) REFERENCES category(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Table structure for table `income`
--

DROP TABLE IF EXISTS `income`;

CREATE TABLE `income` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `amount` double DEFAULT NULL,
  `category_id` int NOT NULL,
  `date` date DEFAULT NULL,
  `payment_type` varchar(45) DEFAULT NULL,
  `note` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (user_id) REFERENCES user(id),
  FOREIGN KEY (category_id) REFERENCES category(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `user`
--

INSERT INTO `user` VALUES 
	('Leslie','leslie@gmail.com','password123'),
	('Emma','emma@gmail.com','test'),
	('Avani','avani@gmail.com','test123'),
	('Yuri','yuri@gmail.com','123test');
    
--
-- Data for table `category`
--

	INSERT INTO `expense_tracker`.`category`
(`name`, `date`, `description`)
VALUES
('Food', '2024-09-30', 'Dinner with friends'),
('Transport','2024-10-02', 'Taxi to work'),
('Gift', '2024-08-20', '1 year aniversary'),
('Salary', '2024-10-25', '');

--
-- Data for table `expense`
--

INSERT INTO `expense_tracker`.`expense`
(`user_id`, `amount`, `category_id`, `date`, `payment_type`, `note`)
VALUES
(1, 200, 1, '2024-10-02', 'Credit Card', null);

INSERT INTO `expense_tracker`.`income`
(`user_id`, `amount`, `category_id`, `date`, `source`, `note`)
VALUES
(2, 100, 2, '2024-08-20', 'Credit Card', null);




