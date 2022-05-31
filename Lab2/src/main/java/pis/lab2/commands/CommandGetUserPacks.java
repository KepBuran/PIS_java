package pis.lab2.commands;

import pis.lab2.manager.Config;
import pis.lab2.services.PackService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommandGetUserPacks implements ICommand {
    private static final String USER_ID = "userId";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        HttpSession session = request.getSession();
        session.setAttribute("createPackStatus", "GoTo");
        Long userId = (Long) session.getAttribute(USER_ID);
        page = Config.getInstance().getProperty(Config.USERPACKS);

        if (userId!=null) {
            PackService packService = new PackService();
            request.setAttribute("userPacks", packService.findUserPacks(userId));
        }

        return page;
    }
}
