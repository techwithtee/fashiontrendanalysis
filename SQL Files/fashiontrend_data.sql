-- Insert data into the designer table
INSERT INTO designer (designer_id, designer_name, designer_location) VALUES 
(1, 'Burberry', 'New York'),
(2, 'Tove', 'New York'),
(3, 'Emilia Wickstead', 'New York'),
(4, 'Standing Ground', 'New York'),
(5, 'Roksanda', 'New York'),
(6, 'Simone Rocha', 'New York'),
(7, '16Arlington', 'New York');
(8, 'Harris Reed', 'London'),
(9, 'Gabriela Hearst', 'London'),
(10, 'Carolina Herrera', 'London'),
(11, 'Tory Burch', 'London'),
(12, 'Michael Kors', 'London'),
(13, 'Ralph Lauren', 'London'),
(14, 'Proenza Schouler', 'London'),
(15, 'Bevza', 'London'),
(16, 'Huishan Zhang', 'London'),
(17, 'Christopher Kane', 'London');

-- Insert data into the category table
INSERT INTO category (category_id, category_name) VALUES 
(1, 'Outerwear'),
(2, 'Dresses'),
(3, 'Tailoring'),
(4, 'Eveningwear'),
(5, 'Tops');

-- Insert data into the trend table
INSERT INTO trend (trend_id, trend_name, trend_desc, category_id, designer_id, location, season) VALUES 
(1, 'British Summer', 'A celebration of British summer with iconic Burberry check prints, summer blooms, and classic trench updates.', 1, 1, 'New York', 'SS24'),
(2, 'Sleek and Elegant', 'Tove\'s Spring/Summer 2024 collection embraces renewed optimism with sleek and elegant designs.', 2, 2, 'New York', 'SS24'),
(3, '1930s\' France', 'Inspired by 1930s\' France, Emilia Wickstead\'s collection focuses on fluid tailoring, bias-cut dresses, and colorful knitwear.', 2, 3, 'New York', 'SS24'),
(4, 'Slinky Jersey Eveningwear', 'Standing Ground presents a standout collection of slinky jersey eveningwear for Spring/Summer 2024, titled \'Tethys,\' featuring cobalt blue, mossy green, and bright white pieces.', 4, 4, 'New York', 'SS24'),
(5, 'Fragile and Delicate', 'Roksanda\'s collection draws influence from fresco paintings and features colorful fluid silhouettes with a fragile and delicate aesthetic.', 2, 5, 'New York', 'SS24'),
(6, 'Bridal Inspiration', 'Simone Rocha\'s SS24 collection, \'Dress Rehearsal,\' is inspired by bridal elements, featuring sheer dresses, pearl embellishments, and beautifully detailed white gowns.', 4, 6, 'New York', 'SS24'),
(7, 'Glamorous Modernist', 'Marco Capaldo\'s Spring/Summer 2024 collection celebrates glamorous modernism with sequin dresses, glossy translucent vinyl, and simple cotton frocks.', 4, 7, 'New York', 'SS24');
(8, 'Duality Exploration', 'Harris Reed\'s Spring/Summer 2024 collection, \'Duet,\' explores duality with exaggerated silhouettes, sculptural pieces, and a performance by Ashley Graham and Cosima.', 2, 8, 'London', 'SS24'),
(9, 'Druids Inspiration', 'Gabriela Hearst\'s Spring/Summer 2024 collection draws inspiration from Druids and features metallic dresses, statement shoulders, crocheted lace gowns, and faux-leather separates with a theme of strength and protection.', 2, 9, 'London', 'SS24'),
(10, 'Carolyn Bessette-Kennedy Tribute', 'Carolina Herrera\'s Spring/Summer 2024 collection is inspired by Carolyn Bessette-Kennedy, featuring sheer lace dresses, tulle dresses, knitted knickers, and a celebration of femininity and New York energy.', 2, 10, 'London', 'SS24'),
(11, 'Effortless Style', 'Tory Burch\'s Spring/Summer 2024 collection defines effortless style with modular tailoring, feather-weight materials, dynamic layers, and an emphasis on lightness and optimism.', 3, 11, 'London', 'SS24'),
(12, 'Glamorous Getaway', 'Michael Kors\' Spring/Summer 2024 collection is inspired by glamorous getaways and features classic black summer dresses, streamlined romance, and Jane Birkin-inspired bucket bags.', 2, 12, 'London', 'SS24'),
(13, 'Western Influences', 'Ralph Lauren\'s Spring/Summer 2024 collection embraces Western influences with denim, lace, fringing, and standout evening looks in black and gold.', 4, 13, 'London', 'SS24'),
(14, 'Nineties Minimalism', 'Proenza Schouler\'s Spring/Summer 2024 collection channels Nineties minimalism with a new monogram, leather dresses, one-tone dressing, and strappy sandals.', 2, 14, 'London', 'SS24'),
(15, 'Passionate and Conscious', 'Bevza\'s Spring/Summer 2024 collection is dedicated to the passionate and conscious young generation, featuring marigold-inspired designs, colorful dresses, tasselled loafers, and printed pieces.', 2, 15, 'London', 'SS24'),
(16, 'Luca Guadagnino\'s Inspiration', 'Huishan Zhang\'s Spring/Summer 2024 collection draws inspiration from Luca Guadagnino\'s \'Suspiria\' with pink, red, gold, sequins, tulle, and peplums.', 5, 16, 'London', 'SS24'),
(17, 'Romanticism', 'Christopher Kane\'s Spring/Summer 2024 collection embraces romanticism with florals, lace, silk, crystal embellishments, and pastel shades.', 5, 17, 'London', 'SS24');


-- Insert data into the product table
INSERT INTO product (product_id, product_name, category_id, designer_id, product_description) VALUES 
(1, 'Checkered Trench Coat', 1, 1, 'Classic Burberry checkered trench coat'),
(2, 'Silk Dress', 2, 2, 'Elegant silk dress from Tove\'s collection'),
(3, 'Bias-Cut Dress', 2, 3, 'Bias-cut dress with colorful knitwear from Emilia Wickstead\'s collection'),
(4, 'Slinky Jersey Dress', 4, 4, 'Slinky jersey evening dress in cobalt blue'),
(5, 'Fluid Silhouette Dress', 2, 5, 'Colorful fluid silhouette dress inspired by fresco paintings from Roksanda\'s collection'),
(6, 'Sheer Bridal Dress', 4, 6, 'Sheer bridal-inspired dress with pearl embellishments from Simone Rocha\'s collection'),
(7, 'Sequin Dress', 4, 7, 'Glamorous sequin dress from 16Arlington\'s collection'),
(8, 'Exaggerated Silhouette Dress', 2, 8, 'Exaggerated silhouette dress from Harris Reed\'s collection'),
(9, 'Metallic Dress', 2, 9, 'Metallic dress with statement shoulders from Gabriela Hearst\'s collection'),
(10, 'Sheer Lace Dress', 2, 10, 'Sheer lace dress inspired by Carolyn Bessette-Kennedy from Carolina Herrera\'s collection'),
(11, 'Modular Tailoring Dress', 3, 11, 'Modular tailoring dress with feather-weight materials from Tory Burch\'s collection'),
(12, 'Black Summer Dress', 2, 12, 'Classic black summer dress from Michael Kors\' collection'),
(13, 'Western Denim Dress', 4, 13, 'Western denim dress with lace and fringing from Ralph Lauren\'s collection'),
(14, 'Leather Dress', 2, 14, 'Leather dress with Nineties minimalism from Proenza Schouler\'s collection'),
(15, 'Marigold-Inspired Dress', 2, 15, 'Marigold-inspired dress with colorful designs from Bevza\'s collection'),
(16, 'Pink and Red Dress', 5, 16, 'Pink and red dress inspired by Luca Guadagnino\'s \'Suspiria\' from Huishan Zhang\'s collection'),
(17, 'Romantic Lace Dress', 5, 17, 'Romantic lace dress with silk and crystal embellishments from Christopher Kane\'s collection');

-- Insert data into the user table
INSERT INTO fashion_user (user_id, username, email, password_hash, designer_name, address, phone) VALUES 
(1001, 'ASOSUser', 'user@asos.com', 'hashedASOSpassword2023', 'ASOS', '123 Fashion St, London, UK', '+44123456789');


