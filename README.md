# 🏥 Hospital Appointment & Patient Record System

A comprehensive RESTful API system for managing hospital appointments, patient records, doctors, and medical information. Built with Spring Boot, PostgreSQL, and secured with JWT authentication.

---

## 📋 Table of Contents
- [Project Overview](#-project-overview)
- [Features](#-features)
- [Technology Stack](#️-technology-stack)
- [System Architecture](#️-system-architecture)
- [Database Design](#-database-design)
- [Security Implementation](#-security-implementation)
- [Getting Started](#-getting-started)
- [API Endpoints](#-api-endpoints)
- [Testing with Postman](#-testing-with-postman)
- [Team Members](#-team-members)
- [Challenges & Solutions](#-challenges--solutions)
- [Lessons Learned](#-lessons-learned)
- [Future Enhancements](#-future-enhancements)

---

## 🎯 Project Overview

This project is a backend system designed for a hospital management platform. It provides secure RESTful APIs for managing the complete lifecycle of hospital operations including patient registration, doctor management, appointment scheduling, and medical record keeping.

**Course:** Java Programming II (Object-Oriented Programming)  
**Project Type:** Hospital Appointment & Patient Record System  
**Group:** Group 8  
**Date:** December 2025

### Purpose
To demonstrate advanced Java and Object-Oriented Programming concepts through a real-world application that includes:
- Modular OOP design with proper encapsulation and abstraction
- RESTful API development following industry best practices
- Database integration using JPA/Hibernate
- Authentication and authorization using Spring Security and JWT
- Collaborative software engineering using Git and GitHub

---

## ✨ Features

### Core Functionality
- ✅ **User Authentication System**
    - Secure user registration with role assignment
    - JWT-based login mechanism
    - Token-based session management

- ✅ **Patient Management**
    - Complete CRUD operations for patient records
    - Store demographic and contact information
    - Track patient medical history

- ✅ **Doctor Management**
    - Manage doctor profiles with specializations
    - Store license and contact information
    - Associate doctors with appointments

- ✅ **Appointment Scheduling**
    - Create and manage patient-doctor appointments
    - Track appointment status and datetime
    - Link appointments to medical records

- ✅ **Medical Records System**
    - Store patient diagnoses and treatments
    - Maintain prescription records
    - Associate records with specific appointments

### Technical Features
- ✅ RESTful API Design following HTTP standards
- ✅ JWT (JSON Web Token) Authentication
- ✅ Role-Based Access Control (ADMIN, DOCTOR, PATIENT)
- ✅ Input Validation using Bean Validation
- ✅ Global Exception Handling
- ✅ Layered Architecture (Controller → Service → Repository → Entity)
- ✅ PostgreSQL Database with JPA/Hibernate ORM
- ✅ Spring Security Configuration with custom filters

---

## 🛠️ Technology Stack

| Component | Technology | Version |
|-----------|------------|---------|
| **Backend Framework** | Spring Boot | 3.5.9 |
| **Programming Language** | Java | 21 |
| **Database** | PostgreSQL | 18.1 |
| **ORM** | Hibernate/JPA | 6.6.39 |
| **Security** | Spring Security + JWT | 6.5.7 |
| **Build Tool** | Maven | - |
| **API Testing** | Postman | - |
| **Version Control** | Git & GitHub | - |
| **IDE** | IntelliJ IDEA | 2025.2 |

### Key Dependencies
```xml
<dependencies>
    <!-- Spring Boot Starters -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    
    <!-- Database -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
    </dependency>
    
    <!-- JWT -->
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt-api</artifactId>
        <version>0.11.5</version>
    </dependency>
    
    <!-- Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
    </dependency>
</dependencies>
```

---

## 🏗️ System Architecture

### Layered Architecture Pattern

```
┌─────────────────────────────────────────────────────────┐
│              Client Layer (Postman/Frontend)            │
│                   HTTP Requests (JSON)                  │
└───────────────────────────┬─────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────┐
│            Security Layer (JWT Filter)                  │
│  - Authentication Filter                                │
│  - JWT Token Validation                                 │
│  - Role-Based Authorization                             │
└───────────────────────────┬─────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────┐
│               Controller Layer                          │
│  - AuthController        - PatientController            │
│  - DoctorController      - AppointmentController        │
│  - MedicalRecordController                              │
│  (Handle HTTP Requests, Validation, Response)           │
└───────────────────────────┬─────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────┐
│                Service Layer                            │
│  - AuthService           - PatientService               │
│  - DoctorService         - AppointmentService           │
│  - MedicalRecordService                                 │
│  (Business Logic, Data Processing, Validation)          │
└───────────────────────────┬─────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────┐
│              Repository Layer (JPA)                     │
│  - UserRepository        - PatientRepository            │
│  - DoctorRepository      - AppointmentRepository        │
│  - MedicalRecordRepository                              │
│  (Database Operations - CRUD)                           │
└───────────────────────────┬─────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────┐
│            Entity/Model Layer                           │
│  - User      - Patient      - Doctor                    │
│  - Appointment             - MedicalRecord              │
│  (JPA Entities with Relationships)                      │
└───────────────────────────┬─────────────────────────────┘
                            │
                            ▼
┌─────────────────────────────────────────────────────────┐
│              PostgreSQL Database                        │
│                  (hospital_db)                          │
└─────────────────────────────────────────────────────────┘
```

### Package Structure
```
com.hospital.appointmentsystem/
├── controller/
│   ├── AuthController.java
│   ├── PatientController.java
│   ├── DoctorController.java
│   ├── AppointmentController.java
│   └── MedicalRecordController.java
├── service/
│   ├── PatientService.java
│   ├── DoctorService.java
│   ├── AppointmentService.java
│   └── MedicalRecordService.java
├── repository/
│   ├── UserRepository.java
│   ├── PatientRepository.java
│   ├── DoctorRepository.java
│   ├── AppointmentRepository.java
│   └── MedicalRecordRepository.java
├── model/
│   ├── User.java
│   ├── Patient.java
│   ├── Doctor.java
│   ├── Appointment.java
│   └── MedicalRecord.java
├── security/
│   ├── SecurityConfig.java
│   ├── JwtUtil.java
│   ├── JwtRequestFilter.java
│   └── CustomUserDetailsService.java
└── HospitalAppointmentSystemApplication.java
```

---

## 📊 Database Design

### Entity Relationship Overview

```
┌─────────────┐
│    User     │
│─────────────│
│ id (PK)     │
│ username    │
│ email       │
│ password    │
│ role        │
└─────────────┘

┌──────────────────┐           ┌──────────────────┐
│     Patient      │           │      Doctor      │
│──────────────────│           │──────────────────│
│ id (PK)          │           │ id (PK)          │
│ firstName        │           │ firstName        │
│ lastName         │           │ lastName         │
│ dateOfBirth      │           │ specialization   │
│ gender           │           │ licenseNumber    │
│ phoneNumber      │           │ phoneNumber      │
│ email            │           │ email            │
│ address          │           │ department       │
└────────┬─────────┘           └─────────┬────────┘
         │                               │
         │      ┌──────────────────┐     │
         └──────│   Appointment    │─────┘
                │──────────────────│
                │ id (PK)          │
                │ patient_id (FK)  │
                │ doctor_id (FK)   │
                │ appointmentDate  │
                │ appointmentTime  │
                │ status           │
                │ reason           │
                └────────┬─────────┘
                         │
                         │
                ┌────────▼─────────┐
                │  MedicalRecord   │
                │──────────────────│
                │ id (PK)          │
                │ patient_id (FK)  │
                │ appointment_id   │
                │ diagnosis        │
                │ treatment        │
                │ prescription     │
                │ notes            │
                │ recordDate       │
                └──────────────────┘
```

### Relationships
- **Patient ↔ Appointment**: One-to-Many (One patient can have multiple appointments)
- **Doctor ↔ Appointment**: One-to-Many (One doctor can have multiple appointments)
- **Patient ↔ MedicalRecord**: One-to-Many (One patient can have multiple medical records)
- **Appointment ↔ MedicalRecord**: One-to-One (Each appointment can have one medical record)

---

## 🔐 Security Implementation

### Authentication Flow

```
1. User Registration
   POST /api/auth/register
   ↓
   Password hashed with BCrypt
   ↓
   User saved to database

2. User Login
   POST /api/auth/login
   ↓
   Credentials validated
   ↓
   JWT token generated
   ↓
   Token returned to client

3. Accessing Protected Resources
   Request with Authorization header
   ↓
   JwtRequestFilter intercepts
   ↓
   Token validated and user authenticated
   ↓
   Request processed if authorized
```

### Security Features
- **Password Encryption**: BCrypt algorithm for secure password storage
- **JWT Tokens**: Stateless authentication with configurable expiration
- **Role-Based Access**: Different permissions for ADMIN, DOCTOR, PATIENT roles
- **Protected Endpoints**: All endpoints except registration/login require authentication
- **CORS Configuration**: Configured for cross-origin requests
- **CSRF Protection**: Disabled for stateless REST API

### JWT Configuration
```properties
jwt.secret=MyVerySecureHospitalSecretKeyThatIsAtLeast256BitsLong12345678
jwt.expiration=86400000  # 24 hours in milliseconds
```

---

## 🚀 Getting Started

### Prerequisites
- Java JDK 21 or higher
- PostgreSQL 18.1 or higher
- Maven 3.6+
- Git
- Postman (for API testing)
- IDE (IntelliJ IDEA recommended)

### Installation Steps

#### 1. Clone the Repository
```bash
git clone https://github.com/your-username/hospital-appointment-system.git
cd hospital-appointment-system
```

#### 2. Create PostgreSQL Database
```sql
-- Open PostgreSQL command line or pgAdmin
CREATE DATABASE hospital_db;
```

#### 3. Configure Database Connection
Edit `src/main/resources/application.properties`:

```properties
# Server Configuration
server.port=8080

# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/hospital_db
spring.datasource.username=postgres
spring.datasource.password=1234
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true

# JWT Configuration
jwt.secret=MyVerySecureHospitalSecretKeyThatIsAtLeast256BitsLong12345678
jwt.expiration=86400000

# Logging
logging.level.org.springframework.security=DEBUG
logging.level.com.hospital.appointmentsystem=DEBUG
```

#### 4. Build the Project
```bash
mvn clean install
```

#### 5. Run the Application
```bash
mvn spring-boot:run
```

Or run from IDE:
- Open `HospitalAppointmentSystemApplication.java`
- Click Run

#### 6. Verify Application is Running
```
Console output should show:
Started HospitalAppointmentSystemApplication in X.XXX seconds
Tomcat started on port 8080
```

Access: `http://localhost:8080`

---

## 📡 API Endpoints

### Authentication Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| POST | `/api/auth/register` | Register a new user | No |
| POST | `/api/auth/login` | Login and get JWT token | No |

**Register Request:**
```json
"POST" http://localhost:8080/api/auth/register
Content-Type: application/json

{
  "username": "admin",
  "email": "admin@hospital.com",
  "password": "Admin@123",
  "role": "ADMIN"
}
```

**Login Request:**
```json
POST http://localhost:8080/api/auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "Admin@123"
}
```

**Login Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYxNjIzOTAyMiwiZXhwIjoxNjE2MzI1NDIyfQ..."
}
```

---

### Patient Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/api/patients` | Get all patients | Yes |
| GET | `/api/patients/{id}` | Get patient by ID | Yes |
| POST | `/api/patients` | Create new patient | Yes |
| PUT | `/api/patients/{id}` | Update patient | Yes |
| DELETE | `/api/patients/{id}` | Delete patient | Yes |

**Create Patient Request:**
```json
POST http://localhost:8080/api/patients
Authorization: Bearer {your-jwt-token}
Content-Type: application/json

{
  "firstName": "John",
  "lastName": "Doe",
  "dateOfBirth": "1990-01-15",
  "gender": "MALE",
  "phoneNumber": "+1234567890",
  "email": "john.doe@email.com",
  "address": "123 Main St, City, Country"
}
```

---

### Doctor Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/api/doctors` | Get all doctors | Yes |
| GET | `/api/doctors/{id}` | Get doctor by ID | Yes |
| POST | `/api/doctors` | Create new doctor | Yes |
| PUT | `/api/doctors/{id}` | Update doctor | Yes |
| DELETE | `/api/doctors/{id}` | Delete doctor | Yes |

**Create Doctor Request:**
```json
POST http://localhost:8080/api/doctors
Authorization: Bearer {your-jwt-token}
Content-Type: application/json

{
  "firstName": "Dr. Jane",
  "lastName": "Smith",
  "specialization": "CARDIOLOGY",
  "licenseNumber": "DOC12345",
  "phoneNumber": "+1234567890",
  "email": "dr.jane@hospital.com",
  "department": "Cardiology"
}
```

---

### Appointment Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/api/appointments` | Get all appointments | Yes |
| GET | `/api/appointments/{id}` | Get appointment by ID | Yes |
| POST | `/api/appointments` | Create new appointment | Yes |
| PUT | `/api/appointments/{id}` | Update appointment | Yes |
| DELETE | `/api/appointments/{id}` | Delete appointment | Yes |

**Create Appointment Request:**
```json
POST http://localhost:8080/api/appointments
Authorization: Bearer {your-jwt-token}
Content-Type: application/json

{
  "patientId": 1,
  "doctorId": 1,
  "appointmentDate": "2025-01-20",
  "appointmentTime": "10:00",
  "status": "SCHEDULED",
  "reason": "Regular checkup"
}
```

---

### Medical Record Endpoints

| Method | Endpoint | Description | Auth Required |
|--------|----------|-------------|---------------|
| GET | `/api/medical-records` | Get all records | Yes |
| GET | `/api/medical-records/{id}` | Get record by ID | Yes |
| POST | `/api/medical-records` | Create new record | Yes |
| PUT | `/api/medical-records/{id}` | Update record | Yes |
| DELETE | `/api/medical-records/{id}` | Delete record | Yes |

**Create Medical Record Request:**
```json
POST http://localhost:8080/api/medical-records
Authorization: Bearer {your-jwt-token}
Content-Type: application/json

{
  "patientId": 1,
  "appointmentId": 1,
  "diagnosis": "Hypertension",
  "treatment": "Lifestyle changes and medication",
  "prescription": "Lisinopril 10mg daily",
  "notes": "Patient advised to reduce salt intake",
  "recordDate": "2025-01-20"
}
```

---

## 🧪 Testing with Postman

### Setup Instructions

#### 1. Import Collection
- Download `Hospital_Management_API.postman_collection.json` from repository
- In Postman: File → Import → Select the file

#### 2. Import Environment
- Download `Hospital_API_Local.postman_environment.json`
- Import into Postman
- Select "Hospital API - Local" from environment dropdown

#### 3. Environment Variables
```
base_url = http://localhost:8080
jwt_token = (auto-populated after login)
```

### Testing Workflow

#### Step 1: Register a User
```
POST {{base_url}}/api/auth/register
```

#### Step 2: Login
```
POST {{base_url}}/api/auth/login
```
The JWT token will be automatically saved to `{{jwt_token}}` variable.

#### Step 3: Test Protected Endpoints
All other endpoints automatically use `{{jwt_token}}` for authorization.

Example:
```
GET {{base_url}}/api/patients
Authorization: Bearer {{jwt_token}}
```

### Common HTTP Status Codes
- `200 OK` - Request successful
- `201 Created` - Resource created successfully
- `400 Bad Request` - Invalid input data
- `401 Unauthorized` - Missing or invalid JWT token
- `403 Forbidden` - Insufficient permissions
- `404 Not Found` - Resource not found
- `500 Internal Server Error` - Server error

---

## 👥 Team Members

### Group 8

| Name | Role | Responsibilities |
|------|------|-----------------|
| **Muhammed Sey** | Controllers & API Development | Designed and implemented all REST controllers, handled HTTP request/response mapping, implemented validation and exception handling |
| **Mba Jaiteh** | Security & Authentication | Implemented Spring Security configuration, JWT token generation and validation, created authentication filters and user details service |
| **Awa Banjang** | Backend (Entities, Repositories, Services) | Designed entity models with JPA annotations, created repository interfaces, implemented service layer business logic |
| **Susan Mendy** | Database Design & Testing | Designed database schema and relationships, created SQL scripts, performed comprehensive API testing with Postman |

### Contributions
Each team member contributed equally to the project's success. We followed an agile approach with regular meetings, code reviews, and collaborative problem-solving sessions.

---

## 💡 Challenges & Solutions

### Challenge 1: JWT Token Expiration Issues
**Problem:** Users were getting 401 Unauthorized errors randomly, and tokens from previous sessions weren't working after application restart.

**Root Cause:**
- JWT secret key was being randomly generated on each application startup
- Old tokens signed with different keys became invalid

**Solution:**
- Set a fixed JWT secret key in `application.properties`
- Configured consistent token expiration time (24 hours)
- Implemented proper token refresh mechanism

```properties
jwt.secret=MyVerySecureHospitalSecretKeyThatIsAtLeast256BitsLong12345678
jwt.expiration=86400000
```

---

### Challenge 2: Database Relationship Mapping
**Problem:** Complex relationships between entities caused circular dependency issues and JSON serialization problems.

**Issues Faced:**
- Infinite recursion during JSON serialization
- LazyInitializationException when accessing related entities
- Confusion about bidirectional vs unidirectional relationships

**Solution:**
- Used `@JsonManagedReference` and `@JsonBackReference` to prevent circular references
- Implemented proper fetch strategies (LAZY vs EAGER)
- Created separate DTOs for complex responses
- Used `@JsonIgnore` on back-references where appropriate

Example:
```java
@Entity
public class Patient {
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Appointment> appointments;
}

@Entity
public class Appointment {
    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonBackReference
    private Patient patient;
}
```

---

### Challenge 3: Spring Security Configuration
**Problem:** Understanding Spring Security's filter chain and properly configuring authentication/authorization was complex.

**Issues Faced:**
- All endpoints were blocked, even registration and login
- CORS errors when testing from frontend
- Confusion about which endpoints to protect
- 403 Forbidden errors even with valid tokens

**Solution:**
- Created `SecurityConfig` with proper filter chain configuration
- Whitelisted authentication endpoints (`/api/auth/**`)
- Implemented custom `JwtRequestFilter` to validate tokens
- Added proper CORS configuration
- Disabled CSRF for stateless REST API

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated()
            )
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }
}
```

---

### Challenge 4: 403 Forbidden Errors
**Problem:** Getting 403 errors even after successful login with valid credentials.

**Root Cause:**
- Not including JWT token in request headers
- Token expired but not refreshed
- Incorrect Authorization header format

**Solution:**
- Set up Postman environment variables for automatic token management
- Added token auto-capture script in login request
- Standardized Authorization header: `Bearer {token}`
- Implemented better error messages for debugging

Postman Auto-Token Script:
```javascript
var jsonData = pm.response.json();
if (jsonData.token) {
    pm.environment.set("jwt_token", jsonData.token);
}
```

---

## 📚 Lessons Learned

### Technical Skills Gained

1. **Spring Boot Mastery**
    - Understanding of dependency injection and IoC container
    - Auto-configuration and component scanning
    - Application properties management

2. **RESTful API Design**
    - Proper HTTP method usage (GET, POST, PUT, DELETE)
    - Status code conventions
    - Request/Response body design
    - API versioning considerations

3. **ORM and JPA**
    - Entity relationship mapping
    - JPQL and derived query methods
    - Transaction management
    - Database migration strategies

4. **Security Implementation**
    - JWT token lifecycle
    - Password encryption with BCrypt
    - Role-based access control
    - Security filter chains

5. **Database Design**
    - Normalization principles
    - Foreign key relationships
    - Index optimization
    - Data integrity constraints

### Soft Skills Developed

1. **Team Collaboration**
    - Effective use of Git for version control
    - Code review practices
    - Merge conflict resolution
    - Task distribution and management

2. **Problem-Solving**
    - Debugging complex security issues
    - Reading stack traces and error logs
    - Using documentation effectively
    - Breaking down large problems into smaller tasks

3. **Time Management**
    - Meeting project deadlines
    - Balancing multiple features
    - Prioritizing critical functionality
    - Incremental development approach

### Best Practices Learned

- Always validate input data at controller level
- Use DTOs to separate internal models from API contracts
- Implement global exception handling
- Write meaningful commit messages
- Keep secrets and passwords in environment variables
- Document APIs thoroughly
- Test endpoints incrementally
- Use meaningful variable and method names
- Follow consistent code formatting

---

## 🔮 Future Enhancements

### Short-term Improvements
- [ ] Add Swagger/OpenAPI documentation for interactive API testing
- [ ] Implement pagination and sorting for list endpoints
- [ ] Add search and filter functionality
- [ ] Create unit and integration tests
- [ ] Implement email notifications for appointments
- [ ] Add appointment reminder system

### Medium-term Features
- [ ] Develop frontend using React or Angular
- [ ] Add file upload for medical documents
- [ ] Implement real-time notifications using WebSockets
- [ ] Add appointment calendar view
- [ ] Create reporting and analytics dashboard
- [ ] Implement multi-language support

 ### Long-term Vision
- [ ] Mobile application (React Native)
- [ ] Integration with payment gateways
- [ ] Telemedicine video consultation feature
- [ ] Integration with external lab systems
- [ ] AI-powered diagnosis suggestions
- [ ] Blockchain for medical record security
- [ ] Microservices architecture migration

---

📄 License

This project was developed as part of the Java Programming II course curriculum.

Academic Use Only - This project is for educational purposes and demonstration of Object-Oriented Programming concepts.

---

🙏 Acknowledgments

- Instructor: Mr Babocarr Drammeh - For guidance and support throughout the project
- Course: Java Programming II (Object-Oriented Programming)
- Spring Boot Documentation - For comprehensive framework documentation
- Stack Overflow Community - For troubleshooting assistance
- Baeldung & Spring Guides - For excellent tutorials and examples

---

📞 Contact

For questions or feedback about this project:

- Muhammed Sey - Controllers & API Development
- Mba Jaiteh - Security & Authentication
- Awa Banjang - Backend Development
- Susan Gomez - Database Design & Testing
- Binta Jallow - Database Design & Testing 2

Group: Group 8  
Course: Java Programming II  
Project Repository: https://github.com/Muhammed782/java2_Group8_hospital_management_system
---

Built with by Group 8 Members
Muhammed Sey
Mba Jaiteh
Awa Banjang
Susan Gomez
Binta Jallow

Susan Gomez
Binta Jallow
