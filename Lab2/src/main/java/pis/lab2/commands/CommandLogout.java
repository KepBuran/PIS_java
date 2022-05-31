package pis.lab2.commands;

import pis.lab2.manager.Config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommandLogout implements ICommand {
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        HttpSession session = request.getSession();
        session.invalidate();

        page = Config.getInstance().getProperty(Config.LOGIN);

        System.out.println("NextPage: "+page);
        return page;
    }

}
