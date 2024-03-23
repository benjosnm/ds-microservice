--liquibase formatted sql

--changeset brosas:4

CREATE TABLE `transactions` (
    `transaction_id` bigint        NOT NULL AUTO_INCREMENT,
    `date`           datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `type`           varchar(45)   NOT NULL,
    `amount`         decimal(2, 0) NOT NULL,
    `balance`        decimal(2, 0) NOT NULL,
    `account_id`     int           NOT NULL,
    KEY              `account_id_idx` (`account_id`),
    CONSTRAINT `account_id_fk` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- rollback drop table transactions;