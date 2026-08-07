# i-Mart E-Commerce Application

i-Mart is a full-stack E-Commerce web application built using **Spring Boot**, **React.js (Vite)**, **Spring Security (JWT)**, and **MySQL**.

---

## 📋 Prerequisites

Before running the application, make sure you have the following installed on your machine:

- **Java JDK**: Version 17 or higher
- **Node.js**: Version 18+ (includes `npm`)
- **MySQL Server**: Version 8.0+ running on port `3306`
- **Maven**: (Optional, Maven Wrapper `./mvnw` is included in the project)

---

## 🛠️ Step-by-Step Guide to Run the Application

### 1️⃣ Database Setup (MySQL)

1. Make sure your MySQL Server is running.
2. Open MySQL Command Line or MySQL Workbench and log in:
   ```bash
   mysql -u root -p
   ```
3. Create the database (Optional: backend will automatically create it if enabled in `application.properties`):
   ```sql
   CREATE DATABASE IF NOT EXISTS imart_db;
   ```
4. Configure database credentials in `spring_boot_backend/src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/imart_db?createDatabaseIfNotExist=true
   spring.datasource.username=root
   spring.datasource.password=root
   ```

---

### 2️⃣ Run the Spring Boot Backend

Open **Terminal 1** (at project root `DAC_Project`):

```bash
# Navigate to backend directory
cd spring_boot_backend

# Clean and run Spring Boot Application
# On Windows (PowerShell / CMD):
.\mvnw spring-boot:run

# On Linux / macOS / Bash:
./mvnw spring-boot:run
```

*(Alternatively, if you have Maven installed globally)*:
```bash
mvn spring-boot:run
```

* **Backend Server**: Starts at `http://localhost:8080`
* **Swagger UI / API Docs**: `http://localhost:8080/swagger-ui.html`

---

### 3️⃣ Run the React Frontend

Open **Terminal 2** (at project root `DAC_Project`):

```bash
# Navigate to frontend directory
cd i-mart_frontend

# Install dependencies (First time setup)
npm install

# Start Vite Development Server
npm run dev
```

* **Frontend Server**: Starts at `http://localhost:5173` (or port displayed in terminal)

---

## ⚡ Summary of All Commands

| Action | Commands |
| :--- | :--- |
| **Start MySQL Service (Windows)** | `net start MySQL80` *(or via Services app)* |
| **Run Backend (PowerShell)** | `cd spring_boot_backend; .\mvnw spring-boot:run` |
| **Run Backend (Bash / Linux)** | `cd spring_boot_backend && ./mvnw spring-boot:run` |
| **Install Frontend Dependencies** | `cd i-mart_frontend; npm install` |
| **Run Frontend Dev Server** | `cd i-mart_frontend; npm run dev` |
| **Build Backend JAR** | `cd spring_boot_backend; .\mvnw clean package` |
| **Build Frontend Production** | `cd i-mart_frontend; npm run build` |

---

## 🏗️ Project Architecture & Tech Stack

### Tech Stack
* **Backend**: Java 17, Spring Boot, Spring Security (JWT), Spring Data JPA (Hibernate), REST APIs, Swagger / OpenAPI
* **Frontend**: React.js 19, Vite, React Router DOM, TailwindCSS / Custom Styling
* **Database**: MySQL 8.0

### Project Structure
```text
DAC_Project/
│
├── spring_boot_backend/          # Spring Boot Backend
│   ├── src/main/java/com/        # Controllers, Services, Repositories, Entities, Security
│   ├── src/main/resources/       # application.properties
│   ├── pom.xml                   # Maven dependencies
│   └── mvnw / mvnw.cmd           # Maven Wrapper executables
│
└── i-mart_frontend/              # React (Vite) Frontend
    ├── src/                      # Components, Pages, Context, Services
    ├── package.json              # NPM dependencies & scripts
    └── vite.config.js            # Vite configuration
```

---

## 🛡️ Features & Security

- **User Authentication**: JWT-based login and registration (Role-based: `USER`, `ADMIN`).
- **Product Catalog**: Browse products, search, and view product details.
- **Cart & Orders**: Add items to cart, adjust quantities, place orders, view order history.
- **Admin Panel**: Manage products, users, and orders.

---

## 👥 Contributors

- **Nikhil Patil** - [GitHub](https://github.com/NikhilPatil1010)
- **Heeranand Kashyap** - [GitHub](https://github.com/goldenheera)
- **Manoj Yadav** - [GitHub](https://github.com/lalit923)
- **Dishant Samudre** - [GitHub](https://github.com/dishantsamudre9)

