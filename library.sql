-- library.sql - run after: CREATE DATABASE library_db;
use library_db;

-- table author
create table author(
id INT primary key auto_increment,
name varchar(255) NOT NULL,
email varchar(255)
);

-- table member
create table member(
id INT primary key auto_increment,
name varchar(255) NOT NULL,
email varchar(255)
);

-- table books
create table books(
id INT primary key auto_increment,
title varchar(255) NOT NULL,
published_year INT,
genre ENUM('FICTION','NON_FICTION','SCIENCE'),
book_status ENUM('AVAILABLE','BORROWED'),
author_id INT NOT NULL,
borrowed_by INT NULL,
FOREIGN KEY (author_id) REFERENCES author(id),
FOREIGN KEY (borrowed_by) REFERENCES member(id)
);

desc author;
desc member;
desc books;

-- seed 2 authors
insert into author(name, email) values 
('George Orwell', 'orwell@example.com'),
('Jane Austen', 'austen@example.com');

-- seed 2 members
insert into member(name, email) values
('John Smith', 'john@example.com'),
('Alice Brown', 'alice@example.com');

-- seed 3 books (at least one BORROWED with member, at least two AVAILABLE unborrowed)
insert into books(title, published_year, genre, book_status, author_id, borrowed_by) values
('1984', 1949, 'FICTION', 'AVAILABLE', 1, NULL),
('Pride and Prejudice', 1813, 'FICTION', 'AVAILABLE', 2, NULL),
('Animal Farm', 1945, 'FICTION', 'BORROWED', 1, 1);

-- demo selects
select * from author;
select * from member;
select * from books;

-- demo join for book with author and member
select b.*, a.name as author_name, a.email as author_email,
       m.name as member_name, m.email as member_email
from books b
join author a on b.author_id = a.id
left join member m on b.borrowed_by = m.id;