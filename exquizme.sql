-- MySQL Script generated by MySQL Workbench
-- Sat Aug  5 10:07:09 2017
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema exquizme
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `exquizme` ;

-- -----------------------------------------------------
-- Schema exquizme
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `exquizme` DEFAULT CHARACTER SET utf8 ;
USE `exquizme` ;

-- -----------------------------------------------------
-- Table `exquizme`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `exquizme`.`users` ;

CREATE TABLE IF NOT EXISTS `exquizme`.`users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `fb_id` BIGINT NULL,
  `email` VARCHAR(100) NULL,
  `profile_img` VARCHAR(500) NULL,
  `nickname` VARCHAR(50) NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `exquizme`.`quizzes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `exquizme`.`quizzes` ;

CREATE TABLE IF NOT EXISTS `exquizme`.`quizzes` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `text` VARCHAR(200) NOT NULL,
  `type` VARCHAR(10) NOT NULL,
  `user_id` BIGINT NOT NULL,
  `quiz_group_id` BIGINT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `exquizme`.`quiz_answers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `exquizme`.`quiz_answers` ;

CREATE TABLE IF NOT EXISTS `exquizme`.`quiz_answers` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `quiz_id` BIGINT NOT NULL,
  `quiz_option_id` BIGINT NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `exquizme`.`quiz_results`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `exquizme`.`quiz_results` ;

CREATE TABLE IF NOT EXISTS `exquizme`.`quiz_results` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `correct` INT NOT NULL,
  `wrong` INT NOT NULL,
  `time` INT NOT NULL,
  `nickname` VARCHAR(50) NOT NULL,
  `score` INT NOT NULL DEFAULT 0,
  `quiz_group_id` BIGINT NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `exquizme`.`quiz_groups`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `exquizme`.`quiz_groups` ;

CREATE TABLE IF NOT EXISTS `exquizme`.`quiz_groups` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `url` VARCHAR(200) NOT NULL,
  `title` VARCHAR(100) NULL,
  `user_id` BIGINT NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `exquizme`.`quiz_options`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `exquizme`.`quiz_options` ;

CREATE TABLE IF NOT EXISTS `exquizme`.`quiz_options` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `order` INT NOT NULL,
  `text` VARCHAR(50) NULL,
  `quiz_id` BIGINT NOT NULL,
  `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
