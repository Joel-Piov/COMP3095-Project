INSERT INTO client (id, address, city, country, postal_code, email, first_name, last_name, dob, password, role) VALUES
(1, '123 Test Street', 'Toronto', 'Canada', 'B1B 1B1', 'admin@isp.net', 'John', 'Doe', '1969-01-01', 'P@ssword1', 'Admin');

INSERT INTO client (id, address, city, country, postal_code, email, first_name, last_name, dob, password, role) VALUES
(3, '123 Test Street', 'Toronto', 'Canada', 'B1B 1B1', 'DD@isp.net', 'Don', 'Draper', '1969-01-01', 'P@ssword1', 'Admin');

INSERT INTO profile (id, address, city, client_first_name, client_id, client_last_name, country,  email, postal_code, pref_billing, pref_shipping) VALUES
(1, '123 Test Street', 'Toronto', 'John', 1, 'Doe', 'Canada', 'admin@isp.net', 'A1A 1A1', true, true);

INSERT INTO client (id, address, city, country, postal_code, email, first_name, last_name, dob, password, role) VALUES
(2, '123 Test Street', 'Toronto', 'Canada', 'A1A 1A1', 'client@isp.net', 'Jane', 'Smith','1990-01-01', 'P@ssword1',  'Client');

INSERT INTO profile (id, address, city, client_first_name, client_id, client_last_name, country,  email, postal_code, pref_billing, pref_shipping) VALUES
(2, '123 Test Street', 'Toronto', 'Jane', 2, 'Smith', 'Canada', 'client@isp.net', 'A1A 1A1', true, true);

INSERT INTO card (id, card_name, card_number, card_type, client_id, exp_code, pref_card) VALUES
(1,'Joel Piovesan', '1234567812345678', 'VISA', 2, '2022-10', false);

