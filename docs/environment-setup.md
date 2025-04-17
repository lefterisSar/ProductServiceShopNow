# Environment Setup Guide

This document explains how to set up environment variables for the ShopNow Product Service.

## Why Environment Variables?

Environment variables are used to store sensitive configuration data such as:
- Database credentials
- JWT secrets
- API keys
- Other environment-specific configuration

Using environment variables instead of hardcoding these values in configuration files provides several benefits:
1. **Security**: Sensitive data is not stored in version control
2. **Flexibility**: Different environments (development, testing, production) can use different configurations
3. **Compliance**: Follows security best practices for handling credentials

## Setting Up Environment Variables

### Local Development

1. Copy the `.env.example` file to a new file named `.env` (this file should not be committed to version control)
2. Fill in the values in the `.env` file with your local development settings
3. Use a tool like [dotenv](https://github.com/cdimascio/dotenv-java) to load these variables (already configured in the application)

### Production Deployment

In production environments, set the environment variables according to your deployment platform:

#### Docker
```
docker run -e SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/shopnow_products \
           -e SPRING_DATASOURCE_USERNAME=postgres \
           -e SPRING_DATASOURCE_PASSWORD=secure_password \
           -e JWT_SECRET=your_secure_jwt_secret \
           shopnow/product-service
```

#### Kubernetes
Use Kubernetes Secrets and ConfigMaps to manage environment variables:

```yaml
apiVersion: v1
kind: Secret
metadata:
  name: shopnow-product-service-secrets
type: Opaque
data:
  SPRING_DATASOURCE_PASSWORD: <base64-encoded-password>
  JWT_SECRET: <base64-encoded-jwt-secret>
---
apiVersion: v1
kind: ConfigMap
metadata:
  name: shopnow-product-service-config
data:
  SPRING_DATASOURCE_URL: jdbc:postgresql://postgres-service:5432/shopnow_products
  SPRING_DATASOURCE_USERNAME: postgres
```

Then reference these in your deployment:

```yaml
env:
  - name: SPRING_DATASOURCE_URL
    valueFrom:
      configMapKeyRef:
        name: shopnow-product-service-config
        key: SPRING_DATASOURCE_URL
  - name: JWT_SECRET
    valueFrom:
      secretKeyRef:
        name: shopnow-product-service-secrets
        key: JWT_SECRET
```

## Required Environment Variables

| Variable | Description | Example |
|----------|-------------|---------|
| SPRING_DATASOURCE_URL | JDBC URL for the database | jdbc:postgresql://localhost:5433/shopnow_products |
| SPRING_DATASOURCE_USERNAME | Database username | postgres |
| SPRING_DATASOURCE_PASSWORD | Database password | secure_password |
| JWT_SECRET | Secret key for JWT token signing | (32+ character random string) |

## Generating Secure Values

For production environments, use secure random values for secrets:

### JWT Secret
Generate a secure random string for JWT_SECRET:

```bash
openssl rand -base64 32
```

## Troubleshooting

If the application fails to start with environment variables:

1. Verify that all required environment variables are set
2. Check for typos in variable names
3. Ensure the values are properly formatted (especially database URLs)
4. Check application logs for specific error messages