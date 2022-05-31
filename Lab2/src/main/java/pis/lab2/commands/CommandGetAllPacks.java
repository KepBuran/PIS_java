package pis.lab2.commands;

import pis.lab2.manager.Config;
import pis.lab2.services.PackService;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CommandGetAllPacks implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;

        page = Config.getInstance().getProperty(Config.ALLPACKS);

        PackService packService = new PackService();
        request.setAttribute("packs", packService.findAllPacks());


        return page;
    }


}

