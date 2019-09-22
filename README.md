* Continuous Integration with CircleCi [![CircleCI](https://circleci.com/gh/OlegPod/spring5-reactive-mongo-recipe-app.svg?style=svg)](https://circleci.com/gh/OlegPod/spring5-reactive-mongo-recipe-app)

* Test Coverage with CodeCov [![codecov](https://codecov.io/gh/OlegPod/spring5-reactive-mongo-recipe-app/branch/master/graph/badge.svg)](https://codecov.io/gh/OlegPod/spring5-reactive-mongo-recipe-app)

# Food Recipes Application
The main features of this project are CRUD operations with Recipe object and possibility to upload picture of the meal.
Application was written according to the best practice the Test Driven Development.
Persistence to the database is implemented through Command objects and Converters. 
In this project i have implemented Reactive Types(Mono, Flux) with WebFlux and database with reactive driver MongoDB.
Data Validation is handled with Exception Handlers.

## Stack of technologies that are used in this application:
* Gradle
* Hibernate
* Spring Data JPA
* WebFlux
* Project Lombok
* Thymeleaf
* Bootstrap
* MongoDB Database
* JUnit
* Mockito
