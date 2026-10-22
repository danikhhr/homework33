package com.example.homework33.DAO;

import com.example.homework33.Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public void addUser(User user) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "root");
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users" +
                    "(name, username, password) VALUES (?, ?, ?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "root");
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users"
            );
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                users.add(
                        new User(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4)
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public boolean findByUsername(String username) {

        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("DRIVER CLASS LOADED");
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "root"
            );

            PreparedStatement preparedStatement =
                    connection.prepareStatement(
                            "SELECT 1 FROM users WHERE username = ?"
                    );

            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteUser(String username) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "root");
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM users WHERE username = ?"
            );
            preparedStatement.setString(1, username);
            int resultSet = preparedStatement.executeUpdate();
            return resultSet > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean updateUsername(String oldUsername,
                                  String newUsername) {

        String sql = "UPDATE users SET username = ? WHERE username = ?";

        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("PostgreSQL DRIVER LOADED");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(
                    "PostgreSQL JDBC driver not found", e
            );
        }

        try (Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/postgres",
                "postgres",
                "root"
        );
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, newUsername);
            statement.setString(2, oldUsername);

            int result = statement.executeUpdate();

            return result > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
