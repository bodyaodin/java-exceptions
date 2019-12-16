package com.projects.commands;

import com.projects.managers.Manager;

import static com.projects.commands.RuleGroup.*;

public enum Commands {

    ADD(true, URL) {
        @Override
        public String operation(String arg, Manager manager) {
            return manager.addURL(arg).toString();
        }
    },
    DELETE(true, ID) {
        @Override
        public String operation(String arg, Manager manager) {
            return manager.deleteURL(Long.parseLong(arg));
        }
    },
    GETALL(false, NONE) {
        @Override
        public String operation(String arg, Manager manager) {
            return manager.getAll().toString();
        }
    };

    private final boolean hasArgs;
    private final RuleGroup ruleType;

    Commands(boolean hasArgs, RuleGroup ruleType) {
        this.hasArgs = hasArgs;
        this.ruleType = ruleType;
    }

    public abstract String operation(String arg, Manager manager);

    public boolean hasArgs() {
        return hasArgs;
    }

    public RuleGroup getTypeRule() {
        return ruleType;
    }
}
