-- set time zone
SET TIME ZONE 'Europe/Zagreb';

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
CREATE SEQUENCE users_id_seq;
CREATE TABLE users (
  id INTEGER NOT NULL DEFAULT nextval('users_id_seq'),
  full_name VARCHAR(255) NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
--  CONSTRAINT chk_password CHECK (password ~ '^[a-zA-Z0-9!@#$%^&*(),.?":{}|<>]+$'),
  CONSTRAINT pk_users PRIMARY KEY (id)
);

-- Create 'users' init data
INSERT INTO users (full_name, email, password, created_at, updated_at)
VALUES ('John Doe', 'johndoe@example.com', 'mypassword', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

