-- MySQL Script generated by MySQL Workbench
-- Sat Jun  9 22:30:32 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
SET global time_zone = "-03:00";
-- -----------------------------------------------------
-- Schema erp
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema erp
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `erp` DEFAULT CHARACTER SET utf8 ;
USE `erp` ;

-- -----------------------------------------------------
-- Table `erp`.`estado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `erp`.`estado` ;

CREATE TABLE IF NOT EXISTS `erp`.`estado` (
  `id` INT NOT NULL auto_increment,
  `nome` VARCHAR(50) NOT NULL,
  `sigla` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `erp`.`cidade`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `erp`.`cidade` ;

CREATE TABLE IF NOT EXISTS `erp`.`cidade` (
  `id` INT NOT NULL auto_increment,
  `nome` VARCHAR(255) NOT NULL,
  `estado_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cidade_estado1_idx` (`estado_id` ASC),
  CONSTRAINT `fk_cidade_estado1`
    FOREIGN KEY (`estado_id`)
    REFERENCES `erp`.`estado` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `erp`.`estabelecimento`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `erp`.`estabelecimento` ;

CREATE TABLE IF NOT EXISTS `erp`.`estabelecimento` (
  `id` INT NOT NULL auto_increment,
  `cnpj` VARCHAR(19) NOT NULL,
  `nome` VARCHAR(60) NOT NULL,
  `nome_fantasia` VARCHAR(60) NULL,
  `telefone` VARCHAR(20) NOT NULL,
  `telefone_opt` VARCHAR(20) NULL,
  `email` VARCHAR(45) NULL,
  `cep` VARCHAR(10) NOT NULL,
  `logradouro` VARCHAR(35) NOT NULL,
  `numero` VARCHAR(8) NOT NULL,
  `complemento` VARCHAR(40) NULL,
  `cidade_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_estabelecimento_cidade_idx` (`cidade_id` ASC),
  CONSTRAINT `fk_estabelecimento_cidade`
    FOREIGN KEY (`cidade_id`)
    REFERENCES `erp`.`cidade` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `erp`.`icms`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `erp`.`ncm` ;

CREATE TABLE IF NOT EXISTS `erp`.`ncm` (
  `id` INT NOT NULL,
  `codigo` VARCHAR(8) NULL,
  `descricao` VARCHAR(255) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `erp`.`icms`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `erp`.`icms` ;

CREATE TABLE IF NOT EXISTS `erp`.`icms` (
  `id` INT NOT NULL,
  `codigo` VARCHAR(3) NOT NULL,
  `descricao` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `erp`.`cfop`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `erp`.`cfop` ;

CREATE TABLE IF NOT EXISTS `erp`.`cfop` (
  `id` INT NOT NULL,
  `codigo` VARCHAR(5) NOT NULL,
  `descricao` VARCHAR(255) NOT NULL,
  `icms_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cfop_icms1_idx` (`icms_id` ASC),
  CONSTRAINT `fk_cfop_icms1`
    FOREIGN KEY (`icms_id`)
    REFERENCES `erp`.`icms` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `erp`.`produto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `erp`.`produto` ;

CREATE TABLE IF NOT EXISTS `erp`.`produto` (
  `id` INT NOT NULL auto_increment,
  `nome` VARCHAR(60) NULL,
  `descricao` VARCHAR(255) NULL,
  `estoque` INT NULL,
  `preco` DOUBLE NULL,
  `ncm_id` INT NOT NULL,
  `cfop_id` INT NOT NULL,
  PRIMARY KEY (`id`, `ncm_id`, `cfop_id`),
  INDEX `fk_produto_ncm1_idx` (`ncm_id` ASC),
  INDEX `fk_produto_cfop1_idx` (`cfop_id` ASC),
  CONSTRAINT `fk_produto_ncm1`
    FOREIGN KEY (`ncm_id`)
    REFERENCES `erp`.`ncm` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_produto_cfop1`
    FOREIGN KEY (`cfop_id`)
    REFERENCES `erp`.`cfop` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `erp`.`cfop`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `erp`.`cfop` ;

CREATE TABLE IF NOT EXISTS `erp`.`cfop` (
  `id` INT NOT NULL,
  `codigo` VARCHAR(5) NOT NULL,
  `descricao` VARCHAR(255) NOT NULL,
  `icms_id` INT NOT NULL,
  PRIMARY KEY (`id`, `icms_id`),
  INDEX `fk_cfop_icms1_idx` (`icms_id` ASC),
  CONSTRAINT `fk_cfop_icms1`
    FOREIGN KEY (`icms_id`)
    REFERENCES `erp`.`icms` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `erp`.`cest`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `erp`.`cest` ;

CREATE TABLE IF NOT EXISTS `erp`.`cest` (
  `id` INT NOT NULL,
  `codigo` VARCHAR(7) NULL,
  `descricao` VARCHAR(255) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `erp`.`usuario`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `erp`.`usuario` ;

CREATE TABLE IF NOT EXISTS `erp`.`usuario` (
  `id` INT NOT NULL auto_increment,
  `cpf` VARCHAR(15) NOT NULL,
  `nome` VARCHAR(45) NOT NULL,
  `login` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(120) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `erp`.`grupo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `erp`.`grupo` ;

CREATE TABLE IF NOT EXISTS `erp`.`grupo` (
  `id` INT NOT NULL,
  `nome` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `erp`.`usuario_has_grupo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `erp`.`usuario_has_grupo` ;

CREATE TABLE IF NOT EXISTS `erp`.`usuario_has_grupo` (
  `usuario_id` INT NOT NULL ,
  `grupo_id` INT NOT NULL,
  PRIMARY KEY (`usuario_id`, `grupo_id`),
  INDEX `fk_usuario_has_grupo_grupo1_idx` (`grupo_id` ASC),
  INDEX `fk_usuario_has_grupo_usuario1_idx` (`usuario_id` ASC),
  CONSTRAINT `fk_usuario_has_grupo_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `erp`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_usuario_has_grupo_grupo1`
    FOREIGN KEY (`grupo_id`)
    REFERENCES `erp`.`grupo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `erp`.`permissao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `erp`.`permissao` ;

CREATE TABLE IF NOT EXISTS `erp`.`permissao` (
  `id` INT NOT NULL,
  `nome` VARCHAR(50) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `erp`.`grupo_has_permissao`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `erp`.`grupo_has_permissao` ;

CREATE TABLE IF NOT EXISTS `erp`.`grupo_has_permissao` (
  `grupo_id` INT NOT NULL,
  `permissao_id` INT NOT NULL,
  PRIMARY KEY (`grupo_id`, `permissao_id`),
  INDEX `fk_grupo_has_permissao_permissao1_idx` (`permissao_id` ASC),
  INDEX `fk_grupo_has_permissao_grupo1_idx` (`grupo_id` ASC),
  CONSTRAINT `fk_grupo_has_permissao_grupo1`
    FOREIGN KEY (`grupo_id`)
    REFERENCES `erp`.`grupo` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_grupo_has_permissao_permissao1`
    FOREIGN KEY (`permissao_id`)
    REFERENCES `erp`.`permissao` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `erp`.`cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `erp`.`cliente` ;

CREATE TABLE IF NOT EXISTS `erp`.`cliente` (
  `id` INT NOT NULL auto_increment,
  `cpf` VARCHAR(15) NULL,
  `nome` VARCHAR(45) NULL,
  `telefone` VARCHAR(20) NULL,
  `email` VARCHAR(45) NULL,
  `cep` VARCHAR(10) NULL,
  `logradouro` VARCHAR(35) NULL,
  `numero` VARCHAR(8) NULL,
  `complemento` VARCHAR(40) NULL,
  `cidade_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_cliente_cidade1_idx` (`cidade_id` ASC),
  CONSTRAINT `fk_cliente_cidade1`
    FOREIGN KEY (`cidade_id`)
    REFERENCES `erp`.`cidade` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `erp`.`venda`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `erp`.`venda` ;

CREATE TABLE IF NOT EXISTS `erp`.`venda` (
  `id` INT NOT NULL auto_increment,
     `data_criacao` DATETIME NOT NULL,
     `valor_frete` DECIMAL(10,2),
     `valor_desconto` DECIMAL(10,2),
     `valor_total` DECIMAL(10,2) NOT NULL,
     `status` VARCHAR(30) NOT NULL,
     `observacao` VARCHAR(200),
     `data_hora_entrega` DATETIME,
  `usuario_id` INT NOT NULL,
  `cliente_id` INT NOT NULL,
  PRIMARY KEY (`id`, `cliente_id`),
  INDEX `fk_venda_usuario1_idx` (`usuario_id` ASC),
  INDEX `fk_venda_cliente1_idx` (`cliente_id` ASC),
  CONSTRAINT `fk_venda_usuario1`
    FOREIGN KEY (`usuario_id`)
    REFERENCES `erp`.`usuario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_venda_cliente1`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `erp`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `erp`.`item_venda`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `erp`.`item_venda` ;

CREATE TABLE IF NOT EXISTS `erp`.`item_venda` (
  `id` INT NOT NULL auto_increment,
  `quantidade` INT NOT NULL,
  `valor_unitario` DECIMAL(10,2) NULL,
  `venda_id` INT NOT NULL,
  `produto_id` INT NOT NULL,
  PRIMARY KEY (`id`, `venda_id`, `produto_id`),
  INDEX `fk_item_venda_venda1_idx` (`venda_id` ASC),
  INDEX `fk_item_venda_produto1_idx` (`produto_id` ASC),
  CONSTRAINT `fk_item_venda_venda1`
    FOREIGN KEY (`venda_id`)
    REFERENCES `erp`.`venda` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_item_venda_produto1`
    FOREIGN KEY (`produto_id`)
    REFERENCES `erp`.`produto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


INSERT INTO estado (id, nome, sigla) VALUES (1,'Acre', 'AC');
INSERT INTO estado (id, nome, sigla) VALUES (2,'Bahia', 'BA');
INSERT INTO estado (id, nome, sigla) VALUES (3,'Goiás', 'GO');
INSERT INTO estado (id, nome, sigla) VALUES (4,'Minas Gerais', 'MG');
INSERT INTO estado (id, nome, sigla) VALUES (5,'Santa Catarina', 'SC');
INSERT INTO estado (id, nome, sigla) VALUES (6,'São Paulo', 'SP');


INSERT INTO cidade (nome, estado_id) VALUES ('Rio Branco', 1);
INSERT INTO cidade (nome, estado_id) VALUES ('Cruzeiro do Sul', 1);
INSERT INTO cidade (nome, estado_id) VALUES ('Salvador', 2);
INSERT INTO cidade (nome, estado_id) VALUES ('Porto Seguro', 2);
INSERT INTO cidade (nome, estado_id) VALUES ('Santana', 2);
INSERT INTO cidade (nome, estado_id) VALUES ('Goiânia', 3);
INSERT INTO cidade (nome, estado_id) VALUES ('Itumbiara', 3);
INSERT INTO cidade (nome, estado_id) VALUES ('Novo Brasil', 3);
INSERT INTO cidade (nome, estado_id) VALUES ('Belo Horizonte', 4);
INSERT INTO cidade (nome, estado_id) VALUES ('Uberlândia', 4);
INSERT INTO cidade (nome, estado_id) VALUES ('Montes Claros', 4);
INSERT INTO cidade (nome, estado_id) VALUES ('Florianópolis', 5);
INSERT INTO cidade (nome, estado_id) VALUES ('Criciúma', 5);
INSERT INTO cidade (nome, estado_id) VALUES ('Camboriú', 5);
INSERT INTO cidade (nome, estado_id) VALUES ('Lages', 5);
INSERT INTO cidade (nome, estado_id) VALUES ('São Paulo', 6);
INSERT INTO cidade (nome, estado_id) VALUES ('Ribeirão Preto', 6);
INSERT INTO cidade (nome, estado_id) VALUES ('Campinas', 6);
INSERT INTO cidade (nome, estado_id) VALUES ('Santos', 6);
INSERT INTO `grupo` VALUES (1,'Administrador'),(2,'Gerente'),(3,'Vendedor'),(4,'Estoquista');

INSERT INTO `permissao` VALUES (1,'CADASTRAR_USUARIO'),(2,'CADASTRAR_ALUNO'),(3,'CADASTRAR_ACERVO'),(4,'DELETAR_USUARIO'),(5,'DELETAR_ACERVO'),(6,'DELETAR_ALUNO'),(7,'PESQUISAR_USUARIO'),(8,'PESQUISAR_ACERVO'),(9,'PESQUISAR_ALUNO'),(10,'EDITAR_USUARIO'),(11,'EDITAR_ACERVO'),(12,'EDITAR_ALUNO'),(13,'CADASTRAR_EMPRESTIMO'),(14,'DELETAR_EMPRESTIMO'),(15,'EDITAR_EMPRESTIMO'),(16,'QUITAR_EMPRESTIMO');

INSERT INTO `grupo_has_permissao` VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(2,2),(2,3),(2,5),(2,6),(2,7),(2,8),(2,9),(2,11),(2,12),(2,13),(2,14),(2,15),(2,16),(3,6),(3,7);

INSERT INTO `usuario` VALUES (1,'097.035.854-77','Caio Lucena','caio','$2a$10$8IAlZZ5BX1huMcpp2kgrQ.pRfiWe2s1BDhH7YiKiqA8mdcsQvw24e');

INSERT INTO `usuario_has_grupo` VALUES (1,1),(1,2);

INSERT INTO `icms` (`id`, `codigo`, `descricao`) VALUES 
(1, '102', 'Simples Nacional: 102: Sem permissão de crédito');
INSERT INTO `icms` (`id`, `codigo`, `descricao`) VALUES
 (2, '500', 'Simples Nacional: 500: ICMS cobrado antes por subst trib ou antecipação');

INSERT INTO `cfop` (`id`, `codigo`, `descricao`,`icms_id`)
VALUES (1,'5.405', 'Venda de mercadoria adquirida ou recebida de terceiros em operação com mercadoria sujeita ao regime de substituição tributária, na condição de contribuinte substituído',2);

INSERT INTO `cfop` (`id`, `codigo`, `descricao`,`icms_id`)
VALUES (2,'5.408', 'Transferência de produção do estabelecimento em operação com produto sujeito ao regime de substituição tributária',2);

INSERT INTO `cfop` (`id`, `codigo`, `descricao`,`icms_id`) VALUES 
(3,'5.102', 'Venda de mercadoria adquirida ou recebida de terceiros',1);
INSERT INTO `cfop`  (`id`, `codigo`, `descricao`,`icms_id`) VALUES
 (4,'5.103', 'Venda de produção do estabelecimento, efetuada fora do estabelecimento',1);

INSERT INTO `ncm` VALUES (1, '99910000', 'ENCOMENDAS POSTAIS');
INSERT INTO `ncm` VALUES (2, '99920000', 'AMOSTRAS');

#insert into venda values (2,20151010,100,1,1);
#insert into venda values (2,20181220,999,1,1);



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


