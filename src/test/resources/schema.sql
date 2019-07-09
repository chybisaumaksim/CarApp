CREATE SCHEMA IF NOT EXISTS CARDB;

USE CARDB;

CREATE TABLE IF NOT EXISTS CAR
(
    carId  numeric auto_increment,
    model  varchar(20),
    colour varchar (12)
);

INSERT INTO CAR VALUES (1, 'F4', 'RED');