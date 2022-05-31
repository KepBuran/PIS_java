package pis.lab2.commands;

import pis.lab2.manager.Config;
import pis.lab2.manager.Message;
import pis.lab2.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommandRegisterUser implements ICommand{
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        HttpSession session = request.getSession();

        if (session.getAttribute("registerStatus") == null) {
            session.setAttribute("registerStatus", "do");
            return Config.getInstance().getProperty(Config.REGISTERUSER);
        }

        if (session.getAttribute("registerStatus").equals("GoTo")) {
            session.setAttribute("registerStatus", "do");
            return Config.getInstance().getProperty(Config.REGISTERUSER);
        }


        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);

        page = Config.getInstance().getProperty(Config.REGISTERUSER);

        UserService userService = new UserService();

        if (username == null || username.equals("") || password == null || password.equals("")) {
            request.setAttribute("message", "Username and password cannot be empty!");
            return page;
        }


        if (!userService.registerUser(username, password)) {
            request.setAttribute("message", "This username is already occupied :(");
            return page;
        }

        page = Config.getInstance().getProperty(Config.MAIN);
        session.setAttribute("username", username);
        session.setAttribute("userId", userService.getUserIdByUsername(username));

        System.out.println("NextPage: "+page);
        System.out.println("");

        return page;
    }
}
