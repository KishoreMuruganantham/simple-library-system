package com.service;

import com.dao.BookDao;
import com.model.Book;

import java.util.List;

public class BookService {
    private BookDao bookDao = new BookDao();

    // Step 1: Prepare Book object with author and borrowedBy if any
    // Step 2: Save book to DB using dao (borrowed_by uses setNull when unborrowed)
    public void save(Book book) {
        bookDao.save(book);
    }

    // Step 1: Fetch book from DB by id using dao with JOIN author + LEFT JOIN member
    // Step 2: Return full object graph, null borrower stays null
    public Book findById(int id) {
        return bookDao.findById(id);
    }

    // Step 1: Fetch all books from DB using dao with same joins for all rows
    public List<Book> findAll() {
        return bookDao.findAll();
    }
}