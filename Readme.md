# Survey Service

## Overview 
A backend service allowing managing surveys.
Designed and implemented for solving Coding Challenge.

## Tech stack:
- java 17
- spring-boot 3
- H2 in-memory database
- gradle

## How to run:
- Build: ```./gradlew build ``` (java 17 is required)
- Run tests: ```./gradlew test ``` 
- Run app: ```./gradlew bootRun ```

## Todo:
- Add comments/javadoc if needed
- Add Logging/Tracing/Metrics
- Improve error handling
- Add more tests for errors scenarios
- Do not use same model for service and repository/dao layer
- DB connection pool configuration
- Add tech fields to tables (version, createAt, updateAt)