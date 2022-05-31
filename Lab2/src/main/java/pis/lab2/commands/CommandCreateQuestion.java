package pis.lab2.commands;

import pis.lab2.manager.Config;
import pis.lab2.manager.Message;
import pis.lab2.services.PackService;
import pis.lab2.services.QuestionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommandCreateQuestion implements ICommand{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = Config.getInstance().getProperty(Config.CREATEQUESTION);
        HttpSession session = request.getSession();

        String question = request.getParameter("question");
        String answer = request.getParameter("answer");

        if (question == null || answer == null) {
            return page;
        }

        if (question.equals("") || answer.equals("")) {
            request.setAttribute("message", Message.getInstance().getProperty(Message.EMPTY_QUESTION));
            return page;
        }

        QuestionService questionService = new QuestionService();

        questionService.create(question, answer, Long.parseLong(request.getParameter("packId")));

        ICommand nextCommand = new CommandOpenPack();

        return nextCommand.execute(request, response);
    }
}
