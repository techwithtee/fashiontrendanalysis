-- Insert data into the designer table
INSERT INTO designer (designer_id, designer_name) VALUES 
(1, 'Daniel Lee');

-- Insert data into the category table
INSERT INTO category (category_id, category_name) VALUES 
(1, 'British Summer');

-- Insert data into the trend table
INSERT INTO trend (trend_id, trend_name, trend_desc, category_id, designer_id, location) VALUES 
(1, 'Burberry', 'A celebration of British summer with iconic Burberry check prints, summer blooms, and classic trench updates.', 1, 1, 'New York');

-- Insert data into the product table
INSERT INTO product (product_id, product_name, category_id, designer_id, product_description) VALUES 
(1, 'Burberry Spring/Summer 2024 Collection', 1, 1, 'A celebration of British summer with iconic Burberry check prints, summer blooms, and classic trench updates.');

-- Insert data into the user table
INSERT INTO fashion_user (user_id, username, email, password_hash, designer_name, address, phone) VALUES 
(1001, 'ASOSUser', 'user@asos.com', 'hashedASOSpassword2023', 'ASOS', 'User', '1995-06-15', 'Male', '123 Fashion St, London, UK', '+44123456789');
