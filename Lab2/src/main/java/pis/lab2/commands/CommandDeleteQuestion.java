package pis.lab2.commands;

import pis.lab2.manager.Config;
import pis.lab2.services.PackService;
import pis.lab2.services.QuestionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandDeleteQuestion implements ICommand{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;

        QuestionService questionService = new QuestionService();
        questionService.deleteQuestion(Long.valueOf(request.getParameter("questionId")));

        ICommand nextCommand = new CommandOpenPack();

        return nextCommand.execute(request, response);

    }
}
