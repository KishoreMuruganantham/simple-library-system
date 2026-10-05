package com.dao;

import com.model.Member;
import com.utility.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {
    private DBConnection dbConnection = new DBConnection();

    public void save(Member member) {
        Connection conn = dbConnection.dbConnect();
        String sql = "INSERT INTO member (name, email) VALUES (?, ?)";
        try {
            PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, member.getName());
            pst.setString(2, member.getEmail());
            pst.executeUpdate();

            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next()) {
                member.setId(generatedKeys.getInt(1));
            }
            System.out.println("record inserted...");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dbConnection.dbClose();
    }

    public Member findById(int id) {
        Connection conn = dbConnection.dbConnect();
        String sql = "SELECT * FROM member WHERE id = ?";
        Member member = null;
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rst = pst.executeQuery();
            if (rst.next()) {
                member = new Member(
                        rst.getInt("id"),
                        rst.getString("name"),
                        rst.getString("email")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dbConnection.dbClose();
        return member;
    }

    public List<Member> findAll() {
        Connection conn = dbConnection.dbConnect();
        String sql = "SELECT * FROM member";
        List<Member> list = new ArrayList<>();
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rst = pst.executeQuery();
            while (rst.next()) {
                Member member = new Member(
                        rst.getInt("id"),
                        rst.getString("name"),
                        rst.getString("email")
                );
                list.add(member);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        dbConnection.dbClose();
        return list;
    }
}