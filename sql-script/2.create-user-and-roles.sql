USE `banking_demo_zigmunds`;

SET foreign_key_checks = 0;
SET FOREIGN_KEY_CHECKS = 0;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` char(80) NOT NULL,
  `enabled` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--
-- NOTE: The passwords are encrypted using BCrypt
--

INSERT INTO `user` (`username`,`password`,`enabled`)
VALUES
('admin','$2a$12$oMa0E/XXPF5OQCIdUC9fpOcRBFriyNZq7YF7svWc71n7R4IPSouQK',1),
('manager','$2a$12$oMa0E/XXPF5OQCIdUC9fpOcRBFriyNZq7YF7svWc71n7R4IPSouQK',1),
('testDelete1','$2a$12$oMa0E/XXPF5OQCIdUC9fpOcRBFriyNZq7YF7svWc71n7R4IPSouQK',1),
('testDelete2','$2a$12$oMa0E/XXPF5OQCIdUC9fpOcRBFriyNZq7YF7svWc71n7R4IPSouQK',1),
('testDelete3','$2a$12$oMa0E/XXPF5OQCIdUC9fpOcRBFriyNZq7YF7svWc71n7R4IPSouQK',1),
('testDelete4','$2a$12$oMa0E/XXPF5OQCIdUC9fpOcRBFriyNZq7YF7svWc71n7R4IPSouQK',1),
('testDelete5','$2a$12$oMa0E/XXPF5OQCIdUC9fpOcRBFriyNZq7YF7svWc71n7R4IPSouQK',1),
('zigmunds','$2a$12$oMa0E/XXPF5OQCIdUC9fpOcRBFriyNZq7YF7svWc71n7R4IPSouQK',1);

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `roles`
--

INSERT INTO `role` (name)
VALUES
('ROLE_MANAGER'),
('ROLE_ADMIN'),
('ROLE_CUSTOMER');

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;

CREATE TABLE `users_roles` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,

  PRIMARY KEY (`user_id`,`role_id`),

  KEY `FK_ROLE_idx` (`role_id`),

  CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`)
  REFERENCES `user` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION,

  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`)
  REFERENCES `role` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;
SET foreign_key_checks = 1;

--
-- Dumping data for table `users_roles`
--

INSERT INTO `users_roles` (user_id,role_id)
VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 1),
(2, 3),
(3, 3),
(4, 3),
(5, 3),
(6, 3),
(7, 3),
(8, 3)

