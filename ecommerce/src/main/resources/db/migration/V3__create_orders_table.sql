-- V3__create_orders_table.sql

CREATE TABLE `orders` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `cart_id` BIGINT NOT NULL,
    `user_id` BIGINT NOT NULL,
    `status` ENUM('PENDING', 'PAID', 'CANCELLED') NOT NULL DEFAULT 'PENDING',
    `order_date` TIMESTAMP NULL DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_order_cart` (`cart_id`),
    KEY `fk_order_user` (`user_id`),
    CONSTRAINT `fk_order_cart` FOREIGN KEY (`cart_id`) REFERENCES `carts` (`id`) ON DELETE CASCADE,
    CONSTRAINT `fk_order_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Trigger to update product inventory and availability when order is PAID
DELIMITER $$

CREATE TRIGGER trg_order_paid
AFTER UPDATE ON `orders`
FOR EACH ROW
BEGIN
    IF NEW.status = 'PAID' AND OLD.status <> 'PAID' THEN
        -- Set the order_date when the status is updated to PAID
        UPDATE `orders`
        SET order_date = NOW()
        WHERE id = NEW.id;

        -- Update inventory and availability for each product in the cart
        UPDATE `product` p
        JOIN `cart_items` ci ON ci.product_id = p.id
        JOIN `carts` c ON c.id = ci.cart_id
        SET p.inventory_quantity = p.inventory_quantity - ci.quantity,
            p.is_available = (p.inventory_quantity - ci.quantity) > 0
        WHERE c.id = NEW.cart_id;
    END IF;
END$$

DELIMITER ;
