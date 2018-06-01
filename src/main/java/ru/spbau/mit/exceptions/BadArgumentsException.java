package ru.spbau.mit.exceptions;

/**
 * Thrown when passing an unexpected number of arguments to a command
 */
public class BadArgumentsException extends Exception {

    public BadArgumentsException() {
        super();
    }

    public BadArgumentsException(String command) {
        super(command + ": can't resolve this count of arguments");
    }
}
