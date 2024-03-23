--liquibase formatted sql

--changeset brosas:3

CREATE TABLE `accounts` (
    `account_id`      bigint AUTO_INCREMENT PRIMARY KEY,
    `client_id`       bigint        NOT NULL,
    `type`            varchar(45)            DEFAULT NULL,
    `opening_balance` decimal(2, 0) NOT NULL DEFAULT '0',
    `status`          varchar(45)   NOT NULL,
    KEY               `client_id_idx` (`client_id`),
    CONSTRAINT `client_id_fk` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- rollback drop table accounts;