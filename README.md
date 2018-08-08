# IM_Server Eclipse Instructions

File -> Open Projects from file system
Directory
Locate the cloned repo
Choose the IM_Server folder
Finish

Expand IM_Server project in Eclipse
Expand lib package
right click json_simple-1.1.1.jar and mysql-connector-java-5.1.45-bin.jar
build path -> Add to build path
Expand view package
Run ServerWindow.java as java application


# DB Set-up

To run the program you need a running mySQL server.
This can be done using MySQL Workbench which can be downloaded at:
https://www.mysql.com/products/workbench/

Once MySQL Workbench is installed create a new connection by clicking on the "+" and configure the connection with the following settings: 
	hostname = 127.0.0.1
	port = 3306
	username = root
	password = admin

Once the connection is set up, click on it to run the server.
Paste the following query into the query window to initialize the database.


USERS TABLE ============================================================

CREATE DATABASE chatDB;
CREATE TABLE IF NOT EXISTS `chatDB`.`Users` (
  `userID` VARCHAR(40) NOT NULL,
  `password` VARCHAR(40) NOT NULL,
  PRIMARY KEY (`userID`))
ENGINE = InnoDB;

USE `chatDB`;
INSERT INTO `Users` (`userID`,`password`) VALUES 
('user1','123456'),
('user2','123456'),
('user3','123456');

FRIEND TABLE ============================================================

CREATE TABLE IF NOT EXISTS `chatDB`.`Friends` (
	relationID int NOT NULL AUTO_INCREMENT,
	userID VARCHAR(40) NOT NULL,
	friendID VARCHAR(40) NOT NULL,
CONSTRAINT uniqueRow_Friends UNIQUE(userID, friendID),
CONSTRAINT CHK_cantAddSelf CHECK(userID != friendID),
PRIMARY KEY (`relationID`))
ENGINE = InnoDB;
USE `chatDB`;
INSERT INTO `Friends` (`userID`,`friendID`) VALUES 
('user1','user2'),
('user2','user1'),
('user3','user1');

Execute the query by clicking the lightning bolt. 