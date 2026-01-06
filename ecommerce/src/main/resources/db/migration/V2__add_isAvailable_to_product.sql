-- V2__add_isAvailable_to_product.sql

ALTER TABLE product
ADD COLUMN is_available BOOLEAN NOT NULL DEFAULT FALSE;

-- Update existing products based on inventory
UPDATE product
SET is_available = CASE
    WHEN inventory_quantity < 1 THEN FALSE
    ELSE TRUE
END;
