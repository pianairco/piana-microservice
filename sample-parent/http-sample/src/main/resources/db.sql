DROP DATABASE IF EXISTS qp;
CREATE DATABASE qp;
ALTER DATABASE qp CHARACTER SET utf8 COLLATE utf8_general_ci;
use qp;

DROP TABLE IF EXISTS principal;
create table principal (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  type INT default 0,
  username VARCHAR(128),
  password VARCHAR(128),
  roles VARCHAR(1024)
);
ALTER TABLE principal CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci;

-- DROP TABLE IF EXISTS user;
-- create table user (
--   id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
--   principal_id INT NOT NULL,
--   username VARCHAR(128),
--   password VARCHAR(128),
--   roles VARCHAR(1024),
--   constraint user_principal_id foreign key (principal_id) REFERENCES principal (id)
-- );
-- ALTER TABLE user CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci;

DROP TABLE IF EXISTS user_address;
create table user_address (
  id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  principal_id INT NOT NULL,
  address VARCHAR(128),
  constraint user_address_principal_id foreign key (principal_id) REFERENCES principal (id)
);
ALTER TABLE user_address CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci;

INSERT INTO principal (username, password, roles) VALUES
        ('admin1', '123', 'admin,user'),
        ('user1', '123', 'user'),
        ('user2', '123', 'customer');

INSERT INTO user_address (principal_id, address) VALUES
        (1, 'tehran');

-- INSERT INTO user (principal_id, username, password, roles) VALUES
--         (1, 'admin', '123', 'admin,user'),
--         (2, 'user1', '123', 'user'),
--         (3, 'user2', '123', 'customer');
