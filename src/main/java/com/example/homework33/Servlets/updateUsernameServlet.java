package com.example.homework33.Servlets;

import com.example.homework33.DAO.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/update-username")
public class updateUsernameServlet extends HttpServlet {

    private final UserDAO userStorage = UserDAO.init();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oldUsername = req.getParameter("oldUsername");
        String newUsername = req.getParameter("newUsername");

        if(oldUsername == null || oldUsername.isBlank() ||
                 newUsername== null || newUsername.isBlank()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "empty value");
            return;
        }

        if(oldUsername.equals(newUsername)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Old username cannot be equal to the new password");
            return;
        }

        if (userStorage.updateUsername(oldUsername, newUsername))
            req.setAttribute("message", "The username has been updated");
        else
            req.setAttribute("message", "The username has not been updated");
        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}
