package pis.lab2.commands;

import pis.lab2.manager.Config;
import pis.lab2.services.PackService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandDeletePack implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Deleting Pack: "+request.getParameter("packId"));
        String page = null;

        //page = Config.getInstance().getProperty(Config.USERPACKS);

        PackService packService = new PackService();
        packService.deletePack(Long.valueOf(request.getParameter("packId")));

        ICommand nextCommand = new CommandGetUserPacks();

        return nextCommand.execute(request, response);
    }
}
