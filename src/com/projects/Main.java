package com.projects;

import com.projects.commands.CommandParser;
import com.projects.exceptions.*;
import com.projects.listener.CommandListener;
import com.projects.managers.DataManager;
import com.projects.managers.Manager;
import com.projects.validator.Rule;
import com.projects.validator.Validator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.projects.commands.RuleGroup.*;

public class Main {

    private static final String ASTERISK = "*";
    private static final String DOUBLE_AMPERSAND = "&&";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Long, String> URLs = new HashMap<>();

        Validator validator = new Validator();
        Rule<String> forbiddenSymbolRule = new Rule<>(Main::signValidation, () -> {
            throw new ForbiddenSymbolException("Entered URL contains forbidden symbols: " + ASTERISK + " or " + DOUBLE_AMPERSAND + "!");
        });
        Rule<String> containsElementRule = new Rule<>(URLs::containsValue, () -> {
            throw new MapContainsSuchElementException("Map already contains passed URL!");
        });
        Rule<Long> incorrectIdRule = new Rule<>(i -> i <= 0, () -> {
            throw new IncorrectValueException("Entered ID is less or equal to zero!");
        });

        validator.addValueRule(URL, forbiddenSymbolRule);
        validator.addValueRule(URL, containsElementRule);
        validator.addValueRule(ID, incorrectIdRule);

        Manager dataManager = new DataManager(URLs);
        CommandParser commandParser = new CommandParser(validator);
        CommandListener commandListener = new CommandListener(dataManager, commandParser);

        System.out.println("Please enter commands - add (URL), delete (ID) or getAll");
        commandListener.start(scanner);
    }

    private static boolean signValidation(String url) {
        return url.contains(ASTERISK) ||
                url.contains(DOUBLE_AMPERSAND);
    }
}
