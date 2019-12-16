package com.projects.commands;

public enum RuleGroup {
    URL {
        @Override
        public String convertArg(String arg) {
            return arg;
        }
    },
    ID {
        @Override
        public Long convertArg(String arg) {
            return Long.parseLong(arg);
        }
    },
    NONE {
        @Override
        public String convertArg(String empty) {
            return empty;
        }
    };

    public abstract <T> T convertArg(String arg);
}
