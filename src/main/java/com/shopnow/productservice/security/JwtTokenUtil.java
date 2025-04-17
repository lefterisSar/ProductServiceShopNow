package com.shopnow.productservice.security;

import com.shopnow.productservice.config.JwtProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;

/**
 * Utility class for JWT token operations.
 */
@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    private final JwtProperties jwtProperties;
    private Key key;

    /**
     * Initializes the signing key from the JWT secret.
     */
    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
    }

    /**
     * Checks if a JWT token is valid.
     * A token is valid if it can be parsed and has not expired.
     *
     * @param token the JWT token to validate
     * @return true if the token is valid, false otherwise
     */
    public boolean isValid(String token) {
        try {
            Claims claims = parse(token).getBody();
            Date expiration = claims.getExpiration();

            // Check if token has expired
            if (expiration != null && expiration.before(new Date())) {
                return false;
            }

            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    /**
     * Extracts the username (subject) from a JWT token.
     *
     * @param token the JWT token
     * @return the username stored in the token
     * @throws JwtException if the token cannot be parsed
     */
    public String getUsername(String token) {
        return parse(token).getBody().getSubject();
    }

    /**
     * Extracts the roles from a JWT token.
     *
     * @param token the JWT token
     * @return a list of roles stored in the token
     * @throws JwtException if the token cannot be parsed
     */
    public List<String> getRoles(String token) {
        return parse(token).getBody().get("roles", List.class);
    }

    /**
     * Parses a JWT token and returns the claims.
     *
     * @param token the JWT token to parse
     * @return the parsed JWT claims
     * @throws JwtException if the token cannot be parsed or has an invalid signature
     */
    private Jws<Claims> parse(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
    }
}
