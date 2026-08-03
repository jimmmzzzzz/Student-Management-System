# Student Management System (Java + SQLite)

A clean, production-grade CLI Student Management System built in Java. It demonstrates layered architecture, object-oriented design principles, the DAO pattern, and JDBC database persistence using SQLite.

---

## 🚀 Features

- **Full CRUD Operations:** Add, view, edit, search, and delete student records.
- **SQLite Database Persistence:** Data is automatically created and stored locally in `students.db`.
- **Layered Architecture:** Strict separation between Presentation (CLI), Business Logic (Service), and Data Access (DAO).
- **SQL Injection Defense:** All queries use `PreparedStatement` parameters.
- **Smart Partial Updates:** Edit specific fields (e.g., just the GPA) while keeping remaining student details intact.
- **Formated Console Output:** Clean tabular list display with dynamic column alignment.

---

## 🛠️ Tech Stack

- **Language:** Java 17+
- **Database:** SQLite (Embedded)
- **Database Driver:** `org.xerial:sqlite-jdbc`
- **Build System:** Standard Java / Maven

---

## 📁 Project Structure

```text
src/
├── dao/
│   ├── StudentDao.java         # Data Access Object interface
│   └── StudentDaoImpl.java     # SQLite JDBC implementation
├── model/
│   └── Student.java            # Core Student domain entity
├── service/
│   └── StudentService.java     # Input validation & business logic
└── App.java                    # Entry point & interactive CLI
