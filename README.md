# 🍔 Foodies Backend API (Spring Boot Project)

A scalable and production-ready **Food Ordering Backend System** built using **Spring Boot**, providing RESTful APIs for food ordering, cart management, authentication, and payment integration.

---

## 🚀 Features

- 🔐 JWT Authentication & Authorization
- 👤 User Registration & Login
- 🍽️ Food Item Management (CRUD APIs)
- 🛒 Cart Management System
- 📦 Order Placement & Tracking
- 💳 Payment Integration (Razorpay)
- 📊 Secure REST APIs with role-based access
- ⚡ Exception Handling & Validation
- 🗄️ Database Integration with JPA & Hibernate

---

## 🛠️ Tech Stack

- **Backend:** Java, Spring Boot
- **Frameworks:** Spring Security, Spring Data JPA
- **Database:** MySQL
- **Authentication:** JWT (JSON Web Token)
- **Payment Gateway:** Razorpay
- **Build Tool:** Maven
- **API Testing:** Postman

---

## 📂 Project Structure
src/
├── controller/
├── service/
├── repository/
├── entity/
├── config/
├── filter/
└── io/
---

## 🔗 API Endpoints (Sample)

| Method | Endpoint            | Description              |
|--------|--------------------|--------------------------|
| POST   | /api/auth/register | Register user           |
| POST   | /api/auth/login    | Login user              |
| GET    | /api/foods         | Get all food items      |
| POST   | /api/cart/add      | Add item to cart        |
| POST   | /api/order/place   | Place order             |

---

## ⚙️ Setup & Installation

### 1. Clone the repository
```bash
git clone https://github.com/Anuj1143/foodies_App_Backend_Apis.git
