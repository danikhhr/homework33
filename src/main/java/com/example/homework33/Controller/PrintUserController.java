package com.example.homework33.Controller;

import com.example.homework33.DAO.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class PrintUserController implements Controller{

    private final UserDAO userDAO;

    public PrintUserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(!req.getMethod().equalsIgnoreCase("GET")){
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            return;
        }

        req.setAttribute("users", userDAO.findAll());
        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}
