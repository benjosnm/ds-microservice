INSERT INTO persons (first_name, last_name, gender, birth_date, address, phone)
VALUES
 ('Jhon', 'Potter', 'Male', '1985-02-16', 'St. A 13', '111111'),
 ('Mary', 'Potter', 'Female', '1985-04-16', 'St. A 13', '222222');

INSERT INTO clientEntities (person_id, pwd, status)
VALUES
 (1, '1234', 'ACTIVE'),
 (2, '1234', 'INACTIVE');
