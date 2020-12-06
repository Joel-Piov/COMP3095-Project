INSERT INTO client (id, address, city, country, postal_code, email, first_name, last_name, dob, password, role) VALUES
(1, '123 Test Street', 'Toronto', 'Canada', 'A1A 1A1', 'admin@isp.net', 'John', 'Doe', '1969-01-01', 'P@ssword1', 'Admin');

INSERT INTO client (id, address, city, country, postal_code, email, first_name, last_name, dob, password, role) VALUES
(2, '123 Test Street', 'Toronto', 'Canada', 'A1A 1A1', 'client@isp.net', 'Jane', 'Smith','1990-01-01', 'P@ssword1',  'Client');

INSERT INTO card (id, card_name, card_number, card_type, client_id, exp_code, pref_card) VALUES
(1,'Joel Piovesan', '1234567812345678', 'VISA', 2, '2022-10', false);