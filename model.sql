-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema book_store
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema book_store
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `book_store` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `book_store` ;

-- -----------------------------------------------------
-- Table `book_store`.`publisher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_store`.`publisher` (
  `Name` VARCHAR(50) NOT NULL,
  `Address` VARCHAR(100) NULL DEFAULT NULL,
  `Phone` VARCHAR(25) NULL DEFAULT NULL,
  PRIMARY KEY (`Name`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `book_store`.`book`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_store`.`book` (
  `ISBN` INT NOT NULL,
  `Title` VARCHAR(30) NULL DEFAULT NULL,
  `Author` VARCHAR(30) NULL DEFAULT NULL,
  `Publisher_name` VARCHAR(30) NULL DEFAULT NULL,
  `Year` CHAR(4) NULL DEFAULT NULL,
  `Selling_price` FLOAT NULL DEFAULT NULL,
  `Category` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`ISBN`),
  INDEX `Publisher_name` (`Publisher_name` ASC) VISIBLE,
  CONSTRAINT `book_ibfk_1`
    FOREIGN KEY (`Publisher_name`)
    REFERENCES `book_store`.`publisher` (`Name`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `book_store`.`authors`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_store`.`authors` (
  `Book_id` INT NULL DEFAULT NULL,
  `Author_name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`Author_name`),
  INDEX `Book_id` (`Book_id` ASC) VISIBLE,
  CONSTRAINT `authors_ibfk_1`
    FOREIGN KEY (`Book_id`)
    REFERENCES `book_store`.`book` (`ISBN`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `book_store`.`user_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_store`.`user_info` (
  `userID` INT NOT NULL,
  `userName` VARCHAR(50) NULL DEFAULT NULL,
  `Password` VARCHAR(100) NULL DEFAULT NULL,
  `Lname` VARCHAR(30) NULL DEFAULT NULL,
  `Fname` VARCHAR(20) NULL DEFAULT NULL,
  `Email` VARCHAR(30) NULL DEFAULT NULL,
  `Phone` CHAR(11) NULL DEFAULT NULL,
  `Address` VARCHAR(100) NULL DEFAULT NULL,
  `type` VARCHAR(8) NULL DEFAULT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE INDEX `userName` (`userName` ASC) VISIBLE,
  UNIQUE INDEX `Email` (`Email` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `book_store`.`book_order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_store`.`book_order` (
  `Order_id` INT NOT NULL,
  `customerID` INT NULL DEFAULT NULL,
  `Book_id` INT NULL DEFAULT NULL,
  `quantity` INT NULL DEFAULT NULL,
  PRIMARY KEY (`Order_id`),
  INDEX `Book_id` (`Book_id` ASC) VISIBLE,
  INDEX `customerID` (`customerID` ASC) VISIBLE,
  CONSTRAINT `book_order_ibfk_1`
    FOREIGN KEY (`Book_id`)
    REFERENCES `book_store`.`book` (`ISBN`)
    ON UPDATE CASCADE,
  CONSTRAINT `book_order_ibfk_2`
    FOREIGN KEY (`customerID`)
    REFERENCES `book_store`.`user_info` (`userID`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `book_store`.`borrower`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_store`.`borrower` (
  `Card_no` INT NOT NULL,
  `Name` VARCHAR(50) NULL DEFAULT NULL,
  `Address` VARCHAR(100) NULL DEFAULT NULL,
  `Phone` CHAR(9) NULL DEFAULT NULL,
  PRIMARY KEY (`Card_no`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `book_store`.`done_orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_store`.`done_orders` (
  `userID` INT NULL DEFAULT NULL,
  `bookISBN` INT NULL DEFAULT NULL,
  `bookQuantity` INT NULL DEFAULT NULL,
  `price` FLOAT NULL DEFAULT NULL,
  `Date` DATE NULL DEFAULT NULL,
  INDEX `userID` (`userID` ASC) VISIBLE,
  INDEX `bookISBN` (`bookISBN` ASC) VISIBLE,
  CONSTRAINT `done_orders_ibfk_1`
    FOREIGN KEY (`userID`)
    REFERENCES `book_store`.`user_info` (`userID`)
    ON DELETE SET NULL
    ON UPDATE CASCADE,
  CONSTRAINT `done_orders_ibfk_2`
    FOREIGN KEY (`bookISBN`)
    REFERENCES `book_store`.`book` (`ISBN`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `book_store`.`shopping_cart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `book_store`.`shopping_cart` (
  `customerID` INT NULL DEFAULT NULL,
  `bookISBN` INT NULL DEFAULT NULL,
  `bookQuantity` INT NULL DEFAULT NULL,
  INDEX `customerID` (`customerID` ASC) VISIBLE,
  INDEX `bookISBN` (`bookISBN` ASC) VISIBLE,
  CONSTRAINT `shopping_cart_ibfk_1`
    FOREIGN KEY (`customerID`)
    REFERENCES `book_store`.`user_info` (`userID`)
    ON UPDATE CASCADE,
  CONSTRAINT `shopping_cart_ibfk_2`
    FOREIGN KEY (`bookISBN`)
    REFERENCES `book_store`.`book` (`ISBN`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
