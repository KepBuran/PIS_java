package pis.lab2.commands;

import pis.lab2.manager.Config;
import pis.lab2.manager.Message;
import pis.lab2.services.PackService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommandChangePackName implements ICommand{
    private static final String PACKNAME = "packName";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        page = Config.getInstance().getProperty(Config.CHANGEPACK);
        Long packId = Long.valueOf(request.getParameter("packId"));
        System.out.println("Changing Pack: "+packId);

        PackService packService = new PackService();
        HttpSession session = request.getSession();

        String newPackName = request.getParameter("newPackName");

        String packName = packService.getPackName(packId);
        request.setAttribute("packName", packName);

        ICommand nextCommand = new CommandOpenPack();

        if (newPackName == null) {
            //request.setAttribute("message", Message.getInstance().getProperty(Message.EMPTY_PACKNAME));
            return page;
            //return nextCommand.execute(request, response);
        }
        if (newPackName.equals("")) {
            request.setAttribute("message", Message.getInstance().getProperty(Message.EMPTY_PACKNAME));
            return page;
            //return nextCommand.execute(request, response);
        }

        if (newPackName.equals(packName)){
            return nextCommand.execute(request, response);
        }

        if(!packService.changePackName(packId, newPackName, (Long) session.getAttribute("userId"))){
            request.setAttribute("message", Message.getInstance().getProperty(Message.PACKNAME_ALREADY_EXISTS));
            return page;
            //return nextCommand.execute(request, response);
        }

        //return page;
        return nextCommand.execute(request, response);
    }
}
