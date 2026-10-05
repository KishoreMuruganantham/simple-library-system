package com.dao;

import com.model.Author;
import com.utility.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {
    private DBConnection dbConnection = new DBConnection();

    public void save(Author author) {
        Connection conn = dbConnection.dbConnect();
        String sql = "INSERT INTO author (name, email) VALUES (?, ?)";
        try {
            PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, author.getName());
            pst.setString(2, author.getEmail());
            pst.executeUpdate();

            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {
                author.setId(generatedKeys.getInt(1));
            }
            System.out.println("record inserted...");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dbConnection.dbClose();
    }

    public Author findById(int id) {
        Connection conn = dbConnection.dbConnect();
        String sql = "SELECT * FROM author WHERE id = ?";
        Author author = null;
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rst = pst.executeQuery();
            if (rst.next()) {
                author = new Author(
                        rst.getInt("id"),
                        rst.getString("name"),
                        rst.getString("email")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dbConnection.dbClose();
        return author;
    }

    public List<Author> findAll() {
        Connection conn = dbConnection.dbConnect();
        String sql = "SELECT * FROM author";
        List<Author> list = new ArrayList<>();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                Author author = new Author(
                        rst.getInt("id"),
                        rst.getString("name"),
                        rst.getString("email")
                );
                list.add(author);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dbConnection.dbClose();
        return list;
    }
}