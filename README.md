# Student Result Management System (Java + JDBC + MySQL)

---

## About Project
- The Student Result Management System is a console-based Java application that helps manage student academic records efficiently.
- It connects to a MySQL database using JDBC to store and retrieve data.
- With this system, you can add new students, view all students, search by roll number, and delete records.
- The program also calculates the total marks and assigns a grade automatically based on predefined criteria, making result management fast and error-free.

---

## Features
- **Add Student**: Insert a student's name, roll number, and marks into the database.
- **View All Students**: Display all student records with marks, totals, and grades.
- **Search Student**: Find a student by roll number.
- **Delete Student**: Remove a student's record by roll number.
- **Automatic Grade Calculation**:
  - A: Total > 255
  - B: Total > 210
  - C: Total > 165
  - D: Total > 120
  - E: Total > 75
  - F: Otherwise

---

## Technologies Used
- **Java** (JDK 8 or above)
- **MySQL** (8.0 or above recommended)
- **JDBC** (MySQL Connector/J)

---

## Database Setup
1. Create a database in MySQL:
    ```sql
    CREATE DATABASE project1;
    ```
2. Use the database:
    ```sql
    USE project1;
    ```
3. Create the `marksheet` table:
    ```sql
    CREATE TABLE marksheet (
        roll_no INT PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        english INT NOT NULL,
        maths INT NOT NULL,
        science INT NOT NULL,
        total INT NOT NULL,
        grade VARCHAR(2) NOT NULL
    );
    ```

---

## How to Run (Intellij/Eclipse)
1. **Clone this repository**:
    ```bash
    git clone https://github.com/your-username/StudentManagement.git
    cd sStudentManagement
    ```
2. **Open in IntelliJ IDEA / Eclipse**.
3. **Update Database Credentials** in `Main.java`:
    ```java
    private static final String url = "jdbc:mysql://localhost:3306/project1";
    private static final String username = "root";
    private static final String password = "your_password";
    ```
4. **Add MySQL Connector/J** to your project classpath.
5. **Run the application** from your IDE or command line:
    ```bash
    javac Main.java
    java Main
    ```

---
