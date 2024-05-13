-- Create 'models' schema
CREATE SCHEMA models;

-- Create 'products' table
CREATE TABLE models.products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    price INTEGER,
    available BOOLEAN
);

-- Create 'products' init data
INSERT INTO models.products
(id, "name", price, available)
VALUES(1, 'salad', 23, true);
INSERT INTO models.products
(id, "name", price, available)
VALUES(2, 'tomato', 32, true);
INSERT INTO models.products
(id, "name", price, available)
VALUES(3, 'paprika', 32, true);
INSERT INTO models.products
(id, "name", price, available)
VALUES(4, 'pastrva', 32, true);
INSERT INTO models.products
(id, "name", price, available)
VALUES(5, 'pršut', 32, true);

-- Create 'users' table
CREATE TABLE models.users (
    id SERIAL PRIMARY KEY,
    login VARCHAR(255) UNIQUE,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    password VARCHAR(255)
);

-- Create 'users' init data
INSERT INTO models.users
(id, login, "password")
VALUES(1, 'user1', '1234');
INSERT INTO models.users
(id, login, "password")
VALUES(2, 'user2', '1234');

