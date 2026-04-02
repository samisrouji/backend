package com.sami.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.sami.ecommerce.service.ProductService;

@SpringBootApplication
public class EcommerceApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(EcommerceApplication.class, args);

        // Fix product availability on startup
        ProductService productService = context.getBean(ProductService.class);
        productService.updateAvailabilityForAllProducts();
    }
}

