# Simple Library System - Task 1

Basic CRUD operations for Library Management using pure Java + JDBC (Java 21).

## Requirements
- Java 21
- MySQL Server running on localhost:3306
- MySQL user: root, password: deepcoder

## Project Structure
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
└── README.md
```

## Database Setup
```sql
mysql -u root -p < library.sql
```
Enter password: `deepcoder`

This creates:
- Database: `library_db`
- Tables: `author`, `member`, `books`
- Seed data: 2 authors, 2 members, 3 books

## Compile
```bash
javac -cp "lib/mysql-connector-j-9.1.0.jar" -d out src/com/**/*.java
```

## Run
```bash
java -cp "out;lib/mysql-connector-j-9.1.0.jar" com.main.App
```

## Expected Output
```
record inserted...
record inserted...
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

## Entities
- **Author**: id, name, email
- **Member**: id, name, email
- **Book**: id, title, publishedYear, Genre, BookStatus, Author, Member (borrowedBy)

## Enums
- **Genre**: FICTION, NON_FICTION, SCIENCE
- **BookStatus**: AVAILABLE, BORROWED

## DAO Methods (exactly 9)
- AuthorDao: save, findById, findAll
- MemberDao: save, findById, findAll
- BookDao: save, findById (with JOIN author + LEFT JOIN member), findAll (same joins)

## Notes
- Pure Java + JDBC, no Spring, no Hibernate, no Maven
- DriverManager utility, PreparedStatement, manual ResultSet mapping
- BookStatus stored as given (dumb-faithful-storage)
- Service methods carry step comments (// Step 1: ... // Step 2: ...)