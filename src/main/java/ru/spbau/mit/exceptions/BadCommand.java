package ru.spbau.mit.exceptions;

public class BadCommand extends Exception {

    public BadCommand(String command) {
        super(command + " command not found");
    }
}
