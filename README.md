# Anheuser-Busch InBev: Code challenge

This API aims to fulfill the requirements of the Anheuser-Busch InBev Code challenge.

# The challenge

Your mission if you choose to accept is to build an API and simple database to handle the products of the ABInBev ecommerce.
To build the API, the following requirements are mandatory:
  - Should be able to save a product considering the fields name, description, price and brand.
  - Should be able delete the product.
  - Should be able to update the product name.
  - Should be able to search a product by name.
  - Should be able to search a product by id.

Technology Rules:
  - Java 8 (minimum)
  - Spring

Bonuses:
  - Security: OAUTH/JWT;
  - Database: NoSQL database;
  - Tests
  - Docker
  - Microservices
  - Swagger documentation

# Running the application

The application can be executed by the command bellow:

```sh
mvn spring-boot:run
```

The project comes with `application-dev.yml` config file, which can be used as an example for building a valid application config. I already has valid properties for development environments. To use it, run the application using the following command:

```sh
mvn spring-boot:run -Dspring.config.location=application-dev.yml
```

Once the application is running, it should navigate to the following endpoints, for assisting purpose:
  - /oauth2
  - /actuator/health
  - /swagger-ui.html
  
Also, the project repository on GitHub should be opened in a tab of your browser.

# Java

The application was written in Java 14. Make sure to use this version before running it.

# Security

The application uses OAuth2 for security. The authentication provider chosen is from GitHub.
The application endpoints require a `Cookie` in the header.
To get a valid cookie, browse to `/oauth2` endoint. You will be redirected to a GitHub sign in page.
After signing in, you will have a valid Cookie.
Obs: The Actuator and Swagger endpoints are not protected by authentication, so you don't need to use the Cookie to access it.

# Database

The NoSQL database choosen is MongoDB.
Before running the application, you should configure a connection your MongoDB server, under `spring.data.mongodb`.
You don't need to worry about creating a new database. Once the application start querying, it will be created automatically.

# Swagger documentation

The application exposes a page for Swagger documentation. Check /swagger-ui.html endpoint.

# Actuator

All Actuators endpoints are exposed. You don't need to be authenticated to access them.

# Test

To run the tests, use:

```sh
mvn test
```