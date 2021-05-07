--Utilizando o REPLACE para nâo adicionar um novo repetido.
--Table Categoria
REPLACE INTO categoria  values (1,'Apartamento');
REPLACE INTO categoria  values (2,'Casa');
REPLACE INTO categoria  values (3,'Casa de Condomínio');
REPLACE INTO categoria  values (4,'Chácara');



--Table Negócios
REPLACE INTO negocio  values (1,'Comprar');
REPLACE INTO negocio  values (2,'Alugar');
REPLACE INTO negocio  values (3,'Imóveis Novos');

--Table Quartos
REPLACE INTO quarto  values (1,'1');
REPLACE INTO quarto  values (2,'2');
REPLACE INTO quarto  values (3,'3');
REPLACE INTO quarto  values (4,'4');
REPLACE INTO quarto  values (5,'5');

--Table de Estado
REPLACE INTO estado  values (1,'São Paulo','SP');
REPLACE INTO estado  values (2,'Rio de Janeiro', 'RJ');
REPLACE INTO estado  values (3,'Paraná', 'PR');

--Table Município 
REPLACE INTO municipio  values (1,'Sorocaba',1);
REPLACE INTO municipio  values (2,'Rio de Janeiro',2);
REPLACE INTO municipio  values (3,'Curitiba',3);

--Table Bairro
REPLACE INTO bairro  values (1,'Campolim',1,1);
REPLACE INTO bairro  values (2,'Barra da Tijuca',2,2);
REPLACE INTO bairro  values (3,'Centro',3,3);

--Table Imóvel
REPLACE INTO imovel  values (1,2,1,1,1,2);
REPLACE INTO imovel  values (2,3,2,2,2,3);
REPLACE INTO imovel  values (3,1,3,3,3,2);