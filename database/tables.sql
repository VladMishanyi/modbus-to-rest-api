DROP DATABASE IF EXISTS db_database;
CREATE DATABASE IF NOT EXISTS db_database CHARSET = utf8 COLLATE = utf8_general_ci;
USE db_database;

/*----------------------------------------------------------------------------------*/
DROP TABLE IF EXISTS trm251;
CREATE TABLE trm251 (
                        id              int UNSIGNED NOT NULL AUTO_INCREMENT,
                        date            TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        inputRegister0  FLOAT        NOT NULL DEFAULT 0,
                        inputRegister1  FLOAT        NOT NULL DEFAULT 0,
                        PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;

SELECT * FROM trm251 ORDER BY date DESC LIMIT 1