package com.example.homework33.Servlets;

import com.example.homework33.Model.User;
import com.example.homework33.DAO.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/add")
public class addUserServlet extends HttpServlet {

    UserDAO userStorage = UserDAO.init();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if(name == null || name.isBlank() ||
        username == null || username.isBlank() ||
        password == null || password.isBlank()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Empty value");
            return;
        }

        if(userStorage.findByUsername(username)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "User with this username already exists");
            return;
        }

        userStorage.addUser(new User(name, username, password));
        req.setAttribute("message", "The user has been added");
        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}
