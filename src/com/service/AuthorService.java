package com.service;

import com.dao.AuthorDao;
import com.model.Author;

import java.util.List;

public class AuthorService {
    private AuthorDao authorDao = new AuthorDao();

    // Step 1: Prepare Author object
    // Step 2: Save author to DB using dao
    public void save(Author author) {
        authorDao.save(author);
    }

    // Step 1: Fetch author from DB by id using dao
    public Author findById(int id) {
        return authorDao.findById(id);
    }

    // Step 1: Fetch all authors from DB using dao
    public List<Author> findAll() {
        return authorDao.findAll();
    }
}