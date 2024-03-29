-- MySQL Script generated by MySQL Workbench
-- Thu Jan 30 21:57:53 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema bugalho_FootDream
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bugalho_FootDream
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bugalho_FootDream` DEFAULT CHARACTER SET utf8 ;
USE `bugalho_FootDream` ;

-- -----------------------------------------------------
-- Table `bugalho_FootDream`.`Clubes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bugalho_FootDream`.`Clubes` (
  `idClube` INT NOT NULL AUTO_INCREMENT,
  `nome_clube` VARCHAR(45) NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `descricao` TEXT NULL,
  `imagem` VARCHAR(45) NULL,
  PRIMARY KEY (`idClube`),
  UNIQUE INDEX `nome_clube_UNIQUE` (`nome_clube` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `idClube_UNIQUE` (`idClube` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bugalho_FootDream`.`Jogadores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bugalho_FootDream`.`Jogadores` (
  `idJogador` INT NOT NULL AUTO_INCREMENT,
  `nome_jogador` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `escalao` VARCHAR(45) NOT NULL,
  `posicao` VARCHAR(45) NOT NULL,
  `divisao` VARCHAR(45) NOT NULL,
  `descricao` TEXT NULL,
  `Clubes_idClube` INT NOT NULL,
  `imagem` VARCHAR(45) NULL,
  PRIMARY KEY (`idJogador`, `Clubes_idClube`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `fk_Jogadores_Clubes1_idx` (`Clubes_idClube` ASC),
  UNIQUE INDEX `idJogador_UNIQUE` (`idJogador` ASC),
  CONSTRAINT `fk_Jogadores_Clubes1`
    FOREIGN KEY (`Clubes_idClube`)
    REFERENCES `bugalho_FootDream`.`Clubes` (`idClube`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bugalho_FootDream`.`Treinadores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bugalho_FootDream`.`Treinadores` (
  `idTreinador` INT NOT NULL AUTO_INCREMENT,
  `nome_treinador` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `descricao` TEXT NULL,
  `Clubes_idClube` INT NOT NULL,
  `imagem` VARCHAR(45) NULL,
  PRIMARY KEY (`idTreinador`, `Clubes_idClube`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `fk_Treinadores_Clubes_idx` (`Clubes_idClube` ASC),
  UNIQUE INDEX `idTreinador_UNIQUE` (`idTreinador` ASC),
  CONSTRAINT `fk_Treinadores_Clubes`
    FOREIGN KEY (`Clubes_idClube`)
    REFERENCES `bugalho_FootDream`.`Clubes` (`idClube`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bugalho_FootDream`.`TipoEventos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bugalho_FootDream`.`TipoEventos` (
  `idTipo` INT NOT NULL AUTO_INCREMENT,
  `nome_tipo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idTipo`),
  UNIQUE INDEX `nome_tipo_UNIQUE` (`nome_tipo` ASC),
  UNIQUE INDEX `idTipo_UNIQUE` (`idTipo` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bugalho_FootDream`.`Eventos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bugalho_FootDream`.`Eventos` (
  `idEvento` INT NOT NULL AUTO_INCREMENT,
  `nome_evento` VARCHAR(45) NOT NULL,
  `dia_hora_evento` DATETIME NOT NULL,
  `descricao` TEXT NULL,
  `TipoEventos_idTipo` INT NOT NULL,
  `Clubes_idClube` INT NOT NULL,
  PRIMARY KEY (`idEvento`, `TipoEventos_idTipo`, `Clubes_idClube`),
  INDEX `fk_Eventos_TipoEventos1_idx` (`TipoEventos_idTipo` ASC),
  UNIQUE INDEX `idEvento_UNIQUE` (`idEvento` ASC),
  INDEX `fk_Eventos_Clubes1_idx` (`Clubes_idClube` ASC),
  CONSTRAINT `fk_Eventos_TipoEventos1`
    FOREIGN KEY (`TipoEventos_idTipo`)
    REFERENCES `bugalho_FootDream`.`TipoEventos` (`idTipo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Eventos_Clubes1`
    FOREIGN KEY (`Clubes_idClube`)
    REFERENCES `bugalho_FootDream`.`Clubes` (`idClube`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bugalho_FootDream`.`Convocatoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bugalho_FootDream`.`Convocatoria` (
  `idConvocatoria` INT NOT NULL,
  `descricao` VARCHAR(45) NULL,
  `escalao` VARCHAR(45) NULL,
  `Treinadores_idTreinador` INT NOT NULL,
  `Treinadores_Clubes_idClube` INT NOT NULL,
  PRIMARY KEY (`idConvocatoria`, `Treinadores_idTreinador`, `Treinadores_Clubes_idClube`),
  UNIQUE INDEX `idConvocatoria_UNIQUE` (`idConvocatoria` ASC),
  INDEX `fk_Convocatoria_Treinadores1_idx` (`Treinadores_idTreinador` ASC, `Treinadores_Clubes_idClube` ASC),
  CONSTRAINT `fk_Convocatoria_Treinadores1`
    FOREIGN KEY (`Treinadores_idTreinador` , `Treinadores_Clubes_idClube`)
    REFERENCES `bugalho_FootDream`.`Treinadores` (`idTreinador` , `Clubes_idClube`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
