# ⚙️ EduCloud Assessment & School Management System
**Java • Spring Boot • MySQL • Thymeleaf • License: MIT**

A production-ready Spring Boot backend and full-stack application designed to manage core school operations. It seamlessly orchestrates relationships between Teachers, Students, and Parents, providing both RESTful APIs for modern frontends and Server-Side Rendered (SSR) Thymeleaf dashboards for direct administration.

---

## 📌 Table of Contents
1. [Overview](#-overview)
2. [Features](#-features)
3. [Architecture](#-architecture)
4. [Tech Stack](#-tech-stack)
5. [API Reference](#-api-reference)
6. [Getting Started](#-getting-started)
7. [Project Structure](#-project-structure)
8. [Key Design Decisions](#-key-design-decisions)

---

## 🔍 Overview
The **EduCloud Assessment System** is a unified platform designed to digitize and manage educational records. It tracks students, maps them to their guardians (parents), and assigns them to respective teachers. 

Beyond standard CRUD operations, the system features a **Dynamic DTO-to-UI Metadata Engine**, allowing frontends to dynamically generate forms and data tables based on backend Java object structures without hardcoding fields on the client side.

**Use cases it solves:**
- Centralized tracking of educational stakeholders (Students, Teachers, Parents).
- Providing flexible, paginated, and sortable APIs for large datasets.
- Rendering dynamic, metadata-driven HTML tables and forms.

---

## ✨ Features

| Feature | Details |
| :--- | :--- |
| **📚 Entity Mapping** | Robust JPA relations mapping Students to multiple Parents (`@ManyToMany`) and Teachers (`@ManyToOne`). |
| **🧠 Dynamic UI Metadata** | Exposes DTO field metadata via APIs (e.g., `/api/teachers/metadata`) to auto-generate frontend forms and tables. |
| **🏎️ Pagination & Sorting** | Built-in high-performance pagination using Spring Data JPA, returning structured `TableResponse` wrappers. |
| **🌐 Hybrid Delivery** | Exposes both standard JSON REST APIs and SSR Thymeleaf views (like `/dashboard` and `/form`). |
| **🛡️ Validation** | Strict entity validation using Jakarta Bean Validation (e.g., `@NotBlank`, `@Email`, `@Pattern`). |
| **🚦 Centralized Error Handling** | Standardized, predictable HTTP error codes for missing resources, invalid data, or SQL constraints. |

---

## 🏛️ Architecture

```text
┌─────────────────────────────────────────────────────────────────────┐
│                          Client (HTTP)                              │
│             (React/Vue Frontend OR Thymeleaf Views)                 │
└───────────────────────────────┬─────────────────────────────────────┘
                                │
          ┌─────────────────────▼──────────────────────┐
          │             Controllers Layer              │
          │  TeacherController  |   HomeController     │
          └─────────────────────┬──────────────────────┘
                                │
          ┌─────────────────────▼──────────────────────┐
          │               Service Layer                │
          │ TeacherService | StudentService | Parent...│
          └─────────────────────┬──────────────────────┘
                                │
   ┌────────────────────────────▼──────────────────────────────┐
   │                 JPA Repositories (MySQL)                  │
   │ TeacherRepo | StudentRepo | ParentRepo | AssessmentRepo   │
   └───────────────────────────────────────────────────────────┘
```

**Domain Model (ERD)**
* `Teacher` ─── *OneToMany* ──► `Student`
* `Student` ─── *ManyToMany* ──► `Parent`

---

## 🛠️ Tech Stack

| Layer | Technology |
| :--- | :--- |
| **Language** | Java 17 |
| **Framework** | Spring Boot 3.5.x |
| **Persistence** | Spring Data JPA + Hibernate + MySQL |
| **Connection Pooling**| HikariCP |
| **Validation** | Jakarta Bean Validation |
| **Frontend/Views** | Thymeleaf, HTML5, CSS3 |
| **Build Tool** | Maven Wrapper (`./mvnw`) |
| **Utilities** | Lombok (Boilerplate reduction) |

---

## 📡 API Reference

### Teacher Management Endpoints
*Base Path: `/api/teachers`*

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/metadata` | Fetch dynamic table columns and DTO field metadata. |
| `GET` | `?page=0&size=10` | Fetch paginated and sorted list of teachers. |
| `POST` | `/` | Add a new teacher to the system. |
| `PUT` | `/{id}` | Update an existing teacher's records. |
| `DELETE` | `/{id}` | Remove a teacher from the system. |

**Example POST Payload:**
```json
{
  "firstName": "John",
  "lastName": "Doe",
  "employeeId": "EMP9876",
  "email": "john.doe@educloud.com",
  "phone": "9876543210",
  "subject": "Mathematics",
  "experienceYears": 8,
  "qualification": "M.Sc Mathematics",
  "address": "123 Education St.",
  "joiningDate": "2023-08-15"
}
```

### Dashboard Endpoints (Thymeleaf)
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/dashboard` | Main admin dashboard rendering system statistics. |
| `GET` | `/form?type={entity}` | Returns a dynamically generated HTML form for a given entity type. |
| `POST` | `/save` | Accepts `x-www-form-urlencoded` form data from the dynamic frontend UI. |

---

## 🚀 Getting Started

### Prerequisites
* Java 17+
* MySQL 8.0+
* Maven 3.8+ (or use the provided `./mvnw` wrapper)

### Clone & Run

```bash
git clone https://github.com/yourusername/educloud-assessment.git
cd educloud-assessment

# Configure MySQL in src/main/resources/application.properties
# Make sure your local MySQL instance has a database named 'dashboard_db' 
# Provide correct DB_USER and DB_PASSWORD either in properties or via environment variables.

# Run the application
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`. 
Access the main UI at `http://localhost:8080/dashboard`.

---

## 📁 Project Structure

```text
assessment/
├── src/main/java/com/educloud/assessment/
│   ├── controller/                    # REST APIs and Thymeleaf View Controllers
│   ├── dto/                           # Data Transfer Objects with Validation
│   ├── entity/                        # Core JPA Entities (Teacher, Student, Parent)
│   ├── exceptions/                    # Global exception handlers
│   ├── repository/                    # Spring Data JPA Interfaces
│   ├── request/                       # Standardized request wrappers
│   ├── response/                      # Standardized response structures (TableResponse, PageResponse)
│   ├── service/                       # Core Business Logic 
│   └── util/                          # Utilities (DTOMapper for dynamic metadata)
├── src/main/resources/
│   ├── application.properties         # App configuration & MySQL credentials
│   └── templates/                     # Thymeleaf HTML files (dashboard, forms)
└── pom.xml                            # Dependencies configuration
```

---

## 🔑 Key Design Decisions

* **Dynamic Metadata Generation**: Instead of hardcoding UI tables and forms on the frontend, the `DTOMapper` utility leverages Java Reflection to read the fields of `TeacherDTO`, `StudentDTO`, etc., and sends configuration objects (metadata) to the frontend. This drastically reduces duplicate code across the stack.
* **Separation of Concerns (DTO Pattern)**: The application strictly utilizes DTOs (Data Transfer Objects) in its API layer instead of exposing Raw JPA Entities. This prevents infinite recursion issues (like Jackson `StackOverflowError`) during the serialization of bidirectional JPA relationships (e.g., `Student` <-> `Teacher`).
* **Hybrid Application Support**: By utilizing both `@RestController` (for JSON API consumers like React/Mobile) and `@Controller` (for SSR Thymeleaf templates), the system serves as both an API backend and a standalone administrative application.

---

## 📄 License
This project is licensed under the MIT License.
