package com.example.homework33.Servlets;

import com.example.homework33.DAO.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delete")
public class deleteUserServlet extends HttpServlet {

    private final UserDAO userStorage = UserDAO.init();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        if(username == null || username.isBlank()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "empty value");
            return;
        }

        if(!userStorage.findByUsername(username)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "This user is not exist");
            return;
        }
        if(userStorage.deleteUser(username))
            req.setAttribute("message", "The user has been deleted");
        else
            req.setAttribute("message", "The user has not been deleted");
        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}
