# Movie Watchlist Application

Welcome to the Movie Watchlist Application! This is a simple Spring Boot application that allows users to create a watchlist of movies and rate them.

## Requirements

Before getting started, make sure you have the following installed:

- Java Development Kit (JDK) 
- Apache Maven
- Docker Desktop
- Postman - This is optional, but it is recommended to have Postman installed to test the API endpoints.

## Setup

1. Clone the repository to your local machine

   ```bash
   git clone https://github.com/cadusouza2001/bancos-de-dados-II-orm-exemplo.git
    ```
   
2. Navigate to the project directory:

   ```bash
   cd bancos-de-dados-II-orm-exemplo
   ```

3. Run the following command to start the PostgreSQL database:

   ```bash
   docker-compose up -d
   ```

4. Build the project using Maven:

   ```bash
   mvn clean install
   ```

5. Run the application:

   ```bash
   mvn spring-boot:run
    ```
   
## Postman Collection
The Movie_Watchlist.postman_collection.json file contains a Postman collection with all the API endpoints. You can import this file into Postman to test the endpoints.
