package com.example.homework33.Controller;

import com.example.homework33.DAO.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DeleteUserController implements Controller{

    private final UserDAO userDAO;

    public DeleteUserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!req.getMethod().equalsIgnoreCase("GET")){
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return;
        }

        String username = req.getParameter("username");

        if (username == null || username.isBlank()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "empty value");
            return;
        }

        if (!userDAO.findByUsername(username)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "This user is not exist");
            return;
        }
        if (userDAO.deleteUser(username))
            req.setAttribute("message", "The user has been deleted");
        else
            req.setAttribute("message", "The user has not been deleted");
        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}
