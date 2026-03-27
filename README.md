🛒 eCommerce Web Application

A scalable full-stack eCommerce Web Application developed using React.js for the frontend and Spring Boot for the backend, providing RESTful APIs for product management, cart, and order processing. The application implements JWT-based authentication and role-based authorization using Spring Security.

🚀 Tech Stack

Frontend
React.js
HTML
CSS
JavaScript
Axios

Backend
Java
Spring Boot
Spring Data JPA
Hibernate
Spring Security
JWT Authentication

Database
MySQL

Tools & Technologies
Maven
Git & GitHub
Postman
Swagger UI
BCrypt Password Encoder
JUnit (Unit Testing)

✨ Features

👤 User Features
User Registration and Login
JWT-based Authentication
Browse Products
Add Products to Cart
Place Orders
View Order History

User Profile Management

🔑 Admin Features
Admin Login
Add / Update / Delete Products
Manage Users
View All Orders
Manage Product Inventory

🔐 Security Features

JWT Token Authentication
Role-Based Authorization (ADMIN / USER)
Password Encryption using BCrypt
Secure REST API with Spring Security

🧩 Modules

Authentication Module (Login / Signup)
User Module
Product Module
Cart Module
Order Module
Admin Module

📡 Sample API Endpoints

Method	Endpoint	Description
POST	/auth/signup	Register user
POST	/auth/login	Login user
GET	/products	Get all products
POST	/cart/add	Add product to cart
POST	/orders/place	Place order
GET	/orders/user/{id}	Get user orders
POST	/admin/product	Add product (Admin)

🗄️ Database Schema (Tables)
Users
Roles
Products
Cart
Orders
Order_Items

🏗️ Project Architecture
React Frontend
       ↓
REST API (HTTP)
       ↓
Spring Boot Backend
       ↓
Service Layer
       ↓
Repository Layer (JPA)
       ↓
MySQL Database

⚙️ Installation & Setup
1. Clone Repository
git clone https://github.com/Nirikshan03/ecommerce-app.git
2. Configure MySQL in application.properties
server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/ecomdb
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
3. Run Backend
mvn spring-boot:run
4. Run Frontend
npm install
npm start

📄 API Documentation (Swagger)
http://localhost:8080/swagger-ui/index.html
🧪 Testing
Unit Testing performed using JUnit
API testing performed using Postman

