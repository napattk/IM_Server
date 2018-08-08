/*
	hostname = 127.0.0.1
	port = 3306
	db Name = chatDB
	username = "root";
	password = "admin";
*/

CREATE DATABASE chatDB;
CREATE TABLE IF NOT EXISTS `chatDB`.`Users` (
	`userID` VARCHAR(40) NOT NULL,
	`password` VARCHAR(40) NOT NULL,
PRIMARY KEY (`userID`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `chatDB`.`Friends` (
	relationID int NOT NULL AUTO_INCREMENT,
	userID VARCHAR(40) NOT NULL,
	friendID VARCHAR(40) NOT NULL,
CONSTRAINT uniqueRow_Friends UNIQUE(userID, friendID),
CONSTRAINT CHK_cantAddSelf CHECK(userID != friendID),
PRIMARY KEY (`relationID`))
ENGINE = InnoDB;

USE `chatDB`;
INSERT INTO `Users` (`userID`,`password`) VALUES 
('user1','123456'),
('user2','123456'),
('user3','123456');

USE `chatDB`;
INSERT INTO `Friends` (`userID`,`friendID`) VALUES 
('user1','user3');

USE `chatDB`;
Drop table `Users`;

USE `chatDB`;
Drop table `Friends`;


SELECT * FROM `chatDB`.`Users`;

SELECT * FROM `chatDB`.`Friends`;