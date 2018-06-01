package ru.spbau.mit.exceptions;

/**
 * Thrown when an expression being parsed contains non-matching amount of quotes
 */
public class UnbalancedQuotesException extends Exception {

    public UnbalancedQuotesException(String parseLine) {
        super("unbalanced quotes in" + parseLine);
    }
}
