-- Insert data into the designer table
INSERT INTO designer (designer_id, designer_name) VALUES 
(1, 'Burberry');

-- Insert data into the category table
INSERT INTO category (category_id, category_name) VALUES 
(1, 'Outerwear');

-- Insert data into the trend table
INSERT INTO trend (trend_id, trend_name, trend_desc, category_id, designer_id, location, season) VALUES 
(1, 'British Summer', 'A celebration of British summer with iconic Burberry check prints, summer blooms, and classic trench updates.', 1, 1, 'New York', 'SS24');

-- Insert data into the product table
INSERT INTO product (product_id, product_name, category_id, designer_id, product_description) VALUES 
(1, 'Checkered Tranch Coat', 1, 1, 'Classic Burberry checkered trench coat');

-- Insert data into the user table
INSERT INTO fashion_user (user_id, username, email, password_hash, designer_name, address, phone) VALUES 
(1001, 'ASOSUser', 'user@asos.com', 'hashedASOSpassword2023', 'ASOS', '123 Fashion St, London, UK', '+44123456789');
