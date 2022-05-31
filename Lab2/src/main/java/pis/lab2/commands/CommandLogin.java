package pis.lab2.commands;

import pis.lab2.manager.Config;
import pis.lab2.manager.Message;
import pis.lab2.services.PackService;
import pis.lab2.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommandLogin implements ICommand{
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);
        HttpSession session = request.getSession();
        session.setAttribute("registerStatus", "GoTo");

        page = Config.getInstance().getProperty(Config.MAIN);

        UserService userService = new UserService();

        if (!userService.checkAuthorization(username, password)) {
            page = Config.getInstance().getProperty(Config.LOGIN);
            request.setAttribute("message", Message.getInstance().getProperty(Message.LOGIN_ERROR));
            return page;
        }


        session.setAttribute("username", username);
        request.setAttribute("username", username);
        session.setAttribute("userId", userService.getUserIdByUsername(username));

        System.out.println("NextPage: "+page);
        System.out.println("");

        return page;
    }
}
