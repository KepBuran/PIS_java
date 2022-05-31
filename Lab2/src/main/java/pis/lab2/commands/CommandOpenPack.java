package pis.lab2.commands;

import pis.lab2.entities.Question;
import pis.lab2.manager.Config;
import pis.lab2.services.PackService;
import pis.lab2.services.QuestionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommandOpenPack implements ICommand
{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        Long packId = Long.valueOf(request.getParameter("packId"));
        page = Config.getInstance().getProperty(Config.PACK);

       //String newPackName = request.getParameter("newPackName");

       PackService packService = new PackService();
       String packName = packService.getPackName(packId);
       System.out.println("Current packname: "+packName);
//
//       if (!(newPackName == null) && !(newPackName == packName)) {
//           ICommand changePackNameCommand = new CommandChangePackName();
//           changePackNameCommand.execute(request, response);
//           packName = packService.getPackName(packId);
//       }

        request.setAttribute("packName", packName);

        if (packId!=null) {
            QuestionService questionService = new QuestionService();
            request.setAttribute("questions", questionService.findQuestionsByPackId(packId));
        }

        return page;
    }
}
