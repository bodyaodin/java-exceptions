package com.projects.validator;

import com.projects.commands.Commands;
import com.projects.commands.RuleGroup;
import com.projects.exceptions.IncorrectValueException;
import com.projects.exceptions.ValueNotFoundException;
import com.sun.tools.javac.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Validator {
    private Map<RuleGroup, List<Rule<?>>> rules;


    public Validator() {
        rules = new HashMap<>();
    }

    public void addValueRule(RuleGroup ruleType, Rule<?> rule) {
        if (rules.containsKey(ruleType)) {
            rules.get(ruleType).add(rule);
        } else {
            List<Rule<?>> newRules = new ArrayList<>();
            newRules.add(rule);
            rules.put(ruleType, newRules);
        }
    }

    public void validate(Pair<String, String> pair) {
        try {
            Commands command = Commands.valueOf(pair.fst.toUpperCase());

            if (command.hasArgs()) {
                RuleGroup ruleGroup = command.getTypeRule();

                rules.get(ruleGroup)
                        .forEach(rule -> rule.validate(ruleGroup.convertArg(pair.snd)));
            }
        } catch (NumberFormatException e) {
            throw new IncorrectValueException("Arguments is not long:  " + pair.snd);
        } catch (IllegalArgumentException e) {
            throw new ValueNotFoundException("Invalid command: " + pair.fst);
        }
    }
}
