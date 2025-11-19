DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM roles;

INSERT INTO roles (name, description) VALUES ('USER', 'user');
INSERT INTO roles (name, description) VALUES ('ACCOUNTANT', 'accountant');
INSERT INTO roles (name, description) VALUES ('ADMIN', 'admin');


INSERT INTO users (name, username, password, last_login_date) VALUES

--pw: user
( 'Normál User', 'user','$2a$12$WqTxdcoWaTC0dBjGPKVFS.Yfd4LyG7rVcmcH0u8MLMTRw/CQnU22C',CURRENT_DATE),


--pw: accountant
( 'Könyvelő User', 'accountant','$2a$12$B7LaKLojRvikbjo7XnjH9.BsmDD7AJfAogUuiqbtxh3/RiX/mPEbe',CURRENT_DATE),

--pw: admin
( 'Admin User', 'admin','$2a$12$gfVB2YAG/CV9ZNwuqWh8AeaiA9/CjGNtCIvkc7EtJeEKRAZmQ//Uq',CURRENT_DATE);

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);


-- accountant → ACCOUNTANT
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2);

-- admin → ADMIN + USER + ACCOUNTANT
INSERT INTO user_roles (user_id, role_id) VALUES (3, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (3, 2);
INSERT INTO user_roles (user_id, role_id) VALUES (3, 3);

