package com.example.homework33.Controller;

import com.example.homework33.DAO.UserDAO;
import com.example.homework33.Model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AddUserController implements Controller{

    private final UserDAO userDAO;

    public AddUserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!req.getMethod().equalsIgnoreCase("GET")){
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return;
        }

        String name = req.getParameter("name");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (name == null && username == null && password == null) {
            req.getRequestDispatcher("/home.jsp").forward(req, resp);
            return;
        }

        if (name == null || name.isBlank() ||
                username == null || username.isBlank() ||
                password == null || password.isBlank()) {

            resp.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Empty value"
            );
            return;
        }

        if (userDAO.findByUsername(username)) {
            resp.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "User with this username already exists"
            );
            return;
        }

        userDAO.addUser(new User(name, username, password));
        req.setAttribute("message", "The user has been added");
        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}
