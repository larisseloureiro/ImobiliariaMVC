--Table de Categoria
CREATE TABLE IF NOT EXISTS `categoria` (
 	`codigo` bigint(20) NOT NULL AUTO_INCREMENT,
	 `tipo` varchar(60) DEFAULT NULL,
  	PRIMARY KEY (`codigo`)
	) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--Table de Negócio
CREATE TABLE IF NOT EXISTS  `negocio` (
 `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--Table de Quartos
CREATE TABLE IF NOT EXISTS `quarto` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `qntd` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--Table de Estado
CREATE TABLE  IF NOT EXISTS  `estado` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `state` varchar(30) DEFAULT NULL,
  `ufstate` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--Table de Municipio
CREATE TABLE  IF NOT EXISTS  `municipio` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome_municipio` varchar(30) DEFAULT NULL,
  `estado_codigo` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FKm89enj3tgxo272gajyxye14tj` (`estado_codigo`),
  CONSTRAINT `FKm89enj3tgxo272gajyxye14tj` FOREIGN KEY (`estado_codigo`) REFERENCES `estado` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--Table de Bairro
CREATE TABLE  IF NOT EXISTS  `bairro` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome_bairro` varchar(30) DEFAULT NULL,
  `estado_codigo` bigint(20) DEFAULT NULL,
  `municipio_codigo` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK951e22wvfew8iw192qqforbqx` (`estado_codigo`),
  KEY `FKb6bahav312nr62nipi295wk1u` (`municipio_codigo`),
  CONSTRAINT `FK951e22wvfew8iw192qqforbqx` FOREIGN KEY (`estado_codigo`) REFERENCES `estado` (`codigo`),
  CONSTRAINT `FKb6bahav312nr62nipi295wk1u` FOREIGN KEY (`municipio_codigo`) REFERENCES `municipio` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--Table de Imóvel
CREATE TABLE  IF NOT EXISTS  `imovel` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `categoria_codigo` bigint(20) DEFAULT NULL,
  `estado_codigo` bigint(20) DEFAULT NULL,
  `municipio_codigo` bigint(20) DEFAULT NULL,
  `negocio_codigo` bigint(20) DEFAULT NULL,
  `quarto_codigo` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FKeks1mw5o8gd897a61oy755tkh` (`categoria_codigo`),
  KEY `FKev6mlcot1g7cn18f9k4do9vr9` (`estado_codigo`),
  KEY `FKc79kppr6pngvdan1705qg4dqf` (`municipio_codigo`),
  KEY `FKkowcn9vhebrcti3ddx2o3alpa` (`negocio_codigo`),
  KEY `FKch7ypnihgp9hu5kh8oxk9rhh9` (`quarto_codigo`),
  CONSTRAINT `FKc79kppr6pngvdan1705qg4dqf` FOREIGN KEY (`municipio_codigo`) REFERENCES `municipio` (`codigo`),
  CONSTRAINT `FKch7ypnihgp9hu5kh8oxk9rhh9` FOREIGN KEY (`quarto_codigo`) REFERENCES `quarto` (`codigo`),
  CONSTRAINT `FKeks1mw5o8gd897a61oy755tkh` FOREIGN KEY (`categoria_codigo`) REFERENCES `categoria` (`codigo`),
  CONSTRAINT `FKev6mlcot1g7cn18f9k4do9vr9` FOREIGN KEY (`estado_codigo`) REFERENCES `estado` (`codigo`),
  CONSTRAINT `FKkowcn9vhebrcti3ddx2o3alpa` FOREIGN KEY (`negocio_codigo`) REFERENCES `negocio` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
