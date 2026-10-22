package com.example.homework33.Servlets;

import com.example.homework33.DAO.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/find")
public class findAllUsersServlet extends HttpServlet {

    private final UserDAO userStorage = UserDAO.init();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", userStorage.findAll());
        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}
