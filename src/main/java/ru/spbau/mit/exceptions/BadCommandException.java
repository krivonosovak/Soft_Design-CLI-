package ru.spbau.mit.exceptions;

/**
 * Thrown when trying to invoke an unknown command that does not match any external process name
 */
public class BadCommandException extends Exception {

    public BadCommandException(String command) {
        super(command + " command not found");
    }
}
