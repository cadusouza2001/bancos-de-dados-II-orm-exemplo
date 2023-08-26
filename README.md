# Movie Watchlist Application

Welcome to the Movie Watchlist Application! This is a simple Spring Boot application that allows users to create a watchlist of movies and rate them.

## Requirements

Before getting started, make sure you have the following installed:

- Java Development Kit (JDK) 
- Apache Maven
- PostgreSQL - Make sure you have a PostgreSQL server running locally or on a reachable host.
- Postman - This is optional, but it is recommended to have Postman installed to test the API endpoints.

## Setup

1. Clone the repository to your local machine

2. Create a PostgreSQL database

   ```bash
   createdb your_db_name
   ```
   
3. Create a '.env' file in the root directory of the project and add the following environment variables:

   ```bash
   DATABASE_NAME=your_db_name
   DATABASE_USER=your_db_user
   DATABASE_PASSWORD=your_db_password
   ```
   
4. Build the project using Maven:

   ```bash
   mvn clean install
   ```

5. Run the application:

   ```bash
    mvn spring-boot:run
    ```
