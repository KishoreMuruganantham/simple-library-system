package com.dao;

import com.model.Author;
import com.model.Book;
import com.model.Member;
import com.utility.DBConnection;
import com.enums.BookStatus;
import com.enums.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    private DBConnection dbConnection = new DBConnection();

    public void save(Book book) {
        Connection conn = dbConnection.dbConnect();
        String sql = "INSERT INTO books (title, published_year, genre, book_status, author_id, borrowed_by) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, book.getTitle());
            pst.setInt(2, book.getPublishedYear());
            pst.setString(3, book.getGenre().name());
            pst.setString(4, book.getBookStatus().name());
            pst.setInt(5, book.getAuthor().getId());
            if (book.getBorrowedBy() != null) {
                pst.setInt(6, book.getBorrowedBy().getId());
            } else {
                pst.setNull(6, Types.INTEGER);
            }
            pst.executeUpdate();

            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {
                book.setId(generatedKeys.getInt(1));
            }
            System.out.println("record inserted...");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dbConnection.dbClose();
    }

    public Book findById(int id) {
        Connection conn = dbConnection.dbConnect();
        String sql = "SELECT b.*, a.name as author_name, a.email as author_email, " +
                "m.name as member_name, m.email as member_email " +
                "FROM books b " +
                "JOIN author a ON b.author_id = a.id " +
                "LEFT JOIN member m ON b.borrowed_by = m.id " +
                "WHERE b.id = ?";
        Book book = null;
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rst = pst.executeQuery();
            if (rst.next()) {
                Author author = new Author(
                        rst.getInt("author_id"),
                        rst.getString("author_name"),
                        rst.getString("author_email")
                );

                Member borrowedBy = null;
                if (rst.getObject("borrowed_by") != null) {
                    borrowedBy = new Member(
                            rst.getInt("borrowed_by"),
                            rst.getString("member_name"),
                            rst.getString("member_email")
                    );
                }

                book = new Book(
                        rst.getInt("id"),
                        rst.getString("title"),
                        rst.getInt("published_year"),
                        Genre.valueOf(rst.getString("genre")),
                        BookStatus.valueOf(rst.getString("book_status")),
                        author,
                        borrowedBy
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dbConnection.dbClose();
        return book;
    }

    public List<Book> findAll() {
        Connection conn = dbConnection.dbConnect();
        String sql = "SELECT b.*, a.name as author_name, a.email as author_email, " +
                "m.name as member_name, m.email as member_email " +
                "FROM books b " +
                "JOIN author a ON b.author_id = a.id " +
                "LEFT JOIN member m ON b.borrowed_by = m.id";
        List<Book> list = new ArrayList<>();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                Author author = new Author(
                        rst.getInt("author_id"),
                        rst.getString("author_name"),
                        rst.getString("author_email")
                );

                Member borrowedBy = null;
                if (rst.getObject("borrowed_by") != null) {
                    borrowedBy = new Member(
                            rst.getInt("borrowed_by"),
                            rst.getString("member_name"),
                            rst.getString("member_email")
                    );
                }

                Book book = new Book(
                        rst.getInt("id"),
                        rst.getString("title"),
                        rst.getInt("published_year"),
                        Genre.valueOf(rst.getString("genre")),
                        BookStatus.valueOf(rst.getString("book_status")),
                        author,
                        borrowedBy
                );
                list.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dbConnection.dbClose();
        return list;
    }
}