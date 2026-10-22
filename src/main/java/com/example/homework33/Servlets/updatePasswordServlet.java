package com.example.homework33.Servlets;

import com.example.homework33.DAO.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/update-password")
public class updatePasswordServlet extends HttpServlet {

    private final UserDAO userStorage = UserDAO.init();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String oldPassword = req.getParameter("oldPassword");
        String newPassword = req.getParameter("newPassword");

        if(oldPassword == null || oldPassword.isBlank() ||
        newPassword == null || newPassword.isBlank()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "empty value");
            return;
        }

        if(oldPassword.equals(newPassword)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Old password cannot be equal to the new password");
            return;
        }

        if (userStorage.updatePassword(oldPassword, newPassword))
            req.setAttribute("message", "The password has been updated");
        else
            req.setAttribute("message", "The password has not been updated");
        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}
