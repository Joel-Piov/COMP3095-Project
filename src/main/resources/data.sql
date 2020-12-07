INSERT INTO client (id, address, city, country, postal_code, email, first_name, last_login_date, last_name, date_of_birth, password, register_date, role) VALUES
(1, '123 Test Street', 'Toronto', 'Canada', 'B1B 1B1', 'admin@isp.net', 'John', '2020-12-05', 'Doe', '1969-01-01', 'P@ssword1', '2020-12-01', 'Admin');

INSERT INTO client (id, address, city, country, postal_code, email, first_name, last_login_date, last_name, date_of_birth, password, register_date, role) VALUES
(3, '123 Test Street', 'Toronto', 'Canada', 'C1C 1C1', 'DD@isp.net', 'Don', '2020-12-05', 'Draper', '1969-01-01', 'P@ssword1', '2020-12-01', 'Admin');

INSERT INTO client (id, address, city, country, postal_code, email, first_name, last_login_date, last_name, date_of_birth, password, register_date, role) VALUES
(2, '123 Test Street', 'Toronto', 'Canada', 'A1A 1A1', 'client@isp.net', 'Jane', '2020-12-05', 'Smith', '1990-01-01', 'P@ssword1',  '2020-12-01', 'Client');

INSERT INTO profile (id, address, city, client_date_of_birth, client_first_name, client_id, client_last_name, country, email, postal_code, pref_billing, pref_shipping) VALUES
(1, '123 Test Street', 'Toronto','1969-01-01', 'John', 1, 'Doe', 'Canada', 'admin@isp.net', 'B1B 1B1', true, true);

INSERT INTO profile (id, address, city, client_date_of_birth, client_first_name, client_id, client_last_name, country, email, postal_code, pref_billing, pref_shipping) VALUES
(2, '123 Test Street', 'Toronto', '1990-01-01', 'Jane', 2, 'Smith', 'Canada', 'client@isp.net', 'A1A 1A1', true, true);

INSERT INTO profile (id, address, city, client_date_of_birth, client_first_name, client_id, client_last_name, country, email, postal_code, pref_billing, pref_shipping) VALUES
(3, '123 Test Street', 'Toronto', '1969-01-01', 'Don', 3, 'Draper', 'Canada', 'DD@isp.net', 'C1C 1C1', true, true);

INSERT INTO card (id, card_name, card_number, card_type, client_id, exp_code, pref_card) VALUES
(1,'Joel Piovesan', '1234567812345678', 'VISA', 2, '2022-10', false);

INSERT INTO support(id, admin_id, case_code, client_id, date_added, email, first_name, message, subject) VALUES
(1,1,'11220101926',0,'December 06/2020 10:19','client@isp.net', 'Joel', 'We don’t serve their kind here! What? Your droids. They’ll have to wait outside. We don’t want them here. Listen, why don’t you wait out by the speeder. We don’t want any trouble. I heartily agree with you sir.',	'Porknose')
