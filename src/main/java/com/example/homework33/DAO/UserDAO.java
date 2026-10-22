package com.example.homework33.DAO;

import com.example.homework33.Model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private static UserDAO userStorage;

    private UserDAO() {}

    public static UserDAO init() {
        if(userStorage == null)
            userStorage = new UserDAO();
        return userStorage;
    }

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
                    "DELETE * FROM users WHERE username = ?"
            );
            preparedStatement.setString(1, username);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateUsername(String oldUsername, String newUsername) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres");
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE users SET username = ? WHERE username = ?"
            );
            preparedStatement.setString(1, newUsername);
            preparedStatement.setString(2, oldUsername);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updatePassword(String oldPassword, String newPassword) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "root");
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE users SET password = ? WHERE password = ?"
            );
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, oldPassword);
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
