CREATE USER 'postgre'@'localhost' IDENTIFIED BY '1234';

CREATE DATABASE app;

GRANT ALL PRIVILEGES ON app.* TO 'postgree'@'localhost';

FLUSH PRIVILEGES;


