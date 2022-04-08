DROP DATABASE IF EXISTS movies;
CREATE DATABASE movies;

USE movies;

DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS movies;

CREATE TABLE movies (
    movie_id int PRIMARY KEY AUTO_INCREMENT,
    name varchar(60) NOT NULL,
    price decimal(4,2) NOT NULL
);

CREATE TABLE users (
   user_id int PRIMARY KEY AUTO_INCREMENT,
   first_name varchar(60) NOT NULL,
   last_name varchar(60) NOT NULL,
   username varchar(60) NOT NULL,
   password varchar(255) NOT NULL
);

CREATE TABLE orders (
    order_id int PRIMARY KEY AUTO_INCREMENT,
    user_id int NOT NULL,
    movie_id int NOT NULL,
    price decimal(4,2) NOT NULL,
    FOREIGN KEY (movie_id)
        REFERENCES movies (movie_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (user_id)
        REFERENCES users (user_id)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);
