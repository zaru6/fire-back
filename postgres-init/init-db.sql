-- set time zone
SET TIME ZONE 'Europe/Zagreb';

-- Create 'models' schema
CREATE SCHEMA models;

-- Create 'users' table
CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  username VARCHAR(100) UNIQUE NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Create 'users' init data
INSERT INTO public.users (username, first_name, last_name, email, "password", created_at, updated_at)
VALUES('admin', 'admin', 'admin', 'admin@fireappadmin.com', '$2a$10$DfcdfP.HWJgE/ZYQ1.IDiOwhNu8GNkjsyu7deAqYM3/YZMIEKgjGK', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO public.users
(username, first_name, last_name, email, "password", created_at, updated_at)
VALUES('johndoe', 'John', 'Doe', 'johndoe@sthgwtf.com', '$2a$10$USDDHVYh6gXERCDn3IMDJOgpwTWFqiKsq2BLRTMl2Czz5tiOjXRCi', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO public.users (username, first_name, last_name, email, "password", created_at, updated_at)
VALUES('test', 'test', 'test', 'test@somedomain.com', '$2a$10$p8Ey2ZkPwZB3A92u9a4CCOmY6XfQ.ssONyCOLTX97v1iohsKVWYU.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO public.users (username, first_name, last_name, email, "password", created_at, updated_at)
VALUES('user', 'user', 'user', 'user@maildom.com', '$2a$10$C2o/2vgPfSk1pSRx3W2kUe3VMLkO.GNKzMJbUVAcxKWAGh4XC6po.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Create 'categories' table
CREATE TABLE models.categories (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  label VARCHAR(255) NOT NULL
);

-- Create 'categories' init data
INSERT INTO models.categories (name, label) VALUES ('groceries', 'Groceries');
INSERT INTO models.categories (name, label) VALUES ('musical_instruments', 'Musical  Instruments');
INSERT INTO models.categories (name, label) VALUES ('chemistry', 'Chemistry');
INSERT INTO models.categories (name, label) VALUES ('appliances', 'Appliances');
INSERT INTO models.categories (name, label) VALUES ('misc', 'Misc');

-- Create 'subcategories' table
CREATE TABLE models.subcategories (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  label VARCHAR(255) NOT NULL,
  category_id INTEGER
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
VALUES('Salad', 23, (select id from models.subcategories where name='vegetables_groceries'), true, (select id from users where username='johndoe'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO models.products
(name, price, subcategory_id, available, created_by, created_at, updated_at)
VALUES('Tomato', 32, (select id from models.subcategories where name='vegetables_groceries'), true, (select id from users where username='johndoe'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO models.products
(name, price, subcategory_id, available, created_by, created_at, updated_at)
VALUES('Paprika', 32, (select id from models.subcategories where name='vegetables_groceries'), true, (select id from users where username='johndoe'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO models.products
(name, price, subcategory_id, available, created_by, created_at, updated_at)
VALUES('Pastrva', 32, (select id from models.subcategories where name='meat_groceries'), true, (select id from users where username='johndoe'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO models.products
(name, price, subcategory_id, available, created_by, created_at, updated_at)
VALUES('Pršut', 32, (select id from models.subcategories where name='meat_groceries'), true, (select id from users where username='johndoe'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO models.products
(name, price, subcategory_id, available, created_by, created_at, updated_at)
VALUES('Ibanez Prestige', 1500, (select id from models.subcategories where name='cleaning_appliances'), true, (select id from users where username='johndoe'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO models.products
(name, price, subcategory_id, available, created_by, created_at, updated_at)
VALUES('Sonor drum set', 1500, (select id from models.subcategories where name='drums_and_percussion_musical_instruments'), true, (select id from users where username='johndoe'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO models.products
(name, price, subcategory_id, available, created_by, created_at, updated_at)
VALUES('Gorenje washing machine', 2000, (select id from models.subcategories where name='drums_and_percussion_musical_instruments'), true, (select id from users where username='johndoe'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO models.products
(name, price, subcategory_id, available, created_by, created_at, updated_at)
VALUES('Old spice lagoon', 2.5, (select id from models.subcategories where name='deodorants_chemistry'), true, (select id from users where username='johndoe'), CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Create 'topic' table
CREATE TABLE models.topic (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255),
    description VARCHAR(255),
    created_by INTEGER,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO models.topic (title, description, created_by, created_at, updated_at)
VALUES('Fire App. Why?', 'Here we discuss the need for the app.', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO models.topic (title, description, created_by, created_at, updated_at)
VALUES('The meaning of life and it''s fallacies', 'As the title says. I had a strange deja vu while writing this description.', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO models.topic (title, description, created_by, created_at, updated_at)
VALUES('Nothing special', 'Rubbish. Jus sum rubbish ffs', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Create 'topic_reply' table
CREATE TABLE models.topic_reply (
    id SERIAL PRIMARY KEY,
    reply_text VARCHAR(255),
    topic_id INTEGER,
    reply_order INTEGER,
    created_by INTEGER,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO models.topic_reply (reply_text, topic_id, reply_order, created_by, created_at, updated_at)
VALUES('First reply. I don''t even care.', 1, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO models.topic_reply (reply_text, topic_id, reply_order, created_by, created_at, updated_at)
VALUES('Second reply. Life is meaningless.', 1, 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO models.topic_reply (reply_text, topic_id, reply_order, created_by, created_at, updated_at)
VALUES('Black and void. Life devoid', 1, 3, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO models.topic_reply (reply_text, topic_id, reply_order, created_by, created_at, updated_at)
VALUES('DIS SUM BUUUULSHIII', 2, 1, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO models.topic_reply (reply_text, topic_id, reply_order, created_by, created_at, updated_at)
VALUES('Nah', 2, 2, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
VALUES('I give up.', 2, 3, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO models.topic_reply (reply_text, topic_id, reply_order, created_by, created_at, updated_at)
VALUES('wierd.', 2, 4, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO models.topic_reply (reply_text, topic_id, reply_order, created_by, created_at, updated_at)
VALUES('splendid', 3, 1, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO models.topic_reply (reply_text, topic_id, reply_order, created_by, created_at, updated_at)
VALUES('Good for you, kind sir', 3, 2, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)






