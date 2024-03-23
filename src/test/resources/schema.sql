CREATE TABLE `persons`
(
    `person_id`  bigint AUTO_INCREMENT PRIMARY KEY,
    `first_name` varchar(100) NOT NULL,
    `last_name`  varchar(100) NOT NULL,
    `gender`     varchar(15) DEFAULT NULL,
    `birth_date` timestamp(6) NOT NULL,
    `address`    varchar(150) NOT NULL,
    `phone`      varchar(100) NOT NULL
);

CREATE TABLE `clientEntities`
(
    `client_id` bigint AUTO_INCREMENT PRIMARY KEY,
    `person_id` bigint      NOT NULL,
    `pwd`       varchar(100) DEFAULT NULL,
    `status`    varchar(45) NOT NULL,
    CONSTRAINT `person_id_fk` FOREIGN KEY (`person_id`) REFERENCES `persons` (`person_id`)
);