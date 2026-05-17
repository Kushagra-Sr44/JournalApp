# JournalApp

A secure, modern journal application built with Spring Boot that allows users to create, read, and manage personal journal entries.

## Features

- 📝 Create and manage journal entries
- 🔐 Secure authentication with JWT tokens
- 🛡️ Spring Security integration
- 🗄️ MongoDB database for persistent storage
- 🚀 RESTful API endpoints
- 👤 User-specific journal entries

## Tech Stack

**Backend:**
- Java 17
- Spring Boot 3.5.0
- Spring Web (REST API)
- Spring Security
- JWT Authentication (JJWT)

**Database:**
- MongoDB (Spring Data MongoDB)

**Tools:**
- Maven (Build Management)
- Lombok (Boilerplate Reduction)
- JUnit & Spring Boot Test (Testing)

## Getting Started

### Prerequisites
- Java 17+
- MongoDB running locally or remotely
- Maven 3.6+

### Installation

1. Clone the repository
```bash
git clone https://github.com/Kushagra-Sr44/JournalApp.git
cd JournalApp
```

2. Navigate to the project directory
```bash
cd "Journal App"
```

3. Configure your MongoDB connection in `application.properties`

4. Build the project
```bash
./mvnw clean install
```

5. Run the application
```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

## Project Structure

```
Journal App/
├── src/
│   ├── main/
│   │   ├── java/
│   │   └── resources/
│   └── test/
├── pom.xml
└── mvnw
```

## API Endpoints

- `POST /api/auth/register` - Register a new user
- `POST /api/auth/login` - User login
- `GET /api/journals` - Get all journals
- `POST /api/journals` - Create new journal entry
- `GET /api/journals/{id}` - Get specific journal
- `PUT /api/journals/{id}` - Update journal entry
- `DELETE /api/journals/{id}` - Delete journal entry
