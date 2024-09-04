USE `banking_demo_zigmunds`;

SET foreign_key_checks = 0;
SET FOREIGN_KEY_CHECKS = 0;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`first_name`,`last_name`,`email`)
VALUES
('admin','admin',"admin@admin.com"),
('manager','manager',"manager@manager.com"),
('testDelete1','testDelete1',"testDelete1@test.com"),
('testDelete2','testDelete2',"testDelete2@test.com"),
('testDelete3','testDelete3',"testDelete3@test.com"),
('testDelete4','testDelete4',"testDelete4@test.com"),
('testDelete5','testDelete5',"testDelete5@test.com");

--
-- Table structure for table `users_customer`
--

DROP TABLE IF EXISTS `users_customer`;

CREATE TABLE `users_customer` (
  `user_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,

  PRIMARY KEY (`user_id`),

  UNIQUE KEY `UNIQUE_CUSTOMER` (`customer_id`),

  CONSTRAINT `FK_USER_06` FOREIGN KEY (`user_id`)
  REFERENCES `user` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION,

  CONSTRAINT `FK_Customer` FOREIGN KEY (`customer_id`)
  REFERENCES `customer` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


SET FOREIGN_KEY_CHECKS = 1;
SET foreign_key_checks = 1;

--
-- Dumping data for table `users_customer`
--

INSERT INTO `users_customer` (user_id,customer_id)
VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7)