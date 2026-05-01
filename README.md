# Restaurant Reservation System

A Spring Boot-based REST API for managing restaurant table reservations.

## Overview

This project provides a simple table management system for restaurants, allowing you to:
- Query table availability
- Update table reservation status
- Manage table information (capacity, location, availability)

## Tech Stack

- **Framework**: Spring Boot 3.4.5
- **Language**: Java 17
- **Build Tool**: Maven
- **Container**: Docker
- **Orchestration**: Kubernetes

## Project Structure

```
Restaurant/
├── reservationSystem/          # Main reservation system service
│   ├── src/
│   │   └── main/
│   │       ├── java/
│   │       │   └── com/reservationApp/reservationSystem/
│   │       │       ├── ReservationSystemApplication.java
│   │       │       ├── controller/
│   │       │       │   └── TableController.java
│   │       │       └── entity/
│   │       │           └── Table.java
│   │       └── resources/
│   │           └── application.properties
│   ├── pom.xml
│   └── Dockerfile
├── reservationRequest/          # Reservation request service (optional)
│   └── ...
└── k8s/                         # Kubernetes manifests
    ├── deployment.yaml
    └── service.yaml
```

## API Endpoints

### Get Table Details
```bash
GET http://localhost:8081/tables/{tableId}
```

**Response:**
```json
{
    "tableId": "T001",
    "capacity": 4,
    "location": "Window",
    "available": true
}
```

### Update Table Availability
```bash
PUT http://localhost:8081/tables/{tableId}
Content-Type: application/json

{
    "available": false
}
```

## Running Locally

### Prerequisites
- Java 17+
- Maven 3.9+

### Build and Run
```bash
cd Restaurant/reservationSystem/reservationSystem
mvn clean install
java -jar target/reservationSystem-0.0.1-SNAPSHOT.jar
```

Or use the provided batch script:
```bash
Restaurant\reservationSystem\reservationSystem\run-app.bat
```

## Docker Deployment

### Build Image
```bash
docker build -t reservationsystem:latest Restaurant/reservationSystem/reservationSystem
```

### Run Container
```bash
docker run -p 8081:8081 reservationsystem:latest
```

### Push to Docker Hub
```bash
docker tag reservationsystem:latest curthwell/reservationsystem:latest
docker push curthwell/reservationsystem:latest
```

**Docker Hub Image**: `curthwell/reservationsystem:latest`

## Kubernetes Deployment

### Apply Manifests
```bash
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml
```

### Check Status
```bash
kubectl get pods -l app=reservationsystem
kubectl get svc reservationsystem-service
```

### Access Service
```bash
# Get external IP
kubectl get svc reservationsystem-service

# Access the application
curl http://<EXTERNAL-IP>/tables/T001
```

## Configuration

| Property | Default | Description |
|----------|----------|-------------|
| server.port | 8081 | Application port |
| spring.application.name | reservationSystem | Application name |

## Pre-loaded Data

The system comes with pre-configured tables:

| Table ID | Capacity | Location | Available |
|---------|----------|----------|-----------|
| T001 | 4 | Window | Yes |
| T002 | 2 | Corner | No |
| T003 | 6 | Center | Yes |
| T004 | 8 | Balcony | No |

## License

MIT License
