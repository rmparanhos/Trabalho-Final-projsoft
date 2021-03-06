DROP TABLE banco.CLIENTE2;

CREATE TABLE banco.CLIENTE2
(ID             INT(11) NOT NULL AUTO_INCREMENT,
 NOME           VARCHAR(30), 
 SEXO           CHAR(1),
 IDADE          TINYINT(1),
 NEWS_LETTER    TINYINT(1),
 CONSTRAINT CLIENTE2_ID_PK PRIMARY KEY (id),
 CONSTRAINT CLIENTE2_NOME_NN CHECK (NOME IS NOT NULL)
)
ENGINE = INNODB
CHARACTER SET utf8
COLLATE utf8_general_ci;

INSERT INTO CLIENTE2(NOME, SEXO, IDADE, NEWS_LETTER)
VALUES('Jose Roberto', 'M', 1, 0);

INSERT INTO CLIENTE2(NOME, SEXO, IDADE, NEWS_LETTER)
VALUES('Joana Rocha', 'F', 3, 1);

INSERT INTO CLIENTE2(NOME, SEXO, IDADE, NEWS_LETTER)
VALUES('Vinicius Ribeiro', 'M', 2, 0);

INSERT INTO CLIENTE2(NOME, SEXO, IDADE, NEWS_LETTER)
VALUES('Pedro Arnaldo', 'M', 1, 1);

INSERT INTO CLIENTE2(NOME, SEXO, IDADE, NEWS_LETTER)
VALUES('Teresa Rosa', 'F', 2, 0);

INSERT INTO CLIENTE2(NOME, SEXO, IDADE, NEWS_LETTER)
VALUES('Rogerio Senna', 'M', 3, 1);

INSERT INTO CLIENTE2(NOME, SEXO, IDADE, NEWS_LETTER)
VALUES('Mirza Roman', 'F', 2, 1);

INSERT INTO CLIENTE2(NOME, SEXO, IDADE, NEWS_LETTER)
VALUES('Sonia Albuquerque', 'F', 3, 1);

INSERT INTO CLIENTE2(NOME, SEXO, IDADE, NEWS_LETTER)
VALUES('Luis Carlos Rosa', 'M', 2, 0);

INSERT INTO CLIENTE2(NOME, SEXO, IDADE, NEWS_LETTER)
VALUES('Vitor Cerqueira', 'M', 1, 1);

INSERT INTO CLIENTE2(NOME, SEXO, IDADE, NEWS_LETTER)
VALUES('Rosana Lima', 'F', 1, 1);

INSERT INTO CLIENTE2(NOME, SEXO, IDADE, NEWS_LETTER)
VALUES('Samuel Santana', 'M', 1, 1);

INSERT INTO CLIENTE2(NOME, SEXO, IDADE, NEWS_LETTER)
VALUES('Paula Monteiro', 'F', 3, 1);

INSERT INTO CLIENTE2(NOME, SEXO, IDADE, NEWS_LETTER)
VALUES('Izabela Moreira', 'F', 2, 0);

INSERT INTO CLIENTE2(NOME, SEXO, IDADE, NEWS_LETTER)
VALUES('Celia Marques', 'F', 3, 1);

INSERT INTO CLIENTE2(NOME, SEXO, IDADE, NEWS_LETTER)
VALUES('Ridualdo Monteiro', 'F', 2, 1);

UPDATE CLIENTE2 SET NOME = UPPER(NOME);

COMMIT;


DROP TABLE CLIENTE2;

CREATE TABLE CLIENTE2
(ID             INT(11) NOT NULL AUTO_INCREMENT,
 NOME           VARCHAR(30) NOT NULL, 
 DATANASC      VARCHAR(30) NOT NULL,
 CONSTRAINT CLIENTE_ID_PK PRIMARY KEY (id)
);

INSERT INTO CLIENTE2(NOME, DATANASC)
VALUES('Jose Roberto', '25/08/1995');

INSERT INTO CLIENTE2(NOME, DATANASC)
VALUES('Joana Rocha', '25/09/1997');

INSERT INTO CLIENTE2(NOME, DATANASC)
VALUES('Vinicius Ribeiro', '05/08/1988');

INSERT INTO CLIENTE2(NOME, DATANASC)
VALUES('Pedro Arnaldo', '27/05/1955');

INSERT INTO CLIENTE2(NOME, DATANASC)
VALUES('Teresa Rosa', '14/12/2000');

INSERT INTO CLIENTE2(NOME, DATANASC)
VALUES('Rogerio Senna', '04/08/1995');

INSERT INTO CLIENTE2(NOME, DATANASC)
VALUES('Mirza Roman', '15/08/1975');

INSERT INTO CLIENTE2(NOME, DATANASC)
VALUES('Sonia Albuquerque', '08/09/1997');

INSERT INTO CLIENTE2(NOME, DATANASC)
VALUES('Luis Carlos Rosa', '14/12/1995');

INSERT INTO CLIENTE2(NOME, DATANASC)
VALUES('Vitor Cerqueira', '09/08/1999');

INSERT INTO CLIENTE2(NOME, DATANASC)
VALUES('Rosana Lima', '18/08/1995');

INSERT INTO CLIENTE2(NOME, DATANASC)
VALUES('Samuel Santana', '04/11/1995');

INSERT INTO CLIENTE2(NOME, DATANASC)
VALUES('Paula Monteiro', '03/06/1998');

INSERT INTO CLIENTE2(NOME, DATANASC)
VALUES('Izabela Moreira', '05/08/2000');

INSERT INTO CLIENTE2(NOME, DATANASC)
VALUES('Celia Marques', '04/06/1995');

INSERT INTO CLIENTE2(NOME, DATANASC)
VALUES('Ridualdo Monteiro', '04/08/1888');

UPDATE CLIENTE2 SET NOME = UPPER(NOME);

COMMIT;

CREATE TABLE CONTA
(ID             INT(11) NOT NULL AUTO_INCREMENT,
 NUMERO         VARCHAR(30) NOT NULL, 
 DATAABR      	VARCHAR(30) NOT NULL,
 CLIENTE        INT(11) NOT NULL,
 CONSTRAINT CONTA_ID_PK PRIMARY KEY (id),
 CONSTRAINT CONTA_CLIENTE_FK FOREIGN KEY (CLIENTE) REFERENCES CLIENTE2(ID)
);
