INSERT INTO users (user_id, first_name, last_name, username, password) VALUES(1, 'Hektor', 'Attaway', 'hektorattaway', 'hekspassword');
INSERT INTO users (user_id, first_name, last_name, username, password) VALUES(2, 'Torbjorg', 'Maynard', 'torbjorgmaynard', 'torbspassword');

INSERT INTO movies (movie_id, name, price) VALUES(1, 'First Movie', 10.00);
INSERT INTO movies (movie_id, name, price) VALUES(2, 'Second Movie', 9.55);
INSERT INTO movies (movie_id, name, price) VALUES(3, 'Third Movie', 15.30);
INSERT INTO movies (movie_id, name, price) VALUES(4, 'Fourth Movie', 24.67);

INSERT INTO orders (order_id, user_id, movie_id, price) VALUES(1, 1, 3, 15.30);
INSERT INTO orders (order_id, user_id, movie_id, price) VALUES(2, 1, 2, 9.55);