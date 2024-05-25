-- set time zone
SET TIME ZONE 'Europe/Zagreb';

-- Create 'models' schema
CREATE SCHEMA models;

-- Create 'users' table
CREATE SEQUENCE users_id_seq;
CREATE TABLE users (
  id INTEGER NOT NULL DEFAULT nextval('users_id_seq'),
  username VARCHAR(100) UNIQUE NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
--  CONSTRAINT chk_password CHECK (password ~ '^[a-zA-Z0-9!@#$%^&*(),.?':{}|<>]+$'),
  CONSTRAINT pk_users PRIMARY KEY (id)
);

-- Create 'users' init data
INSERT INTO users (username, first_name, last_name, email, password, created_at, updated_at)
VALUES ('johndoe', 'John', 'Doe', 'johndoe@example.com', 'mypassword', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Create 'categories' table
CREATE SEQUENCE categories_id_seq;
CREATE TABLE models.categories (
  id INTEGER NOT NULL DEFAULT nextval('categories_id_seq'),
  name VARCHAR(255) NOT NULL,
  label VARCHAR(255) NOT NULL,
  CONSTRAINT pk_categories PRIMARY KEY (id)
);

-- Create 'categories' init data
INSERT INTO models.categories (name, label) VALUES ('groceries', 'Groceries');
INSERT INTO models.categories (name, label) VALUES ('musical_instruments', 'Musical  Instruments');
INSERT INTO models.categories (name, label) VALUES ('chemistry', 'Chemistry');
INSERT INTO models.categories (name, label) VALUES ('appliances', 'Appliances');
INSERT INTO models.categories (name, label) VALUES ('misc', 'Misc');

-- Create 'subcategories' table
CREATE SEQUENCE subcategories_id_seq;
CREATE TABLE models.subcategories (
  id INTEGER NOT NULL DEFAULT nextval('subcategories_id_seq'),
  name VARCHAR(255) NOT NULL,
  label VARCHAR(255) NOT NULL,
  category_id INTEGER,
  CONSTRAINT pk_subcategories PRIMARY KEY (id)
);

-- Create 'subcategories' init data
INSERT INTO models.subcategories (name, label, category_id) VALUES ('fruits_groceries', 'Fruits', (select id from models.categories where name='groceries' ));
INSERT INTO models.subcategories (name, label, category_id) VALUES ('vegetables_groceries', 'Vegetables', (select id from models.categories where name='groceries'));
INSERT INTO models.subcategories (name, label, category_id) VALUES ('meat_groceries', 'Meat', (select id from models.categories where name='groceries'));
INSERT INTO models.subcategories (name, label, category_id) VALUES ('electric_guitars_musical_instruments', 'Electric Guitars', (select id from models.categories where name='musical_instruments'));
INSERT INTO models.subcategories (name, label, category_id) VALUES ('acoustic_guitars_musical_instruments', 'Acoustic Guitars', (select id from models.categories where name='musical_instruments'));
INSERT INTO models.subcategories (name, label, category_id) VALUES ('drums_and_percussion_musical_instruments', 'Drums And Percussion', (select id from models.categories where name='musical_instruments'));
INSERT INTO models.subcategories (name, label, category_id) VALUES ('deodorants_chemistry', 'Deodorants', (select id from models.categories where name='chemistry'));
INSERT INTO models.subcategories (name, label, category_id) VALUES ('cleaning_products_chemistry', 'Cleaning Products', (select id from models.categories where name='chemistry'));
INSERT INTO models.subcategories (name, label, category_id) VALUES ('hygiene_chemistry', 'Hygiene', (select id from models.categories where name='chemistry'));
INSERT INTO models.subcategories (name, label, category_id) VALUES ('cooking_appliances', 'Cooking', (select id from models.categories where name='appliances'));
INSERT INTO models.subcategories (name, label, category_id) VALUES ('cleaning_appliances', 'Cleaning', (select id from models.categories where name='appliances'));

-- Create 'products' table
CREATE TABLE models.products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    price INTEGER,
    subcategory_id INTEGER,
    available BOOLEAN,
    created_by INTEGER,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Create 'products' init data
INSERT INTO models.products
(name, price, subcategory_id, available, created_by, created_at, updated_at)
VALUES('Salad', 23, (select id from models.subcategories where name='vegetables_groceries'), true, (select id from public.users where username='johndoe'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO models.products
(name, price, subcategory_id, available, created_by, created_at, updated_at)
VALUES('Tomato', 32, (select id from models.subcategories where name='vegetables_groceries'), true, (select id from public.users where username='johndoe'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO models.products
(name, price, subcategory_id, available, created_by, created_at, updated_at)
VALUES('Paprika', 32, (select id from models.subcategories where name='vegetables_groceries'), true, (select id from public.users where username='johndoe'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO models.products
(name, price, subcategory_id, available, created_by, created_at, updated_at)
VALUES('Pastrva', 32, (select id from models.subcategories where name='meat_groceries'), true, (select id from public.users where username='johndoe'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO models.products
(name, price, subcategory_id, available, created_by, created_at, updated_at)
VALUES('Pršut', 32, (select id from models.subcategories where name='meat_groceries'), true, (select id from public.users where username='johndoe'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO models.products
(name, price, subcategory_id, available, created_by, created_at, updated_at)
VALUES('Ibanez Prestige', 1500, (select id from models.subcategories where name='cleaning_appliances'), true, (select id from public.users where username='johndoe'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO models.products
(name, price, subcategory_id, available, created_by, created_at, updated_at)
VALUES('Sonor drum set', 1500, (select id from models.subcategories where name='drums_and_percussion_musical_instruments'), true, (select id from public.users where username='johndoe'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO models.products
(name, price, subcategory_id, available, created_by, created_at, updated_at)
VALUES('Gorenje washing machine', 2000, (select id from models.subcategories where name='drums_and_percussion_musical_instruments'), true, (select id from public.users where username='johndoe'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO models.products
(name, price, subcategory_id, available, created_by, created_at, updated_at)
VALUES('Old spice lagoon', 2.5, (select id from models.subcategories where name='deodorants_chemistry'), true, (select id from public.users where username='johndoe'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);








