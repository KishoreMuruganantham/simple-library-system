# Simple Library System - Task 1

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
[![JDBC](https://img.shields.io/badge/JDBC-MySQL-blue.svg)](https://dev.mysql.com/downloads/connector/j/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE.md)
[![Build](https://img.shields.io/badge/Build-Passing-brightgreen.svg)]()

Basic CRUD operations for Library Management using **pure Java + JDBC (Java 21)**.  
No Spring, no Hibernate, no Maven — just plain Java, `DriverManager`, `PreparedStatement`, and manual `ResultSet` mapping.

---

## 📋 Project Overview

This project implements **Task 1** of the Hexaware Java + React FSD training case study (Sep 2026 batch).  
It demonstrates a clean, layered JDBC application following the trainer's classroom patterns:

- **3 Entities**: `Author`, `Member`, `Book`
- **2 Enums**: `Genre` (FICTION, NON_FICTION, SCIENCE), `BookStatus` (AVAILABLE, BORROWED)
- **9 DAO Methods**: save/findById/findAll for each entity
- **Service Layer** with step-commented methods (`// Step 1: ... // Step 2: ...`)
- **MySQL Database** with proper FK relationships and ENUM columns
- **MySQL Workbench Model** (`.mwb`) included

---

## 🗂 Project Structure

```
simple-library-system/
├── lib/
│   └── mysql-connector-j-9.1.0.jar
├── src/
│   └── com/
│       ├── model/
│       │   ├── Author.java
│       │   ├── Member.java
│       │   └── Book.java
│       ├── enums/
│       │   ├── Genre.java
│       │   └── BookStatus.java
│       ├── dao/
│       │   ├── AuthorDao.java
│       │   ├── MemberDao.java
│       │   └── BookDao.java
│       ├── service/
│       │   ├── AuthorService.java
│       │   ├── MemberService.java
│       │   └── BookService.java
│       ├── utility/
│       │   └── DBConnection.java
│       └── main/
│           └── App.java
├── library.sql
├── library.mwb
├── LICENSE.md
└── README.md
```

---

## 🛠 Requirements

- **Java 21** (JDK 21+)
- **MySQL Server** running on `localhost:3306`
- **MySQL User**: `root` | **Password**: `deepcoder` (classroom credential)
- **MySQL Connector/J 9.1.0** (provided in `lib/`)

---

## 🗄 Database Setup

### Option 1: Full schema + seeds (from `library.sql`)
```sql
CREATE DATABASE library_db;
USE library_db;
SOURCE library.sql;
```

### Option 2: Schema only (clean slate for demo)
```sql
CREATE DATABASE library_db;
USE library_db;
SOURCE schema-only.sql;  -- create this from library.sql without INSERTs
```

The `library.sql` creates:
- Database: `library_db`
- Tables: `author`, `member`, `books` (with FKs)
- Seed data: 2 authors, 2 members, 3 books (1 BORROWED, 2 AVAILABLE)
- Demo SELECT queries left in (trainer habit)

---

## ⚙️ Compile & Run

### Compile
```bash
javac -cp "lib/mysql-connector-j-9.1.0.jar" -d out src/com/enums/*.java src/com/model/*.java src/com/utility/*.java src/com/dao/*.java src/com/service/*.java src/com/main/*.java
```

### Run
```bash
# Windows (PowerShell)
java -cp "out;lib/mysql-connector-j-9.1.0.jar" com.main.App

# Linux/macOS
java -cp "out:lib/mysql-connector-j-9.1.0.jar" com.main.App
```

---

## 📤 Expected Output

```
record inserted...
record inserted...
record inserted...
record inserted...
record inserted...
record inserted...
record inserted...
---------- Book findById (id=1) ----------
Book{id=1, title='1984', publishedYear=1949, genre=FICTION, bookStatus=AVAILABLE, author=Author{id=1, name='George Orwell', email='orwell@example.com'}, borrowedBy=null}
---------- Author findAll ----------
Author{id=1, name='George Orwell', email='orwell@example.com'}
Author{id=2, name='Jane Austen', email='austen@example.com'}
---------- Member findAll ----------
Member{id=1, name='John Smith', email='john@example.com'}
Member{id=2, name='Alice Brown', email='alice@example.com'}
---------- Book findAll ----------
Book{id=1, title='1984', publishedYear=1949, genre=FICTION, bookStatus=AVAILABLE, author=Author{id=1, name='George Orwell', email='orwell@example.com'}, borrowedBy=null}
Book{id=2, title='Pride and Prejudice', publishedYear=1813, genre=FICTION, bookStatus=AVAILABLE, author=Author{id=2, name='Jane Austen', email='austen@example.com'}, borrowedBy=null}
Book{id=3, title='Animal Farm', publishedYear=1945, genre=FICTION, bookStatus=BORROWED, author=Author{id=1, name='George Orwell', email='orwell@example.com'}, borrowedBy=Member{id=1, name='John Smith', email='john@example.com'}}
```

---

## 🧱 Entities

| Entity | Fields |
|--------|--------|
| **Author** | `id`, `name`, `email` |
| **Member** | `id`, `name`, `email` |
| **Book** | `id`, `title`, `publishedYear`, `Genre genre`, `BookStatus bookStatus`, `Author author`, `Member borrowedBy` |

---

## 🏷 Enums

- **Genre**: `FICTION`, `NON_FICTION`, `SCIENCE`
- **BookStatus**: `AVAILABLE`, `BORROWED`

---

## 📚 DAO Methods (Exactly 9)

| DAO | Methods |
|-----|---------|
| **AuthorDao** | `save(author)`, `findById(id)`, `findAll()` |
| **MemberDao** | `save(member)`, `findById(id)`, `findAll()` |
| **BookDao** | `save(book)` — uses `setNull` for unborrowed `borrowed_by`<br>`findById(id)` — JOIN author + LEFT JOIN member, full object graph<br>`findAll()` — same joins for all rows |

---

## 🎯 Trainer Style Fingerprints (Mirrored from `pl-sql` branch)

- Package layout: `com.model`, `com.enums`, `com.dao`, `com.service`, `com.utility`, `com.main`
- Entity style: default + all-args constructors, getters/setters, `toString()` (like `Employee.java`)
- `DBConnection`: `DriverManager`, hardcoded `root`/`deepcoder`, `Class.forName` + `getConnection`
- Comments: short, practical, broken classroom English (`// Step 1: ... // Step 2: ...`)
- Print style: `list.forEach(System.out :: println);` (spaces around `::`)
- SQL: lowercase snake_case tables, ENUMs, FKs, demo selects left in `library.sql`
- BookStatus stored as-is (dumb-faithful-storage, no hidden logic)
- `BookDao.save`: `borrowed_by` uses `setNull` when unborrowed
- `BookDao.findById/findAll`: JOIN author + LEFT JOIN member; null borrower stays null, no crash
- Starter named `App.java` with plain `main`
- No Javadoc, no Lombok, no pools, no Optional chains, no badges/emojis in code

---

## 📄 License

This project is licensed under the **MIT License** — see [LICENSE.md](LICENSE.md) for details.

---

## 🏷 Tags

- `java` `jdbc` `mysql` `crud` `hexaware` `training` `task1` `library-management` `java21`

---

## 👨‍💻 Author

**Kishore Muruganantham**  
Hexaware Technologies — Java + React FSD, Sep 2026 Batch  
Trainer: Imtiyaz Hirani  
GitHub: [@KishoreMuruganantham](https://github.com/KishoreMuruganantham)  
Email: Kishore.muruganantham@gmail.com

---

## 📦 Release

- **v0.1.0** — Task 1 Basic CRUD (partial case study, NOT v1.0.0)  
  [Release Notes](https://github.com/KishoreMuruganantham/simple-library-system/releases/tag/v0.1.0)