-- Insert data into the category table
INSERT INTO category (category_id, category_name) VALUES
(1, 'Tops'),
(2, 'Bottoms'),
(3, 'Outerwear'),
(4, 'Dresses'),
(5, 'Activewear'),
(6, 'Formalwear'),
(7, 'Footwear'),
(8, 'Accessories');

-- Insert data into the designer table
INSERT INTO designer (designer_id, designer_name, designer_location) VALUES
(1, 'Designer A', 'New York'),
(2, 'Designer B', 'Paris'),
(3, 'Designer C', 'Milan'),
(4, 'Designer D', 'London'),
(5, 'Designer E', 'Los Angeles'),
(6, 'Designer F', 'Tokyo'),
(7, 'Designer G', 'Paris'),
(8, 'Designer H', 'Milan'),
(9, 'Designer I', 'New York'),
(10, 'Designer J', 'Seoul'),
(11, 'Designer K', 'Shanghai'),
(12, 'Designer L', 'Sydney'),
(13, 'Designer M', 'Barcelona'),
(14, 'Designer N', 'Berlin'),
(15, 'Designer O', 'Rio de Janeiro');

-- Insert data into the trend table
INSERT INTO trend (trend_id, trend_name, trend_desc, category_id, designer_id, location, season) VALUES
(1, 'Parisian Chic', 'Elegant fashion inspired by Paris', 3, 4, 'Paris', 'Year-round'),
(2, 'Milanese Style', 'Sophisticated Milanese fashion', 3, 5, 'Milan', 'Year-round'),
(3, 'New York Streetwear', 'Urban streetwear trends in New York', 1, 6, 'New York', 'Year-round'),
(4, 'London Outerwear', 'Stylish outerwear from London', 3, 7, 'London', 'Fall'),
(5, 'Tokyo Fashion', 'Trendy Tokyo fashion for all seasons', 3, 8, 'Tokyo', 'Year-round'),
(6, 'Parisian Hats', 'Chic hats inspired by Paris', 8, 9, 'Paris', 'Year-round'),
(7, 'Milan Scarves', 'Luxurious scarves from Milan', 8, 10, 'Milan', 'Winter'),
(8, 'London Socks', 'Colorful London socks for any outfit', 1, 11, 'London', 'Year-round'),
(9, 'Tokyo Eyewear', 'Fashionable eyewear from Tokyo', 8, 12, 'Tokyo', 'Year-round'),
(10, 'Parisian Watches', 'Elegant watches with a Parisian touch', 3, 13, 'Paris', 'Year-round'),
(11, 'Milanese Belts', 'Stylish belts from Milan', 2, 14, 'Milan', 'Year-round'),
(12, 'New York Gloves', 'Gloves for New York\'s changing seasons', 3, 15, 'New York', 'Year-round'),
(13, 'Seoul Street Fashion', 'Trendy street fashion from Seoul', 1, 10, 'Seoul', 'Year-round'),
(14, 'Shanghai Chic', 'Chic fashion trends from Shanghai', 3, 11, 'Shanghai', 'Year-round'),
(15, 'Rio Beachwear', 'Stylish beachwear from Rio de Janeiro', 4, 15, 'Rio de Janeiro', 'Summer');

-- Insert data into the product table
INSERT INTO product (product_id, product_name, category_id, designer_id, product_description) VALUES
(1, 'Floral Dress', 4, 1, 'A beautiful floral dress for spring'),
(2, 'Denim Jacket', 3, 2, 'A stylish denim jacket for fall'),
(3, 'Sunglasses', 8, 3, 'Designer sunglasses for the summer'),
(4, 'Leather Bag', 8, 4, 'Elegant leather bag for any occasion'),
(5, 'High Heels', 7, 5, 'Fashionable high heels for a night out'),
(6, 'Men\'s Suit', 6, 6, 'Classic men\'s suit for formal events'),
(7, 'Winter Coat', 3, 7, 'Warm winter coat for chilly days'),
(8, 'Summer Dress', 4, 8, 'Light and breezy summer dress'),
(9, 'Casual Shirt', 1, 9, 'Comfortable and stylish casual shirt'),
(10, 'Designer Belt', 8, 10, 'Trendy designer belt to complete your look'),
(11, 'Sneakers', 7, 11, 'Stylish sneakers for everyday wear'),
(12, 'Beach Hat', 8, 12, 'A fashionable beach hat for sunny days'),
(13, 'Winter Boots', 7, 13, 'Warm winter boots for cold weather'),
(14, 'Formal Gown', 6, 14, 'Elegant formal gown for special occasions'),
(15, 'Summer Shorts', 2, 15, 'Cool and comfortable summer shorts');

-- Insert data into the fashion_user table
INSERT INTO fashion_user (user_id, username, email, password_hash, designer_name, address, phone, role) VALUES
(1, 'user1', 'user1@example.com', 'hashed_password_1', NULL, '123 Main St, City', '123-456-7890', 'USER'),
(2, 'designer1', 'designer1@example.com', 'hashed_password_2', 'Designer A', '456 Elm St, Town', '987-654-3210', 'DESIGNER'),
(3, 'designer2', 'designer2@example.com', 'hashed_password_3', 'Designer B', '789 Oak St, Village', '555-555-5555', 'DESIGNER'),
(4, 'user2', 'user2@example.com', 'hashed_password_4', NULL, '101 Maple St, City', '222-333-4444', 'USER'),
(5, 'user3', 'user3@example.com', 'hashed_password_5', NULL, '789 Elm St, Town', '111-222-3333', 'USER'),
(6, 'user4', 'user4@example.com', 'hashed_password_6', NULL, '456 Oak St, Village', '777-888-9999', 'USER'),
(7, 'user5', 'user5@example.com', 'hashed_password_7', NULL, '123 Cedar St, City', '555-123-4567', 'USER'),
(8, 'designer3', 'designer3@example.com', 'hashed_password_8', 'Designer C', '234 Pine St, Town', '888-777-6666', 'DESIGNER'),
(9, 'designer4', 'designer4@example.com', 'hashed_password_9', 'Designer D', '567 Birch St, Village', '333-444-1111', 'DESIGNER'),
(10, 'user6', 'user6@example.com', 'hashed_password_10', NULL, '890 Elm St, City', '333-444-5555', 'USER'),
(11, 'user7', 'user7@example.com', 'hashed_password_11', NULL, '789 Oak St, Town', '222-555-4444', 'USER'),
(12, 'user8', 'user8@example.com', 'hashed_password_12', NULL, '456 Birch St, Village', '666-333-8888', 'USER'),
(13, 'designer5', 'designer5@example.com', 'hashed_password_13', 'Designer E', '123 Elm St, Town', '555-222-1111', 'DESIGNER'),
(14, 'designer6', 'designer6@example.com', 'hashed_password_14', 'Designer F', '789 Cedar St, City', '444-999-3333', 'DESIGNER'),
(15, 'user9', 'user9@example.com', 'hashed_password_15', NULL, '567 Pine St, Village', '111-222-6666', 'USER');

-- Insert data into the trend_category bridge table
INSERT INTO trend_category (trend_category_id, trend_id, category_id) VALUES
(1, 1, 8),
(2, 2, 3),
(3, 3, 1),
(4, 4, 3),
(5, 5, 3),
(6, 6, 8),
(7, 7, 3),
(8, 8, 7),
(9, 9, 8),
(10, 10, 3),
(11, 11, 2),
(12, 12, 3),
(13, 13, 1),
(14, 14, 6),
(15, 15, 2);

-- Insert data into the designer_product bridge table
INSERT INTO designer_product (designer_product_id, designer_id, product_id) VALUES
(1, 1, 1),
(2, 2, 2),
(3, 3, 3),
(4, 4, 4),
(5, 5, 5),
(6, 6, 6),
(7, 7, 7),
(8, 8, 8),
(9, 9, 9),
(10, 10, 10),
(11, 11, 11),
(12, 12, 12),
(13, 13, 13),
(14, 14, 14),
(15, 15, 15);

-- Insert data into the trend_popularity table
INSERT INTO trend_popularity (trend_popularity_id, trend_id, popularity_score) VALUES
(1, 1, 100),
(2, 2, 80),
(3, 3, 70),
(4, 4, 90),
(5, 5, 85),
(6, 6, 75),
(7, 7, 60),
(8, 8, 70),
(9, 9, 75),
(10, 10, 80),
(11, 11, 85),
(12, 12, 90),
(13, 13, 100),
(14, 14, 75),
(15, 15, 60);

-- Insert data into the category_popularity table
INSERT INTO category_popularity (category_popularity_id, category_id, season, popularity_score) VALUES
(1, 1, 'Spring', 100),
(2, 2, 'Summer', 75),
(3, 3, 'Fall', 50),
(4, 4, 'Winter', 90),
(5, 5, 'Spring', 80),
(6, 1, 'Summer', 70),
(7, 2, 'Fall', 60),
(8, 3, 'Winter', 85),
(9, 4, 'Spring', 70),
(10, 5, 'Summer', 80),
(11, 1, 'Fall', 60),
(12, 2, 'Winter', 75),
(13, 3, 'Spring', 90),
(14, 4, 'Summer', 70),
(15, 5, 'Fall', 85);

-- Insert data into the trend_interactions table
INSERT INTO trend_interactions (interaction_id, user_id, trend_id, interaction_type, timestamp) VALUES
(1, 1, 1, 'like', '2023-09-25 10:00:00'),
(2, 2, 1, 'comment', '2023-09-25 10:15:00'),
(3, 3, 2, 'like', '2023-09-25 11:30:00'),
(4, 4, 3, 'like', '2023-09-25 12:45:00'),
(5, 5, 3, 'comment', '2023-09-25 13:15:00'),
(6, 6, 4, 'like', '2023-09-25 14:30:00'),
(7, 7, 5, 'like', '2023-09-25 15:45:00'),
(8, 8, 5, 'comment', '2023-09-25 16:00:00'),
(9, 9, 6, 'like', '2023-09-25 17:15:00'),
(10, 10, 6, 'comment', '2023-09-25 18:30:00'),
(11, 11, 7, 'like', '2023-09-25 19:45:00'),
(12, 12, 8, 'like', '2023-09-25 20:00:00'),
(13, 13, 9, 'comment', '2023-09-25 21:15:00'),
(14, 14, 10, 'like', '2023-09-25 22:30:00'),
(15, 15, 10, 'comment', '2023-09-25 23:45:00');
