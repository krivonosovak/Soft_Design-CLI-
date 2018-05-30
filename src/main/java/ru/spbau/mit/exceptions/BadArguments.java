package ru.spbau.mit.exceptions;

public class BadArguments extends Exception {

    public BadArguments() {
        super();
    }

    public BadArguments(String command) {
        super(command + ": can't resolve this count of arguments");
    }
}
