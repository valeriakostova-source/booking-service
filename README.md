# Booking System

A microservice-based hotel booking application built with Spring Boot.

The system consists of three main backend services:

* **Booking Service** – manages rooms and reservations
* **Customer Service** – manages customers and authentication
* **Review Service** – manages customer reviews and ratings

The services communicate through REST APIs and are deployed using Docker and Kubernetes.

## Architecture

```text
                     Client / Frontend
                            |
                            v

                            |
          +-----------------+-----------------+
          |                 |                 |
          v                 v                 v
   Customer Service   Booking Service   Review Service
        :8081              :8082             :8083
          |                 |                 |
          v                 v                 v
       MySQL              MySQL             MySQL
```

Nginx routes requests to the correct microservice depending on the API path.

```text
/api/customers/**  -> Customer Service
/api/reviews/**    -> Review Service
other requests     -> Booking Service
```

## Services

### Booking Service

Responsible for rooms and reservations.

Main functionality:

* Search for available rooms
* Create reservations
* Update reservations
* Cancel reservations
* Retrieve customer reservations
* Validate customer existence through Customer Service

Booking Service communicates with Customer Service before creating a reservation to verify that the customer exists.

### Customer Service

Responsible for customer management and authentication.

Main functionality:

* Register customers
* Login
* Customer information
* Customer validation
* JWT authentication

Other services can communicate with Customer Service through its REST API.

### Review Service

Responsible for reviews and ratings.

Main functionality:

* Create reviews
* Store review content
* Store ratings
* Connect reviews to rooms
* Retrieve reviews and ratings

Example review request:

```json
{
  "roomId": 1,
  "reviewContent": "Very nice room and good service.",
  "rating": 5
}
```

## Technologies

* Java
* Spring Boot
* Spring Data JPA
* Spring Security
* REST API
* JWT
* MySQL
* Maven
* Docker
* Kubernetes
* Nginx
* HTML / CSS / JavaScript

## Docker

Each microservice has its own Docker image.

Example:

```bash
docker build -t booking-service:1 .
```

Docker images can be stored locally or pushed to Docker Hub.

## Kubernetes

The application services can be deployed separately to Kubernetes.

Example:

```bash
kubectl apply -f booking-service.yaml
kubectl apply -f customer-service.yaml
kubectl apply -f review-service.yaml
```

Check running pods:

```bash
kubectl get pods
```

Check services:

```bash
kubectl get services
```

Check logs:

```bash
kubectl logs <pod-name>
```

## Ports

| Service          | Port |
| ---------------- | ---: |
| Customer Service | 8081 |
| Booking Service  | 8082 |
| Review Service   | 8083 |


The internal Spring Boot container port may differ from the Kubernetes Service port depending on the deployment configuration.

## Environment Variables

Database credentials and JWT secrets are provided through environment variables or Kubernetes Secrets.

Example:

```text
SPRING_DATASOURCE_URL
SPRING_DATASOURCE_USERNAME
SPRING_DATASOURCE_PASSWORD
JWT_SECRET
```

Sensitive values should not be committed to Git.

## Microservice Communication

The services communicate using REST APIs.

Example:

```text
Booking Service
      |
      | Check if customer exists
      v
Customer Service
```

This allows each service to remain responsible for its own domain and database.

## Running the Application

Build the services and start the required infrastructure.

For Kubernetes:

```bash
kubectl apply -f .
```

Verify that all pods are running:

```bash
kubectl get pods
```

Expected result:

```text
booking-service     Running
customer-service    Running
review-service      Running
```

Once the services are running, requests can be routed through Nginx to the appropriate microservice.
