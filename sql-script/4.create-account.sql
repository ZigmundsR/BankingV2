USE `banking_demo_zigmunds`;

SET foreign_key_checks = 0;
SET FOREIGN_KEY_CHECKS = 0;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `balance` int NOT NULL,
  `iban` varchar(21) DEFAULT NULL,
  `customer_id` int DEFAULT NULL,

  PRIMARY KEY (`id`),

  UNIQUE KEY `IBAN_UNIQUE` (`iban`),

  KEY `FK_CUSTOMER_idx` (`customer_id`),

  CONSTRAINT `FK_CUSTOMER_05`
  FOREIGN KEY (`customer_id`)
  REFERENCES `customer` (`id`)

  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;
SET foreign_key_checks = 1;

INSERT INTO `account` (`balance`,`iban`,`customer_id`)
VALUES
(529,"LV59UNLA0050021640004",8);
