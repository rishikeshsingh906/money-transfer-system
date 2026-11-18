# Money Transfer System

A backend REST API built using **Java, Spring Boot, Spring Data JPA, Hibernate, and MySQL** to demonstrate basic banking operations such as account creation, deposits, money transfers, balance enquiries, and transaction history.

## Features

- Create bank accounts
- Retrieve account details
- Deposit money into an account
- Transfer money between accounts
- Check account balances
- View transaction history
- Request validation using Jakarta Validation
- Global exception handling
- Database transaction management using `@Transactional`
- Monetary calculations using `BigDecimal`

## Tech Stack

| Technology | Purpose |
|---|---|
| Java 21 | Programming language |
| Spring Boot | Backend framework |
| Spring Web | REST API development |
| Spring Data JPA | Database operations |
| Hibernate | ORM |
| MySQL | Relational database |
| Jakarta Validation | Request validation |
| Maven | Build and dependency management |
| Postman | API testing |

## Project Architecture

The project follows a layered architecture:

```text
Client / Postman
       |
       v
Controller Layer
       |
       v
Service Layer
(Business Logic)
       |
       v
Repository Layer
(Spring Data JPA)
       |
       v
MySQL Database
```

### Package Structure

```text
com.moneytransfer
|
├── controller
│   ├── AccountController.java
│   └── TransferController.java
|
├── service
│   ├── AccountService.java
│   └── TransferService.java
|
├── repository
│   ├── AccountRepository.java
│   └── TransferRepository.java
|
├── entity
│   ├── Account.java
│   └── TransferRecord.java
|
├── dto
│   ├── AccountRequestDto.java
│   ├── AccountResponseDto.java
│   ├── DepositRequestDto.java
│   ├── TransferRequestDto.java
│   └── TransferResponseDto.java
|
├── exception
│   ├── GlobalExceptionHandler.java
│   ├── ResourceNotFoundException.java
│   └── InsufficientBalanceException.java
|
└── MoneyTransferSystemApplication.java
```

## REST API Endpoints

Base URL: `http://localhost:8080`

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/accounts` | Create an account |
| GET | `/api/accounts/{accountNumber}` | Get account details |
| POST | `/api/accounts/{accountNumber}/deposit` | Deposit money |
| POST | `/api/transfers` | Transfer money |
| GET | `/api/transfers/history/{accountNumber}` | View transaction history |

## Money Transfer Example

**Request**

`POST /api/transfers`

```json
{
  "fromAccountNumber": "ACC1001",
  "toAccountNumber": "ACC1002",
  "amount": 1500
}
```

**Sample response**

```json
{
  "transferId": 1,
  "fromAccountNumber": "ACC1001",
  "toAccountNumber": "ACC1002",
  "amount": 1500,
  "status": "SUCCESS",
  "transferDate": "2026-11-18T07:10:15"
}
```

## How Money Transfer Works

1. The client sends a transfer request.
2. The controller receives and validates the request.
3. The service checks whether both accounts exist.
4. The service checks the sender's available balance.
5. The sender's account is debited.
6. The receiver's account is credited.
7. The transfer record is saved.
8. The transaction is committed.

The transfer service uses `@Transactional` so that database changes within the transaction are rolled back if an exception occurs.

## Database Tables

### 1. accounts

Stores account information and account balances.

### 2. transfer_records

Stores money transfer details, including sender, receiver, amount, status, and transfer date.

## Getting Started

### Prerequisites

- JDK 21
- MySQL Server
- IntelliJ IDEA or another Java IDE
- Postman
- Git

### 1. Clone the repository

```bash
git clone https://github.com/rishikeshsingh906/money-transfer-system.git
```

### 2. Open the project

Open the project directory in IntelliJ IDEA.

### 3. Create the MySQL database

```sql
CREATE DATABASE money_transfer_db;
```

### 4. Configure environment variables

The application uses environment variables for database configuration.

```text
DB_URL=jdbc:mysql://localhost:3306/money_transfer_db
DB_USERNAME=root
DB_PASSWORD=your_mysql_password
```

Set `DB_PASSWORD` to your own MySQL password. Do not commit real passwords or credentials to GitHub.

### 5. Run the application

On Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

The application will start at:

```text
http://localhost:8080
```

## Learning Outcomes

Through this project, I practised:

- Building RESTful APIs with Spring Boot
- Implementing layered architecture
- Using Spring Data JPA and Hibernate
- Connecting Spring Boot with MySQL
- Implementing CRUD operations
- Working with DTOs
- Validating API requests
- Handling exceptions globally
- Managing database transactions
- Using `BigDecimal` for monetary values
- Testing REST APIs using Postman
- Managing source code using Git and GitHub

## Future Improvements

- Spring Security and JWT authentication
- Role-based authorization
- Automated unit and integration testing
- Concurrency control for simultaneous transfers
- Idempotency for transfer requests
- Docker-based deployment

## Disclaimer

This is an educational backend project developed for learning and demonstrating Java and Spring Boot concepts. It is not a production-ready banking or payment application.

## Author

**Rishikesh Kumar Singh**

GitHub: [rishikeshsingh906](https://github.com/rishikeshsingh906)

Project Repository: [Money Transfer System](https://github.com/rishikeshsingh906/money-transfer-system)
