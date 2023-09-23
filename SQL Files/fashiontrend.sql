-- Create the database if it doesn't exist
CREATE DATABASE IF NOT EXISTS fashion_trend_db;

-- Use the database
USE fashion_trend_db;

-- Create the category table
CREATE TABLE category (
    category_id INT PRIMARY KEY,
    category_name VARCHAR(255) NOT NULL
);

-- Create the designer table
CREATE TABLE designer (
    designer_id INT PRIMARY KEY,
    designer_name VARCHAR(255) NOT NULL,
    designer_country VARCHAR(255),
    designer_desc TEXT
);

-- Create the trend table
CREATE TABLE trend (
    trend_id INT PRIMARY KEY,
    trend_name VARCHAR(255) NOT NULL,
    trend_desc TEXT,
    category_id INT,
    designer_id INT,
    location VARCHAR(255),
    season VARCHAR(255));
    /**
    
    colours JSON,    -- Store colors as JSON array
    fabrics JSON,   -- Store fabrics as JSON array
    styles JSON,    -- Store styles as JSON array
    themes JSON     -- Store themes as JSON array
    */

-- Create the product table
CREATE TABLE product (
    product_id INT PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    category_id INT,
    designer_id INT,
    product_description TEXT);
    
    /**price DECIMAL(10, 2)-- Adjust precision and scale as needed
    colour VARCHAR(255),
    fabric VARCHAR(255),
    style VARCHAR(255)*/

-- Create the user table
CREATE TABLE fashion_user (
    user_id INT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    designer_name VARCHAR(255),
    address TEXT,
    phone VARCHAR(20)
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

