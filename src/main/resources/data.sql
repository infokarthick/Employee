INSERT INTO EMPLOYEE(name, department) VALUES ('Lokesh', 'electrical');

INSERT INTO ADDRESS (city, postal_code, employee_id) VALUES ('Brussels',1050, (SELECT id FROM EMPLOYEE WHERE name = 'Lokesh'));
INSERT INTO ADDRESS (city, postal_code, employee_id) VALUES ('Antwerb',2502, (SELECT id FROM EMPLOYEE WHERE name = 'Lokesh'));

INSERT INTO EMPLOYEE(name, department) VALUES ('Johan', 'communication');
INSERT INTO ADDRESS (city, postal_code, employee_id) VALUES ('Paris',8050, (SELECT id FROM EMPLOYEE WHERE name = 'Johan'));

INSERT INTO EMPLOYEE(name, department) VALUES ('marie', 'professor');
INSERT INTO ADDRESS (city, postal_code, employee_id) VALUES ('Berlin',10899, (SELECT id FROM EMPLOYEE WHERE name = 'marie'));