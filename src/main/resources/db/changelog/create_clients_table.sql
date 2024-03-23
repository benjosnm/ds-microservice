--liquibase formatted sql

--changeset brosas:2

CREATE TABLE `clients` (
  `client_id` bigint AUTO_INCREMENT PRIMARY KEY,
  `person_id` bigint NOT NULL,
  `pwd` varchar(100) DEFAULT NULL,
  `status` varchar(45) NOT NULL,
  KEY `person_id_fk_idx` (`person_id`),
  CONSTRAINT `person_id_fk` FOREIGN KEY (`person_id`) REFERENCES `persons` (`person_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- rollback drop table clients;