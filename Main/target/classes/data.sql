BEGIN;
CREATE TABLE IF NOT EXISTS testdb.roles;
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');
INSERT INTO users(id, email, name, password, username) VALUES(1 ,'admin@admin.com','Admin','iskratel', 'admin');
COMMIT;
