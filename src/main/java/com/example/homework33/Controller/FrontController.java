package com.example.homework33.Controller;

import com.example.homework33.DAO.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/")
public class FrontController extends HttpServlet {

    private final Map<String, Controller> controllers = new HashMap<>();

    @Override
    public void init() throws ServletException {
        UserDAO userDAO = new UserDAO();

        controllers.put("/", new HomeController());
        controllers.put("/add", new AddUserController(userDAO));
        controllers.put("/delete", new DeleteUserController(userDAO));
        controllers.put("/findAll", new PrintUserController(userDAO));
        controllers.put("/upgrade-username", new UpgradeUserByUsernameController(userDAO));
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String path = req.getServletPath();

        if(path == null) {
            path = "/";
        }

        Controller controller = controllers.get(path);

        if(controller == null) {
            res.sendError(HttpServletResponse.SC_NOT_FOUND,
                    "Controller not found");
            return;
        }

        controller.execute(req, res);
    }
}
