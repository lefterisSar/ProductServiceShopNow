package com.shopnow.productservice.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Tests for JwtProperties configuration.
 * This test verifies that the JwtProperties bean is properly configured
 * and can read properties from environment variables.
 */
@SpringBootTest(classes = JwtProperties.class)
@EnableConfigurationProperties(JwtProperties.class)
@TestPropertySource(properties = {
    "jwt.secret=test-jwt-secret-for-unit-testing",
    "jwt.expiration=3600000"
})
public class JwtPropertiesTest {

    @Autowired
    private JwtProperties jwtProperties;

    @Test
    public void testJwtPropertiesLoaded() {
        // Verify that the JwtProperties bean is not null
        assertNotNull(jwtProperties, "JwtProperties bean should not be null");

        // Verify that the secret is loaded from test properties
        assertNotNull(jwtProperties.getSecret(), "JWT secret should not be null");
        assertEquals("test-jwt-secret-for-unit-testing", jwtProperties.getSecret(), 
                "JWT secret should match the test property value");

        // Verify that the expiration is loaded from test properties
        assertEquals(3600000, jwtProperties.getExpiration(), 
                "JWT expiration should match the test property value");

        System.out.println("[DEBUG_LOG] JWT Properties loaded successfully:");
        System.out.println("[DEBUG_LOG] Secret: " + jwtProperties.getSecret());
        System.out.println("[DEBUG_LOG] Expiration: " + jwtProperties.getExpiration());
    }
}
