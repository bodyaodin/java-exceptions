package com.projects.commands;

import com.projects.validator.Validator;
import com.sun.tools.javac.util.Pair;

public class CommandParser {

    private static final String EMPTY_ARG = "";

    private final Validator validator;

    public CommandParser(Validator validator) {
        this.validator = validator;
    }

    public Pair<String, String> parse(String command) {
        command = command.trim();
        Pair<String, String> pair;

        if (checkArgs(command)) {
            String[] commandArray = command.split(" ");
            pair = Pair.of(commandArray[0], commandArray[1]);
        } else {
            pair = Pair.of(command, EMPTY_ARG);
        }

        validator.validate(pair);
        return pair;
    }

    private boolean checkArgs (String command) {
        return command.contains(" ");
    }


}
