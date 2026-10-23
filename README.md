# Multi-Service-Bus-Booking-APIs

A distributed bus management application built using **Java, Spring Boot, RESTful Web Services, and MySQL**, consisting of three independent Spring Boot applications: RedBus, Neeta Travels, and Purple Travels.

The system enables bus data to be created, retrieved, updated, and deleted across multiple services through REST API communication, with RedBus acting as the central application for coordinating operations.

## 🚀 Features

- **Multi-Service Architecture:** Three independent Spring Boot applications for RedBus, Neeta Travels, and Purple Travels.
- **CRUD Operations:** Create, retrieve, update, and delete bus records through RESTful APIs.
- **Inter-Service Communication:** Uses `RestTemplate` to exchange data between the applications over HTTP.
- **Centralized API Access:** RedBus endpoints can coordinate bus operations across the connected travel services.
- **RESTful Web Services:** Implements HTTP methods such as `GET`, `POST`, `PUT`, and `DELETE`.
- **ResponseEntity:** Controls HTTP responses, response bodies, and status codes returned by REST endpoints.
- **HTTP Status Codes:** Uses appropriate status codes such as `200 OK`, `201 CREATED`, `202 ACCEPTED` where applicable, and `404 NOT FOUND`.
- **Layered Architecture:** Follows the Controller → Service → Repository → Database flow for handling requests and persistence.
- **Database Integration:** Uses Spring Data JPA and Hibernate to perform database operations.
- **API Testing:** Tests endpoints using Postman to verify requests, responses, status codes, and CRUD functionality.

## 🛠️ Tech Stack

- **Language:** Java
- **Framework:** Spring Boot
- **Web Services:** RESTful APIs
- **Inter-Service Communication:** RestTemplate
- **Persistence:** Spring Data JPA, Hibernate ORM
- **Database:** MySQL
- **API Testing:** Postman
- **Build Tool:** Maven

## 🏗️ Project Architecture

The project consists of three independent Spring Boot applications:

1. **RedBus:** Central application that exposes APIs and coordinates requests to the connected travel services.
2. **Neeta Travels:** Manages its own bus records and provides REST endpoints for CRUD operations.
3. **Purple Travels:** Manages its own bus records and provides REST endpoints for CRUD operations.

### Request Flow

`Client / Postman → RedBus Controller → RedBus Service → Repository → Database`

For operations involving other travel services, RedBus also communicates with the corresponding application through REST APIs using `RestTemplate`.

`RedBus → Neeta Travels / Purple Travels → Controller → Service → Repository → Database`

## 📌 API Operations

| HTTP Method | Operation | Purpose |
|---|---|---|
| POST | Create | Add bus records |
| GET | Retrieve | Fetch all buses or retrieve a bus by ID |
| PUT | Update | Modify existing bus records |
| DELETE | Delete | Remove bus records |

RedBus provides separate endpoints for interacting with Neeta Travels and Purple Travels, allowing the client to select the target service.

## 🔄 HTTP Response Handling

The application uses `ResponseEntity` to return response data along with appropriate HTTP status codes.

- `200 OK` — Request completed successfully.
- `201 CREATED` — Resource created successfully.
- `202 ACCEPTED` — Request accepted for processing, where appropriate.
- `404 NOT FOUND` — Requested bus record does not exist.

## 🧪 Testing

All REST API endpoints are tested using **Postman**, including:

- Creating bus records.
- Retrieving all buses and individual buses by ID.
- Updating existing bus records.
- Deleting bus records.
- Verifying HTTP status codes and response bodies.
- Testing communication between RedBus, Neeta Travels, and Purple Travels.

## 🎯 Key Learning Outcomes

- Implementing RESTful Web Services using Spring Boot.
- Building applications with a layered architecture.
- Integrating multiple independent Spring Boot applications.
- Performing inter-service HTTP communication using `RestTemplate`.
- Managing persistence with Spring Data JPA and Hibernate.
- Handling HTTP responses and status codes using `ResponseEntity`.
- Testing APIs and debugging integration issues with Postman.

## ▶️ Getting Started

1. Clone the repository.
2. Configure the MySQL database connections for all three applications.
3. Update the database credentials in each application's `application.properties`.
4. Start Neeta Travels and Purple Travels on their configured ports.
5. Start RedBus.
6. Import or create the API requests in Postman and test the available endpoints.

Ensure that each application has its own correct database configuration and that the URLs and ports used by `RestTemplate` match the running services.

## 👩‍💻 Author

**Janhvi Kale**
