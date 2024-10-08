# api-store

## Description

This project consists of creating a REST endpoint that allows
data belonging to a specific product to be recovered on a specific date.
This api was writing in Java 17 with maven and H2 database. Database
creating and inserting data automatically on the start of application.

## Run in local environment

Need to add in environment variables:

-Dspring.profiles.active=local

![img.png](img.png)

# Start the application:

- **Maven install:** Do a Maven install `mvn install`
- **Run test:** Execute the following maven
  command `mvn test`
- **Web App:** To start the embedded server from the command line use `mvn spring-boot:run`, or run
  StoreApplication class.
- **API Endpoint:** Open a browser and go
  to `/inditex/api/prices?applicationDate={applicationDate}&productId={productId}&brandId={brandId}`
  , from there discover all the
  endpoints.
- **Swagger Documentation:** available at  `localhost:9030/swagger-ui.html`
- **Code coverage report:** available
  at  `http://localhost:63342/api-store/target/site/jacoco/index.html`

![img_1.png](img_1.png)

## Curl of endpoint

curl
--location 'http://localhost:9030/inditex/api/prices?applicationDate=2020-06-14T15%3A00%3A00&productId=35455&brandId=1'

Architecture:

- Api: contains the endpoint
- Domain: contains model and use case
- Adapter: contains repository
- Commons: contains configuration, utils, constants and exceptions

Api receives requests from external users and it calls use case. Use case
contain the business logic and could communicate with other external microservices o could call
repositories. The Adapter layer contains repository.

- Used @ControllerAdvice to manage exceptions to avoided using try-catch blocks.
- Used flyway to automatically run database.
- Used Facade, Builder patterns
- Used Validator custom annotation for fields
- Used Jacoco for code coverage report

## Conclusion.

I have made the project small and simple based on the presented task.
Of course, in a real project there would be a different structure and there would be more logic
and different implementations.

P.S. I have commented PriceRepositoryTest because it up the context and try to
insert the second time the same data in database. I have tried to put @Profile("test")
but didnt work. Maybe you can tell me the solution. Thanks.
