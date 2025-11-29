# Spring Boot Reference Project

A complete Spring Boot 4.0.0 reference application demonstrating enterprise-grade patterns and best practices for building REST APIs with Spring Framework.

## Quick Start

### Prerequisites
- Java 21
- Maven 3.x

### Run the Application

```bash
# Build the project
mvn clean package

# Run the application
mvn spring-boot:run
```

The application will start with the H2 in-memory database.

### Run Tests

```bash
# Run all tests
mvn test

# Run a specific test class
mvn test -Dtest=ProductControllerTest
```

## Technology Stack

- **Java 21**
- **Spring Boot 4.0.0**
- **Spring Data JPA** - Data persistence
- **Spring Security** - Authentication & authorization
- **H2 Database** - In-memory database
- **JUnit 5** - Testing framework
- **MockMvc** - API testing

## Project Structure

```
src/main/java/com/great/project/
├── controller/       # REST API endpoints
├── service/          # Business logic
├── domain/
│   ├── model/        # JPA entities
│   └── repository/   # Data access layer
├── dto/              # Data transfer objects
├── config/           # Spring configuration
├── security/         # Security handlers and filters
└── errorhandling/    # Exception handlers
```

## API Endpoints

Base URL: `/v1/api`

### Product API
- `POST /v1/api/product` - Create a product
- `GET /v1/api/product/{id}` - Get product by ID
- `GET /v1/api/product` - Get all products
- `PUT /v1/api/product/{id}` - Update product
- `DELETE /v1/api/product/{id}` - Delete product

### Authentication

The application uses HTTP Basic authentication:
- **User**: `user` / `password` (role: USER)
- **Admin**: `admin` / `password` (role: ADMIN)

## Development

### Profiles

- `dev` - Development (default)
- `prod` - Production
- `in-memory` - Testing with in-memory database

## Training Exercises

- [AI for Coding](docs/ai-for-coding.md) - Using AI to develop software (prompts from IDE/CLI)
- [AI in Apps](docs/ai-in-apps.md) - Using AI in software (API integrations)

## Further Information

For detailed architecture information and development guidelines, see [CLAUDE.md](./CLAUDE.md).
