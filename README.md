i-Mart E-Commerce Application

i-Mart is a full-stack E-Commerce web application built using Spring Boot, React.js, and MySQL.
This project was developed as part of the DAC final project.

--------------------------------------------------

PROJECT OVERVIEW

The i-Mart E-Commerce Application is a scalable and secure online shopping platform that allows users
to browse products, manage orders, and perform secure operations. It also provides admin
functionalities for managing products, users, and orders.

--------------------------------------------------

FEATURES

User Features:
- User registration and login
- Product browsing and detailed product view
- Add to cart and checkout
- Order placement and order history
- Secure authentication

Admin Features:
- Admin login
- Product management (Add / Update / Delete)
- Order management
- User management

Technical Features:
- RESTful APIs
- DTO-based architecture
- Global exception handling
- Input validations
- Responsive UI using Bootstrap

--------------------------------------------------

TECH STACK

Backend:
- Spring Boot
- Spring Data JPA (Hibernate)
- Spring Security
- RESTful APIs
- MySQL

Frontend:
- React.js
- Bootstrap CSS
- Axios
- React Router

Database:
- MySQL

--------------------------------------------------

ARCHITECTURE

- Controller Layer: Handles API requests
- Service Layer: Contains business logic
- Repository Layer: Handles database operations using JPA
- DTO Layer: Used for data transfer
- Global Exception Handling: Centralized error handling

--------------------------------------------------

PROJECT STRUCTURE

DAC_Project/
|
|-- spring_boot_backend/
|   |-- controller
|   |-- service
|   |-- repository
|   |-- dto
|   |-- entity
|   |-- exception
|
|-- src/ (React Frontend)
|   |-- components
|   |-- pages
|   |-- services
|   |-- App.js
|
|-- public/
|-- package.json
|-- README.txt

--------------------------------------------------

BACKEND SETUP

1. Navigate to backend directory:
   cd spring_boot_backend

2. Configure MySQL in application.properties:
   spring.datasource.url=jdbc:mysql://localhost:3306/imart_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password

3. Run the backend:
   mvn spring-boot:run

Backend runs on:
http://localhost:8080

--------------------------------------------------

FRONTEND SETUP

1. Navigate to project root:
   cd i-mart_project

2. Install dependencies:
   npm install

3. Start frontend:
   npm start

Frontend runs on:
http://localhost:3000

--------------------------------------------------

SECURITY

- Spring Security implementation
- Role-based access (USER / ADMIN)
- Secure authentication and authorization
- Input validation for APIs

--------------------------------------------------

FUTURE ENHANCEMENTS

- Payment gateway integration
- Wishlist functionality
- Product reviews and ratings
- Email notifications
- Docker support
- Cloud deployment (AWS / Azure)

--------------------------------------------------

CONTRIBUTOR
Heeranand kashyap
GitHub: https://github.com/goldenheera

Nikhil Patil
GitHub: https://github.com/NikhilPatil1010

Manoj Yadav
GitHub : https://github.com/lalit923

Dishant samudre
GitHub : https://github.com/dishantsamudre9
