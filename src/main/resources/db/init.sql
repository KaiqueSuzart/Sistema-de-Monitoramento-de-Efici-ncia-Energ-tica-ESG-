-- Criar usuário e conceder privilégios
ALTER SESSION SET CONTAINER = ORCLPDB1;

-- Criar usuário
CREATE USER esg_user IDENTIFIED BY esg_password
    DEFAULT TABLESPACE USERS
    TEMPORARY TABLESPACE TEMP
    QUOTA UNLIMITED ON USERS;

-- Conceder privilégios necessários
GRANT CREATE SESSION TO esg_user;
GRANT CREATE TABLE TO esg_user;
GRANT CREATE VIEW TO esg_user;
GRANT CREATE SEQUENCE TO esg_user;
GRANT CREATE PROCEDURE TO esg_user;
GRANT CREATE TRIGGER TO esg_user;

-- Privilégios adicionais para Flyway
GRANT CREATE ANY TABLE TO esg_user;
GRANT DROP ANY TABLE TO esg_user;
GRANT ALTER ANY TABLE TO esg_user;
GRANT SELECT ANY TABLE TO esg_user;
GRANT INSERT ANY TABLE TO esg_user;
GRANT UPDATE ANY TABLE TO esg_user;
GRANT DELETE ANY TABLE TO esg_user;
GRANT CREATE ANY INDEX TO esg_user;
GRANT DROP ANY INDEX TO esg_user;
GRANT ALTER ANY INDEX TO esg_user;
GRANT CREATE ANY SEQUENCE TO esg_user;
GRANT SELECT ANY SEQUENCE TO esg_user;
GRANT CREATE ANY TRIGGER TO esg_user;
GRANT DROP ANY TRIGGER TO esg_user;
GRANT ALTER ANY TRIGGER TO esg_user;