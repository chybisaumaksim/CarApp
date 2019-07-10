CREATE SCHEMA IF NOT EXISTS CARDB;

USE CARDB;

CREATE TABLE IF NOT EXISTS CAR
(
    carId  numeric auto_increment,
    model  varchar(20),
    colour varchar (12)
);

INSERT into CAR values (1, 'A4', 'RED')