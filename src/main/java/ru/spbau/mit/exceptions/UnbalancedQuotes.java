package ru.spbau.mit.exceptions;

public class UnbalancedQuotes extends Exception{

    public UnbalancedQuotes(String parseLine) {
        super("unbalanced quotes in" + parseLine);
    }
}
