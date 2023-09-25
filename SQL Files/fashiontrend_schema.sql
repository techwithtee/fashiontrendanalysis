-- Drop the database if it exists
DROP DATABASE IF EXISTS fashiontrend_db;

-- Create the database if it doesn't exist
CREATE DATABASE IF NOT EXISTS fashiontrend_db;

-- Use the database
USE fashiontrend_db;

-- Create the category table
CREATE TABLE category (
    category_id INT PRIMARY KEY,
    category_name VARCHAR(255) NOT NULL
);

-- Create the designer table
CREATE TABLE designer (
    designer_id INT PRIMARY KEY,
    designer_name VARCHAR(255) NOT NULL,
    designer_location VARCHAR(255),
    trend_count INT DEFAULT 0,
    popularity_score INT DEFAULT 0
);

-- Create the trend table
CREATE TABLE trend (
    trend_id INT PRIMARY KEY,
    trend_name VARCHAR(255) NOT NULL,
    trend_desc TEXT,
    category_id INT,
    designer_id INT,
    location VARCHAR(255),
    season VARCHAR(255)
);

-- Create the product table
CREATE TABLE product (
    product_id INT PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    category_id INT,
    designer_id INT,
    product_description TEXT
);

-- Create the fashion_user table
CREATE TABLE fashion_user (
    user_id INT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    designer_name VARCHAR(255),
    address TEXT,
    phone VARCHAR(20),
    role VARCHAR(50) DEFAULT 'USER'
);

-- Create the trend_category bridge table
CREATE TABLE trend_category (
    trend_category_id INT PRIMARY KEY,
    trend_id INT,
    category_id INT,
    FOREIGN KEY (trend_id) REFERENCES trend(trend_id),
    FOREIGN KEY (category_id) REFERENCES category(category_id)
);

-- Create the designer_product bridge table
CREATE TABLE designer_product (
    designer_product_id INT PRIMARY KEY,
    designer_id INT,
    product_id INT,
    FOREIGN KEY (designer_id) REFERENCES designer(designer_id),
    FOREIGN KEY (product_id) REFERENCES product(product_id)
);

-- Create the category_popularity table
CREATE TABLE category_popularity (
    category_popularity_id INT PRIMARY KEY,
    category_id INT,
    season VARCHAR(255),
    popularity_score INT,
    FOREIGN KEY (category_id) REFERENCES category(category_id)
);

-- Create the trend_interactions table
CREATE TABLE trend_interactions (
    interaction_id INT PRIMARY KEY,
    user_id INT,
    trend_id INT,
    interaction_type VARCHAR(50),
    timestamp TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES fashion_user(user_id),
    FOREIGN KEY (trend_id) REFERENCES trend(trend_id)
);

-- Create the product_popularity table
CREATE TABLE product_popularity (
    product_popularity_id INT PRIMARY KEY,
    product_id INT,
    trend_id INT,
    popularity_score INT,
    FOREIGN KEY (product_id) REFERENCES product(product_id),
    FOREIGN KEY (trend_id) REFERENCES trend(trend_id)
);

-- Create the trend_popularity table
CREATE TABLE trend_popularity (
    trend_popularity_id INT PRIMARY KEY,
    trend_id INT,
    popularity_score INT,
    FOREIGN KEY (trend_id) REFERENCES trend(trend_id)
);
