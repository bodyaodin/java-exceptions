package com.projects.listener;

import com.projects.commands.CommandParser;
import com.projects.commands.Commands;
import com.projects.exceptions.DataException;
import com.projects.managers.Manager;
import com.sun.tools.javac.util.Pair;

import java.util.Scanner;

public class CommandListener {

    private final Manager dataManager;
    private final CommandParser commandParser;

    private boolean isExit = false;

    public CommandListener(Manager dataManager, CommandParser commandParser) {
        this.dataManager = dataManager;
        this.commandParser = commandParser;
    }

    public void start(Scanner scanner) {
        while (!isExit) {
            try {
                String command = scanner.nextLine();
                execute(command);
            } catch (DataException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private boolean checkExit(String command) {
        isExit = command.equals("exit");
        return isExit;
    }

    private void execute(String command) throws DataException {
        if (!checkExit(command)) {
            Pair<String, String> pair = commandParser.parse(command);
            Commands currentCommand = Commands.valueOf(pair.fst.toUpperCase());
            System.out.println(currentCommand.operation(pair.snd, dataManager));
        } else {
            System.out.println("Good bye!");
        }
    }
}
