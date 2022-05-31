package pis.lab2.commands;

import pis.lab2.manager.Config;
import pis.lab2.manager.Message;
import pis.lab2.services.PackService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



public class CommandCreatePack implements ICommand{
    private static final String PACKNAME = "packName";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = Config.getInstance().getProperty(Config.CREATEPACK);
        HttpSession session = request.getSession();

        if (session.getAttribute("createPackStatus").equals("GoTo")) {
            session.setAttribute("createPackStatus", "do");
            return page;
        }

        String packName = request.getParameter(PACKNAME);
        System.out.println("PACK NAME: "+packName);

        if (packName == null) {
            request.setAttribute("message", Message.getInstance().getProperty(Message.EMPTY_PACKNAME));
            return page;
        }
        if (packName.equals("")) {
            request.setAttribute("message", Message.getInstance().getProperty(Message.EMPTY_PACKNAME));
            return page;
        }

        PackService packService = new PackService();

        if(!packService.createPack(packName, (Long) session.getAttribute("userId"))){
            request.setAttribute("message", Message.getInstance().getProperty(Message.PACKNAME_ALREADY_EXISTS));
            return page;
        }

        ICommand nextCommand = new CommandGetUserPacks();

        return nextCommand.execute(request, response);
    }
}
