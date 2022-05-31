package pis.lab2.commands;

import pis.lab2.manager.Config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandGoToRegister implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;

        page = Config.getInstance().getProperty(Config.REGISTERUSER);

        request.setAttribute("registerStatus","GoTo");

        System.out.println("NextPage: "+page);
        return page;
    }
}
