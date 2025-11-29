package com.library.dao;

import com.library.model.Book;
import com.library.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    public boolean addBook(Book book) {
        String query = "INSERT INTO books(title, author, price) VALUES(?, ?, ?)";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setDouble(3, book.getPrice());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public List<Book> getAllBooks() {
        List<Book> list = new ArrayList<>();
        String query = "SELECT * FROM books";
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                list.add(new Book(
                        rs.getInt("bookId"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDouble("price")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Book getBookById(int id) {
        String query = "SELECT * FROM books WHERE bookId=?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Book(
                        rs.getInt("bookId"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getDouble("price")
                );
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean updateBook(int id, double newPrice) {
        String query = "UPDATE books SET price=? WHERE bookId=?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setDouble(1, newPrice);
            ps.setInt(2, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean deleteBook(int id) {
        String query = "DELETE FROM books WHERE bookId=?";
        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
}
