package com.model;

import com.enums.BookStatus;
import com.enums.Genre;

public class Book {
    private int id;
    private String title;
    private int publishedYear;
    private Genre genre;
    private BookStatus bookStatus;
    private Author author;
    private Member borrowedBy;

    public Book() {
    }

    public Book(int id, String title, int publishedYear, Genre genre, BookStatus bookStatus, Author author, Member borrowedBy) {
        this.id = id;
        this.title = title;
        this.publishedYear = publishedYear;
        this.genre = genre;
        this.bookStatus = bookStatus;
        this.author = author;
        this.borrowedBy = borrowedBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Member getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(Member borrowedBy) {
        this.borrowedBy = borrowedBy;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publishedYear=" + publishedYear +
                ", genre=" + genre +
                ", bookStatus=" + bookStatus +
                ", author=" + author +
                ", borrowedBy=" + borrowedBy +
                '}';
    }
}