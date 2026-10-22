package com.example.homework33.Controller;

import com.example.homework33.DAO.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class UpgradeUserByUsernameController implements Controller {

    private final UserDAO userDAO;

    public UpgradeUserByUsernameController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!req.getMethod().equalsIgnoreCase("GET")){
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return;
        }

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

        if (userDAO.updateUsername(oldUsername, newUsername))
            req.setAttribute("message", "The username has been updated");
        else
            req.setAttribute("message", "The username has not been updated");
        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}
