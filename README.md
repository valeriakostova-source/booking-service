# Booking Service

Booking Service is a Spring Boot microservice responsible for managing room reservations in the booking system.

## Features

* Search for available rooms
* Create reservations
* Update reservations
* Cancel reservations
* Retrieve customer reservations
* Validate customer existence through Customer Service
* JWT authentication
* MySQL database
* Docker support
* Kubernetes deployment

## Technologies

* Java
* Spring Boot
* Spring Data JPA
* Spring Security
* MySQL
* REST API
* JWT
* Docker
* Kubernetes
* Maven

## Service Communication

Booking Service communicates with Customer Service to verify that a customer exists before creating a reservation.

```text
Client
  |
  v
Booking Service
  |
  +----> Customer Service
  |
  +----> Booking Database
```

## API Endpoints

### Get available rooms

```http
GET /api/reservation
```

Example parameters:

```text
checkIn=2026-09-10
checkOut=2026-09-15
guests=2
```

### Create reservation

```http
POST /api/reservation
```

Requires authentication.

### Update reservation

```http
PUT /api/reservation/{id}
```

Requires authentication.

### Cancel reservation

```http
DELETE /api/reservation/{id}
```

Requires authentication.

## Configuration

The application uses environment variables for database credentials and JWT configuration.

```text
SPRING_DATASOURCE_URL
SPRING_DATASOURCE_USERNAME
SPRING_DATASOURCE_PASSWORD
JWT_SECRET
```

## Docker

Build the Docker image:

```bash
docker build -t booking-service:1 .
```

Run the application using Docker Compose if the required services and database are configured.

## Kubernetes

The service can be deployed to Kubernetes using the Kubernetes configuration files.

Apply the configuration:

```bash
kubectl apply -f booking-service.yaml
```

Check the pods:

```bash
kubectl get pods
```

Check the services:

```bash
kubectl get services
```

View application logs:

```bash
kubectl logs <booking-service-pod>
```

### Ports

The Spring Boot application runs inside the container on port:

```text
8080
```

The Kubernetes Service exposes Booking Service on:

```text
8082
```

Example configuration:

```yaml
ports:
  - port: 8082
    targetPort: 8080
```

## Database

Booking Service uses a MySQL database.

Example JDBC URL:

```text
jdbc:mysql://booking-mysql:3306/Bookingservice
```

Database credentials should be provided through environment variables or Kubernetes Secrets and should not be committed to Git.

## Project Structure

```text
Booking-service/
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   └── test/
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
```

## Running Locally

Build the project:

```bash
./mvnw clean package
```

Run Spring Boot:

```bash
./mvnw spring-boot:run
```

Or build the Docker image:

```bash
docker build -t booking-service:1 .
```
