# Bank Management System
Simple Bank Management System built with **Spring Boot**, **PostgreSQL**, and **Docker**. Supports user management, deposit, withdraw, and transfer transactions.

## Features
- Create and manage user accounts
- Deposit, withdraw, and transfer money
- Track transaction history for each user
- Secure authentication with email and password
  
## Requirements
- Docker & Docker Compose
- Java 17+
- Maven

## Setup

### 1. Clone
```bash
git clone https://github.com/mragilsa/bank-management-system.git
```
```
cd bank-management-system
```
Open the project in your preferred IDE to inspect and run the code.

### 2. Setup Environment Variables
Create .env file in project root:
```
POSTGRES_USER=your_postgres_user
POSTGRES_PASSWORD=your_postgres_password
POSTGRES_DB=your_postgres_db
```
> **Note:** Make sure these values match what is set in `src/main/resources/application.properties` for Spring Boot datasource.

### 3. Run Database with Docker Compose
```
docker-compose up -d
```

### 4. Build & Run
```
mvn clean install
```
```
mvn spring-boot:run
```
Access the app at: http://localhost:8080/swagger-ui/index.html

## Selected API Endpoints

### Users
- `PUT /api/users/me` – Update your account name
- `GET /api/users/me/balance` – Check your account balance  

### Transactions
- `POST /api/transactions/deposit` – Deposit money  
- `POST /api/transactions/withdraw` – Withdraw money  
- `POST /api/transactions/transfer` – Transfer money  
- `GET /api/transactions` – List all your transactions  

### Auth
- `POST /api/auth/register` – Register a new user  
- `POST /api/auth/login` – Login to your account  

For full API documentation, check Swagger UI:
http://localhost:8080/swagger-ui/index.html
