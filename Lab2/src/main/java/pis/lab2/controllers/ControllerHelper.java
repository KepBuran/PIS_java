package pis.lab2.controllers;

import pis.lab2.commands.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class ControllerHelper {

    private static ControllerHelper instance = null;
    HashMap<String, ICommand> commands = new HashMap<>();


    private ControllerHelper() {
        commands.put("login", new CommandLogin());
        commands.put("getAllPacks", new CommandGetAllPacks());
        commands.put("getUserPacks", new CommandGetUserPacks());
        commands.put("openPack", new CommandOpenPack());
        commands.put("logout", new CommandLogout());
        commands.put("registerUser", new CommandRegisterUser());
        commands.put("createPack", new CommandCreatePack());
        commands.put("createQuestion", new CommandCreateQuestion());
        commands.put("goToRegisterUser", new CommandGoToRegister());
        commands.put("deletePack", new CommandDeletePack());
        commands.put("changePackName", new CommandChangePackName());
        commands.put("deleteQuestion", new CommandDeleteQuestion());
    }

    public ICommand getCommand(HttpServletRequest request) {
        //String commandName = "getUserPacks";
        String commandName = request.getParameter("command");
        System.out.println("Executing command: "+commandName);
        ICommand command = commands.get(commandName);

        HttpSession session = request.getSession();
//        session.setAttribute("username", "anton");
//        session.setAttribute("userId", new Long(20));

        String username = (String) session.getAttribute("username");

        if (command == null && username == null) {
            session.setAttribute("registerStatus", "GoTo");
            command = new CommandGoToLogin();
        }
        if (command == null)
            command = new CommandMissing();

        if (command.equals(username))
            return command;

        return command;
    }

    public static ControllerHelper getInstance() {
        if (instance == null) {
            instance = new ControllerHelper();
        }

        return instance;
    }


}
