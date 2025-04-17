package com.shopnow.productservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Main application context test.
 * Uses TestPropertySource to provide database credentials for testing,
 * without relying on default values in application.yml or .env files.
 */
@SpringBootTest
class ProductServiceApplicationTests {

    @Test
    void contextLoads() {
        // This test verifies that the application context loads successfully
        // All beans should be initialized properly, including database connections
    }

}
