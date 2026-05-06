# Backend App

This is a **Spring Boot (Java)** backend application that provides data (products) for the frontend.

To run this app, make sure you have **Java** and **Maven (mvn)** installed. Then follow these steps:

```bash
# 1. Clone the repository
git clone https://github.com/samisrouji/backend.git
cd backend

# 2. Run the Spring Boot application
mvn spring-boot:run
```

## Run with Docker

A `Dockerfile` and `docker-compose.yml` have been added so you can run the backend together with MySQL.

```bash
# Build and start both containers
docker compose up --build
```

The backend will be available at `http://localhost:8081` and MySQL at `localhost:3307`.

If you want to build only the backend image:

```bash
docker build -t emarket-backend .
```

