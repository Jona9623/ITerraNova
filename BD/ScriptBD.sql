-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema TerraNova
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema TerraNova
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `TerraNova` DEFAULT CHARACTER SET utf8 ;
USE `TerraNova` ;

-- -----------------------------------------------------
-- Table `TerraNova`.`Ct_AreaAlumno`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TerraNova`.`Ct_AreaAlumno` ;

CREATE TABLE IF NOT EXISTS `TerraNova`.`Ct_AreaAlumno` (
  `idCt_AreaAlumno` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `status` INT NULL,
  PRIMARY KEY (`idCt_AreaAlumno`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TerraNova`.`Ct_CptAlumno`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TerraNova`.`Ct_CptAlumno` ;

CREATE TABLE IF NOT EXISTS `TerraNova`.`Ct_CptAlumno` (
  `idCt_CptAlumno` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `status` INT NULL,
  PRIMARY KEY (`idCt_CptAlumno`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TerraNova`.`Tb_Tutor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TerraNova`.`Tb_Tutor` ;

CREATE TABLE IF NOT EXISTS `TerraNova`.`Tb_Tutor` (
  `idTb_Tutor` INT NOT NULL AUTO_INCREMENT,
  `nombre(s)` VARCHAR(45) NULL,
  `apellidopaterno` VARCHAR(20) NULL,
  `apellidomaterno` VARCHAR(20) NULL,
  `ocupacion` VARCHAR(20) NULL,
  `parentesco` VARCHAR(20) NULL,
  `calledomicilio` VARCHAR(30) NULL,
  `numerodomicilio` INT NULL,
  `coloniadomicilio` VARCHAR(20) NULL,
  `codigopostal` INT NULL,
  `telefonocasa` INT NULL,
  `celular` INT NULL,
  `correo` VARCHAR(45) NULL,
  `status` INT NULL,
  PRIMARY KEY (`idTb_Tutor`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TerraNova`.`Ct_Grado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TerraNova`.`Ct_Grado` ;

CREATE TABLE IF NOT EXISTS `TerraNova`.`Ct_Grado` (
  `idCt_Grado` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `status` INT NULL,
  PRIMARY KEY (`idCt_Grado`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TerraNova`.`Ct_Grupo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TerraNova`.`Ct_Grupo` ;

CREATE TABLE IF NOT EXISTS `TerraNova`.`Ct_Grupo` (
  `idCt_Grupo` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `status` INT NULL,
  PRIMARY KEY (`idCt_Grupo`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TerraNova`.`Tb_Alumnos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TerraNova`.`Tb_Alumnos` ;

CREATE TABLE IF NOT EXISTS `TerraNova`.`Tb_Alumnos` (
  `idTb_Alumnos` INT NOT NULL AUTO_INCREMENT,
  `matricula` VARCHAR(45) NULL,
  `nombre(s)` VARCHAR(45) NULL,
  `apellidopaterno` VARCHAR(20) NULL,
  `apellidomaterno` VARCHAR(20) NULL,
  `fechanacimiento` DATE NULL,
  `curp` VARCHAR(45) NULL,
  `municipionacimiento` VARCHAR(20) NULL,
  `estadonacimiento` VARCHAR(20) NULL,
  `nacionalidad` VARCHAR(20) NULL,
  `sexo` INT NULL,
  `calledomicilio` VARCHAR(45) NULL,
  `numerodomicilio` INT NULL,
  `coloniadomicilio` VARCHAR(45) NULL,
  `codigopostal` INT NULL,
  `telefonocasa` INT NULL,
  `celularalumno` INT NULL,
  `correoalumno` VARCHAR(45) NULL,
  `nivelcursa` VARCHAR(20) NULL,
  `r_grado` INT NULL,
  `r_grupo` INT NULL,
  `r_area` INT NULL,
  `r_cpt` INT NULL,
  `plantelprocedencia` VARCHAR(45) NULL,
  `nivelanterior` INT NULL,
  `gradoanterior` INT NULL,
  `turnoanterior` INT NULL,
  `municipioanterior` VARCHAR(20) NULL,
  `r_tutor` INT NULL,
  `status` INT NULL,
  PRIMARY KEY (`idTb_Alumnos`),
  INDEX `fk_areaAlumno_idx` (`r_area` ASC) VISIBLE,
  INDEX `fk_cptAlumno_idx` (`r_cpt` ASC) VISIBLE,
  INDEX `fk_tutorAlumno_idx` (`r_tutor` ASC) VISIBLE,
  INDEX `fk_gradoAlumno_idx` (`r_grado` ASC) VISIBLE,
  INDEX `fk_grupoAlumno_idx` (`r_grupo` ASC) VISIBLE,
  CONSTRAINT `fk_areaAlumno`
    FOREIGN KEY (`r_area`)
    REFERENCES `TerraNova`.`Ct_AreaAlumno` (`idCt_AreaAlumno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cptAlumno`
    FOREIGN KEY (`r_cpt`)
    REFERENCES `TerraNova`.`Ct_CptAlumno` (`idCt_CptAlumno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tutorAlumno`
    FOREIGN KEY (`r_tutor`)
    REFERENCES `TerraNova`.`Tb_Tutor` (`idTb_Tutor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_gradoAlumno`
    FOREIGN KEY (`r_grado`)
    REFERENCES `TerraNova`.`Ct_Grado` (`idCt_Grado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_grupoAlumno`
    FOREIGN KEY (`r_grupo`)
    REFERENCES `TerraNova`.`Ct_Grupo` (`idCt_Grupo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TerraNova`.`Ct_Puesto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TerraNova`.`Ct_Puesto` ;

CREATE TABLE IF NOT EXISTS `TerraNova`.`Ct_Puesto` (
  `idCt_Puesto` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `status` INT NULL,
  PRIMARY KEY (`idCt_Puesto`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TerraNova`.`Tb_Personal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TerraNova`.`Tb_Personal` ;

CREATE TABLE IF NOT EXISTS `TerraNova`.`Tb_Personal` (
  `idTb_Personal` INT NOT NULL AUTO_INCREMENT,
  `nombre(s)` VARCHAR(25) NULL,
  `apellidopaterno` VARCHAR(20) NULL,
  `apellidomaterno` VARCHAR(20) NULL,
  `fechanacimiento` DATE NULL,
  `curp` VARCHAR(20) NULL,
  `municipionacimiento` VARCHAR(20) NULL,
  `estadonacimiento` VARCHAR(20) NULL,
  `nacionalidad` VARCHAR(20) NULL,
  `sexo` INT NULL,
  `calledomicilio` VARCHAR(45) NULL,
  `numerodomicilio` INT NULL,
  `coloniadomicilio` VARCHAR(20) NULL,
  `codigopostal` INT NULL,
  `telefonocasa` INT NULL,
  `celular` INT NULL,
  `correo` VARCHAR(45) NULL,
  `nss` INT NULL,
  `rfc` VARCHAR(45) NULL,
  `nivelmaxestudios` VARCHAR(45) NULL,
  `licenciatura` VARCHAR(45) NULL,
  `maestria` VARCHAR(45) NULL,
  `doctorado` VARCHAR(45) NULL,
  `r_puesto` INT NULL,
  `status` INT NULL,
  PRIMARY KEY (`idTb_Personal`),
  INDEX `fk_puestoPersonal_idx` (`r_puesto` ASC) VISIBLE,
  CONSTRAINT `fk_puestoPersonal`
    FOREIGN KEY (`r_puesto`)
    REFERENCES `TerraNova`.`Ct_Puesto` (`idCt_Puesto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TerraNova`.`Ct_TipoCalificacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TerraNova`.`Ct_TipoCalificacion` ;

CREATE TABLE IF NOT EXISTS `TerraNova`.`Ct_TipoCalificacion` (
  `idCt_TipoCalificacion` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `status` INT NULL,
  PRIMARY KEY (`idCt_TipoCalificacion`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TerraNova`.`Tb_Materia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TerraNova`.`Tb_Materia` ;

CREATE TABLE IF NOT EXISTS `TerraNova`.`Tb_Materia` (
  `idTb_Materia` INT NOT NULL AUTO_INCREMENT,
  `nombrelargo` VARCHAR(45) NULL,
  `nombrecorto` VARCHAR(45) NULL,
  `calificacion` FLOAT NULL,
  `r_grado` INT NULL,
  `r_grupo` INT NULL,
  `r_personal` INT NULL,
  `r_alumno` INT NULL,
  `r_tipocali` INT NULL,
  `r_area` INT NULL,
  `r_cpt` INT NULL,
  `status` INT NULL,
  PRIMARY KEY (`idTb_Materia`),
  INDEX `fk_gradoMateria_idx` (`r_grado` ASC) VISIBLE,
  INDEX `fk_grupoMateria_idx` (`r_grupo` ASC) VISIBLE,
  INDEX `fk_personalMateria_idx` (`r_personal` ASC) VISIBLE,
  INDEX `fk_alumnoMateria_idx` (`r_alumno` ASC) VISIBLE,
  INDEX `fk_tipocaliMateria_idx` (`r_tipocali` ASC) VISIBLE,
  INDEX `fk_areaMateria_idx` (`r_area` ASC) VISIBLE,
  INDEX `fk_cptMateria_idx` (`r_cpt` ASC) VISIBLE,
  CONSTRAINT `fk_gradoMateria`
    FOREIGN KEY (`r_grado`)
    REFERENCES `TerraNova`.`Ct_Grado` (`idCt_Grado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_grupoMateria`
    FOREIGN KEY (`r_grupo`)
    REFERENCES `TerraNova`.`Ct_Grupo` (`idCt_Grupo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_personalMateria`
    FOREIGN KEY (`r_personal`)
    REFERENCES `TerraNova`.`Tb_Personal` (`idTb_Personal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_alumnoMateria`
    FOREIGN KEY (`r_alumno`)
    REFERENCES `TerraNova`.`Tb_Alumnos` (`idTb_Alumnos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tipocaliMateria`
    FOREIGN KEY (`r_tipocali`)
    REFERENCES `TerraNova`.`Ct_TipoCalificacion` (`idCt_TipoCalificacion`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_areaMateria`
    FOREIGN KEY (`r_area`)
    REFERENCES `TerraNova`.`Ct_AreaAlumno` (`idCt_AreaAlumno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cptMateria`
    FOREIGN KEY (`r_cpt`)
    REFERENCES `TerraNova`.`Ct_CptAlumno` (`idCt_CptAlumno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TerraNova`.`Ct_Incidente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TerraNova`.`Ct_Incidente` ;

CREATE TABLE IF NOT EXISTS `TerraNova`.`Ct_Incidente` (
  `idCt_incidente` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `status` INT NULL,
  PRIMARY KEY (`idCt_incidente`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TerraNova`.`Tb_ReporteDisciplinar`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TerraNova`.`Tb_ReporteDisciplinar` ;

CREATE TABLE IF NOT EXISTS `TerraNova`.`Tb_ReporteDisciplinar` (
  `idTb_ReporteDisciplinar` INT NOT NULL AUTO_INCREMENT,
  `r_alumno` INT NULL,
  `r_personal` INT NULL,
  `hora` TIME NULL,
  `fecha` DATE NULL,
  `fechareporte` DATE NULL,
  `personalsolicita` INT NULL,
  `personalllena` INT NULL,
  `r_materia` INT NULL,
  `profesor` INT NULL,
  `lugar` VARCHAR(45) NULL,
  `r_tipoincidente` INT NULL,
  `nivel` INT NULL,
  `descripcion` VARCHAR(150) NULL,
  `foto` VARCHAR(45) NULL,
  `status` INT NULL,
  PRIMARY KEY (`idTb_ReporteDisciplinar`),
  INDEX `fk_reportediAlumno_idx` (`r_alumno` ASC) VISIBLE,
  INDEX `fk_reportediPersonal_idx` (`r_personal` ASC) VISIBLE,
  INDEX `fk_reportediMateria_idx` (`r_materia` ASC) VISIBLE,
  INDEX `fk_reportediTipoincidente_idx` (`r_tipoincidente` ASC) VISIBLE,
  CONSTRAINT `fk_reportediAlumno`
    FOREIGN KEY (`r_alumno`)
    REFERENCES `TerraNova`.`Tb_Alumnos` (`idTb_Alumnos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reportediPersonal`
    FOREIGN KEY (`r_personal`)
    REFERENCES `TerraNova`.`Tb_Personal` (`idTb_Personal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reportediMateria`
    FOREIGN KEY (`r_materia`)
    REFERENCES `TerraNova`.`Tb_Materia` (`idTb_Materia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reportediTipoincidente`
    FOREIGN KEY (`r_tipoincidente`)
    REFERENCES `TerraNova`.`Ct_Incidente` (`idCt_incidente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TerraNova`.`Tb_ReporteAcademico`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TerraNova`.`Tb_ReporteAcademico` ;

CREATE TABLE IF NOT EXISTS `TerraNova`.`Tb_ReporteAcademico` (
  `idTb_ReporteAcademico` INT NOT NULL AUTO_INCREMENT,
  `r_personal` INT NULL,
  `r_materia` INT NULL,
  `r_semanafiscal` INT NULL,
  `r_alumnohonor` INT NULL,
  `r_alumnoatencion` INT NULL,
  `r_atencion` INT NULL,
  `status` INT NULL,
  PRIMARY KEY (`idTb_ReporteAcademico`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TerraNova`.`Ct_Atencion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TerraNova`.`Ct_Atencion` ;

CREATE TABLE IF NOT EXISTS `TerraNova`.`Ct_Atencion` (
  `idCt_atencion` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(150) NULL,
  `status` INT NULL,
  PRIMARY KEY (`idCt_atencion`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TerraNova`.`Ct_SemanaFiscal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TerraNova`.`Ct_SemanaFiscal` ;

CREATE TABLE IF NOT EXISTS `TerraNova`.`Ct_SemanaFiscal` (
  `idCt_SemanaFiscal` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(100) NULL,
  `status` INT NULL,
  PRIMARY KEY (`idCt_SemanaFiscal`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TerraNova`.`Ct_Dia`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TerraNova`.`Ct_Dia` ;

CREATE TABLE IF NOT EXISTS `TerraNova`.`Ct_Dia` (
  `idCt_Dia` INT NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NULL,
  `status` INT NULL,
  PRIMARY KEY (`idCt_Dia`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `TerraNova`.`Tb_TareaSemanal`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `TerraNova`.`Tb_TareaSemanal` ;

CREATE TABLE IF NOT EXISTS `TerraNova`.`Tb_TareaSemanal` (
  `idTb_TareaSemanal` INT NOT NULL AUTO_INCREMENT,
  `tarea` VARCHAR(150) NULL,
  `r_semanafiscal` INT NULL,
  `r_dia` INT NULL,
  `r_personal` INT NULL,
  `status` INT NULL,
  PRIMARY KEY (`idTb_TareaSemanal`),
  INDEX `fk_tareasemSemanaFiscal_idx` (`r_semanafiscal` ASC) VISIBLE,
  INDEX `fk_tareasemDia_idx` (`r_dia` ASC) VISIBLE,
  INDEX `fk_tareasemPersonal_idx` (`r_personal` ASC) VISIBLE,
  CONSTRAINT `fk_tareasemSemanaFiscal`
    FOREIGN KEY (`r_semanafiscal`)
    REFERENCES `TerraNova`.`Ct_SemanaFiscal` (`idCt_SemanaFiscal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tareasemDia`
    FOREIGN KEY (`r_dia`)
    REFERENCES `TerraNova`.`Ct_Dia` (`idCt_Dia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tareasemPersonal`
    FOREIGN KEY (`r_personal`)
    REFERENCES `TerraNova`.`Tb_Personal` (`idTb_Personal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;