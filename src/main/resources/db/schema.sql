-- eCommerce Database Schema
-- This schema is compatible with MySQL and will work across different environments

SET FOREIGN_KEY_CHECKS = 0;

-- Drop tables in proper order (reverse dependency order)
DROP TABLE IF EXISTS `cart_items`;
DROP TABLE IF EXISTS `carts`;
DROP TABLE IF EXISTS `addresses`;
DROP TABLE IF EXISTS `profiles`;
DROP TABLE IF EXISTS `product`;
DROP TABLE IF EXISTS `users`;

-- ==================================================================================
-- Table structure for table `users`
-- ==================================================================================

CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `created_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
);

-- Sample data for table `users`
INSERT INTO `users` (name, email, password) VALUES ('Alice Johnson','alice@example.com','newPassword');

-- ==================================================================================
-- Table structure for table `product`
-- ==================================================================================

CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `category` varchar(255) NOT NULL,
  `inventory_quantity` int NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` decimal(38,2) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `product_chk_1` CHECK ((`inventory_quantity` >= 0))
);

-- Sample data for table `product`
INSERT INTO `product` (id, category, inventory_quantity, name, price) VALUES 
(1,'Electronics',10,'iPhone 16',899.99),
(2,'Electronics',12,'Samsung Galaxy S26',999.99),
(3,'Home',8,'Dyson V15 Vacuum Cleaner',749.99),
(4,'Home',6,'Instant Pot Duo 7-in-1',99.99),
(5,'Home',5,'Ninja Air Fryer',129.99),
(6,'Fashion',14,'Nike Air Force 1 Sneakers',110.00),
(7,'Fashion',9,'Adidas Ultraboost Shoes',180.00),
(8,'Fashion',7,'Levi''s 501 Original Jeans',69.50),
(9,'Beauty',11,'Olaplex Hair Perfector No.3',30.00),
(10,'Beauty',13,'CeraVe Hydrating Facial Cleanser',15.99),
(11,'Toys',6,'LEGO Classic Bricks Set',29.99),
(12,'Toys',4,'Barbie Dreamhouse',199.99),
(13,'Toys',8,'Hot Wheels 20-Car Pack',24.99),
(14,'Sports',10,'Spalding Basketball',29.99),
(15,'Sports',12,'Wilson Tennis Racket',89.99),
(16,'Sports',5,'Yeti Rambler Tumbler',39.99),
(17,'Books',9,'Atomic Habits',18.00),
(18,'Books',7,'The 48 Laws of Power',21.99),
(19,'Books',6,'Rich Dad Poor Dad',16.99),
(20,'Groceries',13,'Kirkland Organic Almonds',19.99),
(21,'Groceries',11,'Nutella Hazelnut Spread',8.99),
(22,'Groceries',14,'Lay''s Classic Chips',4.99),
(23,'Pet Supplies',8,'Purina Dog Chow',34.99),
(24,'Pet Supplies',6,'Temptations Cat Treats',12.99),
(25,'Baby',5,'Pampers Swaddlers Diapers',39.99),
(26,'Baby',7,'Graco Pack n Play',79.99),
(27,'Automotive',9,'Meguiar''s Car Wash Kit',27.99),
(28,'Automotive',4,'Armor All Cleaning Wipes',9.99),
(29,'Office',10,'HP DeskJet Printer',129.99),
(30,'Office',12,'Amazon Basics Notebook Pack',12.99),
(31,'Gaming',6,'PlayStation 5 Console',499.99),
(32,'Gaming',8,'Xbox Series X',499.99),
(33,'Gaming',7,'Nintendo Switch OLED',349.99),
(34,'Music',5,'Beats Studio Pro Headphones',349.99),
(35,'Music',9,'JBL Flip 6 Speaker',129.99),
(36,'Health',11,'Fitbit Charge 6',159.99),
(37,'Health',6,'TheraBand Resistance Bands',24.99),
(38,'Garden',8,'Scotts Lawn Fertilizer',29.99),
(39,'Garden',4,'Garden Hose 50ft',25.99),
(40,'Home',10,'Shark Navigator Lift-Away Vacuum',199.99);

-- ==================================================================================
-- Table structure for table `addresses`
-- ==================================================================================

CREATE TABLE `addresses` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `street` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  `zip` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user` (`user_id`),
  CONSTRAINT `fk_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
);

-- Sample data for table `addresses`
INSERT INTO `addresses` (user_id, street, city, state, zip) VALUES (5,'456 Park Avenue','New York','NY','10022');

-- ==================================================================================
-- Table structure for table `profiles`
-- ==================================================================================

CREATE TABLE `profiles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `loyalty_points` int DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`),
  CONSTRAINT `fk_user_profile` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
);

-- Sample data for table `profiles`
INSERT INTO `profiles` (user_id, phone_number, loyalty_points) VALUES (5,'555-987-6543',200);

-- ==================================================================================
-- Table structure for table `carts`
-- ==================================================================================

CREATE TABLE `carts` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `modified_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_cart_user` (`user_id`),
  CONSTRAINT `fk_cart_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);

-- Sample data for table `carts`
INSERT INTO `carts` (user_id) VALUES (5);

-- ==================================================================================
-- Table structure for table `cart_items`
-- ==================================================================================

CREATE TABLE `cart_items` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `quantity` int DEFAULT NULL,
  `cart_id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6oue0maw421roerltnxn16a38` (`cart_id`,`product_id`),
  KEY `FKl7je3auqyq1raj52qmwrgih8x` (`product_id`),
  CONSTRAINT `FKl7je3auqyq1raj52qmwrgih8x` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKpcttvuq4mxppo8sxggjtn5i2c` FOREIGN KEY (`cart_id`) REFERENCES `carts` (`id`)
);

-- Sample data for table `cart_items`
INSERT INTO `cart_items` (quantity, cart_id, product_id) VALUES (2, 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
