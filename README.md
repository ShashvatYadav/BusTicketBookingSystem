---
# 🚌 Bus Ticket Booking System – Backend

A Spring Boot REST API for managing bus ticket bookings, seat allocation, and payment handling.

This backend powers the React frontend application and uses JWT authentication for secure API access.

---

## 🚀 Tech Stack

- Java 17+
- Spring Boot
- Spring Security (JWT Authentication)
- Spring Data JPA (Hibernate)
- PostgreSQL
- Lombok
- Maven

---

## 🏗️ Architecture

- Layered Architecture:
  - Controller Layer
  - Service Layer
  - Repository Layer
- DTO-based API responses
- Transactional booking operations
- Secure API endpoints using JWT

---

## ✨ Features

- 🔐 JWT-based Authentication
- 👤 User Registration & Login
- 🚌 Bus & Seat Management
- 💺 Seat Booking with Availability Check
- 💳 Payment Handling
- 📜 Booking History per User
- 🔄 Transaction-safe Booking Creation
- 🛡 Secure Endpoints with Spring Security

---

## 📂 Project Structure
src/main/java/com/busapp/busticketbookingsystem/
│
├── controller/
├── services/
│   ├── implementation/
│
├── repository/
├── entity/
├── dto/
├── enums/
├── security/
└── config/
---

## 🗄️ Database Design

Main Tables:

- users
- buses
- seats
- bookings
- booking_seats
- payments

Relationships:

- One User → Many Bookings
- One Booking → Many BookingSeats
- One Booking → One Payment
- One Bus → Many Seats

---

## 🔄 Booking Flow

1. User selects seats from frontend.
2. Frontend sends booking request with selected seats.
3. Backend:
   - Rechecks seat availability
   - Creates Booking
   - Creates Payment
   - Saves BookingSeat entries
4. Returns booking confirmation.

---

## ⚙️ Installation & Setup

### 1️⃣ Clone the Repository
git clone 
cd
### 2️⃣ Configure PostgreSQL

Update `application.properties`:
spring.datasource.url=jdbc:postgresql://localhost:5432/busbooking
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

### 3️⃣ Run the Application
mvn spring-boot:run
Backend runs at: http://localhost:8080
API Base Path: /api
---

## 🔐 Authentication

- JWT token generated on login
- Token validated using custom JWT filter
- Protected endpoints require authentication

---

## 📌 Future Improvements

- Payment Gateway Integration (Razorpay / Stripe)
- Seat Locking with Expiry
- Booking Cancellation & Refund
- Global Exception Handling
- API Pagination & Filtering
- Role-Based Access (Admin Panel)

---

## 👨‍💻 Author

Shashvat Yadav  
Backend Developer | Spring Boot | JPA | PostgreSQL
