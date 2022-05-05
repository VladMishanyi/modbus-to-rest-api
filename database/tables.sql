DROP DATABASE IF EXISTS db_database;
CREATE DATABASE IF NOT EXISTS db_database CHARSET = utf8 COLLATE = utf8_general_ci;
USE db_database;

/*----------------------------------------------------------------------------------*/
DROP TABLE IF EXISTS modbus;
CREATE TABLE modbus (
                        db_id             INT UNSIGNED   NOT NULL AUTO_INCREMENT,
                        db_date           TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        db_address        INT            NOT NULL DEFAULT 0,
                        db_register       INT            NOT NULL DEFAULT 0,
                        db_write          BOOL           NOT NULL DEFAULT FALSE,
                        db_archive        BOOL           NOT NULL DEFAULT FALSE,
                        db_hysteresis     VARCHAR (255)  NOT NULL DEFAULT '1',
                        db_value          VARCHAR (255)  NOT NULL DEFAULT '0',
                        db_register_range INT            NOT NULL DEFAULT 0,
                        db_offset         INT            NOT NULL DEFAULT 0,
                        db_data_type      INT            NOT NULL DEFAULT 0,
                        PRIMARY KEY (db_id)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8;
